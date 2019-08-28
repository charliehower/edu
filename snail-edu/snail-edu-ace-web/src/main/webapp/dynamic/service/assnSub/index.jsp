<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>assnSub</title>
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
						姓名： <input name="studentName" type="text"
							style="width: 80px;" />
							编号： <input name="studentId" type="text"
							style="width: 100px;" />
								班级：<input class="easyui-combobox"
					style="width: 200px; height: 25px; line-height: 25px;"
					name="classesId" value="${classesId }"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=15&gradeId=1,2,3&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'">
                    <INPUT type="radio" name="status" value=0 checked> 退回
　　					<INPUT type="radio" name="status" value=1 >通过
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/assnSub/findAssnSubList.do">
							 <i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						
						<button class="btn btn-info" id="btn-view-audit"
							authority="${pageContext.request.contextPath}/assnSub/updateAuditByPrimaryKey.do">
							 <i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>
						
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/assnSub/deleteAssnSubByAssnSubId.do">
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

		<form id="fm-reg">
		
			<div  id="student-target" >
				
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">
					审核结果：
				</div>
				<div class="profile-info-value" style="width:600px" id="auditRs">
				
					<INPUT type="radio" name="audit_radio" value=0> 退回
　　					<INPUT type="radio" name="audit_radio" value=1 checked>通过
				</div>
			</div>
		</form>

	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/assnSub/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/assnSub/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/assnSub/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/assnSub/view.js"></script>
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