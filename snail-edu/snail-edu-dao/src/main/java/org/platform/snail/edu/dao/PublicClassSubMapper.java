package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.platform.snail.edu.model.PublicClassSub;

public interface PublicClassSubMapper {
    int deleteByPrimaryKey(String publicClassSubId);

    int insert(PublicClassSub record);

    int insertSelective(PublicClassSub record);

    PublicClassSub selectByPrimaryKey(String publicClassSubId);

    int updateByPrimaryKeySelective(PublicClassSub record);

    int updateByPrimaryKey(PublicClassSub record);
    
    List<Map<String,Object>> selectListByid(String id);
    
    int isReg(PublicClassSub o);
}