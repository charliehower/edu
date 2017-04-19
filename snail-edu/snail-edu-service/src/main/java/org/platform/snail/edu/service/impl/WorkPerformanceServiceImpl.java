package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.WorkPerformanceMapper;
import org.platform.snail.edu.model.WorkPerformance;
import org.platform.snail.edu.service.WorkPerformanceService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("workPerformanceService")
public class WorkPerformanceServiceImpl implements WorkPerformanceService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private WorkPerformanceMapper workPerformanceMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	
	public List<WorkPerformance> findWorkPerformanceListByTeacherId(
			String teacherId) throws Exception {
		return this.workPerformanceMapper.findWorkPerformanceListByTeacherId(teacherId);
	}

	public DataResponse saveOrUpdateWorkPerformance(JSONObject json,
			SystemUser systemUser) throws Exception {
		JSONArray list=json.getJSONArray("list");
		String name=json.getString("name");
		for(Object obj:list){
			JSONObject jo=(JSONObject)obj;
			WorkPerformance o=new WorkPerformance();
			SnailBeanUtils.copyProperties(o, jo);
			o.setCreateTime(new Date());
			if(SnailUtils.isBlankString(o.getTeacherId())){
				return new DataResponse(false,"教职工编号不能为空！");
			}
			if(SnailUtils.isBlankString(o.getClasses())){
				return new DataResponse(false,"班级不能为空！");
			}
			if(SnailUtils.isBlankString(o.getDuty())){
				return new DataResponse(false,"职责不能为空！");
			}
			if(SnailUtils.isBlankString(o.getDescription())){
				return new DataResponse(false,"在岗表现不能为空！");
			}
			if(SnailUtils.isBlankString(o.getRecord())){
				return new DataResponse(false,"记录人不能为空！");
			}
			if(o.getWorkPerformanceId()==null||o.getWorkPerformanceId().equals(Integer.valueOf(0))){
				this.workPerformanceMapper.insert(o);
			}else{
				this.workPerformanceMapper.updateByPrimaryKey(o);
			}
		}
		this.dataBaseLogService.log("变更在岗表现", "教职工",name ,
				name, "教职工",systemUser);
		return new DataResponse(true,"保存成功！");
	}

	
	public DataResponse selectWorkPerformanceByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.workPerformanceMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteWorkPerformanceByWorkPerformanceId(String id,
			SystemUser systemUser) throws Exception {
		this.workPerformanceMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除在岗表现", "教职工", String.valueOf(id), String.valueOf(id),
				"教职工", systemUser);
		return new DataResponse(true,"删除成功！");
	}
	
}
