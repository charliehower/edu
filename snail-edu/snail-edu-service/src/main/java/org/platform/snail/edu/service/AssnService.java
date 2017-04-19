package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.AssnQVo;

public interface AssnService {
	public abstract DataResponse findAssnList(AssnQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertAssn(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateAssn(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectAssnByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteAssnByAssnId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateAssn(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse insertAssnSubReg(JSONObject json, SystemUser systemUser)
			throws Exception;

}
