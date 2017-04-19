package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.DocFlow;
import org.platform.snail.edu.vo.DocFlowQVo;
import org.platform.snail.edu.vo.DocFlowVo;

public interface DocFlowMapper {
    int deleteByPrimaryKey(String DocFlowId);

    int insert(DocFlow record);

    int insertSelective(DocFlow record);

    DocFlowVo selectByPrimaryKey(String DocFlowId);

    int updateByPrimaryKeySelective(DocFlow record);

    int updateByPrimaryKey(DocFlow record);
    
    List<DocFlowVo> findList(@Param("condition") DocFlowQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DocFlowQVo condition);

	int isExit(DocFlow record);
}