package org.platform.snail.portal.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.vo.TemplateCmccQVo;

public interface TemplateCmccService {
	public abstract DataResponse findTemplateCmccList(TemplateCmccQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertTemplateCmcc(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateTemplateCmcc(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectTemplateCmccByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteTemplateCmccByTemplateCmccId(String id,SystemUser systemUser) throws Exception;

}
