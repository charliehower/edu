package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Grade;
import org.platform.snail.edu.vo.GradeQVo;
import org.platform.snail.edu.vo.GradeVo;

public interface GradeMapper {
    int deleteByPrimaryKey(String gradeId);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(String gradeId);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
    
    List<GradeVo> findList(@Param("condition") GradeQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") GradeQVo condition);

	int isExitByName(@Param("name") String name);
	int updateUpgrade(String year);
	int updateunDoUpgrade(String year);
	int isExitById(String id);
}