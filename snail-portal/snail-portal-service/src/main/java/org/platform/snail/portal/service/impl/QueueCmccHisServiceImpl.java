package org.platform.snail.portal.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.dao.QueueCmccHisMapper;
import org.platform.snail.portal.service.QueueCmccHisService;
import org.platform.snail.portal.vo.QueueCmccHisQVo;
import org.platform.snail.portal.vo.QueueCmccHisVo;
import org.platform.snail.service.DataBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("queueCmccHisService")
public class QueueCmccHisServiceImpl implements QueueCmccHisService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QueueCmccHisMapper queueCmccHisMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findQueueCmccHisList(QueueCmccHisQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<QueueCmccHisVo> list = this.queueCmccHisMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.queueCmccHisMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}

	public DataResponse deleteQueueCmccHisByQueueCmccHisId(String id,
			SystemUser systemUser) throws Exception {
		DataResponse rst = new DataResponse();
		this.queueCmccHisMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除教职工", "教职工", String.valueOf(id), String.valueOf(id),
				"教职工", systemUser);
		return rst;
	}

}
