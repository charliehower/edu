package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Headmaster;

public interface HeadmasterMapper {
    int deleteByPrimaryKey(Integer headmasterId);

    int insert(Headmaster record);

    int insertSelective(Headmaster record);

    Headmaster selectByPrimaryKey(Integer headmasterId);

    int updateByPrimaryKeySelective(Headmaster record);

    int updateByPrimaryKey(Headmaster record);
    
    int saveOrUpdateHeadmaster(Headmaster record);
    
    
    List<Map<String,String>> selectHeadmasterListByYear(@Param("year")String year);
}