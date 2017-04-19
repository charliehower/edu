package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.TeamGradeQVo;

public interface TeamGradeService {
	public abstract DataResponse findTeamGradeList(TeamGradeQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertTeamGrade(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateTeamGrade(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectTeamGradeByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteTeamGradeByTeamGradeId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateTeamGrade(JSONObject json,SystemUser systemUser) throws Exception;

}
