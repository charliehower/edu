package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Salaryc;

public interface SalarycMapper {
    int deleteByPrimaryKey(String salaryId);

    int insert(Salaryc record);

    int insertSelective(Salaryc record);

    Salaryc selectByPrimaryKey(String salaryId);

    int updateByPrimaryKeySelective(Salaryc record);

    int updateByPrimaryKey(Salaryc record);
    List<Salaryc> findList(@Param("condition")Map<String,Object> condition);
}