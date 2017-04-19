package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.ClassChedule;

public interface ClassCheduleMapper {
    int deleteByPrimaryKey(Integer classScheduleId);

    int insert(ClassChedule record);

    int insertSelective(ClassChedule record);

    ClassChedule selectByPrimaryKey(Integer classScheduleId);

    int updateByPrimaryKeySelective(ClassChedule record);

    int updateByPrimaryKey(ClassChedule record);
    
    List<Map<String,String>> selectAllWeekList();
    List<Map<String,String>> selectAllSectionList();
    int saveOrUpdateClassChedule(ClassChedule record);
    
    List<Map<String,String>> selectClassCheduleListByClassesId(@Param("classesId")String classesId);
}