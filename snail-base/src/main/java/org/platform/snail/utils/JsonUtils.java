package org.platform.snail.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;


public class JsonUtils {
	/**
	 * 
	 * 方法描述：取得Json转换配置
	 * 
	 * @param set
	 *            需要过滤掉的不予转换的属性
	 * @author: leshjmail@163.com
	 * @version:
	 * @Date 2012-3-23 下午07:21:17
	 */
	public static JsonConfig getJsonConfig(final String[] ignoreProps) {
		Set<String> set = new HashSet<String>();
		if (ignoreProps != null && ignoreProps.length > 0) {
			for (String propName : ignoreProps) {
				set.add(propName);
			}
		}
		return getJsonConfig(set);
	}

	/**
	 * 
	 * 方法描述：取得Json转换配置
	 * 
	 * @param set
	 *            需要过滤掉的不予转换的属性
	 * @author: leshjmail@163.com
	 * @Date 2012-3-23 下午07:21:17
	 */
	public static JsonConfig getJsonConfig(final Set<String> set) {
		JsonConfig jsonConfig = new JsonConfig();
		DateJsonValueProcessor dateProc = new DateJsonValueProcessor();
		jsonConfig.registerJsonValueProcessor(Date.class, dateProc);
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (set.contains(name)) {
					return true;
				} else {
					return false;
				}
			}

		});

		return jsonConfig;
	}

	public static JsonConfig getJsonConfig() {
		JsonConfig jsonConfig = new JsonConfig();
		DateJsonValueProcessor dateProc = new DateJsonValueProcessor();
		jsonConfig.registerJsonValueProcessor(Date.class, dateProc);
		return jsonConfig;
	}
	public static JsonConfig getFullDateJsonConfig() {
		JsonConfig jsonConfig = new JsonConfig();
		FullDateJsonValueProcessor dateProc = new FullDateJsonValueProcessor();
		jsonConfig.registerJsonValueProcessor(Date.class, dateProc);
		return jsonConfig;
	}

	public static String toJSONString(Object obj) {
		JSON json = JSONSerializer.toJSON(obj);
		return json.toString();
	}

	public static String toJSONString(Object obj, String[] ignoreProp) {
		Set<String> set = new HashSet<String>();
		if (ignoreProp != null) {
			for (String prop : ignoreProp) {
				set.add(prop);
			}
		}
		JsonConfig cfg = getJsonConfig(set);
		JSON json = JSONSerializer.toJSON(obj, cfg);
		return json.toString();
	}
}
