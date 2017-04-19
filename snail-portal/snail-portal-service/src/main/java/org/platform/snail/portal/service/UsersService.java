package org.platform.snail.portal.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.model.Users;

public interface UsersService {
	public abstract DataResponse findUsersSearchList(Users condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse findUsersList(Users condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertUsers(JSONObject josnObject,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateUsers(JSONObject josnObject,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateUsersStautsByPrimaryKey(String usersId,String struts,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectUsersByPrimaryKey(String usersId) throws Exception;
	public abstract DataResponse updateUsersForInitPassword(String usersId,String password,SystemUser systemUser) throws Exception;
	public abstract DataResponse insertUsersRole(String userId,String[] roleId,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectRoleList() throws Exception;
	public abstract DataResponse selectRoleListByUserId(String userId) throws Exception;
	public  List<Tree>  selectGroupDepTreeByPid(String pid,SystemUser systemUser);
	public  Map<String,Object>  selectGroupDepAceTreeByPid(String pid,SystemUser systemUser);
}
