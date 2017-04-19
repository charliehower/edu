package org.platform.snail.edu.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.ArrangementMapper;
import org.platform.snail.edu.model.Arrangement;
import org.platform.snail.edu.service.ArrangementService;
import org.platform.snail.edu.vo.ArrangementQVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("arrangementService")
public class ArrangementServiceImpl implements ArrangementService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ArrangementMapper arrangementMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findArrangementList(ArrangementQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		
		return rst;
	}

	public DataResponse insertArrangement(JSONObject json, SystemUser systemUser)
			throws Exception {
		Arrangement o = new Arrangement();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getArrangementId())) {
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
		
		int temp = this.arrangementMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}
		this.arrangementMapper.insert(o);
		this.dataBaseLogService.log("添加任课安排", "任课安排", "", o.getName(),
				o.getName(), systemUser);*/
		return new DataResponse(true, "添加任课安排完成！");
	}

	public DataResponse updateArrangement(JSONObject json, SystemUser systemUser)
			throws Exception {
		Arrangement o = new Arrangement();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getArrangementId())) {
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
		
		this.arrangementMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更任课安排", "任课安排", "", o.getName(),
				o.getName(), systemUser);*/
		return new DataResponse(true, "变更任课安排完成！");
	}
	public DataResponse saveOrUpdateArrangement(JSONObject json, SystemUser systemUser)
			throws Exception {
		Arrangement o = new Arrangement();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "教职工不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
		if (SnailUtils.isBlankString(o.getClassesId())) {
			return new DataResponse(false, "班级不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		o.setCreateTime(new Date());
		
		this.arrangementMapper.saveOrUpdateArrangement(o);
		this.dataBaseLogService.log("变更任课安排", "任课安排", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更任课安排完成！");
	}
	public DataResponse selectArrangementByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.arrangementMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteArrangementByArrangementId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.arrangementMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除任课安排", "任课安排", String.valueOf(id), String.valueOf(id),
				"任课安排", systemUser);
		return rst;
	}

}
