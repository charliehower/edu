package org.platform.snail.workflow.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.platform.snail.cxf.model.S01;
import org.platform.snail.edu.dao.RepairsMapper;
import org.platform.snail.service.CxfClientService;
import org.platform.snail.utils.SpringUtils;

public class JavaCmccService {
	
	
	private RepairsMapper repairsMapper;
	
	private CxfClientService cxfClientService;
	
	Logger logger = Logger.getLogger(this.getClass());
	public JavaCmccService(){
		this.logger.info("JavaCmccService init start");
		repairsMapper=(RepairsMapper)SpringUtils.getInstance().getBean("repairsMapper");
		cxfClientService=(CxfClientService)SpringUtils.getInstance().getBean("cxfClientService");
	}
	public void sendCmcc(String userId,String name,String msg){
		msg=msg.replaceAll("R", name);
		this.logger.info("=============>sendCmcc");
		this.logger.info("=============>userId->"+userId);
		this.logger.info("=============>msg->"+msg);
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("msg", msg);
		S01 body=new S01();
		body.setUserId(userId);
		body.setData(data);
		body.setTid("flowMsg");
		body.setTitle("请假流程提醒");
		cxfClientService.S01(body);
	}
}
