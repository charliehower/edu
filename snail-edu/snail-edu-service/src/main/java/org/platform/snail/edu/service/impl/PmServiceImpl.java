package org.platform.snail.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.PmMapper;
import org.platform.snail.edu.model.Pm;
import org.platform.snail.edu.service.PmService;
import org.platform.snail.edu.vo.PmQVo;
import org.platform.snail.edu.vo.PmVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("pmService")
public class PmServiceImpl implements PmService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PmMapper pmMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public DataResponse findPmList(PmQVo condition, int start, int limit,
			String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<PmVo> list = this.pmMapper.findList(condition, start, start
				+ limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.pmMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertPm(JSONObject json, SystemUser systemUser)
			throws Exception {
		String id = UUID.randomUUID().toString();
		Pm o = new Pm();
		SnailBeanUtils.copyProperties(o, json);
		o.setPmId(id);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlank(o.getPmId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getName())) {
			return new DataResponse(false, "考试名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getStudentId())) {
			return new DataResponse(false, "学生编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getSubjectId())) {
			return new DataResponse(false, "学科编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getYears())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlank(o.getScore())) {
			return new DataResponse(false, "成绩不能为空！");
		}
		
		int temp = this.pmMapper.isExit(o);
		if (temp > 0) {
			return new DataResponse(false, "已存在的数据！");
		}
		this.pmMapper.insert(o);
		this.dataBaseLogService.log("添加成绩", "成绩", "", o.getStudentId(), o.getStudentId(),
				systemUser);
		return new DataResponse(true, "添加成绩完成！");
	}

	public DataResponse updatePm(JSONObject json, SystemUser systemUser)
			throws Exception {
		Pm o = new Pm();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlank(o.getPmId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getName())) {
			return new DataResponse(false, "考试名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getStudentId())) {
			return new DataResponse(false, "学生编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getSubjectId())) {
			return new DataResponse(false, "学科编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getYears())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlank(o.getScore())) {
			return new DataResponse(false, "成绩不能为空！");
		}
		
		this.pmMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更成绩", "成绩", "", o.getStudentId(), o.getStudentId(),
				systemUser);
		return new DataResponse(true, "变更成绩完成！");
	}

	public DataResponse selectPmByPrimaryKey(String id) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.pmMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deletePmByPmId(String id, SystemUser systemUser)
			throws Exception {
		DataResponse rst = new DataResponse();
		this.pmMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除成绩", "成绩", String.valueOf(id),
				String.valueOf(id), "成绩", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdatePm(JSONObject json, SystemUser systemUser)
			throws Exception {
		Pm o = new Pm();
		SnailBeanUtils.copyProperties(o, json);
		
		return new DataResponse(true, "变更成绩完成！");
	}

	public DataResponse importXls(MultipartFile[] files, JSONObject json,
			SystemUser systemUser) throws Exception {
		ExcelUtils utils = new ExcelUtils();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<Map<String,String>> listSu=this.pmMapper.selectSubject();
		List<Map<String,String>> listSt=this.pmMapper.selectStudent();
		List<Map<String,String>> listYe=this.pmMapper.selectYears();
		Map<String,String> subject=new HashMap<String,String>();
		Map<String,String> student=new HashMap<String,String>();
		Map<String,String> years=new HashMap<String,String>();
		for(Map<String,String> o:listSu){
			subject.put(o.get("NAME"), o.get("CODE"));
		}
		for(Map<String,String> o:listSt){
			student.put(o.get("CODE"), o.get("NAME"));
		}
		for(Map<String,String> o:listYe){
			years.put(o.get("CODE"), o.get("NAME"));
		}
		this.logger.info(subject);
		this.logger.info(student);
		this.logger.info(years);
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
				Pm o = new Pm();
				o.setPmId(UUID.randomUUID().toString());
				o.setCreateTime(new Date());
				o.setName(row.get("名称"));
				o.setRemark(row.get("备注"));
				o.setScore(new java.math.BigDecimal(row.get("分数")));
				if(student.containsKey(row.get("学号"))){
					o.setStudentId(row.get("学号"));
				}
				
				if(subject.get(row.get("学科"))!=null){
					o.setSubjectId(subject.get(row.get("学科")));
				}
				if(years.containsKey((row.get("学年")))){
					o.setYears(row.get("学年"));
				}
				this.logger.info(o.toString());
				
				if (SnailUtils.isBlank(o.getStudentId())) {
					return new DataResponse(false,  "行"+i+"学号错误！");
				}
				if (SnailUtils.isBlank(o.getName())) {
					return new DataResponse(false, "行"+i+"考试名称不能为空！");
				}
				if (SnailUtils.isBlank(o.getSubjectId())) {
					return new DataResponse(false,  "行"+i+"学科错误！");
				}
				if (SnailUtils.isBlank(o.getYears())) {
					return new DataResponse(false,  "行"+i+"学年错误！");
				}
				if (SnailUtils.isBlank(o.getScore())) {
					return new DataResponse(false,  "行"+i+"成绩不能为空！");
				}
				int t = pmMapper.isExit(o);
				if (t > 0) {
					this.pmMapper.updateByPrimaryKey(o);

				} else {
					this.pmMapper.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("信息导入", "成绩", "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "成绩信息导入工完成！");
	}
}
