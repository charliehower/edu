package org.platform.snail.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.QueueCmccHis;
import org.platform.snail.portal.vo.QueueCmccHisQVo;
import org.platform.snail.portal.vo.QueueCmccHisVo;

public interface QueueCmccHisMapper {
    int deleteByPrimaryKey(String queueId);

    int insert(QueueCmccHis record);

    int insertSelective(QueueCmccHis record);

    QueueCmccHis selectByPrimaryKey(String queueId);

    int updateByPrimaryKeySelective(QueueCmccHis record);

    int updateByPrimaryKey(QueueCmccHis record);
    List<QueueCmccHisVo> findList(@Param("condition") QueueCmccHisQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") QueueCmccHisQVo condition);
	
	 int batchInsert(QueueCmccHis record);
}