package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.DocFlowTask;
import org.platform.snail.edu.vo.DocFlowTaskQVo;
import org.platform.snail.edu.vo.DocFlowTaskVo;

public interface DocFlowTaskMapper {
    int deleteByPrimaryKey(String DocFlowTaskId);

    int insert(DocFlowTask record);

    int insertSelective(DocFlowTask record);

    DocFlowTask selectByPrimaryKey(String DocFlowTaskId);

    int updateByPrimaryKeySelective(DocFlowTask record);

    int updateByPrimaryKey(DocFlowTask record);
    
    List<DocFlowTaskVo> findList(@Param("condition") DocFlowTaskQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DocFlowTaskQVo condition);

	int isExit(DocFlowTask record);
	
	int updateDocFlowForStatusById(@Param("id") String id,
			@Param("status") String status);
	
	List<Map<String,String>> selectMyDeptIds(String id);
	
	int updateEndTaskByPrimaryKey(String id);
	
	List<DocFlowTaskVo> selectTaskListByDocFlowId(String docFlowId);
	
	List<Map<String,String>> selectTaskTreeListByPid(@Param("docFlowId")String docFlowId,@Param("pid") String pid);
	Map<String,String> selectUserByDeptId(String deptId);
}