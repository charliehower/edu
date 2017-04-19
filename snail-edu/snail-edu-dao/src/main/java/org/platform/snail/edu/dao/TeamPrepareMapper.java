package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.TeamPrepare;

public interface TeamPrepareMapper {
    int deleteByPrimaryKey(Integer teamPrepareId);

    int insert(TeamPrepare record);

    int insertSelective(TeamPrepare record);

    TeamPrepare selectByPrimaryKey(Integer teamPrepareId);

    int updateByPrimaryKeySelective(TeamPrepare record);

    int updateByPrimaryKey(TeamPrepare record);
    
    int saveOrUpdateTeamPrepare(TeamPrepare record);
    
    
	List<Map<String, String>> selectTeamPrepareListByYear(@Param("year")String year);
}