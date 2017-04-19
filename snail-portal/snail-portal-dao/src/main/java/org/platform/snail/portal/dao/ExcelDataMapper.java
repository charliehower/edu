package org.platform.snail.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.ExcelData;

public interface ExcelDataMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExcelData record);

    int insertSelective(ExcelData record);

    ExcelData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExcelData record);

    int updateByPrimaryKey(ExcelData record);
    
    int isExit(ExcelData record);
    
    List<ExcelData> selectList(String id);
    List<ExcelData> selectListSelf(@Param("id") String id,@Param("userId")String userId);
}