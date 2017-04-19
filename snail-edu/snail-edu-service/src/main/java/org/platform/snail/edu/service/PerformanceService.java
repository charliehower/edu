package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.PerformanceQVo;
import org.springframework.web.multipart.MultipartFile;
public interface PerformanceService {
	public abstract DataResponse findPerformanceList(PerformanceQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertPerformance(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updatePerformance(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectPerformanceByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deletePerformanceByPerformanceId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdatePerformance(JSONObject json,SystemUser systemUser) throws Exception;
	public DataResponse importXls(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
	

}
