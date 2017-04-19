package org.platform.snail.edu.model;

import java.util.Date;
import java.math.BigDecimal;
public class Growth {
    private String growthId;

    private String classesId;

    private BigDecimal attendance;

    private BigDecimal hygieneAreas;

    private BigDecimal hygieneTools;

    private BigDecimal ceremony;

    private BigDecimal liveFlag;

    private BigDecimal exerciseBody;

    private BigDecimal exerciseEye;

    private BigDecimal security;

    private BigDecimal contraband;

    private BigDecimal contrabandWeekend;

    private BigDecimal dormitory;

    private BigDecimal construction;

    private BigDecimal bonus;

    private BigDecimal totalScore;

    private Date checkDate;

    private String checkerA;

    private String checkerB;

    private String checkerC;

    private String checkerD;

    private Date createTime;

    private String remark;

    public String getGrowthId() {
        return growthId;
    }

    public void setGrowthId(String growthId) {
        this.growthId = growthId == null ? null : growthId.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public BigDecimal getAttendance() {
        return attendance;
    }

    public void setAttendance(BigDecimal attendance) {
        this.attendance = attendance;
    }

    public BigDecimal getHygieneAreas() {
        return hygieneAreas;
    }

    public void setHygieneAreas(BigDecimal hygieneAreas) {
        this.hygieneAreas = hygieneAreas;
    }

    public BigDecimal getHygieneTools() {
        return hygieneTools;
    }

    public void setHygieneTools(BigDecimal hygieneTools) {
        this.hygieneTools = hygieneTools;
    }

    public BigDecimal getCeremony() {
        return ceremony;
    }

    public void setCeremony(BigDecimal ceremony) {
        this.ceremony = ceremony;
    }

    public BigDecimal getLiveFlag() {
        return liveFlag;
    }

    public void setLiveFlag(BigDecimal liveFlag) {
        this.liveFlag = liveFlag;
    }

    public BigDecimal getExerciseBody() {
        return exerciseBody;
    }

    public void setExerciseBody(BigDecimal exerciseBody) {
        this.exerciseBody = exerciseBody;
    }

    public BigDecimal getExerciseEye() {
        return exerciseEye;
    }

    public void setExerciseEye(BigDecimal exerciseEye) {
        this.exerciseEye = exerciseEye;
    }

    public BigDecimal getSecurity() {
        return security;
    }

    public void setSecurity(BigDecimal security) {
        this.security = security;
    }

    public BigDecimal getContraband() {
        return contraband;
    }

    public void setContraband(BigDecimal contraband) {
        this.contraband = contraband;
    }

    public BigDecimal getContrabandWeekend() {
        return contrabandWeekend;
    }

    public void setContrabandWeekend(BigDecimal contrabandWeekend) {
        this.contrabandWeekend = contrabandWeekend;
    }

    public BigDecimal getDormitory() {
        return dormitory;
    }

    public void setDormitory(BigDecimal dormitory) {
        this.dormitory = dormitory;
    }

    public BigDecimal getConstruction() {
        return construction;
    }

    public void setConstruction(BigDecimal construction) {
        this.construction = construction;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckerA() {
        return checkerA;
    }

    public void setCheckerA(String checkerA) {
        this.checkerA = checkerA == null ? null : checkerA.trim();
    }

    public String getCheckerB() {
        return checkerB;
    }

    public void setCheckerB(String checkerB) {
        this.checkerB = checkerB == null ? null : checkerB.trim();
    }

    public String getCheckerC() {
        return checkerC;
    }

    public void setCheckerC(String checkerC) {
        this.checkerC = checkerC == null ? null : checkerC.trim();
    }

    public String getCheckerD() {
        return checkerD;
    }

    public void setCheckerD(String checkerD) {
        this.checkerD = checkerD == null ? null : checkerD.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}