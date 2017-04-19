package org.platform.snail.portal.web.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.service.ScheduleService;
import org.platform.snail.portal.vo.ScheduleQVo;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/schedule")
public class ScheduleAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ScheduleService scheduleService;
	@RequestMapping(value = "/findScheduleList.do")
	@ResponseBody
	public  DataResponse findScheduleList(Page page,String userId){
		try{
			ScheduleQVo condition=new ScheduleQVo();
			SystemUser systemUser =this.getSessionSystemUser();
			condition.setUserId(userId);
			if(SnailUtils.isBlankString(userId)){
				condition.setUserId(systemUser.getUsers().getUserId());
			}
			DataResponse rst = this.scheduleService.findScheduleList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertSchedule.do")
	@ResponseBody
	public  DataResponse insertSchedule(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.scheduleService.insertSchedule(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateSchedule.do")
	@ResponseBody
	public  DataResponse updateSchedule(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.scheduleService.updateSchedule(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectScheduleByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectScheduleByPrimaryKey(String id){
		try{
			return this.scheduleService.selectScheduleByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteScheduleByScheduleId.do")
	@ResponseBody
	public  DataResponse deleteScheduleByScheduleId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.scheduleService.deleteScheduleByScheduleId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectDepUserListByDepId.do")
	@ResponseBody
	public  DataResponse selectDepUserListByDepId(){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			DataResponse rst=new DataResponse();
			rst.setList(this.scheduleService.selectDepUserListByDepId(systemUser.getUsers().getDepartmentId()));
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
}
