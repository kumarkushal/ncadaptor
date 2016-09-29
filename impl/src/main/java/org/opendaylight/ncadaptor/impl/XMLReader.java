/*
 * Copyright (c) 2015 Cisco Systems and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.ncadaptor.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class is a combination of DOM and SAX parsing since import files like
 * BMECat files are generally too big for DOM parsers but SAX is too
 * inconvenient, this class extracts part of the SAX-stream, converts them into
 * sub-DOMs and calls the application for each sub-DOM.
 *
 * @author aha
 */
public class XMLReader extends DefaultHandler {

	private boolean isTextNode = false;

	private StringBuilder textNode = new StringBuilder();

	private Map<String, NodeHandler> handlers = new TreeMap<String, NodeHandler>();

	private List<SAX2DOMHandler> activeHandlers = new ArrayList<SAX2DOMHandler>();

	public static StringBuffer sbf = new StringBuffer();

	private static String indentation = "";
	
	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		// Delegate to active handlers...
		for (SAX2DOMHandler handler : activeHandlers) {
			handler.startElement(uri, name, attributes);
		}

		// Start a new handler is necessary
		try {
			// QName qualifiedName = new QName(uri, localName);
			NodeHandler handler = handlers.get(name);
			if (handler != null) {
				activeHandlers.add(new SAX2DOMHandler(handler, uri, name, attributes));
			} else {

				sbf.append('<');
				sbf.append(name);
				if (attributes != null) {
					int numberAttributes = attributes.getLength();
					for (int loopIndex = 0; loopIndex < numberAttributes; loopIndex++) {
						sbf.append(' ');
						sbf.append(attributes.getQName(loopIndex));
						sbf.append("=\"");
						sbf.append(attributes.getValue(loopIndex));
						sbf.append('"');
					}
				}
				sbf.append('>');
			}
		} catch (ParserConfigurationException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		isTextNode = true;
		if (!activeHandlers.isEmpty()) {
			textNode.append(ch, start, length);
		} else {
			String characterData = (new String(ch, start, length)).trim();
			if (characterData.indexOf("\n") < 0 && characterData.length() > 0) {
				sbf.append(characterData);
			}
		}

	}

	@Override
	public void endDocument() throws SAXException {
		// Consider iterating over all activeHandler which are not complete
		// yet and raise an exception.
		// For now this is simply ignored to make processing more robust.
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		// Delegate to active handlers and deletes them if they are finished...

		if (!activeHandlers.isEmpty()) {

			if (isTextNode) {
				String data = textNode.toString();
				for (SAX2DOMHandler handler : activeHandlers) {
					handler.text(data);
				}
				textNode = new StringBuilder();
				isTextNode = false;
			}

			Iterator<SAX2DOMHandler> iter = activeHandlers.iterator();
			while (iter.hasNext()) {
				SAX2DOMHandler handler = iter.next();
				if (handler.endElement(uri, name)) {
					iter.remove();
				}
			}
		} else {
			// indentation = indentation.substring(0, indentation.length() - 4);
			// sbf.append(indentation);
			sbf.append("</");
			sbf.append(name);
			sbf.append('>');
		}

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// Delegate to active handlers...
		for (SAX2DOMHandler handler : activeHandlers) {
			handler.processingInstruction(target, data);
		}
	}

	/**
	 * Registers a new handler for a qualified name of a node. Handlers are
	 * invoked AFTER the complete node was read. Since documents like BMECat
	 * usually don't mix XML-data, namespaces are ignored for now which eases
	 * the processing a lot (especially xpath related tasks). Namespaces however
	 * could be easily added by repalcing String with QName here.
	 */

	public void addHandler(String name, NodeHandler handler) {

		handlers.put(name, handler);
	}

	class UserInterruptException extends RuntimeException {

		private static final long serialVersionUID = -7454219131982518216L;

	}

	/**
	 * Parses the given stream and using the given monitor
	 */

	public void parse(InputStream stream) throws ParserConfigurationException, SAXException, IOException {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			org.xml.sax.XMLReader reader = saxParser.getXMLReader();

			reader.setEntityResolver(new EntityResolver() {

				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {

					URL url = new URL(systemId);
					// Check if file is local
					if ("file".equals(url.getProtocol())) {
						// Check if file exists
						File file = new File(url.getFile());
						if (file.exists()) {
							return new InputSource(new FileInputStream(file));
						}
					}
					return null;
				}
			});

			reader.setContentHandler(this);
			reader.parse(new InputSource(stream));
			System.out.println(prettyFormat(sbf.toString(),2));
		} catch (UserInterruptException e) {
			/*
			 * IGNORED - this is used to cancel parsing if the used tried to
			 * cancel a process.
			 */
		} finally {
			stream.close();
		}
	}
	public static String prettyFormat(String input, int indent) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}
	public static String prettyFormat2(Node node) {

	DOMImplementationLS lsImpl = (DOMImplementationLS) node.getOwnerDocument().getImplementation()
			.getFeature("LS", "3.0");
	LSSerializer serializer = lsImpl.createLSSerializer();
	serializer.getDomConfig().setParameter("xml-declaration", false);
	String str = serializer.writeToString(node);
	System.out.println("FILTER TAG MODIFIED : \n\n" + str);
	return str;
	}
	
	private static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc =null;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse(new InputSource(new StringReader(xmlStr)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	private static String convertDocumentToString(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
			// "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return null;
	}
}