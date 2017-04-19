package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.RepairsUsers;
import org.platform.snail.edu.vo.RepairsUsersQVo;
import org.platform.snail.edu.vo.RepairsUsersVo;

public interface RepairsUsersMapper {
    int deleteByPrimaryKey(String repairsUsersId);

    int insert(RepairsUsers record);

    int insertSelective(RepairsUsers record);

    RepairsUsers selectByPrimaryKey(String repairsUsersId);

    int updateByPrimaryKeySelective(RepairsUsers record);

    int updateByPrimaryKey(RepairsUsers record);
    List<RepairsUsersVo> findList(@Param("condition") RepairsUsersQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") RepairsUsersQVo condition);
	
	int isExitByUserId(String id);
	
	int batchInsertOrUpdate(@Param("list")List<Map<String,Object>> list,@Param("userId")String userId);
	
	List<Map<String,String>> selectLocationTreeList(String userId);
	String selectOnlyUserIdByLocationId(String locationId);
	List<Map<String,String>> selectUserListByCategoryId(String categoryId);
	
	int deleteUsersLocation(@Param("userId")String userId);
	
	
}