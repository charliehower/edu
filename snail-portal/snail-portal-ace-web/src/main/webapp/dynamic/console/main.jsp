<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%!public boolean JudgeIsMoblie(HttpServletRequest request) {
		boolean isMoblie = false;
		String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
		"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
		"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
		"docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
		"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
		"wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
		"pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
		"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
		"blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
		"kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
		"mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
		"prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
		"smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
		"voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
		"Googlebot-Mobile" };
		if (request.getHeader("User-Agent") != null) {
		for (String mobileAgent : mobileAgents) {
		if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
		isMoblie = true;
		break;
		}
		}
		}
		return isMoblie;
		}%>
<%
	String cs = "widget-container-col";
	boolean isMobile = JudgeIsMoblie(request);
	if (isMobile) {
		cs = "";
	}
	request.setAttribute("cs", "cs");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title><%=request.getHeader("User-Agent")%></title>

</head>

<jsp:include page="/dynamic/common/common.jsp" />
<style>
.layout-user {
	width: 150px;
	height: 30px;
	float: left;
	margin: 5px 5px 5px;
}
</style>


<body>
	<!-- /section:basics/sidebar -->
	<div class="page-content">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-xs-12 col-sm-6 ${cs}">
				<!-- #section:custom/widget-box -->
				<div class="widget-box transparent" id="recent-box">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller">
							<i class="ace-icon glyphicon glyphicon-th-large green"></i>系统公告
						</h4>

						<div class="widget-toolbar no-border">
							<ul class="nav nav-tabs" id="recent-tab">


								<li class="active" style="height: 28px"><a
									data-toggle="tab" href="#notice-tab"
									style="padding-bottom: 0px; padding-top: 6px;">公告</a></li>
								<li style="height: 28px"><a data-toggle="tab"
									href="#dwmReport-tab"
									style="padding-bottom: 0px; padding-top: 6px;">部门总结</a></li>
								<li style="height: 28px"><a data-toggle="tab"
									href="#duty-tab" style="padding-bottom: 0px; padding-top: 6px;">今日值班</a></li>
								<li style="height: 28px"><a data-toggle="tab"
									href="#comment-tab"
									style="padding-bottom: 0px; padding-top: 6px;">日程安排</a></li>
							</ul>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main padding-4">
							<div class="tab-content padding-8">
								<div id="duty-tab" class="tab-pane"></div>
								<div id="dwmReport-tab" class="tab-pane">
									<table width="100%">


										<tbody id="dwmReport-list-grid-3">

										</tbody>
									</table>
								</div>

								<div id="notice-tab" class="tab-pane active">
									<table width="100%">


										<tbody id="notice-list-grid">

										</tbody>
									</table>
								</div>
								<!-- /.#member-tab -->

								<div id="comment-tab" class="tab-pane">comment</div>
							</div>
						</div>
						<!-- /.widget-main -->
					</div>
					<!-- /.widget-body -->
				</div>
				<!-- /.widget-box -->

				<!-- /section:custom/widget-box -->
			</div>

			<div class="col-xs-12 col-sm-6 ${cs}">
				<div class="widget-box widget-color-blue">
					<!-- #section:custom/widget-box.options -->
					<div class="widget-header">
						<h5 class="widget-title bigger lighter">
							<i class="ace-icon fa fa-table"></i> <a
								href="javascript:parent.addPanel('电子工作流','workflow.jsp',true)"
								style="color: #FFFFFF; font-size: 16px; text-decoration: none;">
								电子工作流</a>
						</h5>

						<div class="widget-toolbar widget-toolbar-light no-border">

							<select id="simple-colorpicker-1" class="hide">
								<option selected="" data-class="blue" value="#307ECC">#307ECC</option>
								<option data-class="blue2" value="#5090C1">#5090C1</option>
								<option data-class="blue3" value="#6379AA">#6379AA</option>
								<option data-class="green" value="#82AF6F">#82AF6F</option>
								<option data-class="green2" value="#2E8965">#2E8965</option>
								<option data-class="green3" value="#5FBC47">#5FBC47</option>
								<option data-class="red" value="#E2755F">#E2755F</option>
								<option data-class="red2" value="#E04141">#E04141</option>
								<option data-class="red3" value="#D15B47">#D15B47</option>
								<option data-class="orange" value="#FFC657">#FFC657</option>
								<option data-class="purple" value="#7E6EB0">#7E6EB0</option>
								<option data-class="pink" value="#CE6F9E">#CE6F9E</option>
								<option data-class="dark" value="#404040">#404040</option>
								<option data-class="grey" value="#848484">#848484</option>
								<option data-class="default" value="#EEE">#EEE</option>
							</select>
						</div>
					</div>

					<!-- /section:custom/widget-box.options -->
					<div class="widget-body">
						<div class="widget-main no-padding">
							<table class="table table-striped table-bordered table-hover">
								<thead class="thin-border-bottom">
									<tr>
										<th>序号</th>

										<th>电子流程名称</th>
										<th>版本</th>
										<th>申请</th>
										<th>代理</th>
										<th>查看</th>
									</tr>
								</thead>

								<tbody id="workflow-list-grid">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- /.span -->
		</div>
		<!-- /.row -->

		<div class="space-12"></div>


		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-xs-12 col-sm-6 ${cs}">
				<!-- #section:custom/widget-box -->
				<div class="widget-box">
					<div class="widget-header">
						<h5 class="widget-title">周结周报</h5>

						<!-- #section:custom/widget-box.toolbar -->
						<div class="widget-toolbar">
							<div class="widget-menu">
								<a href="#" data-action="settings" data-toggle="dropdown"> <i
									class="ace-icon fa fa-bars"></i>
								</a>

								<ul
									class="dropdown-menu dropdown-menu-right dropdown-light-blue dropdown-caret dropdown-closer">
									<li><a data-toggle="tab" href="#dropdown1">Option#1</a></li>

									<li><a data-toggle="tab" href="#dropdown2">Option#2</a></li>
								</ul>
							</div>

							<a href="#" data-action="fullscreen" class="orange2"> <i
								class="ace-icon fa fa-expand"></i>
							</a> <a href="#" data-action="reload"> <i
								class="ace-icon fa fa-refresh"></i>
							</a> <a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a> <a href="#" data-action="close"> <i
								class="ace-icon fa fa-times"></i>
							</a>
						</div>

						<!-- /section:custom/widget-box.toolbar -->
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<table width="100%">


								<tbody id="dwmReport-list-grid-2">

								</tbody>
							</table>

						</div>
					</div>
				</div>

			</div>
			<!-- /.span -->
			<div class="col-xs-12 col-sm-6 ${cs}">
				<!-- #section:custom/widget-box -->
				<div class="widget-box">
					<div class="widget-header">
						<h5 class="widget-title">每日常规检查通报</h5>

						<!-- #section:custom/widget-box.toolbar -->
						<div class="widget-toolbar">
							<div class="widget-menu">
								<a href="#" data-action="settings" data-toggle="dropdown"> <i
									class="ace-icon fa fa-bars"></i>
								</a>

								<ul
									class="dropdown-menu dropdown-menu-right dropdown-light-blue dropdown-caret dropdown-closer">
									<li><a data-toggle="tab" href="#dropdown1">Option#1</a></li>

									<li><a data-toggle="tab" href="#dropdown2">Option#2</a></li>
								</ul>
							</div>

							<a href="#" data-action="fullscreen" class="orange2"> <i
								class="ace-icon fa fa-expand"></i>
							</a> <a href="#" data-action="reload"> <i
								class="ace-icon fa fa-refresh"></i>
							</a> <a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a> <a href="#" data-action="close"> <i
								class="ace-icon fa fa-times"></i>
							</a>
						</div>

						<!-- /section:custom/widget-box.toolbar -->
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<table width="100%">


								<tbody id="dwmReport-list-grid-1">

								</tbody>
							</table>
						</div>
					</div>
				</div>

				<!-- /section:custom/widget-box -->
			</div>


		</div>
		<!-- /.row -->













	</div>
	<!-- /.page-content -->
	<div id="dialog-confirm" class="hide">
		<div class="alert alert-info bigger-110">点击确定后，系统将开启一个电子工作流程.</div>

		<div class="space-6"></div>

		<p class="bigger-110 bolder center grey">
			<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> 您确定吗?
		</p>
	</div>

	<div id="dialog-message" class="hide">
		<div class="profile-info-row">
			<div class="profile-info-name">被代理人</div>

			<div class="profile-info-value">
				<select class="easyui-combogrid"
					style="width: 400px; height: 25px; line-height: 25px;" name="proxy"
					data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select>
			</div>
		</div>
	</div>

	<jsp:include page="/dynamic/common/footer-1.jsp" />
	<jsp:include page="/dynamic/common/footer-2.jsp" />
	<script type="text/javascript">
		jQuery(function($) {

			$('#simple-colorpicker-1')
					.ace_colorpicker({
						pull_right : true
					})
					.on(
							'change',
							function() {
								var color_class = $(this).find(
										'option:selected').data('class');
								var new_class = 'widget-box';
								if (color_class != 'default')
									new_class += ' widget-color-' + color_class;
								$(this).closest('.widget-box').attr('class',
										new_class);
							});

			// scrollables
			$('.scrollable').each(function() {
				var $this = $(this);
				$(this).ace_scroll({
					size : $this.data('height') || 100,
				//styleClass: 'scroll-left scroll-margin scroll-thin scroll-dark scroll-light no-track scroll-visible'
				});
			});
			$('.scrollable-horizontal').each(function() {
				var $this = $(this);
				$(this).ace_scroll({
					horizontal : true,
					styleClass : 'scroll-top',//show the scrollbars on top(default is bottom)
					size : $this.data('width') || 500,
					mouseWheelLock : true
				}).css({
					'padding-top' : 12
				});
			});

			$(window).on('resize.scroll_reset', function() {
				$('.scrollable-horizontal').ace_scroll('reset');
			});

			/**
			//or use slimScroll plugin
			$('.slim-scrollable').each(function () {
				var $this = $(this);
				$this.slimScroll({
					height: $this.data('height') || 100,
					railVisible:true
				});
			});
			 */

			/**$('.widget-box').on('setting.ace.widget' , function(e) {
				e.preventDefault();
			});*/

			/**
			$('.widget-box').on('show.ace.widget', function(e) {
				//e.preventDefault();
				//this = the widget-box
			});
			$('.widget-box').on('reload.ace.widget', function(e) {
				//this = the widget-box
			});
			 */

			//$('#my-widget-box').widget_box('hide');
			// widget boxes
			// widget box drag & drop example
			$('.${cs}').sortable({
				connectWith : '.${cs}',
				items : '> .widget-box',
				opacity : 0.8,
				revert : true,
				forceHelperSize : true,
				placeholder : 'widget-placeholder',
				forcePlaceholderSize : true,
				tolerance : 'pointer',
				start : function(event, ui) {
					//when an element is moved, it's parent becomes empty with almost zero height.
					//we set a min-height for it to be large enough so that later we can easily drop elements back onto it
					ui.item.parent().css({
						'min-height' : ui.item.height()
					})
					//ui.sender.css({'min-height':ui.item.height() , 'background-color' : '#F5F5F5'})
				},
				update : function(event, ui) {
					ui.item.parent({
						'min-height' : ''
					})
					//p.style.removeProperty('background-color');
				}
			});

		});
	</script>
	<script
		src="${pageContext.request.contextPath}/content/js/console/main/main.js"></script>
</body>
</html>