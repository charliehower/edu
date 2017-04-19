package org.platform.snail.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.Config;
import org.platform.snail.portal.vo.ConfigVo;

public interface ConfigMapper {
	int deleteByPrimaryKey(String configKey);

	int insert(Config record);

	int insertSelective(Config record);

	Config selectByPrimaryKey(String configKey);

	int updateByPrimaryKeySelective(Config record);

	int updateByPrimaryKey(Config record);

	List<ConfigVo> findList(@Param("condition") Config condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") Config condition);


	int isExitByName(@Param("name") String name);
}