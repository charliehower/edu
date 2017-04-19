package org.platform.snail.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.model.Role;
import org.platform.snail.model.Users;
import org.platform.snail.portal.vo.UsersVo;

public interface UsersDao {
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
	
	public abstract List<Map<String,String>> findUsersSearchList (@Param("condition")Users condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
	
	public abstract int findUsersSearchCount (@Param("condition")Users condition);
	
    public abstract int insertUsers(@Param("users")Users users);
	
	public abstract int updateUsersByPrimaryKey(@Param("users")Users users);
	
	public abstract int updateUsersStautsByPrimaryKey(@Param("userId")String usersId,@Param("struts")String struts);
	
	public abstract List<UsersVo> findUsersList (@Param("condition")Users condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
	
	public abstract int findUsersCount (@Param("condition")Users condition);
	
	public abstract UsersVo selectUsersVoByPrimaryKey(@Param("userId")String userId);
	
	public abstract int updateUsersForInitPassword(@Param("userId")String userId,@Param("password")String password);
	
	public abstract int isExitUsersAccount(@Param("account")String account);
	
	public abstract int insertUsersRole(@Param("userId")String userId,@Param("roleId")String[] roleId);
	
	public abstract List<Role> selectRoleList();
	public abstract List<Role> selectRoleListByUserId(@Param("userId")String userId);
	
	public abstract int isExitUsersByEmail(@Param("email")String email);
	
	List<Map<String,String>> selectGroupDepTreeByPid(@Param("pid") String pid);
	
	public String selectDepLeaderByUserId(String id);
	
}
