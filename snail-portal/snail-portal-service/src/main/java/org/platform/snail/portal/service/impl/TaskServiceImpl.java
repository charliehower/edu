package org.platform.snail.portal.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.TaskMapper;
import org.platform.snail.portal.model.Task;
import org.platform.snail.portal.service.TaskService;
import org.platform.snail.portal.vo.TaskVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findTaskList(Task condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<TaskVo> list = this.taskMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.taskMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertTask(JSONObject json, SystemUser systemUser)
			throws Exception {
		Task o = new Task();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getTaskId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}
		
		int temp = this.taskMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.taskMapper.insert(o);
		this.dataBaseLogService.log("添加教职工", "教职工", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "添加教职工完成！");
	}

	public DataResponse updateTask(JSONObject json, SystemUser systemUser)
			throws Exception {
		Task o = new Task();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getTaskId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}*/
		
		this.taskMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更教职工", "教职工", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "变更教职工完成！");
	}

	public DataResponse selectTaskByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.taskMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteTaskByTaskId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.taskMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除教职工", "教职工", String.valueOf(id), String.valueOf(id),
				"教职工", systemUser);
		return rst;
	}
	public  DataResponse findListByUserId(SystemUser systemUser)  throws Exception{
		DataResponse rst = new DataResponse();
		List<Task> list=this.taskMapper.findListByUserId(systemUser.getUsers().getUserId());
		rst.setList(list);
		rst.setAllRows(list.size());
		return rst;
	}

}
