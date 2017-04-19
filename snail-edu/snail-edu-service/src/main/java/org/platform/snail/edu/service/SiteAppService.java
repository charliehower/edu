package org.platform.snail.edu.service;

import java.util.Map;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.SiteAppQVo;
import org.springframework.web.multipart.MultipartFile;
public interface SiteAppService {
	public abstract DataResponse findSiteAppList(SiteAppQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertSiteApp(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateSiteApp(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectSiteAppByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteSiteAppBySiteAppId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateSiteApp(JSONObject json,SystemUser systemUser) throws Exception;
	public DataResponse importXls(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
	public  Map<String,Object>  selectSiteTreeByPid(String pid,SystemUser systemUser);
	

}
