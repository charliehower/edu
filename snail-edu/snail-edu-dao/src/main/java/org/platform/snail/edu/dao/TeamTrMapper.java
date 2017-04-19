package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.TeamTr;

public interface TeamTrMapper {
    int deleteByPrimaryKey(Integer teamTrId);

    int insert(TeamTr record);

    int insertSelective(TeamTr record);

    TeamTr selectByPrimaryKey(Integer teamTrId);

    int updateByPrimaryKeySelective(TeamTr record);

    int updateByPrimaryKey(TeamTr record);
    int saveOrUpdateTeamTr(TeamTr record);
    
    
   	List<Map<String, String>> selectTeamTrListByYear(@Param("year")String year);
}