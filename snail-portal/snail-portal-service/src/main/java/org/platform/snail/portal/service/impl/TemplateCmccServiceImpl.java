package org.platform.snail.portal.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.TemplateCmccMapper;
import org.platform.snail.portal.model.TemplateCmcc;
import org.platform.snail.portal.service.TemplateCmccService;
import org.platform.snail.portal.vo.TemplateCmccQVo;
import org.platform.snail.portal.vo.TemplateCmccVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("templateCmccService")
public class TemplateCmccServiceImpl implements TemplateCmccService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TemplateCmccMapper templateCmccMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findTemplateCmccList(TemplateCmccQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<TemplateCmccVo> list = this.templateCmccMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.templateCmccMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertTemplateCmcc(JSONObject json, SystemUser systemUser)
			throws Exception {
		TemplateCmcc o = new TemplateCmcc();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTemplateCmccId())) {
			return new DataResponse(false, "模板编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "模板不能为空！");
		}
		int temp = this.templateCmccMapper.isExitByName(o.getName());
		if (temp > 0) {
			return new DataResponse(false, "已存在此身份证的数据！");
		}
		o.setCreateTime(new Date());
		this.templateCmccMapper.insert(o);
		this.dataBaseLogService.log("添加模板", "模板", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "添加模板完成！");
	}

	public DataResponse updateTemplateCmcc(JSONObject json, SystemUser systemUser)
			throws Exception {
		TemplateCmcc o = new TemplateCmcc();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getTemplateCmccId())) {
			return new DataResponse(false, "模板编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getContent())) {
			return new DataResponse(false, "模板不能为空！");
		}
		o.setCreateTime(new Date());
		
		this.templateCmccMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更模板", "模板", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "变更模板完成！");
	}

	public DataResponse selectTemplateCmccByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.templateCmccMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteTemplateCmccByTemplateCmccId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.templateCmccMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除模板", "模板", String.valueOf(id), String.valueOf(id),
				"模板", systemUser);
		return rst;
	}

}
