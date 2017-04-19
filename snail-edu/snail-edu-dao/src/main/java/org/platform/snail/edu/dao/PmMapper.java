package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Pm;
import org.platform.snail.edu.vo.PmQVo;
import org.platform.snail.edu.vo.PmVo;

public interface PmMapper {
    int deleteByPrimaryKey(String PmId);

    int insert(Pm record);

    int insertSelective(Pm record);

    Pm selectByPrimaryKey(String PmId);

    int updateByPrimaryKeySelective(Pm record);

    int updateByPrimaryKey(Pm record);
    
    List<PmVo> findList(@Param("condition") PmQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PmQVo condition);

	int isExit(Pm record);
	
	List<Map<String,String>> selectSubject();
	List<Map<String,String>> selectStudent();
	List<Map<String,String>> selectYears();
}