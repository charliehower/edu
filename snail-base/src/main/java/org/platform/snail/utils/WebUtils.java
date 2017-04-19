package org.platform.snail.utils;

import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static int getDayOfWeek() {
		TimeZone zone=TimeZone.getTimeZone("Asia/Beijing");
		Calendar cal = Calendar.getInstance(zone);
		
		int c = cal.get(cal.WEEK_OF_YEAR);
		System.out.println(c);
		return c;
	}
	public static void main(String args[]){
		WebUtils.getDayOfWeek();
	}

}
