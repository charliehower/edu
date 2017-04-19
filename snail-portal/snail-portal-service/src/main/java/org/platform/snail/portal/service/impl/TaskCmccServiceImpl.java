package org.platform.snail.portal.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.cxf.model.R01;
import org.platform.snail.cxf.model.Result;
import org.platform.snail.cxf.model.S01;
import org.platform.snail.portal.dao.QueueCmccHisMapper;
import org.platform.snail.portal.dao.QueueCmccWaitMapper;
import org.platform.snail.portal.dao.TaskCmccMapper;
import org.platform.snail.portal.model.QueueCmccHis;
import org.platform.snail.portal.model.QueueCmccWait;
import org.platform.snail.portal.model.TaskCmcc;
import org.platform.snail.portal.service.TaskCmccService;
import org.platform.snail.portal.vo.TaskCmccQVo;
import org.platform.snail.portal.vo.TaskCmccVo;
import org.platform.snail.service.CxfClientService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.threadpool.RunThread;
import org.platform.snail.threadpool.ThreadPool;
import org.platform.snail.threadpool.ThreadProcess;
import org.platform.snail.utils.Config;
import org.platform.snail.utils.HttpRuquest;
import org.platform.snail.utils.HttpSend;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taskCmccService")
public class TaskCmccServiceImpl implements TaskCmccService, ThreadProcess {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TaskCmccMapper taskCmccMapper;
	@Autowired
	private QueueCmccWaitMapper queueCmccWaitMapper;
	@Autowired
	private QueueCmccHisMapper queueCmccHisMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	
	@Autowired
	private CxfClientService cxfClientService;

	private ThreadPool threadPool;

	public DataResponse findTaskCmccList(TaskCmccQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<TaskCmccVo> list = this.taskCmccMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.taskCmccMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertTaskCmcc(JSONObject json, SystemUser systemUser)
			throws Exception {
		TaskCmcc o = new TaskCmcc();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		o.setCreateUserId(systemUser.getUsers().getUserId());
		o.setStatus("0");
		o.setTaskId(String.valueOf(new Date().getTime()));
		if (SnailUtils.isBlankString(o.getTaskId())) {
			return new DataResponse(false, "任务编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTaskName())) {
			return new DataResponse(false, "任务名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTel())) {
			return new DataResponse(false, "手机号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getMsg())) {
			return new DataResponse(false, "短信不能为空！");
		}
		int temp = this.taskCmccMapper.isExitByTaskName(o.getTaskName());
		if (temp > 0) {
			return new DataResponse(false, "已存在此任务名称的数据！");
		}
		this.taskCmccMapper.insert(o);
		this.dataBaseLogService.log("添加短信任务", "短信任务", "", o.getTaskName(),
				o.getTaskName(), systemUser);
		return new DataResponse(true, "添加短信任务完成！");
	}

	public DataResponse updateTaskCmcc(JSONObject json, SystemUser systemUser)
			throws Exception {
		TaskCmcc o = new TaskCmcc();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		o.setCreateUserId(systemUser.getUsers().getUserId());
		o.setStatus("0");
		if (SnailUtils.isBlankString(o.getTaskId())) {
			return new DataResponse(false, "任务编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTaskName())) {
			return new DataResponse(false, "任务名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTel())) {
			return new DataResponse(false, "手机号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getMsg())) {
			return new DataResponse(false, "短信不能为空！");
		}
		this.taskCmccMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更短信任务", "短信任务", "", o.getTaskName(),
				o.getTaskName(), systemUser);
		return new DataResponse(true, "变更短信任务完成！");
	}

	public DataResponse selectTaskCmccByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.taskCmccMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteTaskCmccByTaskCmccId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.taskCmccMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除短信任务", "短信任务", String.valueOf(id), String.valueOf(id),
				"短信任务", systemUser);
		return rst;
	}
	public DataResponse updateTaskStatusCmccByTaskCmccId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.taskCmccMapper.updateStatusByPrimaryKey(id,"0");
		this.dataBaseLogService.log("重新发送短信", "短信任务", String.valueOf(id), String.valueOf(id),
				"短信任务", systemUser);
		return rst;
	}
	public  void queueTask() throws Exception{
		this.logger.info("queueTask executed");
		List<TaskCmcc> list=this.taskCmccMapper.selectByTask();
		for(TaskCmcc o:list){
			String [] telArr=o.getTel().split(";");
			for(int i=0;i<telArr.length;i++){
				if(telArr[i]!=null&&telArr[i].indexOf(",")!=-1){
					QueueCmccWait q=new QueueCmccWait();
					q.setCreateTime(new Date());
					q.setMsg(o.getMsg());
					q.setQueueId(UUID.randomUUID().toString());
					q.setName(telArr[i].split(",")[1]);
					q.setTel(telArr[i].split(",")[0]);
					queueCmccWaitMapper.insert(q);
				}
				
			}
			this.taskCmccMapper.updateStatusByPrimaryKey(o.getTaskId(),"1");
		}
	}
	public  void queueTaskDetail() throws Exception{
		this.logger.info("start dispatch for task");
		this.logger.info("threadPool.dataSize:"+threadPool.dataSize());
		if (threadPool.dataSize() < 1) {
			List<QueueCmccWait> list=this.taskCmccMapper.selectQueueByTask();
			for(QueueCmccWait o:list){
				if (!threadPool.dataQueue.contains(o)) {
					threadPool.addData(o);
				}
			}
			logger.info("add new task " + list.size());
			logger.info("dataQueue " + threadPool.dataQueue.procSize());
			logger.info("threadSize " + threadPool.getThreadList().size());
		}
		
	}
	/**
	 * 业务处理回调方法
	 * 
	 * @param rt
	 * @param obj
	 * @throws UnsupportedEncodingException 
	 */
	public void execute(RunThread rt, Object obj) {
		this.logger.info("execute start");
		QueueCmccWait o = (QueueCmccWait) obj;
		QueueCmccHis record=new QueueCmccHis();
		record.setAddTime(o.getCreateTime());
		record.setCreateTime(new Date());
		record.setMsg(o.getMsg());
		record.setName(o.getName());
		record.setQueueId(o.getQueueId());
		record.setTel(o.getTel());
		record.setRemark("OK");
		Map<String,String> params=new HashMap<String,String>();
		params.put("req",Config.getProperty("sms.req"));
		params.put("pwd",Config.getProperty("sms.pwd"));
		params.put("sourceadd",Config.getProperty("sms.sourceadd"));
		params.put("phone",o.getTel());
		params.put("content",o.getMsg());
		String qm=Config.getProperty("sms.suffix");
		String strSmsParam=null;
		try {
			strSmsParam = "reg=" + params.get("req") + "&pwd=" + params.get("pwd") + "&sourceadd=" + params.get("sourceadd") + "&phone=" + params.get("phone") + "&content=" + HttpSend.paraTo16(params.get("content")+qm);
		} catch (UnsupportedEncodingException e) {
			this.logger.error(e);
		}
		String p=HttpRuquest.requestPost(Config.getProperty("sms.url"), strSmsParam);
		this.logger.info(Config.getProperty("sms.url"));
		this.logger.info(p);
		record.setRemark(p);
		/*Map<String,String> param=HttpUtils.getParams(p);
		if(param.containsKey("result")){
			if(param.get("result").equals("0")){
				//this.queueCmccHisMapper.batchInsert(record);
			}
			
		}*/
		this.queueCmccHisMapper.batchInsert(record);
	}

	/**
	 * 执行时间超过指定时间的回调方法
	 * 
	 * @param rt
	 */
	public void timeOut(RunThread rt) {
		
	}
	public  TaskCmccServiceImpl(){
		logger.info("启动线程池->初始化 " + 5);
		threadPool = new ThreadPool(this, 5, 20, 1000 * 10);
		threadPool.start();
	}
	public void workFlowMsgTask(){
		logger.info("扫描工作流短信提醒任务");
		List<Map<String,Object>> list=this.taskCmccMapper.selectWorkflowTaskMsg();
		for(Map<String,Object> data:list){
			S01 body=new S01();
			body.setUserId((String)data.get("userId"));
			body.setTid("workFlow");
			body.setData(data);
			body.setTitle((String)data.get("objname_"));
			Result<R01> rst=cxfClientService.S01(body);
			this.logger.info(rst);
			if(rst.isState()){
				this.taskCmccMapper.updateWorkflowTaskMsgByTaskId(String.valueOf(data.get("DBID_")));
			}
		}
		
	}

}
