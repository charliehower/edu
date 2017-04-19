package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.DocFlowQVo;
import org.springframework.web.multipart.MultipartFile;
public interface DocFlowService {
	public abstract DataResponse findDocFlowList(DocFlowQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertDocFlow(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateDocFlow(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectDocFlowByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteDocFlowByDocFlowId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateDocFlow(JSONObject json,SystemUser systemUser) throws Exception;
	public DataResponse importXls(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
	

}
