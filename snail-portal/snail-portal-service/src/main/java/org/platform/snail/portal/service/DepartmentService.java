package org.platform.snail.portal.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.platform.snail.model.Department;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;

public interface DepartmentService {
	
	public abstract DataResponse findDepartmentList(Department condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertDepartment(JSONObject josnObject,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateDepartment(JSONObject josnObject,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateDepartmentStautsByPrimaryKey(String departmentId,String struts,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectDepartmentByPrimaryKey(String departmentId) throws Exception;
	public abstract List<Tree>  selectDepartmentTreeList() throws Exception;
	
	public abstract DataResponse delDepartmentByPrimaryKey(String departmentId,SystemUser systemUser) throws Exception;
	
	public abstract DataResponse selectUsersListByDepartmentId(String departmentId) throws Exception;
	
	public abstract DataResponse batchUpdateUserAndTeacherByUserIds(JSONObject json,SystemUser systemUser) throws Exception;
}
