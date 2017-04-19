package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.QuitQVo;

public interface QuitService {
	public abstract DataResponse findQuitList(QuitQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertQuit(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateQuit(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectQuitByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteQuitByQuitId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse isExitByTeacherId(String teacherId) throws Exception;
}
