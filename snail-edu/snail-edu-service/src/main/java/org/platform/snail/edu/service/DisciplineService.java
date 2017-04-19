package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.DisciplineQVo;

public interface DisciplineService {
	public abstract DataResponse findDisciplineList(DisciplineQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertDiscipline(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateDiscipline(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectDisciplineByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteDisciplineByDisciplineId(String id,SystemUser systemUser) throws Exception;

}
