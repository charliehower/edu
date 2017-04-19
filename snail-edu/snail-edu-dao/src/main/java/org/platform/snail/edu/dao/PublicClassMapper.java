package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.PublicClass;
import org.platform.snail.edu.model.PublicClassWithBLOBs;
import org.platform.snail.edu.vo.PublicClassQVo;
import org.platform.snail.edu.vo.PublicClassVo;

public interface PublicClassMapper {
	int deleteByPrimaryKey(String publicClassId);

	int insert(PublicClassWithBLOBs record);


	PublicClassVo selectByPrimaryKey(String publicClassId);
	
	PublicClass selectOnlyOne(String id);


	int updateByPrimaryKeyWithBLOBs(PublicClassWithBLOBs record);

	int updateByPrimaryKey(PublicClass record);

	int isExitByTitle(String title);

	int updateAlertStatus(String id);

	List<PublicClassVo> findList(@Param("condition") PublicClassQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PublicClassQVo condition);

	int updateAudit(@Param("id") String id,
			@Param("auditorId") String auditorId, @Param("status") String status, @Param("remark") String remark);

	int updateRelease(@Param("id") String id,
			@Param("pusherId") String pusherId, @Param("status") String status);

	int updateScore(@Param("id") String id, @Param("score") float score,
			@Param("remark") String remark, @Param("teacherId") String teacerId);

	Map<String, Object> selectSumScore(String id);

	List<Map<String, Object>> selectTaskAlertList();

	int updateAuditSec(@Param("id") String id, @Param("auditorId") String auditorId,
			@Param("status") String status, @Param("remark") String remark);

	int updateAuditThi(@Param("id") String id, @Param("auditorId") String auditorId,
			@Param("status") String status, @Param("remark") String remark);

	int updateAuditFor(@Param("id") String id, @Param("auditorId") String auditorId,
			@Param("status") String status, @Param("remark") String remark);
}