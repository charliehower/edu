package org.platform.snail.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.model.Resources;

public interface SystemService {
	public abstract SystemUser getSystemUser(String userId,Map<String,String> cfg);
	
	public abstract List<Tree> getTreeList(List<Resources> resources,String id,boolean loadButton);
	public abstract Map<String,String> getButtonAuthor(List<Resources> resources,String id);
	
	public abstract List<Tree>  selectProvinceTreeList();
	
	public abstract List<Tree>  selectProvinceTreeList(String pid,boolean isRoot);
	
	public abstract DataResponse updatePassword(String password,String repassword,SystemUser systemUser) throws Exception;
	
	public abstract SystemUser getSystemUserByAccount(String account,Map<String,String> cfg);
	
	public abstract Map<String,Object> selectDepartment(Map<String,String> params);
	
	public abstract Map<String,Object> selectUsers(Map<String,Object> params);
	public abstract Map<String,Object> selectStudent(Map<String,Object> params);
	
	public abstract DataResponse retrievePassword(String email) throws Exception;
	
	public abstract Map<String,Object> getDiseaseListByPcodeOrName(Map<String,String> params);
	
	public abstract List<Tree>  selectDepartmentTreeList(String pid);
	
	public abstract Map<String,Object> selectTeacher(Map<String,Object> params);
	
	public void loadConfig(ServletContext servletContext);

	

}
