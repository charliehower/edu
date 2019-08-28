<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>user</title>
</head>
<script type="text/javascript">
	var urlid = '${param.id}';
	var edit = false;
</script>
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
						总结类型：
		<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="subCategoryId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=33&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
					部门： 
							<input id="cc1" name="departmentId" class="easyui-combotree" data-options="url:'${sessionScope.portalContextPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false," style='width:200px;line-height: 30px;height: 30px;'>
						标题： <input name="title" type="text" style="width: 150px;" /> 
					学期：<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="type"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=32&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
            	周：<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="weekId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=34&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
            时间:
							<input class="easyui-datebox" name="startDate" style="width:100px;height:30px;line-height: 30px;">
							至
							<input class="easyui-datebox" name="endDate" style="width:100px;height:30px;line-height: 30px;">
					
						<button class="btn btn-info" id="btn-search"
							authority="false">
							部门总结查询<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="false">
							部门总结添加
							<i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="false">
							部门总结变更
							<i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>
						
						<!--  
						<button class="btn btn-warning" id="btn-view-push"
							authority="${pageContext.request.contextPath}/dwmReport/updateForStatusByPrimaryKey.do">
							<i
								class="ace-icon glyphicon  glyphicon-cog  align-top bigger-125 icon-on-right"></i>
						</button>-->
						<button class="btn btn-warning" id="btn-view-del"
							authority="false">
							部门总结删除
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
		<div class="profile-info-row">
			<div class="profile-info-name">自由组</div>

			<div class="profile-info-value">
				<input class="easyui-combobox"
					style="width: 100px; height: 25px; line-height: 25px;"
					name="groupId" id="groupId"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=20&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">部门</div>

			<div class="profile-info-value">
				<input name="departmentId"  id="departmentId" class="easyui-combotree"
					data-options="url:'${sessionScope.portalContextPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false,"
					style='width: 200px; line-height: 30px; height: 30px;'>
			</div>
		</div>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/mReport/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/mReport/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/mReport/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/mReport/view.js"></script>
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