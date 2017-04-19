package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.SalaryImportMapper;
import org.platform.snail.edu.dao.SalaryaMapper;
import org.platform.snail.edu.dao.SalarybMapper;
import org.platform.snail.edu.dao.SalarycMapper;
import org.platform.snail.edu.model.SalaryImport;
import org.platform.snail.edu.model.Salarya;
import org.platform.snail.edu.model.Salaryb;
import org.platform.snail.edu.model.Salaryc;
import org.platform.snail.edu.service.SalaryImportService;
import org.platform.snail.edu.vo.SalaryImportQVo;
import org.platform.snail.edu.vo.SalaryImportVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("salaryImportService")
public class SalaryImportServiceImpl implements SalaryImportService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SalaryImportMapper salaryImportMapper;
	@Autowired
	private SalaryaMapper salaryaMapper;
	@Autowired
	private SalarybMapper salarybMapper;
	@Autowired
	private SalarycMapper salarycMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findSalaryImportList(SalaryImportQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<SalaryImportVo> list = this.salaryImportMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.salaryImportMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertSalaryImport(MultipartFile[] files,JSONObject json,SystemUser systemUser)
			throws Exception {
		String salaryImportId="";
		ExcelUtils utils=new ExcelUtils();
		List<Map<String,String>> list;
		SalaryImport si = new SalaryImport();
		SnailBeanUtils.copyProperties(si, json);
		if (SnailUtils.isBlankString(si.getCategory())) {
			return new DataResponse(false, "职工类别不能为空！");
		}
		if (SnailUtils.isBlankString(si.getYear())) {
			return new DataResponse(false, "发放年度不能为空！");
		}
		if (SnailUtils.isBlankString(si.getMonth())) {
			return new DataResponse(false, "发放月份不能为空！");
		}
		
		for(MultipartFile file:files){
			SalaryImport o = new SalaryImport();
			SnailBeanUtils.copyProperties(o, json);
			o.setUserId(systemUser.getUsers().getUserId());
			o.setCreateTime(new Date());
			salaryImportId=o.getCategory()+o.getYear()+""+o.getMonth();
			o.setSalaryImportId(salaryImportId);
			o.setTitle(file.getOriginalFilename());
			if (this.salaryImportMapper.isExitBySalaryImportId(salaryImportId)>0) {
				return new DataResponse(false, "重复的导入！");
			}
			if(o.getCategory().equals("a")){
				list=utils.readExcelByJXLMH(file.getInputStream(), 0, 3, 0, 1, 2);
				for(Map<String,String> tmp:list){
					if(SnailUtils.isNotBlankString(tmp.get("姓名"))){
						Salarya obj=new Salarya();
						SnailBeanUtils.copyProperties(obj, tmp);
						obj.setCreateTime(new Date());
						obj.setSalaryId(UUID.randomUUID().toString());
						obj.setSalaryImportId(salaryImportId);
						this.logger.info(obj.toString());
						this.salaryaMapper.insert(obj);
					}
					
				}
				//this.salaryImportMapper.updateTeacherCategoryByNameList(salaryImportId);
			}
			if(o.getCategory().equals("b")){
				list=utils.readExcelByJXLMH(file.getInputStream(), 1, 5, 2, 3, 4);
				for(Map<String,String> tmp:list){
					if(SnailUtils.isNotBlankString(tmp.get("姓名"))){
						Salaryb obj=new Salaryb();
						SnailBeanUtils.copyProperties(obj, tmp);
						obj.setCreateTime(new Date());
						obj.setSalaryId(UUID.randomUUID().toString());
						obj.setSalaryImportId(salaryImportId);
						this.logger.info(obj.toString());
						this.salarybMapper.insert(obj);
					}
				}
			}
			if(o.getCategory().equals("c")){
				list=utils.readExcelByJXLMH(file.getInputStream(), 0, 5, 3, 4, 0);
				for(Map<String,String> tmp:list){
					if(SnailUtils.isNotBlankString(tmp.get("人员姓名"))){
						Salaryc obj=new Salaryc();
						SnailBeanUtils.copyProperties(obj, tmp);
						obj.setCreateTime(new Date());
						obj.setSalaryId(UUID.randomUUID().toString());
						obj.setSalaryImportId(salaryImportId);
						this.logger.info(obj.toString());
						this.salarycMapper.insert(obj);
					}
				}
			}
			this.salaryImportMapper.insert(o);
		}
		
		
		
		
		this.dataBaseLogService.log("教职工工资导入", "工资", "", si.getMonth(),
				si.getYear(), systemUser);
		return new DataResponse(true, si.getMonth()+"月份工资导入工完成！");
	}

	

	public DataResponse deleteSalaryImportBySalaryImportId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.salaryImportMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除教职工工资", "工资", String.valueOf(id), String.valueOf(id),
				"工资", systemUser);
		return rst;
	}
	
}
