package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Repairs;
import org.platform.snail.edu.vo.RepairsQVo;
import org.platform.snail.edu.vo.RepairsVo;

public interface RepairsMapper {
    int deleteByPrimaryKey(String repairsId);

    int insert(Repairs record);

    int insertSelective(Repairs record);

    Repairs selectByPrimaryKey(String repairsId);

    int updateByPrimaryKeySelective(Repairs record);

    int updateByPrimaryKey(Repairs record);
    List<RepairsVo> findList(@Param("condition") RepairsQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") RepairsQVo condition);
	
	int insertOrUpdateSelective(Repairs record);
	RepairsVo selectVoByPrimaryKey(String repairsId);
	int updateByByPrimaryKeyAndRepairsUserId(@Param("id")String id,@Param("userId")String userId);
}