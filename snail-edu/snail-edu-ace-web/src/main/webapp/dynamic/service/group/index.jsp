<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>分组信息</title>
</head>
<jsp:include page="../../common/common.jsp" />

<body>
	<div class="page-content">
		<!-- #section:elements.tab.option -->
		<div class="tabbable">
			<ul class="nav nav-tabs padding-16" id="myTab4">
				<li class="active">
				<a data-toggle="tab" href="#dept"> <i
						class="green ace-icon glyphicon glyphicon-user bigger-125"></i>
						自由组分组

				</a>
				</li>
				<li>
				<a data-toggle="tab" id="tab-group-grade" href="#grade"> <i
						class="green ace-icon fa fa-users bigger-125"></i> 年级分组
				</a>
				</li>
				<li><a data-toggle="tab" id="tab-group-discribline"
					href="#discripline"> <i
						class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
						学科分组
				</a></li>

<li><a data-toggle="tab" id="tab-group-free"
					href="#free"> <i
						class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
						自由分组
				</a></li>
			</ul>

			<div class="tab-content">
				<div id="dept" class="tab-pane in active">
					<div id="tree-dept-panel" class="easyui-panel"
						style="padding: 5px; width: 550px; height: 400px">
						<ul id="tree-dept"></ul>
					</div>
				</div>
				<div id="grade" class="tab-pane">
					<div id="tree-grade-panel" class="easyui-panel"
						style="padding: 5px; width: 550px; height: 400px">
						<ul id="tree-grade"></ul>
					</div>
				</div>
				<div id="discripline" class="tab-pane">
					<div id="tree-discribline-panel" class="easyui-panel"
						style="padding: 5px; width: 550px; height: 400px">
						<ul id="tree-discribline"></ul>
					</div>
				</div>
				<div id="free" class="tab-pane">
					<div id="tree-free-panel" class="easyui-panel"
						style="padding: 5px; width: 550px; height: 400px">
						<ul id="tree-free"></ul>
					</div>
				</div>

			</div>
		</div>

	</div>
	<jsp:include page="../../common/footer-1.jsp" />

	<script
		src="${pageContext.request.contextPath}/content/js/service/group/controller-dept.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/group/controller-grade.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/group/controller-discribline.js"></script>
<script
		src="${pageContext.request.contextPath}/content/js/service/group/controller-free.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	
</body>
</html>