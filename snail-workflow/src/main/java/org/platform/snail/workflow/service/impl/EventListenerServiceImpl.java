/**
 * @Title: EventListenerServiceImpl.java
 * @Package org.platform.snail.workflow.service.impl
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月30日 上午11:21:21
 * @version V1.0
 */

package org.platform.snail.workflow.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.cxf.model.S01;
import org.platform.snail.edu.dao.LeaveMapper;
import org.platform.snail.edu.dao.PositiveMapper;
import org.platform.snail.edu.dao.QuitMapper;
import org.platform.snail.edu.dao.RehireMapper;
import org.platform.snail.edu.dao.RepairsMapper;
import org.platform.snail.edu.model.Leave;
import org.platform.snail.edu.model.Positive;
import org.platform.snail.edu.model.Quit;
import org.platform.snail.edu.model.Rehire;
import org.platform.snail.edu.model.Repairs;
import org.platform.snail.edu.vo.RepairsVo;
import org.platform.snail.service.CxfClientService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.workflow.service.EventListenerService;
import org.platform.snail.workflow.service.WorkflowService;
import org.platform.snail.workflow.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: EventListenerServiceImpl
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月30日 上午11:21:21
 *
 */
@Service("eventListenerService")
public class EventListenerServiceImpl implements EventListenerService {

	/*
	 * <p>Title: positive</p>
	 * <p>Description: </p>
	 * @param instanceId
	 * @see org.platform.snail.workflow.service.EventListenerService#positive(java.lang.String)
	 */
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private PositiveMapper positiveMapper;
	@Autowired
	private QuitMapper quitMapper;
	
	@Autowired
	private RehireMapper rehireMapper;
	
	@Autowired
	private RepairsMapper repairsMapper;
	
	@Autowired
	private LeaveMapper leaveMapper;
	
	
	
	public void positive(String instanceId,String status) throws Exception{
		this.logger.info("positive->"+instanceId);
		DataResponse rst=workflowService.findHistoryTaskByProcessInstanceId(instanceId);
		TaskVo tkv=null;
		for(Object obj:rst.getList()){
			tkv=(TaskVo)obj;
		}
		TaskVo first=(TaskVo)rst.getList().get(0);
		Map<String,Object> p=(Map<String,Object>)rst.getResponse();
		Positive o=new Positive();
		o.setCteateTime(new Date());
		o.setEvaluation((String)p.get("evaluation"));
		o.setInstanceId(instanceId);
		o.setLeader((String)p.get("leaderFullName"));
		o.setLo((String)tkv.getVariables().get("evaluation"));
		o.setPositiveTime(SnailUtils.parseDate((String)p.get("positiveTime")));
		o.setRemark((String)p.get("dpFullName"));
		o.setRs((String)tkv.getVariables().get("rs"));
		o.setTeacherId((String)p.get("assigneeId"));
		this.positiveMapper.insert(o);
	}
	public void quit(String instanceId,String status) throws Exception{
		this.logger.info("quit->"+instanceId);
		DataResponse rst=workflowService.findHistoryTaskByProcessInstanceId(instanceId);
		TaskVo tkv=null;
		for(Object obj:rst.getList()){
			tkv=(TaskVo)obj;
		}
		TaskVo first=(TaskVo)rst.getList().get(0);
		Map<String,Object> p=(Map<String,Object>)rst.getResponse();
		Quit o=new Quit();
		o.setCteateTime(new Date());
		o.setReasion((String)p.get("reasion"));
		o.setInstanceId(instanceId);
		o.setLeader((String)p.get("leaderFullName"));
		o.setQuitTime(SnailUtils.parseDate((String)p.get("quitTime")));
		o.setRemark((String)p.get("dpFullName"));
		o.setQuitCategory((String)p.get("quitCategory"));
		o.setTeacherId((String)p.get("assigneeId"));
		this.quitMapper.insert(o);
	}
	public void rehire(String instanceId,String status) throws Exception{
		this.logger.info("rehire->"+instanceId);
		DataResponse rst=workflowService.findHistoryTaskByProcessInstanceId(instanceId);
		TaskVo tkv=null;
		for(Object obj:rst.getList()){
			tkv=(TaskVo)obj;
		}
		TaskVo first=(TaskVo)rst.getList().get(0);
		Map<String,Object> p=(Map<String,Object>)rst.getResponse();
		Rehire o=new Rehire();
		o.setCreateTime(new Date());
		o.setReasion((String)p.get("reasion"));
		o.setInstanceId(instanceId);
		o.setLeader((String)p.get("leaderFullName"));
		o.setRehireTime(SnailUtils.parseDate((String)p.get("rehireTime")));
		o.setRemark((String)p.get("dpFullName"));
		o.setTeacherId((String)p.get("assigneeId"));
		this.rehireMapper.insert(o);
	}
	public void repairs(String instanceId,String status) throws Exception{
		this.logger.info("repairs->"+instanceId);
		DataResponse rst=workflowService.findHistoryTaskByProcessInstanceId(instanceId);
		Map<String,Object> p=(Map<String,Object>)rst.getResponse();
		Repairs o=new Repairs();
		o.setCreateTime(new Date());
		o.setAlertsUserId((String)p.get("assigneeId"));
		SnailBeanUtils.copyMap2Bean(o, rst.getGloble());
		o.setRepairsId(instanceId);//避免冗余的记录
		this.repairsMapper.insertOrUpdateSelective(o);
		
	}
	public void leave(String instanceId,String status) throws Exception{
		this.logger.info("repairs->"+instanceId);
		DataResponse rst=workflowService.findHistoryTaskByProcessInstanceId(instanceId);
		Leave o=new Leave();
		o.setCreateTime(new Date());
		SnailBeanUtils.copyMap2Bean(o, rst.getGloble());
		o.setLeaveId(instanceId);//避免冗余的记录
		o.setStatus(status);
		this.leaveMapper.insertOrUpdateSelective(o);
	}
}
