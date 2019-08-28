<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
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
	String assnId = request.getParameter("assnId");
	if (SnailUtils.isBlankString(assnId)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
		assnId = "";
	}
	request.setAttribute("assnId", assnId);
%>
<script type="text/javascript">
	var assnId = '${assnId}';
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
							<div class="profile-info-name">社团编号:</div>

							<div class="profile-info-value" id="assnId" style="width: 90%">

							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">类别:</div>

							<div class="profile-info-value" id="categoryName"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">名称:</div>

							<div class="profile-info-value" id="assnName"></div>

						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">学年:</div>

							<div class="profile-info-value" id="years"></div>
						</div>


						<div class="profile-info-row">
							<div class="profile-info-name">简介:</div>

							<div class="profile-info-value" id="discri"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">指导老师:</div>

							<div class="profile-info-value" id="adviserName"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">社团人数:</div>

							<div class="profile-info-value" id="limitMax"></div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">社团成员:</div>

							<div class="profile-info-value" id="task-content"></div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">社长:</div>

							<div class="profile-info-value" id="task-content-two"></div>
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
				<div class="profile-info-name">
					社团：
				</div>
				<div class="profile-info-value" id="reg-target">
					
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">
					申请人：
				</div>
				<div class="profile-info-value">
					${SystemUser.users.name}
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">
					申请说明：
				</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox"
						style="width:550px; height: 200px" name="discri"
										data-options="multiline:true,required:false" />
				</div>
			</div>
		</form>

	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/assn/preview.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
</body>
</html>