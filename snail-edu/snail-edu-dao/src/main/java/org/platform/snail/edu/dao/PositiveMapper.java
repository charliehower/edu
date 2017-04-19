package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Positive;
import org.platform.snail.edu.vo.PositiveQVo;
import org.platform.snail.edu.vo.PositiveVo;

public interface PositiveMapper {
    int deleteByPrimaryKey(String teacherId);

    int insert(Positive record);

    int insertSelective(Positive record);

    Positive selectByPrimaryKey(String teacherId);

    int updateByPrimaryKeySelective(Positive record);

    int updateByPrimaryKeyWithBLOBs(Positive record);

    int updateByPrimaryKey(Positive record);
    
    List<PositiveVo> findList(@Param("condition") PositiveQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PositiveQVo condition);
	
	int isExitByTeacherId(String teacherId);
}