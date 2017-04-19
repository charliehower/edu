package org.platform.snail.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.edu.dao.report.Query;
import org.platform.snail.edu.service.ReportService;
import org.platform.snail.utils.SpringUtils;
import org.springframework.stereotype.Service;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	public DataResponse query(Map<String, Object> params,String reportId) throws Exception {
		DataResponse rst=new DataResponse();
		Query q=(Query)SpringUtils.getBean(reportId);
		List<Map<String,Object>> list=q.query(params);
		rst.setList(list);
		return rst;
	}

}
