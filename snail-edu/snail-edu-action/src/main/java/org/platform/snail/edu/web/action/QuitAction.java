package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.QuitService;
import org.platform.snail.edu.vo.QuitQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/quit")
public class QuitAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QuitService quitService;
	@RequestMapping(value = "/findQuitList.do")
	@ResponseBody
	public  DataResponse findQuitList(QuitQVo condition,Page page){
		try{
			DataResponse rst = this.quitService.findQuitList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertQuit.do")
	@ResponseBody
	public  DataResponse insertQuit(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.quitService.insertQuit(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateQuit.do")
	@ResponseBody
	public  DataResponse updateQuit(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.quitService.updateQuit(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectQuitByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectQuitByPrimaryKey(String id){
		try{
			return this.quitService.selectQuitByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteQuitByQuitId.do")
	@ResponseBody
	public  DataResponse deleteQuitByQuitId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.quitService.deleteQuitByQuitId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/isExitByTeacherId.do")
	@ResponseBody
	public  DataResponse isExitByTeacherId(){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.quitService.isExitByTeacherId(systemUser.getUsers().getUserId());
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
}
