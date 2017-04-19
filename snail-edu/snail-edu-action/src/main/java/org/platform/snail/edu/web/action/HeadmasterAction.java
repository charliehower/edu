package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.Headmaster;
import org.platform.snail.edu.service.HeadmasterService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/headmaster")
public class HeadmasterAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private HeadmasterService headmasterService;
	@RequestMapping(value = "/findHeadmasterList.do")
	@ResponseBody
	public  DataResponse findHeadmasterList(Headmaster condition,Page page){
		try{
			DataResponse rst = this.headmasterService.findHeadmasterList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertHeadmaster.do")
	@ResponseBody
	public  DataResponse insertHeadmaster(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.headmasterService.insertHeadmaster(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateHeadmaster.do")
	@ResponseBody
	public  DataResponse updateHeadmaster(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.headmasterService.updateHeadmaster(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectHeadmasterByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectHeadmasterByPrimaryKey(String id){
		try{
			return this.headmasterService.selectHeadmasterByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteHeadmasterByHeadmasterId.do")
	@ResponseBody
	public  DataResponse deleteHeadmasterByHeadmasterId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.headmasterService.deleteHeadmasterByHeadmasterId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateHeadmaster.do")
	@ResponseBody
	public  DataResponse saveOrUpdateHeadmaster(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.headmasterService.saveOrUpdateHeadmaster(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
}
