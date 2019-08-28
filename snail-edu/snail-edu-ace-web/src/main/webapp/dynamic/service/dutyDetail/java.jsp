<%@page import="org.platform.snail.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>

<%
	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);
	DutyDetailMapper dutyDetailMapper = (DutyDetailMapper) webApplicationContext
			.getBean("dutyDetailMapper");
	
	
String dutyDetailId=request.getParameter("id");
	request.setAttribute("o",
			dutyDetailMapper.selectVoByPrimaryKey(dutyDetailId));
	TimeZone zone=TimeZone.getTimeZone("Asia/Beijing");
	Calendar cal = Calendar.getInstance(zone);
	
	int c = cal.get(cal.WEEK_OF_YEAR);
	request.setAttribute("WEEK_OF_YEAR", c);
%>
