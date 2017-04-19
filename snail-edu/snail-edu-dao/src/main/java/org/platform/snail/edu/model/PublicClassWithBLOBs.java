package org.platform.snail.edu.model;

public class PublicClassWithBLOBs extends PublicClass {
    private String globleTitle;

    private String content;

    private String taskContent;

    private String remark;

    private String auditRemark;

    private String auditSecRemark;

    private String auditThiRemark;

    private String auditForRemark;

    public String getGlobleTitle() {
        return globleTitle;
    }

    public void setGlobleTitle(String globleTitle) {
        this.globleTitle = globleTitle == null ? null : globleTitle.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent == null ? null : taskContent.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public String getAuditSecRemark() {
        return auditSecRemark;
    }

    public void setAuditSecRemark(String auditSecRemark) {
        this.auditSecRemark = auditSecRemark == null ? null : auditSecRemark.trim();
    }

    public String getAuditThiRemark() {
        return auditThiRemark;
    }

    public void setAuditThiRemark(String auditThiRemark) {
        this.auditThiRemark = auditThiRemark == null ? null : auditThiRemark.trim();
    }

    public String getAuditForRemark() {
        return auditForRemark;
    }

    public void setAuditForRemark(String auditForRemark) {
        this.auditForRemark = auditForRemark == null ? null : auditForRemark.trim();
    }
}