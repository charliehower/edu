package org.platform.snail.portal.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.model.Department;
import org.platform.snail.portal.service.DepartmentService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/department")
public class DepartmentAction extends BaseController{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DepartmentService departmentService;
	@RequestMapping(value = "/findDepartmentList.do")
	@ResponseBody
	public DataResponse findDepartmentList(Department condition,Page page){
		DataResponse rst;
		try {
			rst = this.departmentService.findDepartmentList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
		
		return rst;
	}
	@RequestMapping(value = "/insertDepartment.do", method = RequestMethod.POST)
	@ResponseBody
	public DataResponse insertDepartment(String jsons){
		
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.departmentService.insertDepartment(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/updateDepartment.do", method = RequestMethod.POST)
	@ResponseBody
	public DataResponse updateDepartment(String jsons){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return this.departmentService.updateDepartment(json, systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/updateDepartmentStautsByPrimaryKey.do", method = RequestMethod.POST)
	@ResponseBody
	public DataResponse updateDepartmentStautsByPrimaryKey(String jsons){
		
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String departmentId=json.getString("id");
			String stauts="0";
			if(json.containsKey("stauts")){
				stauts=json.getString("stauts");
			}
			return  this.departmentService.updateDepartmentStautsByPrimaryKey(departmentId,stauts,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/selectDepartmentByPrimaryKey.do")
	@ResponseBody
	public DataResponse selectDepartmentByPrimaryKey(String departmentId){
		try{
			return  this.departmentService.selectDepartmentByPrimaryKey(departmentId);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/selectDepartmentTreeList.do")
	@ResponseBody
	public List<Tree> selectDepartmentTreeList()throws Exception {
		List<Tree> list=this.departmentService.selectDepartmentTreeList();
		return list;
	}
	@RequestMapping(value = "/delDepartmentByPrimaryKey.do", method = RequestMethod.POST)
	@ResponseBody
	public DataResponse delDepartmentByPrimaryKey(String jsons){
		
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			String departmentId=json.getString("id");
			return  this.departmentService.delDepartmentByPrimaryKey(departmentId,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败，请核实数据是否已经外键关联！",e.getMessage());
		}
	}
	@RequestMapping(value = "/selectUsersListByDepartmentId.do")
	@ResponseBody
	public DataResponse selectUsersListByDepartmentId(String departmentId){
		try{
			return  this.departmentService.selectUsersListByDepartmentId(departmentId);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"查询部门下的人员失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/batchUpdateUserAndTeacherByUserIds.do")
	@ResponseBody
	public DataResponse batchUpdateUserAndTeacherByUserIds(String jsons){
		this.logger.info(jsons);
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			JSONObject json=JSONObject.fromObject(jsons);
			return  this.departmentService.batchUpdateUserAndTeacherByUserIds(json,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"更新部门的人员失败！",e.getMessage());
		}
	}
	public static void main(String args[]){
		String jsons="{\"departmentId\":\"01\",\"list\":[\"T10020955\",\"4403072021\"]}";
		JSONObject json=JSONObject.fromObject(jsons);
		System.out.println(json.toString());
	}
}
