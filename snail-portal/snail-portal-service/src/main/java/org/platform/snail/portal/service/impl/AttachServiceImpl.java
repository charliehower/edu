package org.platform.snail.portal.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.AttachMapper;
import org.platform.snail.portal.model.Attach;
import org.platform.snail.portal.service.AttachService;
import org.platform.snail.portal.vo.AttachQVo;
import org.platform.snail.portal.vo.AttachVo;
import org.platform.snail.service.DataBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

@Service("attachService")
public class AttachServiceImpl implements AttachService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findAttachList(AttachQVo condition) throws Exception {
		DataResponse rst = new DataResponse();
		List<AttachVo> list = this.attachMapper.findList(condition);
		rst.setList(list);
		return rst;
	}

	public DataResponse deleteAttachByFileName(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.attachMapper.deleteByFileUrl(id);
		this.dataBaseLogService.log("删除附件", "附件", String.valueOf(id), String.valueOf(id),
				"附件", systemUser);
		return rst;
	}
	public  DataResponse upload(MultipartFile[] file,String noticeId, String collectionName,SystemUser systemUser) throws IOException{
		DataResponse rst = new DataResponse();
		String fileName = null;
		String fileId=null;
		List<Attach> list=new ArrayList<Attach>();
		for (int i = 0; i < file.length; i++) {
			fileId=UUID.randomUUID().toString();
			fileName=fileId+""+file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));
			this.logger.info(file[i].getOriginalFilename());
			this.logger.info(fileName);
			
			this.savefile(collectionName, file[i].getInputStream(), fileId, fileName);
			Attach o = new Attach();
			o.setNoticeId(noticeId);
			o.setCategory(collectionName);
			o.setCreateTime(new Date());
			o.setFileName(file[i].getOriginalFilename());
			o.setFileUrl(fileName);
			o.setFileSize(String.valueOf(file[i].getSize()));
			list.add(o);
			this.attachMapper.insert(o);
			this.dataBaseLogService.log("附件上传", "附件上传", "",file[i].getOriginalFilename(), fileName, systemUser);
		}
		rst.setErrorMessage("上传成功");
		rst.setList(list);
		rst.setResponse(noticeId);
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
