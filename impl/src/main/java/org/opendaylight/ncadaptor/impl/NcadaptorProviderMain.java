package org.opendaylight.ncadaptor.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

public class NcadaptorProviderMain {
	String s = this.getClass().getName();
	Object testPath = this.getClass().getResource(s);

	public static void main(String[] args) {
		URL location = NcadaptorProviderMain.class.getProtectionDomain().getCodeSource().getLocation();
	//	System.out.println(location.getFile());
	//	System.out.println(System.getProperty("user.dir"));
		XMLReader reader = new XMLReader();
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
			reader.parse(new FileInputStream("/home/sdn/ncadaptor/impl/src/main/java/org/opendaylight/ncadaptor/impl/rpc.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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


	}

}

