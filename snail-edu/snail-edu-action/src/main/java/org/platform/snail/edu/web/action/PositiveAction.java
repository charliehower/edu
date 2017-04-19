package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.PositiveService;
import org.platform.snail.edu.vo.PositiveQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/positive")
public class PositiveAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PositiveService positiveService;
	@RequestMapping(value = "/findPositiveList.do")
	@ResponseBody
	public  DataResponse findPositiveList(PositiveQVo condition,Page page){
		try{
			DataResponse rst = this.positiveService.findPositiveList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertPositive.do")
	@ResponseBody
	public  DataResponse insertPositive(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.positiveService.insertPositive(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updatePositive.do")
	@ResponseBody
	public  DataResponse updatePositive(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.positiveService.updatePositive(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectPositiveByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectPositiveByPrimaryKey(String id){
		try{
			return this.positiveService.selectPositiveByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deletePositiveByPositiveId.do")
	@ResponseBody
	public  DataResponse deletePositiveByPositiveId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.positiveService.deletePositiveByPositiveId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/isExitByTeacherId.do")
	@ResponseBody
	public  DataResponse isExitByTeacherId(){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.positiveService.isExitByTeacherId(systemUser.getUsers().getUserId());
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
}
