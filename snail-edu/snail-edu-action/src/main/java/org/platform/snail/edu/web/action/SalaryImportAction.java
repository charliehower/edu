package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.SalaryImport;
import org.platform.snail.edu.vo.SalaryImportQVo;
import org.platform.snail.edu.service.SalaryImportService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/salaryImport")
public class SalaryImportAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SalaryImportService salaryImportService;
	@RequestMapping(value = "/findSalaryImportList.do")
	@ResponseBody
	public  DataResponse findSalaryImportList(SalaryImportQVo condition,Page page){
		try{
			DataResponse rst = this.salaryImportService.findSalaryImportList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertSalaryImport.do")
	@ResponseBody
	public  DataResponse insertSalaryImport(@RequestParam MultipartFile[] file,String jsons){
		this.logger.info(jsons);
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.salaryImportService.insertSalaryImport(file,json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/deleteSalaryImportBySalaryImportId.do")
	@ResponseBody
	public  DataResponse deleteSalaryImportBySalaryImportId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.salaryImportService.deleteSalaryImportBySalaryImportId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	
}
