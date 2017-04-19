package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Salarya;

public interface SalaryaMapper {
    int deleteByPrimaryKey(String salaryId);

    int insert(Salarya record);

    int insertSelective(Salarya record);

    Salarya selectByPrimaryKey(String salaryId);

    int updateByPrimaryKeySelective(Salarya record);

    int updateByPrimaryKey(Salarya record);
    List<Salarya> findList(@Param("condition")Map<String,Object> condition);


}