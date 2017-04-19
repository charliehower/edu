package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.TeamGrade;

public interface TeamGradeMapper {
	int deleteByPrimaryKey(Integer teamGradeId);

	int insert(TeamGrade record);

	int insertSelective(TeamGrade record);

	TeamGrade selectByPrimaryKey(Integer teamGradeId);

	int updateByPrimaryKeySelective(TeamGrade record);

	int updateByPrimaryKey(TeamGrade record);

	int saveOrUpdateTeamGrade(TeamGrade record);

	List<Map<String, String>> selectTeamGradeListByYear(
			@Param("year") String year);
}