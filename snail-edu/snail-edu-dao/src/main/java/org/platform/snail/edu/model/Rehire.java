package org.platform.snail.edu.model;

import java.util.Date;

public class Rehire {
    private String teacherId;

    private String instanceId;

    private Date rehireTime;

    private String reasion;

    private String leader;

    private String lo;

    private String remark;

    private Date createTime;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
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
        this.reasion = reasion == null ? null : reasion.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo == null ? null : lo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}