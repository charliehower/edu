<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var curYear = new Date().getFullYear();
	var portalContextPath = "${sessionScope.portalContextPath}";
</script>
<script type="text/javascript"
	src= "${sessionScope.portalContextPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript"
	src= "${sessionScope.portalContextPath}/system/getSessionSystemUser.do"></script>
<script type="text/javascript"
	src= "${sessionScope.portalContextPath}/content/js/common/base.js"></script>
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/css/jquery-ui.min.css" />
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/css/datepicker.css" />
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/css/ui.jqgrid.css" />
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/css/ace-fonts.css" />
	
<link rel="stylesheet" href= "${sessionScope.portalContextPath}/content/assets/css/ace-ie8.min.css" />

	
<!--[if lte IE 9]>
			<link rel="stylesheet" href= "${sessionScope.portalContextPath}/content/assets/css/ace-part2.min.css" />
<![endif]-->
		
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/css/ace-rtl.min.css" />
	
<!--[if lte IE 9]>
		  <link rel="stylesheet" href= "${sessionScope.portalContextPath}/content/assets/css/ace-ie.min.css" />
<![endif]-->

       <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src= "${sessionScope.portalContextPath}/content/assets/js/gz/html5shiv.js"></script>
	<script src= "${sessionScope.portalContextPath}/content/assets/js/gz/respond.min.js"></script>
	<![endif]-->		

<style type="text/css">
body {
	background-color: #FFFFFF;
	
	font-family: "微软雅黑";
	font-size: 1.2em;
}
</style>


