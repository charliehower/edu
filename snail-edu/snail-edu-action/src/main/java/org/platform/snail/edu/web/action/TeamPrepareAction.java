package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.TeamPrepareService;
import org.platform.snail.edu.vo.TeamPrepareQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/teamPrepare")
public class TeamPrepareAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TeamPrepareService teamPrepareService;
	@RequestMapping(value = "/findTeamPrepareList.do")
	@ResponseBody
	public  DataResponse findTeamPrepareList(TeamPrepareQVo condition,Page page){
		try{
			DataResponse rst = this.teamPrepareService.findTeamPrepareList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertTeamPrepare.do")
	@ResponseBody
	public  DataResponse insertTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teamPrepareService.insertTeamPrepare(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateTeamPrepare.do")
	@ResponseBody
	public  DataResponse updateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teamPrepareService.updateTeamPrepare(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectTeamPrepareByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectTeamPrepareByPrimaryKey(String id){
		try{
			return this.teamPrepareService.selectTeamPrepareByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteTeamPrepareByTeamPrepareId.do")
	@ResponseBody
	public  DataResponse deleteTeamPrepareByTeamPrepareId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.teamPrepareService.deleteTeamPrepareByTeamPrepareId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateTeamPrepare.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.teamPrepareService.saveOrUpdateTeamPrepare(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
}
