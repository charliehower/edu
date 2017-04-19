package org.platform.snail.workflow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryProcessInstanceQuery;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.service.SystemService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.workflow.dao.WorkflowDao;
import org.platform.snail.workflow.service.WorkflowService;
import org.platform.snail.workflow.vo.ProcessDefinitionVo;
import org.platform.snail.workflow.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 陈晓克
 * @version 2013-12-23 下午1:16:07
 */
@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {
	@Autowired
	private WorkflowDao workflowDao;

	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ExecutionService executionService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private SystemService systemService;
	

	public DataResponse deploy(MultipartFile[] file, String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		ZipInputStream zipInputStream = null;
		String key = null;
		for (int i = 0; i < file.length; i++) {
			this.logger.info(file[i].getOriginalFilename());
			if (file[i].getOriginalFilename().toLowerCase().indexOf("zip") != -1) {
				zipInputStream = new ZipInputStream(file[i].getInputStream());
				key = this.repositoryService.createDeployment()
						.addResourcesFromZipInputStream(zipInputStream)
						.deploy();
				this.logger.info(file[i].getOriginalFilename()
						+ " deploy complete");
			} else {
				this.logger.error(file[i].getOriginalFilename()
						+ " is not zip file upload complete deploy fail");
			}
			this.dataBaseLogService.log("发布流程", "流程管理", "",
					file[i].getOriginalFilename(), key, systemUser);
		}
		if (zipInputStream != null) {
			zipInputStream.close();
		}
		rst.setErrorMessage("发布成功");
		rst.setResponse(id);
		return rst;
	}

	public DataResponse findWorkflowList(int start, int limit) throws Exception {
		this.logger.info("start "+start);
		this.logger.info("limit "+limit);
		if(limit==0){
			limit=5;
		}
		DataResponse rst = new DataResponse();
		List l = new ArrayList();
		List<ProcessDefinition> list = this.processEngine
				.getRepositoryService().createProcessDefinitionQuery().page(start, limit).list();
		for (ProcessDefinition pd : list) {
			ProcessDefinitionVo pv = new ProcessDefinitionVo();
			SnailBeanUtils.copyProperties(pv, pd);
			l.add(pv);
		}
		rst.setList(l);
		rst.setAllRows(l.size());
		return rst;
	}

	public DataResponse deleteWorkflowById(String arg0, SystemUser systemUser)
			throws Exception {
		DataResponse rst = new DataResponse();
		this.processEngine.getRepositoryService().deleteDeploymentCascade(arg0);
		rst.setErrorMessage("卸载成功！");
		this.dataBaseLogService.log("卸载流程", "流程管理", "", "", arg0, systemUser);
		return rst;
	}
	private SystemUser getSystemUser(SystemUser systemUser){
		SystemUser su=new SystemUser();
		su.setUsers(systemUser.getUsers());
		su.setDepartment(systemUser.getDepartment());
		su.setLeader1(systemUser.getLeader1());
		su.setLeader2(systemUser.getLeader2());
		su.setLeader3(systemUser.getLeader3());
		su.setLeader4(systemUser.getLeader4());
		su.setLeader5(systemUser.getLeader5());
		su.setDpFullName(systemUser.getDpFullName());
		su.setLeaderFullName(systemUser.getLeaderFullName());
		su.setOther(systemUser.getOther());
		return su;
	}
	public DataResponse startProcessInstanceByKey(String key,String proxy,
			SystemUser systemUser,Map<String,String> cfg) throws Exception {
		DataResponse rst = new DataResponse();
		SystemUser proxyUser=null;
		Map<String, Object> variablesHistory=new HashMap<String, Object>();
		String userId = systemUser.getUsers().getUserId();
		String formResourceName = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemUser",this.getSystemUser(systemUser));
		variablesHistory.put("assigneeId",systemUser.getUsers().getUserId());
		variablesHistory.put("assignee",systemUser.getUsers().getName());
		variablesHistory.put("department",systemUser.getDepartment().getDepartmentName());
		variablesHistory.put("dpFullName",systemUser.getDpFullName());
		variablesHistory.put("leaderFullName",systemUser.getLeaderFullName());
		variablesHistory.put("leaderName",systemUser.getLeaderFullName().split("/")[0]);
		if(SnailUtils.isNotBlankString(proxy)){
			proxyUser=this.systemService.getSystemUser(proxy,cfg);
			params.put("proxy",proxyUser);
			SystemUser p=this.getSystemUser(proxyUser);
			p.getUsers().setUserId(systemUser.getUsers().getUserId());
			params.put("systemUser",p);
			
		}
		
		ProcessInstance instance = this.processEngine.getExecutionService()
				.startProcessInstanceByKey(key, params);
		
		String instanceId = instance.getId();
		if(SnailUtils.isNotBlankString(proxy)){
			variablesHistory.put("assignee",proxyUser.getUsers().getName());
			variablesHistory.put("assigneeId",proxyUser.getUsers().getUserId());
			variablesHistory.put("department",proxyUser.getDepartment().getDepartmentName());
			variablesHistory.put("dpFullName",proxyUser.getDpFullName());
			variablesHistory.put("leaderFullName",proxyUser.getLeaderFullName());
			variablesHistory.put("leaderName",proxyUser.getLeaderFullName().split("/")[0]);
			
			variablesHistory.put("proxy_assignee",systemUser.getUsers().getName());
			variablesHistory.put("proxy_assigneeId",systemUser.getUsers().getUserId());
			variablesHistory.put("proxy_department",systemUser.getDepartment().getDepartmentName());
			variablesHistory.put("proxy_dpFullName",systemUser.getDpFullName());
			variablesHistory.put("proxy_leaderFullName",systemUser.getLeaderFullName());
			variablesHistory.put("proxy_leaderName",systemUser.getLeaderFullName().split("/")[0]);
		}
		
		this.processEngine.getExecutionService().createVariables(instanceId, variablesHistory, true);
		this.logger.info("instanceId " + instanceId);
		List<Task> list = this.taskService.createTaskQuery()
				.processInstanceId(instanceId).assignee(userId).list();
		TaskVo e=new TaskVo();
		for (Task task : list) {
			formResourceName = task.getFormResourceName();
			this.logger.info("task name-> " + task.getName()
					+ " activityName-> " + task.getActivityName()
					+ " assignee-> " + task.getAssignee());
			this.logger.info("formResourceName " + formResourceName);
			SnailBeanUtils.copyProperties(e, task);
		}
		rst.setResponse(e);
		this.dataBaseLogService.log("开启流程实例", e.getName(), "",
				instanceId, key, systemUser);
		return rst;
	}

	public DataResponse completeTask(String taskId,
			Map<String, Object> variables, SystemUser systemUser)
			throws Exception {
		boolean isProxy=false;
		DataResponse rst = new DataResponse();
		Task task = this.taskService.getTask(taskId);
		taskService.setVariables(task.getId(), variables);
		Map<String, Object> variablesHistory=new HashMap<String, Object>();
		Iterator<String> it=variables.keySet().iterator();
		String key=null;
		while(it.hasNext()){
			key=it.next();
			variablesHistory.put(taskId+"_"+key, variables.get(key));
		}
		Set<String> set=this.processEngine.getExecutionService().getVariableNames(task.getExecutionId());
		
		
		if(set.contains("proxy")){
			long len=this.processEngine.getHistoryService().createHistoryTaskQuery().executionId(task.getExecutionId()).count();
			if(len==1){
				isProxy=true;
			}
		}
		this.logger.info("isProxy->"+isProxy);
		if(isProxy){
			SystemUser proxy=(SystemUser)this.processEngine.getExecutionService().getVariable(task.getExecutionId(), "proxy");
			variablesHistory.put(taskId+"_assignee",proxy.getUsers().getName());
			variablesHistory.put(taskId+"_assigneeId",proxy.getUsers().getUserId());
			variablesHistory.put(taskId+"_department",proxy.getDepartment().getDepartmentName());
			variablesHistory.put(taskId+"_dpFullName",proxy.getDpFullName());
			variablesHistory.put(taskId+"_leaderFullName",proxy.getLeaderFullName());
			variablesHistory.put(taskId+"_leaderName",proxy.getLeaderFullName().split("/")[0]);
			variablesHistory.put(taskId+"_proxy_assigneeId",systemUser.getUsers().getUserId());
			variablesHistory.put(taskId+"_proxy_assignee",systemUser.getUsers().getName());
			variablesHistory.put(taskId+"_proxy_department",systemUser.getDepartment().getDepartmentName());
			variablesHistory.put(taskId+"_proxy_dpFullName",systemUser.getDpFullName());
			variablesHistory.put(taskId+"_proxy_leaderFullName",systemUser.getLeaderFullName());
			variablesHistory.put(taskId+"_proxy_leaderName",systemUser.getLeaderFullName().split("/")[0]);
		}else{
			variablesHistory.put(taskId+"_assignee",systemUser.getUsers().getName());
			variablesHistory.put(taskId+"_assigneeId",systemUser.getUsers().getUserId());
			variablesHistory.put(taskId+"_department",systemUser.getDepartment().getDepartmentName());
			variablesHistory.put(taskId+"_dpFullName",systemUser.getDpFullName());
			variablesHistory.put(taskId+"_leaderFullName",systemUser.getLeaderFullName());
			variablesHistory.put(taskId+"_leaderName",systemUser.getLeaderFullName().split("/")[0]);
		}
		variablesHistory.put(taskId+"_taskName",task.getName());
		this.logger.info("executionId->"+task.getExecutionId());
		this.processEngine.getExecutionService().createVariables(task.getExecutionId(), variablesHistory, true);
		this.logger.info("completeTask->"+taskId);
		taskService.completeTask(taskId);
		rst.setErrorMessage("提交成功！");
		this.dataBaseLogService.log("工作流提交任务", task.getName(), "",
				taskId, task.getExecutionId(), systemUser);
		return rst;
	}
	private ProcessDefinition getProcessDefinitionByExecutionId(String executionId){
		
		executionId=executionId.substring(0, executionId.indexOf("."));
		this.logger.info("executionId->"+executionId);
		return processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(executionId).uniqueResult();
	}
	private String findActiveActivityNameByExecutionId(String executionId){
		Execution pi=processEngine.getExecutionService().findExecutionById(executionId);
		if(pi==null){
			return "流程结束";
		}
		Set<String> set=pi.findActiveActivityNames();
		Iterator<String> it=set.iterator();
		String activeName="";
		while(it.hasNext()){
			activeName=it.next();
		}
		List<Task> lt=this.taskService.createTaskQuery().executionId(executionId).activityName(activeName).list();
		if(lt!=null&&lt.size()>0){
			String name="";
			if(lt.get(0).getAssignee()!=null){
				
				name=this.workflowDao.findUserById(lt.get(0).getAssignee()).getGivenName();
			}
			activeName=activeName+"·"+name;
		}
		
		return activeName;
	}
	public DataResponse findPersonalTasks(
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		List<Task> items = this.taskService.findPersonalTasks(systemUser
				.getUsers().getUserId());
		List<TaskVo> list=new ArrayList<TaskVo>();
		for(Task o:items){
			TaskVo e=new TaskVo();
			ProcessDefinition pd=this.getProcessDefinitionByExecutionId(o.getExecutionId());
			e.setActivityName(o.getActivityName());
			e.setAssignee(o.getAssignee());
			e.setCreateTime(o.getCreateTime());
			e.setDescription(o.getDescription());
			e.setDuedate(o.getDuedate());
			e.setExecutionId(o.getExecutionId());
			e.setFormResourceName(o.getFormResourceName());
			e.setId(o.getId());
			e.setName(pd.getName()+"·"+o.getName()+"·"+systemUser
					.getUsers().getName());
			e.setPriority(o.getPriority());
			e.setProgress(o.getProgress());
			list.add(e);
		}
		items = this.taskService.findGroupTasks(systemUser.getUsers()
				.getUserId());
		for(Task o:items){
			ProcessDefinition pd=this.getProcessDefinitionByExecutionId(o.getExecutionId());
			TaskVo e=new TaskVo();
			e.setActivityName(o.getActivityName());
			e.setAssignee(o.getAssignee());
			e.setCreateTime(o.getCreateTime());
			e.setDescription(o.getDescription());
			e.setDuedate(o.getDuedate());
			e.setExecutionId(o.getExecutionId());
			e.setFormResourceName(o.getFormResourceName());
			e.setId(o.getId());
			e.setName(pd.getName()+"·"+o.getName()+"·"+systemUser
					.getUsers().getName());
			e.setPriority(o.getPriority());
			e.setProgress(o.getProgress());
			list.add(e);
		}
		rst.setList(list);
		rst.setAllRows(list.size());
		rst.setErrorMessage("加载成功");
		return rst;
	}

	public DataResponse getTask(String taskId) throws Exception {
		DataResponse rst = new DataResponse();
		Task task = this.taskService.getTask(taskId);
		 Set<String> set = taskService.getVariableNames(taskId);  
	    Map<String, Object> variables = taskService.getVariables(taskId, set); 
		TaskVo dest=new TaskVo();
		SnailBeanUtils.copyProperties(dest, task);
		dest.setVariables(variables);
		rst.setResponse(dest);
		rst.setErrorMessage("加载成功");
		return rst;
	}
	private Map<String,?> getVariables(String taskId,Map<String,?> params){
		 Map<String, Object> variables=new HashMap<String, Object>();
		 Iterator<String> it=params.keySet().iterator();
		 String key=null;
		 while(it.hasNext()){
		 	key=it.next();
		 	if(key.startsWith(taskId+"_")){
		 		variables.put(key.replaceAll(taskId+"_", ""), params.get(key));
		 	}
		 }
		 return variables;
	}
	public DataResponse findTaskByProcessInstanceId(String instanceId)
			throws Exception {
		DataResponse rst = new DataResponse();
		List<Task> items = this.taskService.createTaskQuery()
				.processInstanceId(instanceId).list();
		Set<String> set=processEngine.getHistoryService().getVariableNames(instanceId);
		Map<String,?> params=processEngine.getHistoryService().getVariables(instanceId, set);
		ProcessDefinition pd=this.getProcessDefinitionByExecutionId(instanceId);
		String activeName=this.findActiveActivityNameByExecutionId(instanceId);
		List list=new ArrayList();
		for(Task o:items){
			TaskVo e=new TaskVo();
			e.setActivityName(o.getActivityName());
			e.setAssignee(o.getAssignee());
			e.setCreateTime(o.getCreateTime());
			e.setDescription(o.getDescription());
			e.setDuedate(o.getDuedate());
			e.setExecutionId(o.getExecutionId());
			e.setFormResourceName(o.getFormResourceName());
			e.setId(o.getId());
			e.setName(o.getName());
			e.setPriority(o.getPriority());
			e.setProgress(o.getProgress());
			e.setVariables(this.getVariables(o.getId(), params));
			list.add(e);
		}
		rst.setList(list);
		rst.setAllRows(list.size());
		rst.setErrorMessage("加载成功");
		rst.setResponse(pd.getName());
		return rst;
	}

	public DataResponse findGroupTasks(SystemUser systemUser)
			throws Exception {
		DataResponse rst = new DataResponse();
		List<Task> items = this.taskService.findGroupTasks(systemUser.getUsers()
				.getUserId());
		List list=new ArrayList();
		for(Task o:items){
			TaskVo e=new TaskVo();
			e.setActivityName(o.getActivityName());
			e.setAssignee(o.getAssignee());
			e.setCreateTime(o.getCreateTime());
			e.setDescription(o.getDescription());
			e.setDuedate(o.getDuedate());
			e.setExecutionId(o.getExecutionId());
			e.setFormResourceName(o.getFormResourceName());
			e.setId(o.getId());
			e.setName(o.getName());
			e.setPriority(o.getPriority());
			e.setProgress(o.getProgress());
			list.add(e);
		}
		rst.setList(list);
		rst.setAllRows(list.size());
		rst.setErrorMessage("加载成功");
		return rst;
	}
	public DataResponse findHistoryTaskByProcessInstanceId(String instanceId)
			throws Exception {
		DataResponse rst = new DataResponse();
		Map<String,Object> globle=new HashMap<String,Object>();
		String name="";
		List<HistoryTask> items = this.processEngine.getHistoryService().createHistoryTaskQuery().executionId(instanceId).list();
		Set<String> set=processEngine.getHistoryService().getVariableNames(instanceId);
		Map<String,?> params=processEngine.getHistoryService().getVariables(instanceId, set);
		ProcessDefinition pd=this.getProcessDefinitionByExecutionId(instanceId);
		if(pd!=null){
			name=pd.getName();
		}
		String activeTaskName=this.findActiveActivityNameByExecutionId(instanceId);
		List<TaskVo> list=new ArrayList<TaskVo>();
		for(HistoryTask o:items){
			TaskVo e=new TaskVo();
			e.setAssignee(o.getAssignee());
			e.setCreateTime(o.getCreateTime());
			e.setExecutionId(o.getExecutionId());
			e.setId(o.getId());
			e.setVariables(this.getVariables(o.getId(), params));
			list.add(e);
			globle.putAll(e.getVariables());
		}
		rst.getOther().put("instanceId", instanceId);
		rst.getOther().put("name", name);
		rst.getOther().put("activeTaskName",activeTaskName);
		rst.getOther().put("date",list.get(0).getCreateTime());
		rst.getOther().put("assignee",list.get(0).getVariables().get("assignee"));
		rst.getOther().put("department",list.get(0).getVariables().get("department"));
		rst.getOther().put("leaderFullName",list.get(0).getVariables().get("leaderFullName"));
		rst.getOther().put("dpFullName",list.get(0).getVariables().get("dpFullName"));
		rst.getOther().put("leaderName",list.get(0).getVariables().get("leaderName"));
		rst.setList(list);
		rst.setResponse(list.get(0).getVariables());
		rst.setAllRows(list.size());
		rst.setGloble(globle);
		rst.setErrorMessage("加载成功");
		return rst;
	}
	public DataResponse findHistoryProcessInstance(String key)
			throws Exception {
		DataResponse rst = new DataResponse();
		List<HistoryProcessInstance> list = this.processEngine.getHistoryService()
			      .createHistoryProcessInstanceQuery().processInstanceKey(key).orderAsc(HistoryProcessInstanceQuery.PROPERTY_STARTTIME)
			      .list();
		rst.setList(list);
		rst.setAllRows(list.size());
		rst.setErrorMessage("加载成功");
		return rst;
	}
	public DataResponse findHistoryProcessInstance(Map<String,Object> condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<Map<String,Object>> list = this.workflowDao.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.workflowDao.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}
	public DataResponse  deleteProcessInstanceCascade(String instanceId,SystemUser systemUser) throws Exception{
		ProcessDefinition pd=this.getProcessDefinitionByExecutionId(instanceId);
		this.dataBaseLogService.log("撤销工作流", pd.getName(), "",
				instanceId, pd.getKey(), systemUser);
		this.executionService.deleteProcessInstanceCascade(instanceId);
		return new DataResponse(true,"撤销成功！");
	}
	public DataResponse  deleteProcessInstanceCascadeByTaskId(String taskId,SystemUser systemUser) throws Exception{
		Task task=this.taskService.getTask(taskId);
		String instanceId=task.getExecutionId();
		ProcessDefinition pd=this.getProcessDefinitionByExecutionId(instanceId);
		this.dataBaseLogService.log("撤销工作流", pd.getName(), "",
				instanceId, pd.getKey(), systemUser);
		this.executionService.deleteProcessInstanceCascade(instanceId);
		return new DataResponse(true,"撤销成功！");
	}
	public Map<String,?> getVariablesByTaskId(String taskId)
			throws Exception {
		Task task=this.taskService.getTask(taskId);
		String instanceId=task.getExecutionId();
		Set<String> set=processEngine.getHistoryService().getVariableNames(instanceId);
		Map<String,?> params=processEngine.getHistoryService().getVariables(instanceId, set);
		return params;
	}
	public Map<String,?> getVariablesByInstanceId(String instanceId)
			throws Exception{
		Set<String> set=processEngine.getHistoryService().getVariableNames(instanceId);
		Map<String,?> params=processEngine.getHistoryService().getVariables(instanceId, set);
		return params;
	}
}
