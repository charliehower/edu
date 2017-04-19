package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.LearningExperienceMapper;
import org.platform.snail.edu.model.LearningExperience;
import org.platform.snail.edu.service.LearningExperienceService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("learningExperienceService")
public class LearningExperienceServiceImpl implements LearningExperienceService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private LearningExperienceMapper learningExperienceMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public List<LearningExperience> findLearningExperienceListByTeacherId(String teacherId)
			throws Exception {
		return this.learningExperienceMapper.findLearningExperienceListByTeacherId(teacherId);
	}

	public DataResponse saveOrUpdateLearningExperience(JSONObject json,
			SystemUser systemUser) throws Exception {
		JSONArray list=json.getJSONArray("list");
		String name=json.getString("name");
		for(Object obj:list){
			JSONObject jo=(JSONObject)obj;
			LearningExperience o=new LearningExperience();
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
			if(SnailUtils.isBlankString(o.getSchoolName())){
				return new DataResponse(false,"学校不能为空！");
			}
			if(SnailUtils.isBlankString(o.getProfessional())){
				return new DataResponse(false,"专业不能为空！");
			}
			if(SnailUtils.isBlankString(o.getEducation())){
				return new DataResponse(false,"业绩不能为空！");
			}
			if(o.getLearningExperienceId()==null||o.getLearningExperienceId().equals(Integer.valueOf(0))){
				this.learningExperienceMapper.insert(o);
			}else{
				this.learningExperienceMapper.updateByPrimaryKey(o);
			}
		}
		this.dataBaseLogService.log("变更学习经历", "教职工",name ,
				name, "教职工",systemUser);
		return new DataResponse(true,"保存成功！");
	}

	public DataResponse selectLearningExperienceByPrimaryKey(String id)
			throws Exception {
		DataResponse rst=new DataResponse();
		rst.setResponse(this.learningExperienceMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteLearningExperienceByLearningExperienceId(
			String id, SystemUser systemUser) throws Exception {
		this.learningExperienceMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除学习经历", "教职工",id ,
				id, "教职工",systemUser);
		return new DataResponse(true,"删除成功！");
	}

}
