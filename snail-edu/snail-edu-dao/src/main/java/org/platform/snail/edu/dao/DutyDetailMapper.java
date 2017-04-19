package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.DutyDetail;
import org.platform.snail.edu.vo.DutyDetailQVo;
import org.platform.snail.edu.vo.DutyDetailVo;

public interface DutyDetailMapper {
    int deleteByPrimaryKey(Integer dutyDetailId);

    int insert(DutyDetail record);

    int insertSelective(DutyDetail record);

    DutyDetail selectByPrimaryKey(Integer dutyDetailId);

    int updateByPrimaryKeySelective(DutyDetail record);

    int updateByPrimaryKey(DutyDetail record);
    
    List<DutyDetailVo> findList(@Param("condition") DutyDetailQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DutyDetailQVo condition);
	
	DutyDetailVo selectVoByPrimaryKey(String dutyDetailId);
}