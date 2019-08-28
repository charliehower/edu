<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>leave</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	
</script>
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
						 申请编号：<input
							name="leaveId" type="text" style="width: 200px;" /> 请假类型：<input
							class="easyui-combobox"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="categoryId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=28&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
						申请时间：<input class="easyui-datetimebox" name="startDate"
							style="width: 200px; height: 25px; line-height: 25px;"> 至
						<input class="easyui-datetimebox" name="endDate"
							style="width: 200px; height: 25px; line-height: 25px;"> <br>
						状态：<input class="easyui-combobox"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="status"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=29&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
						

						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/leave/findLeaveList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-detail"
							authority="false">
							查看 <i
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
		src="${pageContext.request.contextPath}/content/js/service/leave/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/leave/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/leave/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/leave/view.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight - layoutTopHeight);
		}
	</script>
</body>
</html>