package org.platform.snail.edu.service;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.edu.vo.DutyDetailQVo;

public interface DutyDetailService {
	public abstract DataResponse findDutyDetailList(DutyDetailQVo condition, int start, int limit, String orderBy) throws Exception;
	
}
