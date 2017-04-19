package org.platform.snail.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.QueueCmccWait;
import org.platform.snail.portal.model.TaskCmcc;
import org.platform.snail.portal.vo.TaskCmccQVo;
import org.platform.snail.portal.vo.TaskCmccVo;

public interface TaskCmccMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(TaskCmcc record);

    int insertSelective(TaskCmcc record);

    TaskCmcc selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(TaskCmcc record);

    int updateByPrimaryKeyWithBLOBs(TaskCmcc record);

    int updateByPrimaryKey(TaskCmcc record);
    int isExitByTaskName(@Param("name") String name);
    List<TaskCmccVo> findList(@Param("condition") TaskCmccQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TaskCmccQVo condition);
	
	List<TaskCmcc> selectByTask();
	
	List<QueueCmccWait>selectQueueByTask();
	
	int updateStatusByPrimaryKey(@Param("taskId") String taskId,@Param("status") String status);
	
	List<Map<String,Object>> selectWorkflowTaskMsg();
	
	int updateWorkflowTaskMsgByTaskId(String id);
}