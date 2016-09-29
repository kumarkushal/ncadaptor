package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.GetNodeInstanceOutput;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;ncadaptor&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/ncadaptor.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container node-cfg-data {
 *     leaf jsonURI {
 *         type string;
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;ncadaptor/get-node-instance/output/node-cfg-data&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeCfgDataBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeCfgDataBuilder
 *
 */
public interface NodeCfgData
    extends
    ChildOf<GetNodeInstanceOutput>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeCfgData>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:ncadaptor","2015-01-05","node-cfg-data"));

    /**
     * Indicates the path of the JSON file containing information for various tags and 
     * operations in the RPC XML sent to the device. The value of this leaf is set by 
     * the Bussiness Rules engine when a rule is fired, to the cell value(under heading
     * \Set JSON URI\) in the rules table(excel)
     *
     */
    java.lang.String getJsonURI();

}

