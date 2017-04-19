package org.platform.snail.utils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXReader extends DefaultHandler {

	java.util.Stack tags = new java.util.Stack();

	// ********************XML������************************
	String NAME = null;//��������
	String BOOK_NO = null;//ҽ��֤��
	String HOSPITAL = null;//ҽԺ����
	String DOCTOR = null;//ҽ��
	String DIAGNOSIS = null;//��ϲ���ICD-10����
	String IN_HOSPITAL_NO = null;//סԺ��
	String START_DATE = null;//��Ժ��
	String END_DATE = null;//��Ժ��
	String TOTAL_COSTS=null;//�ܷ���
	String BILL_NO=null;//סԺ�������ݺ�
	
	String MED_SN = null;//����
	String MED_NAME = null;//����
	String MED_TYPE = null;//����
	String MED_SPEC = null;//����
	String MED_UNIT = null;//��λ
	String MED_AMOUNT = null;//����
	String MED_PRICE = null;//�۸�
	String MED_SCALE = null;//����
	String MED_TAG = null;//ҽ����Χ

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tags.push(qName);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		tags.pop();
		if(qName.endsWith("MED_TAG")){
			printout();
		}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		String tag=(String)tags.peek();
		if(tag.equals("MED_SN")){
			MED_SN=new String(ch,start,length);
		}
		if(tag.equals("NAME")){
			NAME=new String(ch,start,length);
		}
		
	}
	
	private void printout(){
		System.out.println(MED_SN);
		System.out.println(NAME);
	}
	
	public static void main(String []args){
		SAXParserFactory spf=SAXParserFactory.newInstance();
		SAXParser saxParser=null;
		try {
			saxParser=spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		try {
			saxParser.parse(SAXReader.class.getResourceAsStream("/4104.xml"), new SAXReader());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
