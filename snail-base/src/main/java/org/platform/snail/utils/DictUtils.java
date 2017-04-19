package org.platform.snail.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

public class DictUtils {
	public static String toJsonString(Map<String, Object> valueObject,
			String tableNames) {
		String[] tables = tableNames.split(",");
		Set<String> tablesSet = new HashSet<String>();
		for (int i = 0; i < tables.length; i++) {
			tablesSet.add(tables[i]);
		}
		Set<String> ignorePropSet = new HashSet<String>();
		Set<String> keySet = valueObject.keySet();
		for (String key : keySet) {
			if (!tablesSet.contains(key)) {
				ignorePropSet.add(key);
			}
		}

		JsonConfig config = JsonUtils.getJsonConfig(ignorePropSet);
		JSON json = JSONSerializer.toJSON(valueObject, config);
		return json.toString();
	}

	public static String toJsonString(Map<String, ?> valueObject) {
		JSON json = JSONSerializer.toJSON(valueObject);
		return json.toString();
	}

	public static String toJsonString(Map<String, ?> valueObject,
			String[] ignoreProps) {
		Set<String> ignorePropSet = new HashSet<String>();
		for (String key : ignoreProps) {
			ignorePropSet.add(key);
		}
		JsonConfig config = JsonUtils.getJsonConfig(ignorePropSet);
		JSON json = JSONSerializer.toJSON(valueObject, config);
		return json.toString();
	}
}
