package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Leave;
import org.platform.snail.edu.vo.LeaveQVo;
import org.platform.snail.edu.vo.LeaveVo;

public interface LeaveMapper {
	int deleteByPrimaryKey(String leaveId);

	int insert(Leave record);

	int insertSelective(Leave record);

	Leave selectByPrimaryKey(String leaveId);

	int updateByPrimaryKeySelective(Leave record);

	int updateByPrimaryKey(Leave record);

	List<LeaveVo> findList(@Param("condition") LeaveQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") LeaveQVo condition);

	int insertOrUpdateSelective(Leave record);

	LeaveVo selectVoByPrimaryKey(String leaveId);

	int updateAuditById(@Param("leaveId") String leaveId,
			@Param("auditStatus") String auditStatus,
			@Param("auditRemark") String auditRemark);

	
}