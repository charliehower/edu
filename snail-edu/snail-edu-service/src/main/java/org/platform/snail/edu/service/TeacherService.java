package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.TeacherQVo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {
	public abstract DataResponse findTeacherList(TeacherQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertTeacher(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateTeacher(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectTeacherByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteTeacherByTeacherId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateUsersByTeacherId(String teacherId,SystemUser systemUser) throws Exception;
	public abstract DataResponse createUsersByTeacherIds(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateGroup(JSONObject json,SystemUser systemUser) throws Exception;
	public  DataResponse importTeacher(@RequestParam MultipartFile[] file,SystemUser systemUser)throws Exception;
	public abstract DataResponse updateQuitByIdTeacherId(String teacherId,SystemUser systemUser) throws Exception;
	
	public void sync() throws Exception;
	

}
