package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.WorkExperienceMapper;
import org.platform.snail.edu.model.WorkExperience;
import org.platform.snail.edu.service.WorkExperienceService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("workExperienceService")
public class WorkExperienceServiceImpl implements WorkExperienceService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private WorkExperienceMapper workExperienceMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public List<WorkExperience> findWorkExperienceListByTeacherId(
			String teacherId) throws Exception {
		return this.workExperienceMapper.findWorkExperienceListByTeacherId(teacherId);
	}

	public DataResponse saveOrUpdateWorkExperience(JSONObject json,
			SystemUser systemUser) throws Exception {
		JSONArray list=json.getJSONArray("list");
		String name=json.getString("name");
		for(Object obj:list){
			JSONObject jo=(JSONObject)obj;
			WorkExperience o=new WorkExperience();
			SnailBeanUtils.copyProperties(o, jo);
			o.setCreateTime(new Date());
			if(o.getDateStart()==null){
				return new DataResponse(false,"开始时间不能为空！");
			}
			if(o.getDateEnd()==null){
				return new DataResponse(false,"截止时间不能为空！");
			}
			if(SnailUtils.isBlankString(o.getTeacherId())){
				return new DataResponse(false,"教职工编号不能为空！");
			}
			if(SnailUtils.isBlankString(o.getWorkUnit())){
				return new DataResponse(false,"工作单位不能为空！");
			}
			if(SnailUtils.isBlankString(o.getPosition())){
				return new DataResponse(false,"职务不能为空！");
			}
			if(SnailUtils.isBlankString(o.getPerformance())){
				return new DataResponse(false,"业绩不能为空！");
			}
			if(o.getWorkExperienceId()==null||o.getWorkExperienceId().equals(Integer.valueOf(0))){
				this.workExperienceMapper.insert(o);
			}else{
				this.workExperienceMapper.updateByPrimaryKeyWithBLOBs(o);
			}
		}
		this.dataBaseLogService.log("变更工作经历", "教职工",name ,
				name, "教职工",systemUser);
		return new DataResponse(true,"保存成功！");
	}

	public DataResponse selectWorkExperienceByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.workExperienceMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteWorkExperienceByWorkExperienceId(String id,
			SystemUser systemUser) throws Exception {
		this.workExperienceMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除工作经历", "教职工", String.valueOf(id), String.valueOf(id),
				"教职工", systemUser);
		return new DataResponse(true,"删除成功！");	}

}
