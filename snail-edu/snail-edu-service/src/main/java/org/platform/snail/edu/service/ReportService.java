package org.platform.snail.edu.service;

import java.util.Map;

import org.platform.snail.beans.DataResponse;

public interface ReportService {
	public abstract DataResponse query(Map<String, Object> params,String reportId)
			throws Exception;

}
