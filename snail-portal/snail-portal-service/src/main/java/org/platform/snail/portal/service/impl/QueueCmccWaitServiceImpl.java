package org.platform.snail.portal.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.QueueCmccWaitMapper;
import org.platform.snail.portal.model.QueueCmccWait;
import org.platform.snail.portal.service.QueueCmccWaitService;
import org.platform.snail.portal.vo.QueueCmccWaitQVo;
import org.platform.snail.portal.vo.QueueCmccWaitVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("queueCmccWaitService")
public class QueueCmccWaitServiceImpl implements QueueCmccWaitService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QueueCmccWaitMapper queueCmccWaitMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findQueueCmccWaitList(QueueCmccWaitQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<QueueCmccWaitVo> list = this.queueCmccWaitMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.queueCmccWaitMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	
	public DataResponse deleteQueueCmccWaitByQueueCmccWaitId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.queueCmccWaitMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除教职工", "教职工", String.valueOf(id), String.valueOf(id),
				"教职工", systemUser);
		return rst;
	}

}
