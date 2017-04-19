package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.LearningExperience;

public interface LearningExperienceMapper {
    int deleteByPrimaryKey(Integer learningExperienceId);

    int insert(LearningExperience record);

    int insertSelective(LearningExperience record);

    LearningExperience selectByPrimaryKey(Integer learningExperienceId);

    int updateByPrimaryKeySelective(LearningExperience record);

    int updateByPrimaryKey(LearningExperience record);
    
    List<LearningExperience>findLearningExperienceListByTeacherId(@Param("teacherId") String teacherId);
}