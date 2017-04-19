package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.ClassesMapper;
import org.platform.snail.edu.model.Classes;
import org.platform.snail.edu.service.ClassesService;
import org.platform.snail.edu.vo.ClassesQVo;
import org.platform.snail.edu.vo.ClassesVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ClassesMapper classesMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findClassesList(ClassesQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<ClassesVo> list = this.classesMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.classesMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertClasses(JSONObject json, SystemUser systemUser)
			throws Exception {
		Classes o = new Classes();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlankString(o.getClassesId())) {
			return new DataResponse(false, "班级编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getClassesName())) {
			return new DataResponse(false, "班级名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "所属年级不能为空！");
		}
		int temp = this.classesMapper.isExitByName(o.getClassesName());
		if (temp > 0) {
			return new DataResponse(false, "已存在同名称的数据！");
		}
		this.classesMapper.insert(o);
		this.dataBaseLogService.log("添加班级", "班级", "", o.getClassesName(),
				o.getClassesName(), systemUser);
		return new DataResponse(true, "添加班级完成！");
	}

	public DataResponse updateClasses(JSONObject json, SystemUser systemUser)
			throws Exception {
		Classes o = new Classes();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlankString(o.getClassesId())) {
			return new DataResponse(false, "班级编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getClassesName())) {
			return new DataResponse(false, "班级名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "所属年级不能为空！");
		}
		
		this.classesMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更班级", "班级", "", o.getClassesName(),
				o.getClassesName(), systemUser);
		return new DataResponse(true, "变更班级完成！");
	}

	public DataResponse selectClassesByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.classesMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteClassesByClassesId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse(true,"删除成功！");
		this.classesMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除班级", "班级", String.valueOf(id), String.valueOf(id),
				"班级", systemUser);
		return rst;
	}

}
