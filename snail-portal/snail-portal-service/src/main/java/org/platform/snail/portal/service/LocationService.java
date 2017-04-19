package org.platform.snail.portal.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.portal.vo.LocationQVo;

public interface LocationService {
	public abstract DataResponse findLocationList(LocationQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertLocation(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateLocation(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectLocationByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteLocationByLocationId(String id,SystemUser systemUser) throws Exception;
	public abstract List<Tree> selectLocationTreeList(String pid,String load) throws Exception;

}
