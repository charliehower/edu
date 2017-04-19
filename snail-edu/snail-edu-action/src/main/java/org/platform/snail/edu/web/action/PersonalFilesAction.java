/**
 * @Title: PersonalFilesAction.java
 * @Package org.platform.snail.edu.web.action
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author Comsys-chenxiaoke
 * @date 2014年12月14日 下午5:42:12
 * @version V1.0
 */

package org.platform.snail.edu.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.PersonalFiles;
import org.platform.snail.edu.service.PersonalFilesService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: PersonalFilesAction
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月14日 下午5:42:12
 *
 */
@Controller
@RequestMapping("/personalFiles")
public class PersonalFilesAction extends BaseController {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PersonalFilesService personalFilesService;
	@RequestMapping(value = "/findPersonalFilesListByTeacherId.do")
	@ResponseBody
	Map<String,Object> findPersonalFilesListByTeacherId(String teacherId){
		try{
			List<PersonalFiles> list =this.personalFilesService.findPersonalFilesListByTeacherId(teacherId);
			Map<String,Object> rst=new HashMap<String,Object>();
			rst.put("total", list.size());
			rst.put("rows", list);
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return null;
		}
	}
	@RequestMapping(value = "/insertPersonalFiles.do")
	@ResponseBody
	public  DataResponse insertPersonalFiles(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.personalFilesService.insertPersonalFiles(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updatePersonalFiles.do")
	@ResponseBody
	public  DataResponse updatePersonalFiles(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.personalFilesService.updatePersonalFiles(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectPersonalFilesByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectPersonalFilesByPrimaryKey(String id){
		try{
			return this.personalFilesService.selectPersonalFilesByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deletePersonalFilesByPersonalFilesId.do")
	@ResponseBody
	public  DataResponse deletePersonalFilesByPersonalFilesId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.personalFilesService.deletePersonalFilesByPersonalFilesId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public DataResponse uploadFile(@RequestParam MultipartFile[] file,String category,String teacherId, String collectionName)
			throws Exception {
		try {
			return this.personalFilesService.upload(file,category,teacherId, collectionName, this.getSessionSystemUser());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"上传失败",e.getMessage());
		}
	}


}
