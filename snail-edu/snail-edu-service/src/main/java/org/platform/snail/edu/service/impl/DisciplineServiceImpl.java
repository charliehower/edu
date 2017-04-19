package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.DisciplineMapper;
import org.platform.snail.edu.model.Discipline;
import org.platform.snail.edu.service.DisciplineService;
import org.platform.snail.edu.vo.DisciplineQVo;
import org.platform.snail.edu.vo.DisciplineVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("disciplineService")
public class DisciplineServiceImpl implements DisciplineService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DisciplineMapper disciplineMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findDisciplineList(DisciplineQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<DisciplineVo> list = this.disciplineMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.disciplineMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertDiscipline(JSONObject json, SystemUser systemUser)
			throws Exception {
		Discipline o = new Discipline();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.disciplineMapper.isExitByName(o.getDisciplineName());
		if (temp > 0) {
			return new DataResponse(false, "已存在同名称的数据！");
		}
		this.disciplineMapper.insert(o);
		this.dataBaseLogService.log("添加学科", "学科", "", o.getDisciplineName(),
				o.getDisciplineName(), systemUser);
		return new DataResponse(true, "添加学科完成！");
	}

	public DataResponse updateDiscipline(JSONObject json, SystemUser systemUser)
			throws Exception {
		Discipline o = new Discipline();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		o.setCreateTime(new Date());
		this.disciplineMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更学科", "学科", "", o.getDisciplineName(),
				o.getDisciplineName(), systemUser);
		return new DataResponse(true, "变更学科完成！");
	}

	public DataResponse selectDisciplineByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.disciplineMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteDisciplineByDisciplineId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.disciplineMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除学科", "学科", String.valueOf(id), String.valueOf(id),
				"学科", systemUser);
		return rst;
	}

}
