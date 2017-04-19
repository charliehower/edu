package org.platform.snail.portal.dao;

import java.util.List;
import java.util.Map;

public interface DictDao {
	
	List<Map> selectAllDictList();
	int init();
}
