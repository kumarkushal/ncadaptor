/*
 * Copyright (c) 2015 Cisco Systems and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.ncadaptor.impl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.Attributes;

/**
 * Used to create a dom-tree for incoming nodes.
 */

class SAX2DOMHandler {

	private Document document;
	private Node root;
	private Node currentNode;
	private NodeHandler nodeHandler;

	public SAX2DOMHandler(NodeHandler handler, String uri, String name,
			Attributes attributes) throws ParserConfigurationException {
		this.nodeHandler = handler;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder loader = factory.newDocumentBuilder();
		document = loader.newDocument();
		createElement(name, attributes);
	}

	private boolean nodeUp() {
		if (isComplete()) {
			//nodeHandler.process(new XMLNodeImpl(root));
			nodeHandler.process(root);
			DOMImplementationLS lsImpl = (DOMImplementationLS)root.getOwnerDocument().getImplementation().getFeature("LS", "3.0");
			LSSerializer serializer = lsImpl.createLSSerializer();
			serializer.getDomConfig().setParameter("xml-declaration", false); //by default its true, so set it to false to get String without xml-declaration
			String str = serializer.writeToString(root);
			XMLReader.sbf.append(str);
			return true;
		}
		currentNode = currentNode.getParentNode();
		return false;
	}

	private boolean isComplete() {
		return currentNode.equals(root);
	}

	private void createElement(String name, Attributes attributes) {
		Element element = document.createElement(name);
		for (int i = 0; i < attributes.getLength(); i++) {
			String attrName = attributes.getLocalName(i);
			if (attrName == null || "".equals(attrName)) {
				attrName = attributes.getQName(i);
			}
			if (attrName != null || !"".equals(attrName)) {
				element.setAttribute(attrName, attributes.getValue(i));
			}
		}
		// To close selfclosing tag
		element.setTextContent(" ");
		if (currentNode != null) {
			currentNode.appendChild(element);
		} else {
			root = element;
			document.appendChild(element);
		}
		currentNode = element;
		//document.getElementsByTagName(name).item(0).setTextContent(" ");
	//System.out.println("document object");
	}

	public Node getRoot() {
		return root;
	}

	public void startElement(String uri, String name, Attributes attributes) {
		createElement(name, attributes);
	}

	public void processingInstruction(String target, String data) {
		ProcessingInstruction instruction = document
				.createProcessingInstruction(target, data);
		currentNode.appendChild(instruction);
	}

	public boolean endElement(String uri, String name) {
		if (!currentNode.getNodeName().equals(name)) {
			throw new DOMException(DOMException.SYNTAX_ERR,
					"Unexpected end-tag: " + name + " expected: "
							+ currentNode.getNodeName());
		}
		return nodeUp();
	}

	public void text(String data) {
		currentNode.appendChild(document.createTextNode(data));
	}

	public NodeHandler getNodeHandler() {
		return nodeHandler;
	}
	
}
