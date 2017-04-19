package org.platform.snail.edu.model;

import java.util.Date;

public class Positive {
    private String instanceId;

    private String teacherId;

    private Date positiveTime;

    private String rs;

    private String lo;

    private String leader;

    private String remark;

    private Date cteateTime;

    private String evaluation;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public Date getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(Date positiveTime) {
        this.positiveTime = positiveTime;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs == null ? null : rs.trim();
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo == null ? null : lo.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCteateTime() {
        return cteateTime;
    }

    public void setCteateTime(Date cteateTime) {
        this.cteateTime = cteateTime;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation == null ? null : evaluation.trim();
    }
}