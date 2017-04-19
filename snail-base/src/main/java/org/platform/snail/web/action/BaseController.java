package org.platform.snail.web.action;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.utils.CommonKeys;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController implements Serializable {
	public Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	private int start;
	private int limit;
	private int totalRecord;
	private String orderBy;

	public int getStart() {
		return start + 1;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		if (limit <= 0) {
			limit = CommonKeys.GRID_DEFAULT_PAGE_SIZE;
		}
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	protected Object getSession(String name) {
		return this.getRequest().getSession().getAttribute(name);
	}

	protected SystemUser getSessionSystemUser() {
		Object obj = this.getSession(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
		return systemUser;
	}
	public Map<String,Object> getParamsMap(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();  
		@SuppressWarnings("unchecked")
		Enumeration<String> e=request.getParameterNames();
		while(e.hasMoreElements()){
			String key=(String)e.nextElement();
			String value=request.getParameter(key);
			params.put(key, value);
		}
		this.logger.info(params);
		return params;
	}
}
