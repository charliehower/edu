package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.RehireService;
import org.platform.snail.edu.vo.RehireQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/rehire")
public class RehireAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RehireService rehireService;
	@RequestMapping(value = "/findRehireList.do")
	@ResponseBody
	public  DataResponse findRehireList(RehireQVo condition,Page page){
		try{
			DataResponse rst = this.rehireService.findRehireList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/insertRehire.do")
	@ResponseBody
	public  DataResponse insertRehire(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.rehireService.insertRehire(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateRehire.do")
	@ResponseBody
	public  DataResponse updateRehire(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.rehireService.updateRehire(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectRehireByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectRehireByPrimaryKey(String id){
		try{
			return this.rehireService.selectRehireByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteRehireByRehireId.do")
	@ResponseBody
	public  DataResponse deleteRehireByRehireId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.rehireService.deleteRehireByRehireId(id,systemUser);
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
			return this.rehireService.isExitByTeacherId(systemUser.getUsers().getUserId());
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
}
