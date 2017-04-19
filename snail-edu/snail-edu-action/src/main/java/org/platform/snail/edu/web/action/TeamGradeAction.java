package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.TeamGradeService;
import org.platform.snail.edu.vo.TeamGradeQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/teamGrade")
public class TeamGradeAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TeamGradeService teamGradeService;
	@RequestMapping(value = "/findTeamGradeList.do")
	@ResponseBody
	public  DataResponse findTeamGradeList(TeamGradeQVo condition,Page page){
		try{
			DataResponse rst = this.teamGradeService.findTeamGradeList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertTeamGrade.do")
	@ResponseBody
	public  DataResponse insertTeamGrade(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teamGradeService.insertTeamGrade(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateTeamGrade.do")
	@ResponseBody
	public  DataResponse updateTeamGrade(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teamGradeService.updateTeamGrade(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectTeamGradeByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectTeamGradeByPrimaryKey(String id){
		try{
			return this.teamGradeService.selectTeamGradeByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteTeamGradeByTeamGradeId.do")
	@ResponseBody
	public  DataResponse deleteTeamGradeByTeamGradeId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.teamGradeService.deleteTeamGradeByTeamGradeId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateTeamGrade.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teamGradeService.saveOrUpdateTeamGrade(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
}
