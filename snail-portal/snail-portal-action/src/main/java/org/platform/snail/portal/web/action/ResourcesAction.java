package org.platform.snail.portal.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.portal.model.Resources;
import org.platform.snail.portal.service.ResourcesService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/resources")
public class ResourcesAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ResourcesService resourcesService;
	@RequestMapping(value = "/findResourcesList.do")
	@ResponseBody
	public  DataResponse findResourcesList(Resources condition,Page page){
		try{
			DataResponse rst = this.resourcesService.findResourcesList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertResources.do")
	@ResponseBody
	public  DataResponse insertResources(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.resourcesService.insertResources(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateResources.do")
	@ResponseBody
	public  DataResponse updateResources(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.resourcesService.updateResources(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectResourcesByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectResourcesByPrimaryKey(String id){
		try{
			return this.resourcesService.selectResourcesByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteResourcesByResourcesId.do")
	@ResponseBody
	public  DataResponse deleteResourcesByResourcesId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.resourcesService.deleteResourcesByResourcesId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectResourcesTreeList.do")
	@ResponseBody
	public  List<Tree>  selectResourcesTreeList() {
		try{
			return this.resourcesService.selectResourcesTreeList();
		}catch(Exception e){
			this.logger.error(e);
			return null;
		}
	}
	@RequestMapping(value = "/importXls.do")
	@ResponseBody
	public  DataResponse importXls(@RequestParam MultipartFile[] file,String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.resourcesService.importXls(file, json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"导入失败！",e.getMessage());
		}
	}
}
