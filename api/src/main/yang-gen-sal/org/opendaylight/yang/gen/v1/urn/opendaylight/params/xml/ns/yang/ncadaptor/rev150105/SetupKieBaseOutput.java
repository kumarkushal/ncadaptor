package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;ncadaptor&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/ncadaptor.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container output {
 *     leaf kie-session-status {
 *         type boolean;
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;ncadaptor/setup-kie-base/output&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.SetupKieBaseOutputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.SetupKieBaseOutputBuilder
 *
 */
public interface SetupKieBaseOutput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.SetupKieBaseOutput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:ncadaptor","2015-01-05","output"));

    /**
     * Indicates if kie-session instance is created or not
     *
     */
    java.lang.Boolean isKieSessionStatus();

}

