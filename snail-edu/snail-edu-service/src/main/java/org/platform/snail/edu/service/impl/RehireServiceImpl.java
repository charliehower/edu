package org.platform.snail.edu.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.RehireMapper;
import org.platform.snail.edu.model.Quit;
import org.platform.snail.edu.model.Rehire;
import org.platform.snail.edu.service.RehireService;
import org.platform.snail.edu.vo.RehireQVo;
import org.platform.snail.edu.vo.RehireVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rehireService")
public class RehireServiceImpl implements RehireService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RehireMapper rehireMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findRehireList(RehireQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<RehireVo> list = this.rehireMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.rehireMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertRehire(JSONObject json, SystemUser systemUser)
			throws Exception {
		Rehire o = new Rehire();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getRehireId())) {
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
		
		int temp = this.rehireMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.rehireMapper.insert(o);
		this.dataBaseLogService.log("添加返聘", "返聘", "", o.getInstanceId(),
				o.getInstanceId(), systemUser);
		return new DataResponse(true, "添加返聘完成！");
	}

	public DataResponse updateRehire(JSONObject json, SystemUser systemUser)
			throws Exception {
		Rehire o = new Rehire();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getRehireId())) {
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
		
		this.rehireMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更返聘", "返聘", "", o.getInstanceId(),
				o.getInstanceId(), systemUser);
		return new DataResponse(true, "变更返聘完成！");
	}

	public DataResponse selectRehireByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.rehireMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteRehireByRehireId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.rehireMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除返聘", "返聘", String.valueOf(id), String.valueOf(id),
				"返聘", systemUser);
		return rst;
	}
	public  DataResponse isExitByTeacherId(String teacherId) throws Exception{
		int t=this.rehireMapper.isExitByTeacherId(teacherId);
		if(t>0){
			Rehire  o=this.rehireMapper.selectByPrimaryKey(teacherId);
			return new DataResponse(false,"已存在申请记录"+o.getInstanceId()+"时间"+o.getCreateTime().toLocaleString()+"，不能申请！");
		}
		return new DataResponse(true,"无申请记录可以申请！");
	}

}
