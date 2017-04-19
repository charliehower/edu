package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.AssnSub;
import org.platform.snail.edu.vo.AssnSubQVo;
import org.platform.snail.edu.vo.AssnSubVo;

public interface AssnSubMapper {
    int deleteByPrimaryKey(String assnSubId);

    int insert(AssnSub record);

    int insertSelective(AssnSub record);

    AssnSub selectByPrimaryKey(String assnSubId);

    int updateByPrimaryKeySelective(AssnSub record);

    int updateByPrimaryKeyWithBLOBs(AssnSub record);

    int updateByPrimaryKey(AssnSub record);
    
    int updateAuditByPrimaryKey(AssnSub record);
    
   
    
    List<AssnSubVo> findList(@Param("condition") AssnSubQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") AssnSubQVo condition);
}