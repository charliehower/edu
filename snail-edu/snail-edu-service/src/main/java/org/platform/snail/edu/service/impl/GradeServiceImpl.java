package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.GradeMapper;
import org.platform.snail.edu.model.Grade;
import org.platform.snail.edu.service.GradeService;
import org.platform.snail.edu.vo.GradeQVo;
import org.platform.snail.edu.vo.GradeVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gradeService")
public class GradeServiceImpl implements GradeService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GradeMapper gradeMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findGradeList(GradeQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<GradeVo> list = this.gradeMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.gradeMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertGrade(JSONObject json, SystemUser systemUser)
			throws Exception {
		Grade o = new Grade();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeName())) {
			return new DataResponse(false, "名称不能为空！");
		}	
		int temp = this.gradeMapper.isExitByName(o.getGradeName());
		if (temp > 0) {
			return new DataResponse(false, "已存同名称的数据！");
		}
		this.gradeMapper.insert(o);
		this.dataBaseLogService.log("添加年级", "年级", "", o.getGradeName(),
				o.getGradeName(), systemUser);
		return new DataResponse(true, "添加年级完成！");
	}

	public DataResponse updateGrade(JSONObject json, SystemUser systemUser)
			throws Exception {
		Grade o = new Grade();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeName())) {
			return new DataResponse(false, "名称不能为空！");
		}	
		this.gradeMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更年级", "年级", "", o.getGradeName(),
				o.getGradeName(), systemUser);
		return new DataResponse(true, "变更年级完成！");
	}

	public DataResponse selectGradeByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.gradeMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteGradeByGradeId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse(true,"删除成功！");
		this.gradeMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除年级", "年级", String.valueOf(id), String.valueOf(id),
				"年级", systemUser);
		return rst;
	}
	public DataResponse updateUpgrade(String year, SystemUser systemUser)
			throws Exception {
		int t=this.gradeMapper.isExitById(year);
		if(t>0){
			return new DataResponse(false, "升级失败,当前年度已经进行过升级！");
		}
		Grade o = new Grade();
		o.setGradeId(year);
		o.setGradeName(year+"毕业班");
		o.setCreateTime(new Date());
		this.gradeMapper.insert(o);
		this.gradeMapper.updateUpgrade(year);
		this.dataBaseLogService.log("升级", "升级", "",year,
				year, systemUser);
		return new DataResponse(true, "升级完成！");
	}
	public DataResponse updateunDoUpgrade(String year, SystemUser systemUser)
			throws Exception {
		int t=this.gradeMapper.isExitById(year);
		if(t<=0){
			return new DataResponse(false, "回退失败,当前年度没有进行过升级！");
		}
		;
		
		this.gradeMapper.updateunDoUpgrade(year);
		this.gradeMapper.deleteByPrimaryKey(year);
		this.dataBaseLogService.log("回退升级", "回退升级", "",year,
				year, systemUser);
		return new DataResponse(true, "回退完成！");
	}

}
