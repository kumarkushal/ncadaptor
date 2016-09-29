package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.impl.rev141210.modules.module.configuration;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.impl.rev141210.modules.module.configuration.ncadaptor.Broker;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.modules.module.Configuration;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;ncadaptor-impl&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/ncadaptor-impl.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * case ncadaptor {
 *     container broker {
 *         leaf type {
 *             type leafref;
 *         }
 *         leaf name {
 *             type leafref;
 *         }
 *         uses service-ref {
 *             refine (urn:opendaylight:params:xml:ns:yang:ncadaptor:impl?revision=2014-12-10)type {
 *                 leaf type {
 *                     type leafref;
 *                 }
 *             }
 *         }
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;ncadaptor-impl/modules/module/configuration/(urn:opendaylight:params:xml:ns:yang:ncadaptor:impl?revision=2014-12-10)ncadaptor&lt;/i&gt;
 *
 */
public interface Ncadaptor
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.impl.rev141210.modules.module.configuration.Ncadaptor>,
    Configuration
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:ncadaptor:impl","2014-12-10","ncadaptor"));

    Broker getBroker();

}

