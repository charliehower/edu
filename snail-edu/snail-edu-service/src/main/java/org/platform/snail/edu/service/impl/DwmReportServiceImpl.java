package org.platform.snail.edu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.DwmReportMapper;
import org.platform.snail.edu.dao.QueueCmccWaitMapper;
import org.platform.snail.edu.dao.TaskMapper;
import org.platform.snail.edu.model.DwmReport;
import org.platform.snail.edu.model.QueueCmccWait;
import org.platform.snail.edu.model.Task;
import org.platform.snail.edu.service.DwmReportService;
import org.platform.snail.edu.vo.DwmReportQVo;
import org.platform.snail.edu.vo.DwmReportVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dwmReportService")
public class DwmReportServiceImpl implements DwmReportService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DwmReportMapper dwmReportMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private QueueCmccWaitMapper queueCmccWaitMapper;

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	public DataResponse findDwmReportList(DwmReportQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<DwmReportVo> list = this.dwmReportMapper.findList(condition,
				start, start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.dwmReportMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertDwmReport(JSONObject json, SystemUser systemUser)
			throws Exception {
		DwmReport o = new DwmReport();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getDwmReportId())) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "内容不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategoryId())) {
			return new DataResponse(false, "类型不能为空！");
		}
		o.setCreateTime(new Date());

		o.setPublisher(systemUser.getUsers().getUserId());
		o.setPublishTime(null);
		int temp = this.dwmReportMapper.isExitByTitle(o.getTitle());
		if (temp > 0) {
			return new DataResponse(false, "已存在此标题的数据！");
		}
		if (SnailUtils.isBlankString(o.getDepartmentId())) {
			String deptId = null;
			List<Map<String, Object>> list = this.dwmReportMapper
					.selectDeptIdByUserId(systemUser.getUsers().getUserId());
			for (Map<String, Object> p : list) {
				deptId = String.valueOf(p.get("code"));
			}
			if (SnailUtils.isBlankString(deptId)) {
				deptId = systemUser.getUsers().getDepartmentId();
			}
			o.setDepartmentId(deptId);
		}

		this.dwmReportMapper.insert(o);
		this.updateForStatusByPrimaryKey(o.getDwmReportId(), "1", null, "09",
				"/edu", systemUser);
		this.dataBaseLogService.log("添加日查周报", "日查周报", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "添加日查周报完成！");
	}

	public DataResponse updateDwmReport(JSONObject json, SystemUser systemUser)
			throws Exception {
		DwmReport o = new DwmReport();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getDwmReportId())) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "内容不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategoryId())) {
			return new DataResponse(false, "类型不能为空！");
		}
		o.setCreateTime(new Date());
		o.setDepartmentId(systemUser.getUsers().getDepartmentId());
		o.setPublisher(systemUser.getUsers().getUserId());
		o.setPublishTime(null);
		this.dwmReportMapper.updateByPrimaryKeyWithBLOBs(o);
		this.dataBaseLogService.log("变更日查周报", "日查周报", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "变更日查周报完成！");
	}

	public DataResponse selectDwmReportByPrimaryKey(String id) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.dwmReportMapper.selectVoByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteDwmReportByDwmReportId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.dwmReportMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除日查周报", "日查周报", String.valueOf(id),
				String.valueOf(id), "日查周报", systemUser);
		return rst;
	}

	public List<Map<String, Object>> selectMyDeptUser(SystemUser systemUser)  throws Exception{
		
		List<Map<String, Object>> list = this.dwmReportMapper
				.selectDeptIdByUserId(systemUser.getUsers().getUserId());
		for(Map<String, Object> p:list){
			p.put("selected", true);
			break;
		}
		
		return list;
	}

	public DataResponse saveOrUpdateDwmReport(JSONObject json,
			SystemUser systemUser) throws Exception {
		DwmReport o = new DwmReport();
		SnailBeanUtils.copyProperties(o, json);
		/*
		 * if (SnailUtils.isBlankString(o.getYear())) { return new
		 * DataResponse(false, "学年不能为空！"); } if
		 * (SnailUtils.isBlankString(o.getDwmReportId())) { return new
		 * DataResponse(false, "日查周报不能为空！"); } if
		 * (SnailUtils.isBlankString(o.getDisciplineId())) { return new
		 * DataResponse(false, "学科不能为空！"); } if
		 * (SnailUtils.isBlankString(o.getGradeId())) { return new
		 * DataResponse(false, "年级不能为空！"); }
		 */
		o.setCreateTime(new Date());

		this.dwmReportMapper.saveOrUpdateDwmReport(o);
		this.dataBaseLogService.log("变更日查周报", "日查周报", "", o.getDwmReportId(),
				o.getDwmReportId(), systemUser);
		return new DataResponse(true, "变更日查周报完成！");
	}

	public DataResponse updateForTopByPrimaryKey(String dwmReportId,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		DwmReport o = this.dwmReportMapper.selectByPrimaryKey(dwmReportId);
		this.dwmReportMapper.updateForTopByPrimaryKey(dwmReportId,
				o.getCategoryId());
		this.dataBaseLogService.log("报告置顶", o.getTitle(), dwmReportId,
				dwmReportId, "报告", systemUser);
		return rst;
	}

	public DataResponse updateForStatusByPrimaryKey(String dwmReportId,
			String status, String departmentId, String groupId,
			String contextPath, SystemUser systemUser) throws Exception {
		DwmReportVo v = this.dwmReportMapper.selectVoByPrimaryKey(dwmReportId);
		DwmReport o = this.dwmReportMapper.selectByPrimaryKey(dwmReportId);
		o.setAuthorGroupsId(groupId);
		o.setAuthorDepartmentId(departmentId);
		o.setPublishTime(new Date());
		this.dwmReportMapper.updateByPrimaryKey(o);
		String title = v.getCategoryName();
		String taskId = null;
		List<Task> list = new ArrayList<Task>();
		this.logger.info("departmentId->" + departmentId);
		this.logger.info("groupId->" + groupId);
		if (SnailUtils.isNotBlankString(departmentId)) {
			this.logger.info("selectUserIdByDepartmentId->");
			List<Map<String, String>> items = this.dwmReportMapper
					.selectUserIdByDepartmentId(departmentId);
			for (Map<String, String> p : items) {
				taskId = UUID.randomUUID().toString();
				Task task = new Task();
				task.setCategory("02");
				task.setCreateTime(new Date());
				task.setObjId(dwmReportId);
				task.setTaskId(taskId);
				task.setTitle(title);
				task.setUrl(contextPath
						+ "/dynamic/service/dwmReport/preview.jsp?dwmReportId="
						+ dwmReportId + "&taskId=" + taskId);
				task.setUserId((String) p.get("ID"));
				task.setUserName((String) p.get("NAME"));
				task.setTel((String) p.get("TEL"));
				task.setMsg(o.getTitle());
				list.add(task);
			}
		}
		if (SnailUtils.isNotBlankString(groupId)) {
			Connection conn = null;
			SqlRunner sqlRunner = null;
			try {
				this.logger.info("selectSqlTextByGroupId->");
				conn = sqlSessionFactory.getConfiguration().getEnvironment()
						.getDataSource().getConnection();
				sqlRunner = new SqlRunner(conn);

				List<Map<String, Object>> items = sqlRunner
						.selectAll(this.dwmReportMapper
								.selectSqlTextByGroupId(groupId));
				for (Map<String, Object> p : items) {
					taskId = UUID.randomUUID().toString();
					Task task = new Task();
					task.setCategory("02");
					task.setCreateTime(new Date());
					task.setObjId(dwmReportId);
					task.setTaskId(taskId);
					task.setTitle(title);
					task.setUrl(contextPath
							+ "/dynamic/service/dwmReport/preview.jsp?dwmReportId="
							+ dwmReportId + "&taskId=" + taskId);
					task.setUserId((String) p.get("ID"));
					task.setUserName((String) p.get("TEXT"));
					task.setTel((String) p.get("HREF"));
					task.setMsg(o.getTitle());
					list.add(task);
				}
			} catch (SQLException e) {
				this.logger.info(e);
			} finally {
				if (sqlRunner != null) {
					sqlRunner.closeConnection();
				}
			}
		}
		this.taskMapper.batchInsert(list);
		this.dwmReportMapper.updateForStatusByPrimaryKey(dwmReportId, status);
		for (Task e : list) {
			QueueCmccWait q = new QueueCmccWait();
			q.setCreateTime(new Date());
			q.setMsg(e.getUserName() + ",您好!\n" + e.getMsg()
					+ "\n请登陆校园管理信息化系统查看详细,地址：http://121.15.140.22/cas/");
			q.setQueueId(UUID.randomUUID().toString());
			q.setName(e.getUserName());
			q.setTel(e.getTel());
			// queueCmccWaitMapper.insert(q);
		}
		String msg = "报告发布";
		if (status.equals("2")) {
			msg = "报告下线";
		}
		DwmReportServiceImpl.Plushlet.send("notice");
		this.dataBaseLogService.log(msg, o.getTitle(), dwmReportId,
				dwmReportId, "报告", systemUser);
		return new DataResponse(true, "发布成功！");
	}

	public static class Plushlet extends EventPullSource {
		private static boolean status = false;
		private static String category = "notice";

		@Override
		protected long getSleepTime() {
			return 8000;
		}

		@Override
		protected Event pullEvent() {
			Event event = Event.createDataEvent("/message/plushlet");
			event.setField("time", new Date().toLocaleString());
			event.setField("status", "0");
			event.setField("category", category);
			if (status) {
				System.out.println(category + "/" + status);
				event.setField("status", "1");
				status = false;
			}

			return event;
		}

		public static void send(String arg) {
			status = true;
			category = arg;
		}
	}

	public DataResponse findListTop(String categoryId, int limit,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.logger.info(limit);
		List<DwmReportVo> list = this.dwmReportMapper.findListTop(categoryId,
				limit, systemUser.getUsers().getUserId());
		rst.setList(list);
		rst.setAllRows(list.size());
		return rst;
	}

	public DataResponse getReportByCategoryIdAndTime(DwmReportQVo condition)
			throws Exception {
		DataResponse rst = new DataResponse();
		List<Map<String, Object>> list = this.dwmReportMapper
				.getReportByCategoryIdAndTime(condition);
		rst.setList(list);
		rst.setAllRows(list.size());
		return rst;
	}

	public DataResponse getViewerByDwrReportId(String dwmReportId)
			throws Exception {

		DwmReport o = this.dwmReportMapper.selectByPrimaryKey(dwmReportId);
		List<String> list = new ArrayList<String>();
		if (SnailUtils.isNotBlankString(o.getAuthorDepartmentId())) {
			this.logger.info("selectUserIdByDepartmentId->");
			List<Map<String, String>> items = this.dwmReportMapper
					.selectUserIdByDepartmentId(o.getAuthorDepartmentId());
			for (Map<String, String> p : items) {
				list.add(p.get("NAME"));
				this.logger.info(p.get("NAME"));
			}
		}
		if (SnailUtils.isNotBlankString(o.getAuthorGroupsId())) {
			Connection conn = null;
			SqlRunner sqlRunner = null;
			try {
				this.logger.info("selectSqlTextByGroupId->");
				conn = sqlSessionFactory.getConfiguration().getEnvironment()
						.getDataSource().getConnection();
				sqlRunner = new SqlRunner(conn);

				List<Map<String, Object>> items = sqlRunner
						.selectAll(this.dwmReportMapper
								.selectSqlTextByGroupId(o.getAuthorGroupsId()));
				for (Map<String, Object> p : items) {
					list.add((String) p.get("TEXT"));
					this.logger.info(p.get("TEXT"));
				}
			} catch (SQLException e) {
				this.logger.info(e);
			} finally {
				if (sqlRunner != null) {
					sqlRunner.closeConnection();
				}
			}
		}
		DataResponse rst = new DataResponse();
		rst.setList(list);
		return rst;
	}
}
