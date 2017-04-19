package org.platform.snail.edu.model;

import java.util.Date;


public class Salarya {


	private String salaryId;

    private String salaryImportId;

    private Integer 序号;

    private String 职工卡号;

    private String 姓名;

    private String 基本工资;

    private String 绩效工资;

    private String 应发工资;

    private String 个人缴交社保;

    private String 个人缴交住房公积金;

    private String 所得税;

    private String 应扣合计;
    
    private String 房改住房补贴;

    private String 单位社保;

    private String 单位缴交住房公积金;

    private String 实发合计;

    private Date createTime;

	public String getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}

	public String getSalaryImportId() {
		return salaryImportId;
	}

	public void setSalaryImportId(String salaryImportId) {
		this.salaryImportId = salaryImportId;
	}

	public Integer get序号() {
		return 序号;
	}

	public void set序号(Integer 序号) {
		this.序号 = 序号;
	}

	public String get职工卡号() {
		return 职工卡号;
	}

	public void set职工卡号(String 职工卡号) {
		this.职工卡号 = 职工卡号;
	}

	public String get姓名() {
		return 姓名;
	}

	public void set姓名(String 姓名) {
		this.姓名 = 姓名;
	}

	public String get基本工资() {
		return 基本工资;
	}

	public void set基本工资(String 基本工资) {
		this.基本工资 = 基本工资;
	}

	public String get绩效工资() {
		return 绩效工资;
	}

	public void set绩效工资(String 绩效工资) {
		this.绩效工资 = 绩效工资;
	}

	public String get应发工资() {
		return 应发工资;
	}

	public void set应发工资(String 应发工资) {
		this.应发工资 = 应发工资;
	}

	public String get个人缴交社保() {
		return 个人缴交社保;
	}

	public void set个人缴交社保(String 个人缴交社保) {
		this.个人缴交社保 = 个人缴交社保;
	}

	public String get个人缴交住房公积金() {
		return 个人缴交住房公积金;
	}

	public void set个人缴交住房公积金(String 个人缴交住房公积金) {
		this.个人缴交住房公积金 = 个人缴交住房公积金;
	}

	public String get所得税() {
		return 所得税;
	}

	public void set所得税(String 所得税) {
		this.所得税 = 所得税;
	}

	public String get应扣合计() {
		return 应扣合计;
	}

	public void set应扣合计(String 应扣合计) {
		this.应扣合计 = 应扣合计;
	}

	public String get房改住房补贴() {
		return 房改住房补贴;
	}

	public void set房改住房补贴(String 房改住房补贴) {
		this.房改住房补贴 = 房改住房补贴;
	}

	public String get单位社保() {
		return 单位社保;
	}

	public void set单位社保(String 单位社保) {
		this.单位社保 = 单位社保;
	}

	public String get单位缴交住房公积金() {
		return 单位缴交住房公积金;
	}

	public void set单位缴交住房公积金(String 单位缴交住房公积金) {
		this.单位缴交住房公积金 = 单位缴交住房公积金;
	}

	public String get实发合计() {
		return 实发合计;
	}

	public void set实发合计(String 实发合计) {
		this.实发合计 = 实发合计;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Salarya [salaryId=" + salaryId + ", salaryImportId="
				+ salaryImportId + ", 序号=" + 序号 + ", 职工卡号=" + 职工卡号 + ", 姓名="
				+ 姓名 + ", 基本工资=" + 基本工资 + ", 绩效工资=" + 绩效工资 + ", 应发工资=" + 应发工资
				+ ", 个人缴交社保=" + 个人缴交社保 + ", 个人缴交住房公积金=" + 个人缴交住房公积金 + ", 所得税="
				+ 所得税 + ", 应扣合计=" + 应扣合计 + ", 房改住房补贴=" + 房改住房补贴 + ", 单位社保="
				+ 单位社保 + ", 单位缴交住房公积金=" + 单位缴交住房公积金 + ", 实发合计=" + 实发合计
				+ ", createTime=" + createTime + "]";
	}

   
}