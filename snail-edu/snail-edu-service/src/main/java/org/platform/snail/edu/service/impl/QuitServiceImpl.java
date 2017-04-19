package org.platform.snail.edu.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.QuitMapper;
import org.platform.snail.edu.model.Quit;
import org.platform.snail.edu.service.QuitService;
import org.platform.snail.edu.vo.QuitQVo;
import org.platform.snail.edu.vo.QuitVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("quitService")
public class QuitServiceImpl implements QuitService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QuitMapper quitMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findQuitList(QuitQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<QuitVo> list = this.quitMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.quitMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertQuit(JSONObject json, SystemUser systemUser)
			throws Exception {
		Quit o = new Quit();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getQuitId())) {
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
		
		int temp = this.quitMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}*/
		this.quitMapper.insert(o);
		this.dataBaseLogService.log("添加离职申请", "离职申请", "", o.getInstanceId(),
				o.getInstanceId(), systemUser);
		return new DataResponse(true, "添加离职申请完成！");
	}

	public DataResponse updateQuit(JSONObject json, SystemUser systemUser)
			throws Exception {
		Quit o = new Quit();
		SnailBeanUtils.copyProperties(o, json);
		/*if (SnailUtils.isBlankString(o.getQuitId())) {
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
		
		this.quitMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更离职申请", "离职申请", "", o.getInstanceId(),
				o.getInstanceId(), systemUser);
		return new DataResponse(true, "变更离职申请完成！");
	}

	public DataResponse selectQuitByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.quitMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteQuitByQuitId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.quitMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除离职申请", "离职申请", String.valueOf(id), String.valueOf(id),
				"离职申请", systemUser);
		return rst;
	}
	public  DataResponse isExitByTeacherId(String teacherId) throws Exception{
		int t=this.quitMapper.isExitByTeacherId(teacherId);
		if(t>0){
			Quit  o=this.quitMapper.selectByPrimaryKey(teacherId);
			return new DataResponse(false,"已存在申请记录"+o.getInstanceId()+"时间"+o.getCteateTime().toLocaleString()+"，不能申请！");
		}
		return new DataResponse(true,"无申请记录可以申请！");
	}
}
