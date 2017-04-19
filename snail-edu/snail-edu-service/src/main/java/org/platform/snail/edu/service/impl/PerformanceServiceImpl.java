package org.platform.snail.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.PerformanceMapper;
import org.platform.snail.edu.model.Performance;
import org.platform.snail.edu.service.PerformanceService;
import org.platform.snail.edu.vo.PerformanceQVo;
import org.platform.snail.edu.vo.PerformanceVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("performanceService")
public class PerformanceServiceImpl implements PerformanceService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PerformanceMapper performanceMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public DataResponse findPerformanceList(PerformanceQVo condition,
			int start, int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<PerformanceVo> list = this.performanceMapper.findList(condition,
				start, start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.performanceMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertPerformance(JSONObject json, SystemUser systemUser)
			throws Exception {
		String id = String.valueOf(new Date().getTime());
		Performance o = new Performance();
		SnailBeanUtils.copyProperties(o, json);
		o.setPerformanceId(id);
		o.setRegUserId(systemUser.getUsers().getUserId());
		o.setCreateTime(new Date());
		if (SnailUtils.isBlank(o.getPerformanceId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getName())) {
			return new DataResponse(false, "业务竞赛名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getSection())) {
			return new DataResponse(false, "学段不能为空！");
		}
		if (SnailUtils.isBlank(o.getSubjectId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlank(o.getOrganization())) {
			return new DataResponse(false, "竞赛组织单位不能为空！");
		}
		if (SnailUtils.isBlank(o.getwEntries())) {
			return new DataResponse(false, "获奖作品名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getwLevel())) {
			return new DataResponse(false, "获奖级别 不能为空！");
		}
		if (SnailUtils.isBlank(o.getwGrade())) {
			return new DataResponse(false, "获奖等级不能为空！");
		}
		if (SnailUtils.isBlank(o.getwTime())) {
			return new DataResponse(false, "获奖时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getCategory())) {
			return new DataResponse(false, "类别不能为空！");
		}
		if (SnailUtils.isBlank(o.getRegUserId())) {
			return new DataResponse(false, "申报人不能为空！");
		}
		if (SnailUtils.isBlank(o.getMoney())) {
			return new DataResponse(false, "金额不能为空！");
		}
		if (SnailUtils.isBlank(o.getFile())) {
			return new DataResponse(false, "证明材料不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		int temp = this.performanceMapper.isExit(o);
		if (temp > 0) {
			return new DataResponse(false, "已存在的数据！");
		}
		this.performanceMapper.insert(o);
		this.dataBaseLogService.log("添加业绩", "业绩", "", o.getName(), o.getName(),
				systemUser);
		return new DataResponse(true, "添加业绩完成！");
	}

	public DataResponse updatePerformance(JSONObject json, SystemUser systemUser)
			throws Exception {
		Performance o = new Performance();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		o.setRegUserId(systemUser.getUsers().getUserId());
		if (SnailUtils.isBlank(o.getPerformanceId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getName())) {
			return new DataResponse(false, "业务竞赛名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getSection())) {
			return new DataResponse(false, "学段不能为空！");
		}
		if (SnailUtils.isBlank(o.getSubjectId())) {
			return new DataResponse(false, "学科不能为空！");
		}
		if (SnailUtils.isBlank(o.getOrganization())) {
			return new DataResponse(false, "竞赛组织单位不能为空！");
		}
		if (SnailUtils.isBlank(o.getwEntries())) {
			return new DataResponse(false, "获奖作品名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getwLevel())) {
			return new DataResponse(false, "获奖级别 不能为空！");
		}
		if (SnailUtils.isBlank(o.getwGrade())) {
			return new DataResponse(false, "获奖等级不能为空！");
		}
		if (SnailUtils.isBlank(o.getwTime())) {
			return new DataResponse(false, "获奖时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getCategory())) {
			return new DataResponse(false, "类别不能为空！");
		}
		if (SnailUtils.isBlank(o.getRegUserId())) {
			return new DataResponse(false, "申报人不能为空！");
		}
		if (SnailUtils.isBlank(o.getMoney())) {
			return new DataResponse(false, "金额不能为空！");
		}
		if (SnailUtils.isBlank(o.getFile())) {
			return new DataResponse(false, "证明材料不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		this.performanceMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更业绩", "业绩", "", o.getName(), o.getName(),
				systemUser);
		return new DataResponse(true, "变更业绩完成！");
	}

	public DataResponse selectPerformanceByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.performanceMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deletePerformanceByPerformanceId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.performanceMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除业绩", "业绩", String.valueOf(id),
				String.valueOf(id), "业绩", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdatePerformance(JSONObject json,
			SystemUser systemUser) throws Exception {
		Performance o = new Performance();
		SnailBeanUtils.copyProperties(o, json);
		
		o.setCreateTime(new Date());

		return new DataResponse(true, "变更业绩完成！");
	}

	public DataResponse importXls(MultipartFile[] files, JSONObject json,
			SystemUser systemUser) throws Exception {
		ExcelUtils utils = new ExcelUtils();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
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
			int i = 0;
			for (Map<String, String> row : list) {
				Performance o = new Performance();
				// o.setPerformanceId(row.get("考号"));
				o.setCreateTime(new Date());
				this.logger.info(o.toString());
				/*
				 * if (SnailUtils.isBlankString(o.getPerformanceId())) { return
				 * new DataResponse(false, "行"+i+"考号不能为空！"); }
				 */
				int t = performanceMapper.isExit(o);
				if (t > 0) {
					this.performanceMapper.updateByPrimaryKey(o);

				} else {
					this.performanceMapper.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("信息导入", "业绩", "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "业绩信息导入工完成！");
	}
}
