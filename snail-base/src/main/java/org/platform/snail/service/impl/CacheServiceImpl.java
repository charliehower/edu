package org.platform.snail.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.platform.snail.dao.SystemDao;
import org.platform.snail.service.CacheService;
import org.platform.snail.utils.CommonTreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("cacheService")
public class CacheServiceImpl implements CacheService{
	private Map<String, Object> content = new HashMap<String, Object>();
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SystemDao systemDao;
	public Object get(String key) {

		return content.get(key);
	}

	public void put(String key,Object value) {
		content.put(key, value);
		//this.logger.info("\n\r"+value);
		logger.info("cache content size: "+content.size());
	}

	public boolean containsKey(String key) {
		return content.containsKey(key);
	}

	public void clear() {
		logger.info("cache content size: "+content.size());
		logger.info("start clear");
		content.clear();
		logger.info("complete clear");
		logger.info("cache content size: "+content.size());
	}
	public void init() {
		logger.info("cache init C0001");
		String key="C0001";
		if(content.get(key)==null){
			CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.systemDao.selectProvinceTreeList());
			content.put(key, commonTreeUtils.getTreeList("00"));
		}
	}
}
