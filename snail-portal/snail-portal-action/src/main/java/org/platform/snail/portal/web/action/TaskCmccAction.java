package org.platform.snail.portal.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.service.TaskCmccService;
import org.platform.snail.portal.vo.TaskCmccQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/taskCmcc")
public class TaskCmccAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TaskCmccService taskCmccService;
	@RequestMapping(value = "/findTaskCmccList.do")
	@ResponseBody
	public  DataResponse findTaskCmccList(TaskCmccQVo condition,Page page){
		try{
			DataResponse rst = this.taskCmccService.findTaskCmccList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertTaskCmcc.do")
	@ResponseBody
	public  DataResponse insertTaskCmcc(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.taskCmccService.insertTaskCmcc(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateTaskCmcc.do")
	@ResponseBody
	public  DataResponse updateTaskCmcc(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.taskCmccService.updateTaskCmcc(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectTaskCmccByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectTaskCmccByPrimaryKey(String id){
		try{
			return this.taskCmccService.selectTaskCmccByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteTaskCmccByTaskCmccId.do")
	@ResponseBody
	public  DataResponse deleteTaskCmccByTaskCmccId(String jsons){
		try{
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			SystemUser systemUser =this.getSessionSystemUser();
			return this.taskCmccService.deleteTaskCmccByTaskCmccId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateTaskStatusCmccByTaskCmccId.do")
	@ResponseBody
	public  DataResponse updateTaskStatusCmccByTaskCmccId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.taskCmccService.updateTaskStatusCmccByTaskCmccId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"操作失败！",e.getMessage());
		}
	}
}
