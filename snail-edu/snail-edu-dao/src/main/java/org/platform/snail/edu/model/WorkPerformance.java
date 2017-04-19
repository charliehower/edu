package org.platform.snail.edu.model;

import java.util.Date;

public class WorkPerformance {
	
    private Integer workPerformanceId;

    private String teacherId;

    private String classes;

    private String duty;

    private String record;

    private Date createTime;

    private String description;

    public Integer getWorkPerformanceId() {
        return workPerformanceId;
    }

    public void setWorkPerformanceId(Integer workPerformanceId) {
        this.workPerformanceId = workPerformanceId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}