/**
 * @Title: TaskCmccVo.java
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

import org.platform.snail.portal.model.TaskCmcc;

/**
 * @ClassName: TaskCmccVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class TaskCmccQVo extends TaskCmcc{
	    private String dateStart;
	    private String dateEnd;
		public String getDateStart() {
			return dateStart;
		}
		public void setDateStart(String dateStart) {
			this.dateStart = dateStart;
		}
		public String getDateEnd() {
			return dateEnd;
		}
		public void setDateEnd(String dateEnd) {
			this.dateEnd = dateEnd;
		}
	    
}
