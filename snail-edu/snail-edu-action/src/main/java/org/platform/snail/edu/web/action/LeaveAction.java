package org.platform.snail.edu.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.service.LeaveService;
import org.platform.snail.edu.vo.LeaveQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/leave")
public class LeaveAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private LeaveService leaveService;

	@RequestMapping(value = "/findLeaveList.do")
	@ResponseBody
	public DataResponse findLeaveList(LeaveQVo condition, Page page) {
		try {
			condition.setAssignee(this.getSessionSystemUser().getUsers().getUserId());
			if(condition.getAdmin()!=null&&condition.getAdmin().equals("false")){
				condition.setLeaveUserId(this.getSessionSystemUser().getUsers().getUserId());
			}
			DataResponse rst = this.leaveService.findLeaveList(condition,
					page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false, e.getMessage());
		}
	}

	@RequestMapping(value = "/selectLeaveByPrimaryKey.do")
	@ResponseBody
	public DataResponse selectLeaveByPrimaryKey(String id) {
		try {
			return this.leaveService.selectLeaveByPrimaryKey(id);
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false, "加载失败！", e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteLeaveByLeaveId.do")
	@ResponseBody
	public DataResponse deleteLeaveByLeaveId(String jsons) {
		try {
			SystemUser systemUser = this.getSessionSystemUser();
			JSONObject json = JSONObject.fromObject(jsons);
			String id = json.getString("id");
			return this.leaveService.deleteLeaveByLeaveId(id, systemUser);
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false, "删除失败！", e.getMessage());
		}
	}
	@RequestMapping(value = "/updateAuditById.do")
	@ResponseBody
	public DataResponse updateAuditById(String leaveId, String auditStatus,
			String auditRemark) {
		try {
			SystemUser systemUser = this.getSessionSystemUser();
			return this.leaveService.updateAuditById(leaveId, auditStatus,
					auditRemark, systemUser);
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false, "消假失败！", e.getMessage());
		}
	}

}
