package org.platform.snail.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Debug {
	private static boolean isPrint = true;

	public static void out(Exception e) {
		if (isPrint) {
			Logger.getLogger(Debug.class).info(e);

		}
	}

	public static void out(String error) {
		StringBuffer sb = new StringBuffer();

		sb.append(error);

		if (isPrint) {
			Logger.getLogger(Debug.class).info(error);
		}
	}

	public static void out(boolean state) {
		StringBuffer sb = new StringBuffer();

		sb.append(state);

		if (isPrint) {
			Logger.getLogger(Debug.class).info(sb.toString());
		}
	}

	public static void out(int message) {
		StringBuffer sb = new StringBuffer();

		sb.append(message);
		if (isPrint) {
			Logger.getLogger(Debug.class).info(sb.toString());
		}
	}

	public static void out(float message) {
		StringBuffer sb = new StringBuffer();

		sb.append(message);

		if (isPrint) {
			Logger.getLogger(Debug.class).info(sb.toString());
		}
	}

	public static void out(double message) {
		StringBuffer sb = new StringBuffer();

		sb.append(message);

		if (isPrint) {
			Logger.getLogger(Debug.class).info(sb.toString());
		}
	}

	public boolean isPrint() {
		return isPrint;
	}

	public void setPrint(boolean isPrint) {
		this.isPrint = isPrint;
	}

	public static void error(String error) {
		StringBuffer sb = new StringBuffer();

		sb.append(error);

		if (isPrint) {
			Logger.getLogger(Debug.class).info(sb.toString());
		}
	}

	public static void out(Boolean arg0) {
		StringBuffer sb = new StringBuffer();

		sb.append(arg0);

		if (isPrint) {
			Logger.getLogger(Debug.class).info(sb.toString());
		}
	}

	public static void out(String[] arg0) {
		StringBuffer sb = new StringBuffer();
		if (arg0 != null) {
			for (int i = 0; i < arg0.length; i++) {

				sb.append(arg0[i]);
				sb.append("\n\r");

			}
		}
		if (isPrint) {
			Logger.getLogger(Debug.class).info(sb.toString());
		}
	}

	
	public static void out(Map map) {
		if (map != null) {
			java.util.Set<String> set = map.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				Logger.getLogger(Debug.class).info(key + ":" + map.get(key));
			}
		}
	}

}
