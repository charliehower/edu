package org.platform.snail.portal.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.portal.service.LocationService;
import org.platform.snail.portal.vo.LocationQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/location")
public class LocationAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private LocationService locationService;
	@RequestMapping(value = "/findLocationList.do")
	@ResponseBody
	public  DataResponse findLocationList(LocationQVo condition,Page page){
		try{
			DataResponse rst = this.locationService.findLocationList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertLocation.do")
	@ResponseBody
	public  DataResponse insertLocation(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.locationService.insertLocation(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateLocation.do")
	@ResponseBody
	public  DataResponse updateLocation(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.locationService.updateLocation(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectLocationByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectLocationByPrimaryKey(String id){
		try{
			return this.locationService.selectLocationByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteLocationByLocationId.do")
	@ResponseBody
	public  DataResponse deleteLocationByLocationId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.locationService.deleteLocationByLocationId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectLocationTreeList.do")
	@ResponseBody
	public  List<Tree> selectLocationTreeList(String pid,String load){
		try{
			return this.locationService.selectLocationTreeList(pid,load);
		}catch(Exception e){
			this.logger.error(e);
			return null;
		}
	}
	
}
