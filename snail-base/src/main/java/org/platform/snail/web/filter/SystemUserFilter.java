package org.platform.snail.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.service.SystemService;
import org.platform.snail.utils.AppSystemUser;
import org.platform.snail.utils.CommonKeys;
import org.platform.snail.utils.WebUtils;
import org.springframework.security.cas.authentication.CasAuthenticationToken;

public class SystemUserFilter implements Filter {
	Logger logger = Logger.getLogger(this.getClass());
	private SystemService systemService;
	private String portalContextPath;
	private static String portalContextPathKey = "portalContextPath";

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		// this.initService(request);
		if (AppSystemUser.getInstance().getSystemUser(request) == null
				&& request.getUserPrincipal() != null) {
			Map<String,String> cfg=(Map<String,String>)request.getSession().getServletContext().getAttribute(CommonKeys.cfg);
			CasAuthenticationToken token = (CasAuthenticationToken) request
					.getUserPrincipal();
			String account = token.getUserDetails().getUsername();
			SystemUser systemUser = this.systemService
					.getSystemUserByAccount(account,cfg);
			systemUser.setIp(WebUtils.getIpAddr(request));
			AppSystemUser.getInstance().setSystemUser(systemUser, request);
		}
		if (request.getSession().getAttribute(portalContextPathKey) == null) {
			request.getSession().setAttribute(portalContextPathKey,
					portalContextPath);
			this.logger.info("load in session portal.contextPath :"
					+ portalContextPath);
		}
		filterChain.doFilter(arg0, arg1);
	}

	public void init(FilterConfig cfg) throws ServletException {
		javax.servlet.ServletContext servletContext = cfg.getServletContext();
		org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		portalContextPath = cfg.getServletContext().getInitParameter(
				"portal.contextPath");
		if (portalContextPath == null) {
			portalContextPath = "/oa";
		}
		if (this.systemService == null) {
			this.systemService = (SystemService) webApplicationContext
					.getBean("systemService");
			this.systemService.loadConfig(servletContext);
		}
		this.logger
				.info("init systemUserFilter for params portal.contextPath :"
						+ portalContextPath);
	}

}
