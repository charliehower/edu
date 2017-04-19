package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.AssnMapper;
import org.platform.snail.edu.model.Assn;
import org.platform.snail.edu.model.AssnSub;
import org.platform.snail.edu.service.AssnService;
import org.platform.snail.edu.vo.AssnQVo;
import org.platform.snail.edu.vo.AssnVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("assnService")
public class AssnServiceImpl implements AssnService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AssnMapper assnMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findAssnList(AssnQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<AssnVo> list = this.assnMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.assnMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertAssn(JSONObject json, SystemUser systemUser)
			throws Exception {
		int memberSize=0;
		Assn o = new Assn();
		SnailBeanUtils.copyProperties(o, json);
		o.setAssnId(String.valueOf(new Date().getTime()));
		o.setStatus("1");
		if (SnailUtils.isBlankString(o.getAssnName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getYears())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getAdviser())) {
			return new DataResponse(false, "指导老师不能为空！");
		}
		if (o.getLimitMax()==null) {
			return new DataResponse(false, "社团人数不能为空！");
		}
		if (o.getRegStartDate()==null) {
			return new DataResponse(false, "报名开始日期不能为空！");
		}
		if (o.getRegDeadline()==null) {
			return new DataResponse(false, "报名截止日期不能为空！");
		}
		o.setCreateTime(new Date());
		String str=SnailUtils.parseDate(o.getRegDeadline())+" 23:59:59:00";
		this.logger.info(str);
		o.setRegDeadline(SnailUtils.parseDate(str, "yyyy-MM-dd HH:mm:ss"));
		int temp = this.assnMapper.isExitByRecord(o);
		if (temp > 0) {
			return new DataResponse(false, "已存在此名称的数据！");
		}
		if(SnailUtils.isNotBlankString(o.getData1())){
			for(String e:o.getData1().split(";")){
				memberSize+=1;
			}
		}
		if(SnailUtils.isNotBlankString(o.getData2())){
			for(String e:o.getData2().split(";")){
				memberSize+=1;
			}
		}
		if (memberSize > o.getLimitMax()) {
			return new DataResponse(false, "成员超过社团设定的人数"+o.getLimitMax()+"！");
		}
		if(SnailUtils.isNotBlankString(o.getData1())){
			for(String e:o.getData1().split(";")){
				Map<String,Object> record =new HashMap<String,Object>();
				record.put("assnSubId", UUID.randomUUID().toString());
				record.put("assnId",o.getAssnId());
				record.put("categoryId","1");
				record.put("studentId",e.split(",")[0]);
				record.put("studentName",e.split(",")[1]);
				record.put("auditor",systemUser.getUsers().getName());
				this.assnMapper.insertAssnSub(record);
			}
		}
		if(SnailUtils.isNotBlankString(o.getData2())){
			for(String e:o.getData2().split(";")){
				Map<String,Object> record =new HashMap<String,Object>();
				record.put("assnSubId", UUID.randomUUID().toString());
				record.put("assnId",o.getAssnId());
				record.put("categoryId","0");
				record.put("studentId",e.split(",")[0]);
				record.put("studentName",e.split(",")[1]);
				record.put("auditor",systemUser.getUsers().getName());
				this.assnMapper.insertAssnSub(record);
			}
		}
		this.logger.info(o.getData1());
		this.logger.info(o.getData2());
		this.assnMapper.insert(o);
		this.dataBaseLogService.log("添加社团", "社团", "", o.getAssnName(),
				o.getAssnName(), systemUser);
		return new DataResponse(true, "添加社团完成！");
	}

	public DataResponse updateAssn(JSONObject json, SystemUser systemUser)
			throws Exception {
		Assn o = new Assn();
		SnailBeanUtils.copyProperties(o, json);
		this.logger.info(json);
		this.logger.info(o);
		if (SnailUtils.isBlankString(o.getAssnId())) {
			return new DataResponse(false, "社团编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getAssnName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getYears())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getAdviser())) {
			return new DataResponse(false, "指导老师不能为空！");
		}
		if (o.getLimitMax()==null) {
			return new DataResponse(false, "社团人数不能为空！");
		}
		if (o.getRegStartDate()==null) {
			return new DataResponse(false, "报名开始日期不能为空！");
		}
		if (o.getRegDeadline()==null) {
			return new DataResponse(false, "报名截止日期不能为空！");
		}
		
		String str=SnailUtils.parseDate(o.getRegDeadline())+" 23:59:59:00";
		this.logger.info(str);
		o.setRegDeadline(SnailUtils.parseDate(str, "yyyy-MM-dd HH:mm:ss"));
		
		o.setCreateTime(new Date());
		int memberSize=0;
		if(SnailUtils.isNotBlankString(o.getData1())){
			for(String e:o.getData1().split(";")){
				memberSize+=1;
			}
		}
		if(SnailUtils.isNotBlankString(o.getData2())){
			for(String e:o.getData2().split(";")){
				memberSize+=1;
			}
		}
		if (memberSize > o.getLimitMax()) {
			return new DataResponse(false, "成员超过社团设定的人数"+o.getLimitMax()+"！");
		}
		this.logger.info(o.getData1());
		this.logger.info(o.getData2());
		this.assnMapper.updateByPrimaryKey(o);
		if(SnailUtils.isNotBlankString(o.getData1())){
			this.assnMapper.deleteSubByAssnId(o.getAssnId(),"1");
			for(String e:o.getData1().split(";")){
				Map<String,Object> record =new HashMap<String,Object>();
				record.put("assnSubId", UUID.randomUUID().toString());
				record.put("assnId",o.getAssnId());
				record.put("categoryId","1");
				record.put("studentId",e.split(",")[0]);
				record.put("studentName",e.split(",")[1]);
				record.put("auditor",systemUser.getUsers().getName());
				this.assnMapper.insertAssnSub(record);
			}
		}
		if(SnailUtils.isNotBlankString(o.getData2())){
			this.assnMapper.deleteSubByAssnId(o.getAssnId(),"0");
			for(String e:o.getData2().split(";")){
				Map<String,Object> record =new HashMap<String,Object>();
				record.put("assnSubId", UUID.randomUUID().toString());
				record.put("assnId",o.getAssnId());
				record.put("categoryId","0");
				record.put("studentId",e.split(",")[0]);
				record.put("studentName",e.split(",")[1]);
				record.put("auditor",systemUser.getUsers().getName());
				this.assnMapper.insertAssnSub(record);
			}
		}
		this.dataBaseLogService.log("变更社团", "社团", "", o.getAssnName(),
				o.getAssnName(), systemUser);
		return new DataResponse(true, "变更社团完成！");
	}

	public DataResponse selectAssnByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		List<Map<String,Object>> listSub=this.assnMapper.selectAssnSubByAssnId(id);
		rst.setList(listSub);;
		rst.setResponse(this.assnMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteAssnByAssnId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.assnMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除社团", "社团", String.valueOf(id), String.valueOf(id),
				"社团", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateAssn(JSONObject json, SystemUser systemUser)
			throws Exception {
		Assn o = new Assn();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "社团不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
	*/
		o.setCreateTime(new Date());
		
		
		return new DataResponse(true, "变更社团完成！");
	}
	public DataResponse insertAssnSubReg(JSONObject json, SystemUser systemUser)
			throws Exception {
		AssnSub o = new AssnSub();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		o.setAssnSubId(UUID.randomUUID().toString());
		o.setStatus("0");
		o.setStudentId(systemUser.getUsers().getUserId());
		o.setStudentName(systemUser.getUsers().getName());
		int t=this.assnMapper.isReg(o);
		if (t > 0) {
			return new DataResponse(false, "已经申请过的社团，无需再次申请");
		}
		AssnVo obj=this.assnMapper.selectByPrimaryKey(o.getAssnId());
		Date today=new Date();
		if(obj.getRegStartDate().after(today)){
			return new DataResponse(false, "申请开通日期为"+SnailUtils.parseDate(obj.getRegStartDate()));
		}
		if(obj.getRegDeadline().before(today)){
			return new DataResponse(false, "申请截至日期为"+SnailUtils.parseDate(obj.getRegDeadline()));
		}
		this.assnMapper.insertAssnSubReg(o);
		this.dataBaseLogService.log("参加社团", "社团", o.getAssnId(),  o.getAssnId(),
				"社团", systemUser);
		
		return new DataResponse(true, "申请完成！");
	}
}
