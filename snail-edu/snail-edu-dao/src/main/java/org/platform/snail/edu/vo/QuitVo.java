/**
 * @Title: QuitVo.java
 * @Package org.platform.snail.edu.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package org.platform.snail.edu.vo;

import java.util.Date;


/**
 * @ClassName: QuitVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class QuitVo extends TeacherVo{
		private String instanceId;

	    private Date quitTime;

	    private String quitCategory;

	    private String reasion;

	    private String leader;


	    private Date auditTime;


		public String getInstanceId() {
			return instanceId;
		}


		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}


		public Date getQuitTime() {
			return quitTime;
		}


		public void setQuitTime(Date quitTime) {
			this.quitTime = quitTime;
		}


		public String getQuitCategory() {
			return quitCategory;
		}


		public void setQuitCategory(String quitCategory) {
			this.quitCategory = quitCategory;
		}


		public String getReasion() {
			return reasion;
		}


		public void setReasion(String reasion) {
			this.reasion = reasion;
		}


		public String getLeader() {
			return leader;
		}


		public void setLeader(String leader) {
			this.leader = leader;
		}


		public Date getAuditTime() {
			return auditTime;
		}


		public void setAuditTime(Date auditTime) {
			this.auditTime = auditTime;
		}
	    
	    
}
