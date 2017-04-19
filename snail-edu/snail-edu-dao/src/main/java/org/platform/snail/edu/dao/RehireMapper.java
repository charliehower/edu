package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Rehire;
import org.platform.snail.edu.vo.RehireQVo;
import org.platform.snail.edu.vo.RehireVo;

public interface RehireMapper {
    int deleteByPrimaryKey(String teacherId);

    int insert(Rehire record);

    int insertSelective(Rehire record);

    Rehire selectByPrimaryKey(String teacherId);

    int updateByPrimaryKeySelective(Rehire record);

    int updateByPrimaryKey(Rehire record);
    List<RehireVo> findList(@Param("condition") RehireQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") RehireQVo condition);
	
	int isExitByTeacherId(String teacherId);
	
	
}