package org.platform.snail.portal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.model.Dict;


public interface DictService {

	
	/**
	 * 更新静态字典JavaScript文件
	 * 
	 * @param path
	 * @param fileName
	 * @throws Exception
	 */
	public void flushJavaScriptFile(String path, String fileName,ServletContext servletContext);
	
	public abstract DataResponse findDictList(Dict condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertDict(JSONObject josnObject,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateDict(JSONObject josnObject,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectDictByPrimaryKey(int id) throws Exception;
	public abstract DataResponse deleteDictByDictId(int id,SystemUser systemUser) throws Exception;
	public abstract List<Dict> findListByCategoryId(String categoryId,String selected,Map<String,Object> params) throws Exception;



}
