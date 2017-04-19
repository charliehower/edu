package org.platform.snail.edu.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.AceTree;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.SiteAppMapper;
import org.platform.snail.edu.dao.SiteMapper;
import org.platform.snail.edu.model.Site;
import org.platform.snail.edu.model.SiteApp;
import org.platform.snail.edu.service.SiteAppService;
import org.platform.snail.edu.vo.SiteAppQVo;
import org.platform.snail.edu.vo.SiteAppVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CommonAceTreeUtils;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("siteAppService")
public class SiteAppServiceImpl implements SiteAppService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SiteAppMapper siteAppMapper;
	@Autowired
	private SiteMapper siteMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public DataResponse findSiteAppList(SiteAppQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<SiteAppVo> list = this.siteAppMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.siteAppMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertSiteApp(JSONObject json, SystemUser systemUser)
			throws Exception {
		String id = String.valueOf(new Date().getTime());
		SiteApp o = new SiteApp();
		SnailBeanUtils.copyProperties(o, json);
		o.setId(id);
		o.setUserId(systemUser.getUsers().getUserId());
		o.setCreateTime(new Date());
		o.setAppTime(new Date());
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getTitle())) {
			return new DataResponse(false, "主题不能为空！");
		}
		if (SnailUtils.isBlank(o.getCategory())) {
			return new DataResponse(false, "类别不能为空！");
		}
		if (SnailUtils.isBlank(o.getUserId())) {
			return new DataResponse(false, "申请人不能为空！");
		}
		if (SnailUtils.isBlank(o.getAppTime())) {
			return new DataResponse(false, "申请时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getStart())) {
			return new DataResponse(false, "开始时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getEnd())) {
			return new DataResponse(false, "截止时间不能为空！");
		}
		
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		if (o.getStart().after(o.getEnd())) {
			return new DataResponse(false, "截止时间不能在开始之前，请检查！");
		}
		Site site = siteMapper.selectByPrimaryKey(o.getCategory());
		if (site.getStatus().equals("1")) {
			return new DataResponse(false, "场地关闭,"+site.getReason());
		}
		
		Date bdate = o.getStart();
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			if(site.getFlag().equals("0")){
				return new DataResponse(false, "周末不开放！");
			}
		}
		
		
		
		if(o.getStart().before(this.parseDate(o.getStart(), site.getStartHh(), site.getStartMm()))){
			return new DataResponse(false, "场地开放开始时间为"+site.getStartHh()+":"+site.getStartMm());
		}
		if(o.getEnd().after(this.parseDate(o.getEnd(), site.getEndHh(), site.getEndMm()))){
			return new DataResponse(false, "场地开放截止时间为"+site.getEndHh()+":"+site.getEndMm());
		}
		List<Map<String,Object>> list=this.siteAppMapper.selectListByStart(SnailUtils.parseDate(o.getStart()),o.getId());
		Date startDate=o.getStart();
		Date endDate=o.getEnd();
		for(Map<String,Object> map:list){
			Date startDate2=(Date)map.get("start");
			Date endDate2=(Date)map.get("end");
			if(SnailUtils.getTimeIntersection(startDate, endDate, startDate2, endDate2)){
				return new DataResponse(false, "场地已经占用,申请名称"+map.get("name"));
			}
		}
		int temp = this.siteAppMapper.isExit(o);
		if (temp > 0) {
			return new DataResponse(false, "已存在的数据！");
		}
		this.siteAppMapper.insert(o);
		this.dataBaseLogService.log("添加场地申请", "场地申请", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "场地申请成功！");
	}
	
	private Date parseDate(Date date,String hh,String mm){
		return SnailUtils.parseDate(SnailUtils.parseDate(date)+" "+hh+":"+mm,"yyyy-MM-dd HH:mm");
	}

	public DataResponse updateSiteApp(JSONObject json, SystemUser systemUser)
			throws Exception {
		SiteApp o = new SiteApp();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		o.setAppTime(new Date());
		o.setUserId(systemUser.getUsers().getUserId());
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getTitle())) {
			return new DataResponse(false, "主题不能为空！");
		}
		
		if (SnailUtils.isBlank(o.getUserId())) {
			return new DataResponse(false, "申请人不能为空！");
		}
		if (SnailUtils.isBlank(o.getAppTime())) {
			return new DataResponse(false, "申请时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getStart())) {
			return new DataResponse(false, "开始时间不能为空！");
		}
		
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		if (o.getStart().after(o.getEnd())) {
			return new DataResponse(false, "截止时间不能在开始之前，请检查！");
		}
		SiteApp m=this.siteAppMapper.selectByPrimaryKey(o.getId());
		Site site = siteMapper.selectByPrimaryKey(m.getCategory());
		if (site.getStatus().equals("1")) {
			return new DataResponse(false, "场地关闭,"+site.getReason());
		}
		Date bdate = o.getStart();
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			if(site.getFlag().equals("0")){
				return new DataResponse(false, "周末不开放！");
			}
		}
		if(o.getStart().before(this.parseDate(o.getStart(), site.getStartHh(), site.getStartMm()))){
			return new DataResponse(false, "场地开放开始时间为"+site.getStartHh()+":"+site.getStartMm());
		}
		if(o.getEnd().after(this.parseDate(o.getEnd(), site.getEndHh(), site.getEndMm()))){
			return new DataResponse(false, "场地开放截止时间为"+site.getEndHh()+":"+site.getEndMm());
		}
		List<Map<String,Object>> list=this.siteAppMapper.selectListByStart(SnailUtils.parseDate(o.getStart()),o.getId());
		Date startDate=o.getStart();
		Date endDate=o.getEnd();
		for(Map<String,Object> map:list){
			Date startDate2=(Date)map.get("start");
			Date endDate2=(Date)map.get("end");
			if(SnailUtils.getTimeIntersection(startDate, endDate, startDate2, endDate2)){
				return new DataResponse(false, "场地已经占用,申请名称"+map.get("title"));
			}
		}
		this.siteAppMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更场地申请", "场地申请", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "变更场地申请成功！");
	}

	public DataResponse selectSiteAppByPrimaryKey(String id) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.siteAppMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteSiteAppBySiteAppId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		SiteApp o=this.siteAppMapper.selectByPrimaryKey(id);
		if(!o.getUserId().equals(systemUser.getUsers().getUserId())){
			return new DataResponse(false, "不能撤销，非自己申请内容！");
		}
		this.siteAppMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除场地申请", "场地申请", String.valueOf(id),
				String.valueOf(id), "场地申请", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdateSiteApp(JSONObject json,
			SystemUser systemUser) throws Exception {
		SiteApp o = new SiteApp();
		SnailBeanUtils.copyProperties(o, json);

		return new DataResponse(true, "变更场地申请完成！");
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
				SiteApp o = new SiteApp();
				// o.setSiteAppId(row.get("考号"));
				o.setCreateTime(new Date());
				this.logger.info(o.toString());
				/*
				 * if (SnailUtils.isBlankString(o.getSiteAppId())) { return new
				 * DataResponse(false, "行"+i+"考号不能为空！"); }
				 */
				int t = siteAppMapper.isExit(o);
				if (t > 0) {
					this.siteAppMapper.updateByPrimaryKey(o);

				} else {
					this.siteAppMapper.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("信息导入", "场地申请", "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "场地申请信息导入工完成！");
	}

	public Map<String, Object> selectSiteTreeByPid(String pid,
			SystemUser systemUser) {
		if(SnailUtils.isBlank(pid)){
			pid="0";
		}
		Map<String, Object> rs = new HashMap<String, Object>();
		List<AceTree> list = new ArrayList<AceTree>();
		rs.put("status", "OK");
		CommonAceTreeUtils commonTreeUtils = new CommonAceTreeUtils(
				this.siteAppMapper.selectSiteList(pid));
		list = commonTreeUtils.getAceTreeList(pid);
		rs.put("data", list);
		return rs;
	}
	public static void main(String[] args){
		Calendar start = Calendar.getInstance();
		start.setTime(new Date());
		
		System.out.println(start.get(Calendar.HOUR_OF_DAY));
	}
}
