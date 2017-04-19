package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.SiteQVo;
import org.springframework.web.multipart.MultipartFile;
public interface SiteService {
	public abstract DataResponse findSiteList(SiteQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertSite(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateSite(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectSiteByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteSiteBySiteId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateSite(JSONObject json,SystemUser systemUser) throws Exception;
	public DataResponse importXls(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
	

}
