/**
 * @Title: WorkflowMsgServiceImpl.java
 * @Package org.platform.snail.workflow.service.impl
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年2月8日 下午12:33:25
 * @version V1.0
 */

package org.platform.snail.workflow.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.platform.snail.cxf.model.S01;
import org.platform.snail.edu.dao.RepairsMapper;
import org.platform.snail.edu.model.Repairs;
import org.platform.snail.edu.vo.RepairsVo;
import org.platform.snail.service.CxfClientService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.workflow.service.WorkflowMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: WorkflowMsgServiceImpl
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年2月8日 下午12:33:25
 *
 */
@Service("workflowMsgService")
public class WorkflowMsgServiceImpl implements WorkflowMsgService {

	@Autowired
	private RepairsMapper repairsMapper;
	@Autowired
	private CxfClientService cxfClientService;
	Logger logger = Logger.getLogger(this.getClass());

	public void repairs(String instanceId,String assignee,String status){
		this.repairsMapper.updateByByPrimaryKeyAndRepairsUserId(instanceId, assignee);
		Repairs o=repairsMapper.selectByPrimaryKey(instanceId);
		if(o.getStauts().equals("1")&&o.getIsSmsAlerts().equals("1")){
			this.logger.info("=======>发送短信与邮件");
			Map<String,Object> data=new HashMap<String,Object>();
			RepairsVo vo=this.repairsMapper.selectVoByPrimaryKey(instanceId);
			SnailBeanUtils.copyBean2Map(vo, data);
			SnailBeanUtils.copyBean2Map(o, data);
			this.logger.info(data);
			S01 body=new S01();
			body.setUserId(o.getRepairsUserId());
			body.setTid("repairs");
			body.setData(data);
			body.setTitle("报修申请/"+vo.getFullName());
			cxfClientService.S01(body);
		}
		if(o.getStauts().equals("2")&&o.getIsSmsAlerts().equals("1")){
			this.logger.info("=======>发送短信与邮件");
			Map<String,Object> data=new HashMap<String,Object>();
			RepairsVo vo=this.repairsMapper.selectVoByPrimaryKey(instanceId);
			SnailBeanUtils.copyBean2Map(vo, data);
			this.logger.info(data);
			S01 body=new S01();
			body.setUserId(o.getAlertsUserId());
			body.setTid("repairs.complete");
			body.setTitle("报修申请完成/"+vo.getFullName());
			body.setData(data);
			cxfClientService.S01(body);
		}

	}
	

}
