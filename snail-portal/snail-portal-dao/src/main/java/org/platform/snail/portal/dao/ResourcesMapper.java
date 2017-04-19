package org.platform.snail.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.Resources;
import org.platform.snail.portal.vo.ResourcesVo;

public interface ResourcesMapper {
	int deleteByPrimaryKey(String resourcesId);

	int insert(Resources record);

	int insertSelective(Resources record);

	Resources selectByPrimaryKey(String resourcesId);

	int updateByPrimaryKeySelective(Resources record);

	int updateByPrimaryKey(Resources record);

	List<Map<String, String>> selectResourcesTreeList();

	List<ResourcesVo> findList(
			@Param("condition") Resources condition, @Param("start") int start,
			@Param("limit") int limit, @Param("orderBy") String orderBy);
	int findCount(@Param("condition") Resources condition);
	
	int isExitByName(@Param("name") String name);
	int isExit(Resources record);

}