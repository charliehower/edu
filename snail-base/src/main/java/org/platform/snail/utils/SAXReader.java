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

	// ********************XML的内容************************
	String NAME = null;//病人姓名
	String BOOK_NO = null;//医疗证号
	String HOSPITAL = null;//医院代码
	String DOCTOR = null;//医生
	String DIAGNOSIS = null;//诊断病名ICD-10编码
	String IN_HOSPITAL_NO = null;//住院号
	String START_DATE = null;//入院日
	String END_DATE = null;//出院日
	String TOTAL_COSTS=null;//总费用
	String BILL_NO=null;//住院补助单据号
	
	String MED_SN = null;//编码
	String MED_NAME = null;//名称
	String MED_TYPE = null;//类型
	String MED_SPEC = null;//描述
	String MED_UNIT = null;//单位
	String MED_AMOUNT = null;//数量
	String MED_PRICE = null;//价格
	String MED_SCALE = null;//比例
	String MED_TAG = null;//医保范围

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
