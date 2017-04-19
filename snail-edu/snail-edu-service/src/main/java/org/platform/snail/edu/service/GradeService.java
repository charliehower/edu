package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.GradeQVo;

public interface GradeService {
	public abstract DataResponse findGradeList(GradeQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertGrade(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateGrade(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectGradeByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteGradeByGradeId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateUpgrade(String year, SystemUser systemUser)
			throws Exception;
	public abstract DataResponse updateunDoUpgrade(String year, SystemUser systemUser)
			throws Exception;

}
