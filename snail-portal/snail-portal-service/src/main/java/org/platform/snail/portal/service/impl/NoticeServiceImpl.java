package org.platform.snail.portal.service.impl;

import java.io.Serializable;
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
import org.platform.snail.portal.dao.AttachMapper;
import org.platform.snail.portal.dao.NoticeMapper;
import org.platform.snail.portal.dao.QueueCmccWaitMapper;
import org.platform.snail.portal.dao.TaskMapper;
import org.platform.snail.portal.model.Notice;
import org.platform.snail.portal.model.QueueCmccWait;
import org.platform.snail.portal.model.Task;
import org.platform.snail.portal.service.NoticeService;
import org.platform.snail.portal.vo.NoticeQVo;
import org.platform.snail.portal.vo.NoticeVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService, Serializable {

	private static final long serialVersionUID = 8914025046850561346L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private QueueCmccWaitMapper queueCmccWaitMapper;

	public DataResponse findNoticeList(NoticeQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<NoticeVo> list = this.noticeMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.noticeMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertNotice(JSONObject json, SystemUser systemUser)
			throws Exception {
		Notice o = new Notice();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getNoticeId())) {
			return new DataResponse(false, "公告编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "标题不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategory())) {
			return new DataResponse(false, "类别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "内容不能为空！");
		}
		if (o.getDeadline() == null) {
			return new DataResponse(false, "有效时间不能为空！");
		}
		o.setPublisher(systemUser.getUsers().getUserId());
		o.setCreateTime(new Date());
		int temp = this.noticeMapper.isExitByTitle(o.getTitle());
		if (temp > 0) {
			return new DataResponse(false, "已存在此标题的数据！");
		}
		this.noticeMapper.insert(o);
		this.dataBaseLogService.log("添加公告", "公告", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "添加公告完成！");
	}

	public DataResponse updateNotice(JSONObject json, SystemUser systemUser)
			throws Exception {
		Notice o = new Notice();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getNoticeId())) {
			return new DataResponse(false, "公告编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "标题不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategory())) {
			return new DataResponse(false, "类别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "内容不能为空！");
		}
		if (o.getDeadline() == null) {
			return new DataResponse(false, "有效时间不能为空！");
		}
		o.setPublisher(systemUser.getUsers().getUserId());
		o.setCreateTime(new Date());
		this.noticeMapper.updateByPrimaryKeyWithBLOBs(o);
		this.dataBaseLogService.log("变更公告", "公告", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "变更公告完成！");
	}

	public DataResponse selectNoticeByPrimaryKey(String id) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.noticeMapper.selectVoByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteNoticeByNoticeId(String id, SystemUser systemUser)
			throws Exception {
		DataResponse rst = new DataResponse();

		this.noticeMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除公告", "公告", String.valueOf(id),
				String.valueOf(id), "公告", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdateNotice(JSONObject json,
			SystemUser systemUser) throws Exception {
		Notice o = new Notice();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getNoticeId())) {
			return new DataResponse(false, "公告编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "标题不能为空！");
		}
		if (SnailUtils.isBlankString(o.getCategory())) {
			return new DataResponse(false, "类别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "内容不能为空！");
		}
		if (o.getDeadline() == null) {
			return new DataResponse(false, "有效时间不能为空！");
		}
		o.setCreateTime(new Date());
		this.noticeMapper.saveOrUpdateNotice(o);
		this.dataBaseLogService.log("变更公告", "公告", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "变更公告完成！");
	}

	public DataResponse updateForTopByPrimaryKey(String noticeId,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		Notice o = this.noticeMapper.selectByPrimaryKey(noticeId);
		this.noticeMapper.updateForTopByPrimaryKey(noticeId, o.getCategory());
		this.dataBaseLogService.log("公告置顶", o.getTitle(), noticeId, noticeId,
				"公告", systemUser);
		return rst;
	}

	public DataResponse updateForStatusByPrimaryKey(String noticeId,
			String status, String departmentId, String groupId,
			String contextPath, SystemUser systemUser) throws Exception {
		if(SnailUtils.isBlankString(groupId)){
			return new DataResponse(false, "请选择发布对象！");
		}
		Notice o = this.noticeMapper.selectByPrimaryKey(noticeId);
		o.setGroupsId(groupId);
		o.setDepartmentId(departmentId);
		o.setPublishTime(new Date());
		this.noticeMapper.updateByPrimaryKey(o);
		String title = this.noticeMapper.selectDictNameByCategoryIdAndCode(
				"21", o.getCategory());
		String taskId = null;
		List<Task> list = new ArrayList<Task>();
		if (SnailUtils.isNotBlankString(departmentId)) {
			List<Map<String, String>> items = this.noticeMapper
					.selectUserIdByDepartmentId(departmentId);
			for (Map<String, String> p : items) {
				taskId = UUID.randomUUID().toString();
				Task task = new Task();
				task.setCategory("01");
				task.setCreateTime(new Date());
				task.setObjId(noticeId);
				task.setTaskId(taskId);
				task.setTitle(title);
				task.setUrl(contextPath
						+ "/dynamic/console/notice/preview.jsp?noticeId="
						+ noticeId + "&taskId=" + taskId);
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
				conn = sqlSessionFactory.getConfiguration().getEnvironment()
						.getDataSource().getConnection();
				sqlRunner = new SqlRunner(conn);

				List<Map<String, Object>> items = sqlRunner
						.selectAll(this.noticeMapper
								.selectSqlTextByGroupId(groupId));
				for (Map<String, Object> p : items) {
					taskId = UUID.randomUUID().toString();
					Task task = new Task();
					task.setCategory("01");
					task.setCreateTime(new Date());
					task.setObjId(noticeId);
					task.setTaskId(taskId);
					task.setTitle(title);
					task.setUrl(contextPath
							+ "/dynamic/console/notice/preview.jsp?noticeId="
							+ noticeId + "&taskId=" + taskId);
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
		this.noticeMapper.updateForStatusByPrimaryKey(noticeId, status);
		for(Task e:list){
			QueueCmccWait q=new QueueCmccWait();
			q.setCreateTime(new Date());
			q.setMsg("您好!\n有一条关于“"+e.getMsg()+"”的通知公告发布,\n请登陆校园管理信息化系统查看详细,地址：http://oa.psgz.cn/cas/");
			q.setQueueId(UUID.randomUUID().toString());
			q.setName(e.getUserName());
			q.setTel(e.getTel());
			queueCmccWaitMapper.insert(q);
		}
		String msg = "公告发布";
		if (status.equals("2")) {
			msg = "公告下线";
		}
		NoticeServiceImpl.Plushlet.send("notice");
		this.dataBaseLogService.log(msg, o.getTitle(), noticeId, noticeId,
				"公告", systemUser);
		return new DataResponse(true, "发布成功！");
	}

	public static class Plushlet extends EventPullSource {
		private static boolean status=false;
		private static String category="";
		@Override
		protected long getSleepTime() {
			return 8000;
		}
		
		@Override
		protected Event pullEvent() {
			Event event = Event.createDataEvent("/message/plushlet");
			event.setField("time", new Date().toLocaleString());
			event.setField("status","0");
			event.setField("category",category);
			if(status){
				System.out.println(category+"/"+status);
				event.setField("status","1");
				status=false;
			}
			
			return event;
		}
		public static void send(String arg){
			status=true;
			category=arg;
		}
	}
	public  DataResponse findListTop(SystemUser systemUser) throws Exception{
		DataResponse rst = new DataResponse();
		List<NoticeVo> list = this.noticeMapper.findListTop();
		rst.setList(list);
		rst.setAllRows(list.size());
		return rst;
	}
}
