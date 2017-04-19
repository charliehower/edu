package org.platform.snail.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.Task;
import org.platform.snail.portal.vo.TaskVo;

public interface TaskMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
    List<TaskVo> findList (@Param("condition")Task condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
	
	int findCount (@Param("condition")Task condition);
	
	List<Task> findListByUserId(@Param("userId")String userId);
	
	 int batchInsert(List<Task> list);
	
}