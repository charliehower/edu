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
						<input type="hidden" name="status" value="03" />
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/publicClass/findPublicClassList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						

						<button class="btn btn-info" id="btn-view-reg"
							authority="${pageContext.request.contextPath}/publicClass/updateScore.do">
							<i
								class="ace-icon glyphicon  glyphicon-check  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-detail"
							authority="false">
							报名 <i
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
		src="${pageContext.request.contextPath}/content/js/service/publicClass/rs/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/publicClass/rs/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/publicClass/rs/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/publicClass/rs/view.js"></script>
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

		<form id="fm-reg">

			<div class="profile-info-row">
				<div class="profile-info-name">评分人：</div>
				<div class="profile-info-value">${SystemUser.users.name}</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">评分：</div>
				<div class="profile-info-value" >
					<input type="text" name="score" id="score" value="100"/>(满分：100分，最低分0分,不计入小数)

				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">内容：</div>
				<div class="profile-info-value">
					<textarea class="form-control limited"
						style="width: 550px; height: 200px" name="remark" id="remark" maxlength="500"></textarea>

				</div>
			</div>
		</form>


	</div>



</body>
</html>