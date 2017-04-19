package org.platform.snail.utils;

import java.util.ResourceBundle;

/**
 * @author 陈晓克
 * @version 2013-5-30 下午11:07:56
 */
public class Config {
	public static String getProperty(String key) {
		ResourceBundle resource=ResourceBundle.getBundle("properties");
		return resource.getString(key);
	}
}
