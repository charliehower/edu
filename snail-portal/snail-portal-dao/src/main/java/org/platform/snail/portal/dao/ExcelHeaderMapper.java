package org.platform.snail.portal.dao;

import org.platform.snail.portal.model.ExcelHeader;

public interface ExcelHeaderMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExcelHeader record);

    int insertSelective(ExcelHeader record);

    ExcelHeader selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExcelHeader record);

    int updateByPrimaryKey(ExcelHeader record);
}