package org.platform.snail.workflow.service;

import java.util.Map;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 陈晓克
 * @version 2013-12-23 上午11:27:54
 */
public interface WorkflowService {

	public DataResponse deploy(MultipartFile[] file, String id,
			SystemUser systemUser) throws Exception;

	public DataResponse findWorkflowList(int start, int limit) throws Exception;

	public DataResponse deleteWorkflowById(String jsons, SystemUser systemUser)
			throws Exception;

	public DataResponse startProcessInstanceByKey(String key,String proxy,SystemUser systemUser,Map<String,String> cfg) throws Exception;
	
	public DataResponse completeTask(String taskId,Map<String, Object> variables,SystemUser systemUser) throws Exception;
	
	public DataResponse findPersonalTasks(SystemUser systemUser) throws Exception;
	
	public DataResponse getTask(String taskId) throws Exception;
	
	public DataResponse findTaskByProcessInstanceId(String instanceId) throws Exception;
	
	public DataResponse findGroupTasks(SystemUser systemUser)throws Exception;
	
	public DataResponse findHistoryTaskByProcessInstanceId(String instanceId) throws Exception;
	
	public DataResponse findHistoryProcessInstance(Map<String,Object> condition, int start,
			int limit, String orderBy) throws Exception ;
	
	public DataResponse  deleteProcessInstanceCascade(String instanceId,SystemUser systemUser ) throws Exception;
	
	public DataResponse  deleteProcessInstanceCascadeByTaskId(String taskId,SystemUser systemUser ) throws Exception;
	public Map<String,?> getVariablesByTaskId(String taskId)
			throws Exception;
	public Map<String,?> getVariablesByInstanceId(String instanceId)
			throws Exception;
	

}
