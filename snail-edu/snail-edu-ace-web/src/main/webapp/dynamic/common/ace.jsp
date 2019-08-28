<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>企业资源管理</title>
</head>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/js/common/base.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/jquery-ui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/datepicker.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ui.jqgrid.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace-fonts.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace-rtl.min.css" />
<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-part2.min.css" />
		<![endif]-->

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-ie.min.css" />
		<![endif]-->
<!-- inline styles related to this page -->
<!--[if lte IE 8]>
		<script src="${pageContext.request.contextPath}/content/assets/js/html5shiv.js"></script>
		<script src="${pageContext.request.contextPath}/content/assets/js/respond.min.js"></script>
		<![endif]-->

<style type="text/css">
body {
	background-color: #FFFFFF;
	font-family: "微软雅黑";
	font-size: 1em;
}
</style>
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
						departmentName： <input name="departmentName" type="text"
							style="width: 200px;"/>
							language
							<input class="easyui-combobox"
							name="language" style="width: 200px; height:30px"
							data-options="url:'${pageContext.request.contextPath}/system/loadDictByKey.do?key=STATIC_DATA_02',method:'get',valueField:'CODE', textField:'NAME',panelHeight:'auto'"/>
						<button class="btn btn-info" id="btn-search"
							authority="/department/findDepartmentList.do">
							Query <i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="/department/insertDepartment.do">
							Add <i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="/department/updateDepartment.do">
							Edit <i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn" id="bootbox-regular"
							authority="/department/insertDepartment.do">Regular
							Dialog</button>
						<button class="btn btn-info" id="bootbox-confirm"
							authority="/department/insertDepartment.do">Confirm</button>
						<button class="btn btn-success" id="bootbox-options"
							authority="/department/insertDepartment.do">More Options</button>
						<h3 class="header blue lighter smaller">
							<i class="ace-icon fa fa-list-alt smaller-90"></i> Dialogs
						</h3>
						<a href="#" id="id-btn-dialog2" class="btn btn-info btn-sm">Confirm
							Dialog</a> <a href="#" id="id-btn-dialog1"
							class="btn btn-purple btn-sm">Modal Dialog</a>

						<div id="dialog-message" class="hide">
							<p>This is the default dialog which is useful for displaying
								information. The dialog window can be moved, resized and closed
								with the 'x' icon.</p>

							<div class="hr hr-12 hr-double"></div>

							<p>
								Currently using <b>36% of your storage space</b>.
							</p>
						</div>
						<!-- #dialog-message -->

						<div id="dialog-confirm" class="hide">
							<div class="alert alert-info bigger-110">These items will
								be permanently deleted and cannot be recovered.</div>

							<div class="space-6"></div>

							<p class="bigger-110 bolder center grey">
								<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> Are
								you sure?
							</p>
						</div>
						<!-- #dialog-confirm -->

					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>

	</div>

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${pageContext.request.contextPath}/content/assets/js/jquery.min.js'>"
								+ "<"+"/script>");
	</script>



	<script
		src="${pageContext.request.contextPath}/content/assets/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/assets/js/bootbox.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/assets/js/jquery-ui.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/assets/js/uncompressed/jqGrid/jquery.jqGrid.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/assets/js/ace.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/assets/js/ace-elements.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/content/assets/js/ace-extra.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/console/department/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/console/department/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/console/department/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/console/department/view.js"></script>

	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/content/jquery-easyui-1.3.6/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/content/jquery-easyui-1.3.6/themes/icon.css">
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/content/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/content/js/common/jquery.form.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/content/js/common/authority.js"></script>




</body>
</html>