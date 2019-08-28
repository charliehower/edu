<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.edu.vo.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>
<%@page import="org.platform.snail.edu.model.*"%>
<%@page import="java.util.*"%>
<%
	String assnId = request.getParameter("assnId");
	if (SnailUtils.isBlankString(assnId)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
		assnId = "";
	}
	request.setAttribute("assnId", assnId);

	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);

	AssnMapper AssnMapper = (AssnMapper) webApplicationContext
			.getBean("assnMapper");

	Assn o = AssnMapper.selectByPrimaryKey(assnId);
	request.setAttribute("o", o);
%>
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


<script type="text/javascript">
	var assnId = '${assnId}';
</script>
<style>
.layout-user {
	width: 60px;
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
					<form id="fm-assn">
						<div style="width: 100%">
							<div class="profile-info-row">
								<div class="profile-info-name">社团编号</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 400px; height: 25px; line-height: 25px;"
										name="assnId" readOnly="true"
										data-options="required:false,validType:'length[1,50]'">
									系统自动产生
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">类别</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 400px; height: 25px; line-height: 25px;"
										name="categoryId"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=40',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									名称<span style="color: red">*</span>
								</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 400px; height: 25px; line-height: 25px;"
										name="assnName"
										data-options="required:true,validType:'length[2,50]'" />

								</div>

							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">
									学年<span style="color: red">*</span>
								</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 400px; height: 25px; line-height: 25px;"
										name="years"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=19',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'" />

								</div>
							</div>


							<div class="profile-info-row">
								<div class="profile-info-name">简介</div>

								<div class="profile-info-value">
									<textarea class="easyui-validatebox textbox"
										style="width: 100%; height: 200px" name="discri"
										data-options="multiline:true,required:false">
										${o.discri}
										</textarea>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									指导老师<span style="color: red">*</span>
								</div>

								<div class="profile-info-value">
									<select class="easyui-combogrid"
										style="width: 400px; height: 25px; line-height: 25px;"
										name="adviser" 
										data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do?id=${o.adviser}',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									社团人数<span style="color: red">*</span>
								</div>

								<div class="profile-info-value">
									<input class="easyui-numberbox"
										style="width: 400px; height: 25px; line-height: 25px;"
										name="limitMax" maxlength="4" data-options="required:true" />

								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">社团成员：</div>

								<div class="profile-info-value">
									<div id="task-content" class="easyui-panel"
										style="padding: 5px; width: 600px; height: 100px"></div>


								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name"></div>

								<div class="profile-info-value">
									<button class="btn btn-purple" id="btn-view-select"
										authority="false">
										添加<i
											class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
									</button>
									<button class="btn btn-purple" id="btn-view-remove"
										authority="false">
										全部清除<i
											class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
									</button>
									<button class="btn btn-purple" id="btn-view-remove-last"
										authority="false">
										删除最后<i
											class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
									</button>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">社长：</div>

								<div class="profile-info-value">
									<div id="task-content-two" class="easyui-panel"
										style="padding: 5px; width: 600px; height: 50px"></div>


								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name"></div>

								<div class="profile-info-value">
									<button class="btn btn-purple" id="btn-view-select-two"
										authority="false">
										添加<i
											class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
									</button>
									<button class="btn btn-purple" id="btn-view-remove-two"
										authority="false">
										全部清除<i
											class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
									</button>
									<button class="btn btn-purple" id="btn-view-remove-last-two"
										authority="false">
										删除最后<i
											class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
									</button>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									报名开始时间<span style="color: red">*</span>
								</div>

								<div class="profile-info-value">
									<input class="easyui-datebox"
										style="width: 400px; height: 25px; line-height: 25px;"
										name="regStartDate" data-options="required:true" />

								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									报名截止时间<span style="color: red">*</span>
								</div>

								<div class="profile-info-value">
									<input class="easyui-datebox"
										style="width: 400px; height: 25px; line-height: 25px;"
										name="regDeadline" data-options="required:true" />

								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name"></div>

								<div class="profile-info-value">
									<button class="btn btn-info" id="btn-assn" authority="false">
										<i class="ace-icon fa fa-check bigger-110"></i> 保存
									</button>

									&nbsp; &nbsp;
									<button id="btn-assn-reset" type="button" class="btn"
										authority="false">
										<i class="ace-icon fa fa-undo bigger-110"></i> 重置
									</button>
								</div>
							</div>




						</div>
					</form>
				</div>

			</div>
		</div>

	</div>

	<div id="dialog-message" class="hide">

		<select class="easyui-combogrid"
			style="width: 560px; height: 25px; line-height: 25px;"
			id="combogrid-tmp"
			data-options="panelWidth: 530,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectStudent.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:100},
			{field:'MOBILE',title:'手机号',width:150,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:250,align:'right'}
			 ]]"></select>

		<div style="height: 5px"></div>
		<div>
			<button class="btn btn-purple" id="btn-view-select-tmp"
				authority="false">
				添加<i
					class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
			</button>
			<button class="btn btn-purple" id="btn-view-remove-tmp"
				authority="false">
				全部清除<i
					class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
			</button>
			<button class="btn btn-purple" id="btn-view-remove-tmp-last"
				authority="false">
				删除最后<i
					class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
			</button>
		</div>
		<div style="height: 5px"></div>
		<div id="task-content-tmp" class="easyui-panel"
			style="padding: 5px; width: 560px; height: 200px"></div>

	</div>



	<div id="dialog-message-two" class="hide">

		<select class="easyui-combogrid"
			style="width: 560px; height: 25px; line-height: 25px;"
			id="combogrid-tmp-two"
			data-options="panelWidth: 530,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectStudent.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:100},
			{field:'MOBILE',title:'手机号',width:150,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:250,align:'right'}
			 ]]"></select>

		<div style="height: 5px"></div>
		<div>
			<button class="btn btn-purple" id="btn-view-select-tmp-two"
				authority="false">
				添加<i
					class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
			</button>
			<button class="btn btn-purple" id="btn-view-remove-tmp-two"
				authority="false">
				全部清除<i
					class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
			</button>
			<button class="btn btn-purple" id="btn-view-remove-tmp-last-two"
				authority="false">
				删除最后<i
					class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
			</button>
		</div>
		<div style="height: 5px"></div>
		<div id="task-content-tmp-two" class="easyui-panel"
			style="padding: 5px; width: 560px; height: 200px"></div>

	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/assn/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/assn/controller-assn.js"></script>

	<script
		src="${pageContext.request.contextPath}/content/js/service/assn/view-multiple-select.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/assn/view-multiple-select-two.js"></script>

	<jsp:include page="../../common/footer-2.jsp" />
	<ckeditor:replaceAll basePath="/edu/ckeditor/" />
</body>
</html>