<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
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
	Map<String, Object> params = new HashMap<String, Object>();
	Enumeration<String> e = request.getParameterNames();
	String key = "";
	while (e.hasMoreElements()) {
		key = (String) e.nextElement();
		params.put(key, request.getParameter(key));
		System.out.println(key + "=" + request.getParameter(key));
	}
	if (((String) params.get("category")).equals("a")) {
		request.setAttribute("list", salaryaMapper.findList(params));
	} else {
		request.setAttribute("list", salarybMapper.findList(params));
	}
	String salaryImportId=request.getParameter("salaryImportId");
	request.setAttribute("o", salaryImportMapper.selectByPrimaryKey(salaryImportId));
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


<body>
	<div class="page-content">
		<div class="widget-box transparent" id="recent-box">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">
					<i class="ace-icon glyphicon glyphicon-th-large green"></i><span
						id="_title">发放年度：${o.year}年 发放月份：${o.month}</span>
				</h4>

				<div class="widget-toolbar no-border"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-4">
					<table width="100%">
						<thead>
							<tr style="font-size: 14px; font-weight: 800">
								<th height="25px">序号</th>
								<th>姓名</th>
								<th>职务级别</th>
								<th>职务工资</th>
								<th>级别薪级工资级别</th>
								<th>级别薪级工资档次</th>
								<th>级别薪级工资金额</th>
								<th>见习</th>
								<th>特区津贴档次</th>
								<th>特区津贴金额</th>
								<th>保留津贴档次</th>
								<th>保留津贴金额</th>
								<th>奖金工作津贴</th>
								<th>房补改革津贴</th>
								<th>物补生活津贴</th>
								<th>基础津贴</th>
								<th>活工资</th>
								<th>考核工资</th>
								<th>独生子女费</th>
								<th>特岗津贴</th>
								<th>特区增资</th>
								<th>全国增资</th>
								<th>应发合计</th>
								<th>补扣金额</th>
								<th>养老保险</th>
								<th>所得税</th>
								<th>个人缴交住房公积金</th>
								<th>医疗保险</th>
								<th>单位社保</th>
								<th>应扣合计</th>
								<th>单位缴交住房公积金</th>
								<th>单位医保</th>
								<th>实发合计</th>
								<th>房改住房补贴</th>
								<th>职业年金</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="status">
								<td>${item.序号}</td>
								<td>${item.姓名}</td>
								<td>${item.职务级别}</td>
								<td>${item.职务工资}</td>
								<td>${item.级别薪级工资级别}</td>
								<td>${item.级别薪级工资档次}</td>
								<td>${item.级别薪级工资金额}</td>
								<td>${item.见习}</td>
								<td>${item.特区津贴档次}</td>
								<td>${item.特区津贴金额}</td>
								<td>${item.保留津贴档次}</td>
								<td>${item.保留津贴金额}</td>
								<td>${item.奖金工作津贴}</td>
								<td>${item.房补改革津贴}</td>
								<td>${item.物补生活津贴}</td>
								<td>${item.基础津贴}</td>
								<td>${item.活工资}</td>
								<td>${item.考核工资}</td>
								<td>${item.独生子女费}</td>
								<td>${item.特岗津贴}</td>
								<td>${item.特区增资}</td>
								<td>${item.全国增资}</td>
								<td>${item.应发合计}</td>
								<td>${item.补扣金额}</td>
								<td>${item.养老保险}</td>
								<td>${item.所得税}</td>
								<td>${item.个人缴交住房公积金}</td>
								<td>${item.医疗保险}</td>
								<td>${item.单位社保}</td>
								<td>${item.应扣合计}</td>
								<td>${item.单位缴交住房公积金}</td>
								<td>${item.单位医保}</td>
								<td>${item.实发合计}</td>
								<td>${item.房改住房补贴}</td>
								<td>${item.职业年金}</td>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>


		</div>

	</div>






	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />


</body>
</html>