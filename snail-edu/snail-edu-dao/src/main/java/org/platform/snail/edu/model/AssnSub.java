package org.platform.snail.edu.model;

import java.util.Date;

public class AssnSub {
    private String assnSubId;

    private String assnId;

    private String categoryId;

    private String studentId;

    private String studentName;

    private String status;

    private Date regTime;

    private String auditor;

    private Date auditTime;

    private Date createTime;

    private String deiscri;

    public String getAssnSubId() {
        return assnSubId;
    }

    public void setAssnSubId(String assnSubId) {
        this.assnSubId = assnSubId == null ? null : assnSubId.trim();
    }

    public String getAssnId() {
        return assnId;
    }

    public void setAssnId(String assnId) {
        this.assnId = assnId == null ? null : assnId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeiscri() {
        return deiscri;
    }

    public void setDeiscri(String deiscri) {
        this.deiscri = deiscri == null ? null : deiscri.trim();
    }
}