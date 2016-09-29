package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;ncadaptor&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/ncadaptor.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container input {
 *     leaf kie-session-name {
 *         type string;
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;ncadaptor/setup-kie-base/input&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.SetupKieBaseInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.SetupKieBaseInputBuilder
 *
 */
public interface SetupKieBaseInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.SetupKieBaseInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:ncadaptor","2015-01-05","input"));

    /**
     * Kie session instance ex. ksession-rules
     *
     */
    java.lang.String getKieSessionName();

}

