<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.beans.*"%>
<%@page import="org.platform.snail.edu.model.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Logger logger = Logger.getLogger(this.getClass());
	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);
	SalaryaMapper salaryaMapper = (SalaryaMapper) webApplicationContext
			.getBean("salaryaMapper");
	SalarybMapper salarybMapper = (SalarybMapper) webApplicationContext
			.getBean("salarybMapper");
	SalaryImportMapper salaryImportMapper = (SalaryImportMapper) webApplicationContext
			.getBean("salaryImportMapper");
	
	TeacherMapper teacherMapper = (TeacherMapper) webApplicationContext
			.getBean("teacherMapper");
	Map<String, Object> params = new HashMap<String, Object>();
	Enumeration<String> e = request.getParameterNames();
	String key = "";
	while (e.hasMoreElements()) {
		key = (String) e.nextElement();
		params.put(key, request.getParameter(key));
		logger.info(key + "=" + request.getParameter(key));
	}
	SystemUser syUser=AppSystemUser.getInstance().getSystemUser(request);
	Teacher t=teacherMapper.selectByPrimaryKey(syUser.getUsers().getUserId());
	
	params.put("category", "bc");
	
	if(t.getSalaryType()!=null&&t.getSalaryType().equals("1")){
		params.put("category", "");
	}
	request.setAttribute("list", salaryImportMapper.findListAll(params));
	
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>工资</title>
</head>

<jsp:include page="../../common/common.jsp" />
<style>
table td,th{word-break: keep-all;white-space:nowrap;}
</style>

<body>
	<div class="page-content">
		
		<div align="center"><h2>${SystemUser.users.name}（${SystemUser.users.userId}）工资发放查询</h2></div>
					 <table 
	class="table table-striped table-bordered table-hover">
						<thead>
							<tr style="font-size: 18px; font-weight: 800">
								
								<th>发放月份</th>
								<th>职工类别</th>
								<th>查看</th>
								
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="status">
							<tr style="font-size: 18px;">
								
								
								<td>${item.year}年${item.month}月</td>
								<td>${item.category_name}</td>
								<td><a href="javascript:_open('${item.year}',${item.month},'${item.category}','${item.salary_import_id}')">查看</a></td>
								
								</tr>
							</c:forEach>
						</tbody>
					</table>
				

	</div>






	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
<script>
function _open(year,month,category,salaryImportId){
	
	var url=contextPath+"/dynamic/service/salaryImport/preview-"+category+".jsp?userName=${SystemUser.users.name}&salaryImportId="+salaryImportId;
	parent.addPanel('${SystemUser.users.name}'+month+"月份工资发放详细",url,true);
}
</script>

</body>
</html>