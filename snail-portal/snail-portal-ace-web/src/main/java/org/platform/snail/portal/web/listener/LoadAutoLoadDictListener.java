package org.platform.snail.portal.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.platform.snail.portal.service.DictService;
import org.platform.snail.service.CacheService;
import org.platform.snail.utils.CommonKeys;

public class LoadAutoLoadDictListener implements ServletContextListener {

	private DictService dictService;

	private CacheService cacheService;

	Logger logger = Logger.getLogger(this.getClass());

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("LoadAutoLoadDictListener start");
		this.initService(arg0.getServletContext());

		dictService.flushJavaScriptFile(
				arg0.getServletContext().getRealPath("/"), CommonKeys.dictPath,
				arg0.getServletContext());
		this.cacheService.init();

	}

	private void initService(javax.servlet.ServletContext servletContext) {
		org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		if (this.dictService == null) {
			this.dictService = (DictService) webApplicationContext
					.getBean("dictService");
		}
		if (this.cacheService == null) {
			this.cacheService = (CacheService) webApplicationContext
					.getBean("cacheService");
		}
	}
}
