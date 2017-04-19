package org.platform.snail.portal.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.vo.NoticeQVo;

public interface NoticeService {
	public abstract DataResponse findNoticeList(NoticeQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertNotice(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateNotice(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectNoticeByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteNoticeByNoticeId(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse saveOrUpdateNotice(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateForTopByPrimaryKey(String noticeId,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateForStatusByPrimaryKey(String noticeId,String status,String departmentId,String groupId,String contextPath,SystemUser systemUser) throws Exception;
	
	public abstract DataResponse findListTop(SystemUser systemUser) throws Exception;

	
	
}
