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
	ClassCheduleMapper classCheduleMapper = (ClassCheduleMapper) webApplicationContext
			.getBean("classCheduleMapper");
	String classesId = request.getParameter("classesId");
	String gradeId = request.getParameter("gradeId");
	if (classesId == null) {
		classesId = "101";
	}
	if (gradeId == null) {
		gradeId = "1";
	}
	request.setAttribute("classesId",classesId);
	request.setAttribute("gradeId",gradeId);
	request.setAttribute("week",classCheduleMapper.selectAllWeekList());
	request.setAttribute("section",classCheduleMapper.selectAllSectionList());
	request.setAttribute("list",classCheduleMapper.selectClassCheduleListByClassesId(classesId));
%>