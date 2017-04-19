package org.platform.snail.edu.dao;

import org.platform.snail.edu.model.TaskCmcc;

public interface TaskCmccMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(TaskCmcc record);

    int insertSelective(TaskCmcc record);

    TaskCmcc selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(TaskCmcc record);

    int updateByPrimaryKeyWithBLOBs(TaskCmcc record);

    int updateByPrimaryKey(TaskCmcc record);
}