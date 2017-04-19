package org.platform.snail.edu.model;

import java.math.BigDecimal;
import java.util.Date;

public class PublicClassSub {
    private String publicClassSubId;

    private String publicClassId;

    private String teacherId;

    private Date regTime;

    private String remark;

    private BigDecimal score;

    private Date scoreTime;

    private Date createTime;

    public String getPublicClassSubId() {
        return publicClassSubId;
    }

    public void setPublicClassSubId(String publicClassSubId) {
        this.publicClassSubId = publicClassSubId == null ? null : publicClassSubId.trim();
    }

    public String getPublicClassId() {
        return publicClassId;
    }

    public void setPublicClassId(String publicClassId) {
        this.publicClassId = publicClassId == null ? null : publicClassId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}