package org.platform.snail.edu.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.TeamTrMapper;
import org.platform.snail.edu.model.TeamTr;
import org.platform.snail.edu.service.TeamTrService;
import org.platform.snail.edu.vo.TeamTrQVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teamTrService")
public class TeamTrServiceImpl implements TeamTrService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TeamTrMapper teamTrMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findTeamTrList(TeamTrQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		/*List<TeamTrVo> list = this.teamTrMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.teamTrMapper.findCount(condition);
			rst.setAllRows(allRows);
		}*/
		return rst;
	}

	public DataResponse insertTeamTr(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamTr o = new TeamTr();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getTeamTrId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.teamTrMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.teamTrMapper.insert(o);
		this.dataBaseLogService.log("添加教研组", "教研组", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "添加教研组完成！");
	}

	public DataResponse updateTeamTr(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamTr o = new TeamTr();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getTeamTrId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}*/
		o.setCreateTime(new Date());
		this.teamTrMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更教研组", "教研组", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更教研组完成！");
	}

	public DataResponse selectTeamTrByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.teamTrMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteTeamTrByTeamTrId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.teamTrMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除教研组", "教研组", String.valueOf(id), String.valueOf(id),
				"教研组", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateTeamTr(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamTr o = new TeamTr();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "教研组不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
	*/
		o.setCreateTime(new Date());
		
		this.teamTrMapper.saveOrUpdateTeamTr(o);
		this.dataBaseLogService.log("变更教研组", "教研组", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更教研组完成！");
	}
}
