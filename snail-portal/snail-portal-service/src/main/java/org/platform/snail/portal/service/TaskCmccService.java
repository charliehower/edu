package org.platform.snail.portal.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.vo.TaskCmccQVo;

public interface TaskCmccService {
	public abstract DataResponse findTaskCmccList(TaskCmccQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertTaskCmcc(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateTaskCmcc(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectTaskCmccByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteTaskCmccByTaskCmccId(String id,SystemUser systemUser) throws Exception;
	public abstract void queueTask() throws Exception;
	public abstract void queueTaskDetail() throws Exception;
	public abstract DataResponse updateTaskStatusCmccByTaskCmccId(String id,
			SystemUser systemUser) throws Exception;
	
	public abstract void workFlowMsgTask() throws Exception;

}
