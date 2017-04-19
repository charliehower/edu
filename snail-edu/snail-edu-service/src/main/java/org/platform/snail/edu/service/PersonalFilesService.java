package org.platform.snail.edu.service;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.PersonalFiles;
import org.springframework.web.multipart.MultipartFile;

public interface PersonalFilesService {
	public abstract List<PersonalFiles> findPersonalFilesListByTeacherId(String teacherId) throws Exception;
	public abstract DataResponse insertPersonalFiles(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updatePersonalFiles(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectPersonalFilesByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deletePersonalFilesByPersonalFilesId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse upload(MultipartFile[] file,String category, String teacherId,String collectionName,SystemUser systemUser) throws IOException;


}
