package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.AssnSubQVo;

public interface AssnSubService {
	public abstract DataResponse findAssnSubList(AssnSubQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertAssnSub(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateAssnSub(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectAssnSubByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteAssnSubByAssnSubId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateAssnSub(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateAuditByPrimaryKey(JSONObject json,SystemUser systemUser) throws Exception;

}
