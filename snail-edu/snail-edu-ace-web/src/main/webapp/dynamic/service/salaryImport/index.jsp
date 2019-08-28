<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>salaryImport</title>
</head>
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />
<style type="text/css">
.excel {
	background-color: #999;
	font-size: 13px;
}

.excel td {
	background-color: #fff;
	white-space: nowrap;
}

.excel th {
	background-color: #E7E7E7;
	font-weight: normal;
}
</style>

<jsp:include page="../../common/common.jsp" />
<body>
	<div class="page-content">
		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form action="#" id="fm-search">
年度： <select name="year" id="year">
			</select> 月份： <select id="month" name="month">
			<option value="">请选择</option>
			</select> 职工类别： <select id="category" name="category">
			<option value="c">正式职工（新）</option>
				<option value="b">正式职工</option>
				<option value="a">临聘职工</option>
				<option value="">请选择</option>
			</select>

						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/salaryImport/findSalaryImportList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-purple" id="btn-view-add"
							authority="${pageContext.request.contextPath}/salaryImport/insertSalaryImport.do">
							<i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>

						<button class="btn btn-info" id="btn-view-detail"
							authority="false">
							详细 <i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/salaryImport/deleteSalaryImportBySalaryImportId.do">
							<i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>

					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>



	</div>
	<div id="dialog-message" class="hide">
	
		<div id="uploader">
			<p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
		</div>

		<div style="margin: 7px">
			<div style="font-weight:800">Excel 导入请下载模板，导入前请在查询页面选择导入年份、月份与职工类别</div>
			<a href="salaryc.xls" >正式职工(Excel新模板)</a><br>
			<a href="salaryb.xls" >正式职工(Excel模板)</a><br>
			
			<a href="salarya.xls">临聘职工(Excel模板)</a><br>

		</div>



	</div>



	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/salaryImport/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/salaryImport/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/salaryImport/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/salaryImport/view.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/salaryImport/upload.js"></script>
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

	<jsp:include page="../../common/footer-2.jsp" />

	<script type="text/javascript">
		window.onresize = function() {
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight - layoutTopHeight);
		}
		var currentYear = new Date().getFullYear();
		var select = document.getElementById("year");
		for (var i = 0; i <= 3; i++) {
			var theOption = document.createElement("option");
			theOption.innerHTML = currentYear - i + "年";
			theOption.value = currentYear - i;
			select.appendChild(theOption);
		}
		var currentMonth = new Date().getMonth()+1;
		var select = document.getElementById("month");
		for (var i = 12; i >= 1; i--) {
			var theOption = document.createElement("option");
			theOption.innerHTML =  i + "月";
			theOption.value =i;
			select.appendChild(theOption);
		}
		select.value=currentMonth;
	</script>

</body>
</html>