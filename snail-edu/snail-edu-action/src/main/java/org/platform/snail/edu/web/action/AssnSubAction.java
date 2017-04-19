package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.AssnSub;
import org.platform.snail.edu.vo.AssnSubQVo;
import org.platform.snail.edu.service.AssnSubService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/assnSub")
public class AssnSubAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AssnSubService assnSubService;
	@RequestMapping(value = "/findAssnSubList.do")
	@ResponseBody
	public  DataResponse findAssnSubList(AssnSubQVo condition,Page page){
		try{
			DataResponse rst = this.assnSubService.findAssnSubList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertAssnSub.do")
	@ResponseBody
	public  DataResponse insertAssnSub(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.assnSubService.insertAssnSub(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateAssnSub.do")
	@ResponseBody
	public  DataResponse updateAssnSub(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.assnSubService.updateAssnSub(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectAssnSubByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectAssnSubByPrimaryKey(String id){
		try{
			return this.assnSubService.selectAssnSubByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteAssnSubByAssnSubId.do")
	@ResponseBody
	public  DataResponse deleteAssnSubByAssnSubId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.assnSubService.deleteAssnSubByAssnSubId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateAssnSub.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.assnSubService.saveOrUpdateAssnSub(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateAuditByPrimaryKey.do")
	@ResponseBody
	public  DataResponse updateAuditByPrimaryKey(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.assnSubService.updateAuditByPrimaryKey(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"审核失败！",e.getMessage());
		}
	}
}
