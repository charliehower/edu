package org.platform.snail.portal.service;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.vo.QueueCmccHisQVo;

public interface QueueCmccHisService {
	public abstract DataResponse findQueueCmccHisList(QueueCmccHisQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse deleteQueueCmccHisByQueueCmccHisId(String id,SystemUser systemUser) throws Exception;

}
