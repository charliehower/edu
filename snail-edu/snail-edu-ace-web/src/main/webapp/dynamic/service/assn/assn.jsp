<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.edu.vo.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);

	AssnMapper AssnMapper = (AssnMapper) webApplicationContext
			.getBean("assnMapper");

	List<Map<String, Object>> category = AssnMapper
			.selectAssnCategory("40");
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>基本信息</title>
</head>
<jsp:include page="../../common/common.jsp" />


<style>
.layout-user {
	width: 60px;
	height: 20px;
	float: left;
	margin: 1px 1px 1px;
}
</style>
<style>
<!--
.datalist {
	border: 1px solid #FCFCFC; /* 表格边框 */
	font-family: Arial;
	border-collapse: collapse; /* 边框重叠 */
	background-color: #ffffff; /* 表格背景色 */
	font-size: 12px;
}

.datalist caption {
	padding-bottom: 5px;
	font: bold 1.4em;
	text-align: left;
}

.datalist th {
	border: 1px solid #000000; /* 行名称边框 */
	background-color: #ffffff; /* 行名称背景色 */
	color: #000000; /* 行名称颜色 */
	font-weight: bold;
	padding-top: 2px;
	padding-bottom: 2px;
	padding-left: 12px;
	padding-right: 12px;
	text-align: center;
}

.datalist td {
	border: 1px solid #000000; /* 单元格边框 */
	text-align: center;
	padding-top: 2px;
	padding-bottom: 2px;
	padding-left: 10px;
	padding-right: 10px;
}

.datalist tr.altrow {
	background-color: #FCFCFC; /* 隔行变色 */
	text-align: center;
}
-->
</style>
<style>
body {
	padding-top: 2.5em;
	background-color: #eeeeee;
	color: #333;
	font-size: 84%;
	font-family: "微软雅黑", "Yahei Mono";
}

.title {
	text-align: center;
}

.page {
	width: 600px;
	margin: 0 auto;
	padding: 1em 0 2em;
	background-color: #ffffff;
	-moz-box-shadow: 0 2px 10px 1px rgba(0, 0, 0, 0.2);
	-webkit-box-shadow: 0 2px 10px 1px rgba(0, 0, 0, 0.2);
	box-shadow: 0 2px 10px 1px rgba(0, 0, 0, 0.2);
	position: relative;
}

.page p {
	line-height: 2em;
	padding: 0 2em;
}

</style>
<body>
	<div align="right">
		<a href="javascript:window.print()">打印</a>
	</div>
	<div class="page">

		<h2 class="title">${cfg.cyear}学年度学生社团社长、指导老师总表</h2>
		<table class="table table-striped table-bordered table-hover" align="center" width="600px">
			<tr >
				<td width="50px">序号</td>
				<td>社团名称</td>
				<td>社长</td>
				<td>指导老师</td>
			</tr>
			<%
			int i=1;
			int j=0;
			String[] title={"一","二","三","四","五","六","七","八","九","十"};
				for (Map<String, Object> o : category) {
					AssnQVo q = new AssnQVo();
					q.setCategoryId((String) o.get("code"));
					request.setAttribute("o", o);
					request.setAttribute("j", title[j]);
			%>
			<tr>
				<th colspan="4">${j}、${o.name}</th>
			</tr>
			<%
				for (AssnVo obj :AssnMapper.findList(q, 0, 999, null) ) {
					request.setAttribute("obj", obj);
					request.setAttribute("i", i);
			%>
			
			<tr >
				<td>${i}</td>
				<td style="text-align:left">${obj.assnName}<a href="javascript:reg('${obj.assnId}','${obj.assnName}')" style="color:blue">［点击参加］</a></td>
				<td>${obj.chiefName}</td>
				<td>${obj.adviserName}</td>
			</tr>
			<%
			i+=1;
				}
			%>
			<%
			j+=1;
				}
			%>

		</table>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
<script type="text/javascript">
function reg(id,name){
	parent.addPanel(name, contextPath
			+ '/dynamic/service/assn/preview.jsp?id=' + urlid+ "&assnId=" + id, true
			);
}

</script>
	<jsp:include page="../../common/footer-2.jsp" />
</body>
</html>