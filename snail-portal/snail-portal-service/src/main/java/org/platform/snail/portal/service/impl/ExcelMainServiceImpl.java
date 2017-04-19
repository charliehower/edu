package org.platform.snail.portal.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jxl.write.WriteException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.ExcelDataMapper;
import org.platform.snail.portal.dao.ExcelHeaderMapper;
import org.platform.snail.portal.dao.ExcelMainMapper;
import org.platform.snail.portal.model.ExcelData;
import org.platform.snail.portal.model.ExcelHeader;
import org.platform.snail.portal.model.ExcelMain;
import org.platform.snail.portal.service.ExcelMainService;
import org.platform.snail.portal.service.MongoService;
import org.platform.snail.portal.vo.ExcelMainQVo;
import org.platform.snail.portal.vo.ExcelMainVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

@Service("excelMainService")
public class ExcelMainServiceImpl implements ExcelMainService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ExcelMainMapper excelMainMapper;
	
	@Autowired
	private ExcelHeaderMapper excelHeaderMapper;
	@Autowired
	private ExcelDataMapper excelDataMapper;
	@Autowired
	private MongoService mongoService;
	
	@Autowired
	private DataBaseLogService dataBaseLogService;
	private ExcelUtils excelUtils=new ExcelUtils();
	public DataResponse findExcelMainList(ExcelMainQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<ExcelMainVo> list = this.excelMainMapper.findList(condition,
				start, start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.excelMainMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertExcelMain(JSONObject json, SystemUser systemUser)
			throws Exception {
		String id = String.valueOf(new Date().getTime());
		ExcelMain o = new ExcelMain();
		SnailBeanUtils.copyProperties(o, json);
		o.setId(id);
		o.setCreateTime(new Date());
		o.setUserId(systemUser.getUsers().getUserId());
		String str=SnailUtils.parseDate(o.getDeadline())+" 23:59:59:00";
		this.logger.info(str);
		o.setDeadline(SnailUtils.parseDate(str, "yyyy-MM-dd HH:mm:ss"));
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getName())) {
			return new DataResponse(false, "填报名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getUserId())) {
			return new DataResponse(false, "申报人不能为空！");
		}
		if (SnailUtils.isBlank(o.getStartTime())) {
			return new DataResponse(false, "开始时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getDeadline())) {
			return new DataResponse(false, "截止时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getTotals())) {
			return new DataResponse(false, "应填报人数不能为空！");
		}
		if (SnailUtils.isBlank(o.getTargets())) {
			return new DataResponse(false, "填报对象不能为空！");
		}
		if (SnailUtils.isBlank(o.getFiles())) {
			return new DataResponse(false, "填报模板不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		int temp = this.excelMainMapper.isExit(o);
		if (temp > 0) {
			return new DataResponse(false, "已存在的数据！");
		}
		GridFSDBFile file=this.mongoService.retrieveFileOne("excel", o.getFiles());
		if (SnailUtils.isBlank(file)) {
			return new DataResponse(false, "无法获取模板的文件！");
		}
		Map<Object, String> header=null;
		if(o.getFiles().endsWith("xlsx")){
			header=excelUtils.getHeaderByPOI(file.getInputStream());
		}else{
			header=excelUtils.getHeaderByJXL(file.getInputStream());
		}
		ExcelHeader h=new ExcelHeader();
		h.setId(id);
		h.setC01(header.get(0));
		h.setC02(header.get(1));
		h.setC03(header.get(2));
		h.setC04(header.get(3));
		h.setC05(header.get(4));
		h.setC06(header.get(5));
		h.setC07(header.get(6));
		h.setC08(header.get(7));
		h.setC09(header.get(8));
		h.setC10(header.get(9));
		h.setC11(header.get(10));
		h.setC12(header.get(11));
		h.setC13(header.get(12));
		h.setC14(header.get(13));
		h.setC15(header.get(14));
		h.setC16(header.get(15));
		h.setC17(header.get(16));
		h.setC18(header.get(17));
		h.setC19(header.get(18));
		h.setC20(header.get(19));
		this.excelHeaderMapper.insert(h);
		this.excelMainMapper.insert(o);
		this.dataBaseLogService.log("添加模板", "模板", "", o.getName(), o.getName(),
				systemUser);
		return new DataResponse(true, "添加模板完成！");
	}

	public DataResponse updateExcelMain(JSONObject json, SystemUser systemUser)
			throws Exception {
		ExcelMain o = new ExcelMain();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		o.setUserId(systemUser.getUsers().getUserId());
		String str=SnailUtils.parseDate(o.getDeadline())+" 23:59:59:00";
		o.setDeadline(SnailUtils.parseDate(str, "yyyy-MM-dd HH:mm:ss"));
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getName())) {
			return new DataResponse(false, "填报名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getUserId())) {
			return new DataResponse(false, "申报人不能为空！");
		}
		if (SnailUtils.isBlank(o.getStartTime())) {
			return new DataResponse(false, "开始时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getDeadline())) {
			return new DataResponse(false, "截止时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getTotals())) {
			return new DataResponse(false, "应填报人数不能为空！");
		}
		if (SnailUtils.isBlank(o.getTargets())) {
			return new DataResponse(false, "填报对象不能为空！");
		}
		if (SnailUtils.isBlank(o.getFiles())) {
			return new DataResponse(false, "填报模板不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		GridFSDBFile file=this.mongoService.retrieveFileOne("excel", o.getFiles());
		if (SnailUtils.isBlank(file)) {
			return new DataResponse(false, "无法获取模板的文件！");
		}
		Map<Object, String> header=null;
		if(o.getFiles().endsWith("xlsx")){
			header=excelUtils.getHeaderByPOI(file.getInputStream());
		}else{
			header=excelUtils.getHeaderByJXL(file.getInputStream());
		}
		ExcelHeader h=new ExcelHeader();
		h.setId(o.getId());
		h.setC01(header.get(0));
		h.setC02(header.get(1));
		h.setC03(header.get(2));
		h.setC04(header.get(3));
		h.setC05(header.get(4));
		h.setC06(header.get(5));
		h.setC07(header.get(6));
		h.setC08(header.get(7));
		h.setC09(header.get(8));
		h.setC10(header.get(9));
		h.setC11(header.get(10));
		h.setC12(header.get(11));
		h.setC13(header.get(12));
		h.setC14(header.get(13));
		h.setC15(header.get(14));
		h.setC16(header.get(15));
		h.setC17(header.get(16));
		h.setC18(header.get(17));
		h.setC19(header.get(18));
		h.setC20(header.get(19));
		this.excelHeaderMapper.updateByPrimaryKey(h);
		this.excelMainMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更模板", "模板", "", o.getName(), o.getName(),
				systemUser);
		return new DataResponse(true, "变更模板完成！");
	}

	public DataResponse selectExcelMainByPrimaryKey(String id) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.excelMainMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteExcelMainByExcelMainId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.excelMainMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除模板", "模板", String.valueOf(id),
				String.valueOf(id), "模板", systemUser);
		return rst;
	}

	public DataResponse importXls(MultipartFile[] files, JSONObject json,
			SystemUser systemUser) throws Exception {
		String excelId=json.getString("excelId");
		ExcelUtils utils = new ExcelUtils();
		if (SnailUtils.isBlank(excelId)) {
			return new DataResponse(false, "ExcelId不能为空！");
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		ExcelHeader header=this.excelHeaderMapper.selectByPrimaryKey(excelId);
		ExcelMain main=this.excelMainMapper.selectByPrimaryKey(excelId);
		for (MultipartFile file : files) {

			String ext = file
					.getOriginalFilename()
					.toLowerCase()
					.substring(
							file.getOriginalFilename().toLowerCase()
									.lastIndexOf("."));
			this.logger.info(ext);
			if (ext.equals(".xls")) {
				list = utils.readExcelByJXL(file.getInputStream(), 2);
			}
			if (ext.equals(".xlsx")) {
				list = utils.readExcelByPOI(file.getInputStream(), 2);
			}
			int i = 0;
			for (Map<String, String> row : list) {
				
				ExcelData o = new ExcelData();
				o.setId(UUID.randomUUID().toString());
				o.setExcelId(excelId);
				o.setUserId(systemUser.getUsers().getUserId());
				o.setCreateTime(new Date());
				o.setC01(row.get(header.getC01()));
				o.setC02(row.get(header.getC02()));
				o.setC03(row.get(header.getC03()));
				o.setC04(row.get(header.getC04()));
				o.setC05(row.get(header.getC05()));
				o.setC06(row.get(header.getC06()));
				o.setC07(row.get(header.getC07()));
				o.setC08(row.get(header.getC08()));
				o.setC09(row.get(header.getC09()));
				o.setC10(row.get(header.getC10()));
				o.setC11(row.get(header.getC11()));
				o.setC12(row.get(header.getC12()));
				o.setC13(row.get(header.getC13()));
				o.setC14(row.get(header.getC14()));
				o.setC15(row.get(header.getC15()));
				o.setC16(row.get(header.getC16()));
				o.setC17(row.get(header.getC17()));
				o.setC18(row.get(header.getC18()));
				o.setC19(row.get(header.getC19()));
				o.setC20(row.get(header.getC20()));
				this.logger.info(o.toString());
				int t = excelDataMapper.isExit(o);
				if (t > 0) {
					if(SnailUtils.isNotBlankString(o.getC01())){
						this.excelDataMapper.updateByPrimaryKey(o);
					}

				} else {
					if(SnailUtils.isNotBlankString(o.getC01())){
						this.excelDataMapper.insert(o);
					}
				}
				i++;
			}

		}
		this.dataBaseLogService.log("协同办公导入", main.getName(), "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "导入工完成！");
	}
	public void exportXls(OutputStream os, String id,boolean exportALl,
			SystemUser systemUser) throws WriteException, IOException {
		ExcelUtils utils = new ExcelUtils();
		Map<Object, String> header=new HashMap<Object, String>();
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		ExcelHeader h=this.excelHeaderMapper.selectByPrimaryKey(id);
		ExcelMain main=this.excelMainMapper.selectByPrimaryKey(id);
		List<ExcelData> list=null;
		if(exportALl){
			list=this.excelDataMapper.selectList(id);
		}else{
			list=this.excelDataMapper.selectListSelf(id,systemUser.getUsers().getUserId());
		}
		
		String sheetName=main.getName();
		header.put(0, h.getC01());
		header.put(1, h.getC02());
		header.put(2, h.getC03());
		header.put(3, h.getC04());
		header.put(4, h.getC05());
		header.put(5, h.getC06());
		header.put(6, h.getC07());
		header.put(7, h.getC08());
		header.put(8, h.getC09());
		header.put(9, h.getC10());
		header.put(10, h.getC11());
		header.put(11, h.getC12());
		header.put(12, h.getC13());
		header.put(13, h.getC14());
		header.put(14, h.getC15());
		header.put(15, h.getC16());
		header.put(16, h.getC17());
		header.put(17, h.getC18());
		header.put(18, h.getC19());
		header.put(19, h.getC20());
		header.put(20, "上报人");
		header.put(21, "上报时间");
		for(ExcelData o:list){
			Map<String, String> e=new HashMap<String, String>(); 
			e.put(h.getC01(), o.getC01());
			e.put(h.getC02(), o.getC02());
			e.put(h.getC03(), o.getC03());
			e.put(h.getC04(), o.getC04());
			e.put(h.getC05(), o.getC05());
			e.put(h.getC06(), o.getC06());
			e.put(h.getC07(), o.getC07());
			e.put(h.getC08(), o.getC08());
			e.put(h.getC09(), o.getC09());
			e.put(h.getC10(), o.getC10());
			e.put(h.getC11(), o.getC11());
			e.put(h.getC12(), o.getC12());
			e.put(h.getC13(), o.getC13());
			e.put(h.getC14(), o.getC14());
			e.put(h.getC15(), o.getC15());
			e.put(h.getC16(), o.getC16());
			e.put(h.getC17(), o.getC17());
			e.put(h.getC18(), o.getC18());
			e.put(h.getC19(), o.getC19());
			e.put(h.getC20(), o.getC20());
			e.put("上报人", o.getUserId());
			e.put("上报时间", o.getCreateTime().toLocaleString());
			data.add(e);
		}
		utils.createExcel(os, sheetName, header, data);
	}
}
