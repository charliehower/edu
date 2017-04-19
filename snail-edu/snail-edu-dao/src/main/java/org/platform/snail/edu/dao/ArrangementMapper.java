package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Arrangement;

public interface ArrangementMapper {
    int deleteByPrimaryKey(Integer arrangementId);

    int insert(Arrangement record);

    int insertSelective(Arrangement record);

    Arrangement selectByPrimaryKey(Integer arrangementId);

    int updateByPrimaryKeySelective(Arrangement record);

    int updateByPrimaryKey(Arrangement record);
    
    List<Map<String,String>> selectArrListByYear(@Param("year")String year);
    List<Map<String,String>> selectAllDisciplineList();
    List<Map<String,String>> selectClassesListByGradeId(@Param("gradeId")String gradeId);
    List<Map<String,String>> selectAllGradeList();
    int saveOrUpdateArrangement(Arrangement record);
}