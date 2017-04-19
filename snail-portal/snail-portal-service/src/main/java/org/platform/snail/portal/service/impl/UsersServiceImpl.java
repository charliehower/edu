package org.platform.snail.portal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.AceTree;
import org.platform.snail.beans.AdditionalParameters;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.model.Role;
import org.platform.snail.model.Users;
import org.platform.snail.portal.dao.UsersDao;
import org.platform.snail.portal.service.UsersService;
import org.platform.snail.portal.vo.UsersVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CommonAceTreeUtils;
import org.platform.snail.utils.CommonTreeUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public DataResponse findUsersSearchList(Users condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<Map<String, String>> list = this.usersDao.findUsersSearchList(
				condition, start,limit, orderBy);
		rst.setList(list);
		int allRows = this.usersDao.findUsersSearchCount(condition);
		rst.setAllRows(allRows);
		return rst;
	}

	public DataResponse findUsersList(Users condition, int start, int limit,
			String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<UsersVo> list = this.usersDao.findUsersList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.usersDao.findUsersCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertUsers(JSONObject josnObject, SystemUser systemUser)
			throws Exception {

		Users users = new Users();
		SnailBeanUtils.copyProperties(users, josnObject);
		if (SnailUtils.isBlankString(users.getDepartmentId())) {

			return new DataResponse(false, "所属部门不能为空!");
		}
		if (SnailUtils.isBlankString(users.getAccount())) {

			return new DataResponse(false, "账户不能为空!");
		}
		if (this.usersDao.isExitUsersAccount(users.getAccount()) > 0) {

			return new DataResponse(false, "账户已存在!");
		}
		if (SnailUtils.isBlankString(users.getPassword())) {

			return new DataResponse(false, "密码不能为空!");
		}

		if (SnailUtils.isBlankString(users.getSex())) {

			return new DataResponse(false, "性别不能为空!");
		}

		/*if (SnailUtils.isBlankString(users.getIdCard())) {

			return new DataResponse(false, "身份证号不能为空!");
		}
		if (!SnailUtils.isValidIdCard(users.getIdCard())) {

			return new DataResponse(false, "身份证号格式不正确!");
		}*/
		if (SnailUtils.isBlankString(users.getName())) {

			return new DataResponse(false, "用户名不能为空!");
		}

		if (SnailUtils.isBlankString(users.getAreaCode())) {

			return new DataResponse(false, "所属地区不能为空!");
		}

		if (users.getBirthday() == null) {

			return new DataResponse(false, "出生日期不能为空!");
		}

		if (SnailUtils.isBlankString(users.getMobile())) {

			return new DataResponse(false, "手机号不能为空!");
		}
		if (!SnailUtils.isValidMobile(users.getMobile())) {

			return new DataResponse(false, "手机号格式不正确!");
		}

		if (SnailUtils.isBlankString(users.getEmail())) {

			return new DataResponse(false, "电子邮箱不能为空!");
		}

		if (!SnailUtils.isValidEmail(users.getEmail())) {

			return new DataResponse(false, "电子邮箱格式不正确!");
		}
		if (this.usersDao.isExitUsersByEmail(users.getEmail()) > 0) {

			return new DataResponse(false, "电子邮箱已经被注册使用!");
		}
		if (!SnailUtils.isBlankString(users.getUserLevel())) {
			if (!SnailUtils.isDouble(users.getUserLevel())) {

				return new DataResponse(false, "用户级别不是数字!");
			}
		}

		users.setStauts("1");
		users.setCreateTime(new Date());
		users.setPassword(SnailUtils.getMd5(users.getPassword()));
		this.usersDao.insertUsers(users);
		this.dataBaseLogService.log("添加新用户", "用户", users.getAccount(),
				users.getName(), "01", systemUser);
		return new DataResponse(true, "添加新用户完成,请分配角色！");
	}

	public DataResponse updateUsers(JSONObject josnObject, SystemUser systemUser)
			throws Exception {

		Users users = new Users();
		SnailBeanUtils.copyProperties(users, josnObject);
		if (SnailUtils.isBlankString(users.getUserId())) {

			return new DataResponse(false, "用户编号不能为空!");
		}

		if (SnailUtils.isBlankString(users.getDepartmentId())) {

			return new DataResponse(false, "所属部门不能为空!");
		}
		if (SnailUtils.isBlankString(users.getAccount())) {

			return new DataResponse(false, "账户不能为空!");
		}

		if (SnailUtils.isBlankString(users.getPassword())) {

			return new DataResponse(false, "密码不能为空!");
		}

		if (SnailUtils.isBlankString(users.getSex())) {

			return new DataResponse(false, "性别不能为空!");
		}

		/*if (SnailUtils.isBlankString(users.getIdCard())) {

			return new DataResponse(false, "身份证号不能为空!");
		}
		if (!SnailUtils.isValidIdCard(users.getIdCard())) {

			return new DataResponse(false, "身份证号格式不正确!");
		}*/
		if (SnailUtils.isBlankString(users.getName())) {

			return new DataResponse(false, "用户名不能为空!");
		}

		if (SnailUtils.isBlankString(users.getAreaCode())) {

			return new DataResponse(false, "所属地区不能为空!");
		}

		

		if (SnailUtils.isBlankString(users.getMobile())) {

			return new DataResponse(false, "手机号不能为空!");
		}
		if (!SnailUtils.isValidMobile(users.getMobile())) {

			return new DataResponse(false, "手机号格式不正确!");
		}

		if (SnailUtils.isBlankString(users.getEmail())) {

			return new DataResponse(false, "电子邮箱不能为空!");
		}
		if (!SnailUtils.isValidEmail(users.getEmail())) {

			return new DataResponse(false, "电子邮箱格式不正确!");
		}
		if (!SnailUtils.isBlankString(users.getUserLevel())) {
			if (!SnailUtils.isDouble(users.getUserLevel())) {

				return new DataResponse(false, "用户级别不是数字!");
			}
		}
		if (!SnailUtils.isBlankString(users.getUserLevel())) {
			if (!SnailUtils.isDouble(users.getUserLevel())) {

				return new DataResponse(false, "用户级别不是数字!");
			}
		}
		if (users.getPassword().length() < 20) {
			users.setPassword(SnailUtils.getMd5(users.getPassword()));
		}

		this.usersDao.updateUsersByPrimaryKey(users);
		this.dataBaseLogService.log("用户信息变更", "用户", users.getUserId(),
				users.getName(), users.getUserId(), systemUser);
		return new DataResponse(true, "用户信息变更完成！");
	}

	public DataResponse updateUsersStautsByPrimaryKey(String usersId,
			String struts, SystemUser systemUser) throws Exception {
		if (SnailUtils.isBlankString(usersId)) {

			return new DataResponse(false, "用户编号不能为空！");
		}
		if (SnailUtils.isBlankString(usersId)) {

			return new DataResponse(false, "用户状态不能为空！");
		}
		this.usersDao.updateUsersStautsByPrimaryKey(usersId, struts);
		this.dataBaseLogService.log("用户状态变更", "用户状态", "", struts, usersId,
				systemUser);
		return new DataResponse(true, "用户状态变更完成！");
	}

	public DataResponse updateUsersForInitPassword(String usersId,
			String password, SystemUser systemUser) throws Exception {
		if (SnailUtils.isBlankString(usersId)) {

			return new DataResponse(false, "用户编号不能为空！");
		}
		if (SnailUtils.isBlankString(password)) {

			return new DataResponse(false, "密码不能为空！");
		}
		password = SnailUtils.getMd5(password);
		this.usersDao.updateUsersForInitPassword(usersId, password);
		this.dataBaseLogService.log("用户初始化密码", "用户密码", "", password,
				usersId, systemUser);
		return new DataResponse(true, "用户初始化密码完成！");
	}

	public DataResponse selectUsersByPrimaryKey(String usersId)
			throws Exception {
		DataResponse rst = new DataResponse();
		UsersVo usersVo = this.usersDao.selectUsersVoByPrimaryKey(usersId);
		rst.setResponse(usersVo);
		return rst;
	}

	public DataResponse insertUsersRole(String userId, String[] roleId,
			SystemUser systemUser) throws Exception {
		this.usersDao.insertUsersRole(userId, roleId);
		this.dataBaseLogService.log("用户分配角色", "分配角色", "", "", userId,
				systemUser);
		return new DataResponse(true, "角色分配完成！");
	}

	public DataResponse selectRoleList() throws Exception {
		DataResponse rst = new DataResponse();
		List<Role> list = this.usersDao.selectRoleList();
		rst.setList(list);
		if (list != null) {
			rst.setAllRows(list.size());
		}
		return rst;
	}

	public DataResponse selectRoleListByUserId(String userId) throws Exception {
		DataResponse rst = new DataResponse();
		List<Role> list = this.usersDao.selectRoleListByUserId(userId);
		rst.setList(list);
		if (list != null) {
			rst.setAllRows(list.size());
		}
		return rst;
	}
	public  List<Tree>  selectGroupDepTreeByPid(String pid,SystemUser systemUser){
		String depId=this.usersDao.selectDepLeaderByUserId(systemUser.getUsers().getUserId());
		if(SnailUtils.isBlankString(depId)){
			List<Tree> list=new ArrayList<Tree>();
			Tree e=new Tree();
			e.setId(systemUser.getUsers().getUserId());
			e.setText(systemUser.getUsers().getName());
			e.setHref(systemUser.getUsers().getMobile());
			list.add(e);
			return list;
		}else{
			if(SnailUtils.isBlankString(pid)){
				CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.usersDao.selectGroupDepTreeByPid(depId));
				return commonTreeUtils.getTreeList(depId);
			}else{
				CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.usersDao.selectGroupDepTreeByPid(pid));
				return commonTreeUtils.getTreeList(pid);
			}
			
		}
		
	}
	public  Map<String,Object>  selectGroupDepAceTreeByPid(String pid,SystemUser systemUser){
		Map<String,Object> rs=new HashMap<String,Object>();
		rs.put("status", "OK");
		String depId=this.usersDao.selectDepLeaderByUserId(systemUser.getUsers().getUserId());
		List<AceTree> list=new ArrayList<AceTree>();
		if(SnailUtils.isBlankString(depId)){
			AceTree e=new AceTree();
			e.setId(systemUser.getUsers().getUserId());
			e.setName(systemUser.getUsers().getName());
			e.setType("item");
			AdditionalParameters additionalParameters=new AdditionalParameters();
			additionalParameters.setId(e.getId());
			additionalParameters.setItemSelected(false);
			additionalParameters.setChildren(false);
			e.setAdditionalParameters(additionalParameters);
			list.add(e);
		}else{
			if(SnailUtils.isBlankString(pid)){
				CommonAceTreeUtils commonTreeUtils=new CommonAceTreeUtils(this.usersDao.selectGroupDepTreeByPid(depId));
				list= commonTreeUtils.getAceTreeList(depId);
			}else{
				CommonAceTreeUtils commonTreeUtils=new CommonAceTreeUtils(this.usersDao.selectGroupDepTreeByPid(pid));
				list= commonTreeUtils.getAceTreeList(pid);
			}
			
		}
		rs.put("data", list);
		return rs;
	}
}
