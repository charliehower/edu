<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String start = org.platform.snail.utils.SnailUtils
			.parseDate(new java.util.Date()) + " 00:00:00";
	String end = org.platform.snail.utils.SnailUtils
			.parseDate(new java.util.Date()) + " 23:59:59";
	request.setAttribute("start", start);
	request.setAttribute("end", end);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>calendar</title>
</head>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var curYear = new Date().getFullYear();
	var portalContextPath = "${sessionScope.portalContextPath}";
	var urlid='${param.id}';
</script>
<script type="text/javascript"
	src="${sessionScope.portalContextPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript"
	src="${sessionScope.portalContextPath}/system/getSessionSystemUser.do"></script>
<script type="text/javascript"
	src="${sessionScope.portalContextPath}/content/js/common/base.js"></script>
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/jquery-ui.min.css" />
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/datepicker.css" />

<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/ace-fonts.css" />
	
<link rel="stylesheet" href="${sessionScope.portalContextPath}/content/assets/css/ace-ie8.min.css" />
<link rel="stylesheet"
	href= "${sessionScope.portalContextPath}/content/assets/awesome/css/font-awesome.min.css" />
	
<!--[if lte IE 9]>
			<link rel="stylesheet" href="${sessionScope.portalContextPath}/content/assets/css/ace-part2.min.css" />
<![endif]-->
		
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/ace-rtl.min.css" />
	
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${sessionScope.portalContextPath}/content/assets/css/ace-ie.min.css" />
<![endif]-->

       <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src="${sessionScope.portalContextPath}/content/assets/js/gz/html5shiv.js"></script>
	<script src="${sessionScope.portalContextPath}/content/assets/js/gz/respond.min.js"></script>
	<![endif]-->		

<style type="text/css">
body {
	background-color: #FFFFFF;
	
	font-family: "微软雅黑";
	font-size: 1.2em;
}

</style>

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/fullcalendar.css" />

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
<div style="text-align:center">
<h2 id="_title"></h2>
<div id="_subtitle" style="font-size:16px"></div>
</div>

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-sm-9">
						<div class="space"></div>


						<!-- #section:plugins/data-time.calendar -->
						<div id="calendar"></div>

						<!-- /section:plugins/data-time.calendar -->
					</div>

					<div class="col-sm-3">
						<!--  <div class="widget-box transparent">
							<div class="widget-header">
								<h4>常用事件</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main no-padding">
									<div id="external-events">
										<div class="external-event label-grey" data-class="label-grey">
											<i class="ace-icon fa fa-arrows"></i> 会议
										</div>

										<div class="external-event label-success"
											data-class="label-success">
											<i class="ace-icon fa fa-arrows"></i> 报告
										</div>

										<div class="external-event label-danger"
											data-class="label-danger">
											<i class="ace-icon fa fa-arrows"></i> 听课
										</div>

										<div class="external-event label-purple"
											data-class="label-purple">
											<i class="ace-icon fa fa-arrows"></i> 讲课
										</div>

										<div class="external-event label-yellow"
											data-class="label-yellow">
											<i class="ace-icon fa fa-arrows"></i> 外出
										</div>

										<div class="external-event label-pink" data-class="label-pink">
											<i class="ace-icon fa fa-arrows"></i> 自习
										</div>

										<div class="external-event label-info" data-class="label-info">
											<i class="ace-icon fa fa-arrows"></i> 出差
										</div>

										<label> <input type="checkbox"
											class="ace ace-checkbox" id="drop-remove" /> <span
											class="lbl"> 移除删除</span>
										</label>

									</div>

								</div>
							</div>
						</div>-->
						<div class="widget-box transparent">
							<div class="widget-header">
								<h4>场地</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main no-padding">
									<div id="tree-dept" class="tree"></div>
								</div>
							</div>
						</div>
						<!-- end col-sm-3 -->
					</div>
				</div>

				<!-- PAGE CONTENT ENDS -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->

	</div>
		<!-- basic scripts -->
		<!--[if lte IE 8]>
			<script type="text/javascript" src="${sessionScope.portalContextPath}/content/assets/js/gz/jquery1x.min.js"></script>
		<![endif]-->
	    <script type="text/javascript">
			window.jQuery || document.write("<script src='${sessionScope.portalContextPath}/content/assets/js/gz/jquery.min.js'>"+"<"+"/script>");
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
	src="${sessionScope.portalContextPath}/content/js/common/jquery.ba-resize.js"></script>

	<script
		src="${sessionScope.portalContextPath}/content/assets/js/fuelux/fuelux.spinner.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/bootstrap-timepicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/uncompressed/date-time/moment.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/daterangepicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/bootstrap-colorpicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/jquery.knob.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/jquery.autosize.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/jquery.maskedinput.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/bootstrap-tag.min.js"></script>
<script src="${sessionScope.portalContextPath}/content/assets/js/fuelux/fuelux.tree.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/siteApp/fullcalendar.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/siteApp/controller.js"></script>


	<div id="dialog-message" class="hide">
		<div>
			<label for="simple-colorpicker-1">颜色</label> <select
				id="simple-colorpicker-1" name="backgroundColor" class="hide">
				<option value="#ac725e">#ac725e</option>
				<option value="#d06b64">#d06b64</option>
				<option value="#f83a22">#f83a22</option>
				<option value="#fa573c">#fa573c</option>
				<option value="#ff7537">#ff7537</option>
				<option value="#ffad46">#ffad46</option>
				<option value="#42d692">#42d692</option>
				<option value="#16a765">#16a765</option>
				<option value="#7bd148">#7bd148</option>
				<option value="#b3dc6c">#b3dc6c</option>
				<option value="#fbe983">#fbe983</option>
				<option value="#fad165">#fad165</option>
				<option value="#92e1c0">#92e1c0</option>
				<option value="#9fe1e7">#9fe1e7</option>
				<option value="#9fc6e7">#9fc6e7</option>
				<option value="#4986e7">#4986e7</option>
				<option value="#9a9cff">#9a9cff</option>
				<option value="#b99aff">#b99aff</option>
				<option value="#c2c2c2">#c2c2c2</option>
				<option value="#cabdbf">#cabdbf</option>
				<option value="#cca6ac">#cca6ac</option>
				<option value="#f691b2">#f691b2</option>
				<option value="#cd74e6">#cd74e6</option>
				<option value="#a47ae2">#a47ae2</option>
				<option value="#555">#555</option>
			</select>



		</div>
		<div>
			<label for="form-field-9">标题</label>
			<textarea class="form-control limited" id="form-field-9"
				maxlength="50" name="title"></textarea>
		</div>
		<div>
		<input type="hidden" name="category">
			<label for="form-field-9">内容</label>
			<textarea class="form-control limited" id="form-field-9"
				maxlength="50" name="content"></textarea>
		</div>
		<div>
			<label for="date-timepicker1">开始</label>

			<!-- #section:plugins/date-time.datetimepicker -->
			<div class="input-group">
				<input id="date-timepicker1" name="start" type="text"
					data-date-format="YYYY-MM-DD HH:MM" class="form-control" /> <span
					class="input-group-addon"> <i
					class="fa fa-clock-o bigger-110"></i>
				</span>
			</div>
		</div>
		<div>
			<label for="date-timepicker2">截止</label>

			<!-- #section:plugins/date-time.datetimepicker -->
			<div class="input-group">
				<input id="date-timepicker2" name="end" type="text"
					data-date-format="YYYY-MM-DD HH:MM" class="form-control" /> <span
					class="input-group-addon"> <i
					class="fa fa-clock-o bigger-110"></i>
				</span>
			</div>
		</div>


	</div>
</body>
</html>