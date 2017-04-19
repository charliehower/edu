package org.platform.snail.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.platform.snail.beans.SystemUser;

public class AppSystemUser {

	public AppSystemUser() {

	}

	private static AppSystemUser instance;
	Logger logger = Logger.getLogger(this.getClass());

	public static synchronized AppSystemUser getInstance() {
		if (instance == null) {
			instance = new AppSystemUser();
		}
		return instance;
	}

	public SystemUser getSystemUser(HttpServletRequest request) {
		return (SystemUser) request.getSession().getAttribute(CommonKeys.SystemUser);
		
	}

	public void setSystemUser(SystemUser systemUser, HttpServletRequest request) {
		logger.info("----> "+systemUser.getUsers().getAccount() +" in session from "+systemUser.getIp());
		request.getSession().setAttribute(CommonKeys.SystemUser, systemUser);
	}
}
