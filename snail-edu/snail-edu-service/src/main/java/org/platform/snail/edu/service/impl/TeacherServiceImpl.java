package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.QuitMapper;
import org.platform.snail.edu.dao.TcEmployeeMapper;
import org.platform.snail.edu.dao.TeacherMapper;
import org.platform.snail.edu.model.Quit;
import org.platform.snail.edu.model.TcEmployee;
import org.platform.snail.edu.model.Teacher;
import org.platform.snail.edu.service.TeacherService;
import org.platform.snail.edu.vo.TeacherQVo;
import org.platform.snail.edu.vo.TeacherVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private TcEmployeeMapper tcEmployeeMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Autowired
	private QuitMapper quitMapper;

	Map<String, String> dict = new HashMap<String, String>();

	public DataResponse findTeacherList(TeacherQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<TeacherVo> list = this.teacherMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.teacherMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertTeacher(JSONObject json, SystemUser systemUser)
			throws Exception {
		Teacher o = new Teacher();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}
		if (!SnailUtils.isBlankString(o.getIdCard())) {
			int temp = this.teacherMapper.isExitByIdCard(o.getIdCard());
			if (temp > 0) {
				return new DataResponse(false, "已存在此身份证的数据！");
			}
		}

		if (o.getTeacherId().startsWith("T")
				|| o.getTeacherId().startsWith("t")) {
			o.setCategory("1");
		} else if (o.getTeacherId().startsWith("P")
				|| o.getTeacherId().startsWith("p")) {
			o.setCategory("2");
		} else {
			o.setCategory("3");
		}
		o.setCreateTime(new Date());
		this.teacherMapper.insert(o);
		this.dataBaseLogService.log("添加教职工", "教职工", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "添加教职工完成！");
	}

	public DataResponse updateTeacher(JSONObject json, SystemUser systemUser)
			throws Exception {
		Teacher o = new Teacher();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "姓名不能为空！");
		}
		if (SnailUtils.isBlankString(o.getSex())) {
			return new DataResponse(false, "性别不能为空！");
		}

		if (o.getTeacherId().startsWith("T")
				|| o.getTeacherId().startsWith("t")) {
			o.setCategory("1");
		} else if (o.getTeacherId().startsWith("P")
				|| o.getTeacherId().startsWith("p")) {
			o.setCategory("2");
		} else {
			o.setCategory("3");
		}
		this.teacherMapper.updateByPrimaryKeyWithBLOBs(o);
		this.dataBaseLogService.log("变更教职工", "教职工", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "变更教职工完成，如果没有分配角色，请分配角色！");
	}

	public DataResponse selectTeacherByPrimaryKey(String id) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.teacherMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteTeacherByTeacherId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.teacherMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除教职工", "教职工", String.valueOf(id),
				String.valueOf(id), "教职工", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdateUsersByTeacherId(String teacherId,
			SystemUser systemUser) throws Exception {
		this.teacherMapper.saveOrUpdateUsersByTeacherId(teacherId);
		this.dataBaseLogService.log("同步教职工系统信息", "教职工", teacherId, teacherId,
				"教职工", systemUser);
		return new DataResponse(true, "同步教职工系统信息完成！");
	}

	public DataResponse createUsersByTeacherIds(JSONObject json,
			SystemUser systemUser) throws Exception {
		JSONArray ja = json.getJSONArray("list");
		String teacherId = null;
		String name = null;
		for (Object o : ja) {
			JSONObject jo = (JSONObject) o;
			teacherId = jo.getString("teacherId");
			name = jo.getString("name");
			this.teacherMapper.saveOrUpdateUsersByTeacherId(teacherId);
			this.dataBaseLogService.log("同步教职工系统信息", name, teacherId,
					teacherId, "教职工", systemUser);
		}
		return new DataResponse(true, "同步教职工系统信息完成！");
	}

	public DataResponse updateGroup(JSONObject json, SystemUser systemUser)
			throws Exception {
		Teacher o = new Teacher();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTeacherId())) {
			return new DataResponse(false, "员工编号不能为空！");
		}
		this.teacherMapper.updateGroupByPrimaryKey(o);
		this.dataBaseLogService.log("变更教职工组信息", "教职工", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "变更教职工完成！");
	}

	private Map<String, String> getMap(Map<String, String> o) {
		Map<String, String> map = new HashMap<String, String>();
		Set<String> set = o.keySet();
		Iterator<String> it = set.iterator();
		String key = null;
		while (it.hasNext()) {
			key = it.next();
			if (dict.containsKey(o.get(key))) {
				map.put(key, dict.get(o.get(key)));
			} else {
				map.put(key, o.get(key));
			}
		}
		this.logger.info(map);
		return map;
	}

	public DataResponse importTeacher(@RequestParam MultipartFile[] files,
			SystemUser systemUser) throws Exception {
		ExcelUtils utils = new ExcelUtils();
		List<Map<String, String>> list = null;
		List<Map<String, String>> items = this.teacherMapper.getDictList();
		for (Map<String, String> map : items) {
			dict.put(map.get("NAME"), map.get("CODE"));
		}
		this.logger.info("init dict map");
		this.logger.info(dict);
		int t = 0;
		int i = 0;
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
			for (Map<String, String> row : list) {
				Teacher o = new Teacher();

				SnailBeanUtils.copyMap2Bean(o, this.getMap(row));
				o.setCreateTime(new Date());

				this.logger.info(o.toString());
				if (SnailUtils.isBlankString(o.getTeacherId())) {
					return new DataResponse(false, "行" + i + " 员工编号/一卡通卡号不能为空！");
				}
				if (SnailUtils.isBlankString(o.getName())) {
					return new DataResponse(false, "行" + i + " 姓名不能为空！");
				}
				if (SnailUtils.isBlankString(o.getSex())) {
					return new DataResponse(false, "行" + i + " 性别不能为空！");
				}
				if (SnailUtils.isBlankString(o.getCategory())) {
					return new DataResponse(false, "行" + i + " 职工类别不能为空！");
				}
				t = teacherMapper.isExitByIdTeacherId(o.getTeacherId());
				if (t > 0) {
					this.teacherMapper.updateByPrimaryKey(o);

				} else {
					this.teacherMapper.insert(o);
				}
				i++;
			}
		}

		this.dataBaseLogService.log("教职工导入", "教职工", "", systemUser.getUsers()
				.getName(), systemUser.getUsers().getName(), systemUser);
		return new DataResponse(true, "教职工导入完成,共" + i + "人导入！");
	}

	public DataResponse updateQuitByIdTeacherId(String id, SystemUser systemUser)
			throws Exception {
		DataResponse rst = new DataResponse(true, "离职成功！");
		Quit o = new Quit();
		o.setCteateTime(new Date());
		o.setReasion(null);
		o.setInstanceId(id);
		o.setLeader(null);
		o.setQuitTime(new Date());
		o.setRemark("一键离职");
		o.setQuitCategory("01");
		o.setTeacherId(id);
		this.quitMapper.insert(o);
		this.dataBaseLogService.log("教职工离职", "教职工", String.valueOf(id),
				String.valueOf(id), "教职工", systemUser);
		return rst;
	}

	public void sync() throws Exception {
		this.logger.info("start sync ");
		List<TcEmployee> list = tcEmployeeMapper.getList();
		for (TcEmployee e : list) {
			this.logger.info("<同步开卡职工信息>");
			this.logger.info(e);
			Teacher o = new Teacher();
			o.setTeacherId(e.getEmployeestrid());
			o.setSex("2");
			if(e.getSexid()){
				o.setSex("1");
			}
			o.setName(e.getEmployeename());
			o.setIdCard(e.getIdentifier());
			o.setEmail(e.getEmail());
			o.setTel(e.getEmployeetel());
			if (o.getTeacherId().startsWith("T")
					|| o.getTeacherId().startsWith("t")) {
				o.setCategory("1");
			} else if (o.getTeacherId().startsWith("P")
					|| o.getTeacherId().startsWith("p")) {
				o.setCategory("2");
			} else {
				o.setCategory("3");
			}
			o.setCreateTime(new Date());
			this.teacherMapper.insert(o);
			this.teacherMapper.saveOrUpdateUsersByTeacherId(o.getTeacherId());
		}
		this.logger.info("complete sync ");
	}
}
