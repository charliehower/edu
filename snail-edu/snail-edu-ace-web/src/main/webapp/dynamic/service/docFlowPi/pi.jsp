<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.edu.vo.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>
<%@page import="org.platform.snail.edu.model.*"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%
	String id = request.getParameter("id");
	request.setAttribute("edit", "true");
	Object obj = session.getAttribute(CommonKeys.SystemUser);
	SystemUser systemUser = (SystemUser) obj;
	if (SnailUtils.isBlankString(id)) {
		
		id = String.valueOf(new java.util.Date().getTime());
		request.setAttribute("edit", "false");
	}
	request.setAttribute("id", id);
	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);

	DocFlowTaskMapper docFlowTaskMapper = (DocFlowTaskMapper) webApplicationContext
			.getBean("docFlowTaskMapper");
	List<Map<String,String>> list=docFlowTaskMapper.selectMyDeptIds(systemUser.getDepartment().getDepartmentId());
	JSONArray sb=new JSONArray();
	for(Map<String,String> o:list){
		sb.add(o.get("DEPARTMENT_ID"));
	}
	request.setAttribute("piUser", sb.toString().replaceAll("\"", "'"));
	//System.out.println(sb.toString());
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
<script type="text/javascript">
	var id = '${id}';
	var edit = ${edit};
</script>
<jsp:include page="../../common/common.jsp" />


<script type="text/javascript">
	function altRows(id) {
		if (document.getElementsByTagName) {

			var table = document.getElementById(id);
			var rows = table.getElementsByTagName("tr");

			for (i = 0; i < rows.length; i++) {
				if (i % 2 == 0) {
					rows[i].className = "evenrowcolor";
				} else {
					rows[i].className = "oddrowcolor";
				}
			}
		}
	}

	window.onload = function() {
		altRows('alternatecolor');
	}
</script>


<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.altrowstable {
	font-family: verdana, arial, sans-serif;
	font-size: 12px;
	border-width: 0px;
	border-color: #eeeeee;
	border-collapse: collapse;
}

table.altrowstable th {
	border-width: 1px;
	padding: 2px;
	border-style: solid;
	border-color: #eeeeee;
}

table.altrowstable td {
	border-width: 1px;
	padding: 2px;
	border-style: solid;
	border-color: #eeeeee;
}

.oddrowcolor {
	background-color: #FFFFFF;
}

.evenrowcolor {
	background-color: #FFFFFF;
}

.fileList0 {
	border-bottom: 2px solid #eeeeee;
	width: 50%;
	font-size: 14px
}

#container {
	font-size: 14px
}
</style>
<body>
	<form id="fm-docFlow">
	<div class="page-content">
		<div align="center">
			
			<h3 id="title"></h3>
			<table class="altrowstable" id="alternatecolor" style="width: 100%">
				<tr>
					<td align="right">来文单位</td>
					<td id="docDept"></td>
					<td align="right">来文编号</td>
					<td id="docNo"></td>
					<td align="right">来文日期</td>
					<td id="docDate"></td>
				</tr>

				<tr>
					<td align="right">校内编号</td>
					<td id="nativeNo"></td>

					<td align="right">发布者</td>
					<td id="name"></td>
					<td align="right">发布日期</td>
					<td id="deployDate"></td>
				</tr>
				<tr>
					<td colspan="6" id="filelist-history"></td>
				</tr>

				<tr>
					<td colspan="6" id="content"></td>
				</tr>
				<tr>
					<td colspan="6"> 审批结果：<input type="radio" name="status" value="1" checked="checked"> 已审批 <input type="radio" name="status" value="9"> 驳回</td>
				</tr>
				<tr>
					<td colspan="6"> 任务对象：
					<input class="easyui-combotree" name="piUser" id ="cc" data-options="url:'${sessionScope.portalContextPath}/system/selectDepartmentTreeList.do?id=${SystemUser.users.departmentId}',method:'get',label:'Select Node:',labelPosition:'top',multiple:true,cascadeCheck:false,value:${piUser}" style="width:300px">
					</td>
				</tr>
				<tr>
					<td colspan="6"> 审批意见：</td>
				</tr>
				<tr>
					<td colspan="6"> 
					<textarea rows="" cols="" name="piContent"></textarea>
					</td>
				</tr>
			</table>
		</div>
	
		<input type="hidden" name="docFlowId" value="${id }">
		
		<input type="hidden" name="pid" value="${id}">

			<div>
				<button class="btn btn-info" id="btn-docFlow-submit"
					authority="false">
					<i class="ace-icon fa fa-check bigger-110"></i> 保存审批
				</button>
			</div>

		

	</div>
</form>
	<!-- /section:elements.tab.option -->


	<jsp:include page="../../common/footer-1.jsp" />

<script
		src="${pageContext.request.contextPath}/content/js/service/docFlowPi/controller-pi.js"></script>
	
	<jsp:include page="../../common/footer-2.jsp" />

	<ckeditor:replaceAll basePath="/portal/ckeditor/" />

	<script type="text/javascript">
		
	</script>
</body>
</html>