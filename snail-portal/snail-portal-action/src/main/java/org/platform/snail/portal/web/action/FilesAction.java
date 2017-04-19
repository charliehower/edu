package org.platform.snail.portal.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.dao.SystemDao;
import org.platform.snail.model.Files;
import org.platform.snail.portal.service.MongoService;
import org.platform.snail.utils.FileType;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

@Controller
@RequestMapping("/files")
public class FilesAction extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SystemDao systemDao;
	@Autowired
	private MongoService mongoService;
	
	@RequestMapping(value = "/upload.do")
	@ResponseBody
	public Map<String, String> upload(@RequestParam MultipartFile[] file) throws Exception {
		SystemUser systemUser = this.getSessionSystemUser();
		Map<String, String> map = new HashMap<String, String>();
		try {
			String fileExtName = "";
			for (int i = 0; i < file.length; i++) {
				fileExtName = FileType.getFileType(file[i].getInputStream());
				String realName = UUID.randomUUID().toString() + "."+ fileExtName;
				String fileId = this.saveFile(file[i], realName, fileExtName,
						systemUser.getUsers().getUserId());
				map.put("key", fileId);
			}
			map.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("error", e.getMessage());
		}
		return map;
	}

	private String saveFile(MultipartFile file, String fileName, String fileExtName,
			String operater) {
		Files files = new Files();
		files.setFileName(fileName);
		files.setFileExtName(fileExtName);
		files.setFileId(fileName.replaceAll("." + fileExtName, ""));
		try {
			files.setFileContent(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		files.setOperater(operater);
		this.systemDao.insertFiles(files);
		return files.getFileId();
	}
	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public DataResponse uploadFile(@RequestParam MultipartFile[] file, String collectionName)
			throws Exception {
		try {
			return this.mongoService.upload(file, collectionName, this.getSessionSystemUser());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"上传失败",e.getMessage());
		}
	}

	@RequestMapping(value = "/download.do")
	public  void download(HttpServletRequest request,  
            HttpServletResponse response, String collectionName,  
            String fileName,String originalFilename) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null; 
        this.logger.info(collectionName);
        this.logger.info(fileName);
        this.logger.info(originalFilename);
        GridFSDBFile file= mongoService.retrieveFileOne(collectionName, fileName);
        if(SnailUtils.isBlankString(originalFilename)){
        		originalFilename=fileName;
        }
        String contentType=fileName.substring(fileName.lastIndexOf("."));
        long fileLength =file.getLength();  
        response.setContentType(contentType);  
        response.setHeader("Content-disposition", "attachment; filename="+ new String(originalFilename.getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(fileLength));  
        bis = new BufferedInputStream(file.getInputStream());  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bis.close();  
        bos.close();  
    } 
}
