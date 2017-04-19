package org.platform.snail.edu.model;

import java.util.Date;

public class DocFlow {
    private String id;

    private String docDept;

    private String docDate;

    private String docNo;

    private String nativeNo;

    private String title;
    private String content;

    private String deployDate;

    private String deployUser;

    private String status;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDocDept() {
        return docDept;
    }

    public void setDocDept(String docDept) {
        this.docDept = docDept == null ? null : docDept.trim();
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate == null ? null : docDate.trim();
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo == null ? null : docNo.trim();
    }

    public String getNativeNo() {
        return nativeNo;
    }

    public void setNativeNo(String nativeNo) {
        this.nativeNo = nativeNo == null ? null : nativeNo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(String deployDate) {
        this.deployDate = deployDate == null ? null : deployDate.trim();
    }

    public String getDeployUser() {
        return deployUser;
    }

    public void setDeployUser(String deployUser) {
        this.deployUser = deployUser == null ? null : deployUser.trim();
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
		this.content = content;
	}
    
}