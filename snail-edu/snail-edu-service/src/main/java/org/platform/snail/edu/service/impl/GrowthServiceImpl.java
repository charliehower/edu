package org.platform.snail.edu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.GrowthMapper;
import org.platform.snail.edu.model.Growth;
import org.platform.snail.edu.service.GrowthService;
import org.platform.snail.edu.vo.GrowthQVo;
import org.platform.snail.edu.vo.GrowthVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("growthService")
public class GrowthServiceImpl implements GrowthService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GrowthMapper growthMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public DataResponse findGrowthList(GrowthQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<GrowthVo> list = this.growthMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.growthMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertGrowth(JSONObject json, SystemUser systemUser)
			throws Exception {
		String id = String.valueOf(new Date().getTime());
		Growth o = new Growth();
		SnailBeanUtils.copyProperties(o, json);
		o.setGrowthId(id);
		o.setCreateTime(new Date());
		o.setCheckerA(systemUser.getUsers().getUserId());
		totalAll(o);
		if (SnailUtils.isBlank(o.getGrowthId())) {
			return new DataResponse(false, "不能为空！");
		}
		if (SnailUtils.isBlank(o.getClassesId())) {
			return new DataResponse(false, "班级编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		int temp = this.growthMapper.isExit(o);
		if (temp > 0) {
			return new DataResponse(false, "已存在的数据！");
		}
		this.growthMapper.insert(o);
		this.dataBaseLogService.log("添加成长记录", "成长记录", "", o.getClassesId(),
				o.getClassesId(), systemUser);
		return new DataResponse(true, "添加成长记录完成！");
	}

	public DataResponse updateGrowth(JSONObject json, SystemUser systemUser)
			throws Exception {
		Growth o = new Growth();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		totalAll(o);
		boolean enable=true;
		if (SnailUtils.isBlank(o.getGrowthId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		if(enable&&(SnailUtils.isBlank(o.getCheckerA())||o.getCheckerA().equals(systemUser.getUsers().getUserId()))){
			o.setCheckerA(systemUser.getUsers().getUserId());
			enable=false;
		}
		if(enable&&(SnailUtils.isBlank(o.getCheckerB())||o.getCheckerB().equals(systemUser.getUsers().getUserId()))){
			o.setCheckerB(systemUser.getUsers().getUserId());
			enable=false;
		}
		if(enable&&(SnailUtils.isBlank(o.getCheckerC())||o.getCheckerC().equals(systemUser.getUsers().getUserId()))){
			o.setCheckerC(systemUser.getUsers().getUserId());
			enable=false;
		}
		if(enable&&(SnailUtils.isBlank(o.getCheckerD())||o.getCheckerD().equals(systemUser.getUsers().getUserId()))){
			o.setCheckerD(systemUser.getUsers().getUserId());
			enable=false;
		}
		this.growthMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更成长记录", "成长记录", "", o.getClassesId(),
				o.getClassesId(), systemUser);
		return new DataResponse(true, "变更成长记录完成！");
	}

	public DataResponse selectGrowthByPrimaryKey(String id,SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		boolean enable=true;
		GrowthVo o=this.growthMapper.selectByPrimaryKey(id);
		if(enable&&(SnailUtils.isBlank(o.getCheckeraName())||o.getCheckeraName().equals(systemUser.getUsers().getName()))){
			o.setCheckeraName(systemUser.getUsers().getName());
			enable=false;
		}
		if(enable&&(SnailUtils.isBlank(o.getCheckerbName())||o.getCheckerbName().equals(systemUser.getUsers().getName()))){
			o.setCheckerbName(systemUser.getUsers().getName());
			enable=false;
		}
		if(enable&&(SnailUtils.isBlank(o.getCheckercName())||o.getCheckercName().equals(systemUser.getUsers().getName()))){
			o.setCheckercName(systemUser.getUsers().getName());
			enable=false;
		}
		if(enable&&(SnailUtils.isBlank(o.getCheckerdName())||o.getCheckerdName().equals(systemUser.getUsers().getName()))){
			o.setCheckerdName(systemUser.getUsers().getName());
			enable=false;
		}
		rst.setResponse(o);
		return rst;
	}
	private void totalAll(Growth o){
		o.setTotalScore(new java.math.BigDecimal("0"));
		if(o.getAttendance()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getAttendance()));
		}
		if(o.getHygieneAreas()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getHygieneAreas()));
		}
		if(o.getHygieneTools()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getHygieneTools()));
		}
		if(o.getCeremony()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getCeremony()));
		}
		if(o.getLiveFlag()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getLiveFlag()));
		}
		if(o.getExerciseBody()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getExerciseBody()));
		}
		if(o.getExerciseEye()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getExerciseEye()));
		}
		if(o.getSecurity()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getSecurity()));
		}
		if(o.getContraband()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getContraband()));
		}
		if(o.getContrabandWeekend()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getContrabandWeekend()));
		}
		if(o.getDormitory()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getDormitory()));
		}
		if(o.getConstruction()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getConstruction()));
		}
		if(o.getBonus()!=null){
			o.setTotalScore(o.getTotalScore().add(o.getBonus()));
		}
	}

	public DataResponse deleteGrowthByGrowthId(String id, SystemUser systemUser)
			throws Exception {
		DataResponse rst = new DataResponse();
		this.growthMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除成长记录", "成长记录", String.valueOf(id),
				String.valueOf(id), "成长记录", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdateGrowth(JSONObject json,
			SystemUser systemUser) throws Exception {
		Growth o = new Growth();
		SnailBeanUtils.copyProperties(o, json);
		if(this.growthMapper.isExit(o)>0){
			this.growthMapper.updateByPrimaryKey(o);
			this.dataBaseLogService.log("变更成长记录", "成长记录", "", o.getClassesId(),
					o.getClassesId(), systemUser);
		}else{
			o.setGrowthId(String.valueOf(new Date().getTime()));
			this.growthMapper.insert(o);
			this.dataBaseLogService.log("添加成长记录", "成长记录", "", o.getClassesId(),
					o.getClassesId(), systemUser);
		}
		return new DataResponse(true, "登记完成！");
	}

	public DataResponse importXls(MultipartFile[] files, JSONObject json,
			SystemUser systemUser) throws Exception {
		ExcelUtils utils = new ExcelUtils();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (MultipartFile file : files) {

			String ext = file
					.getOriginalFilename()
					.toLowerCase()
					.substring(
							file.getOriginalFilename().toLowerCase()
									.lastIndexOf("."));
			this.logger.info(ext);
			if (ext.equals(".xls")) {
				list = utils.readExcelByJXL(file.getInputStream(), 2);
			}
			if (ext.equals(".xlsx")) {
				list = utils.readExcelByPOI(file.getInputStream(), 2);
			}
			int i = 0;
			for (Map<String, String> row : list) {
				Growth o = new Growth();
				// o.setGrowthId(row.get("考号"));
				o.setCreateTime(new Date());
				this.logger.info(o.toString());
				/*
				 * if (SnailUtils.isBlankString(o.getGrowthId())) { return new
				 * DataResponse(false, "行"+i+"考号不能为空！"); }
				 */
				int t = growthMapper.isExit(o);
				if (t > 0) {
					this.growthMapper.updateByPrimaryKey(o);

				} else {
					this.growthMapper.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("信息导入", "成长记录", "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "成长记录信息导入工完成！");
	}
}
