package org.platform.snail.portal.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.model.DictCategory;
import org.platform.snail.portal.service.DictCategoryService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/dictCategory")
public class DictCategoryAction extends BaseController{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DictCategoryService dictCategoryService;
	@RequestMapping(value = "/findDictCategoryList.do")
	@ResponseBody
	public DataResponse findDictCategoryList(DictCategory condition,Page page){
		try{
			DataResponse rst = this.dictCategoryService.findDictCategoryList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
		
	}
	@RequestMapping(value = "/insertDictCategory.do")
	@ResponseBody
	public DataResponse insertDictCategory(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject josn=JSONObject.fromObject(jsons);
			return   this.dictCategoryService.insertDictCategory(josn, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateDictCategory.do")
	@ResponseBody
	public DataResponse updateDictCategory(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject josn=JSONObject.fromObject(jsons);
			return  this.dictCategoryService.updateDictCategory(josn, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"变更失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/selectDictCategoryByPrimaryKey.do")
	@ResponseBody
	public DataResponse selectDictCategoryByPrimaryKey(String dictCategoryId){
		try{
			return   this.dictCategoryService.selectDictCategoryByPrimaryKey(dictCategoryId);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteDictCategoryByDictCategoryId.do")
	@ResponseBody
	public DataResponse deleteDictCategoryByDictCategoryId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String dictCategoryId=json.getString("id");
			return   this.dictCategoryService.deleteDictCategoryByDictCategoryId(dictCategoryId,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/findDictCategoryListAll.do")
	@ResponseBody
	public List<DictCategory> findDictCategoryListAll(String jsons){
		try{
			return   this.dictCategoryService.findDictCategoryListAll();
		}catch(Exception e){
			this.logger.error(e);
			return null;
		}
	}
}
