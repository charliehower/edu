package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.TeamTrQVo;

public interface TeamTrService {
	public abstract DataResponse findTeamTrList(TeamTrQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertTeamTr(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateTeamTr(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectTeamTrByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteTeamTrByTeamTrId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateTeamTr(JSONObject json,SystemUser systemUser) throws Exception;

}
