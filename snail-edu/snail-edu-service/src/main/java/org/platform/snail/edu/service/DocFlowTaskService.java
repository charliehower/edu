package org.platform.snail.edu.service;

import java.util.Map;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.DocFlowTaskQVo;
import org.springframework.web.multipart.MultipartFile;
public interface DocFlowTaskService {
	public abstract DataResponse findDocFlowTaskList(DocFlowTaskQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertDocFlowTask(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateDocFlowTask(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectDocFlowTaskByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteDocFlowTaskByDocFlowTaskId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateDocFlowTask(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse importXls(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectTaskListByDocFlowId(String docFlowId)throws Exception;
	public abstract Map<String,Object> selectTaskTreeListByPid(String docFlowId,String pid)throws Exception;
	

}
