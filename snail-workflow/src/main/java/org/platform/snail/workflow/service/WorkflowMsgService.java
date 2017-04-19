/**
 * @Title: WorkflowMsgService.java
 * @Package org.platform.snail.workflow.service
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年2月8日 下午12:31:36
 * @version V1.0
 */

package org.platform.snail.workflow.service;

/**
 * @ClassName: WorkflowMsgService
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年2月8日 下午12:31:36
 *
 */

public interface WorkflowMsgService {
	
	void repairs(String instanceId,String assignee,String status);
	
}
