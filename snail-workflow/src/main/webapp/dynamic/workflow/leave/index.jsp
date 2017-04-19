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
	request.setAttribute("now",
	SnailUtils.parseDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
	request.setAttribute("timeStart",
	SnailUtils.parseDate(new Date())+" 08:00:00");
	request.setAttribute("timeEnd",
	SnailUtils.parseDate(new Date())+" 17:00:00");
	request.setAttribute("p",workflowService.getTask(taskId));
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
<link rel="stylesheet"
	href="${portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />s
<body>
	<div class="page-content">

		<form id="fm-workflow">
			<input name="status" type="hidden" value="0"> <input
				name="leaveUserId" type="hidden"
				value="${sessionScope.SystemUser.users.userId}"> <input
				name="applicationTime" type="hidden" value="${now}"> <input
				name="taskId" type="hidden" value="${param.taskId}">

			<table style="width: 100%"
				class="table-striped table-bordered table-hover">
				<tr>
					<td colspan="12" height="40" align="center" style="font-size: 24px"><strong>请假申请单</strong></td>
				</tr>
				<tr>
					<td height="80" align="center" rowspan="2" colspan="2"
						style="font-size: 15px"><strong>请假人</strong></td>
					<td colspan="2" rowspan="2" align="center">${sessionScope.SystemUser.users.name}</td>
					<td colspan="2" align="center">任教班级</td>
					<td colspan="6" align="center">${sessionScope.SystemUser.other.discriblineClassName}</td>

				</tr>
				<tr>
					<td colspan="2" align="center">班主任所带班级</td>
					<td colspan="2" align="center">${sessionScope.SystemUser.other.headmasterClassName}</td>
					<td colspan="2" align="center">所属其他管理部门</td>
					<td colspan="2" align="center">${sessionScope.SystemUser.dpFullName}</td>
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
				<tr>
					<td height="30" align="center"><input type="radio"
						name="categoryId" value="1" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="2" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="3" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="4" checked="checked" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="5" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="6" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="7" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="8" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="9" /></td>
					<td align="center"><input type="radio" name="categoryId"
						value="10" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2" height="30" style="font-size: 15px"><strong>请假时间</strong></td>
					<td colspan="10" style="font-size: 15px">开始时间<input
						class="easyui-datetimebox"
						style="width: 200px; height: 25px; line-height: 25px;"
						name="timeStart" id="timeStart" data-options="required:false">
						截止时间<input class="easyui-datetimebox"
						style="width: 200px; height: 25px; line-height: 25px;"
						name="timeEnd" id="timeEnd" data-options="required:false">
						<input name="qjDates" type="hidden">
						<span id="timeCount"></span><a href="javascript:getDates()">计算我的请假时间</a></td>
				</tr>
				<tr>
					<td align="center" rowspan="4" colspan="2" height="120"
						style="font-size: 15px"><strong>工作交接</strong></td>
					<td align="center" colspan="2" height="30">需调课人</td>
					<td align="center" colspan="3"><select
						class="easyui-combogrid"
						style="width: 200px; height: 25px; line-height: 25px;"
						name="fromUserId"
						data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select></td>
					<td align="center" colspan="2">被调课人</td>
					<td align="center" colspan="3"><select
						class="easyui-combogrid"
						style="width: 200px; height: 25px; line-height: 25px;"
						name="toUserId"
						data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select></td>
				</tr>
				<tr>
					<td align="center" colspan="2" height="30">调换班级、节次</td>
					<td align="center" colspan="3"><input name="fromClassLesson"
						type="text" style="width: 200px;" /></td>
					<td align="center" colspan="2">被调换班级、节次</td>
					<td align="center" colspan="3"><input name="toClassLesson"
						type="text" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2" height="30">班主任被交接人</td>
					<td align="center" colspan="3"><select
						class="easyui-combogrid"
						style="width: 200px; height: 25px; line-height: 25px;"
						name="toHmUserId"
						data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select></td>
					<td align="center" colspan="2">级组长被交接人</td>
					<td align="center" colspan="3"><select
						class="easyui-combogrid"
						style="width: 200px; height: 25px; line-height: 25px;"
						name="toGlUserId"
						data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select></td>
				</tr>
				<tr>
					<td align="center" colspan="2" height="30">部门工作被交接人</td>
					<td align="center" colspan="3"><select
						class="easyui-combogrid"
						style="width: 200px; height: 25px; line-height: 25px;"
						name="toDpUserId"
						data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select></td>
					<td align="center" colspan="2">其他工作被交接人</td>
					<td align="center" colspan="3"><select
						class="easyui-combogrid"
						style="width: 200px; height: 25px; line-height: 25px;"
						name="toOtherUserId"
						data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select></td>
				</tr>
				<tr>
					<td height="200" align="center" style="font-size: 15px"><strong>请假事由</strong></td>
					<td height="34" colspan="12"><input
						class="easyui-validatebox textbox"
						style="width: 600px; height: 150px" name="leaveReason"
						maxlength="600" data-options="multiline:true,required:false"></td>
				</tr>
<tr>
					<td height="200" align="center" style="font-size: 15px"><strong>附件</strong></td>
					<td height="34" colspan="12">

<div>
<div id="filelist-history"></div>
				<div id="filelist">Your browser doesn't have Flash,
					Silverlight or HTML5 support.</div>

				<div id="container">
					附件：<a id="pickfiles" href="javascript:;">[添加附件]</a> <a
						id="uploadfiles" href="javascript:;">[上传]</a>
				</div>
				<br />
				<pre id="console"></pre>
			</div>
</td>
				</tr>
				





				<tr>
					<td align="center" colspan="12"><div>
							<button class="btn btn-info" id="btn-workflow-save"
								authority="false">
								<i class="ace-icon fa fa-check bigger-110"></i> 提交
							</button>


							<button id="btn-workflow-reset" type="button" class="btn"
								authority="false">
								<i class="ace-icon fa fa-undo bigger-110"></i> 重置
							</button>


							<button class="btn btn-warning" id="btn-workflow-destory"
								authority="false">
								<i
									class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
								撤销
							</button>

						</div></td>
				</tr>
			</table>



		</form>


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
<script type="text/javascript"
		src="${portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
	<script type="text/javascript"
		src="${portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript"
		src="${portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
	var noticeId="${p.response.executionId}";
		var formatterDate = function(date) {
			var day = date.getDate() > 9 ? date.getDate() : "0"
					+ date.getDate();
			var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
					+ (date.getMonth() + 1);
			return date.getFullYear() + '-' + month + '-' + day;
		};
		var taskId = '${param.taskId}';
		var date1;
		var date2;
		jQuery(function($) {
			$('#timeStart').datetimebox({
				onSelect : function(date) {
					date1 = date;
					getDates();//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				}
			});
			$('#timeEnd').datetimebox({
				onSelect : function(date) {
					date2 = date;
					getDates();//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				}
			});
			$("#timeStart").datetimebox('setValue', '${timeStart}');
			$("#timeEnd").datetimebox('setValue', '${timeEnd}');
			$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
				_title : function(title) {
					var $title = this.options.title || ''
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
			//$('#repairsTime').datetimebox('setValue', '${now}');
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

									if (params.timeStart == '') {
										alert('请假开始时间不能为空！');
										return;
									}
									if (params.timeEnd == '') {
										alert('请假截止时间不能为空！');
										return;
									}
									if (params.leaveReason == '') {
										alert('请假事由不能为空！');
										return;
									}
									date1 = StringToDate(params.timeStart);
									date2 = StringToDate(params.timeEnd);
									var dates = dateDiffNoWeekDay(date1, date2);
									
									//params.leaveDates = dates/8;
									params.leaveDates = parseInt(dates);
									console.log(params);
									//return;
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
																	html : "<i class='ace-icon fa fa-check bigger-110'></i> 提交电子工作流程",
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
																	html : "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
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
																	html : "<i class='ace-icon fa fa-check bigger-110'></i> 撤销电子工作流程",
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
																	html : "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
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
		function dateDiffNoWeekDay(oDate1, oDate2) {
			//var sDate1=document.getElementById("date1").value;
			//var sDate2=document.getElementById("date2").value;

			//日期格式为yyyy-mm-dd  
			//var oDate1  = StringToDate(sDate1);
			//var oDate2  = StringToDate(sDate2); 
			/*alert('取整-->'+parseInt((oDate2 - oDate1) / 1000 / 60 / 60 /24));
			alert('非取整-->'+(oDate2 - oDate1) / 1000 / 60 / 60 /24);*/
			if (parseInt((oDate2 - oDate1) / 1000 / 60 / 60 / 24) < (oDate2 - oDate1)
					/ 1000 / 60 / 60 / 24) {
				var days = parseInt((oDate2 - oDate1) / 1000 / 60 / 60 / 24) + 1;
			} else {
				days = parseInt((oDate2 - oDate1) / 1000 / 60 / 60 / 24);
			}

			//var days = parseInt((oDate2 - oDate1) / 1000 / 60 / 60 /24);//获取总的天数
			var days1 = parseInt((oDate2 - oDate1) / 1000 / 60 / 60) % 8; //获取余下的小时
			
			/*--减去不用上班的时间,即09-18之外的时间--*/
			if (days1 > 8) {
				days1 = 8;
			}
			
			var tempDate = oDate1;
			while (tempDate.getTime() <= oDate2.getTime()) {
				//tempDate = addDays(tempDate,2);//加一天
				//days>0表示超过1天，防止出现负数days
				if (checkWeekDay(DateToString(tempDate)) & days > 0) {
					//如果是周末,天数减1
					days--;
				}
				tempDate = addDays(tempDate, 2);//加一天
			}
			console.log("------------------------"+days);
			/*if (days == 1) {
				if (parseInt((oDate2 - oDate1) / 1000 / 60 / 60 / 24) == 0
						& days1 == 0) {
					days = 1;
				} else {
					days = 0;
				}
			}/*一天或半天加判断解决天数的问题*/

			// day2.value=days+"天"+days1+"小时";
			console.log("------------------------");
			if(days>0&&days1>0){
				days=days-1;
			}
			if(days1>0&&days<=7){
				days1=days1+1;
			}
			console.log(days + "天" + days1 + "小时");
			
			return days * 8 + days1;
		}
		/*判断是否含有周末,如果是周末 返回true,没有返回false*/
		function checkWeekDay(sDate) {
			arys = sDate.split('-');
			arys1 = arys[2].split(' ');
			arys2 = arys1[1].split(':');
			oDate = new Date(arys[0], parseInt(arys[1], 10) - 1, arys1[0],
					arys2[0], arys2[1], arys2[2]);
			day = oDate.getDay();//判断是否周末
			if (day == 0 || day == 6) {
				return true;
			}
			return false;
		}
		/*增加天数*/
		function addDays(oDate, days) {
			if (days > 0) {
				days = days - 1;
			}
			if (days < 0) {
				days = days + 1;
			}
			var result = new Date(oDate.getTime()
					+ (days * 24 * 60 * 60 * 1000));
			return result;
		}
		/*将字符串转换成日期*/
		function StringToDate(sDate) {
			arys = sDate.split('-');
			arys1 = arys[2].split(' ');
			arys2 = arys1[1].split(':');
			if (arys2[0] > 18) {
				arys2[0] = 18;
				arys2[1] = 00;
				arys2[2] = 00;
			}
			if (arys2[0] < 9) {
				arys2[0] = 9;
				arys2[1] = 0;
				arys2[2] = 0;
			}
			var newDate = new Date(arys[0], parseInt(arys[1], 10) - 1,
					arys1[0], arys2[0], arys2[1], arys2[2]);
			return newDate;
		}
		/*为一部分月份及日期加前+0*/
		function DateToString(oDate) {
			var month = oDate.getMonth() + 1;
			var day = oDate.getDate();
			var hour = oDate.getHours();
			var mi = oDate.getMinutes();
			var second = oDate.getSeconds();
			//如果月份小于10月则在前面加0
			if (month < 10) {
				month = "0" + month;
			}
			//如果日期小于10号则在前面加0
			if (day < 10) {
				day = "0" + day;
			}
			if (hour < 10) {
				//如果小于9点 设置为9点
				if (hour < 10) { //9
					hour = 10;//9
				}
				hour = "0" + hour;
			}
			//如果大于18点，让他等于18点
			if (hour > 18) {
				hour = 18;
			}
			if (mi < 10) {
				mi = "0" + mi;
			}
			if (second < 10) {
				second = "0" + second;
			}
			return oDate.getFullYear() + "-" + month + "-" + day + " " + hour
					+ ":" + mi + ":" + second;
		}
		function getDates() {

			//console.log("--->date1:" + date1 + "date2:" + date2);
			if ($("#timeStart").datetimebox('getValue')) {
				date1 = StringToDate($("#timeStart").datetimebox('getValue'));
			}
			if ($("#timeEnd").datetimebox('getValue')) {
				date2 = StringToDate($("#timeEnd").datetimebox('getValue'));
			}
			if (date1 && date2) {
				var html = new Array();
				var dates = dateDiffNoWeekDay(date1, date2);
				//alert(dates);
				var d = parseInt(dates / 8);
				var h = dates % 8;
				//console.log("dates:" + dates + "--->d:" + d + "h:" + h);
				if (d > 0) {
					html.push("共" + d + "天");
				}
				if (h > 0) {
					html.push("" + h + "小时");
				}
				$('#timeCount').html(html.join(''));
			}
		}
		
		
		jQuery(function($) {
			//init_uploader();

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'pickfiles', // you can pass in id...
				container: document.getElementById('container'), // ... or DOM Element itself
				url : portalContextPath+'/attach/uploadFile.do?noticeId='+noticeId+'&collectionName=notice',
		    		flash_swf_url : portalContextPath+'/content/plupload-2.1.2/js/Moxie.swf',
		    		silverlight_xap_url : portalContextPath+'/content/plupload-2.1.2/js/Moxie.xap',
				filters : {
					max_file_size : '100mb',
					mime_types: [
						{title : "Image files", extensions : "jpg,gif,png"},
			            {title : "Office files", extensions : "xls,xlsx,doc,docx,ppt,pdf"},
			            {title : "Artive files", extensions : "zip,rar,gzip"}
					]
				},

				init: {
					PostInit: function() {
						document.getElementById('filelist').innerHTML = '';

						document.getElementById('uploadfiles').onclick = function() {
							uploader.start();
							return false;
						};
					},

					FilesAdded: function(up, files) {
						plupload.each(files, function(file) {
							//alert(file.id);
							document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
						});
					},

					UploadProgress: function(up, file) {
						document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
					},

					Error: function(up, err) {
						document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
					}
				}
			});

			uploader.init();
			uploader.bind("FileUploaded", function (uploader,file,responseObject) {
					console.log(file.id);
					var id=file.id;
					var rst=JSON.parse(responseObject.response);
					if (!rst.state) {
						
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
									 
								}
							}
						}
					});
			
				}else{
					var html=[];
					$.each(rst.list, function(n, file) {
						
						html.push('<div id="' + file.fileUrl + '"> <a href="'+portalContextPath +'/files/download.do?collectionName=notice&originalFilename='+file.fileName+'&fileName='+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.fileUrl+'\')"></a><b></b></div>');
					});
					document.getElementById('filelist-history').innerHTML+=html.join('');
					$('#'+id).html('');
					
				}
		    });
		});

	</script>
</body>
</html>