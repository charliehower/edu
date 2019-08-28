<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>pm</title>
</head>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />
<style type="text/css">
		.excel{ background-color:#999; font-size:13px;}
		.excel td{ background-color:#fff; white-space:nowrap;}
		.excel th{ background-color:#E7E7E7; font-weight:normal;}
	</style>
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
						姓名： <input name="studentName" type="text" style="width: 100px;" />
						
						学年：<input
							class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="years"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=19&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
            
            班级：<input
							class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="classesId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=15&gradeId=1,2,3&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
						
						  学科：<input
							class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="subjectId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=16&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/pm/findPmList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/pm/insertPm.do">
							<i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>

						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/pm/updatePm.do">
							<i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>

						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/pm/deletePmByPmId.do">
							<i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-import"
							authority="${pageContext.request.contextPath}/pm/importXls.do">
							<i
								class="ace-icon glyphicon glyphicon-upload  align-top bigger-125 icon-on-right"></i>
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
 1、必须提供考号、姓名;<br>
		           2、填写请<a href="pm.xls" style="color:red">下载模板</a>.<br>
		         
		      
		
 </div>
	</div>
	<div id="dialog-message-file" class="hide">
		<div id="load" class="loading"></div>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/pm/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/pm/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/pm/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/pm/view.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/pm/upload.js"></script>
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