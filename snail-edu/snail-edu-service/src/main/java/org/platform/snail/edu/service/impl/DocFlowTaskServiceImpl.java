package org.platform.snail.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.AceTree;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.cxf.model.S01;
import org.platform.snail.edu.dao.DocFlowMapper;
import org.platform.snail.edu.dao.DocFlowTaskMapper;
import org.platform.snail.edu.model.DocFlowTask;
import org.platform.snail.edu.service.DocFlowTaskService;
import org.platform.snail.edu.vo.DocFlowTaskQVo;
import org.platform.snail.edu.vo.DocFlowTaskVo;
import org.platform.snail.edu.vo.DocFlowVo;
import org.platform.snail.service.CxfClientService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.CommonAceTreeUtils;
import org.platform.snail.utils.ExcelUtils;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("docFlowTaskService")
public class DocFlowTaskServiceImpl implements DocFlowTaskService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DocFlowTaskMapper docFlowTaskMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private  CxfClientService cxfClientService;
	@Autowired
	private DocFlowMapper docFlowMapper;
	public DataResponse findDocFlowTaskList(DocFlowTaskQVo condition,
			int start, int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<DocFlowTaskVo> list = this.docFlowTaskMapper.findList(condition,
				start, start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.docFlowTaskMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertDocFlowTask(JSONObject json, SystemUser systemUser)
			throws Exception {
		JSONArray array=json.getJSONArray("piUser");
		if(json.getString("status").equals("1")){
			for(Object obj:array){
				String id = String.valueOf(new Date().getTime());
				DocFlowTask o = new DocFlowTask();
				SnailBeanUtils.copyProperties(o, json);
				o.setId(id);
				o.setCreateTime(new Date());
				o.setPiUser((String)obj);
				o.setPiStatus("0");
				o.setPiDate(new Date());
				if (SnailUtils.isBlank(o.getId())) {
					return new DataResponse(false, "序号不能为空！");
				}
				if (SnailUtils.isBlank(o.getPid())) {
					return new DataResponse(false, "父节点不能为空！");
				}
				if (SnailUtils.isBlank(o.getDocFlowId())) {
					return new DataResponse(false, "公文编号不能为空！");
				}
				if (SnailUtils.isBlank(o.getPiStatus())) {
					return new DataResponse(false, "批示状态不能为空！");
				}
				if (SnailUtils.isBlank(o.getPiUser())) {
					return new DataResponse(false, "批示人不能为空！");
				}
				if (SnailUtils.isBlank(o.getCreateTime())) {
					return new DataResponse(false, "创建时间不能为空！");
				}
				int temp = this.docFlowTaskMapper.isExit(o);
				if (temp > 0) {
					return new DataResponse(false, "已存在的数据！");
				}
				this.docFlowTaskMapper.insert(o);
				this.sendCmcc(o);
			}
		}
		this.docFlowTaskMapper.updateEndTaskByPrimaryKey(json.getString("pid"));
		this.docFlowTaskMapper.updateDocFlowForStatusById(json.getString("docFlowId"), json.getString("status"));
		this.dataBaseLogService.log("公文评审", "公文评审", "", json.getString("docFlowId"),
				json.getString("docFlowId"), systemUser);
		return new DataResponse(true, "成功！");
	}
	
	public DataResponse updateDocFlowTask(JSONObject json, SystemUser systemUser)
			throws Exception {
		DocFlowTask o = new DocFlowTask();
		SnailBeanUtils.copyProperties(o, json);
		o.setCreateTime(new Date());
		if (SnailUtils.isBlank(o.getId())) {
			return new DataResponse(false, "序号不能为空！");
		}
		if (SnailUtils.isBlank(o.getPid())) {
			return new DataResponse(false, "父节点不能为空！");
		}
		if (SnailUtils.isBlank(o.getDocFlowId())) {
			return new DataResponse(false, "公文编号不能为空！");
		}
		if (SnailUtils.isBlank(o.getPiStatus())) {
			return new DataResponse(false, "批示状态不能为空！");
		}
		if (SnailUtils.isBlank(o.getPiUser())) {
			return new DataResponse(false, "批示人不能为空！");
		}
		if (SnailUtils.isBlank(o.getCreateTime())) {
			return new DataResponse(false, "创建时间不能为空！");
		}
		this.docFlowTaskMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更公文评审", "公文评审", "", o.getPiContent(),
				o.getPiContent(), systemUser);
		return new DataResponse(true, "变更公文评审完成！");
	}

	public DataResponse selectDocFlowTaskByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.docFlowTaskMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteDocFlowTaskByDocFlowTaskId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.docFlowTaskMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除公文评审", "公文评审", String.valueOf(id),
				String.valueOf(id), "公文评审", systemUser);
		return rst;
	}

	public DataResponse saveOrUpdateDocFlowTask(JSONObject json,
			SystemUser systemUser) throws Exception {
		DocFlowTask o = new DocFlowTask();
		SnailBeanUtils.copyProperties(o, json);

		return new DataResponse(true, "变更公文评审完成！");
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
				DocFlowTask o = new DocFlowTask();
				// o.setDocFlowTaskId(row.get("考号"));
				o.setCreateTime(new Date());
				this.logger.info(o.toString());
				/*
				 * if (SnailUtils.isBlankString(o.getDocFlowTaskId())) { return
				 * new DataResponse(false, "行"+i+"考号不能为空！"); }
				 */
				int t = docFlowTaskMapper.isExit(o);
				if (t > 0) {
					this.docFlowTaskMapper.updateByPrimaryKey(o);

				} else {
					this.docFlowTaskMapper.insert(o);
				}
				i++;
			}

		}
		this.dataBaseLogService.log("信息导入", "公文评审", "", files[0].getName(),
				files[0].getName(), systemUser);
		return new DataResponse(true, "公文评审信息导入工完成！");
	}
	public  DataResponse selectTaskListByDocFlowId(String docFlowId)throws Exception{
		DataResponse rst = new DataResponse();
		rst.setList(this.docFlowTaskMapper.selectTaskListByDocFlowId(docFlowId));
		return rst;
	}
	public  Map<String,Object> selectTaskTreeListByPid(String docFlowId,String pid)throws Exception{
		Map<String,Object> rs=new HashMap<String,Object>();
		rs.put("status", "OK");
		CommonAceTreeUtils commonTreeUtils=new CommonAceTreeUtils(this.docFlowTaskMapper.selectTaskTreeListByPid(docFlowId,pid));
		List<AceTree> list= commonTreeUtils.getAceTreeList(pid);
		rs.put("data", list);
		return rs;
	}
	
	private void sendCmcc(DocFlowTask o){
		Map<String,String> user=this.docFlowTaskMapper.selectUserByDeptId(o.getPiUser());
		DocFlowVo obj=docFlowMapper.selectByPrimaryKey(o.getDocFlowId());
		Map<String,Object> data=new HashMap<String,Object>();
		SnailBeanUtils.copyBean2Map(obj, data);
		data.put("userName", user.get("userName"));
		data.put("title", obj.getTitle());
		this.logger.info(data);
		S01 body=new S01();
		body.setUserId(user.get("userId"));
		body.setTid("dowFlow");
		body.setTitle("公文流转");
		body.setData(data);
		cxfClientService.S01(body);
	}
}
