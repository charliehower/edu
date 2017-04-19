package org.platform.snail.edu.service;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.edu.vo.EntryQVo;

public interface EntryService {
	public abstract DataResponse findEntryList(EntryQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertEntry(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateEntry(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectEntryByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteEntryByEntryId(String id,SystemUser systemUser) throws Exception;
	
	public abstract DataResponse saveOrUpdateEntry(JSONObject json,SystemUser systemUser) throws Exception;
}
