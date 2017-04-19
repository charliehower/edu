package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.WorkPerformance;

public interface WorkPerformanceMapper {
    int deleteByPrimaryKey(Integer workPerformanceId);

    int insert(WorkPerformance record);

    int insertSelective(WorkPerformance record);

    WorkPerformance selectByPrimaryKey(Integer workPerformanceId);

    int updateByPrimaryKeySelective(WorkPerformance record);

    int updateByPrimaryKeyWithBLOBs(WorkPerformance record);

    int updateByPrimaryKey(WorkPerformance record);
    
    List<WorkPerformance> findWorkPerformanceListByTeacherId(@Param("teacherId")String teacherId);
}