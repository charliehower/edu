package org.platform.snail.edu.web.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.DwmReport;
import org.platform.snail.edu.vo.DwmReportQVo;
import org.platform.snail.edu.service.DwmReportService;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/dwmReport")
public class DwmReportAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DwmReportService dwmReportService;
	@RequestMapping(value = "/findDwmReportList.do")
	@ResponseBody
	public  DataResponse findDwmReportList(DwmReportQVo condition,Page page){
		try{
			DataResponse rst = this.dwmReportService.findDwmReportList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertDwmReport.do")
	@ResponseBody
	public  DataResponse insertDwmReport(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.dwmReportService.insertDwmReport(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateDwmReport.do")
	@ResponseBody
	public  DataResponse updateDwmReport(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.dwmReportService.updateDwmReport(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectDwmReportByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectDwmReportByPrimaryKey(String id){
		try{
			return this.dwmReportService.selectDwmReportByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteDwmReportByDwmReportId.do")
	@ResponseBody
	public  DataResponse deleteDwmReportByDwmReportId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.dwmReportService.deleteDwmReportByDwmReportId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateDwmReport.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.dwmReportService.saveOrUpdateDwmReport(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateForTopByPrimaryKey.do")
	@ResponseBody
	public  DataResponse updateForTopByPrimaryKey(String dwmReportId){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.dwmReportService.updateForTopByPrimaryKey(dwmReportId,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"置顶失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateForStatusByPrimaryKey.do")
	@ResponseBody
	public  DataResponse updateForStatusByPrimaryKey(HttpServletRequest request,String dwmReportId,String status,String departmentId,String groupId){
		try{
			String contextPath=request.getContextPath();
			SystemUser systemUser =this.getSessionSystemUser();
			return this.dwmReportService.updateForStatusByPrimaryKey(dwmReportId,status,departmentId,groupId,contextPath,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"操作失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/findListTop.do")
	@ResponseBody
	public  DataResponse findListTop(String categoryId,String limit){
		this.logger.info("limit:"+limit);
		int limits=0;
		try{
			limits=SnailUtils.parseInt(limit);
			if(limits==0){
				limits=8;
			}
			SystemUser systemUser =this.getSessionSystemUser();
			DataResponse rst = this.dwmReportService.findListTop(categoryId,limits,systemUser);
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/getReportByCategoryIdAndTime.do")
	@ResponseBody
	public  DataResponse getReportByCategoryIdAndTime(DwmReportQVo condition){
		try{
			return this.dwmReportService.getReportByCategoryIdAndTime(condition);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/getViewerByDwrReportId.do")
	@ResponseBody
	public DataResponse getViewerByDwrReportId(String dwmReportId) {
		try{
			return this.dwmReportService.getViewerByDwrReportId(dwmReportId);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/selectMyDeptUser.do")
	@ResponseBody
	public List<Map<String, Object>> selectMyDeptUser(String dwmReportId) {
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.dwmReportService.selectMyDeptUser(systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return null;
		}
	}
	
}
