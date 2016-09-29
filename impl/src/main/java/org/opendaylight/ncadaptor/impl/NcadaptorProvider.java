/*
 * DTV, INC and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.ncadaptor.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kie.api.runtime.KieSession;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
import org.opendaylight.controller.md.sal.binding.api.MountPoint;
import org.opendaylight.controller.md.sal.binding.api.MountPointService;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.ReadFailedException;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.RpcRegistration;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.network.topology.topology.topology.types.TopologyNetconf;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.GetNodeInstanceInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.GetNodeInstanceOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.GetNodeInstanceOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.ModifyRpcPayloadInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.ModifyRpcPayloadInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.ModifyRpcPayloadOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.NcadaptorService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.ParseJsonFileInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.ParseJsonFileOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.ParseJsonFileOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.SetupKieBaseInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.SetupKieBaseOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperDataBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.NodeKey;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
//import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public class NcadaptorProvider implements NcadaptorService, BindingAwareProvider, AutoCloseable {

	private static final Logger LOG = LoggerFactory.getLogger(NcadaptorProvider.class);

	public static final InstanceIdentifier<Topology> NETCONF_TOPO_IID = InstanceIdentifier.create(NetworkTopology.class)
			.child(Topology.class, new TopologyKey(new TopologyId(TopologyNetconf.QNAME.getLocalName())));

	/*
	 * private static final ReleaseId APP_REL_ID = KieServices.Factory.get()
	 * .newReleaseId("org.opendaylight.ncadaptor.impl", "odl-ncadaptor",
	 * "1.0-SNAPSHOT"); private static final String EXTERNAL_XLS_RESOURCE =
	 * "/home/directv/ncadaptor/DecisionTable.xls";
	 */

	private RpcRegistration<NcadaptorService> rpcReg;

	private ListenerRegistration<DataChangeListener> dclReg;
	private MountPointService mountService;
	private DataBroker dataBroker;
	private RpcResult<Void> SUCCESS = RpcResultBuilder.<Void>success().build();

	private JSONParser parser;
	private XMLReader reader;
	private KieSession ksession;

	public static Map<String, Object> nodeHandlerMap = Collections.unmodifiableMap(new HashMap<String, Object>());
	public static Map<String, List<String>> attrHandlerMap = Collections
			.unmodifiableMap(new HashMap<String, List<String>>());
	public static Map<String, List<String>> textHandlerMap = Collections
			.unmodifiableMap(new HashMap<String, List<String>>());

	@Override
	public void onSessionInitiated(ProviderContext session) {
		LOG.info("NcadaptorProvider Session Initiated");

		/*
		 * KieServices ks = KieServices.Factory.get(); KieContainer kcont =
		 * ks.newKieClasspathContainer(getClass().getClassLoader()); KieBase
		 * kbase = kcont.getKieBase("sampleKBase"); this.ksession =
		 * kbase.newKieSession();
		 */

		NodeOperDataBuilder nodeOperDataBuilder = new NodeOperDataBuilder().setDeviceName("QFX")
				.setDeviceFamily("OFSwitch");
		/*
		 * .setDeviceOS("JunOS") .setDeviceOSVersion("1.5")
		 * .setDeviceVendor("Juniper") .setYangRevision("2016-05-19")
		 */

		// this.getNodeInstance(new
		// GetNodeInstanceInputBuilder().setNodeName("testtool").build());
		/*
		 * ksession.insert(nodeOperDataBuilder);
		 * 
		 * // Fire the rules ksession.fireAllRules();
		 */

		System.out.println("JSON URL is : " + nodeOperDataBuilder.getJsonUrl());

		// Get references to the data broker and mount service
		this.mountService = session.getSALService(MountPointService.class);
		this.dataBroker = session.getSALService(DataBroker.class);

		// Register ourselves as the REST API RPC implementation
		this.rpcReg = session.addRpcImplementation(NcadaptorService.class, this);

		parser = new JSONParser();
		reader = new XMLReader();

		String strXML = "<rpc xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\" message-id=\"m-0\">\n"
				+ "<get-config>\n" + "<source>\n" + "<running/>\n" + "</source>\n"
				+ "<filter xmlns:ns0=\"urn:ietf:params:xml:ns:netconf:base:1.0\" ns0:type=\"subtree\"/>\n"
				+ "</get-config>\n" + "</rpc>";

		LOG.info("Started ncadaptor service");

		this.modifyRpcPayload(
				new ModifyRpcPayloadInputBuilder().setDeviceName("testtool").setInitialRpcPayload(strXML).build());

	}

	public Future<RpcResult<ModifyRpcPayloadOutput>> modifyRpcPayload(ModifyRpcPayloadInput input) {

		Future<RpcResult<String>> jsonURL = null;
		ModifyRpcPayloadOutput modifyRpcPayloadOutput = null;

		// Future<RpcResult<GetNodeInstanceOutput>> future = this
		// .getNodeInstance(new
		// GetNodeInstanceInputBuilder().setNodeName(input.getDeviceName()).build());

		// Multiple handlers for the same tag can aslo be added

		reader.addHandler("filter", new NodeHandler() {
			// @Override
			public void process(org.w3c.dom.Node node) {
				DOMImplementationLS lsImpl = (DOMImplementationLS) node.getOwnerDocument().getImplementation()
						.getFeature("LS", "3.0");
				LSSerializer serializer = lsImpl.createLSSerializer();
				serializer.getDomConfig().setParameter("xml-declaration", false);
				String str = serializer.writeToString(node);
				System.out.println("FILTER TAG MODIFIED : \n\n" + str);
			}
		});

		try {
			// Document doc =
			// this.convertStringToDocument(input.getInitialRpcPayload());
			// InputStream is = this.document2InputStream(doc);
			reader.parse(new FileInputStream(
					"/home/directv/ncadaptor/impl/src/main/java/org/opendaylight/ncadaptor/impl/rpc.xml"));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return RpcResultBuilder.success(modifyRpcPayloadOutput).buildFuture();
	}

	@Override
	public Future<RpcResult<ParseJsonFileOutput>> parseJsonFile(ParseJsonFileInput input) {

		String returnMessage = "";

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(new File(input.getJsonUrl())));
			/*
			 * reader.addHandler((String) jsonObject.get("node"), new
			 * NodeHandler() { public void process(StructuredNode node) {
			 * LOG.info("Node details {}", node.getNodeName()); } });
			 */
			nodeHandlerMap = jsonToMap(jsonObject);
			// Document doc = null;
			// reader.parse(document2InputStream(doc));
			// returnMessage = rpcParser.parse(input.getInitRpc(),
			// nodeHandlerMap);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		/*
		 * catch (ParserConfigurationException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (SAXException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		ParseJsonFileOutput parseJsonFileOutput = new ParseJsonFileOutputBuilder().setModRpc(returnMessage).build();
		// parser= null;
		// rpcParser = null;
		return RpcResultBuilder.success(parseJsonFileOutput).buildFuture();
	}

	public static Map<String, Object> jsonToMap(JSONObject json) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if (json != null) {
			retMap = toMap(json);
		}
		return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keySet().iterator();
		while (keysItr.hasNext()) {
			String key = keysItr.next();

			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			} else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public static List<Object> toList(JSONArray array) {
		List<Object> list = new ArrayList<Object>();

		for (int i = 0; i < array.size(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			} else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;

	}

	private URL filePath(String location) throws MalformedURLException {
		URL url = (new File(location)).toURI().toURL();
		LOG.info("External Resource Location : " + url.toString());
		return url;
	}

	/**
	 * A method called when the session to MD-SAL is closed. It closes
	 * registrations in MD-SAL Listener with the MD-SAL
	 */
	@Override
	public void close() throws Exception {
		LOG.info("NcappProvider Closed");
		// Clean up the RPC service registration
		if (rpcReg != null) {
			rpcReg.close();
		}
		// Clean up the Data Change Listener registration
		if (dclReg != null) {
			dclReg.close();
		}
	}

	public static InputStream document2InputStream(Document document) throws IOException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(document);
		Result outputTarget = new StreamResult(outputStream);
		try {
			TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

		return is;
	}

	public static File documetToFile(Document doc, String filename) {
		File file = new File(filename);

		try {
			// Prepare the DOM document for writing
			Source source = new DOMSource(doc);

			// Prepare the output file
			Result result = new StreamResult(file);
			// Write the DOM document to the file
			Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
		} catch (TransformerException e) {
		}
		return file;
	}

	@Override
	public Future<RpcResult<GetNodeInstanceOutput>> getNodeInstance(GetNodeInstanceInput input) {
		GetNodeInstanceOutput getNodeInstanceOutput = null;
		final Optional<MountPoint> xrNodeOptional = mountService
				.getMountPoint(NETCONF_TOPO_IID.child(Node.class, new NodeKey(new NodeId(input.getNodeName()))));
		Preconditions.checkArgument(xrNodeOptional.isPresent(),
				"Unable to locate mountpoint: %s, not mounted yet or not configured", input.getNodeName());
		final MountPoint xrNode = xrNodeOptional.get();
		// Get the DataBroker for the mounted node
		final DataBroker dataBroker = xrNode.getService(DataBroker.class).get();
		// Start a new read only transaction that we will use to read data
		// from the device

		final ReadOnlyTransaction xrNodeReadTx = dataBroker.newReadOnlyTransaction();
		InstanceIdentifier<NodeOperData> iid = InstanceIdentifier.create(NodeOperData.class);
		Optional<NodeOperData> nodeOperDataID;

		try {
			// Read from a transaction is asynchronous, but a simple
			// get/checkedGet makes the call synchronous
			nodeOperDataID = xrNodeReadTx.read(LogicalDatastoreType.OPERATIONAL, iid).checkedGet();
		} catch (ReadFailedException e) {
			throw new IllegalStateException("Unexpected error reading data from " + input.getNodeName(), e);
		}

		if (nodeOperDataID.isPresent()) {

			LOG.info("Node oper data : {}", nodeOperDataID.get().getDeviceName());
			getNodeInstanceOutput = new GetNodeInstanceOutputBuilder().setNodeOperData(nodeOperDataID.get()).build();

			/*
			 * NodeOperData nodeOperData = new
			 * NodeOperDataBuilder().setDeviceFamily("OFSwitch").setDeviceName(
			 * "QFX")
			 * .setDeviceOS("JunOS").setDeviceOSVersion("1.5").setDeviceVendor(
			 * "Juniper").setYangRevision("2016-05-19") .build();
			 */
		} else {
			LOG.error("nodeOperDataID is null");
		}

		return RpcResultBuilder.success(getNodeInstanceOutput).buildFuture();

	}

	@Override
	public Future<RpcResult<SetupKieBaseOutput>> setupKieBase(SetupKieBaseInput input) {
		// TODO Auto-generated method stub
		return null;
	}

}