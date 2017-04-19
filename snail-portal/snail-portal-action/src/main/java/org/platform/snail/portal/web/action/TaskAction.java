package org.platform.snail.portal.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.model.Task;
import org.platform.snail.portal.service.TaskService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/task")
public class TaskAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TaskService taskService;
	@RequestMapping(value = "/findTaskList.do")
	@ResponseBody
	public  DataResponse findTaskList(Task condition,Page page){
		try{
			DataResponse rst = this.taskService.findTaskList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertTask.do")
	@ResponseBody
	public  DataResponse insertTask(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.taskService.insertTask(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateTask.do")
	@ResponseBody
	public  DataResponse updateTask(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.taskService.updateTask(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectTaskByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectTaskByPrimaryKey(String id){
		try{
			return this.taskService.selectTaskByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteTaskByTaskId.do")
	@ResponseBody
	public  DataResponse deleteTaskByTaskId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.taskService.deleteTaskByTaskId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/findListByUserId.do")
	@ResponseBody
	public  DataResponse findListByUserId(){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.taskService.findListByUserId(systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
}
