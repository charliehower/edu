<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%
	String id = request.getParameter("id");
	request.setAttribute("edit", "true");
	if (SnailUtils.isBlankString(id)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
		id = String.valueOf(new java.util.Date().getTime());
		request.setAttribute("edit", "false");
	}
	request.setAttribute("id", id);
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
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />

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
.fileList0{
	border-bottom:2px solid #eeeeee;
	width:50%;
	font-size:14px
}
#container{
font-size:14px
}
 </style>
<body>
	<div class="page-content">

		<form id="fm-docFlow">
			<div>
				<input type="hidden" name="id" value="${id }"> <input
					type="hidden" name="status" value="0">
				<table class="altrowstable" id="alternatecolor" style="width: 100%">
					<tr>
						<td align="right">来文单位</td>
						<td><input class="easyui-validatebox textbox"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="docDept"
							data-options="required:false,validType:'length[1,50]'"></td>
						<td align="right">来文编号</td>
						<td><input class="easyui-validatebox textbox"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="docNo"
							data-options="required:false,validType:'length[1,50]'"></td>
						<td align="right">来文日期</td>
						<td><input class="easyui-datebox"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="docDate" data-options="required:false"></td>
					</tr>
					<tr>
						<td align="right">标题</td>
						<td colspan="5"><input class="easyui-validatebox textbox"
							style="width: 600px; height: 25px; line-height: 25px;"
							name="title"
							data-options="required:false,validType:'length[1,200]'"></td>

					</tr>
					<tr>
						<td align="right">校内编号</td>
						<td><input class="easyui-validatebox textbox"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="nativeNo"
							data-options="required:false,validType:'length[1,50]'"></td>

						<td align="right">发布者</td>
						<td><input class="easyui-validatebox textbox"
							style="width: 200px; height: 25px; line-height: 25px;readOnly:true"
							name="deployUserName" value="${SystemUser.users.name }"
							data-options="required:false,validType:'length[1,50]'"></td>
						<td align="right">发布日期</td>
						<td><input class="easyui-datebox"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="deployDate" data-options="required:false"></td>
					</tr>
					<tr>
						<td colspan="6">

							<div>
								<textarea rows="" cols="" name="content"></textarea>
							</div> <br />
							<div id="filelist-history">
							
							</div>
							<div id="filelist">Your browser doesn't have Flash,
								Silverlight or HTML5 support.</div>

							<div id="container">
								公文：<a id="pickfiles" href="javascript:;">[添加公文]</a> <a
									id="uploadfiles" href="javascript:;">[上传]</a>
							</div> <br /> <pre id="console"></pre>
							<div>
								<button class="btn btn-info" id="btn-docFlow-submit"
									authority="false">
									<i class="ace-icon fa fa-check bigger-110"></i> 保存
								</button>
							</div>
						</td>
					</tr>
				</table>


			</div>



		</form>

	</div>

	<!-- /section:elements.tab.option -->






	<jsp:include page="../../common/footer-1.jsp" />
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

	<script
		src="${pageContext.request.contextPath}/content/js/service/docFlow/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/docFlow/upload.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />

	<ckeditor:replaceAll basePath="/portal/ckeditor/" />
</body>
</html>