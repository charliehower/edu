package org.platform.snail.portal.service;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.vo.QueueCmccWaitQVo;

public interface QueueCmccWaitService {
	public abstract DataResponse findQueueCmccWaitList(QueueCmccWaitQVo condition, int start, int limit, String orderBy) throws Exception;
		public abstract DataResponse deleteQueueCmccWaitByQueueCmccWaitId(String id,SystemUser systemUser) throws Exception;

}
