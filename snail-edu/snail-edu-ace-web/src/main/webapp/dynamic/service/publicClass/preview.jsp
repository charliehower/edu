<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>基本信息</title>
</head>
<jsp:include page="../../common/common.jsp" />

<%
	String publicClassId = request.getParameter("publicClassId");
	if (SnailUtils.isBlankString(publicClassId)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
		publicClassId = "";
	}
	request.setAttribute("publicClassId", publicClassId);
%>
<script type="text/javascript">
	var publicClassId = '${publicClassId}';
</script>
<style>
.layout-user {
	width: 100px;
	height: 20px;
	float: left;
	margin: 1px 1px 1px;
}
</style>

<body>
	<div class="page-content">
		<!-- #section:elements.tab.option -->
		<div class="tabbable">
			<ul class="nav nav-tabs padding-16" id="myTab4">
				<li class="active"><a data-toggle="tab" href="#assn"> <i
						class="green ace-icon fa fa-users bigger-125"></i> 基本信息

				</a></li>

			</ul>

			<div class="tab-content">
				<div id="assn" class="tab-pane in active">

					<div style="width: 100%">
						<div class="profile-info-row">
							<div class="profile-info-name">公开课编号:</div>

							<div class="profile-info-value" id="publicClassId"
								style="width: 90%"></div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">共同体:</div>

							<div class="profile-info-value" id="globleTitle"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">学科:</div>

							<div class="profile-info-value" id="disciplineName"></div>

						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">学年:</div>

							<div class="profile-info-value" id="years"></div>
						</div>


						<div class="profile-info-row">
							<div class="profile-info-name">年级:</div>

							<div class="profile-info-value" id="grade"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">上课内容:</div>

							<div class="profile-info-value" id="title"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">课型:</div>

							<div class="profile-info-value" id="model"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">微课:</div>

							<div class="profile-info-value" id="content"></div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">备课:</div>

							<div class="profile-info-value" id="taskContent"></div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">报名开始时间:</div>

							<div class="profile-info-value" id="regStartDate"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">报名截止时间:</div>

							<div class="profile-info-value" id="regDeadline"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">上课时间:</div>

							<div class="profile-info-value" id="taskDate"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">课时:</div>

							<div class="profile-info-value" id="keName"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">地点:</div>

							<div class="profile-info-value" id="location"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">申请人:</div>

							<div class="profile-info-value" id="teacherName"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">听课:</div>

							<div class="profile-info-value" id="remark"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">状态:</div>

							<div class="profile-info-value" id="status"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">一级审核人:</div>

							<div class="profile-info-value" id="auditorName"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">一级审核意见:</div>

							<div class="profile-info-value" id="auditRemark"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">一级审核时间:</div>

							<div class="profile-info-value" id="auditTime"></div>
						</div>
						<!-- sec -->
						<div class="profile-info-row">
							<div class="profile-info-name">二级审核人:</div>

							<div class="profile-info-value" id="auditorNameSec"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">二级结果:</div>

							<div class="profile-info-value" id="auditSecStatus"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">二级审核意见:</div>

							<div class="profile-info-value" id="auditSecRemark"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">二级审核时间:</div>

							<div class="profile-info-value" id="auditSecTime"></div>
						</div>
						<!-- thi -->
						<div class="profile-info-row">
							<div class="profile-info-name">三级审核人:</div>

							<div class="profile-info-value" id="auditorNameThi"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">三级结果:</div>

							<div class="profile-info-value" id="auditThiStatus"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">三级审核意见:</div>

							<div class="profile-info-value" id="auditThiRemark"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">三级审核时间:</div>

							<div class="profile-info-value" id="auditThiTime"></div>
						</div>
						<!-- for -->
						<div class="profile-info-row">
							<div class="profile-info-name">四级审核人:</div>

							<div class="profile-info-value" id="auditorNameFor"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">四级结果:</div>

							<div class="profile-info-value" id="auditForStatus"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">四级审核意见:</div>

							<div class="profile-info-value" id="auditForRemark"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">四级审核时间:</div>

							<div class="profile-info-value" id="auditForTime"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">发布时间:</div>

							<div class="profile-info-value" id="pushTime"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">报名老师:</div>

							<div class="profile-info-value" id="task-content"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">评课:</div>

							<div class="profile-info-value" id="score_table">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center"><label class="position-relative">
													<input type="checkbox" class="ace" /> <span class="lbl"></span>
											</label></th>
											<th>序号</th>
											<th>评分人</th>
											<th>分数</th>
											<th>评课内容</th>
											<th>评课时间</th>
										</tr>
									</thead>
									<tbody id="tb-list">



									</tbody>
								</table>

							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name"></div>
							<div class="profile-info-value">
								<button class="btn btn-info" id="btn-view-reg" authority="false">
									<i class="ace-icon fa fa-check bigger-110"></i> 报名参加
								</button>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<div id="dialog-message" class="hide">

		<form id="fm-reg">

			<div class="profile-info-row">
				<div class="profile-info-name">报名人：</div>
				<div class="profile-info-value">${SystemUser.users.name}</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">报名说明：</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox"
						style="width: 550px; height: 200px" name="remark"
						data-options="multiline:true,required:false" />
				</div>
			</div>
		</form>

	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/publicClass/preview.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
</body>
</html>