package org.platform.snail.portal.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.ConfigMapper;
import org.platform.snail.portal.model.Config;
import org.platform.snail.portal.service.ConfigService;
import org.platform.snail.portal.vo.ConfigQVo;
import org.platform.snail.portal.vo.ConfigVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ConfigMapper configMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findConfigList(ConfigQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<ConfigVo> list = this.configMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.configMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertConfig(JSONObject json, SystemUser systemUser)
			throws Exception {
		Config o = new Config();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlankString(o.getConfigKey())) {
			return new DataResponse(false, "Key不能为空！");
		}
		if (SnailUtils.isBlankString(o.getConfigName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getConfigValue())) {
			return new DataResponse(false, "系统参数值不能为空！");
		}

		
		int temp = this.configMapper.isExitByName(o.getConfigName());
		if (temp > 0) {
			return new DataResponse(false, "已存在此名称的数据！");
		}
		this.configMapper.insert(o);
		this.dataBaseLogService.log("添加系统参数", "系统参数", "", o.getConfigName(),
				o.getConfigName(), systemUser);
		return new DataResponse(true, "添加系统参数完成！");
	}

	public DataResponse updateConfig(JSONObject json, SystemUser systemUser)
			throws Exception {
		Config o = new Config();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlankString(o.getConfigKey())) {
			return new DataResponse(false, "Key不能为空！");
		}
		if (SnailUtils.isBlankString(o.getConfigName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getConfigValue())) {
			return new DataResponse(false, "系统参数值不能为空！");
		}
		this.configMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更系统参数", "系统参数", "", o.getConfigName(),
				o.getConfigName(), systemUser);
		return new DataResponse(true, "变更系统参数完成！");
	}

	public DataResponse selectConfigByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.configMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteConfigByConfigId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.configMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除系统参数", "系统参数", String.valueOf(id), String.valueOf(id),
				"系统参数", systemUser);
		return rst;
	}

}
