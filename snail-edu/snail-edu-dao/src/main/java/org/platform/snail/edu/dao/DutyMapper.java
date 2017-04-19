package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Duty;
import org.platform.snail.edu.vo.DutyQVo;

public interface DutyMapper {
    int deleteByPrimaryKey(String dutyId);

	int insert(Duty record);

	int insertSelective(Duty record);

	Duty selectByPrimaryKey(String dutyId);

	int updateByPrimaryKeySelective(Duty record);

	int updateByPrimaryKey(Duty record);
	List<Map<String,Object>> findList(@Param("condition") DutyQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DutyQVo condition);
	 int saveOrUpdateDuty(Duty record);
	 List<Map<String,String>> selectTeacherListByNames(@Param("names")String[] names);
	 
	 int batchInsert(@Param("list")List<Duty> list);

   
}