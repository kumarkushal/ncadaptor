package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.GetNodeInstanceOutput;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * Operational information about the device
 *
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;ncadaptor&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/ncadaptor.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container node-oper-data {
 *     leaf oper-node {
 *         type boolean;
 *     }
 *     leaf device-name {
 *         type string;
 *     }
 *     leaf device-family {
 *         type string;
 *     }
 *     leaf yang-revision {
 *         type string;
 *     }
 *     leaf deviceVendor {
 *         type string;
 *     }
 *     leaf deviceOS {
 *         type string;
 *     }
 *     leaf deviceOSVersion {
 *         type string;
 *     }
 *     leaf json-url {
 *         type string;
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;ncadaptor/get-node-instance/output/node-oper-data&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperDataBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperDataBuilder
 *
 */
public interface NodeOperData
    extends
    ChildOf<GetNodeInstanceOutput>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:ncadaptor","2015-01-05","node-oper-data"));

    /**
     * Default true indicates the device is operational
     *
     */
    java.lang.Boolean isOperNode();
    
    /**
     * name of the netconf device
     *
     */
    java.lang.String getDeviceName();
    
    /**
     * Device family - custom family/groups to segregate devices
     *
     */
    java.lang.String getDeviceFamily();
    
    /**
     * Any one yang-revision of the device model to be used .ex. 2016-07-22
     *
     */
    java.lang.String getYangRevision();
    
    /**
     * Device vendor details such as Cisco, Juniper and Brocade
     *
     */
    java.lang.String getDeviceVendor();
    
    /**
     * Device operating system such as IOS, JunOS etc.
     *
     */
    java.lang.String getDeviceOS();
    
    /**
     * Version of the propriety OS on the device
     *
     */
    java.lang.String getDeviceOSVersion();
    
    /**
     * Local path for the JSON file in string format. This URL is set by user in the 
     * excel file
     *
     */
    java.lang.String getJsonUrl();

}

