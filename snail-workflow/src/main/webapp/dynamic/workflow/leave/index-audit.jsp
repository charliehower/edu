<%@page import="org.platform.snail.beans.*"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.jbpm.api.*"%>
<%@ page import="org.jbpm.api.task.*"%>
<%@ page import="org.jbpm.api.history.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.platform.snail.edu.dao.*"%>
<%@ page import="org.platform.snail.edu.vo.*"%>
<%@page import="org.platform.snail.workflow.service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	javax.servlet.ServletContext servletContext = request.getSession()
	.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
	.getRequiredWebApplicationContext(servletContext);
	WorkflowService workflowService = (WorkflowService) webApplicationContext
	.getBean("workflowService");
	LeaveMapper leaveMapper = (LeaveMapper) webApplicationContext
	.getBean("leaveMapper");
	String instanceId = request.getParameter("instanceId");
	request.setAttribute("o", workflowService
	.findHistoryTaskByProcessInstanceId(instanceId));
	//两者一致
	String  leaveId=instanceId;
	LeaveVo vo=leaveMapper.selectVoByPrimaryKey(leaveId);
	request.setAttribute("vo", vo);
	Map<String,?> p=workflowService.getVariablesByInstanceId(instanceId);
	request.setAttribute("p", p);
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

		<table style="width: 100%"
			class="table-striped table-bordered table-hover">
			<tr>
				<td colspan="12" align="center" style="font-size: 24px"><strong>请假申请单</strong></td>
			</tr>
			<tr>
				<td height="120" align="center" rowspan="4" colspan="2"
					style="font-size: 15px"><strong>请假人</strong></td>
				<td colspan="2" rowspan="4" align="center">${vo.leaveUserName}</td>
				<td colspan="2" align="center">任教班级</td>
				<td colspan="6" align="center">${sessionScope.SystemUser.other.discriblineClassName}</td>

			</tr>
			<tr>
				<th colspan="2">申请单号</th>
				<td colspan="2">${o.other.instanceId }</td>
				<th colspan="2">申请日期</th>
				<td colspan="2">${o.other.date }</td>
			</tr>
			<tr>
				<th colspan="2">部门负责人</th>
				<td colspan="2">${o.other.leaderFullName}</td>
				<th colspan="2">当前状态</th>
				<td colspan="2">${o.other.activeTaskName}</td>
			</tr>

			<tr>
				<th colspan="2">班主任所带班级</th>
				<td colspan="2">${sessionScope.SystemUser.other.headmasterClassName}</td>
				<th colspan="2">所属其他管理部门</th>
				<td colspan="2">${sessionScope.SystemUser.dpFullName}</td>
			</tr>
			<tr>
				<td rowspan="2" align="center" colspan="2" height="60"
					style="font-size: 15px"><strong>请假类型</strong></td>
				<td align="center" height="30">公假</td>
				<td align="center">产假</td>
				<td align="center">病假</td>
				<td align="center">事假</td>
				<td align="center">探亲假</td>
				<td align="center">婚假</td>
				<td align="center">丧假</td>
				<td align="center">看护假</td>
				<td align="center">哺乳假</td>
				<td align="center">其他</td>
			</tr>
			<tr id="category-view">
				<td height="30" align="center"><input type="radio"
					name="categoryId" value="1"
					<c:if test="${vo.categoryId=='1'}">checked="checked" </c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="2"
					<c:if test="${vo.categoryId=='2'}">checked="checked"</c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="3"
					<c:if test="${vo.categoryId=='3'}">checked="checked"</c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="4"
					<c:if test="${vo.categoryId=='4'}">checked="checked"</c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="5"
					<c:if test="${vo.categoryId=='5'}">checked="checked"</c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="6"
					<c:if test="${vo.categoryId=='6'}">checked="checked"</c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="7"
					<c:if test="${vo.categoryId=='7'}">checked="checked"</c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="8"
					<c:if test="${vo.categoryId=='8'}">checked="checked"</c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="9"
					<c:if test="${vo.categoryId=='9'}">checked="checked"</c:if> /></td>
				<td align="center"><input type="radio" name="categoryId"
					value="10"
					<c:if test="${vo.categoryId=='10'}">checked="checked"</c:if> /></td>
			</tr>
			<tr>
				<td align="center" colspan="2" height="30" style="font-size: 15px"><strong>请假时间</strong></td>
				<td colspan="10" style="font-size: 15px">开始时间：<fmt:formatDate
						value="${vo.timeStart}" type="both" /> 截止时间：<fmt:formatDate
						value="${vo.timeEnd}" type="both" /> 请假天数：<span id="leaveDates"></span>
				</td>
			</tr>
			<tr>
				<td align="center" rowspan="4" colspan="2" height="120"
					style="font-size: 15px"><strong>工作交接</strong></td>
				<td align="center" colspan="2" height="30">需调课人</td>
				<td align="center" colspan="3">${vo.fromUserName }</td>
				<td align="center" colspan="2">被调课人</td>
				<td align="center" colspan="3">${vo.toUserName }</td>
			</tr>
			<tr>
				<td align="center" colspan="2" height="30">调换班级、节次</td>
				<td align="center" colspan="3">${vo.fromClassLesson}</td>
				<td align="center" colspan="2">被调换班级、节次</td>
				<td align="center" colspan="3">${vo.toClassLesson}</td>
			</tr>
			<tr>
				<td align="center" colspan="2" height="30">班主任被交接人</td>
				<td align="center" colspan="3">${vo.toHmUserName}</td>
				<td align="center" colspan="2">级组长被交接人</td>
				<td align="center" colspan="3">${vo.toGlUserName}</td>
			</tr>
			<tr>
				<td align="center" colspan="2" height="30">部门工作被交接人</td>
				<td align="center" colspan="3">${vo.toDpUserName}</td>
				<td align="center" colspan="2">其他工作被交接人</td>
				<td align="center" colspan="3">${vo.toOtherUserName}</td>
			</tr>
			<tr>
				<td align="center" style="font-size: 15px"><strong>请假事由</strong></td>
				<td colspan="12">${vo.leaveReason}</td>
			</tr>
			<tr>
				<td align="center" style="font-size: 15px"><strong>附件</strong></td>
				<td colspan="12" id="filelist-history"></td>
			</tr>









		</table>
		
		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">${o.other.activeTaskName}</h5>

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
							<div class="profile-info-name">审批结果</div>

							<div class="profile-info-value">
								<select name="rs" class="easyui-combox">
									<option value="1">同意</option>
									<option value="0">驳回</option>
								</select>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">审批意见</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox"
									style="width: 600px; height: 150px" name="evaluation"
									maxlength="600" data-options="multiline:true,required:false">
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name"></div>

							<div class="profile-info-value" style="width: 600px">
								<button class="btn btn-info" id="btn-workflow-save"
									authority="false">
									<i class="ace-icon fa fa-check bigger-110"></i> 审批
								</button>

							</div>
						</div>


					</form>

				</div>
			</div>
		</div>
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
					<td align="left">${item.variables.taskName}<c:choose>
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
	function render(value) {
		var n=value/8;
		n=Math.round(n*100)/100;
		return n;
	}
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
			loadAttach("${param.instanceId}");
			$("#leaveDates").html(render(${vo.leaveDates}));
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
		function loadAttach(noticeId){
			$.ajax({
				type : "get",
				url : portalContextPath + "/attach/findAttachList.do",
				data:{noticeId:noticeId},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {
					if(rst&&rst.state){
						var html=[];
						$.each(rst.list, function(n, file) {
							
							html.push('<div id="' + file.fileUrl + '"><a href="'+portalContextPath +'/files/download.do?collectionName=notice&originalFilename='+file.fileName+'&fileName='+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.fileUrl+'\')"></a><b></b></div>');
						});
						//document.getElementById('filelist').innerHTML=html.join('');
						$('#filelist-history').html(html.join(''));
					}else{
						bootbox.dialog({
							title:'系统提示',
							message:rst.errorMessage,
							detail:rst.detail,
							buttons: 			
							{
								"success" :
								 {
									"label" : "<i class='ace-icon fa fa-check'></i>确定",
									"className" : "btn-sm btn-success",
									"callback": function() {
										//$( this ).dialog( "close" );
									}
								}
							}
						});
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					
				},
				error : function() {
				}
			});
		}
		
		
	</script>
</body>
</html>