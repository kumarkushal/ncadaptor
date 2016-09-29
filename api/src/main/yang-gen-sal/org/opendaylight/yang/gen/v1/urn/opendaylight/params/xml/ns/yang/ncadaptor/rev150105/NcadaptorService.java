package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import java.util.concurrent.Future;


/**
 * Interface for implementing the following YANG RPCs defined in module &lt;b&gt;ncadaptor&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/ncadaptor.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * rpc get-node-instance {
 *     "Retreives required config/operational information from datastore";
 *     input {
 *         leaf node-name {
 *             type string;
 *         }
 *     }
 *     
 *     output {
 *         container node-cfg-data {
 *             leaf jsonURI {
 *                 type string;
 *             }
 *         }
 *         container node-oper-data {
 *             leaf oper-node {
 *                 type boolean;
 *             }
 *             leaf device-name {
 *                 type string;
 *             }
 *             leaf device-family {
 *                 type string;
 *             }
 *             leaf yang-revision {
 *                 type string;
 *             }
 *             leaf deviceVendor {
 *                 type string;
 *             }
 *             leaf deviceOS {
 *                 type string;
 *             }
 *             leaf deviceOSVersion {
 *                 type string;
 *             }
 *             leaf json-url {
 *                 type string;
 *             }
 *         }
 *     }
 *     status CURRENT;
 * }
 * rpc modify-rpc-payload {
 *     input {
 *         leaf initial-rpc-payload {
 *             type string;
 *         }
 *         leaf device-name {
 *             type string;
 *         }
 *     }
 *     
 *     output {
 *         leaf final-rpc-payload {
 *             type string;
 *         }
 *     }
 *     status CURRENT;
 * }
 * rpc parse-json-file {
 *     input {
 *         leaf json-url {
 *             type string;
 *         }
 *         leaf init-rpc {
 *             type string;
 *         }
 *     }
 *     
 *     output {
 *         leaf mod-rpc {
 *             type string;
 *         }
 *     }
 *     status CURRENT;
 * }
 * rpc setup-kie-base {
 *     input {
 *         leaf kie-session-name {
 *             type string;
 *         }
 *     }
 *     
 *     output {
 *         leaf kie-session-status {
 *             type boolean;
 *         }
 *     }
 *     status CURRENT;
 * }
 * &lt;/pre&gt;
 *
 */
public interface NcadaptorService
    extends
    RpcService
{




    /**
     * Retreives required config/operational information from datastore
     *
     */
    Future<RpcResult<GetNodeInstanceOutput>> getNodeInstance(GetNodeInstanceInput input);
    
    Future<RpcResult<ModifyRpcPayloadOutput>> modifyRpcPayload(ModifyRpcPayloadInput input);
    
    Future<RpcResult<ParseJsonFileOutput>> parseJsonFile(ParseJsonFileInput input);
    
    Future<RpcResult<SetupKieBaseOutput>> setupKieBase(SetupKieBaseInput input);

}

