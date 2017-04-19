package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.TeacherService;
import org.platform.snail.edu.vo.TeacherQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/teacher")
public class TeacherAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TeacherService teacherService;
	
	
	
	@RequestMapping(value = "/findTeacherList.do")
	@ResponseBody
	public  DataResponse findTeacherList(TeacherQVo condition,Page page){
		try{
			DataResponse rst = this.teacherService.findTeacherList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertTeacher.do")
	@ResponseBody
	public  DataResponse insertTeacher(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teacherService.insertTeacher(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateTeacher.do")
	@ResponseBody
	public  DataResponse updateTeacher(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teacherService.updateTeacher(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectTeacherByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectTeacherByPrimaryKey(String id){
		try{
			return this.teacherService.selectTeacherByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteTeacherByTeacherId.do")
	@ResponseBody
	public  DataResponse deleteTeacherByTeacherId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.teacherService.deleteTeacherByTeacherId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败,请核是否有关联的数据存在！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateUsersByTeacherId.do")
	@ResponseBody
	public  DataResponse saveOrUpdateUsersByTeacherId(String teacherId){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.teacherService.saveOrUpdateUsersByTeacherId(teacherId,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"同步教职工系统信息失败!",e.getMessage());
		}
	}
	@RequestMapping(value = "/createUsersByTeacherIds.do")
	@ResponseBody
	public  DataResponse createUsersByTeacherIds(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teacherService.createUsersByTeacherIds(json,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"同步教职工系统信息失败!",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateGroup.do")
	@ResponseBody
	public  DataResponse updateGroup(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teacherService.updateGroup(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/importTeacher.do")
	@ResponseBody
	public  DataResponse importTeacher(@RequestParam MultipartFile[] file){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.teacherService.importTeacher(file, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"导入失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateQuitByIdTeacherId.do")
	@ResponseBody
	public  DataResponse updateQuitByIdTeacherId(String teacherId){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.teacherService.updateQuitByIdTeacherId(teacherId, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"离职失败！",e.getMessage());
		}
	}
	
}
