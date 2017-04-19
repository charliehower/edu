package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Salaryb;

public interface SalarybMapper {
    int deleteByPrimaryKey(String salaryId);

    int insert(Salaryb record);

    int insertSelective(Salaryb record);

    Salaryb selectByPrimaryKey(String salaryId);

    int updateByPrimaryKeySelective(Salaryb record);

    int updateByPrimaryKey(Salaryb record);
    List<Salaryb> findList(@Param("condition")Map<String,Object> condition);

}