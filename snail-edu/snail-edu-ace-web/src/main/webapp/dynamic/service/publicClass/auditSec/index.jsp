<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>publicClass</title>
</head>
<jsp:include page="../../../common/common.jsp" />
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
						编号： <input name="publicClassId" type="text" style="width: 100px;" />
						上课内容： <input name="title" type="text" style="width: 200px;" />
						学年：<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="years"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=19&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
						年级：<input class="easyui-combobox"
							style="height: 25px; line-height: 25px;" name="gradeId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=14&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
           
						状态：<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="auditSecStatus"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=43&defaultValue=0',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/publicClass/findPublicClassList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-audit"
							authority="${pageContext.request.contextPath}/publicClass/updateAuditSec.do">
							<i
								class="ace-icon glyphicon  glyphicon-check  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="false">
							编辑<i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-detail"
							authority="false">
							详细 <i
								class="ace-icon glyphicon glyphicon-list  align-top bigger-125 icon-on-right"></i>
						</button>


					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>



	</div>
	<jsp:include page="../../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/publicClass/auditSec/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/publicClass/auditSec/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/publicClass/auditSec/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/publicClass/auditSec/view.js"></script>
	<jsp:include page="../../../common/footer-2.jsp" />

	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight - layoutTopHeight);
		}
	</script>

	<div id="dialog-message" class="hide">
		<div class="profile-info-row">审核结果：</div>
		<div class="profile-info-row">
			<div class="radio">
				<label> <input name="status" type="radio" class="ace"
					checked="true" value="1" /> <span class="lbl">通过</span>
				</label> <label> <input name="status" type="radio" class="ace"
					value="2" /> <span class="lbl">退回</span>
				</label>
			</div>
		</div>
		<div class="profile-info-row">审核意见：</div>
		<div class="profile-info-row">
			<input class="easyui-validatebox textbox"
				style="width: 575px; height: 200px" name="remark"
				data-options="multiline:true,required:false" />

		</div>

	</div>



</body>
</html>