package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.TeamPrepareQVo;

public interface TeamPrepareService {
	public abstract DataResponse findTeamPrepareList(TeamPrepareQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertTeamPrepare(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateTeamPrepare(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectTeamPrepareByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteTeamPrepareByTeamPrepareId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateTeamPrepare(JSONObject json,SystemUser systemUser) throws Exception;
}
