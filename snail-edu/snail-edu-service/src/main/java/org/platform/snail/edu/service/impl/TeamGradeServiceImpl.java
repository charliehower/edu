package org.platform.snail.edu.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.TeamGradeMapper;
import org.platform.snail.edu.model.TeamGrade;
import org.platform.snail.edu.service.TeamGradeService;
import org.platform.snail.edu.vo.TeamGradeQVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teamGradeService")
public class TeamGradeServiceImpl implements TeamGradeService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TeamGradeMapper teamGradeMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findTeamGradeList(TeamGradeQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		/*List<TeamGradeVo> list = this.teamGradeMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.teamGradeMapper.findCount(condition);
			rst.setAllRows(allRows);
		}*/
		return rst;
	}

	public DataResponse insertTeamGrade(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamGrade o = new TeamGrade();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getTeamGradeId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.teamGradeMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.teamGradeMapper.insert(o);
		this.dataBaseLogService.log("添加年级组长", "年级组长", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "添加年级组长完成！");
	}

	public DataResponse updateTeamGrade(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamGrade o = new TeamGrade();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getTeamGradeId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}*/
		o.setCreateTime(new Date());
		this.teamGradeMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更年级组长", "年级组长", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更年级组长完成！");
	}

	public DataResponse selectTeamGradeByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.teamGradeMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteTeamGradeByTeamGradeId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.teamGradeMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除年级组长", "年级组长", String.valueOf(id), String.valueOf(id),
				"年级组长", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateTeamGrade(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamGrade o = new TeamGrade();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "年级组长不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
	
		o.setCreateTime(new Date());
		
		this.teamGradeMapper.saveOrUpdateTeamGrade(o);
		this.dataBaseLogService.log("变更年级组长", "年级组长", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更年级组长完成！");
	}
}
