package org.platform.snail.edu.model;

import java.util.Date;

public class DutyDetail {
    private Integer dutyDetailId;

    private String dutyId;

    private String teacherId;

    private String tel;

    private Date createTime;

    private String category;

    public Integer getDutyDetailId() {
        return dutyDetailId;
    }

    public void setDutyDetailId(Integer dutyDetailId) {
        this.dutyDetailId = dutyDetailId;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId == null ? null : dutyId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

	@Override
	public String toString() {
		return "DutyDetail [dutyDetailId=" + dutyDetailId + ", dutyId="
				+ dutyId + ", teacherId=" + teacherId + ", tel=" + tel
				+ ", createTime=" + createTime + ", category=" + category
				+ ", getDutyDetailId()=" + getDutyDetailId() + ", getDutyId()="
				+ getDutyId() + ", getTeacherId()=" + getTeacherId()
				+ ", getTel()=" + getTel() + ", getCreateTime()="
				+ getCreateTime() + ", getCategory()=" + getCategory()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
    
}