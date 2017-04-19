package org.platform.snail.edu.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.WorkPerformance;

public interface WorkPerformanceService {
	public abstract List<WorkPerformance> findWorkPerformanceListByTeacherId(String teacherId) throws Exception;
	public abstract DataResponse saveOrUpdateWorkPerformance(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectWorkPerformanceByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteWorkPerformanceByWorkPerformanceId(String id,SystemUser systemUser) throws Exception;
	
}
