package org.platform.snail.edu.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.LearningExperience;

public interface LearningExperienceService {
	public abstract List<LearningExperience> findLearningExperienceListByTeacherId(String teacherId) throws Exception;
	public abstract DataResponse saveOrUpdateLearningExperience(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectLearningExperienceByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteLearningExperienceByLearningExperienceId(String id,SystemUser systemUser) throws Exception;

}
