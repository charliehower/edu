/**
 * @Title: MongoServiceImpl.java
 * @Package org.platform.snail.service.impl
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月20日 上午8:41:32
 * @version V1.0
 */

package org.platform.snail.portal.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.service.MongoService;
import org.platform.snail.service.DataBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * @ClassName: MongoServiceImpl
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月20日 上午8:41:32
 *
 */
@Component("mongoService")
public class MongoServiceImpl implements MongoService {
	
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	/*
	 * <p>Title: savefile</p>
	 * <p>Description: </p>
	 * @param collectionName
	 * @param file
	 * @param fileId
	 * @param fileName
	 * @return
	 * @see org.platform.snail.service.MongoService#savefile(java.lang.String, java.io.File, java.lang.String, java.lang.String)
	 */

	public boolean savefile(String collectionName, File file, String fileId,
			String fileName) throws IOException {
		DB db = mongoTemplate.getDb();
        GridFS gridFS = new GridFS(db, collectionName);
        GridFSInputFile gfs = gridFS.createFile(file);
        gfs.put("fileName", fileName);
        gfs.put("fileId", fileId);
        gfs.put("contentType", fileName.substring(fileName.lastIndexOf(".")));
        gfs.save();
		return false;
	}
	public boolean savefile(String collectionName, InputStream in, String fileId,
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
	/*
	 * <p>Title: retrieveFileOne</p>
	 * <p>Description: </p>
	 * @param collectionName
	 * @param filenName
	 * @return
	 * @see org.platform.snail.service.MongoService#retrieveFileOne(java.lang.String, java.lang.String)
	 */

	public GridFSDBFile retrieveFileOne(String collectionName, String filenName) throws IOException{
		DB db = mongoTemplate.getDb();
		GridFS gridFS = new GridFS(db, collectionName);
        GridFSDBFile dbfile = gridFS.findOne(filenName);
		return dbfile;
	}
	public  DataResponse upload(MultipartFile[] file, String collectionName,SystemUser systemUser) throws IOException{
		DataResponse rst = new DataResponse();
		String fileName = null;
		String fileId=null;
		for (int i = 0; i < file.length; i++) {
			fileId=UUID.randomUUID().toString();
			fileName=fileId+""+file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));
			this.logger.info(file[i].getOriginalFilename());
			this.logger.info(fileName);
			this.savefile(collectionName, file[i].getInputStream(), fileId, fileName);
			this.dataBaseLogService.log("文件上传", "文件上传", "",file[i].getOriginalFilename(), fileName, systemUser);
		}
		rst.setErrorMessage("上传成功");
		rst.setResponse(fileName);
		return rst;
	}
}
