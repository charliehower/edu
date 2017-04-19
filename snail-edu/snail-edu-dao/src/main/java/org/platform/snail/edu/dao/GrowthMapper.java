package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Growth;
import org.platform.snail.edu.vo.GrowthQVo;
import org.platform.snail.edu.vo.GrowthVo;
public interface GrowthMapper {
    int deleteByPrimaryKey(String GrowthId);

    int insert(Growth record);

    int insertSelective(Growth record);

    GrowthVo selectByPrimaryKey(String GrowthId);

    int updateByPrimaryKeySelective(Growth record);

    int updateByPrimaryKey(Growth record);
    
    List<GrowthVo> findList(@Param("condition") GrowthQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") GrowthQVo condition);

	int isExit(Growth record);
}