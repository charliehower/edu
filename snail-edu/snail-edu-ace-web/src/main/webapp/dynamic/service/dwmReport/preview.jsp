<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String dwmReportId = request.getParameter("dwmReportId");
	String taskId = request.getParameter("taskId");
	if (SnailUtils.isBlankString(dwmReportId)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
	}
	request.setAttribute("dwmReportId", dwmReportId);
	request.setAttribute("taskId", taskId);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>通知</title>
</head>
<script type="text/javascript">
	var dwmReportId = '${dwmReportId}';
	var taskId = '${taskId}';
</script>
<script type="text/javascript">

	var contextPath = '${pageContext.request.contextPath}';
	var curYear = new Date().getFullYear();
	var portalContextPath = "${sessionScope.portalContextPath}";
	var urlid='${param.id}';
</script>
<script type="text/javascript"
	src= "${sessionScope.portalContextPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript"
	src= "${sessionScope.portalContextPath}/system/getSessionSystemUser.do"></script>
<script type="text/javascript"
	src= "${sessionScope.portalContextPath}/content/js/common/base.js"></script>


       <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src= "${sessionScope.portalContextPath}/content/assets/js/gz/html5shiv.js"></script>
	<script src= "${sessionScope.portalContextPath}/content/assets/js/gz/respond.min.js"></script>
	<![endif]-->		

<style type="text/css">
body {
	background-color: #FFFFFF;
	
	font-family: "微软雅黑";
	
}
</style>
<style>
.layout-user {
	width: 60px;
	height: 20px;
	float: left;
	margin: 1px 1px 1px;
}
</style>
<body>
	<div class="page-content">
		<div  style="font-weight:800" align="center">
			<h4 id="dwmReport-title"></h4>

			发布部门：<span id="dwmReport-department"></span> 发布人：<span
				id="dwmReport-publisher"></span> 时间： <span
				id="dwmReport-publishTime"></span>
		</div>
		<hr align="center" width="100%" size="1" noshade>
		<div id="dwmReport-content"></div>
		<hr align="center" width="100%" size="1" noshade>
		<div id="filelist-history"></div>
		<hr align="center" width="100%" size="1" noshade>
		<div id="viewer"></div>

	</div>








	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		jQuery(function($) {
			loadAttach(dwmReportId);
			loadDwmReport(dwmReportId);
			complectTask(taskId);
			loadViewer(dwmReportId);

		});
		function loadDwmReport(id) {
			$
					.ajax({
						type : "get",
						url : contextPath
								+ "/dwmReport/selectDwmReportByPrimaryKey.do",
						data : {
							id : id
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							console.log(rst);
							if (rst && rst.state) {
								$("#dwmReport-content").html(
										rst.response.content);
								$("#dwmReport-title").html(rst.response.title);
								$("#dwmReport-publisher").html(
										rst.response.name);
								$("#dwmReport-publishTime").html(
										rst.response.publishTime);
								$("#dwmReport-department").html(
										rst.response.departmentName);

							} else {
								bootbox
										.dialog({
											title : '系统提示',
											message : rst.errorMessage,
											detail : rst.detail,
											buttons : {
												"success" : {
													"label" : "<i class='ace-icon fa fa-check'></i>确定",
													"className" : "btn-sm btn-success",
													"callback" : function() {
														//$( this ).dialog( "close" );
													}
												}
											}
										});
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {
						}
					});
		}
		function loadAttach(dwmReportId) {
			$
					.ajax({
						type : "get",
						url : portalContextPath + "/attach/findAttachList.do",
						data : {
							noticeId : dwmReportId
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							if (rst && rst.state) {
								var html = [];
								html.push('<h4>附件列表</h4>');
								$
										.each(
												rst.list,
												function(n, file) {

													html
															.push('<div id="' + file.fileUrl + '"><a style="font-size:20px" href="'
																	+ portalContextPath
																	+ '/files/download.do?collectionName=dwmReport&originalFilename='
																	+ file.fileName
																	+ '&fileName='
																	+ file.fileUrl
																	+ '" target="_blank">'
																	+ file.fileName
																	+ '</a> ('
																	+ parseInt(file.fileSize / 1024)
																	+ 'kb) <b></b></div>');
												});
								//document.getElementById('filelist').innerHTML=html.join('');
								$('#filelist-history').html(html.join(''));
							} else {
								bootbox
										.dialog({
											title : '系统提示',
											message : rst.errorMessage,
											detail : rst.detail,
											buttons : {
												"success" : {
													"label" : "<i class='ace-icon fa fa-check'></i>确定",
													"className" : "btn-sm btn-success",
													"callback" : function() {
														//$( this ).dialog( "close" );
													}
												}
											}
										});
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {
						}
					});
		}
		function complectTask(id) {
			$.ajax({
				type : "get",
				url : portalContextPath + "/task/deleteTaskByTaskId.do",
				data : {
					id : id
				},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {
				},
				complete : function(XMLHttpRequest, textStatus) {
					parent.initTaskList();
				},
				error : function() {
				}
			});
		}
		function loadViewer(dwmReportId) {
			$
					.ajax({
						type : "get",
						url : contextPath + "/dwmReport/getViewerByDwrReportId.do",
						data : {
							dwmReportId : dwmReportId
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							if (rst && rst.state) {
								var html = [];
								html.push('<h4>抄送</h4>');
								$
										.each(
												rst.list,
												function(n, o) {

													html.push('<div class="layout-user" >');
													html.push('<user  class="badge badge-'+cssColor9[0]+'">');
													html.push(o);
													html.push('</user>');
													html.push('</div>');
												});
								//document.getElementById('filelist').innerHTML=html.join('');
								$('#viewer').html(html.join(''));
							} else {
								bootbox
										.dialog({
											title : '系统提示',
											message : rst.errorMessage,
											detail : rst.detail,
											buttons : {
												"success" : {
													"label" : "<i class='ace-icon fa fa-check'></i>确定",
													"className" : "btn-sm btn-success",
													"callback" : function() {
														//$( this ).dialog( "close" );
													}
												}
											}
										});
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {
						}
					});
		}
	</script>
</body>
</html>