package org.platform.snail.portal.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.platform.snail.model.Role;
import org.platform.snail.beans.CheckTree;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;

public interface RoleService {
	public abstract DataResponse findRoleList(Role condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertRole(JSONObject josnObject,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateRole(JSONObject josnObject,SystemUser systemUser) throws Exception;
	public abstract DataResponse insertRoleResources(String roleId,String[] resourcesId,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectRoleByPrimaryKey(String roleId) throws Exception;
	public abstract List<CheckTree>  selectRoleResourceTreeList(String roleId) throws Exception;
	public abstract DataResponse deleteRoleByRoleId(String roleId,SystemUser systemUser) throws Exception;
	
}
