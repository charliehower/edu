package org.platform.snail.edu.web.action;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.SiteAppService;
import org.platform.snail.edu.vo.SiteAppQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/siteApp")
public class SiteAppAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SiteAppService siteAppService;
	@RequestMapping(value = "/findSiteAppList.do")
	@ResponseBody
	public  DataResponse findSiteAppList(SiteAppQVo condition){
		try{
			DataResponse rst = this.siteAppService.findSiteAppList(condition,0,999999, null);
			if (rst.getAllRows() == null) {
				rst.setAllRows(999999);
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertSiteApp.do")
	@ResponseBody
	public  DataResponse insertSiteApp(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.siteAppService.insertSiteApp(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateSiteApp.do")
	@ResponseBody
	public  DataResponse updateSiteApp(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.siteAppService.updateSiteApp(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectSiteAppByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectSiteAppByPrimaryKey(String id){
		try{
			return this.siteAppService.selectSiteAppByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteSiteAppBySiteAppId.do")
	@ResponseBody
	public  DataResponse deleteSiteAppBySiteAppId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			
			return this.siteAppService.deleteSiteAppBySiteAppId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateSiteApp.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.siteAppService.saveOrUpdateSiteApp(json, systemUser);
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
			return this.siteAppService.importXls(file, json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"导入失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectSiteTreeByPid.do")
	@ResponseBody
	public Map<String,Object> selectSiteTreeByPid(String pid)throws Exception {
		SystemUser systemUser =this.getSessionSystemUser();
		return this.siteAppService.selectSiteTreeByPid(pid,systemUser);
	}
}
