package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.ClassCheduleService;
import org.platform.snail.edu.vo.ClassCheduleQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/classChedule")
public class ClassCheduleAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ClassCheduleService classCheduleService;
	@RequestMapping(value = "/findClassCheduleList.do")
	@ResponseBody
	public  DataResponse findClassCheduleList(ClassCheduleQVo condition,Page page){
		try{
			DataResponse rst = this.classCheduleService.findClassCheduleList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertClassChedule.do")
	@ResponseBody
	public  DataResponse insertClassChedule(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.classCheduleService.insertClassChedule(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateClassChedule.do")
	@ResponseBody
	public  DataResponse updateClassChedule(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.classCheduleService.updateClassChedule(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectClassCheduleByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectClassCheduleByPrimaryKey(String id){
		try{
			return this.classCheduleService.selectClassCheduleByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteClassCheduleByClassCheduleId.do")
	@ResponseBody
	public  DataResponse deleteClassCheduleByClassCheduleId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.classCheduleService.deleteClassCheduleByClassCheduleId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateClassChedule.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.classCheduleService.saveOrUpdateClassChedule(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
}
