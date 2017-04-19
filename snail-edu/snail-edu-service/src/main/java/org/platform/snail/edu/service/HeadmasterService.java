package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.Headmaster;

public interface HeadmasterService {
	public abstract DataResponse findHeadmasterList(Headmaster condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertHeadmaster(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateHeadmaster(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectHeadmasterByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteHeadmasterByHeadmasterId(String id,SystemUser systemUser) throws Exception;
	
	public abstract DataResponse saveOrUpdateHeadmaster(JSONObject json,SystemUser systemUser) throws Exception;


}
