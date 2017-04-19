package org.platform.snail.edu.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.edu.vo.GroupQVo;

public interface GroupService {
	public abstract DataResponse findGroupList(GroupQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertGroup(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateGroup(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectGroupByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteGroupByGroupId(String id,SystemUser systemUser) throws Exception;
	
	public abstract List<Tree>  selectGroupDepTreeByPid(String pid);
	public abstract List<Tree>  selectGroupGradeTreeByPid(String pid);
	public abstract List<Tree>  selectGroupDiscriblineTreeByPid(String pid);
	public abstract List<Tree> selectFreeGroupTreeRoot();
	public abstract List<Tree> selectFreeGroupTreeByPid(String pid);
	
	public abstract DataResponse selectFreeGroupUsersListByGorupId(String groupId) throws Exception;
	public abstract DataResponse batchSaveGroupUsersByUserIds(JSONObject json,SystemUser systemUser) throws Exception;

}
