
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <title>login</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace.onpage-help.css" />
		<script type="text/javascript">
		if (!window.console)
			window.console = {};
		if (!window.console.log)
			window.console.log = function() {
			};
		</script>
		<!--[if lte IE 8]>
			<script type="text/javascript" src="${pageContext.request.contextPath}/content/assets/js/gz/jquery1x.min.js"></script>
		<![endif]-->
	    <script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/content/assets/js/gz/jquery.min.js'>"+"<"+"/script>");
		</script>
<script
	src="${pageContext.request.contextPath}/content/assets/js/gz/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/content/assets/js/bootbox.min.js"></script>
		<style type="text/css">
	body {
		background-color: #FFFFFF;
		
		font-family: "微软雅黑";
		font-size: 1.2em;
	}
	</style>
	</head>
	<body  class="login-layout">

