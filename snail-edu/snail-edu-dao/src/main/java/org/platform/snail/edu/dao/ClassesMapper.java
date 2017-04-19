package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Classes;
import org.platform.snail.edu.vo.ClassesQVo;
import org.platform.snail.edu.vo.ClassesVo;

public interface ClassesMapper {
    int deleteByPrimaryKey(String classesId);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(String classesId);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);
    List<ClassesVo> findList(@Param("condition") ClassesQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ClassesQVo condition);

	int isExitByName(@Param("name") String name);
}