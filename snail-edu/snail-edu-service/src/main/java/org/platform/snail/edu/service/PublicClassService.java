package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.PublicClassQVo;

public interface PublicClassService {
	public abstract DataResponse findPublicClassList(PublicClassQVo condition,
			int start, int limit, String orderBy) throws Exception;

	public abstract DataResponse insertPublicClass(JSONObject json,
			SystemUser systemUser) throws Exception;

	public abstract DataResponse updatePublicClass(JSONObject json,
			SystemUser systemUser) throws Exception;

	public abstract DataResponse selectPublicClassByPrimaryKey(String id)
			throws Exception;

	public abstract DataResponse deletePublicClassByPublicClassId(String id,
			SystemUser systemUser) throws Exception;

	public abstract DataResponse saveOrUpdatePublicClass(JSONObject json,
			SystemUser systemUser) throws Exception;

	public abstract DataResponse updateAudit(String id, String status,String remark,
			SystemUser systemUser) throws Exception;

	public abstract DataResponse updateRelease(String id, String status,
			SystemUser systemUser) throws Exception;

	public abstract DataResponse updateScore(String id, float score,
			String remark, SystemUser systemUser) throws Exception;

	public abstract DataResponse selectListByid(String id) throws Exception;

	public abstract DataResponse insertJoin(String id, SystemUser systemUser)
			throws Exception;

	public void taskPublicClassAlert() throws Exception;

	public abstract DataResponse updateAuditSec(String id, String status,
			String remark, SystemUser systemUser) throws Exception;

	public abstract DataResponse updateAuditThi(String id, String status,
			String remark, SystemUser systemUser) throws Exception;

	public abstract DataResponse updateAuditFor(String id, String status,
			String remark, SystemUser systemUser) throws Exception;
}
