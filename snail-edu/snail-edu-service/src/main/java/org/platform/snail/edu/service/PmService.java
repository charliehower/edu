package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.PmQVo;
import org.springframework.web.multipart.MultipartFile;
public interface PmService {
	public abstract DataResponse findPmList(PmQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertPm(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updatePm(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectPmByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deletePmByPmId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdatePm(JSONObject json,SystemUser systemUser) throws Exception;
	public DataResponse importXls(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
	

}
