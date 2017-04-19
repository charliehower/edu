package org.platform.snail.portal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.portal.dao.ResourcesMapper;
import org.platform.snail.portal.model.Resources;
import org.platform.snail.portal.service.ResourcesService;
import org.platform.snail.portal.vo.ResourcesVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CommonTreeUtils;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ResourcesMapper resourcesMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findResourcesList(Resources condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<ResourcesVo> list = this.resourcesMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.resourcesMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertResources(JSONObject json, SystemUser systemUser)
			throws Exception {
		Resources o = new Resources();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getResourcesId())) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getParentResourcesId())) {
			return new DataResponse(false, "父编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getResourcesName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getResourcesType())) {
			return new DataResponse(false, "类型不能为空！");
		}
		
		int temp = this.resourcesMapper.isExitByName(o.getResourcesName());
		if (temp > 0) {
			return new DataResponse(false, "资源名称重复！");
		}
		o.setCreateTime(new Date());
		o.setStauts("1");
		o.setCreateUserId(systemUser.getUsers().getUserId());
		this.resourcesMapper.insert(o);
		this.dataBaseLogService.log("添加资源", "资源", "", o.getResourcesName(),
				o.getResourcesName(), systemUser);
		return new DataResponse(true, "添加资源完成！");
	}

	public DataResponse updateResources(JSONObject json, SystemUser systemUser)
			throws Exception {
		Resources o = new Resources();
		SnailBeanUtils.copyProperties(o, json);
		if (SnailUtils.isBlankString(o.getResourcesId())) {
			return new DataResponse(false, "编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getParentResourcesId())) {
			return new DataResponse(false, "父编号不能为空！");
		}
		if (SnailUtils.isBlankString(o.getResourcesName())) {
			return new DataResponse(false, "名称不能为空！");
		}
		if (SnailUtils.isBlankString(o.getResourcesType())) {
			return new DataResponse(false, "类型不能为空！");
		}
		this.resourcesMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更资源", "资源", "", o.getResourcesName(),
				o.getResourcesName(), systemUser);
		return new DataResponse(true, "变更资源完成！");
	}

	public DataResponse selectResourcesByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.resourcesMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteResourcesByResourcesId(String id,
			SystemUser systemUser) throws Exception {
		this.resourcesMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除资源", "资源", String.valueOf(id), String.valueOf(id),
				"资源", systemUser);
		return new DataResponse(true,"资源删除完成！");
	}
	public  List<Tree>  selectResourcesTreeList() throws Exception{
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.resourcesMapper.selectResourcesTreeList());
		return commonTreeUtils.getTreeList("0");
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
				list = utils.readExcelByJXL(file.getInputStream(), 1);
			}
			if (ext.equals(".xlsx")) {
				list = utils.readExcelByPOI(file.getInputStream(), 1);
			}
			int i = 0;
			for (Map<String, String> row : list) {
				
				Resources o = new Resources();
				SnailBeanUtils.copyMap2Bean(o,row);
				o.setCreateTime(new Date());
				o.setCreateUserId(systemUser.getUsers().getUserId());
				o.setStauts("1");
				this.logger.info(o.toString());
				if (SnailUtils.isBlankString(o.getResourcesId())) {
					return new DataResponse(false, "编号不能为空！");
				}
				if (SnailUtils.isBlankString(o.getParentResourcesId())) {
					return new DataResponse(false, "父编号不能为空！");
				}
				if (SnailUtils.isBlankString(o.getResourcesName())) {
					return new DataResponse(false, "名称不能为空！");
				}
				if (SnailUtils.isBlankString(o.getResourcesType())) {
					return new DataResponse(false, "类型不能为空！");
				}
				int t = resourcesMapper.isExit(o);
				if (t > 0) {
					this.resourcesMapper.updateByPrimaryKey(o);

				} else {
					this.resourcesMapper.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("资源导入", "资源", "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "资源导入工完成！");
	}
}
