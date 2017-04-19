package org.platform.snail.edu.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.WorkExperience;

public interface WorkExperienceService {
	public abstract List<WorkExperience> findWorkExperienceListByTeacherId(String teacherId) throws Exception;
	public abstract DataResponse saveOrUpdateWorkExperience(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectWorkExperienceByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteWorkExperienceByWorkExperienceId(String id,SystemUser systemUser) throws Exception;

}
