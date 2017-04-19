package org.platform.snail.edu.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.PositiveMapper;
import org.platform.snail.edu.model.Positive;
import org.platform.snail.edu.service.PositiveService;
import org.platform.snail.edu.vo.PositiveQVo;
import org.platform.snail.edu.vo.PositiveVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("positiveService")
public class PositiveServiceImpl implements PositiveService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PositiveMapper positiveMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findPositiveList(PositiveQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<PositiveVo> list = this.positiveMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.positiveMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertPositive(JSONObject json, SystemUser systemUser)
			throws Exception {
		Positive o = new Positive();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getPositiveId())) {
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
		
		int temp = this.positiveMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.positiveMapper.insert(o);
		this.dataBaseLogService.log("添加转正申请", "转正申请", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "添加转正申请完成！");
	}

	public DataResponse updatePositive(JSONObject json, SystemUser systemUser)
			throws Exception {
		Positive o = new Positive();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getPositiveId())) {
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
		
		this.positiveMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更转正申请", "转正申请", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更转正申请完成！");
	}

	public DataResponse selectPositiveByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.positiveMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deletePositiveByPositiveId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.positiveMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除转正申请", "转正申请", String.valueOf(id), String.valueOf(id),
				"转正申请", systemUser);
		return rst;
	}
	public  DataResponse isExitByTeacherId(String teacherId) throws Exception{
		int t=this.positiveMapper.isExitByTeacherId(teacherId);
		if(t>0){
			Positive o=this.positiveMapper.selectByPrimaryKey(teacherId);
			return new DataResponse(false,"已存在申请记录"+o.getInstanceId()+"时间"+o.getCteateTime().toLocaleString()+"，不能申请！");
		}
		return new DataResponse(true,"无申请记录可以申请！");
	}

}
