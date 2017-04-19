package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.PositiveQVo;

public interface PositiveService {
	public abstract DataResponse findPositiveList(PositiveQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertPositive(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updatePositive(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectPositiveByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deletePositiveByPositiveId(String id,SystemUser systemUser) throws Exception;
	
	public abstract DataResponse isExitByTeacherId(String teacherId) throws Exception;

}
