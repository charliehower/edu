package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.RepairsQVo;

public interface RepairsService {
	public abstract DataResponse findRepairsList(RepairsQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertRepairs(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateRepairs(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectRepairsByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteRepairsByRepairsId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateRepairs(JSONObject json,SystemUser systemUser) throws Exception;

}
