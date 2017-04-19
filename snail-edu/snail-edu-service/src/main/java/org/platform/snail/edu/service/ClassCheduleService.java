package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.ClassCheduleQVo;

public interface ClassCheduleService {
	public abstract DataResponse findClassCheduleList(ClassCheduleQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertClassChedule(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateClassChedule(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectClassCheduleByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteClassCheduleByClassCheduleId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateClassChedule(JSONObject json,SystemUser systemUser) throws Exception;

}
