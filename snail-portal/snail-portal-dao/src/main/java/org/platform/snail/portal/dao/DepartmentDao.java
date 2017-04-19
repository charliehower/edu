package org.platform.snail.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.model.Department;
import org.platform.snail.portal.vo.DepartmentVo;

public interface DepartmentDao {
	/**
	 *@description: init
	 *@param:  
	 *@return:
	 *@author: 陈晓克
	 *@version:3.5.1
	 *@exception:
	 *@createDate: 2012-12-14
	 */
	public abstract int init();
	
	public abstract int isExitByUserId(String userId);
	
	public abstract int insertDepartment(@Param("department")Department department);
	
	public abstract int updateDepartmentByPrimaryKey(@Param("department")Department department);
	
	public abstract int updateDepartmentStautsByPrimaryKey(@Param("departmentId")String departmentId,@Param("struts")String struts);
	
	public abstract List<DepartmentVo> findDepartmentList (@Param("condition")Department condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
	
	public abstract int findDepartmentCount (@Param("condition")Department condition);
	
	public abstract DepartmentVo selectDepartmentVoByPrimaryKey(@Param("departmentId")String departmentId);
	
	public abstract List<Map<String,String>> selectDepartmentTreeList();

	public abstract int delDepartmentByPrimaryKey(@Param("departmentId")String departmentId);
	
	public abstract List<Map<String,String>> selectUsersListByDepartmentId(@Param("departmentId")String departmentId);
	
	public abstract int batchUpdateUserAndTeacherByUserIds(@Param("list")List<String> list,@Param("departmentId")String departmentId);
	
}
