package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.TeamPrepareMapper;
import org.platform.snail.edu.model.TeamPrepare;
import org.platform.snail.edu.service.TeamPrepareService;
import org.platform.snail.edu.vo.TeamPrepareQVo;
import org.platform.snail.edu.vo.TeamPrepareVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teamPrepareService")
public class TeamPrepareServiceImpl implements TeamPrepareService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TeamPrepareMapper teamPrepareMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findTeamPrepareList(TeamPrepareQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		/*List<TeamPrepareVo> list = this.teamPrepareMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.teamPrepareMapper.findCount(condition);
			rst.setAllRows(allRows);
		}*/
		return rst;
	}

	public DataResponse insertTeamPrepare(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamPrepare o = new TeamPrepare();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getTeamPrepareId())) {
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
		
		int temp = this.teamPrepareMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.teamPrepareMapper.insert(o);
		this.dataBaseLogService.log("添加备课组", "备课组", "","",
				"", systemUser);
		return new DataResponse(true, "添加备课组完成！");
	}

	public DataResponse updateTeamPrepare(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamPrepare o = new TeamPrepare();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getTeamPrepareId())) {
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
		
		this.teamPrepareMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更备课组", "备课组", "", "",
				"", systemUser);
		return new DataResponse(true, "变更备课组完成！");
	}

	public DataResponse selectTeamPrepareByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.teamPrepareMapper.selectByPrimaryKey(Integer.valueOf(id)));
		return rst;
	}

	public DataResponse deleteTeamPrepareByTeamPrepareId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.teamPrepareMapper.deleteByPrimaryKey(Integer.valueOf(id));
		this.dataBaseLogService.log("删除备课组", "备课组", String.valueOf(id), String.valueOf(id),
				"备课组", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateTeamPrepare(JSONObject json, SystemUser systemUser)
			throws Exception {
		TeamPrepare o = new TeamPrepare();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "教职工不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
	
		o.setCreateTime(new Date());
		
		this.teamPrepareMapper.saveOrUpdateTeamPrepare(o);
		this.dataBaseLogService.log("变更备课组", "备课组", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);
		return new DataResponse(true, "变更备课组完成！");
	}

}
