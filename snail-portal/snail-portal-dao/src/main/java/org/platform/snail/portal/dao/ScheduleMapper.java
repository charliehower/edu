package org.platform.snail.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.Schedule;
import org.platform.snail.portal.vo.ScheduleQVo;
import org.platform.snail.portal.vo.ScheduleVo;

public interface ScheduleMapper {
	
	int deleteByPrimaryKey(String id);

	int insert(Schedule record);

	int insertSelective(Schedule record);

	Schedule selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Schedule record);

	int updateByPrimaryKey(Schedule record);

	List<ScheduleVo> findList(@Param("condition") ScheduleQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ScheduleQVo condition);
	
	List<Map<String,Object>> selectDepUserListByDepId(String id);
}