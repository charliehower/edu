package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.StudentQVo;

public interface StudentService {
	public abstract DataResponse findStudentList(StudentQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertStudent(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateStudent(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectStudentByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteStudentByStudentId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateStudent(JSONObject json,SystemUser systemUser) throws Exception;

}
