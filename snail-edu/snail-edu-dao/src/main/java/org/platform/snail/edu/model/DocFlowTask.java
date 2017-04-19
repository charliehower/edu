package org.platform.snail.edu.model;

import java.util.Date;

public class DocFlowTask {
    private String id;

    private String pid;

    private String docFlowId;

    private String piStatus;

    private String piContent;

    private Date piDate;

    private String piUser;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDocFlowId() {
        return docFlowId;
    }

    public void setDocFlowId(String docFlowId) {
        this.docFlowId = docFlowId == null ? null : docFlowId.trim();
    }

    public String getPiStatus() {
        return piStatus;
    }

    public void setPiStatus(String piStatus) {
        this.piStatus = piStatus == null ? null : piStatus.trim();
    }

    public String getPiContent() {
        return piContent;
    }

    public void setPiContent(String piContent) {
        this.piContent = piContent == null ? null : piContent.trim();
    }

    public Date getPiDate() {
        return piDate;
    }

    public void setPiDate(Date piDate) {
        this.piDate = piDate;
    }

    public String getPiUser() {
        return piUser;
    }

    public void setPiUser(String piUser) {
        this.piUser = piUser == null ? null : piUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}