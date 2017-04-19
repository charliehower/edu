package org.platform.snail.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.ScheduleMapper;
import org.platform.snail.portal.model.Schedule;
import org.platform.snail.portal.service.ScheduleService;
import org.platform.snail.portal.vo.ScheduleQVo;
import org.platform.snail.portal.vo.ScheduleVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ScheduleMapper scheduleMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findScheduleList(ScheduleQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<ScheduleVo> list = this.scheduleMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.scheduleMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertSchedule(JSONObject json, SystemUser systemUser)
			throws Exception {
		Schedule o = new Schedule();
		o.setUserId(systemUser.getUsers().getUserId());
		SnailBeanUtils.copyProperties(o, json);
		o.setId(String.valueOf(new Date().getTime()));
		o.setCreateTime(new Date());
		this.scheduleMapper.insert(o);
		this.dataBaseLogService.log("添加日程", "日程", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "添加日程完成！");
	}

	public DataResponse updateSchedule(JSONObject json, SystemUser systemUser)
			throws Exception {
		Schedule o = new Schedule();
		o.setUserId(systemUser.getUsers().getUserId());
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getId())) {
			return new DataResponse(false, "编号不能为空！");
		}
		o.setCreateTime(new Date());
		Schedule tmp=this.scheduleMapper.selectByPrimaryKey(o.getId());
		if(tmp==null){
			this.scheduleMapper.insert(o);
		}
		this.scheduleMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更日程", "日程", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "变更日程完成！");
	}

	public DataResponse selectScheduleByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.scheduleMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteScheduleByScheduleId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.scheduleMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除日程", "日程", String.valueOf(id), String.valueOf(id),
				"日程", systemUser);
		return rst;
	}
	public  List<Map<String,Object>> selectDepUserListByDepId(String id){
		return this.scheduleMapper.selectDepUserListByDepId(id);
	}

}
