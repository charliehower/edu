/**
 * @Title: LogListener.java
 * @Package org.platform.snail.workflow.eventlistener
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月27日 下午2:20:54
 * @version V1.0
 */

package org.platform.snail.workflow.eventlistener;

import java.util.Map;

import org.apache.log4j.Logger;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.platform.snail.utils.SpringUtils;
import org.platform.snail.workflow.service.EventListenerService;

/**
 * @ClassName: LogListener
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月27日 下午2:20:54
 *
 */

public class LogListener implements EventListener {
	
	private static final long serialVersionUID = 1L;
	String msg;
	String status;
	Logger logger = Logger.getLogger(this.getClass());
	
	private EventListenerService eventListenerService;

	
	public LogListener(){
		this.status="0";
		this.logger.info("LogListener init for eventListenerService");
		eventListenerService=(EventListenerService)SpringUtils.getInstance().getBean("eventListenerService");
	}
	public void notify(EventListenerExecution execution) {
		Map<String,?> variables=execution.getVariables();
		this.logger.info("======>status:"+status);
		//this.logger.info(variables);
		this.logger.info(execution.getId());
		String method=execution.getId().split("\\.")[0];
		if(eventListenerService!=null){
			try {this.logger.info(method);			
				eventListenerService.getClass().getMethod(method, new Class[]{String.class,String.class}).invoke(eventListenerService, new Object[]{execution.getId(),status});
			} catch (Exception e) {
				logger.error(e);
			}
		}else{
			this.logger.info("eventListenerService is null");
		}
	}
	public static void main(String args[]){
		String a="quit.970018";
		System.out.println(a.split("\\.")[0]);
	}
}
