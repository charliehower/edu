package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Quit;
import org.platform.snail.edu.vo.QuitQVo;
import org.platform.snail.edu.vo.QuitVo;

public interface QuitMapper {
    int deleteByPrimaryKey(String teacherId);

    int insert(Quit record);

    int insertSelective(Quit record);

    Quit selectByPrimaryKey(String teacherId);

    int updateByPrimaryKeySelective(Quit record);

    int updateByPrimaryKey(Quit record);
    
    List<QuitVo> findList(@Param("condition") QuitQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") QuitQVo condition);
	
	int isExitByTeacherId(String teacherId);
}