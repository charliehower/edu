package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.DutyQVo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface DutyService {
	public abstract DataResponse findDutyList(DutyQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertDuty(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateDuty(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectDutyByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteDutyByDutyId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateDuty(JSONObject json,SystemUser systemUser) throws Exception;
	public  DataResponse importDuty(@RequestParam MultipartFile[] file,SystemUser systemUser)throws Exception;

}
