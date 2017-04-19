package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.AssnSubMapper;
import org.platform.snail.edu.model.AssnSub;
import org.platform.snail.edu.service.AssnSubService;
import org.platform.snail.edu.vo.AssnSubQVo;
import org.platform.snail.edu.vo.AssnSubVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("assnSubService")
public class AssnSubServiceImpl implements AssnSubService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AssnSubMapper assnSubMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findAssnSubList(AssnSubQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<AssnSubVo> list = this.assnSubMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.assnSubMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertAssnSub(JSONObject json, SystemUser systemUser)
			throws Exception {
		AssnSub o = new AssnSub();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getAssnSubId())) {
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
		int temp = this.assnSubMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}
		this.assnSubMapper.insert(o);
		this.dataBaseLogService.log("添加社团", "社团", "", o.getName(),
				o.getName(), systemUser);*/
		return new DataResponse(true, "添加社团完成！");
	}

	public DataResponse updateAssnSub(JSONObject json, SystemUser systemUser)
			throws Exception {
		AssnSub o = new AssnSub();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getAssnSubId())) {
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
		this.assnSubMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更社团", "社团", "", o.getName(),
				o.getName(), systemUser);*/
		return new DataResponse(true, "变更社团完成！");
	}

	public DataResponse selectAssnSubByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.assnSubMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteAssnSubByAssnSubId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.assnSubMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除社团", "社团", String.valueOf(id), String.valueOf(id),
				"社团", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateAssnSub(JSONObject json, SystemUser systemUser)
			throws Exception {
		AssnSub o = new AssnSub();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "社团不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
	
		o.setCreateTime(new Date());
		
		this.assnSubMapper.saveOrUpdateAssnSub(o);
		this.dataBaseLogService.log("变更社团", "社团", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);*/
		return new DataResponse(true, "变更社团完成！");
	}
	public  DataResponse updateAuditByPrimaryKey(JSONObject json,SystemUser systemUser) throws Exception{
		DataResponse rst = new DataResponse(true,"审核完成！");
		JSONArray list=json.getJSONArray("list");
		for(Object o:list){
			JSONObject e=(JSONObject)o;
			AssnSub obj = new AssnSub();
			SnailBeanUtils.copyProperties(obj, e);
			obj.setAuditor(systemUser.getUsers().getName());
			obj.setAuditTime(new Date());
			this.assnSubMapper.updateAuditByPrimaryKey(obj);
			this.dataBaseLogService.log("社团入社审核", obj.getStudentName(), "",obj.getAssnSubId(),
					obj.getAssnSubId(), systemUser);
		}
		return rst;
	}
}
