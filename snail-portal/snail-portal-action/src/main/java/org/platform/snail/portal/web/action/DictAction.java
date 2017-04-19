package org.platform.snail.portal.web.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.model.Dict;
import org.platform.snail.portal.service.DictService;
import org.platform.snail.utils.CommonKeys;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/dict")
public class DictAction extends BaseController{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DictService dictService;
	@RequestMapping(value = "/findDictList.do")
	@ResponseBody
	public DataResponse findDictList(Dict condition,Page page){
		try{
			DataResponse rst = this.dictService.findDictList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
		
	}
	@RequestMapping(value = "/insertDict.do")
	@ResponseBody
	public DataResponse insertDict(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject josn=JSONObject.fromObject(jsons);
			DataResponse rst=   this.dictService.insertDict(josn, systemUser);
			
			
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/updateDict.do")
	@ResponseBody
	public DataResponse updateDict(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject josn=JSONObject.fromObject(jsons);
			return  this.dictService.updateDict(josn, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"变更失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/selectDictByPrimaryKey.do")
	@ResponseBody
	public DataResponse selectDictByPrimaryKey(int id){
		try{
			return   this.dictService.selectDictByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteDictByDictId.do")
	@ResponseBody
	public DataResponse deleteDictByDictId(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			int id=json.getInt("id");
			return   this.dictService.deleteDictByDictId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/findListByCategoryId.do")
	@ResponseBody
	public List<Dict> findListByCategoryId(String categoryId,String selected,HttpServletRequest request){
		try{
			Map<String, Object> params = new HashMap<String, Object>();  
			Enumeration e=request.getParameterNames();
			while(e.hasMoreElements()){
				String key=(String)e.nextElement();
				String value=request.getParameter(key);
				params.put(key, value);
			}
			return   this.dictService.findListByCategoryId(categoryId,selected,params);
		}catch(Exception e){
			this.logger.error(e);
		}
		return null;
	}
	@RequestMapping(value = "/deploy.do")
	@ResponseBody
	public DataResponse deploy(){
		try{
			dictService.flushJavaScriptFile(
					this.getRequest().getSession().getServletContext().getRealPath("/"), CommonKeys.dictPath,
					this.getRequest().getSession().getServletContext());
			return  new DataResponse(false,"发布成功！");
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	
}
