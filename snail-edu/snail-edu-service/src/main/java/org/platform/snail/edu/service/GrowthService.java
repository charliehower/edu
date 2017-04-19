package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.GrowthQVo;
import org.springframework.web.multipart.MultipartFile;
public interface GrowthService {
	public abstract DataResponse findGrowthList(GrowthQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertGrowth(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateGrowth(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectGrowthByPrimaryKey(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse deleteGrowthByGrowthId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateGrowth(JSONObject json,SystemUser systemUser) throws Exception;
	public DataResponse importXls(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
	

}
