<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>duty</title>
</head>
<link rel="stylesheet" href="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<style type="text/css">
		.excel{ background-color:#999; font-size:13px;}
		.excel td{ background-color:#fff; white-space:nowrap;}
		.excel th{ background-color:#E7E7E7; font-weight:normal;}
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
					
						值班日期：<input class="easyui-datebox" name="dateTime" style="width:200px;height:30px;line-height: 30px;">
						
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/duty/findDutyList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/duty/insertDuty.do">
							<i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/duty/updateDuty.do">
							<i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>
						
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/duty/deleteDutyByDutyId.do">
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
	
 <div style="margin:5px">

		           1、姓名与号码之间用“:”隔开,人与人之间用“/”隔开;<br>
		           2、无号码，则默认个人信息的联系方式;<br>
		           3、填写请<a href="duty_day2.xls" style="color:red">下载模板</a>.<br>
		      
		
 </div>



		</div>
		
		

	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/duty/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/duty/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/duty/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/duty/view.js"></script>
		<script
		src="${pageContext.request.contextPath}/content/js/service/duty/upload.js"></script>
		<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
			<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
		
	<jsp:include page="../../common/footer-2.jsp" />
	
	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
}
</script>
</body>
</html>