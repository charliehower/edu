package org.platform.snail.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.SiteMapper;
import org.platform.snail.edu.model.Site;
import org.platform.snail.edu.service.SiteService;
import org.platform.snail.edu.vo.SiteQVo;
import org.platform.snail.edu.vo.SiteVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("siteService")
public class SiteServiceImpl implements SiteService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SiteMapper siteMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public DataResponse findSiteList(SiteQVo condition, int start, int limit,
			String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<SiteVo> list = this.siteMapper.findList(condition, start, start
				+ limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.siteMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertSite(JSONObject json, SystemUser systemUser)
			throws Exception {
		String id = String.valueOf(new Date().getTime());
		Site o = new Site();
		SnailBeanUtils.copyProperties(o, json);
		o.setId(id);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getName())) {
			return new DataResponse(false, "场地名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getAdvance())) {
			return new DataResponse(false, "可以提前申请的天数不能为空！");
		}
		if (SnailUtils.isBlank(o.getStatus())) {
			return new DataResponse(false, "是否锁定不能为空！");
		}
		if (SnailUtils.isBlank(o.getStartHh())) {
			return new DataResponse(false, "开放开始小时不能为空！");
		}
		if (SnailUtils.isBlank(o.getStartMm())) {
			return new DataResponse(false, "开放开始分钟不能为空！");
		}
		if (SnailUtils.isBlank(o.getEndHh())) {
			return new DataResponse(false, "开放结束小时不能为空！");
		}
		if (SnailUtils.isBlank(o.getEndMm())) {
			return new DataResponse(false, "开放结束分钟不能为空！");
		}
		if (SnailUtils.isBlank(o.getFlag())) {
			return new DataResponse(false, "周末是否开放不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		int temp = this.siteMapper.isExit(o);
		if (temp > 0) {
			return new DataResponse(false, "已存在的数据！");
		}
		this.siteMapper.insert(o);
		this.dataBaseLogService.log("添加场地", "场地", "", o.getName(), o.getName(),
				systemUser);
		return new DataResponse(true, "添加场地完成！");
	}

	public DataResponse updateSite(JSONObject json, SystemUser systemUser)
			throws Exception {
		Site o = new Site();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getName())) {
			return new DataResponse(false, "场地名称不能为空！");
		}
		if (SnailUtils.isBlank(o.getAdvance())) {
			return new DataResponse(false, "可以提前申请的天数不能为空！");
		}
		if (SnailUtils.isBlank(o.getStatus())) {
			return new DataResponse(false, "是否锁定不能为空！");
		}
		if (SnailUtils.isBlank(o.getStartHh())) {
			return new DataResponse(false, "开放开始小时不能为空！");
		}
		if (SnailUtils.isBlank(o.getStartMm())) {
			return new DataResponse(false, "开放开始分钟不能为空！");
		}
		if (SnailUtils.isBlank(o.getEndHh())) {
			return new DataResponse(false, "开放结束小时不能为空！");
		}
		if (SnailUtils.isBlank(o.getEndMm())) {
			return new DataResponse(false, "开放结束分钟不能为空！");
		}
		if (SnailUtils.isBlank(o.getFlag())) {
			return new DataResponse(false, "周末是否开放不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		this.siteMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更场地", "场地", "", o.getName(), o.getName(),
				systemUser);
		return new DataResponse(true, "变更场地完成！");
	}

	public DataResponse selectSiteByPrimaryKey(String id) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.siteMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteSiteBySiteId(String id, SystemUser systemUser)
			throws Exception {
		DataResponse rst = new DataResponse();
		this.siteMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除场地", "场地", String.valueOf(id),
				String.valueOf(id), "场地", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdateSite(JSONObject json, SystemUser systemUser)
			throws Exception {
		Site o = new Site();
		SnailBeanUtils.copyProperties(o, json);

		return new DataResponse(true, "变更场地完成！");
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
				Site o = new Site();
				// o.setSiteId(row.get("考号"));
				o.setCreateTime(new Date());
				this.logger.info(o.toString());
				/*
				 * if (SnailUtils.isBlankString(o.getSiteId())) { return new
				 * DataResponse(false, "行"+i+"考号不能为空！"); }
				 */
				int t = siteMapper.isExit(o);
				if (t > 0) {
					this.siteMapper.updateByPrimaryKey(o);

				} else {
					this.siteMapper.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("信息导入", "场地", "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "场地信息导入工完成！");
	}
}
