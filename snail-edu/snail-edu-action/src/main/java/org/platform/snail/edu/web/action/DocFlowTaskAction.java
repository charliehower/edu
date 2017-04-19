package org.platform.snail.edu.web.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.DocFlowTaskService;
import org.platform.snail.edu.vo.DocFlowTaskQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/docFlowTask")
public class DocFlowTaskAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DocFlowTaskService docFlowTaskService;
	@RequestMapping(value = "/findDocFlowTaskList.do")
	@ResponseBody
	public  DataResponse findDocFlowTaskList(DocFlowTaskQVo condition,Page page){
		try{
			DataResponse rst = this.docFlowTaskService.findDocFlowTaskList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertDocFlowTask.do")
	@ResponseBody
	public  DataResponse insertDocFlowTask(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.docFlowTaskService.insertDocFlowTask(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateDocFlowTask.do")
	@ResponseBody
	public  DataResponse updateDocFlowTask(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.docFlowTaskService.updateDocFlowTask(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectDocFlowTaskByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectDocFlowTaskByPrimaryKey(String id){
		try{
			return this.docFlowTaskService.selectDocFlowTaskByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteDocFlowTaskByDocFlowTaskId.do")
	@ResponseBody
	public  DataResponse deleteDocFlowTaskByDocFlowTaskId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.docFlowTaskService.deleteDocFlowTaskByDocFlowTaskId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateDocFlowTask.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.docFlowTaskService.saveOrUpdateDocFlowTask(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/importXls.do")
	@ResponseBody
	public  DataResponse importXls(@RequestParam MultipartFile[] file,String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.docFlowTaskService.importXls(file, json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"导入失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectTaskListByDocFlowId.do")
	@ResponseBody
	public  DataResponse selectTaskListByDocFlowId(String docFlowId){
		try{
			return this.docFlowTaskService.selectTaskListByDocFlowId(docFlowId);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectTaskTreeListByPid.do")
	@ResponseBody
	public  Map<String,Object>  selectTaskTreeListByPid(String docFlowId,String pid){
		Map<String,Object> rs=new HashMap<String,Object>();
		rs.put("status", "NO");
		try{
			return this.docFlowTaskService.selectTaskTreeListByPid(docFlowId,pid);
		}catch(Exception e){
			this.logger.error(e);
			rs.put("data", e.getMessage());
			return rs;
		}
	}
}
