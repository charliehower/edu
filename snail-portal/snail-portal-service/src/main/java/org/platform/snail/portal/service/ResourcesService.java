package org.platform.snail.portal.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.portal.model.Resources;
import org.springframework.web.multipart.MultipartFile;

public interface ResourcesService {
	public abstract DataResponse findResourcesList(Resources condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertResources(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateResources(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectResourcesByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteResourcesByResourcesId(String id,SystemUser systemUser) throws Exception;
	public abstract List<Tree>  selectResourcesTreeList() throws Exception;
	public abstract DataResponse importXls(MultipartFile[] files, JSONObject json,
			SystemUser systemUser) throws Exception;
}
