<%@page import="org.platform.snail.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.jbpm.api.*"%>
<%@ page import="org.jbpm.api.task.*"%>
<%@ page import="org.jbpm.api.history.*"%>
<%@ page import="java.util.*"%>
<%@page import="org.platform.snail.workflow.service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);
	WorkflowService workflowService = (WorkflowService) webApplicationContext
			.getBean("workflowService");
	String taskId = request.getParameter("taskId");
	Map<String,?> o=workflowService.getVariablesByTaskId(taskId);
	request.setAttribute("o", o);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>entry</title>
</head>
<jsp:include page="../../common/common.jsp" />
<body>
	<div class="page-content">

		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">转正申请</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form id="fm-workflow">
						<div class="profile-info-row">
							<div class="profile-info-name">任务编号</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="taskId" readOnly="true" value="${param.taskId}"
									data-options="required:true,validType:'length[1,50]'">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">申请人编号</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="teacherId" readOnly="true"
									value="${o.assigneeId}"
									data-options="required:true,validType:'length[1,50]'">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">申请人姓名</div>

							<div class="profile-info-value">
							
								<input class="easyui-validatebox textbox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="name" readOnly="true"
									value="${o.assignee}"
									data-options="required:true,validType:'length[2,50]'">
							</div>

						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">离职时间</div>

							<div class="profile-info-value">
								<input class="easyui-datebox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="quitTime" data-options="required:false">
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">离职类别</div>
<div class="profile-info-value">
							<input class="easyui-combobox"
								style="width: 200px; height: 25px; line-height: 25px;"
								name="quitCategory"
								data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=17',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
						</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">离职类别</div>

					<div class="profile-info-value">
						<input class="easyui-combobox"
							style="width: 200px; height: 25px; line-height: 25px;"
							name="reasion"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=18',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
					</div>
				</div>
				<div class="profile-info-row">
							<div class="profile-info-name">离职总结</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox"
									style="width: 600px; height: 150px" name="evaluation"
									maxlength="600" data-options="multiline:true,required:false">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">代理申请人</div>

							<div class="profile-info-value">
							
								${o.proxy_assignee}
							</div>
						</div>
				<div class="profile-info-row">
					<div class="profile-info-name"></div>

					<div class="profile-info-value" style="width: 600px">
						<button class="btn btn-info" id="btn-workflow-save"
							authority="false">
							<i class="ace-icon fa fa-check bigger-110"></i> 保存
						</button>

						&nbsp; &nbsp;
						<button id="btn-workflow-reset" type="button" class="btn"
							authority="false">
							<i class="ace-icon fa fa-undo bigger-110"></i> 重置
						</button>
						&nbsp; &nbsp;

						<button class="btn btn-warning" id="btn-workflow-destory"
							authority="false">
							<i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
							撤销
						</button>
					</div>
				</div>


				</form>

			</div>
		</div>
	</div>
	</div>
	<div id="dialog-confirm" class="hide">
		<div class="alert alert-info bigger-110">点击确定后，系统将提交电子工作流程.</div>

		<div class="space-6"></div>

		<p class="bigger-110 bolder center grey">
			<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> 您确定吗?
		</p>
	</div>

	<div id="dialog-confirm-destory" class="hide">
		<div class="alert alert-info bigger-110">点击确定后，系统将撤销电子流程.</div>

		<div class="space-6"></div>

		<p class="bigger-110 bolder center grey">
			<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> 您确定吗?
		</p>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		var taskId = '${param.taskId}';
		jQuery(function($) {
			$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
				_title : function(title) {
					var $title = this.options.title || '&nbsp;'
					if (("title_html" in this.options)
							&& this.options.title_html == true)
						title.html($title);
					else
						title.text($title);
				}
			}));
			$('#btn-workflow-save').on('click', function() {
				saveWorkflow();
			});
			$('#btn-workflow-destory').on('click', function() {
				destoryWorkflow();
			});
			isExitByTeacherId();
		});
		function saveWorkflow() {
			$('#fm-workflow')
					.ajaxForm(
							{
								beforeSubmit : function(formData, jqForm,
										options) {
									var params = {};
									$.each(formData, function(n, obj) {
										params[obj.name] = obj.value;
									});
									$.extend(params, {
										time : new Date()
									});
									if (params.quitTime == '') {
										alert('离职时间不能为空！');
										return;
									}
									
									$("#dialog-confirm")
											.removeClass('hide')
											.dialog(
													{
														resizable : false,
														modal : false,
														title : "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认提交电子工作流程吗?</h4></div>",
														title_html : true,
														buttons : [
																{
																	html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交电子工作流程",
																	"class" : "btn btn-info btn-xs",
																	id : 'btn-workflow-save',
																	click : function() {

																		$
																				.ajax({
																					type : "post",
																					url : "/workflow/workflow/completeTask.do",
																					data : params,
																					beforeSend : function(
																							XMLHttpRequest) {
																						style_ajax_button(
																								'btn-workflow-save',
																								true);
																					},
																					success : function(
																							rst,
																							textStatus) {
																						parent
																								.initWorkflowList();
																						$(
																								"#dialog-confirm")
																								.dialog(
																										"close");
																						alert(rst.errorMessage);
																						parent.removePanel();
																					},
																					complete : function(
																							XMLHttpRequest,
																							textStatus) {
																						style_ajax_button(
																								'btn-workflow-save',
																								false);
																					},
																					error : function() {
																						style_ajax_button(
																								'btn-workflow-save',
																								false);
																					}
																				});
																	}
																},
																{
																	html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
																	"class" : "btn btn-xs",
																	click : function() {
																		$(this)
																				.dialog(
																						"close");
																	}
																} ]
													});
									return false;
								}
							});

		}
		function isExitByTeacherId() {
			$.ajax({
				type : "post",
				url : "/edu/quit/isExitByTeacherId.do",
				data : {
					time : new Date()
				},
				beforeSend : function(XMLHttpRequest) {
					style_ajax_button('btn-workflow-save', true);
				},
				success : function(rst, textStatus) {
					if (!rst.state) {
						style_ajax_button('btn-workflow-save', true);
						alert(rst.errorMessage);

					}else{
						style_ajax_button('btn-workflow-save', false);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {

				},
				error : function() {

				}
			});
		}
		function destoryWorkflow() {
			$('#fm-workflow')
					.ajaxForm(
							{
								beforeSubmit : function(formData, jqForm,
										options) {
									$("#dialog-confirm-destory")
											.removeClass('hide')
											.dialog(
													{
														resizable : false,
														modal : false,
														title : "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认撤销交电子工作流程吗?</h4></div>",
														title_html : true,
														buttons : [
																{
																	html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 撤销电子工作流程",
																	"class" : "btn btn-info btn-xs",
																	id : 'btn-confirm-destory',
																	click : function() {
																		$
																				.ajax({
																					type : "post",
																					url : "/workflow/workflow/deleteProcessInstanceCascadeByTaskId.do",
																					data : {
																						taskId : taskId
																					},
																					beforeSend : function(
																							XMLHttpRequest) {
																						style_ajax_button(
																								'btn-confirm-destory',
																								true);
																					},
																					success : function(
																							rst,
																							textStatus) {
																						parent
																								.initWorkflowList();
																						$(
																								"#dialog-confirm-destory")
																								.dialog(
																										"close");
																						alert(rst.errorMessage);
																						parent.removePanel();
																					},
																					complete : function(
																							XMLHttpRequest,
																							textStatus) {
																						style_ajax_button(
																								'btn-confirm-destory',
																								false);
																					},
																					error : function() {
																						style_ajax_button(
																								'btn-confirm-destory',
																								false);
																					}
																				});
																	}
																},
																{
																	html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
																	"class" : "btn btn-xs",
																	click : function() {
																		$(this)
																				.dialog(
																						"close");
																	}
																} ]
													});

									return false;
								}
							});

		}
		function style_ajax_button(btnId, status) {
			console.log(status);
			var btn = $('#' + btnId);
			if (status) {
				btn.find('i').removeClass('fa-check');
				btn.find('i').addClass('fa-spinner fa-spin');
				btn.attr('disabled', "true");

			} else {
				btn.find('i').removeClass('fa-spinner');
				btn.find('i').removeClass('fa-spin');
				btn.find('i').addClass('fa-check');
				btn.removeAttr("disabled");
			}
		}
	</script>
</body>
</html>