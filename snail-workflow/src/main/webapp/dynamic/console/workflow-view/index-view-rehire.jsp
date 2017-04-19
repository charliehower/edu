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
	String instanceId = request.getParameter("instanceId");
	request.setAttribute("o", workflowService
			.findHistoryTaskByProcessInstanceId(instanceId));
	Map<String,?> p=workflowService.getVariablesByInstanceId(instanceId);
	request.setAttribute("p", p);
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

		<table style="width: 100%"
			class="table table-striped table-bordered table-hover">
			<tr>
				<td align="center" colspan="4"><strong>${o.other.name }</strong>

				</td>
			</tr>
			<tr>
				<th>申请单号：</th>
				<td align="left">${o.other.instanceId }</td>
				<th>申请日期：</th>
				<td align="left">${o.other.date }</td>
			</tr>
			<tr>
				<th>申 请 人：</th>
				<td align="left">${o.other.assignee }</td>
				<th>当前状态：</th>
				<td align="left">${o.other.activeTaskName}</td>
			</tr>
			<tr>
				<th>所属部门：</th>
				<td align="left" colspan="3">${o.other.dpFullName}</td>
			</tr>

			<tr>
				<th>部门负责人：</th>
				<td align="left" colspan="3">${o.other.leaderFullName}</td>
			</tr>
			<tr>
				<th>代理申请人：</th>
				<td align="left">${p.proxy_assignee}</td>
				<th>所属部门：</th>
				<td align="left">${p.proxy_dpFullName}</td>
			</tr>
			<tr>
				<th>返聘时间：</th>
				<td align="left" colspan="3">${o.response.rehireTime}</td>
			</tr>
			
			<tr>
				<th>返聘原因：</th>
				<td align="left" colspan="3">
				
					${o.response.reasion}
				</td>
			</tr>
			
		</table>
		
		<table style="width: 100%"
			class="table table-striped table-bordered table-hover">
			<tr>
				<td align="center" colspan="4"><strong>日志</strong></td>
			</tr>
			<tr>
				<td width="60px">序号</td>
				<td width="200px">日期</td>
				<td width="120px" align="left">操作人</td>
				<td align="left">结果</td>
			</tr>
			<c:forEach var="item" items="${o.list}" varStatus="status">
				<tr>
					<td align="left">${status.index+1}</td>
					<td align="left">${item.createTime}</td>
					<td align="left">${item.variables.assignee}</td>
					<td align="left">${item.variables.taskName} <c:choose>
							<c:when test="${item.variables.rs=='1'}">  
    【通过】  
    </c:when>
    		<c:when test="${item.variables.rs=='0'}">  
    【驳回】 
    </c:when>

							<c:otherwise>  
    
    </c:otherwise>
						</c:choose> ${item.variables.evaluation}

					</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<div id="dialog-confirm" class="hide">
		<div class="alert alert-info bigger-110">点击确定后，系统将提交电子工作流程.</div>

		<div class="space-6"></div>

		<p class="bigger-110 bolder center grey">
			<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> 您确定吗?
		</p>
	</div>


	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
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
									if (params.rs == '') {
										alert('审批结果不能为空！');
										return;
									}
									if (params.evaluation == '') {
										alert('审批意见不能为空！');
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
	</script>
</body>
</html>

