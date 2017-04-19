package org.platform.snail.edu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Duty {
    private String dutyId;

    private Date dutyStart;

    private Date dutyEnd;

    private String remark;

    private Date createTime;
    
    List<DutyDetail> list=new ArrayList<DutyDetail>();

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId == null ? null : dutyId.trim();
    }

    public Date getDutyStart() {
        return dutyStart;
    }

    public void setDutyStart(Date dutyStart) {
        this.dutyStart = dutyStart;
    }

    public Date getDutyEnd() {
        return dutyEnd;
    }

    public void setDutyEnd(Date dutyEnd) {
        this.dutyEnd = dutyEnd;
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

	public List<DutyDetail> getList() {
		return list;
	}

	public void setList(List<DutyDetail> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Duty [dutyId=" + dutyId + ", dutyStart=" + dutyStart
				+ ", dutyEnd=" + dutyEnd + ", remark=" + remark
				+ ", createTime=" + createTime + "]";
	}
    
}