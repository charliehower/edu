<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String docFlowId = request.getParameter("docFlowId");
	String taskId = request.getParameter("taskId");
	if (SnailUtils.isBlankString(docFlowId)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
	}
	request.setAttribute("docFlowId", docFlowId);
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
	var docFlowId = '${docFlowId}';
	var taskId = '${taskId}';
</script>
<style>
.fileList0 {
	border-bottom: 2px solid #eeeeee;
	width: 50%;
	font-size: 14px
}
</style>

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
<jsp:include page="../../common/common.jsp" />


<body>
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
			</table>

		</div>
		<br>
		<div id="content"></div>
		
		
		
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">公文列表</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-8">
					<div id="filelist-history" ></div>
				</div>
			</div>
		</div>
		
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">流转列表</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-8">
					<div id="pilist" ></div>
				</div>
			</div>
		</div>

		
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">流转路径</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-8">
					<div id="tree" class="tree"></div>
				</div>
			</div>
		</div>

	</div>





	<!-- basic scripts -->
	<!--[if lte IE 8]>
			<script type="text/javascript" src= "${sessionScope.portalContextPath}/content/assets/js/gz/jquery1x.min.js"></script>
		<![endif]-->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${sessionScope.portalContextPath}/content/assets/js/gz/jquery.min.js'>"
								+ "<"+"/script>");
	</script>



	<script
		src="${sessionScope.portalContextPath}/content/assets/js/gz/bootstrap.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/gz/bootbox.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/gz/jquery-ui.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/bootstrap-datepicker.min.js"></script>


	<script
		src="${sessionScope.portalContextPath}/content/assets/js/gz/ace.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/gz/ace-elements.min.js"></script>

	<script
		src="${sessionScope.portalContextPath}/content/assets/js/gz/ace-extra.min.js"></script>

	<script
		src="${sessionScope.portalContextPath}/content/js/StringUtil.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/bootstrap-tag.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/fuelux/fuelux.tree.min.js"></script>




	<script type="text/javascript">
		jQuery(function($) {
			loadAttach(docFlowId);
			loadDocFlow(docFlowId);
			loadPi(docFlowId);
			var DataSourceTree = function(options) {
				this.url = options.url;
			}

			DataSourceTree.prototype.data = function(options, callback) {
				var self = this;
				var $data = null;
				var param = null;
				if (!("name" in options) && !("type" in options)) {
					param = docFlowId; // load the first level
				} else if ("type" in options && options.type == "folder") {
					if ("additionalParameters" in options
							&& "children" in options.additionalParameters) {
						param = options.additionalParameters["id"];
					}
				}

				if (true) {
					$.ajax({
						url : this.url,
						data : 'pid=' + param,
						type : 'POST',
						dataType : 'json',
						success : function(response) {
							if (response.status == "OK")
								callback({
									data : response.data
								})
						},
						error : function(response) {
							console.log(response);
						}
					})
				}
			};

			$('#tree')
					.ace_tree(
							{
								dataSource : new DataSourceTree(
										{
											url : contextPath
													+ '/docFlowTask/selectTaskTreeListByPid.do?docFlowId='
													+ docFlowId
										}),
								multiSelect : false,
								loadingHTML : '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>',
								'open-icon' : 'ace-icon tree-minus',
								'close-icon' : 'ace-icon tree-plus',
								'selectable' : true,
								'selected-icon' : 'ace-icon fa fa-check',
								'unselected-icon' : 'ace-icon fa fa-times'
							});
		});
		function loadDocFlow(id) {
			$
					.ajax({
						type : "get",
						url : contextPath
								+ "/docFlow/selectDocFlowByPrimaryKey.do",
						data : {
							id : id
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							if (rst && rst.state) {
								$.each(rst.response, function(key, value) {
									$('#' + key).html(value);
								});

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
		function loadAttach(docFlowId) {
			$
					.ajax({
						type : "get",
						url : portalContextPath + "/attach/findAttachList.do",
						data : {
							noticeId : docFlowId
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							if (rst && rst.state) {
								var html = [];
								$
										.each(
												rst.list,
												function(n, file) {

													html
															.push('<div id="' + file.fileUrl + '" class="fileList0"><a  href="'
																	+ portalContextPath
																	+ '/files/download.do?collectionName=docFlow&originalFilename='
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

		function loadPi(docFlowId) {
			$
					.ajax({
						type : "get",
						url : contextPath
								+ "/docFlowTask/selectTaskListByDocFlowId.do",
						data : {
							docFlowId : docFlowId
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							if (rst && rst.state) {
								var html = [];
								html
										.push('<table class="altrowstable" style="width: 100%">');
								html.push('<tr>');
								html.push('<td>序号</td>');
								html.push('<td>时间</td>');
								html.push('<td>姓名</td>');
								html.push('<td>意见</td>');
								html.push('</tr>');
								$.each(rst.list, function(n, o) {
									html.push('<tr>');
									html.push('<td>' + (n + 1) + '</td>');
									html.push('<td>' + o.piDate + '</td>');
									html.push('<td>' + o.name + '</td>');
									html.push('<td>' + o.piContent + '</td>');
									html.push('</tr>');
								});
								html.push('</table>');
								$('#pilist').html(html.join(''));
							} else {
								alert(rst.errorMessage);
							}
						}
					});
		}
	</script>
</body>
</html>