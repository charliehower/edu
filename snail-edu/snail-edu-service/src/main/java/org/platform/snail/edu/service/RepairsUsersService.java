package org.platform.snail.edu.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.platform.snail.beans.CheckTree;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.RepairsUsersQVo;

public interface RepairsUsersService {
	public abstract DataResponse findRepairsUsersList(RepairsUsersQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertRepairsUsers(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateRepairsUsers(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectRepairsUsersByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteRepairsUsersByRepairsUsersId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateRepairsUsers(JSONObject json,SystemUser systemUser) throws Exception;
	List<CheckTree> selectLocationTreeList(String userId);
	
	String getAssignee(String repairsCategory);

}
