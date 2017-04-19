package org.platform.snail.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.ExcelMain;
import org.platform.snail.portal.vo.ExcelMainQVo;
import org.platform.snail.portal.vo.ExcelMainVo;

public interface ExcelMainMapper {
    int deleteByPrimaryKey(String ExcelMainId);

    int insert(ExcelMain record);

    int insertSelective(ExcelMain record);

    ExcelMain selectByPrimaryKey(String ExcelMainId);

    int updateByPrimaryKeySelective(ExcelMain record);

    int updateByPrimaryKey(ExcelMain record);
    
    List<ExcelMainVo> findList(@Param("condition") ExcelMainQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ExcelMainQVo condition);

	int isExit(ExcelMain record);
}