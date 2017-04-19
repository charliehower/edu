package org.platform.snail.edu.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.edu.service.GroupService;
import org.platform.snail.edu.vo.GroupQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/group")
public class GroupAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GroupService groupService;
	@RequestMapping(value = "/findGroupList.do")
	@ResponseBody
	public  DataResponse findGroupList(GroupQVo condition,Page page){
		try{
			DataResponse rst = this.groupService.findGroupList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertGroup.do")
	@ResponseBody
	public  DataResponse insertGroup(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.groupService.insertGroup(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateGroup.do")
	@ResponseBody
	public  DataResponse updateGroup(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.groupService.updateGroup(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectGroupByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectGroupByPrimaryKey(String id){
		try{
			return this.groupService.selectGroupByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteGroupByGroupId.do")
	@ResponseBody
	public  DataResponse deleteGroupByGroupId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.groupService.deleteGroupByGroupId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/selectGroupDepTreeByPid.do")
	@ResponseBody
	public List<Tree> selectGroupDepTreeByPid(String pid)throws Exception {
		return this.groupService.selectGroupDepTreeByPid(pid);
	}
	@RequestMapping(value = "/selectGroupGradeTreeByPid.do")
	@ResponseBody
	public List<Tree> selectGroupGradeTreeByPid(String pid)throws Exception {
		return this.groupService.selectGroupGradeTreeByPid(pid);
	}
	@RequestMapping(value = "/selectGroupDiscriblineTreeByPid.do")
	@ResponseBody
	public List<Tree> selectGroupDiscriblineTreeByPid(String pid)throws Exception {
		return this.groupService.selectGroupDiscriblineTreeByPid(pid);
	}
	
	@RequestMapping(value = "/selectFreeGroupTreeRoot.do")
	@ResponseBody
	public List<Tree> selectFreeGroupTreeRoot()throws Exception {
		return this.groupService.selectFreeGroupTreeRoot();
	}
	@RequestMapping(value = "/selectFreeGroupTreeByPid.do")
	@ResponseBody
	public List<Tree> selectFreeGroupTreeByPid(String pid)throws Exception {
		return this.groupService.selectFreeGroupTreeByPid(pid);
	}
	@RequestMapping(value = "/selectFreeGroupUsersListByGorupId.do")
	@ResponseBody
	public DataResponse selectFreeGroupUsersListByGorupId(String groupId)throws Exception {
		return this.groupService.selectFreeGroupUsersListByGorupId(groupId);
	}
	@RequestMapping(value = "/batchSaveGroupUsersByUserIds.do")
	@ResponseBody
	public DataResponse batchSaveGroupUsersByUserIds(String jsons)throws Exception {
		JSONObject json=JSONObject.fromObject(jsons);
		SystemUser systemUser =this.getSessionSystemUser();
		return this.groupService.batchSaveGroupUsersByUserIds(json,systemUser);
	}
}
