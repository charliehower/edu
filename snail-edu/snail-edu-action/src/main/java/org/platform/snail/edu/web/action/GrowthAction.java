package org.platform.snail.edu.web.action;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.GrowthService;
import org.platform.snail.edu.vo.GrowthQVo;
import org.platform.snail.utils.CommonKeys;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/growth")
public class GrowthAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GrowthService growthService;
	@RequestMapping(value = "/findGrowthList.do")
	@ResponseBody
	public  DataResponse findGrowthList(GrowthQVo condition,Page page){
		try{
			@SuppressWarnings("unchecked")
			Map<String,String> cfg=(Map<String,String>)this.getRequest().getSession().getServletContext().getAttribute(CommonKeys.cfg);
			condition.setYear(cfg.get("cyear"));
			DataResponse rst = this.growthService.findGrowthList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertGrowth.do")
	@ResponseBody
	public  DataResponse insertGrowth(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.growthService.insertGrowth(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateGrowth.do")
	@ResponseBody
	public  DataResponse updateGrowth(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.growthService.updateGrowth(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectGrowthByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectGrowthByPrimaryKey(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.growthService.selectGrowthByPrimaryKey(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteGrowthByGrowthId.do")
	@ResponseBody
	public  DataResponse deleteGrowthByGrowthId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String id=json.getString("id");
			return this.growthService.deleteGrowthByGrowthId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateGrowth.do")
	@ResponseBody
	public  DataResponse saveOrUpdateTeamPrepare(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.growthService.saveOrUpdateGrowth(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/importXls.do")
	@ResponseBody
	public  DataResponse importXls(@RequestParam MultipartFile[] file,String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.growthService.importXls(file, json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"导入失败！",e.getMessage());
		}
	}
}
