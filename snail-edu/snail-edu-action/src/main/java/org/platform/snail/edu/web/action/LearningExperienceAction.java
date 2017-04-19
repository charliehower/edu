package org.platform.snail.edu.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.LearningExperience;
import org.platform.snail.edu.service.LearningExperienceService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/learningExperience")
public class LearningExperienceAction extends BaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private LearningExperienceService learningExperienceService;
	@RequestMapping(value = "/findLearningExperienceListByTeacherId.do")
	@ResponseBody
	Map<String,Object> findLearningExperienceListByTeacherId(String teacherId){
		try{
			
			List<LearningExperience> list= this.learningExperienceService.findLearningExperienceListByTeacherId(teacherId);
			
			Map<String,Object> rst=new HashMap<String,Object>();
			rst.put("total", list.size());
			rst.put("rows", list);
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return null;
		}
	}
	
	@RequestMapping(value = "/saveOrUpdateLearningExperience.do")
	@ResponseBody
	DataResponse saveOrUpdateLearningExperience(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.learningExperienceService.saveOrUpdateLearningExperience(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/selectLearningExperienceByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectLearningExperienceByPrimaryKey(String id){
		try{
			return this.learningExperienceService.selectLearningExperienceByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteLearningExperienceByLearningExperienceId.do")
	@ResponseBody
	public  DataResponse deleteTeacherByTeacherId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.learningExperienceService.deleteLearningExperienceByLearningExperienceId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	

}
