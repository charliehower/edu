package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.ArrangementQVo;

public interface ArrangementService {
	public abstract DataResponse findArrangementList(ArrangementQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertArrangement(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateArrangement(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectArrangementByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteArrangementByArrangementId(String id,SystemUser systemUser) throws Exception;
	
	public abstract DataResponse saveOrUpdateArrangement(JSONObject json,SystemUser systemUser) throws Exception;

}
