package org.platform.snail.edu.model;

import java.util.Date;

public class DwmReport {
    private String dwmReportId;

    private String type;

    private String categoryId;

    private String subCategoryId;

    private String title;

    private String weekId;

    private String publisher;

    private Date publishTime;

    private String departmentId;

    private String authorGroupsId;

    private String authorDepartmentId;

    private String status;

    private Date createTime;

    private String content;

    public String getDwmReportId() {
        return dwmReportId;
    }

    public void setDwmReportId(String dwmReportId) {
        this.dwmReportId = dwmReportId == null ? null : dwmReportId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId == null ? null : subCategoryId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getWeekId() {
        return weekId;
    }

    public void setWeekId(String weekId) {
        this.weekId = weekId == null ? null : weekId.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getAuthorGroupsId() {
        return authorGroupsId;
    }

    public void setAuthorGroupsId(String authorGroupsId) {
        this.authorGroupsId = authorGroupsId == null ? null : authorGroupsId.trim();
    }

    public String getAuthorDepartmentId() {
        return authorDepartmentId;
    }

    public void setAuthorDepartmentId(String authorDepartmentId) {
        this.authorDepartmentId = authorDepartmentId == null ? null : authorDepartmentId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}