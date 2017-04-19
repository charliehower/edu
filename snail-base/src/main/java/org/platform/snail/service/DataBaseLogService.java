package org.platform.snail.service;

import java.util.Map;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;


public interface DataBaseLogService{
	public  void log(String log,String name,String old,String news,String type,SystemUser systemUser);
	public abstract DataResponse findList(Map<String,Object> condition, int start, int limit, String orderBy) throws Exception;
}
