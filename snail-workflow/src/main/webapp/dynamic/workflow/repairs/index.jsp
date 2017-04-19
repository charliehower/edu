<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.beans.*"%>
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
	request.setAttribute("repairsId", new Date().getTime());//临时后面会替换为流程编号
	request.setAttribute("now", SnailUtils.parseDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
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
				<h5 class="widget-title smaller">报修申请</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form id="fm-workflow">
					<input name="repairsId" type="hidden" value="${repairsId}">
					<input name="stauts" type="hidden" value="1">
					<input name="departmentId" type="hidden" value="${sessionScope.SystemUser.department.departmentId}">
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
									name="teacherId" readOnly="true" value="${o.assigneeId}"
									data-options="required:true,validType:'length[1,50]'">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">申请人姓名</div>

							<div class="profile-info-value">

								<input class="easyui-validatebox textbox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="name" readOnly="true" value="${o.assignee}"
									data-options="required:true,validType:'length[2,50]'">
							</div>

						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">报修时间</div>

							<div class="profile-info-value">
								<input class="easyui-datetimebox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="repairsTime" id="repairsTime" data-options="required:false">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">报修地址</div>

							<div class="profile-info-value">
								<input id="cc1" name="locationId" class="easyui-combotree"
									data-options="url:'${sessionScope.portalContextPath}/location/selectLocationTreeList.do?pid=0&load=all',onlyLeafCheck:true,method:'get',animate: true,
                lines:false,"
									style='width: 400px; line-height: 30px; height: 30px;'>

							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">联系电话</div>

							<div class="profile-info-value">

								<input class="easyui-validatebox textbox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="tel" value="${sessionScope.SystemUser.users.mobile}"
									data-options="required:true,validType:'length[2,50]'">
							</div>

						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">报修类型</div>

							<div class="profile-info-value">

								<input class="easyui-combobox"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="repairsCategory"
									data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=24',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto',
                    onSelect: function(rec){
            var categoryId='35';
            if(rec.code=='2'){
            		categoryId='26';
            }        
            var url = '${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId='+categoryId;
            $('#subCategory').combobox('reload', url);
        }"
            ">
							</div>

						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">分项目</div>

							<div class="profile-info-value">

								<input class="easyui-combobox" id="subCategory"
									style="width: 200px; height: 25px; line-height: 25px;"
									name="subCategory"
									data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=35',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
							</div>

						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">是否短信提醒</div>

							<div class="profile-info-value">
								<select name="isSmsAlerts" class="easyui-combox">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">具体情况</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox"
									style="width: 600px; height: 100px" name="describtion"
									maxlength="600" data-options="multiline:true,required:false">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">备注</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox"
									style="width: 600px; height: 50px" name="remark"
									maxlength="600" data-options="multiline:true,required:false">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">代理申请人</div>

							<div class="profile-info-value">${o.proxy_assignee}</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name"></div>

							<div class="profile-info-value" style="width: 600px">
								<button class="btn btn-info" id="btn-workflow-save"
									authority="false">
									<i class="ace-icon fa fa-check bigger-110"></i> 提交
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
	var formatterDate = function(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
		};
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
			$('#cc1').combotree({
				onSelect: function(node){
					//alert(node.text);  // alert node text property when clicked
					if(!node.leaf){
						$('#cc1').combotree('clear');
						var tree=$('#cc1').combotree('tree');
						$(tree).tree('expand',node.target);
						return;
						//alert("必需选择到最详细的地点！");
					}
				}
			});
			$('#btn-workflow-save').on('click', function() {
				saveWorkflow();
			});
			$('#btn-workflow-destory').on('click', function() {
				destoryWorkflow();
			});
			$('#repairsTime').datetimebox('setValue', '${now}');
			
			
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
									if (params.locationId == '') {
										alert('报修地址不能为空！');
										return;
									}
									if (params.repairsTime == '') {
										alert('报修时间不能为空！');
										return;
									}
									if (params.describtion == '') {
										alert('具体情况不能为空！');
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
				url : "/edu/positive/isExitByTeacherId.do",
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
					} else {
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
																		parent.removePanel();
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