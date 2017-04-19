package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.StudentMapper;
import org.platform.snail.edu.model.Student;
import org.platform.snail.edu.service.StudentService;
import org.platform.snail.edu.vo.StudentQVo;
import org.platform.snail.edu.vo.StudentVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findStudentList(StudentQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<StudentVo> list = this.studentMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.studentMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertStudent(JSONObject json, SystemUser systemUser)
			throws Exception {
		Student o = new Student();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getStudentId())) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		/*if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.studentMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.studentMapper.insert(o);
		this.dataBaseLogService.log("添加学生", "学生", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "添加学生完成！");
	}

	public DataResponse updateStudent(JSONObject json, SystemUser systemUser)
			throws Exception {
		Student o = new Student();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getStudentId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		/*if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (SnailUtils.isBlankString(o.getIdCard())) {
			return new DataResponse(false, "身份证号不能为空！");
		}*/
		o.setCreateTime(new Date());
		this.studentMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更学生", "学生", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "变更学生完成！");
	}

	public DataResponse selectStudentByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.studentMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteStudentByStudentId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.studentMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除学生", "学生", String.valueOf(id), String.valueOf(id),
				"学生", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateStudent(JSONObject json, SystemUser systemUser)
			throws Exception {
		Student o = new Student();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getYear())) {
			return new DataResponse(false, "学年不能为空！");
		}
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "学生不能为空！");
		}
		if (SnailUtils.isBlankString(o.getDisciplineId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlankString(o.getGradeId())) {
			return new DataResponse(false, "年级不能为空！");
		}
	
		o.setCreateTime(new Date());
		
		this.studentMapper.saveOrUpdateStudent(o);
		this.dataBaseLogService.log("变更学生", "学生", "", o.getTeacherId(),
				o.getTeacherId(), systemUser);*/
		return new DataResponse(true, "变更学生完成！");
	}
}
