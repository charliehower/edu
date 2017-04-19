package org.platform.snail.workflow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.workflow.model.GroupImpl;
import org.platform.snail.workflow.model.UserImpl;


/** 
 * @author 陈晓克
 * @version 2013-12-20 上午11:04:37
 */
public interface WorkflowDao {
	
	public GroupImpl findGroupById(@Param("groupId")String groupId) ;

	public List<GroupImpl> findGroupsByUser(@Param("userId")String userId);

	public List<GroupImpl> findGroupsByUserAndGroupType(@Param("userId")String userId, @Param("groupType")String groupType);

	public UserImpl findUserById(@Param("userId")String userId) ;

	public List<UserImpl> findUsers() ;

	public List<UserImpl> findUsersByGroup(@Param("groupId")String groupId);

	public List<UserImpl> findUsersById(@Param("ids")String... ids);
	
	 List<Map<String,Object>> findList(@Param("condition") Map<String,Object> condition,
				@Param("start") int start, @Param("limit") int limit,
				@Param("orderBy") String orderBy);

		int findCount(@Param("condition") Map<String,Object> condition);
}
