package org.platform.snail.utils;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DomReader {
	private DocumentBuilderFactory documentBuilderFactory = null;

	private DocumentBuilder documentBuilder = null;

	private Document document = null;
	public DomReader() {
	}
	public Document parseByInputStream(InputStream inputStream) {
		try {
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(inputStream);
			document.normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (document);
	}
	public Document parseByFileName(String fileName) {
		try {
			InputStream inputStream = getClass().getResourceAsStream(fileName);
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(inputStream);
			document.normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (document);
	}
	public Document parseByString(String xmlContent) {
		Debug.out(xmlContent);
		try {
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(xmlContent);
			
			document.normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (document);
	}
	

}
