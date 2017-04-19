/**
 * @Title: QueueCmccWaitVo.java
 * @Package org.platform.snail.portal.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package org.platform.snail.portal.vo;

import org.platform.snail.portal.model.QueueCmccWait;

/**
 * @ClassName: QueueCmccWaitVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class QueueCmccWaitQVo extends QueueCmccWait{
	 private String startDate;
	    private String endDate;
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
}
