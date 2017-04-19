package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.ClassesQVo;

public interface ClassesService {
	public abstract DataResponse findClassesList(ClassesQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertClasses(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateClasses(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectClassesByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteClassesByClassesId(String id,SystemUser systemUser) throws Exception;

}
