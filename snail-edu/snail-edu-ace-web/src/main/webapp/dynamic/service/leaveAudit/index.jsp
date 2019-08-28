<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>leaveAudit</title>
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
					<input type="hidden" name="admin" value="">
						申请编号：<input name="leaveId" type="text" style="width: 200px;" />

						请假类型：<input class="easyui-combobox"
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
						申请人员：<select class="easyui-combogrid"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="leaveUserId"
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
						<button class="btn btn-info" id="btn-view-audit" authority="false">
							销假 <i
								class="ace-icon fa  fa-cog  align-top bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>


	</div>
	<div id="dialog-message" class="hide">
		<form id="fm-workflow" onsubmit="return false">
			<input name="leaveId" type="hidden">

			<table style="width: 100%"
				class="table-striped table-bordered table-hover">
				<tr>
					<td rowspan="2" align="center" colspan="2" height="60"
						style="font-size: 15px"><strong>请假类型</strong></td>
					<td align="center" height="30">公假</td>
					<td align="center">产假</td>
					<td align="center">病假</td>
					<td align="center">事假</td>
					<td align="center">探亲假</td>
					<td align="center">婚假</td>
					<td align="center">丧假</td>
					<td align="center">看护假</td>
					<td align="center">哺乳假</td>
					<td align="center">其他</td>
				</tr>
				<tr>
					<td height="30" align="center"><input type="radio"
						name="auditStatus" value="1" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="2" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="3" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="4" checked="checked" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="5" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="6" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="7" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="8" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="9" /></td>
					<td align="center"><input type="radio" name="auditStatus"
						value="10" /></td>
				</tr>

				<tr>
					<td height="200" align="center" style="font-size: 15px"><strong>备注</strong></td>
					<td height="34" colspan="12"><input
						class="easyui-validatebox textbox"
						style="width: 380px; height: 150px" name="auditRemark"
						maxlength="600" data-options="multiline:true,required:false"></td>
				</tr>



		<tr>
					<td height="100" align="center" style="font-size: 15px"><strong>附件</strong></td>
					<td height="34" colspan="12">
			<div>

				<div id="filelist-history"></div>
				<div id="filelist">Your browser doesn't have Flash,
					Silverlight or HTML5 support.</div>

				<div id="container">
					<a id="pickfiles" href="javascript:;">[添加附件]</a> <a
						id="uploadfiles" href="javascript:;">[上传]</a>
				</div>
				<br />
				<pre id="console"></pre>
			</div>
</td>
				</tr>
			</table>
	

		</form>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script type="text/javascript"
		src="${portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
	<script type="text/javascript"
		src="${portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript"
		src="${portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/leaveAudit/upload.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/leaveAudit/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/leaveAudit/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/leaveAudit/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/leaveAudit/view.js"></script>
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