package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.EntryMapper;
import org.platform.snail.edu.dao.TeacherMapper;
import org.platform.snail.edu.model.Entry;
import org.platform.snail.edu.model.Teacher;
import org.platform.snail.edu.service.EntryService;
import org.platform.snail.edu.vo.EntryQVo;
import org.platform.snail.edu.vo.EntryVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("entryService")
public class EntryServiceImpl implements EntryService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EntryMapper entryMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findEntryList(EntryQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<EntryVo> list = this.entryMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.entryMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertEntry(JSONObject json, SystemUser systemUser)
			throws Exception {
		Entry o = new Entry();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (o.getEnteryTime()==null) {
			return new DataResponse(false, "入职时间不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDepartmentId())) {
			return new DataResponse(false, "所属部门不能为空！");
		}
		o.setCteateTime(new Date());
		o.setOpertor(systemUser.getUsers().getUserId());
		Teacher t=this.teacherMapper.selectByPrimaryKey(o.getTeacherId());
		this.entryMapper.insert(o);
		this.dataBaseLogService.log("入职登记", "入职", "", t.getName(),
				t.getName(), systemUser);
		return new DataResponse(true, "入职登记完成！");
	}

	public DataResponse updateEntry(JSONObject json, SystemUser systemUser)
			throws Exception {
		Entry o = new Entry();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (o.getEnteryTime()==null) {
			return new DataResponse(false, "入职时间不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDepartmentId())) {
			return new DataResponse(false, "所属部门不能为空！");
		}
		o.setCteateTime(new Date());
		o.setOpertor(systemUser.getUsers().getUserId());
		Teacher t=this.teacherMapper.selectByPrimaryKey(o.getTeacherId());
		
		this.entryMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更入职", "入职", "", t.getName(),
				t.getName(), systemUser);
		return new DataResponse(true, "变更入职完成！");
	}
	public DataResponse saveOrUpdateEntry(JSONObject json, SystemUser systemUser)
			throws Exception {
		Entry o = new Entry();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (o.getEnteryTime()==null) {
			return new DataResponse(false, "入职时间不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDepartmentId())) {
			return new DataResponse(false, "所属部门不能为空！");
		}
		o.setCteateTime(new Date());
		o.setOpertor(systemUser.getUsers().getUserId());
		Teacher t=this.teacherMapper.selectByPrimaryKey(o.getTeacherId());
		this.entryMapper.saveOrUpdateEntry(o);
		this.dataBaseLogService.log("入职登记", "入职", "", t.getName(),
				t.getName(), systemUser);
		return new DataResponse(true, "入职登记完成！");
	}
	public DataResponse selectEntryByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.entryMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteEntryByEntryId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.entryMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除入职", "入职", String.valueOf(id), String.valueOf(id),
				"入职", systemUser);
		return rst;
	}

}
