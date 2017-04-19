package org.platform.snail.portal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.model.Department;
import org.platform.snail.portal.dao.DepartmentDao;
import org.platform.snail.portal.service.DepartmentService;
import org.platform.snail.portal.vo.DepartmentVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CommonTreeUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	
	public DataResponse findDepartmentList(Department condition, int start, int limit, String orderBy) throws Exception{
		DataResponse rst=new DataResponse();
		List<DepartmentVo> list = this.departmentDao.findDepartmentList(condition, start, limit,orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.departmentDao.findDepartmentCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}
	public  DataResponse insertDepartment(JSONObject josnObject,SystemUser systemUser) throws Exception{
		DataResponse rst=new DataResponse();
		Department department=new Department();
		SnailBeanUtils.copyProperties(department, josnObject);
		if(SnailUtils.isBlankString(department.getParentDepartmentId())){
			return new DataResponse(false,"上级部门编号不能为空！");
		}
		if(SnailUtils.isBlankString(department.getDepartmentName())){
			return new DataResponse(false,"部门名称不能为空！");
		}
		if(department.getDepartmentLevel()==null){
			return new DataResponse(false,"部门级别不能为空！");
		}
		if(SnailUtils.isBlankString(department.getUserId())){
			return new DataResponse(false,"部门负责人不能为空！");
		}
		if(departmentDao.isExitByUserId(department.getUserId())<1){
			return new DataResponse(false,"不能直接输入姓名，请检索后选择部门负责人！");
		}
		department.setCreateUserId(systemUser.getUsers().getUserId());
		department.setStauts("1");
		department.setCreateTime(new Date());
		this.departmentDao.insertDepartment(department);
		this.dataBaseLogService.log("添加新部门", "部门", "", department.getDepartmentName(), department.getDepartmentName(), systemUser);
		return  new DataResponse(true,"添加新部门完成！");
	}
	public  DataResponse updateDepartment(JSONObject josnObject,SystemUser systemUser) throws Exception{
		
		Department department=new Department();
		SnailBeanUtils.copyProperties(department, josnObject);	
		if(SnailUtils.isBlankString(department.getDepartmentId())){
			return new DataResponse(false,"部门编号不能为空！");
		}
		if(SnailUtils.isBlankString(department.getParentDepartmentId())){
			return new DataResponse(false,"上级部门编号不能为空！");
		}
		if(SnailUtils.isBlankString(department.getDepartmentName())){
			return new DataResponse(false,"部门名称不能为空！");
		}
		if(department.getDepartmentLevel()==null){
			return new DataResponse(false,"部门级别不能为空！");
		}
		if(SnailUtils.isBlankString(department.getUserId())){
			return new DataResponse(false,"部门负责人不能为空！");
		}
		if(departmentDao.isExitByUserId(department.getUserId())<1){
			return new DataResponse(false,"不能直接输入姓名，请检索后选择部门负责人！");
		}
			department.setCreateUserId(systemUser.getUsers().getUserId());
			//department.setStauts("1");
			department.setCreateTime(new Date());
			this.departmentDao.updateDepartmentByPrimaryKey(department);
			this.dataBaseLogService.log("部门信息变更", "部门", "", department.getDepartmentName(), department.getDepartmentId(), systemUser);
		return new DataResponse(true,"部门信息变更完成！");
	}
	public  DataResponse updateDepartmentStautsByPrimaryKey(String departmentId,String struts,SystemUser systemUser) throws Exception{
		DataResponse rst=new DataResponse();
		if(SnailUtils.isBlankString(departmentId)){
			return new DataResponse(false,"部门编号不能为空！");
		}
		if(SnailUtils.isBlankString(departmentId)){
			return new DataResponse(false,"部门状态不能为空！");
		}
		this.departmentDao.updateDepartmentStautsByPrimaryKey(departmentId, struts);
		this.dataBaseLogService.log("部门状态变更", "部门状态", "",struts, departmentId, systemUser);
		return new DataResponse(true,"部门状态变更完成！");
	}
	public  DataResponse selectDepartmentByPrimaryKey(String departmentId) throws Exception{
		DataResponse rst=new DataResponse();
		DepartmentVo departmentVo=this.departmentDao.selectDepartmentVoByPrimaryKey(departmentId);
		rst.setResponse(departmentVo);
		rst.setState(true);
		return rst;
	}
	public List<Tree> selectDepartmentTreeList() throws Exception{
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.departmentDao.selectDepartmentTreeList());
		return commonTreeUtils.getTreeList("0");
	}
	
	public  DataResponse delDepartmentByPrimaryKey(String departmentId,SystemUser systemUser) throws Exception{
		if(SnailUtils.isBlankString(departmentId)){
			return new DataResponse(false,"部门编号不能为空！");
		}
		this.departmentDao.delDepartmentByPrimaryKey(departmentId);
		this.dataBaseLogService.log("部门删除", "部门", "","", departmentId, systemUser);
		return new DataResponse(true,"部门删除完成！");
	}
	public  DataResponse selectUsersListByDepartmentId(String departmentId) throws Exception{
		DataResponse rst=new DataResponse();
		if(SnailUtils.isBlankString(departmentId)){
			return new DataResponse(false,"部门编号不能为空！");
		}
		List<Map<String,String>> list=this.departmentDao.selectUsersListByDepartmentId(departmentId);
		rst.setList(list);
		return rst;
	}
	
	public  DataResponse batchUpdateUserAndTeacherByUserIds(JSONObject json,SystemUser systemUser) throws Exception{
		String departmentId=json.getString("departmentId");
		if(SnailUtils.isBlankString(departmentId)){
			return new DataResponse(false,"部门编号不能为空！");
		}
		List<String> list=new ArrayList<String>();
		JSONArray jsons=json.getJSONArray("list");
		for(Object obj:jsons){
			list.add(obj.toString());
		}
		this.logger.info(departmentId);
		this.logger.info(json);
		this.departmentDao.batchUpdateUserAndTeacherByUserIds(list, departmentId);
		this.dataBaseLogService.log("部门分配人员", "部门", "","", departmentId, systemUser);
		return new DataResponse(true,"部门分配人员完成！");
	}
}
