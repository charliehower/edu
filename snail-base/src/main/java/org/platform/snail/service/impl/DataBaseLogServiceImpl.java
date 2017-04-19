package org.platform.snail.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.dao.DataBaseLogDao;
import org.platform.snail.model.Logs;
import org.platform.snail.service.DataBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataBaseLogService")
public class DataBaseLogServiceImpl implements DataBaseLogService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DataBaseLogDao dataBaseLogDao;

	private static final long serialVersionUID = 1L;

	public void log(String log, String name, String old, String news,
			String objectValue, SystemUser systemUser) {
		logger.info(systemUser.getUsers().getName() + "  " + log + " "
				+ objectValue);
		Logs logs = new Logs();
		logs.setCreateTime(new Date());
		logs.setLog(log);
		logs.setName(name);
		logs.setNews(news);
		logs.setOld(old);
		logs.setObjectValue(objectValue);
		logs.setUserId(systemUser.getUsers().getUserId());
		logs.setUserName(systemUser.getUsers().getName());
		logs.setIp(systemUser.getIp());
		this.dataBaseLogDao.insert(logs);
	}

	public DataResponse findList(Map<String, Object> condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<Map<String, Object>> list = this.dataBaseLogDao.findList(
				condition, start, start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.dataBaseLogDao.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}
}
