package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.SalaryImportQVo;
import org.springframework.web.multipart.MultipartFile;

public interface SalaryImportService {
	
	public abstract DataResponse findSalaryImportList(SalaryImportQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertSalaryImport(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse deleteSalaryImportBySalaryImportId(String id,SystemUser systemUser) throws Exception;
	
}
