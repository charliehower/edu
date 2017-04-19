package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.GradeService;
import org.platform.snail.edu.vo.GradeQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/grade")
public class GradeAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GradeService gradeService;
	@RequestMapping(value = "/findGradeList.do")
	@ResponseBody
	public  DataResponse findGradeList(GradeQVo condition,Page page){
		try{
			DataResponse rst = this.gradeService.findGradeList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertGrade.do")
	@ResponseBody
	public  DataResponse insertGrade(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.gradeService.insertGrade(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateGrade.do")
	@ResponseBody
	public  DataResponse updateGrade(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.gradeService.updateGrade(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectGradeByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectGradeByPrimaryKey(String id){
		try{
			return this.gradeService.selectGradeByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteGradeByGradeId.do")
	@ResponseBody
	public  DataResponse deleteGradeByGradeId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.gradeService.deleteGradeByGradeId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateUpgrade.do")
	@ResponseBody
	public  DataResponse updateUpgrade(String year){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.gradeService.updateUpgrade(year,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"升级失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateunDoUpgrade.do")
	@ResponseBody
	public  DataResponse updateunDoUpgrade(String year){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.gradeService.updateunDoUpgrade(year,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"升级失败！",e.getMessage());
		}
	}
}
