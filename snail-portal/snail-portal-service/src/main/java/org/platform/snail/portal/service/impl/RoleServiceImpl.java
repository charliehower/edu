package org.platform.snail.portal.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.CheckTree;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.model.Role;
import org.platform.snail.portal.dao.RoleDao;
import org.platform.snail.portal.service.RoleService;
import org.platform.snail.portal.vo.RoleVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CheckTreeUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private DataBaseLogService dataBaseLogService;

	public DataResponse findRoleList(Role condition, int start, int limit,
			String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<RoleVo> list = this.roleDao.findRoleList(condition, start,start+limit, orderBy);
		rst.setList(list);
		int allRows = this.roleDao.findRoleCount(condition);
		rst.setAllRows(allRows);
		rst.setState(true);
		return rst;
	}

	public DataResponse insertRole(JSONObject josnObject, SystemUser systemUser)
			throws Exception {
		Role role = new Role();
		SnailBeanUtils.copyProperties(role, josnObject);

		if (SnailUtils.isBlankString(role.getRoleName())) {

			return new DataResponse(false, "角色名称不能为空！");
		}

		role.setCreateUserId(systemUser.getUsers().getUserId());

		role.setCreateTime(new Date());
		int temp = this.roleDao.isExitRoleByRoleName(role.getRoleName());
		if (temp > 0) {

			return new DataResponse(false, "角色名称已存在！");
		}
		this.roleDao.insertRole(role);
		this.dataBaseLogService.log("添加新角色", "角色", "", role.getRoleName(),
				role.getRoleName(), systemUser);
		return new DataResponse(true, "添加新角色完成！");
	}

	public DataResponse updateRole(JSONObject josnObject, SystemUser systemUser)
			throws Exception {
		Role role = new Role();
		SnailBeanUtils.copyProperties(role, josnObject);
		if (SnailUtils.isBlankString(role.getRoleId())) {

			return new DataResponse(false, "角色编号不能为空！");
		}

		if (SnailUtils.isBlankString(role.getRoleName())) {

			return new DataResponse(false, "角色名称不能为空！");
		}
		this.roleDao.updateRoleByPrimaryKey(role);

		this.dataBaseLogService.log("角色信息变更", "角色", "", role.getRoleName(),
				role.getRoleId(), systemUser);

		return new DataResponse(true, "角色信息变更完成！");
	}

	public DataResponse insertRoleResources(String roleId,
			String[] resourcesId, SystemUser systemUser) throws Exception {
		this.roleDao.insertRoleResources(roleId, resourcesId);
		this.dataBaseLogService.log("角色权限变更", "角色权限", "", "", roleId,systemUser);
		return new DataResponse(true, "权限分配完成！");
	}

	public DataResponse selectRoleByPrimaryKey(String roleId) throws Exception {
		DataResponse rst = new DataResponse();
		RoleVo roleVo = this.roleDao.selectRoleVoByPrimaryKey(roleId);
		rst.setResponse(roleVo);
		rst.setState(true);
		return rst;
	}

	public List<CheckTree> selectRoleResourceTreeList(String roleId) {
		CheckTreeUtils checkTreeUtils = new CheckTreeUtils(
				this.roleDao.selectRoleResourceTreeList(roleId));
		return checkTreeUtils.getCheckTreeList("0");
	}

	public DataResponse deleteRoleByRoleId(String roleId, SystemUser systemUser)
			throws Exception {
		if (roleId.indexOf(",") != -1) {
			String[] role = roleId.split(",");
			for (String id : role) {
				if (this.roleDao.isExitRoleUsedByRoleId(id) > 0) {
					RoleVo rv = this.roleDao.selectRoleVoByPrimaryKey(id);
					return new DataResponse(false, "已有用户使用角色:"
							+ rv.getRoleName() + "，请先解除绑定！");
				} else {
					this.roleDao.deleteRoleByRoleId(id);
				}
			}
		} else {
			if (this.roleDao.isExitRoleUsedByRoleId(roleId) > 0) {
				RoleVo rv = this.roleDao.selectRoleVoByPrimaryKey(roleId);

				return new DataResponse(false, "已有用户使用角色:"
						+ rv.getRoleName() + "，请先解除绑定！");
			} else {
				this.roleDao.deleteRoleByRoleId(roleId);
			}

		}
		this.dataBaseLogService.log("删除角色", "角色", "", "", roleId,
				systemUser);
		return new DataResponse(true, "删除角色完成！");
	}
}
