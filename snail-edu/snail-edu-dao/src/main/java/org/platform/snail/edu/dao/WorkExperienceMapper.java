package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.WorkExperience;

public interface WorkExperienceMapper {
    int deleteByPrimaryKey(Integer workExperienceId);

    int insert(WorkExperience record);

    int insertSelective(WorkExperience record);

    WorkExperience selectByPrimaryKey(Integer workExperienceId);

    int updateByPrimaryKeySelective(WorkExperience record);

    int updateByPrimaryKeyWithBLOBs(WorkExperience record);

    int updateByPrimaryKey(WorkExperience record);
    
    List<WorkExperience> findWorkExperienceListByTeacherId(@Param("teacherId")
			String teacherId);
}