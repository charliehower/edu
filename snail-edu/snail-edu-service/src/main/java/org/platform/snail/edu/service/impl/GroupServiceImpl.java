package org.platform.snail.edu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.edu.dao.GroupMapper;
import org.platform.snail.edu.model.Group;
import org.platform.snail.edu.service.GroupService;
import org.platform.snail.edu.vo.GroupQVo;
import org.platform.snail.edu.vo.GroupVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CommonTreeUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	public DataResponse findGroupList(GroupQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<GroupVo> list = this.groupMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.groupMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertGroup(JSONObject json, SystemUser systemUser)
			throws Exception {
		Group o = new Group();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getGroupId())) {
			return new DataResponse(false, "分组编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGroupName())) {
			return new DataResponse(false, "组名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSqlText())) {
			//return new DataResponse(false, "语句不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.groupMapper.isExitByName(o.getGroupName());
		if (temp > 0) {
			return new DataResponse(false, "已存在此组名的数据！");
		}
		this.groupMapper.insert(o);
		this.dataBaseLogService.log("添加分组", "分组", "", o.getGroupName(),
				o.getGroupName(), systemUser);
		return new DataResponse(true, "添加分组完成！");
	}

	public DataResponse updateGroup(JSONObject json, SystemUser systemUser)
			throws Exception {
		Group o = new Group();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getGroupId())) {
			return new DataResponse(false, "分组编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGroupName())) {
			return new DataResponse(false, "组名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSqlText())) {
			//return new DataResponse(false, "语句不能为空！");
		}
		o.setCreateTime(new Date());
		this.groupMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更分组", "分组", "", o.getGroupName(),
				o.getGroupName(), systemUser);
		return new DataResponse(true, "变更分组完成！");
	}

	public DataResponse selectGroupByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.groupMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteGroupByGroupId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.groupMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除分组", "分组", String.valueOf(id), String.valueOf(id),
				"分组", systemUser);
		return rst;
	}
	
	public  List<Tree>  selectGroupDepTreeByPid(String pid){
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.groupMapper.selectGroupDepTreeByPid(pid));
		return commonTreeUtils.getTreeList(pid);
	}
	public  List<Tree>  selectGroupGradeTreeByPid(String pid){
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.groupMapper.selectGroupGradeTreeByPid(pid));
		return commonTreeUtils.getTreeList(pid);
	}
	public  List<Tree>  selectGroupDiscriblineTreeByPid(String pid){
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.groupMapper.selectGroupDiscriblineTreeByPid(pid));
		return commonTreeUtils.getTreeList(pid);
	}
	public  List<Tree> selectFreeGroupTreeRoot(){
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.groupMapper.selectFreeGroupTreeRoot());
		return commonTreeUtils.getTreeList("0");
	}
	public  List<Tree> selectFreeGroupTreeByPid(String pid){
		Connection conn = null;
		SqlRunner sqlRunner=null;
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		try {
			conn = sqlSessionFactory.getConfiguration().getEnvironment()
					.getDataSource().getConnection();
			sqlRunner = new SqlRunner(conn);
			Group o=this.groupMapper.selectByPrimaryKey(pid);
			if(SnailUtils.isNotBlankString(o.getSqlText())){
				List<Map<String,Object>> items=sqlRunner.selectAll(o.getSqlText());
				for(Map<String,Object> p:items){
					Map<String,String> tmp=new HashMap<String,String>();
					SnailBeanUtils.copyMap2Bean(tmp, p);
					tmp.put("PID", pid);
					this.logger.info(tmp);
					list.add(tmp);
				}
			}else{
				List<Map<String,String>> items=this.groupMapper.selectFreeGroupUsersTreeByGorupId(pid);
				for(Map<String,String> p:items){
					p.put("PID", pid);
					this.logger.info(p);
					list.add(p);
				}
			}
			
		} catch (SQLException e) {
			this.logger.info(e);
			return null;
		}finally{
			if (sqlRunner != null) {
				sqlRunner.closeConnection();
			}
		}
		
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(list);
		return commonTreeUtils.getTreeList(pid);
	}
	public  DataResponse selectFreeGroupUsersListByGorupId(String groupId) throws Exception{
		DataResponse rst=new DataResponse();
		if(SnailUtils.isBlankString(groupId)){
			return new DataResponse(false,"组编号不能为空！");
		}
		List<Map<String,String>> list=this.groupMapper.selectFreeGroupUsersListByGorupId(groupId);
		List<Map<String,String>> items=this.selectFreeGroupListByPid(groupId);
		for(Map<String,String> p:items){
			Map<String,String> tmp=new HashMap<String,String>();
			tmp.put("USER_ID", p.get("ID"));
			tmp.put("NAME", p.get("TEXT"));
			this.logger.info(tmp);
			list.add(tmp);
		}
		rst.setList(list);
		return rst;
	}
	public  DataResponse batchSaveGroupUsersByUserIds(JSONObject json,SystemUser systemUser) throws Exception{
		String groupId=json.getString("groupId");
		if(SnailUtils.isBlankString(groupId)){
			return new DataResponse(false,"自由组编号不能为空！");
		}
		Group o=this.groupMapper.selectByPrimaryKey(groupId);
		if(SnailUtils.isBlankString(o.getSqlText())){
			List<String> list=new ArrayList<String>();
			JSONArray jsons=json.getJSONArray("list");
			for(Object obj:jsons){
				list.add(obj.toString());
			}
			this.logger.info(groupId);
			this.logger.info(json);
			this.groupMapper.batchSaveGroupUsersByUserIds(list, groupId);
			this.dataBaseLogService.log("自由组分配人员", "自由组", "","", groupId, systemUser);
		}
		
		return new DataResponse(true,"自由组分配人员完成！");
	}
	private  List<Map<String,String>> selectFreeGroupListByPid(String pid){
		Connection conn = null;
		SqlRunner sqlRunner=null;
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		try {
			conn = sqlSessionFactory.getConfiguration().getEnvironment()
					.getDataSource().getConnection();
			sqlRunner = new SqlRunner(conn);
			Group o=this.groupMapper.selectByPrimaryKey(pid);
			if(SnailUtils.isNotBlankString(o.getSqlText())){
				List<Map<String,Object>> items=sqlRunner.selectAll(o.getSqlText());
				for(Map<String,Object> p:items){
					Map<String,String> tmp=new HashMap<String,String>();
					SnailBeanUtils.copyMap2Bean(tmp, p);
					tmp.put("PID", pid);
					this.logger.info(tmp);
					list.add(tmp);
				}
			}
			
		} catch (SQLException e) {
			this.logger.info(e);
			return null;
		}finally{
			if (sqlRunner != null) {
				sqlRunner.closeConnection();
			}
		}
		return list;
	}
}
