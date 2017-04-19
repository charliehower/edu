package org.platform.snail.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.Location;
import org.platform.snail.portal.vo.LocationQVo;
import org.platform.snail.portal.vo.LocationVo;

public interface LocationMapper {
	int deleteByPrimaryKey(String locationId);

	int insert(Location record);

	int insertSelective(Location record);

	Location selectByPrimaryKey(String locationId);

	int updateByPrimaryKeySelective(Location record);

	int updateByPrimaryKey(Location record);

	public abstract List<Map<String, String>> selectLocationTreeList(@Param("pid")String pid,@Param("load")String load);

	public abstract List<LocationVo> findList(
			@Param("condition") LocationQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	public abstract int findCount(
			@Param("condition") LocationQVo condition);
}