package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.PersonalFiles;

public interface PersonalFilesMapper {
    int deleteByPrimaryKey(Integer personalFilesId);

    int insert(PersonalFiles record);

    int insertSelective(PersonalFiles record);

    PersonalFiles selectByPrimaryKey(Integer personalFilesId);

    int updateByPrimaryKeySelective(PersonalFiles record);

    int updateByPrimaryKeyWithBLOBs(PersonalFiles record);

    int updateByPrimaryKey(PersonalFiles record);
    
    List<PersonalFiles>  findPersonalFilesListByTeacherId(@Param("teacherId")String teacherId);
}