<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>positive</title>
</head>
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
					编号： <input name="teacherId" type="text" style="width: 100px;" />
						姓名： <input name="name" type="text" style="width: 200px;" />
						是否转正：
						<select name="stauts">
							<option value="1">否</option>
							<option value="2">是</option>
						</select>

						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/positive/findPositiveList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						
						
						
						<button class="btn btn-info" id="btn-view-detail"
							authority="false">
							查看
							<i
								class="ace-icon glyphicon glyphicon-list-alt  align-top bigger-125 icon-on-right"></i>
						</button>

					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>

		
		
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/positive/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/positive/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/positive/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/positive/view.js"></script>
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