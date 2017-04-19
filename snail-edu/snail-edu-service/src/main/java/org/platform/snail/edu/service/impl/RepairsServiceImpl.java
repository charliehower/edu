package org.platform.snail.edu.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.RepairsMapper;
import org.platform.snail.edu.service.RepairsService;
import org.platform.snail.edu.vo.RepairsQVo;
import org.platform.snail.edu.vo.RepairsVo;
import org.platform.snail.service.DataBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("repairsService")
public class RepairsServiceImpl implements RepairsService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private RepairsMapper repairsMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findRepairsList(RepairsQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<RepairsVo> list = this.repairsMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.repairsMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse insertRepairs(JSONObject json, SystemUser systemUser)
			throws Exception {

		return new DataResponse(true, "添加报修工完成！");
	}

	public DataResponse updateRepairs(JSONObject json, SystemUser systemUser)
			throws Exception {
	
		return new DataResponse(true, "变更报修工完成！");
	}

	public DataResponse selectRepairsByPrimaryKey(String id)
			throws Exception {
		DataResponse rst = new DataResponse();
		rst.setResponse(this.repairsMapper.selectByPrimaryKey(id));
		return rst;
	}

	public DataResponse deleteRepairsByRepairsId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.repairsMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除报修工", "报修工", String.valueOf(id), String.valueOf(id),
				"报修工", systemUser);
		return rst;
	}
	public DataResponse saveOrUpdateRepairs(JSONObject json, SystemUser systemUser)
			throws Exception {
	
		return new DataResponse(true, "变更报修工完成！");
	}
}
