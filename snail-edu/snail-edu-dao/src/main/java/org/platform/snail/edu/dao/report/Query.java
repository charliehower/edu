package org.platform.snail.edu.dao.report;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface Query {
	List<Map<String, Object>> query(
			@Param("condition") Map<String, Object> condition);
}
