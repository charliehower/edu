package org.platform.snail.portal.web.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.model.Role;
import org.platform.snail.model.Users;
import org.platform.snail.portal.service.RoleService;
import org.platform.snail.portal.service.UsersService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/users")
public class UsersAction extends BaseController{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private UsersService usersService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/findUsersSearchList.do")
	@ResponseBody
	public DataResponse findUsersSearchList(Users condition,Page page){
		try{
			DataResponse rst = this.usersService.findUsersSearchList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(this.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
		
	}
	@RequestMapping(value = "/findUsersList.do")
	@ResponseBody
	public DataResponse findUsersList(Users condition,Page page){
		try{
			DataResponse rst = this.usersService.findUsersList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
		
	}
	@RequestMapping(value = "/insertUsers.do")
	@ResponseBody
	public DataResponse insertUsers(String jsons){
		
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.usersService.insertUsers(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
		
		
	}
	@RequestMapping(value = "/updateUsers.do")
	@ResponseBody
	public DataResponse updateUsers(String jsons){
		
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.usersService.updateUsers(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/updateUsersStautsByPrimaryKey.do")
	@ResponseBody
	public DataResponse updateUsersStautsByPrimaryKey(String jsons){
		
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String userId=json.getString("id");
			String stauts="0";
			if(json.containsKey("stauts")){
				stauts=json.getString("stauts");
			}
			return this.usersService.updateUsersStautsByPrimaryKey(userId,stauts,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/updateUsersForInitPassword.do")
	@ResponseBody
	public DataResponse updateUsersForInitPassword(String userId,String password){
		
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.usersService.updateUsersForInitPassword(userId,password,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/selectUsersByPrimaryKey.do")
	@ResponseBody
	public DataResponse selectUsersByPrimaryKey(String userId){
		try{
			return this.usersService.selectUsersByPrimaryKey(userId);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertUsersRole.do")
	@ResponseBody
	public DataResponse insertUsersRole(String userId,String roleId){
		
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			String []roleIds=null;
			String temp=roleId;
			if(temp!=null&&temp.indexOf(",")!=-1){
				roleIds=temp.split(",");
			}
			if(temp!=null&&temp.indexOf(",")==-1){
				roleIds=new String[]{temp};
			}
			return this.usersService.insertUsersRole(userId, roleIds, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/selectRoleList.do")
	@ResponseBody
	public DataResponse selectRoleList(){
		try{
			return this.usersService.selectRoleList();
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/selectRoleListByUserId.do")
	@ResponseBody
	public DataResponse selectRoleListByUserId(String userId){
		try{
			return this.usersService.selectRoleListByUserId(userId);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/findRoleList.do")
	@ResponseBody
	public DataResponse findRoleList(Role condition,Page page){
		try{
			DataResponse rst = this.roleService.findRoleList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
		
	}
	@RequestMapping(value = "/selectGroupDepTreeByPid.do")
	@ResponseBody
	public List<Tree> selectGroupDepTreeByPid(String pid)throws Exception {
		SystemUser systemUser =this.getSessionSystemUser();
		return this.usersService.selectGroupDepTreeByPid(pid,systemUser);
	}
	@RequestMapping(value = "/selectGroupDepAceTreeByPid.do")
	@ResponseBody
	public Map<String,Object> selectGroupDepAceTreeByPid(String pid)throws Exception {
		SystemUser systemUser =this.getSessionSystemUser();
		return this.usersService.selectGroupDepAceTreeByPid(pid,systemUser);
	}
}
