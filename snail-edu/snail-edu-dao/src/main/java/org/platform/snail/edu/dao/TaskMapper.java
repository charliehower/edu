package org.platform.snail.edu.dao;

import java.util.List;

import org.platform.snail.edu.model.Task;

public interface TaskMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
    int batchInsert(List<Task> list);
}