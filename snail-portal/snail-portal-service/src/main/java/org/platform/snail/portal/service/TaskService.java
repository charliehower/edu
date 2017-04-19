package org.platform.snail.portal.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.model.Task;

public interface TaskService {
	public abstract DataResponse findTaskList(Task condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertTask(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateTask(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectTaskByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteTaskByTaskId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse findListByUserId(SystemUser systemUser)  throws Exception;

}
