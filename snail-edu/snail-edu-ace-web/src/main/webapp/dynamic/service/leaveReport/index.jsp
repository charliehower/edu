<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>leaveReport</title>
</head>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet"
	href="${portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />

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
						<input type="hidden" name="reportId" value="leave">
						申请时间：<input class="easyui-datebox" name="startDate"
							style="width: 200px; height: 25px; line-height: 25px;"> 至
						<input class="easyui-datebox" name="endDate"
							style="width: 200px; height: 25px; line-height: 25px;"> 
						
						申请人员：<select class="easyui-combogrid"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="userId"
							data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select>

						<button class="btn btn-info" id="btn-search"
							authority="false">
							查询<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">
						
					</div>
				</div>
			</div>
		</div>

		<table class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th width="80px">序号</th>
								<th>请假类型</th>
								<th>天数</th>
							</tr>
						</thead>
						<tbody id="gd-report">
						</tbody>
					</table>


	</div>
	
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/leaveReport/controller.js"></script>
	
	<jsp:include page="../../common/footer-2.jsp" />
	
</body>
</html>