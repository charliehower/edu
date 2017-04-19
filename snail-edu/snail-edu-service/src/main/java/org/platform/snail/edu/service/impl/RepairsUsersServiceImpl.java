package org.platform.snail.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.CheckTree;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.RepairsUsersMapper;
import org.platform.snail.edu.model.RepairsUsers;
import org.platform.snail.edu.service.RepairsUsersService;
import org.platform.snail.edu.vo.RepairsUsersQVo;
import org.platform.snail.edu.vo.RepairsUsersVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CheckTreeUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("repairsUsersService")
public class RepairsUsersServiceImpl implements RepairsUsersService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RepairsUsersMapper repairsUsersMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findRepairsUsersList(RepairsUsersQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<RepairsUsersVo> list = this.repairsUsersMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.repairsUsersMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertRepairsUsers(JSONObject json, SystemUser systemUser)
			throws Exception {
		RepairsUsers o = new RepairsUsers();
		SnailBeanUtils.copyProperties(o, json);
		o.setRepairsUsersId(String.valueOf(new Date().getTime()));
		if (SnailUtils.isBlankString(o.getUserId())) {
			return new DataResponse(false, "维修人不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategoryId())) {
			return new DataResponse(false, "类别不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.repairsUsersMapper.isExitByUserId(o.getUserId());
		if (temp > 0) {
			return new DataResponse(false, "已存在此员工的数据！");
		}
		this.repairsUsersMapper.insert(o);
		this.batchInsertOrUpdate(o.getSubCategory(), o.getUserId());
		this.dataBaseLogService.log("添加维修人员", "维修人员", "", o.getUserId(),
				o.getUserId(), systemUser);
		return new DataResponse(true, "添加维修人员完成！");
	}
	private void batchInsertOrUpdate(String location,String userId){
		if(SnailUtils.isNotBlankString(location)){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			if(location.indexOf(",")!=-1){
				String tmp[]=location.split(",");
				for(String o:tmp){
					Map<String,Object> e=new HashMap<String,Object>();
					e.put("userId", userId);
					e.put("locationId", o);
					e.put("createTime", new Date());
					list.add(e);
				}
				this.repairsUsersMapper.batchInsertOrUpdate(list, userId);
			}
		}
	}
	public DataResponse updateRepairsUsers(JSONObject json, SystemUser systemUser)
			throws Exception {
		RepairsUsers o = new RepairsUsers();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getRepairsUsersId())) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getUserId())) {
			return new DataResponse(false, "维修人不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategoryId())) {
			return new DataResponse(false, "类别不能为空！");
		}
		o.setCreateTime(new Date());
		this.repairsUsersMapper.updateByPrimaryKey(o);
		this.batchInsertOrUpdate(o.getSubCategory(), o.getUserId());
		this.dataBaseLogService.log("变更维修人员", "维修人员", "", o.getUserId(),
				o.getUserId(), systemUser);
		return new DataResponse(true, "变更维修人员完成！");
	}

	public DataResponse selectRepairsUsersByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.repairsUsersMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteRepairsUsersByRepairsUsersId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		RepairsUsers o=this.repairsUsersMapper.selectByPrimaryKey(id);
		this.repairsUsersMapper.deleteUsersLocation(o.getUserId());
		this.repairsUsersMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除维修人员", "维修人员", o.getUserId(), id,
				"维修人员", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateRepairsUsers(JSONObject json, SystemUser systemUser)
			throws Exception {
		RepairsUsers o = new RepairsUsers();
		SnailBeanUtils.copyProperties(o, json);
		
		o.setCreateTime(new Date());
		
		/*this.repairsUsersMapper.saveOrUpdateRepairsUsers(o);
		this.dataBaseLogService.log("变更维修人员", "维修人员", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
				*/
		return new DataResponse(true, "变更维修人员完成！");
	}
	
	public List<CheckTree> selectLocationTreeList(String userId){
		CheckTreeUtils commonTreeUtils=new CheckTreeUtils(this.repairsUsersMapper.selectLocationTreeList(userId));
		return commonTreeUtils.getCheckTreeList("0");
	}
	public String getAssignee(String repairsCategory){
		
		return null;
	}
}
