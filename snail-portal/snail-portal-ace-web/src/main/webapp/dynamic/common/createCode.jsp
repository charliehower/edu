<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="org.platform.snail.utils.*"%>
	<%@page import="org.platform.snail.dao.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	javax.servlet.ServletContext servletContext = request.getSession()
	.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
	.getRequiredWebApplicationContext(servletContext);
	SystemDao systemDao = (SystemDao) webApplicationContext
	.getBean("systemDao");
	String taskId = request.getParameter("taskId");
	List<Map<String,String>> o=systemDao.informationSchemaColumns("leave");
	List<Map<String,String>> list=new ArrayList<Map<String,String>>();
	for(Map<String,String> map:o){
		Map<String,String> e=new HashMap<String,String>();
		e.put("COLUMN_NAME", SnailUtils.fieldToProperty(map.get("COLUMN_NAME")));
		e.put("COLUMN_COMMENT", map.get("COLUMN_COMMENT"));
		list.add(e);
	}
	request.setAttribute("list", list);
	
%>
var _colNames = [<c:forEach var="item" items="${list}" varStatus="status">'${item.COLUMN_COMMENT}',</c:forEach>];
var _colModel = function() {
	return [
<c:forEach var="item" items="${list}" varStatus="status">
			{
				name : '${item.COLUMN_NAME}',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
			},
</c:forEach>
];