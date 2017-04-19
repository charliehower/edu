package org.platform.snail.edu.dao;

import java.util.List;

import org.platform.snail.edu.model.TcEmployee;

public interface TcEmployeeMapper {
    int deleteByPrimaryKey(Long employeeid);

    int insert(TcEmployee record);

    int insertSelective(TcEmployee record);

    TcEmployee selectByPrimaryKey(Long employeeid);

    int updateByPrimaryKeySelective(TcEmployee record);

    int updateByPrimaryKey(TcEmployee record);
    List<TcEmployee> getList();
}