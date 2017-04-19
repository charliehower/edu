package org.platform.snail.edu.model;

import java.util.Date;

public class Site {
    private String id;

    private String name;

    private Integer advance;

    private String status;

    private String reason;

    private String startHh;

    private String startMm;

    private String endHh;

    private String endMm;

    private String flag;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAdvance() {
        return advance;
    }

    public void setAdvance(Integer advance) {
        this.advance = advance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getStartHh() {
        return startHh;
    }

    public void setStartHh(String startHh) {
        this.startHh = startHh == null ? null : startHh.trim();
    }

    public String getStartMm() {
        return startMm;
    }

    public void setStartMm(String startMm) {
        this.startMm = startMm == null ? null : startMm.trim();
    }

    public String getEndHh() {
        return endHh;
    }

    public void setEndHh(String endHh) {
        this.endHh = endHh == null ? null : endHh.trim();
    }

    public String getEndMm() {
        return endMm;
    }

    public void setEndMm(String endMm) {
        this.endMm = endMm == null ? null : endMm.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}