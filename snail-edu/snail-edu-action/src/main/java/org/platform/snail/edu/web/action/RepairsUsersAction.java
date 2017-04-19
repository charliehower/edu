package org.platform.snail.edu.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.CheckTree;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.RepairsUsersService;
import org.platform.snail.edu.vo.RepairsUsersQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/repairsUsers")
public class RepairsUsersAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RepairsUsersService repairsUsersService;
	@RequestMapping(value = "/findRepairsUsersList.do")
	@ResponseBody
	public  DataResponse findRepairsUsersList(RepairsUsersQVo condition,Page page){
		try{
			DataResponse rst = this.repairsUsersService.findRepairsUsersList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertRepairsUsers.do")
	@ResponseBody
	public  DataResponse insertRepairsUsers(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.repairsUsersService.insertRepairsUsers(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateRepairsUsers.do")
	@ResponseBody
	public  DataResponse updateRepairsUsers(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.repairsUsersService.updateRepairsUsers(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectRepairsUsersByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectRepairsUsersByPrimaryKey(String id){
		try{
			return this.repairsUsersService.selectRepairsUsersByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteRepairsUsersByRepairsUsersId.do")
	@ResponseBody
	public  DataResponse deleteRepairsUsersByRepairsUsersId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.repairsUsersService.deleteRepairsUsersByRepairsUsersId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateRepairsUsers.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.repairsUsersService.saveOrUpdateRepairsUsers(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectLocationTreeList.do")
	@ResponseBody
	public  List<CheckTree> selectLocationTreeList(String userId){
		try{
			return this.repairsUsersService.selectLocationTreeList(userId);
		}catch(Exception e){
			this.logger.error(e);
		}
		return null;
	}
	 
}
