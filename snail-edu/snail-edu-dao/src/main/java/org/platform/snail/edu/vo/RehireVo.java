/**
 * @Title: RehireVo.java
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
 * @ClassName: RehireVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class RehireVo extends TeacherVo {
	private String instanceId;

	private Date rehireTime;

	private String reasion;

	private String leader;

	private String lo;

	private String remark;

	private Date auditTime;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public Date getRehireTime() {
		return rehireTime;
	}

	public void setRehireTime(Date rehireTime) {
		this.rehireTime = rehireTime;
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

	public String getLo() {
		return lo;
	}

	public void setLo(String lo) {
		this.lo = lo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

}
