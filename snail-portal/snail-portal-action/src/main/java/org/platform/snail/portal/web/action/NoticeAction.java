package org.platform.snail.portal.web.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.service.NoticeService;
import org.platform.snail.portal.vo.NoticeQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/notice")
public class NoticeAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private NoticeService noticeService;
	@RequestMapping(value = "/findNoticeList.do")
	@ResponseBody
	public  DataResponse findNoticeList(NoticeQVo condition,Page page){
		try{
			DataResponse rst = this.noticeService.findNoticeList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertNotice.do")
	@ResponseBody
	public  DataResponse insertNotice(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.noticeService.insertNotice(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateNotice.do")
	@ResponseBody
	public  DataResponse updateNotice(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.noticeService.updateNotice(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectNoticeByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectNoticeByPrimaryKey(String id){
		try{
			return this.noticeService.selectNoticeByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteNoticeByNoticeId.do")
	@ResponseBody
	public  DataResponse deleteNoticeByNoticeId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.noticeService.deleteNoticeByNoticeId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateNotice.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.noticeService.saveOrUpdateNotice(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateForTopByPrimaryKey.do")
	@ResponseBody
	public  DataResponse updateForTopByPrimaryKey(String noticeId){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.noticeService.updateForTopByPrimaryKey(noticeId,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"置顶失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateForStatusByPrimaryKey.do")
	@ResponseBody
	public  DataResponse updateForStatusByPrimaryKey(HttpServletRequest request,String noticeId,String status,String departmentId,String groupId){
		try{
			String contextPath=request.getContextPath();
			SystemUser systemUser =this.getSessionSystemUser();
			return this.noticeService.updateForStatusByPrimaryKey(noticeId,status,departmentId,groupId,contextPath,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"操作失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/findListTop.do")
	@ResponseBody
	public  DataResponse findListTop(){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			DataResponse rst = this.noticeService.findListTop(systemUser);
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
}
