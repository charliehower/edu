package org.platform.snail.edu.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.LeaveMapper;
import org.platform.snail.edu.service.LeaveService;
import org.platform.snail.edu.vo.LeaveQVo;
import org.platform.snail.edu.vo.LeaveVo;
import org.platform.snail.service.DataBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private LeaveMapper leaveMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findLeaveList(LeaveQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<LeaveVo> list = this.leaveMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.leaveMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	
	public DataResponse selectLeaveByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.leaveMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteLeaveByLeaveId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.leaveMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除教职工", "教职工", String.valueOf(id), String.valueOf(id),
				"教职工", systemUser);
		return rst;
	}
	
	public  DataResponse updateAuditById(String leaveId,
			String auditStatus, String auditRemark,SystemUser systemUser) throws Exception{
		DataResponse rst = new DataResponse();
		int t= this.leaveMapper.updateAuditById(leaveId, auditStatus, auditRemark);
		boolean state=t>0;
		rst.setState(state);
		rst.setErrorMessage("成功！");
		this.dataBaseLogService.log("消假", "消假", String.valueOf(leaveId), String.valueOf(leaveId),
				"消假", systemUser);
		return rst;
	}
	
}
