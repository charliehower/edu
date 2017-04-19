package org.platform.snail.edu.service;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.LeaveQVo;

public interface LeaveService {
	public abstract DataResponse findLeaveList(LeaveQVo condition, int start,
			int limit, String orderBy) throws Exception;

	public abstract DataResponse selectLeaveByPrimaryKey(String id)
			throws Exception;

	public abstract DataResponse deleteLeaveByLeaveId(String id,
			SystemUser systemUser) throws Exception;

	public abstract DataResponse updateAuditById(String leaveId,
			String auditStatus, String auditRemark,SystemUser systemUser) throws Exception;

}
