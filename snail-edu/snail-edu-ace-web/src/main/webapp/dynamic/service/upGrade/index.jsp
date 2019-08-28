<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	java.util.Calendar a = java.util.Calendar.getInstance();
	int year = a.get(java.util.Calendar.YEAR);
	request.setAttribute("year", year);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>upGrade</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	var year = '${year}';
</script>
<body>
	<div class="page-content">


		<h3 class="header smaller lighter green">班级一键升级</h3>

		<!-- #section:elements.button.app -->
		<p>
		<h3>升级年度：${year}</h3>

		</p>

		<p>


			<button class="btn btn-app btn-info" id="btn-upGrade">
				<i class="ace-icon fa fa-share bigger-230"></i> 点击升级
			</button>
			<button class="btn btn-app btn-warning" id="btn-upGrade-undo">
				<i class="ace-icon fa fa-undo bigger-230"></i> 回退
			</button>


		</p>
		<p style="color: red">每年只能进行一次升级，毕业班放到当年毕业年级!</p>



	</div>

	<jsp:include page="../../common/footer-1.jsp" />

	<script
		src="${pageContext.request.contextPath}/content/js/service/upGrade/controller.js"></script>

</body>
</html>