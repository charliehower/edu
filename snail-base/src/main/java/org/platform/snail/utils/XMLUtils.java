//创建时间  Date: 2010-1-1 15:07:37
//创建者 陈晓克

// Source File Name:   ConXml.java

package org.platform.snail.utils;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class XMLUtils {
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document doc;

	public XMLUtils() {
		factory = null;
		builder = null;
		doc = null;
	}

	public Document getDocument(String xmlfilename) {
		InputStream inputStream = getClass().getResourceAsStream(xmlfilename);
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse(inputStream);
			doc.normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public Document getDocument(InputStream inputStream) {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse(inputStream);
			doc.normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

}