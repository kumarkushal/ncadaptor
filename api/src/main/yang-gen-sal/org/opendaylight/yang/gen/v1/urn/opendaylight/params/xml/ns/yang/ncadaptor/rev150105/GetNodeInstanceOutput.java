package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeCfgData;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;ncadaptor&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/ncadaptor.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container output {
 *     container node-cfg-data {
 *         leaf jsonURI {
 *             type string;
 *         }
 *     }
 *     container node-oper-data {
 *         leaf oper-node {
 *             type boolean;
 *         }
 *         leaf device-name {
 *             type string;
 *         }
 *         leaf device-family {
 *             type string;
 *         }
 *         leaf yang-revision {
 *             type string;
 *         }
 *         leaf deviceVendor {
 *             type string;
 *         }
 *         leaf deviceOS {
 *             type string;
 *         }
 *         leaf deviceOSVersion {
 *             type string;
 *         }
 *         leaf json-url {
 *             type string;
 *         }
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;ncadaptor/get-node-instance/output&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.GetNodeInstanceOutputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.GetNodeInstanceOutputBuilder
 *
 */
public interface GetNodeInstanceOutput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.GetNodeInstanceOutput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:ncadaptor","2015-01-05","output"));

    NodeCfgData getNodeCfgData();
    
    /**
     * Operational information about the device
     *
     */
    NodeOperData getNodeOperData();

}

