package org.opendaylight.ncadaptor.drools.osgi;

import org.kie.api.runtime.KieSession;
import org.opendaylight.ncadaptor.drools.model.Cheese;
import org.opendaylight.ncadaptor.drools.rule.EntityHelper;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperDataBuilder;

public class NcadaptorDroolsImpl {

	public static int fireRulesKieSession(KieSession ksession) {

		int numOfRulesFired = 0;
		NodeOperDataBuilder nodeOperDataBuilder = new NodeOperDataBuilder().setDeviceName("QFX")
				.setDeviceFamily("OFSwitch");

		for (int i = 0; i < 10; i++) {
			// Create a Cheese
			Cheese aCheese = EntityHelper.createCheese();
			ksession.insert(nodeOperDataBuilder);
			// Fire the rules
			numOfRulesFired = ksession.fireAllRules();
			// Check Cheese Price
			EntityHelper.cheesePrice(aCheese);
		}

		System.out.println("Cheese added and rules fired.");
		return numOfRulesFired;
	}

}
