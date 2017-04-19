package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.EntryService;
import org.platform.snail.edu.vo.EntryQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/entry")
public class EntryAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private EntryService entryService;
	@RequestMapping(value = "/findEntryList.do")
	@ResponseBody
	public  DataResponse findEntryList(EntryQVo condition,Page page){
		try{
			DataResponse rst = this.entryService.findEntryList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertEntry.do")
	@ResponseBody
	public  DataResponse insertEntry(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.entryService.insertEntry(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateEntry.do")
	@ResponseBody
	public  DataResponse updateEntry(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.entryService.updateEntry(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectEntryByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectEntryByPrimaryKey(String id){
		try{
			return this.entryService.selectEntryByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteEntryByEntryId.do")
	@ResponseBody
	public  DataResponse deleteEntryByEntryId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.entryService.deleteEntryByEntryId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/saveOrUpdateEntry.do")
	@ResponseBody
	public  DataResponse saveOrUpdateEntry(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.entryService.saveOrUpdateEntry(json,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
}
