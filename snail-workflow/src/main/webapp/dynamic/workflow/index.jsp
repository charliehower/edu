<%@page import="org.platform.snail.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.jbpm.api.*" %>
<%@ page import="org.jbpm.api.task.*" %>
<%@ page import="org.jbpm.api.history.*" %>
<%@ page import="java.util.*" %>
<%
javax.servlet.ServletContext servletContext = request.getSession()
.getServletContext();
org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
.getRequiredWebApplicationContext(servletContext);
ProcessEngine processEngine=(ProcessEngine)webApplicationContext.getBean("processEngine");
List<HistoryTask> items = processEngine.getHistoryService().createHistoryTaskQuery().executionId("").list();
%>