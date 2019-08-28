<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>teacher</title>
</head>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet" href="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
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
					编号： <input name="teacherId" type="text" style="width: 100px;" />
						姓名： <input name="name" type="text" style="width: 200px;" />
						学科：<input class="easyui-combobox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="disciplineId"
									data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=16&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
						
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/teacher/findTeacherList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/teacher/insertTeacher.do">
							<i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-import"
							authority="${pageContext.request.contextPath}/teacher/importTeacher.do">
							<i
								class="ace-icon glyphicon glyphicon-upload  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/teacher/updateTeacher.do">
							<i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>
						
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/teacher/deleteTeacherByTeacherId.do">
							<i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-quit"
							authority="${pageContext.request.contextPath}/teacher/updateQuitByIdTeacherId.do">
							<i
								class="ace-icon glyphicon  glyphicon-edit  align-top bigger-125 icon-on-right"></i>
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
		src="${pageContext.request.contextPath}/content/js/service/teacher/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/view.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	
	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
}
</script>

<div id="dialog-message" class="hide">
		
		<div id="uploader">
    <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
</div>
	
 <div style="margin:5px">

		           1、必须提供一卡通编号、姓名、职工类别、性别;<br>
		           2、填写请<a href="teacher.xls" style="color:red">下载模板</a>.<br>
		      
		
 </div>



		</div>
<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/upload.js"></script>
		<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
			<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

</body>
</html>