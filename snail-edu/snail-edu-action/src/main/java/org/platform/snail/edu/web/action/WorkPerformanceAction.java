package org.platform.snail.edu.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.model.WorkPerformance;
import org.platform.snail.edu.service.WorkPerformanceService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/workPerformance")
public class WorkPerformanceAction extends BaseController {
	
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private WorkPerformanceService workPerformanceService;
	@RequestMapping(value = "/findWorkPerformanceListByTeacherId.do")
	@ResponseBody
	Map<String,Object> findWorkPerformanceListByTeacherId(String teacherId){
		try{
			List<WorkPerformance> list= this.workPerformanceService.findWorkPerformanceListByTeacherId(teacherId);
			Map<String,Object> rst=new HashMap<String,Object>();
			rst.put("total", list.size());
			rst.put("rows", list);
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return null;
		}
	}
	
	@RequestMapping(value = "/saveOrUpdateWorkPerformance.do")
	@ResponseBody
	DataResponse saveOrUpdateWorkPerformance(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.workPerformanceService.saveOrUpdateWorkPerformance(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"保存失败！",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/selectWorkPerformanceByPrimaryKey.do")
	@ResponseBody
	public  DataResponse selectWorkPerformanceByPrimaryKey(String id){
		try{
			return this.workPerformanceService.selectWorkPerformanceByPrimaryKey(id);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"加载失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/deleteWorkPerformanceByWorkPerformanceId.do")
	@ResponseBody
	public  DataResponse deleteTeacherByTeacherId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.workPerformanceService.deleteWorkPerformanceByWorkPerformanceId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
}
