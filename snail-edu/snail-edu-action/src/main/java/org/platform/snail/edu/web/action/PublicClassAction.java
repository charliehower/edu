package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.PublicClass;
import org.platform.snail.edu.vo.PublicClassQVo;
import org.platform.snail.edu.service.PublicClassService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/publicClass")
public class PublicClassAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PublicClassService publicClassService;
	@RequestMapping(value = "/findPublicClassList.do")
	@ResponseBody
	public  DataResponse findPublicClassList(PublicClassQVo condition,Page page){
		try{
			DataResponse rst = this.publicClassService.findPublicClassList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertPublicClass.do")
	@ResponseBody
	public  DataResponse insertPublicClass(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.publicClassService.insertPublicClass(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updatePublicClass.do")
	@ResponseBody
	public  DataResponse updatePublicClass(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.publicClassService.updatePublicClass(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectPublicClassByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectPublicClassByPrimaryKey(String id){
		try{
			return this.publicClassService.selectPublicClassByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deletePublicClassByPublicClassId.do")
	@ResponseBody
	public  DataResponse deletePublicClassByPublicClassId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.publicClassService.deletePublicClassByPublicClassId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdatePublicClass.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.publicClassService.saveOrUpdatePublicClass(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateAudit.do")
	@ResponseBody
	public  DataResponse updateAudit(String id,String status,String remark){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.publicClassService.updateAudit(id, status,remark, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateRelease.do")
	@ResponseBody
	public  DataResponse updateRelease(String id,String status){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.publicClassService.updateRelease(id, status, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateScore.do")
	@ResponseBody
	public  DataResponse updateScore(String id,float score,String remark){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.publicClassService.updateScore(id, score, remark, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectListByid.do")
	@ResponseBody
	public  DataResponse selectListByid(String id){
		try{
			return this.publicClassService.selectListByid(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/insertJoin.do")
	@ResponseBody
	public  DataResponse insertJoin(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.publicClassService.insertJoin(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateAuditSec.do")
	@ResponseBody
	public  DataResponse updateAuditSec(String id,String status,String remark){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.publicClassService.updateAuditSec(id, status,remark, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateAuditThi.do")
	@ResponseBody
	public  DataResponse updateAuditThi(String id,String status,String remark){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.publicClassService.updateAuditThi(id, status,remark, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateAuditFor.do")
	@ResponseBody
	public  DataResponse updateAuditFor(String id,String status,String remark){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.publicClassService.updateAuditFor(id, status,remark, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"失败！",e.getMessage());
		}
	}
}
