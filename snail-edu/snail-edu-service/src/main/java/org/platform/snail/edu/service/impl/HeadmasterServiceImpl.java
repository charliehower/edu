package org.platform.snail.edu.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.HeadmasterMapper;
import org.platform.snail.edu.model.Headmaster;
import org.platform.snail.edu.model.Headmaster;
import org.platform.snail.edu.service.HeadmasterService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("headmasterService")
public class HeadmasterServiceImpl implements HeadmasterService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private HeadmasterMapper headmasterMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findHeadmasterList(Headmaster condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		/*List<Headmaster> list = this.headmasterMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.headmasterMapper.findCount(condition);
			rst.setAllRows(allRows);
		}*/
		return rst;
	}

	public DataResponse insertHeadmaster(JSONObject json, SystemUser systemUser)
			throws Exception {
		Headmaster o = new Headmaster();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getHeadmasterId())) {
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
		
		int temp = this.headmasterMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.headmasterMapper.insert(o);
		this.dataBaseLogService.log("添加教职工", "教职工", "", "",
				"", systemUser);
		return new DataResponse(true, "添加教职工完成！");
	}
	public DataResponse saveOrUpdateHeadmaster(JSONObject json, SystemUser systemUser)
			throws Exception {
		Headmaster o = new Headmaster();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "教职工不能为空！");
		}
	
		if (SnailUtils.isBlankString(o.getClassesId())) {
			return new DataResponse(false, "班级不能为空！");
		}
	
		o.setCreateTime(new Date());
		
		this.headmasterMapper.saveOrUpdateHeadmaster(o);
		this.dataBaseLogService.log("变更班主任", "班主任", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更班主任完成！");
	}

	public DataResponse updateHeadmaster(JSONObject json, SystemUser systemUser)
			throws Exception {
		Headmaster o = new Headmaster();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getHeadmasterId())) {
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
		
		this.headmasterMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更教职工", "教职工", "", "",
				"", systemUser);
		return new DataResponse(true, "变更教职工完成！");
	}

	public DataResponse selectHeadmasterByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.headmasterMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteHeadmasterByHeadmasterId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.headmasterMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除教职工", "教职工", String.valueOf(id), String.valueOf(id),
				"教职工", systemUser);
		return rst;
	}

}
