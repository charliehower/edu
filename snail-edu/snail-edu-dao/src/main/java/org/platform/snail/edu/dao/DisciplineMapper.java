package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Discipline;
import org.platform.snail.edu.vo.DisciplineQVo;
import org.platform.snail.edu.vo.DisciplineVo;

public interface DisciplineMapper {
    int deleteByPrimaryKey(String disciplineId);

    int insert(Discipline record);

    int insertSelective(Discipline record);

    Discipline selectByPrimaryKey(String disciplineId);

    int updateByPrimaryKeySelective(Discipline record);

    int updateByPrimaryKey(Discipline record);
    List<DisciplineVo> findList(@Param("condition") DisciplineQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DisciplineQVo condition);

	int isExitByName(@Param("name") String name);
}