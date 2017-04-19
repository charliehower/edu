package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.PublicClassMapper;
import org.platform.snail.edu.dao.PublicClassSubMapper;
import org.platform.snail.edu.dao.TaskCmccMapper;
import org.platform.snail.edu.model.PublicClass;
import org.platform.snail.edu.model.PublicClassSub;
import org.platform.snail.edu.model.PublicClassWithBLOBs;
import org.platform.snail.edu.model.TaskCmcc;
import org.platform.snail.edu.service.PublicClassService;
import org.platform.snail.edu.service.TemplateService;
import org.platform.snail.edu.vo.PublicClassQVo;
import org.platform.snail.edu.vo.PublicClassVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("publicClassService")
public class PublicClassServiceImpl implements PublicClassService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PublicClassMapper publicClassMapper;
	@Autowired
	private PublicClassSubMapper publicClassSubMapper;
	@Autowired
	private TaskCmccMapper taskCmccMapper;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findPublicClassList(PublicClassQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<PublicClassVo> list = this.publicClassMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.publicClassMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertPublicClass(JSONObject json, SystemUser systemUser)
			throws Exception {
		PublicClassWithBLOBs o = new PublicClassWithBLOBs();
		SnailBeanUtils.copyProperties(o, json);
		o.setPublicClassId(String.valueOf(new Date().getTime()));
		
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "上课内容不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDiscriplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getYears())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGrade())) {
			return new DataResponse(false, "年级不能为空！");
		}
		if (SnailUtils.isEmptyObj(o.getRegStartDate())) {
			return new DataResponse(false, "报名开始日期不能为空！");
		}
		if (SnailUtils.isEmptyObj(o.getRegDeadline())) {
			return new DataResponse(false, "报名截止日期不能为空！");
		}
		if (SnailUtils.isEmptyObj(o.getTaskDate())) {
			return new DataResponse(false, "讲课日期不能为空！");
		}
		int temp = this.publicClassMapper.isExitByTitle(o.getTitle());
		if (temp > 0) {
			return new DataResponse(false, "已存在上此课内容的数据！");
		}
		String str=SnailUtils.parseDate(o.getRegDeadline())+" 23:59:59:00";
		this.logger.info(str);
		o.setRegDeadline(SnailUtils.parseDate(str, "yyyy-MM-dd HH:mm:ss"));
		o.setCreateTime(new Date());
		o.setStatus("01");
		o.setTeacherId(systemUser.getUsers().getUserId());
		this.publicClassMapper.insert(o);
		this.dataBaseLogService.log("添加公开课", "公开课", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "添加公开课完成！");
	}

	public DataResponse updatePublicClass(JSONObject json, SystemUser systemUser)
			throws Exception {
		PublicClassWithBLOBs o = new PublicClassWithBLOBs();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getPublicClassId())) {
			return new DataResponse(false, "公开课编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTitle())) {
			return new DataResponse(false, "上课内容不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDiscriplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getYears())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGrade())) {
			return new DataResponse(false, "年级不能为空！");
		}
		if (SnailUtils.isEmptyObj(o.getRegStartDate())) {
			return new DataResponse(false, "报名开始日期不能为空！");
		}
		if (SnailUtils.isEmptyObj(o.getRegDeadline())) {
			return new DataResponse(false, "报名截止日期不能为空！");
		}
		if (SnailUtils.isEmptyObj(o.getTaskDate())) {
			return new DataResponse(false, "讲课日期不能为空！");
		}
		String str=SnailUtils.parseDate(o.getRegDeadline())+" 23:59:59:00";
		this.logger.info(str);
		o.setRegDeadline(SnailUtils.parseDate(str, "yyyy-MM-dd HH:mm:ss"));
		this.publicClassMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更公开课", "公开课", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "变更公开课完成！");
	}

	public DataResponse selectPublicClassByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setList(this.publicClassSubMapper.selectListByid(id));
		rst.setResponse(this.publicClassMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deletePublicClassByPublicClassId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.publicClassMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除公开课", "公开课", String.valueOf(id), String.valueOf(id),
				"公开课", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdatePublicClass(JSONObject json, SystemUser systemUser)
			throws Exception {
		PublicClass o = new PublicClass();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "公开课不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
	*/
		o.setCreateTime(new Date());
		
		this.dataBaseLogService.log("变更公开课", "公开课", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更公开课完成！");
	}
	public  DataResponse updateAudit(String id,String status,String remark,SystemUser systemUser) throws Exception{
		if (SnailUtils.isBlankString(id)) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(status)) {
			return new DataResponse(false, "审核状态码不能为空！");
		}
		this.publicClassMapper.updateAudit(id, systemUser.getUsers().getUserId(), status,remark);
		this.dataBaseLogService.log("公开课审核", "公开课", "", id,
				id, systemUser);
		return new DataResponse(true, "公开课审核完成！");
	}
	public  DataResponse updateRelease(String id,String status,SystemUser systemUser) throws Exception{
		if (SnailUtils.isBlankString(id)) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(status)) {
			return new DataResponse(false, "发布状态码不能为空！");
		}
		this.publicClassMapper.updateRelease(id, systemUser.getUsers().getUserId(), status);
		this.dataBaseLogService.log("公开课发布", "公开课", "", id,
				id, systemUser);
		return new DataResponse(true, "公开课发布完成！");
	}
	public  DataResponse updateScore(String id,float score,String remark,SystemUser systemUser) throws Exception{
		if (SnailUtils.isBlankString(id)) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(remark)) {
			return new DataResponse(false, "评审内容不能为空！");
		}
		this.publicClassMapper.updateScore(id, score, remark,systemUser.getUsers().getUserId());
		this.dataBaseLogService.log("公开课评课", "公开课", "", id,
				id, systemUser);
		return new DataResponse(true, "公开课评课完成！");
	}
	public  DataResponse selectListByid(String id) throws Exception{
		DataResponse rst = new DataResponse();
		List<Map<String,Object>> list = this.publicClassSubMapper.selectListByid(id);
		rst.setList(list);
		return rst;
	}
	public  DataResponse insertJoin(String id,SystemUser systemUser) throws Exception{
		DataResponse rst = new DataResponse(true,"报名成功！");
		if (SnailUtils.isBlankString(id)) {
			return new DataResponse(false, "功课课编号不能为空！");
		}
		
		PublicClassVo obj=this.publicClassMapper.selectByPrimaryKey(id);
		this.logger.info(obj.getStatus());
		if(!obj.getStatus().equals("已发布")){
			return new DataResponse(false, "非发布过的状态");
		}
		Date today=new Date();
		if(obj.getRegStartDate().after(today)){
			return new DataResponse(false, "申请开通日期为"+SnailUtils.parseDate(obj.getRegStartDate()));
		}
		if(obj.getRegDeadline().before(today)){
			return new DataResponse(false, "申请截至日期为"+SnailUtils.parseDate(obj.getRegDeadline()));
		}
		PublicClassSub o=new PublicClassSub();
		o.setPublicClassSubId(UUID.randomUUID().toString());
		o.setTeacherId(systemUser.getUsers().getUserId());
		o.setPublicClassId(id);
		o.setRegTime(new Date());
		o.setCreateTime(new Date());
		int t=this.publicClassSubMapper.isReg(o);
		if (t > 0) {
			return new DataResponse(false, "已经报名，无需再次报名");
		}
		this.publicClassSubMapper.insert(o);
		this.dataBaseLogService.log("公开课报名", "公开课", "", id,
				id, systemUser);
		return rst;
	}
	public void taskPublicClassAlert()
			throws Exception {
		this.logger.info("公开课短信提醒排程任务开始");
		 List<Map<String,Object>>  list=this.publicClassMapper.selectTaskAlertList();
		for(Map<String,Object> p:list){
			TaskCmcc o = new TaskCmcc();
			String expression = templateService.get("publicClass");
			this.logger.info(expression);
			this.logger.info(p);
			o.setTaskName("公开课听课提醒,"+p.get("teacher_name"));
			o.setMsg(SnailUtils.getStringByExpression(expression, p));
			o.setTel((String)p.get("msg")+";");
			o.setCreateTime(new Date());
			o.setCreateUserId("1");
			o.setStatus("0");
			o.setTaskId(String.valueOf(new Date().getTime()));
			this.logger.info(o.getMsg());
			this.logger.info(o.getTel());
			this.taskCmccMapper.insert(o);
			this.publicClassMapper.updateAlertStatus((String)p.get("public_class_id"));
		}
		
	}
	public  DataResponse updateAuditSec(String id,String status,String remark,SystemUser systemUser) throws Exception{
		if (SnailUtils.isBlankString(id)) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(status)) {
			return new DataResponse(false, "审核状态码不能为空！");
		}
		PublicClass o=this.publicClassMapper.selectOnlyOne(id);
		if(!o.getStatus().equals("02")){
			return new DataResponse(false, "非上级审核通过的数据！");
		}
		if(o.getAuditSecStatus().equals("1")){
			return new DataResponse(false, "审核通过的数据不能审核！");
		}
		this.publicClassMapper.updateAuditSec(id, systemUser.getUsers().getUserId(), status,remark);
		this.dataBaseLogService.log("公开课二级审核", "公开课", "", id,
				id, systemUser);
		return new DataResponse(true, "公开课二级审核完成！");
	}
	public  DataResponse updateAuditThi(String id,String status,String remark,SystemUser systemUser) throws Exception{
		if (SnailUtils.isBlankString(id)) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(status)) {
			return new DataResponse(false, "审核状态码不能为空！");
		}
		PublicClass o=this.publicClassMapper.selectOnlyOne(id);
		if(!o.getAuditSecStatus().equals("1")){
			return new DataResponse(false, "非上级审核通过的数据！");
		}
		if(o.getAuditThiStatus().equals("1")){
			return new DataResponse(false, "审核通过的数据不能审核！");
		}
		this.publicClassMapper.updateAuditThi(id, systemUser.getUsers().getUserId(), status,remark);
		this.dataBaseLogService.log("公开课三级审核", "公开课", "", id,
				id, systemUser);
		return new DataResponse(true, "公开课三级审核完成！");
	}
	public  DataResponse updateAuditFor(String id,String status,String remark,SystemUser systemUser) throws Exception{
		if (SnailUtils.isBlankString(id)) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(status)) {
			return new DataResponse(false, "审核状态码不能为空！");
		}
		PublicClass o=this.publicClassMapper.selectOnlyOne(id);
		if(!o.getAuditThiStatus().equals("1")){
			return new DataResponse(false, "非上级审核通过的数据！");
		}
		if(o.getAuditForStatus().equals("1")){
			return new DataResponse(false, "审核通过的数据不能审核！");
		}
		this.publicClassMapper.updateAuditFor(id, systemUser.getUsers().getUserId(), status,remark);
		this.dataBaseLogService.log("公开课四级审核", "公开课", "", id,
				id, systemUser);
		return new DataResponse(true, "公开课四级审核完成！");
	}
}
