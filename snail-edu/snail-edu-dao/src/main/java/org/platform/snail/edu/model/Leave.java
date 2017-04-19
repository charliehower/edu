package org.platform.snail.edu.model;

import java.util.Date;

public class Leave {
    private String leaveId;

    private String leaveUserId;

    private String categoryId;

    private Date timeStart;

    private Date timeEnd;

    private Long leaveDates;

    private String fromUserId;

    private String toUserId;

    private String fromClassLesson;

    private String toClassLesson;

    private String toHmUserId;

    private String toGlUserId;

    private String toDpUserId;

    private String toOtherUserId;

    private String leaveReason;

    private String status;

    private Date applicationTime;

    private Date createTime;
    
    private String auditStatus;
    private String auditRemark;
    
    private long c;
    

    public long getC() {
		return c;
	}

	public void setC(long c) {
		this.c = c;
	}

	public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId == null ? null : leaveId.trim();
    }

    public String getLeaveUserId() {
        return leaveUserId;
    }

    public void setLeaveUserId(String leaveUserId) {
        this.leaveUserId = leaveUserId == null ? null : leaveUserId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getLeaveDates() {
        return leaveDates;
    }

    public void setLeaveDates(Long leaveDates) {
        this.leaveDates = leaveDates;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
    }

    public String getFromClassLesson() {
        return fromClassLesson;
    }

    public void setFromClassLesson(String fromClassLesson) {
        this.fromClassLesson = fromClassLesson == null ? null : fromClassLesson.trim();
    }

    public String getToClassLesson() {
        return toClassLesson;
    }

    public void setToClassLesson(String toClassLesson) {
        this.toClassLesson = toClassLesson == null ? null : toClassLesson.trim();
    }

    public String getToHmUserId() {
        return toHmUserId;
    }

    public void setToHmUserId(String toHmUserId) {
        this.toHmUserId = toHmUserId == null ? null : toHmUserId.trim();
    }

    public String getToGlUserId() {
        return toGlUserId;
    }

    public void setToGlUserId(String toGlUserId) {
        this.toGlUserId = toGlUserId == null ? null : toGlUserId.trim();
    }

    public String getToDpUserId() {
        return toDpUserId;
    }

    public void setToDpUserId(String toDpUserId) {
        this.toDpUserId = toDpUserId == null ? null : toDpUserId.trim();
    }

    public String getToOtherUserId() {
        return toOtherUserId;
    }

    public void setToOtherUserId(String toOtherUserId) {
        this.toOtherUserId = toOtherUserId == null ? null : toOtherUserId.trim();
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason == null ? null : leaveReason.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
    
}