package org.platform.snail.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.DutyMapper;
import org.platform.snail.edu.model.Duty;
import org.platform.snail.edu.model.DutyDetail;
import org.platform.snail.edu.service.DutyService;
import org.platform.snail.edu.vo.DutyQVo;
import org.platform.snail.edu.vo.DutyVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service("dutyService")
public class DutyServiceImpl implements DutyService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DutyMapper dutyMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findDutyList(DutyQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<Map<String,Object>> list = this.dutyMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.dutyMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertDuty(JSONObject json, SystemUser systemUser)
			throws Exception {
		Duty o = new Duty();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getDutyId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDutyId())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.dutyMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.dutyMapper.insert(o);
		this.dataBaseLogService.log("添加值班表", "值班表", "", o.getDutyId(),
				o.getDutyId(), systemUser);
		return new DataResponse(true, "添加值班表完成！");
	}

	public DataResponse updateDuty(JSONObject json, SystemUser systemUser)
			throws Exception {
		Duty o = new Duty();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getDutyId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDutyId())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}*/
		o.setCreateTime(new Date());
		this.dutyMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更值班表", "值班表", "", o.getDutyId(),
				o.getDutyId(), systemUser);
		return new DataResponse(true, "变更值班表完成！");
	}

	public DataResponse selectDutyByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.dutyMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteDutyByDutyId(String id,
			SystemUser systemUser) throws Exception {
		this.dutyMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除值班表", "值班表", String.valueOf(id), String.valueOf(id),
				"值班表", systemUser);
		return new DataResponse(true,"删除成功！");
	}
	public DataResponse saveOrUpdateDuty(JSONObject json, SystemUser systemUser)
			throws Exception {
		Duty o = new Duty();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "值班表不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
	*/
		o.setCreateTime(new Date());
		
		this.dutyMapper.saveOrUpdateDuty(o);
		this.dataBaseLogService.log("变更值班表", "值班表", "", o.getDutyId(),
				o.getDutyId(), systemUser);
		return new DataResponse(true, "变更值班表完成！");
	}
	public  DataResponse importDuty(@RequestParam MultipartFile[] files,SystemUser systemUser)throws Exception{
		ExcelUtils utils=new ExcelUtils();
		List<Map<String,String>> list=null;
		List<Duty> o=new ArrayList<Duty>();
		for(MultipartFile file:files){
			String ext=file.getOriginalFilename().toLowerCase().substring(file.getOriginalFilename().toLowerCase().lastIndexOf("."));
			this.logger.info(ext);
			if(ext.equals(".xls")){
				list=utils.readExcelByJXL(file.getInputStream(), 1);
			}
			if(ext.equals(".xlsx")){
				list=utils.readExcelByPOI(file.getInputStream(), 1);
			}
		}
		String dutya="";
		String dutyb="";
		String tel="";
		String []namesa=null;
		String []namesb=null;
		String []names=null;
		String dutyId=null;
		String name=null;
		for(Map<String,String> row:list){
			dutyId=UUID.randomUUID().toString();
			Duty obj=new Duty();
			obj.setDutyId(dutyId);
			obj.setCreateTime(new Date());
			obj.setDutyStart(SnailUtils.parseDate(row.get("开始时间")));
			obj.setDutyEnd(SnailUtils.parseDate(row.get("结束时间")));
			dutya=row.get("校级领导");
			dutyb=row.get("行政干部");
			if(SnailUtils.isNotBlankString(dutya)){
				if(dutya.indexOf("/")!=-1){
					namesa=dutya.split("/");
				}else{
					namesa=new String[]{dutya};
				}
				names=new String[namesa.length];
				int i=0;
				for(String temp:namesa){
					if(temp.indexOf(":")!=-1){
						name=temp.split(":")[0];
						tel=temp.split(":")[1];
						names[i]=name;
					}else{
						names[i]=temp;
					}
					i++;
				}
				List<Map<String,String>> tmp=this.dutyMapper.selectTeacherListByNames(names);
				for(Map<String,String> t:tmp){
					DutyDetail e=new DutyDetail();
					e.setCategory("1");
					e.setCreateTime(new Date());
					e.setTeacherId(t.get("TEACHER_ID"));
					e.setDutyId(dutyId);
					e.setTel(t.get("TEL"));
					obj.getList().add(e);
				}
				
			}
			if(SnailUtils.isNotBlankString(dutyb)){
				if(dutyb.indexOf("/")!=-1){
					namesb=dutya.split("/");
				}else{
					namesb=new String[]{dutyb};
				}
				names=new String[namesb.length];
				int i=0;
				for(String temp:namesb){
					if(temp.indexOf(":")!=-1){
						name=temp.split(":")[0];
						tel=temp.split(":")[1];
						names[i]=name;
					}else{
						names[i]=temp;
					}
					i++;
				}
				List<Map<String,String>> tmp=this.dutyMapper.selectTeacherListByNames(names);
				for(Map<String,String> t:tmp){
					DutyDetail e=new DutyDetail();
					e.setCategory("2");
					e.setCreateTime(new Date());
					e.setTeacherId(t.get("TEACHER_ID"));
					e.setDutyId(dutyId);
					e.setTel(t.get("TEL"));
					obj.getList().add(e);
				}
			}
			o.add(obj);
		}
		for(Duty e:o){
			this.logger.info(e.toString());
			for(DutyDetail oo:e.getList()){
				this.logger.info(oo.toString());
			}
			this.logger.info("-----------------------");
		}
		this.dutyMapper.batchInsert(o);
		this.dataBaseLogService.log("变更值班表", "值班表", "", systemUser.getUsers().getName(),
				systemUser.getUsers().getName(), systemUser);
		return new DataResponse(true, "变更值班表完成！");
	}
	public static void main(String args[]){
		String tmp="1:2:3:4";
		String []a=tmp.split(":");
		for(String o:a){
			System.out.println(o);
		}
	}
}
