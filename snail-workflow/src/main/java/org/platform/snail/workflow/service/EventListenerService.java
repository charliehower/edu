/**
 * @Title: EventListenerService.java
 * @Package org.platform.snail.workflow.service
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月30日 上午11:18:18
 * @version V1.0
 */

package org.platform.snail.workflow.service;

/**
 * @ClassName: EventListenerService
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月30日 上午11:18:18
 *
 */

public interface EventListenerService {
	void positive(String instanceId,String status) throws Exception;
	void quit(String instanceId,String status) throws Exception;
	void rehire(String instanceId,String status) throws Exception;
	void repairs(String instanceId,String status) throws Exception;
	void leave(String instanceId,String status) throws Exception;
}
