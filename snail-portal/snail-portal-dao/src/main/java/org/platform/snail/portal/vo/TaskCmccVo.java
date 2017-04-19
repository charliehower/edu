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

public class TaskCmccVo extends TaskCmcc{
	    private String name;
	    private String departmentName;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
	    
}
