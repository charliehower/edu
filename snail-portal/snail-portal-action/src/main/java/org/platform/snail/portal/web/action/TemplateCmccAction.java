package org.platform.snail.portal.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.model.TemplateCmcc;
import org.platform.snail.portal.service.TemplateCmccService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.platform.snail.portal.vo.TemplateCmccQVo;
import org.platform.snail.portal.vo.TemplateCmccVo;
@Controller
@RequestMapping("/templateCmcc")
public class TemplateCmccAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TemplateCmccService templateCmccService;
	@RequestMapping(value = "/findTemplateCmccList.do")
	@ResponseBody
	public  DataResponse findTemplateCmccList(TemplateCmccQVo condition,Page page){
		try{
			DataResponse rst = this.templateCmccService.findTemplateCmccList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertTemplateCmcc.do")
	@ResponseBody
	public  DataResponse insertTemplateCmcc(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.templateCmccService.insertTemplateCmcc(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateTemplateCmcc.do")
	@ResponseBody
	public  DataResponse updateTemplateCmcc(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.templateCmccService.updateTemplateCmcc(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectTemplateCmccByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectTemplateCmccByPrimaryKey(String id){
		try{
			return this.templateCmccService.selectTemplateCmccByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteTemplateCmccByTemplateCmccId.do")
	@ResponseBody
	public  DataResponse deleteTemplateCmccByTemplateCmccId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.templateCmccService.deleteTemplateCmccByTemplateCmccId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
}
