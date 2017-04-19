package org.platform.snail.workflow.web.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.utils.CommonKeys;
import org.platform.snail.web.action.BaseController;
import org.platform.snail.workflow.service.WorkflowService;
import org.platform.snail.workflow.vo.ProcessDefinitionVo;
import org.platform.snail.workflow.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/workflow")
public class WorkflowAction extends BaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private WorkflowService workflowService;
	@RequestMapping(value = "/deploy.do")
	@ResponseBody
	public DataResponse deploy(@RequestParam MultipartFile[] file, String id)
			throws Exception {
		try {
			return this.workflowService.deploy(file, id, this.getSessionSystemUser());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"发布失败",e.getMessage());
		}
		
	}

	@RequestMapping(value = "/findWorkflowList.do")
	@ResponseBody
	public DataResponse findWorkflowList(Page page) throws Exception {
		try {			
			return this.workflowService.findWorkflowList(page.getStart(),page.getLimit());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"加载失败",e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteWorkflowById.do")
	@ResponseBody
	public DataResponse deleteWorkflowById(String jsons) throws Exception {
		
		try {
			JSONObject json = JSONObject.fromObject(jsons);
			String arg0 = json.getString("id");
			return this.workflowService.deleteWorkflowById(arg0, this.getSessionSystemUser());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"卸载失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/findTaskByProcessInstanceId.do")
	@ResponseBody
	public DataResponse findTaskByProcessInstanceId(String instanceId) throws Exception {
		try {
			return this.workflowService.findTaskByProcessInstanceId(instanceId);
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"加载失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/findHistoryTaskByProcessInstanceId.do")
	@ResponseBody
	public DataResponse findHistoryTaskByProcessInstanceId(String instanceId) throws Exception {
		try {
			return this.workflowService.findHistoryTaskByProcessInstanceId(instanceId);
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"加载失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/startProcessInstanceByKey.do")
	@ResponseBody
	public DataResponse startProcessInstanceByKey(String key,String proxy) throws Exception {
		try {
			
			Map<String,String> cfg=(Map<String,String>)this.getRequest().getSession().getServletContext().getAttribute(CommonKeys.cfg);
			return this.workflowService.startProcessInstanceByKey(key, proxy,this.getSessionSystemUser(),cfg);
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"启动流程实例失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/completeTask.do")
	@ResponseBody
	public DataResponse completeTask(String taskId,HttpServletRequest request) throws Exception {
		try {
			Map<String, Object> variables = new HashMap<String, Object>();  
			Enumeration e=request.getParameterNames();
			while(e.hasMoreElements()){
				String key=(String)e.nextElement();
				String value=request.getParameter(key);
				variables.put(key, value);
			}
			this.logger.info("taskId ->"+taskId);
			this.logger.info(variables);
			return this.workflowService.completeTask(taskId,variables,this.getSessionSystemUser());
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error(e);
			return new DataResponse(false,"执行任务失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/getTask.do")
	@ResponseBody
	public DataResponse getTask(String taskId) throws Exception {
		try {
			this.logger.info("taskId ->"+taskId);
			return this.workflowService.getTask(taskId);
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"查询任务失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/findGroupTask.do")
	@ResponseBody
	public DataResponse findGroupTask() throws Exception {
		try {
			DataResponse dr=this.workflowService.findGroupTasks(this.getSessionSystemUser());
			this.logger.info(dr.getAllRows());
			return dr;
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"查询任务失败",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/findPersonalTask.do")
	@ResponseBody
	public DataResponse findPersonalTask() throws Exception {
		try {
			return this.workflowService.findPersonalTasks(this.getSessionSystemUser());
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error(e);
			return new DataResponse(false,"查询任务失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/findHistoryProcessInstance.do")
	@ResponseBody
	public DataResponse findHistoryProcessInstance(HttpServletRequest request,Page page) throws Exception {
		try {
			Map<String, Object> variables = new HashMap<String, Object>();  
			@SuppressWarnings("unchecked")
			Enumeration<String> e=request.getParameterNames();
			while(e.hasMoreElements()){
				String key=(String)e.nextElement();
				String value=request.getParameter(key);
				variables.put(key, value);
			}
			variables.put("userId", this.getSessionSystemUser().getUsers().getUserId());
			this.logger.info(variables);
			return this.workflowService.findHistoryProcessInstance(variables, page.getStart(),page.getLimit(), page.getOrderBy());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"加载失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteProcessInstanceCascade.do")
	@ResponseBody
	public DataResponse deleteProcessInstanceCascade(String instanceId) throws Exception {
		try {
			
			return this.workflowService.deleteProcessInstanceCascade(instanceId, this.getSessionSystemUser());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"撤销失败",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteProcessInstanceCascadeByTaskId.do")
	@ResponseBody
	public DataResponse deleteProcessInstanceCascadeByTaskId(String taskId) throws Exception {
		try {
			
			return this.workflowService.deleteProcessInstanceCascadeByTaskId(taskId, this.getSessionSystemUser());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"撤销失败",e.getMessage());
		}
	}
}
