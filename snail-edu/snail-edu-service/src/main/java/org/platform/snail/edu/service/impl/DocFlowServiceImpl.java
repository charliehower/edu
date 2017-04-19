package org.platform.snail.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.DocFlowMapper;
import org.platform.snail.edu.model.DocFlow;
import org.platform.snail.edu.service.DocFlowService;
import org.platform.snail.edu.vo.DocFlowQVo;
import org.platform.snail.edu.vo.DocFlowVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("docFlowService")
public class DocFlowServiceImpl implements DocFlowService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DocFlowMapper docFlowMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public DataResponse findDocFlowList(DocFlowQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<DocFlowVo> list = this.docFlowMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.docFlowMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertDocFlow(JSONObject json, SystemUser systemUser)
			throws Exception {
		String id = String.valueOf(new Date().getTime());
		DocFlow o = new DocFlow();
		SnailBeanUtils.copyProperties(o, json);
		o.setId(id);
		o.setCreateTime(new Date());
		o.setDeployUser(systemUser.getUsers().getUserId());
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "不能为空！");
		}
		if (SnailUtils.isBlank(o.getDocDept())) {
			return new DataResponse(false, "来文单位不能为空！");
		}
		if (SnailUtils.isBlank(o.getDocDate())) {
			return new DataResponse(false, "来文日期不能为空！");
		}
		if (SnailUtils.isBlank(o.getDocNo())) {
			return new DataResponse(false, "来文编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getNativeNo())) {
			return new DataResponse(false, "内部编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getTitle())) {
			return new DataResponse(false, "公文标题不能为空！");
		}
		if (SnailUtils.isBlank(o.getDeployDate())) {
			return new DataResponse(false, "发布时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getDeployUser())) {
			return new DataResponse(false, "发布人不能为空！");
		}
		if (SnailUtils.isBlank(o.getStatus())) {
			return new DataResponse(false, "状态不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		int temp = this.docFlowMapper.isExit(o);
		if (temp > 0) {
			return new DataResponse(false, "已存在的数据！");
		}
		this.docFlowMapper.insert(o);
		this.dataBaseLogService.log("添加公文", "公文", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "添加公文完成！");
	}

	public DataResponse updateDocFlow(JSONObject json, SystemUser systemUser)
			throws Exception {
		DocFlow o = new DocFlow();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		o.setDeployUser(systemUser.getUsers().getUserId());
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "不能为空！");
		}
		if (SnailUtils.isBlank(o.getDocDept())) {
			return new DataResponse(false, "来文单位不能为空！");
		}
		if (SnailUtils.isBlank(o.getDocDate())) {
			return new DataResponse(false, "来文日期不能为空！");
		}
		if (SnailUtils.isBlank(o.getDocNo())) {
			return new DataResponse(false, "来文编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getNativeNo())) {
			return new DataResponse(false, "内部编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getTitle())) {
			return new DataResponse(false, "公文标题不能为空！");
		}
		if (SnailUtils.isBlank(o.getDeployDate())) {
			return new DataResponse(false, "发布时间不能为空！");
		}
		if (SnailUtils.isBlank(o.getDeployUser())) {
			return new DataResponse(false, "发布人不能为空！");
		}
		if (SnailUtils.isBlank(o.getStatus())) {
			return new DataResponse(false, "状态不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		o.setStatus("0");
		this.docFlowMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更公文", "公文", "", o.getTitle(),
				o.getTitle(), systemUser);
		return new DataResponse(true, "变更公文完成！");
	}

	public DataResponse selectDocFlowByPrimaryKey(String id) throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.docFlowMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteDocFlowByDocFlowId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.docFlowMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除公文", "公文", String.valueOf(id),
				String.valueOf(id), "公文", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdateDocFlow(JSONObject json,
			SystemUser systemUser) throws Exception {
		DocFlow o = new DocFlow();
		SnailBeanUtils.copyProperties(o, json);

		return new DataResponse(true, "变更公文完成！");
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
				DocFlow o = new DocFlow();
				// o.setDocFlowId(row.get("考号"));
				o.setCreateTime(new Date());
				this.logger.info(o.toString());
				/*
				 * if (SnailUtils.isBlankString(o.getDocFlowId())) { return new
				 * DataResponse(false, "行"+i+"考号不能为空！"); }
				 */
				int t = docFlowMapper.isExit(o);
				if (t > 0) {
					this.docFlowMapper.updateByPrimaryKey(o);

				} else {
					this.docFlowMapper.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("信息导入", "公文", "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "公文信息导入工完成！");
	}
}
