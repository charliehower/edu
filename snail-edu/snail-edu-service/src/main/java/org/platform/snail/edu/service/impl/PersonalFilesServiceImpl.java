package org.platform.snail.edu.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.PersonalFilesMapper;
import org.platform.snail.edu.model.PersonalFiles;
import org.platform.snail.edu.service.PersonalFilesService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
@Service("personalFilesService")
public class PersonalFilesServiceImpl implements PersonalFilesService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PersonalFilesMapper personalFilesMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private MongoTemplate mongoTemplate;
	public List<PersonalFiles>  findPersonalFilesListByTeacherId(String teacherId)
			throws Exception {
		List<PersonalFiles>  list=this.personalFilesMapper.findPersonalFilesListByTeacherId(teacherId);
		return list;
	}

	public DataResponse insertPersonalFiles(JSONObject json,
			SystemUser systemUser) throws Exception {
		PersonalFiles o = new PersonalFiles();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategory())) {
			return new DataResponse(false, "类别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "内容不能为空！");
		}
		
		this.personalFilesMapper.insert(o);
		this.dataBaseLogService.log("添加教职工档案", "教职工", "", o.getContent(),
				o.getContent(), systemUser);
		return new DataResponse(true, "添加教职工档案完成！");
	}

	public DataResponse updatePersonalFiles(JSONObject json,
			SystemUser systemUser) throws Exception {
		PersonalFiles o = new PersonalFiles();
		SnailBeanUtils.copyProperties(o, json);
		if (o.getPersonalFilesId()==null) {
			return new DataResponse(false, "档案编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategory())) {
			return new DataResponse(false, "类别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "内容不能为空！");
		}
		
		this.personalFilesMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更教职工档案", "教职工", "", o.getContent(),
				o.getContent(), systemUser);
		return new DataResponse(true, "变更教职工档案完成！");
	}

	public DataResponse selectPersonalFilesByPrimaryKey(String id)
			throws Exception {
		DataResponse rst=new DataResponse();
		rst.setResponse(this.personalFilesMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deletePersonalFilesByPersonalFilesId(String id,
			SystemUser systemUser) throws Exception {
		this.personalFilesMapper.deleteByPrimaryKey(Integer.valueOf(id));
		return new DataResponse(true, "删除教职工档案完成！");
	}
	public  DataResponse upload(MultipartFile[] file,String category,String teacherId, String collectionName,SystemUser systemUser) throws IOException{
		DataResponse rst = new DataResponse();
		String fileName = null;
		String fileId=null;
		List<String> fileNames=new ArrayList<String>();
		for (int i = 0; i < file.length; i++) {
			fileId=UUID.randomUUID().toString();
			fileName=fileId+""+file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));
			this.logger.info(file[i].getOriginalFilename());
			this.logger.info(fileName);
			fileNames.add(file[i].getOriginalFilename());
			this.savefile(collectionName, file[i].getInputStream(), fileId, fileName);
			PersonalFiles o = new PersonalFiles();
			o.setCategory(category);
			o.setTeacherId(teacherId);
			o.setCreateTime(new Date());
			o.setFileName(file[i].getOriginalFilename());
			o.setFileUrl(fileName);
			o.setTitle(file[i].getOriginalFilename());
			this.personalFilesMapper.insertSelective(o);
			this.dataBaseLogService.log("文件上传", "文件上传", "",file[i].getOriginalFilename(), fileName, systemUser);
		}
		rst.setErrorMessage("上传成功");
		rst.setResponse(fileNames);
		return rst;
	}
	private boolean savefile(String collectionName, InputStream in, String fileId,
			String fileName) throws IOException {
		DB db = mongoTemplate.getDb();
        GridFS gridFS = new GridFS(db, collectionName);
        GridFSInputFile gfs = gridFS.createFile(in);
        gfs.setFilename(fileName);
        gfs.setId(fileId);
        gfs.put("fileName", fileName);
        gfs.put("fileId", fileId);
        gfs.put("contentType", fileName.substring(fileName.lastIndexOf(".")));
        gfs.save();
		return false;
	}

}
