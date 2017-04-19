<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var curYear = new Date().getFullYear();
	var portalContextPath = "${sessionScope.portalContextPath}";
	var urlid='${param.id}';
	var layoutTopHeight = 205;
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/system/getSessionSystemUser.do"></script>
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
	
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-ie8.min.css" />
<link rel="stylesheet"
	href= "${pageContext.request.contextPath}/content/assets/awesome/css/font-awesome.min.css" />
	
<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-part2.min.css" />
<![endif]-->
		
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace-rtl.min.css" />
	
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-ie.min.css" />
<![endif]-->

       <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src="${pageContext.request.contextPath}/content/assets/js/gz/html5shiv.js"></script>
	<script src="${pageContext.request.contextPath}/content/assets/js/gz/respond.min.js"></script>
	<![endif]-->		

<style type="text/css">
body {
	background-color: #FFFFFF;
	font-family: "微软雅黑";
	font-size: 12px;
}
.bigger-125 {
  font-size: 100% !important;
}
.bigger-110 {
  font-size: 100% !important;
}
.btn {
  padding: 1px 4px;
  font-size: 12px;
  line-height: 1.22857143;
}
.space10{
	height:2px;
	width:100%;
}
.ui-jqgrid .ui-jqgrid-pager {
  line-height: 15px;
  height: 36px;
  padding-top: 2px !important;
  padding-bottom: 2px !important;
  border-bottom: 1px solid #E1E1E1 !important;
  border-top: 1px solid #E1E1E1 !important;
}
.ui-jqgrid .ui-jqgrid-htable th span.ui-jqgrid-resize {
  height: 29px !important;
}
.ui-jqgrid .ui-jqgrid-htable th div {
  padding-top: 5px;
  padding-bottom: 5px;
}
.widget-header {
  font-size: 12px;
  min-height: 30px;
  height: 30px;
}
.widget-header>.widget-title {
    line-height: 30px;
}
/*表格编辑窗口标题栏高度*/
.ui-jqdialog .ui-widget-header {
    min-height: 30px;
}
.ui-jqdialog .ui-widget-header .ui-jqdialog-title {
    line-height: 30px;
}
.ui-jqdialog-content td.EditButton {
    padding: 5px;
}
.ui-jqdialog-content td.navButton {
    padding-bottom: 3px;
    padding-top: 3px;
}
.ui-dialog .ui-dialog-buttonpane {
    margin-top: .5em;
    padding: .0em .0em .0em .0em; 
}
.ui-button-text-only .ui-button-text {
    padding: 1px 1px;
}
.widget-header-pd{
	padding-top:7px
}
textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"] {
    padding: 1px 1px 1px; 
    font-size: 12px;
}
</style>


