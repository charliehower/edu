/**
 * @Title: ExcelUtils.java
 * @Package org.platform.snail.utils
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年1月7日 上午9:34:08
 * @version V1.0
 */

package org.platform.snail.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @ClassName: ExcelUtils
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年1月7日 上午9:34:08
 *
 */

public class ExcelUtils {
	Logger logger = Logger.getLogger(this.getClass());

	public Map<Object, String> getHeaderByJXL(InputStream is)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();
		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("GBK"); // 解决中文乱码，或GBK
		Workbook workbook = Workbook.getWorkbook(is, workbookSettings);
		Sheet sheet = workbook.getSheet(0);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		this.logger.info(cols);
		this.logger.info(rows);
		for (int i = 0; i < cols; i++) {
			Cell c = sheet.getCell(i, 0);
			header.put(i, c.getContents());
		}
		return header;
	}

	public Map<Object, String> getHeaderByPOI(InputStream is)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();
		org.apache.poi.ss.usermodel.Workbook workbook = new XSSFWorkbook(is);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Row row0 = null;
		while (rows.hasNext()) {
			row0 = rows.next();
			break;
		}
		Iterator<org.apache.poi.ss.usermodel.Cell> cells = row0.cellIterator();
		String content = null;
		int i = 0;
		while (cells.hasNext()) {
			org.apache.poi.ss.usermodel.Cell cell = cells.next();
			switch (cell.getCellType()) { // 根据cell中的类型来输出数据
			case HSSFCell.CELL_TYPE_NUMERIC:
				content = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				content = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				content = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				content = String.valueOf(cell.getCellFormula());
				break;
			default:
				content = String.valueOf("");
				break;
			}
			header.put(i, content);
			i++;
		}
		this.logger.info(header);
		return header;
	}

	public List<Map<String, String>> readExcelByJXL(InputStream is, int startRow)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("GBK"); // 解决中文乱码，或GBK
		Workbook workbook = Workbook.getWorkbook(is, workbookSettings);

		Sheet sheet = workbook.getSheet(0);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		this.logger.info(cols);
		this.logger.info(rows);
		for (int i = 0; i < cols; i++) {
			Cell c = sheet.getCell(i, 0);
			header.put(i, c.getContents());
		}
		for (int r = startRow; r < rows; r++) {
			Map<String, String> row = new HashMap<String, String>();
			for (int c = 0; c < cols; c++) {
				Cell cell = sheet.getCell(c, r);
				row.put(header.get(c), cell.getContents());
			}
			list.add(row);
		}
		for (Map<String, String> row : list) {
			logger.info(row);
		}
		return list;
	}

	public List<Map<String, String>> readExcelByPOI(InputStream is, int startRow)
			throws Exception {
		Map<Object, String> header = new HashMap<Object, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		try {
			// workbook = new XSSFWorkbook(is);
			workbook = new XSSFWorkbook(is);
		} catch (Exception e) {
			// workbook = new HSSFWorkbook(is);
		}
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Row row0 = null;
		while (rows.hasNext()) {
			row0 = rows.next();
			break;
		}
		Iterator<org.apache.poi.ss.usermodel.Cell> cells = row0.cellIterator();
		String content = null;
		int i = 0;
		while (cells.hasNext()) {
			org.apache.poi.ss.usermodel.Cell cell = cells.next();
			switch (cell.getCellType()) { // 根据cell中的类型来输出数据
			case HSSFCell.CELL_TYPE_NUMERIC:
				content = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				content = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				content = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				content = String.valueOf(cell.getCellFormula());
				break;
			default:
				content = String.valueOf("");
				break;
			}
			header.put(i, content);
			i++;
		}
		this.logger.info(header);
		while (rows.hasNext()) {
			row0 = rows.next();
			cells = row0.cellIterator();
			Map<String, String> row = new HashMap<String, String>();
			int k = 0;
			while (cells.hasNext()) {
				org.apache.poi.ss.usermodel.Cell cell = cells.next();
				switch (cell.getCellType()) { // 根据cell中的类型来输出数据
				case HSSFCell.CELL_TYPE_NUMERIC:
					content = String.valueOf(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					content = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					content = String.valueOf(cell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					content = String.valueOf(cell.getCellFormula());
					break;
				default:
					content = String.valueOf("");
					break;
				}
				row.put(header.get(k), content);
				k++;
			}
			list.add(row);
		}
		if (workbook != null) {
			workbook.close();
		}
		for (Map<String, String> row : list) {
			logger.info(row);
		}
		return list;
	}

	public List<Map<String, String>> readExcelByJXLMH(InputStream is,
			int sheetStart, int startRow, int header1, int header2, int header3)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("GBK"); // 解决中文乱码，或GBK
		Workbook workbook = Workbook.getWorkbook(is, workbookSettings);

		Sheet sheet = workbook.getSheet(sheetStart);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		this.logger.info(cols);
		this.logger.info(rows);
		for (int i = 0; i < cols; i++) {
			Cell c = sheet.getCell(i, header1);
			header.put(i, c.getContents().replaceAll("\n", ""));
		}
		if (header2 > 0) {
			for (int i = 0; i < cols; i++) {
				Cell c = sheet.getCell(i, header2);
				if (SnailUtils.isNotBlankString(c.getContents())) {
					header.put(i, c.getContents().replaceAll("\n", ""));

				}

			}
		}
		if (header3 > 0) {
			for (int i = 0; i < cols; i++) {
				Cell c = sheet.getCell(i, header3);
				if (SnailUtils.isNotBlankString(c.getContents())) {
					header.put(i, c.getContents().replaceAll("\n", ""));

				}

			}
		}
		/*
		 * for(int i=0;i<36;i++){
		 * System.out.println("`"+header.get(i)+"` DECIMAL NOT NULL DEFAULT 0,"
		 * ); }
		 */
		logger.info(header);
		for (int r = startRow; r < rows; r++) {
			Map<String, String> row = new HashMap<String, String>();
			for (int c = 0; c < cols; c++) {
				Cell cell = sheet.getCell(c, r);
				row.put(header.get(c), cell.getContents());
			}
			list.add(row);
		}
		for (Map<String, String> row : list) {
			logger.info(row);
		}

		return list;
	}

	public Map<Object, String> readExcelHeadByJXLMH(InputStream is,
			int sheetStart, int header1, int header2, int header3)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();

		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("GBK"); // 解决中文乱码，或GBK
		Workbook workbook = Workbook.getWorkbook(is, workbookSettings);

		Sheet sheet = workbook.getSheet(sheetStart);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		this.logger.info(cols);
		this.logger.info(rows);
		for (int i = 0; i < cols; i++) {
			Cell c = sheet.getCell(i, header1);
			header.put(i, c.getContents().replaceAll("\n", ""));
		}
		if (header2 > 0) {
			for (int i = 0; i < cols; i++) {
				Cell c = sheet.getCell(i, header2);
				if (SnailUtils.isNotBlankString(c.getContents())) {
					header.put(i, c.getContents().replaceAll("\n", ""));

				}

			}
		}
		if (header3 > 0) {
			for (int i = 0; i < cols; i++) {
				Cell c = sheet.getCell(i, header3);
				if (SnailUtils.isNotBlankString(c.getContents())) {
					header.put(i, c.getContents().replaceAll("\n", ""));

				}

			}
		}
		logger.info(header);
		return header;
	}

	public void createExcel(OutputStream os, String sheetName,
			Map<Object, String> header, List<Map<String, String>> data)
			throws WriteException, IOException {
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet(sheetName, 0);
		sheet.getSettings().setDefaultColumnWidth(30);
		// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		Set<Object> set = header.keySet();
		Iterator<Object> it = set.iterator();
		int m = 0;

		WritableFont font = new WritableFont(WritableFont.createFont("微软雅黑"),
				12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
		font.setColour(Colour.WHITE);// 设置字体颜色为金黄色
		WritableCellFormat colorFormat = new WritableCellFormat(font);
		colorFormat.setBackground(Colour.BLUE);
		colorFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		colorFormat.setWrap(true);
		Object content=null;
		while (it.hasNext()) {
			content=it.next();
			if(!SnailUtils.isBlank(header.get(content))){
				sheet.addCell(new Label(m, 0, header.get(content), colorFormat));
				m += 1;
			}
			
			
		}
		int k = 1;

		WritableFont font2 = new WritableFont(WritableFont.createFont("微软雅黑"),
				12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat body = new WritableCellFormat(font2);
		body.setBorder(Border.ALL, BorderLineStyle.THIN);
		body.setBackground(Colour.WHITE);
		body.setWrap(true);
		for (Map<String, String> o : data) {
			set = header.keySet();
			it = set.iterator();
			m = 0;
			while (it.hasNext()) {
				content=it.next();
				if(!SnailUtils.isBlank(header.get(content))){
					sheet.addCell(new Label(m, k, o.get(header.get(content)),
							body));
					m += 1;
				}
				
			}
			k += 1;
		}
		workbook.write();
		workbook.close();
		os.close();
	}

	public static void main(String[] args) throws WriteException, IOException, BiffException {
		// TODO Auto-generated method stub
//		String filepath = "/Users/chenxiaoke/Downloads/最新正编工资模板智慧校园.xls";
//		File file=new File(filepath);
//		ExcelUtils eu = new ExcelUtils();
//		InputStream is=new java.io.FileInputStream(file);
//		eu.readExcelByJXLMH(is, 0, 5, 3, 4, 0);

		Map<Integer,String> h=new HashMap<Integer,String>();
		h.put(0,"序号"); 
		h.put(1,"人员姓名"); 
		h.put(2,"职务工资"); 
		h.put(3,"级别工资"); 
		h.put(4,"见习工资"); 
		h.put(5,"绩效工资"); 
		h.put(6,"特区津贴"); 
		h.put(7,"特岗津贴"); 
		h.put(8,"独生子女费"); 
		h.put(9,"岗位津贴"); 
		h.put(10,"应发工资"); 
		h.put(11,"个人合计"); 
		h.put(12,"个人医疗"); 
		h.put(13,"个人养老"); 
		h.put(14,"所得税"); 
		h.put(15,"个人公积金"); 
		h.put(16,"实发工资"); 
		h.put(17,"房改补贴"); 
		h.put(18,"单位公积金"); 
		h.put(19,"单位社保合计"); 
		h.put(20,"单位医疗"); 
		h.put(21,"单位养老"); 
		Iterator<Integer> it=h.keySet().iterator();
		while(it.hasNext()){
			String filed=h.get(it.next());
			System.out.println("<td>${item."+filed+"}</td>");
		}
		

	}
}
