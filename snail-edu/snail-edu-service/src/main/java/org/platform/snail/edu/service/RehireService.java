package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.RehireQVo;

public interface RehireService {
	public abstract DataResponse findRehireList(RehireQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertRehire(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateRehire(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectRehireByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteRehireByRehireId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse isExitByTeacherId(String teacherId) throws Exception;

}
