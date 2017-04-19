package org.platform.snail.portal.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.service.ConfigService;
import org.platform.snail.portal.vo.ConfigQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/config")
public class ConfigAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ConfigService configService;
	@RequestMapping(value = "/findConfigList.do")
	@ResponseBody
	public  DataResponse findConfigList(ConfigQVo condition,Page page){
		try{
			DataResponse rst = this.configService.findConfigList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertConfig.do")
	@ResponseBody
	public  DataResponse insertConfig(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.configService.insertConfig(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateConfig.do")
	@ResponseBody
	public  DataResponse updateConfig(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.configService.updateConfig(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectConfigByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectConfigByPrimaryKey(String id){
		try{
			return this.configService.selectConfigByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteConfigByConfigId.do")
	@ResponseBody
	public  DataResponse deleteConfigByConfigId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.configService.deleteConfigByConfigId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
}
