package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Group;
import org.platform.snail.edu.vo.GroupQVo;
import org.platform.snail.edu.vo.GroupVo;

public interface GroupMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
    List<GroupVo> findList(@Param("condition") GroupQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") GroupQVo condition);

	int isExitByName(@Param("name") String name);
	List<Map<String,String>> selectGroupDepTreeByPid(@Param("pid") String pid);
	List<Map<String,String>> selectGroupGradeTreeByPid(@Param("pid") String pid);
	List<Map<String,String>> selectGroupDiscriblineTreeByPid(@Param("pid") String pid);
	List<Map<String,String>> selectFreeGroupTreeRoot();
	
	List<Map<String,String>> selectFreeGroupUsersListByGorupId(String groupId);
	List<Map<String,String>> selectFreeGroupUsersTreeByGorupId(String groupId);
	
    int batchSaveGroupUsersByUserIds(@Param("list")List<String> list,@Param("groupId")String groupId);
}