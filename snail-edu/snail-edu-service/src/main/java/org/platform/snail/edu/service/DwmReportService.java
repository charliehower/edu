package org.platform.snail.edu.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.DwmReportQVo;

public interface DwmReportService {
	public abstract DataResponse findDwmReportList(DwmReportQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertDwmReport(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateDwmReport(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectDwmReportByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteDwmReportByDwmReportId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateDwmReport(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateForTopByPrimaryKey(String dwmReportId,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateForStatusByPrimaryKey(String dwmReportId,String status,String departmentId,String groupId,String contextPath,SystemUser systemUser) throws Exception;
	
	public abstract DataResponse findListTop(String categoryId,int limit,SystemUser systemUser) throws Exception;
	
	public abstract DataResponse getReportByCategoryIdAndTime(DwmReportQVo condition) throws Exception;
	
	public abstract DataResponse getViewerByDwrReportId(String id) throws Exception;
	
	public abstract List<Map<String, Object>> selectMyDeptUser(SystemUser systemUser) throws Exception;

}
