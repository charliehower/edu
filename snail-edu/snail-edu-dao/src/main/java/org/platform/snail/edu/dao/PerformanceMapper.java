package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Performance;
import org.platform.snail.edu.vo.PerformanceQVo;
import org.platform.snail.edu.vo.PerformanceVo;

public interface PerformanceMapper {
    int deleteByPrimaryKey(String PerformanceId);

    int insert(Performance record);

    int insertSelective(Performance record);

    Performance selectByPrimaryKey(String PerformanceId);

    int updateByPrimaryKeySelective(Performance record);

    int updateByPrimaryKey(Performance record);
    
    List<PerformanceVo> findList(@Param("condition") PerformanceQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PerformanceQVo condition);

	int isExit(Performance record);
}