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
<link rel="stylesheet" href="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<%
String teacherId=request.getParameter("teacherId");
if(SnailUtils.isBlankString(teacherId)){
	Object obj = session.getAttribute(CommonKeys.SystemUser);
	SystemUser systemUser = (SystemUser) obj;
	teacherId=systemUser.getUsers().getUserId();
}
request.setAttribute("teacherId",teacherId);
request.setAttribute("display","none");
if(teacherId.startsWith("T")||teacherId.startsWith("t")){
	request.setAttribute("display","display");
}
%>
<script type="text/javascript">
var teacherId='${teacherId}';
</script>
<body>
	<div class="page-content">
		<!-- #section:elements.tab.option -->
		<div class="tabbable">
			<ul class="nav nav-tabs padding-16" id="myTab4">
				<li class="active"><a data-toggle="tab" href="#teacher"> <i
						class="green ace-icon glyphicon glyphicon-user bigger-125"></i> 基本信息

				</a></li>
<li><a data-toggle="tab" id="tab-group"  href="#Group">
				<i
						class="green ace-icon fa fa-users bigger-125"></i>
				分组信息</a></li>
				<li><a data-toggle="tab" id="tab-workExperience" href="#WorkExperience">
				<i
						class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
				工作经历</a></li>

				<li><a data-toggle="tab" id="tab-learningExperience" href="#LearningExperience"><i
						class="green ace-icon fa fa-pencil-square-o bigger-125"></i>学习经历</a></li>
				<li><a data-toggle="tab" id="tab-workPerformance" href="#WorkPerformance"><i
						class="green ace-icon glyphicon glyphicon-th-list bigger-125"></i>工作表现</a></li>
				<li><a data-toggle="tab" id="tab-personalFiles" href="#PersonalFiles"><i
						class="green ace-icon fa fa-folder-open bigger-125"></i>个人文件档案</a></li>
			</ul>

			<div class="tab-content">
				<div id="teacher" class="tab-pane in active">
				<form id="fm-teacher">
				<div class="profile-user-info profile-user-info-striped profile-bg"  style="width:800px">

<div class="profile-group-title">基本信息</div>
							<div class="profile-info-row">
								<div class="profile-info-name">教职工编号</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="teacherId" readOnly="true"
										data-options="required:true,validType:'length[1,50]'">
								</div>

								<div class="profile-info-name">职工类型</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="category"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=07',
                    method:'get',
                    readonly:false,
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">姓名</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="name"
										data-options="required:true,validType:'length[2,50]'">
								</div>


								<div class="profile-info-name">性别</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="sex"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=01',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">身份证号</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="idCard"
										data-options="required:false,validType:'length[18,18]'">
								</div>

								<div class="profile-info-name">手机号</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="tel" maxlength="11" data-options="required:false">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">Email</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="email" maxlength="50" data-options="required:false">
								</div>

								<div class="profile-info-name">照片</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="photo" maxlength="30" data-options="required:false">
									<a id="btn-upload-add"
										class='ace-icon glyphicon glyphicon-upload bigger-110'
										href="javascript:false">上传</a> <a id="btn-upload-view"
										class='ace-icon fa fa-eye bigger-110' href="javascript:false">浏览</a>
								</div>
							</div>
							<div class="profile-group-title">资历信息</div>
							<div class="profile-info-row">
								<div class="profile-info-name">政治面貌</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="poaf"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=08',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'150'
            ">
								</div>

								<div class="profile-info-name">学历</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="eb"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=10',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'150'
            ">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">专业</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="major" maxlength="30" data-options="required:false">
								</div>

								<div class="profile-info-name">毕业院校</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="gi" maxlength="30" data-options="required:false">
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">毕业时间</div>

								<div class="profile-info-value">
									<input class="easyui-datebox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="gt" data-options="required:false">
								</div>

								<div class="profile-info-name">从教开始年份</div>

								<div class="profile-info-value">
									<input class="easyui-numberbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="ftYear" maxlength="4" data-options="required:false">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">参加工作年数</div>

								<div class="profile-info-value">
									<input class="easyui-numberbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="wYears" maxlength="2" data-options="required:false">
								</div>

								<div class="profile-info-name">入职时间</div>

								<div class="profile-info-value">
									<input class="easyui-datebox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="entryTime" data-options="required:false">
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">教师资格证类别</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="tqcCategory"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=11',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'150'
            ">
								</div>

								<div class="profile-info-name">教师资格证号</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="tqcNo" maxlength="50" data-options="required:false">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">社会属性</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="soattr" id="soattr"
										data-options="
									multiple:true,
                    multiline:true,
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=12',
                    method:'get',
                    valueField:'code',
                    textField:'name'
                    
            ">
								</div>

								<div class="profile-info-name">专业技术职称</div>

								<div class="profile-info-value">
									<input class="easyui-combobox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="ppt"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=13',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
								</div>
							</div>
							<div class="profile-group-title">单位工作信息</div>
							<div class="profile-info-row">
								<div class="profile-info-name">职务</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="position" maxlength="30" data-options="required:false">
								</div>

								<div class="profile-info-name">所属学科</div>

                                		<div class="profile-info-value">
                                			<input class="easyui-combobox"
                                				style="width: 200px; height: 20px; line-height: 20px;"
                                				name="disciplineId"
                                				data-options="
                                                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=16',
                                                    method:'get',
                                                    valueField:'code',
                                                    textField:'name'
                                            ">
                                		</div>
							</div>

							<div class="profile-info-row">
							<div class="profile-info-name">工资类别</div>

                            		<div class="profile-info-value">
<input class="easyui-combobox"
                                				style="width: 200px; height: 20px; line-height: 20px;"
                                				name="salaryType"
                                				data-options="
                                                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=58',
                                                    method:'get',
                                                    valueField:'code',
                                                    textField:'name'
                                            ">
                            			

                            		</div>


								<div class="profile-info-name">籍贯</div>

								<div class="profile-info-value">
									<input class="easyui-combotree"
										style="width: 200px;" name="domicile"
										data-options="url:'${sessionScope.portalContextPath}/system/selectProvinceTreeList.do',required:false">
								</div>
							</div>




							<div class="profile-info-row">
							<div class="profile-info-name">家庭住址</div>

                            								<div class="profile-info-value">
                            									<input class="easyui-validatebox textbox"
                            										style="width: 300px; height: 50px" name="address"
                            										maxlength="50" data-options="multiline:true,required:false">
                            								</div>
								<div class="profile-info-name">简历</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox"
										style="width: 300px; height: 50px" name="resume"
										data-options="multiline:true,required:false">
								</div>


	</div>

</div>

 



</div>

<div style="text-align:center">



                <button class="btn btn-info" id="btn-teacher" authority="false">
                										<i class="ace-icon fa fa-check bigger-110"></i> 保存
                									</button>

                									&nbsp; &nbsp;
                									<button id="btn-teacher-reset" type="button" class="btn"
                										authority="${pageContext.request.contextPath}/teacher/insertTeacher.do">
                										<i class="ace-icon fa fa-undo bigger-110"></i> 重置
                									</button>

                </form>
				</div>
				<div id="Group" class="tab-pane">
					<jsp:include page="group.jsp" />	
				</div>
				<div id="WorkExperience" class="tab-pane">
					<jsp:include page="workExperience.jsp" />	
				</div>

				<div id="LearningExperience" class="tab-pane">
					<jsp:include page="LearningExperience.jsp" />	
				</div>
				<div id="WorkPerformance" class="tab-pane">
					<jsp:include page="workPerformance.jsp" />	
				</div>
				<div id="PersonalFiles" class="tab-pane">
					<jsp:include page="personalFiles.jsp" />
				</div>
			</div>
		</div>

		<!-- /section:elements.tab.option -->

<div id="dialog-message" class="hide">
		
		<div id="uploader">
    <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
</div>

	</div>
	
	<div id="dialog-message-photo" class="hide">
		
		<div id="load" class="loading"></div>


	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
			<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
	
<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/controller-teacher.js"></script>
		<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/controller-workExperience.js"></script>
		<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/controller-learningExperience.js"></script>
		<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/controller-workPerformance.js"></script>
		<script
		src="${pageContext.request.contextPath}/content/js/service/teacher/controller-personalFiles.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />


</body>
</html>