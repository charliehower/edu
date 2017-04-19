package org.platform.snail.portal.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.portal.dao.LocationMapper;
import org.platform.snail.portal.model.Location;
import org.platform.snail.portal.service.LocationService;
import org.platform.snail.portal.vo.LocationQVo;
import org.platform.snail.portal.vo.LocationVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CommonTreeUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("locationService")
public class LocationServiceImpl implements LocationService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findLocationList(LocationQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<LocationVo> list = this.locationMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.locationMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertLocation(JSONObject json, SystemUser systemUser)
			throws Exception {
		Location o = new Location();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getParentId())) {
			return new DataResponse(false, "上级编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		
		if (SnailUtils.isBlankString(o.getFullName())) {
			return new DataResponse(false, "全称不能为空！");
		}
		o.setCreateTime(new Date());
		this.locationMapper.insert(o);
		this.dataBaseLogService.log("添加位置", "位置", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "添加位置完成！");
	}

	public DataResponse updateLocation(JSONObject json, SystemUser systemUser)
			throws Exception {
		Location o = new Location();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getLocationId())) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getParentId())) {
			return new DataResponse(false, "上级编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		
		if (SnailUtils.isBlankString(o.getFullName())) {
			return new DataResponse(false, "全称不能为空！");
		}
		o.setCreateTime(new Date());
		
		this.locationMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更位置", "位置", "", o.getName(),
				o.getName(), systemUser);
		return new DataResponse(true, "变更位置完成！");
	}

	public DataResponse selectLocationByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.locationMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteLocationByLocationId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.locationMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除位置", "位置", String.valueOf(id), String.valueOf(id),
				"位置", systemUser);
		return rst;
	}
	public  List<Tree> selectLocationTreeList(String pid,String load) throws Exception{
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.locationMapper.selectLocationTreeList(pid,load));
		return commonTreeUtils.getTreeList(pid);
	}

}
