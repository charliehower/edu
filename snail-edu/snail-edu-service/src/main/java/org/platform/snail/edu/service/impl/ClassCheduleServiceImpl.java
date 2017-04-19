package org.platform.snail.edu.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.ClassCheduleMapper;
import org.platform.snail.edu.model.ClassChedule;
import org.platform.snail.edu.service.ClassCheduleService;
import org.platform.snail.edu.vo.ClassCheduleQVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classCheduleService")
public class ClassCheduleServiceImpl implements ClassCheduleService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ClassCheduleMapper classCheduleMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findClassCheduleList(ClassCheduleQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		/*List<ClassCheduleVo> list = this.classCheduleMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.classCheduleMapper.findCount(condition);
			rst.setAllRows(allRows);
		}*/
		return rst;
	}

	public DataResponse insertClassChedule(JSONObject json, SystemUser systemUser)
			throws Exception {
		ClassChedule o = new ClassChedule();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getClassCheduleId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.classCheduleMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.classCheduleMapper.insert(o);
		this.dataBaseLogService.log("添加课表", "课表", "", "",
				"", systemUser);
		return new DataResponse(true, "添加课表完成！");
	}

	public DataResponse updateClassChedule(JSONObject json, SystemUser systemUser)
			throws Exception {
		ClassChedule o = new ClassChedule();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getClassCheduleId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}*/
		o.setCreateTime(new Date());
		this.classCheduleMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更课表", "课表", "", "",
				"", systemUser);
		return new DataResponse(true, "变更课表完成！");
	}

	public DataResponse selectClassCheduleByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.classCheduleMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteClassCheduleByClassCheduleId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.classCheduleMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除课表", "课表", String.valueOf(id), String.valueOf(id),
				"课表", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateClassChedule(JSONObject json, SystemUser systemUser)
			throws Exception {
		ClassChedule o = new ClassChedule();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getWeekId())) {
			return new DataResponse(false, "时间不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSectionId())) {
			return new DataResponse(false, "科节不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getClassesId())) {
			return new DataResponse(false, "班级不能为空！");
		}
	
		o.setCreateTime(new Date());
		
		this.classCheduleMapper.saveOrUpdateClassChedule(o);
		this.dataBaseLogService.log("变更课表", "课表", "", o.getDisciplineId(),
				o.getDisciplineId(), systemUser);
		return new DataResponse(true, "变更课表完成！");
	}
}
