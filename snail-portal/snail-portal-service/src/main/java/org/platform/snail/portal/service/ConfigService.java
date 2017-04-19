package org.platform.snail.portal.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.vo.ConfigQVo;

public interface ConfigService {
	public abstract DataResponse findConfigList(ConfigQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertConfig(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateConfig(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectConfigByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteConfigByConfigId(String id,SystemUser systemUser) throws Exception;

}
