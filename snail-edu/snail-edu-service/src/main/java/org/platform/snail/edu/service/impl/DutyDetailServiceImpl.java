package org.platform.snail.edu.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.dao.DutyDetailMapper;
import org.platform.snail.edu.model.DutyDetail;
import org.platform.snail.edu.service.DutyDetailService;
import org.platform.snail.edu.vo.DutyDetailQVo;
import org.platform.snail.edu.vo.DutyDetailVo;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.utils.SnailBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dutyDetailService")
public class DutyDetailServiceImpl implements DutyDetailService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DutyDetailMapper dutyDetailMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public DataResponse findDutyDetailList(DutyDetailQVo condition, int start,
			int limit, String orderBy) throws Exception {
		DataResponse rst = new DataResponse();
		List<DutyDetailVo> list = this.dutyDetailMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setList(list);
		if (start <= 1) {
			int allRows = this.dutyDetailMapper.findCount(condition);
			rst.setAllRows(allRows);
		}
		return rst;
	}
}
