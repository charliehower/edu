package org.platform.snail.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.Dict;
import org.platform.snail.portal.vo.DictVo;

public interface DictMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Dict record);

	int insertSelective(Dict record);

	Dict selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Dict record);

	int updateByPrimaryKey(Dict record);
	List<DictVo> findList (@Param("condition")Dict condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
	
	int findCount (@Param("condition")Dict condition);
	
	List<Dict> findListByCategoryId(@Param("categoryId")String categoryId);
	
	int isExitByNameAndCategoryId(@Param("name")String name,@Param("categoryId")String categoryId);
}