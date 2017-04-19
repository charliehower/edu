package org.platform.snail.edu.dao;

import java.util.List;

import org.platform.snail.edu.model.TemplateCmcc;

public interface TemplateCmccMapper {
    int deleteByPrimaryKey(String templateCmccId);

    int insert(TemplateCmcc record);

    int insertSelective(TemplateCmcc record);

    TemplateCmcc selectByPrimaryKey(String templateCmccId);

    int updateByPrimaryKeySelective(TemplateCmcc record);

    int updateByPrimaryKey(TemplateCmcc record);
    
    List<TemplateCmcc> selectList();
}