package org.platform.snail.portal.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.ExcelMainMapper;
import org.platform.snail.portal.model.ExcelMain;
import org.platform.snail.portal.service.ExcelMainService;
import org.platform.snail.portal.vo.ExcelMainQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/excelMain")
public class ExcelMainAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ExcelMainService excelMainService;
	@Autowired
	private ExcelMainMapper excelMainMapper;
	@RequestMapping(value = "/findExcelMainList.do")
	@ResponseBody
	public  DataResponse findExcelMainList(ExcelMainQVo condition,Page page){
		try{
			DataResponse rst = this.excelMainService.findExcelMainList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertExcelMain.do")
	@ResponseBody
	public  DataResponse insertExcelMain(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.excelMainService.insertExcelMain(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateExcelMain.do")
	@ResponseBody
	public  DataResponse updateExcelMain(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.excelMainService.updateExcelMain(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectExcelMainByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectExcelMainByPrimaryKey(String id){
		try{
			return this.excelMainService.selectExcelMainByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteExcelMainByExcelMainId.do")
	@ResponseBody
	public  DataResponse deleteExcelMainByExcelMainId(String jsons){
		try{
			this.logger.info(jsons);
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			SystemUser systemUser =this.getSessionSystemUser();
			return this.excelMainService.deleteExcelMainByExcelMainId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/importXls.do")
	@ResponseBody
	public  DataResponse importXls(@RequestParam MultipartFile[] file,String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.excelMainService.importXls(file, json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"导入失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/exportXls.do")
	public  void exportXls(HttpServletRequest request,  
            HttpServletResponse response, String id,String action) throws Exception {  
		boolean exportAll=false;
		
		SystemUser systemUser =this.getSessionSystemUser();
		String userName=systemUser.getUsers().getName();
		if(action!=null&&action.equals("true")){
			exportAll=true;
			userName="全部";
		}
		ExcelMain o=excelMainMapper.selectByPrimaryKey(id);
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        String contentType=".xls";
        String originalFilename=o.getName()+new Date().getTime()+"("+userName+")"+contentType;
        response.setContentType(contentType);  
        this.logger.info(originalFilename);
        response.setHeader("Content-disposition", "attachment; filename="+ new String(originalFilename.getBytes("utf-8"), "ISO8859-1"));  
        //response.setHeader("Content-Length", String.valueOf(fileLength));   
        this.excelMainService.exportXls(response.getOutputStream(), id, exportAll,systemUser);
        response.flushBuffer();
    } 
}
