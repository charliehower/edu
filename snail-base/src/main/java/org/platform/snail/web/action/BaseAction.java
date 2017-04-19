package org.platform.snail.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.platform.snail.utils.CommonKeys;
import org.platform.snail.utils.JsonUtils;
import org.platform.snail.beans.SystemUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements Serializable {
	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	JsonConfig cfg = JsonUtils.getJsonConfig();

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	private int start;
	private int limit;
	private int totalRecord;
	private String orderBy;

	public int getStart() {
		return start+1;
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

	/**
	 * 
	 * 功能描述：输出JSON字符串
	 * 
	 * @author chenxiaoke Jun 21, 2012 12:54:41 PM
	 * @param str
	 * @return void
	 * 
	 */
	protected void outJsonString(String str) {
		getResponse().setContentType("application/json;charset=UTF-8");
		outString(str);
		logger.debug(str);
	}
	protected void outJsonString(String str,String contentType) {
		getResponse().setContentType(contentType);
		outString(str);
		logger.debug(str);
	}

	/**
	 * 
	 * 功能描述：把Object转换成JSON字符串后,输出
	 * 
	 * @author chenxiaoke Jun 21, 2012 12:54:59 PM
	 * @param obj
	 * @return void
	 * 
	 */
	protected void outJson(Object obj) {
		String str = JSONObject.fromObject(obj, cfg).toString();
		outJsonString(str);
		logger.debug(str);
	}
	protected void outJsonNoCfg(Object obj) {
		String str = JSONObject.fromObject(obj).toString();
		outJsonString(str);
	}
	protected void outJson(String prefix, Object obj) {
		String str = JSONObject.fromObject(obj, cfg).toString();
		str = prefix + "(" + str + ");";
		outJsonString(str);
		logger.debug(str);
	}
	protected void outJson(Object obj,String contentType) {
		String str = JSONObject.fromObject(obj, cfg).toString();
		outJsonString(str,contentType);
		logger.info(str);
	}
	/**
	 * 
	 * 方法描述：把Object转换成JSON字符串后,输出
	 * 
	 * @author: chenxiaoke
	 * @param obj
	 * @param jsonConf
	 *            配置
	 * @return: void
	 * @version: 2012-10-6 下午07:52:33
	 */
	protected void outJson(Object obj, JsonConfig jsonConf) {
		JSON json = JSONSerializer.toJSON(obj, jsonConf);
		String str = json.toString();
		outJsonString(str);
		logger.debug(str);
	}

	/**
	 * 
	 * 功能描述：把Object转换成JSON字符串后,输出
	 * 
	 * @author chenxiaoke Jun 21, 2012 12:58:10 PM
	 * @param array
	 * @return void
	 * 
	 */
	protected void outJsonArray(Object array) {
		String str = JSONArray.fromObject(array, cfg).toString();
		outJsonString(str);
		logger.debug(str);
	}

	/**
	 * 
	 * 功能描述：把Object转换成JSON字符串后,输出
	 * 
	 * @author chenxiaoke Jun 21, 2012 12:58:10 PM
	 * @param array
	 * @return void
	 * 
	 */
	protected void outJsonArray(Object array, JsonConfig jsonConf) {
		String str = JSONArray.fromObject(array, jsonConf).toString();
		outJsonString(str);
		logger.debug(str);
	}

	/**
	 * 
	 * 功能描述：输出字符串
	 * 
	 * @author chenxiaoke Jun 21, 2012 2:55:05 PM
	 * @param str
	 * @return void
	 * 
	 */
	protected void outString(String str) {
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.write(str);
			logger.debug(str);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	protected Object getSession(String name) {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		return session.get(name);
	}

	protected Object getApplication(String name) {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> application = ctx.getApplication();
		return application.get(name);
	}

	protected void setSession(String name, Object obj) {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		session.put(name, obj);
	}

	protected void setApplication(String name, Object obj) {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> application = ctx.getApplication();
		application.put(name, obj);
	}
	protected SystemUser getSessionSystemUser(){
		Object obj = this.getSession(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
		return systemUser;
	}


	protected Object getParamValue(String name) {
		Object rst = null;
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> params = ctx.getParameters();
		Object paramValue = params.get(name);
		logger.debug(name +" "+paramValue.toString());
		if (paramValue instanceof String[]) {
			String[] tmp = (String[]) paramValue;
			if (tmp.length == 1) {
				rst = tmp[0];
			} else {
				rst = paramValue;
			}
		}
		return rst;
	}
	protected Map<String, Object> getParamValues() {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> params = ctx.getParameters();
		return params;
	}

	protected JSONArray getJsonArrayParam(String param) {
		Object paramStr = this.getParamValue(param);
		JSONArray jsonArray = JSONArray.fromObject(paramStr);
		return jsonArray;

	}

	protected JSONObject getJsonObjectParam(String param) {
		Object paramStr = this.getParamValue(param);
		JSONObject jsonObj = JSONObject.fromObject(paramStr);
		return jsonObj;
	}

}
