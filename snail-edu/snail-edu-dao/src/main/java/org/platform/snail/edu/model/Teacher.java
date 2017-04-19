package org.platform.snail.edu.model;

import java.util.Date;

public class Teacher {
    private String teacherId;

    private String category;

    private String name;

    private String sex;

    private String idCard;

    private String poaf;

    private String eb;

    private String major;

    private String gi;

    private Date gt;

    private Integer ftYear;

    private Integer wYears;

    private String entryTime;

    private String tqcCategory;

    private String tqcNo;

    private String soattr;

    private String ppt;

    private String position;

    private String departmentId;

    private String gradeId;

    private String classesTaught;

    private String disciplineId;

    private String pe;

    private String photo;

    private String address;

    private String domicile;

    private String tel;

    private String email;

    private Date createTime;

    private String resume;
    private String stauts;
    
    private String salaryType;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getPoaf() {
        return poaf;
    }

    public void setPoaf(String poaf) {
        this.poaf = poaf == null ? null : poaf.trim();
    }

    public String getEb() {
        return eb;
    }

    public void setEb(String eb) {
        this.eb = eb == null ? null : eb.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getGi() {
        return gi;
    }

    public void setGi(String gi) {
        this.gi = gi == null ? null : gi.trim();
    }

    public Date getGt() {
        return gt;
    }

    public void setGt(Date gt) {
        this.gt = gt;
    }

    public Integer getFtYear() {
        return ftYear;
    }

    public void setFtYear(Integer ftYear) {
        this.ftYear = ftYear;
    }

    public Integer getwYears() {
        return wYears;
    }

    public void setwYears(Integer wYears) {
        this.wYears = wYears;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime == null ? null : entryTime.trim();
    }

    public String getTqcCategory() {
        return tqcCategory;
    }

    public void setTqcCategory(String tqcCategory) {
        this.tqcCategory = tqcCategory == null ? null : tqcCategory.trim();
    }

    public String getTqcNo() {
        return tqcNo;
    }

    public void setTqcNo(String tqcNo) {
        this.tqcNo = tqcNo == null ? null : tqcNo.trim();
    }

    public String getSoattr() {
        return soattr;
    }

    public void setSoattr(String soattr) {
        this.soattr = soattr == null ? null : soattr.trim();
    }

    public String getPpt() {
        return ppt;
    }

    public void setPpt(String ppt) {
        this.ppt = ppt == null ? null : ppt.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId == null ? null : gradeId.trim();
    }

    public String getClassesTaught() {
        return classesTaught;
    }

    public void setClassesTaught(String classesTaught) {
        this.classesTaught = classesTaught == null ? null : classesTaught.trim();
    }

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId == null ? null : disciplineId.trim();
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe == null ? null : pe.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile == null ? null : domicile.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume == null ? null : resume.trim();
    }

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	
	public String getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", category=" + category
				+ ", name=" + name + ", sex=" + sex + ", idCard=" + idCard
				+ ", poaf=" + poaf + ", eb=" + eb + ", major=" + major
				+ ", gi=" + gi + ", gt=" + gt + ", ftYear=" + ftYear
				+ ", wYears=" + wYears + ", entryTime=" + entryTime
				+ ", tqcCategory=" + tqcCategory + ", tqcNo=" + tqcNo
				+ ", soattr=" + soattr + ", ppt=" + ppt + ", position="
				+ position + ", departmentId=" + departmentId + ", gradeId="
				+ gradeId + ", classesTaught=" + classesTaught
				+ ", disciplineId=" + disciplineId + ", pe=" + pe + ", photo="
				+ photo + ", address=" + address + ", domicile=" + domicile
				+ ", tel=" + tel + ", email=" + email + ", createTime="
				+ createTime + ", resume=" + resume + ", stauts=" + stauts
				+ ", salaryType=" + salaryType + "]";
	}

	
    
}