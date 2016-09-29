package org.opendaylight.ncadaptor.drools.osgi;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieSession;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class NcadaptorDroolsOsgiActivator implements BundleActivator {

    private static final ReleaseId APP_REL_ID = KieServices.Factory.get()
            .newReleaseId("org.opendaylight.ncadaptor.drools", "drools", "1.0-SNAPSHOT");
    private static final String EXTERNAL_XLS_RESOURCE = "file:///home/directv/ncadaptor/DecisionTable.xls";

    private KieSession ksession;

    private NcadaptorDroolsImpl ncadaptorDroolsImpl; 
    @Override
    public void start(BundleContext context) throws Exception {

        KieServices ks = KieServices.Factory.get();
        ks.newKieClasspathContainer(getClass().getClassLoader());
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        KieBase kbase = createKieBase();

        ksession = kbase.newKieSession();
        System.out.println("KieSession created.");
        NcadaptorDroolsImpl.fireRulesKieSession(ksession);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (ksession != null) {
            ksession.dispose();
            System.out.println("KieSession disposed.");
        }
    }

    private KieBase createKieBase() {

        final String PACKAGE_NAME = "org.opendaylight.ncadaptor.drools.cheese";

        KieServices ks = KieServices.Factory.get();
        Resource rs = ks.getResources().newUrlResource(EXTERNAL_XLS_RESOURCE);

        KieFileSystem kfs = ks.newKieFileSystem()
                .generateAndWritePomXML(APP_REL_ID)
                .write(rs)
                .writeKModuleXML(createKieProjectWithPackages(ks, PACKAGE_NAME).toXML());

        ks.newKieBuilder( kfs ).buildAll();

        KieBuilder kbuilder = ks.newKieBuilder(kfs);
        kbuilder.buildAll();

        return ks.newKieContainer(APP_REL_ID).getKieBase();
    }

    private KieModuleModel createKieProjectWithPackages(KieServices ks, String pkg) {
        KieModuleModel kmodule = ks.newKieModuleModel();
        kmodule.newKieBaseModel("KBase")
               .addPackage(pkg)
               .setDefault(true)
               .newKieSessionModel("defaultSession")
                  .setDefault(true);

        return kmodule;
    }
}
