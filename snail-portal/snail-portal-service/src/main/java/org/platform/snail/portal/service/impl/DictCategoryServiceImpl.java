package org.platform.snail.portal.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.DictCategoryMapper;
import org.platform.snail.portal.model.DictCategory;
import org.platform.snail.portal.service.DictCategoryService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("dictCategoryService")
public class DictCategoryServiceImpl implements DictCategoryService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DictCategoryMapper dictCategoryMapper;

	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findDictCategoryList(
			DictCategory condition, int start, int limit, String orderBy)
			throws Exception {
		DataResponse rst = new DataResponse();
		List<DictCategory> list = this.dictCategoryMapper.findList(condition, start, start+limit, orderBy);
		rst.setList(list);
		if(start<=1){
			int allRows = this.dictCategoryMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertDictCategory(JSONObject josnObject,
			SystemUser systemUser) throws Exception {
		DictCategory dictCategory = new DictCategory();
		SnailBeanUtils.copyProperties(dictCategory, josnObject);

		if (SnailUtils.isBlankString(dictCategory.getName())) {

			return new DataResponse(false, "名称不能为空！");
		}
		dictCategory.setCreateTime(new Date());
		int temp = this.dictCategoryMapper.isExitByName(dictCategory.getName());
		if (temp > 0) {
			return new DataResponse(false, "名称已存在！");
		}
		this.dictCategoryMapper.insert(dictCategory);
		this.dataBaseLogService.log("添加字典类型", "字典类型", "", dictCategory.getName(),
				dictCategory.getName(), systemUser);
		return new DataResponse(true, "添加字典类型完成！");
	}

	public DataResponse updateDictCategory(JSONObject josnObject,
			SystemUser systemUser) throws Exception {
		DictCategory dictCategory = new DictCategory();
		SnailBeanUtils.copyProperties(dictCategory, josnObject);

		if (SnailUtils.isBlankString(dictCategory.getName())) {

			return new DataResponse(false, "名称不能为空！");
		}
		this.dictCategoryMapper.updateByPrimaryKeySelective(dictCategory);
		this.dataBaseLogService.log("变更字典类型", "字典类型", "", dictCategory.getName(),
				dictCategory.getName(), systemUser);
		return new DataResponse(true, "字典类型变更完成！");
	}

	

	public DataResponse selectDictCategoryByPrimaryKey(
			String categoryId) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.dictCategoryMapper.selectByPrimaryKey(categoryId));
		return rst;
	}

	public DataResponse deleteDictCategoryByDictCategoryId(
			String dictCategoryId, SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(dictCategoryId);
		this.dictCategoryMapper.deleteByPrimaryKey(dictCategoryId);
		this.dataBaseLogService.log("删除字典类型", "字典类型", dictCategoryId, dictCategoryId,
				"字典类型", systemUser);
		return rst;
	}

	public List<DictCategory> findDictCategoryListAll()
			throws Exception {
		return this.dictCategoryMapper.findListAll();
	}

}
