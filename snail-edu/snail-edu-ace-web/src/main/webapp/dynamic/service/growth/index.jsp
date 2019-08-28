<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String start = org.platform.snail.utils.SnailUtils
			.parseDate(new java.util.Date()) + " 00:00:00";
	String end = org.platform.snail.utils.SnailUtils
			.parseDate(new java.util.Date()) + " 23:59:59";
	request.setAttribute("start", start);
	request.setAttribute("end", end);
	request.setAttribute("now", org.platform.snail.utils.SnailUtils
			.parseDate(new java.util.Date()));
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>calendar</title>
</head>
<jsp:include page="../../common/common.jsp" />

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/fullcalendar.css" />

<link rel="stylesheet"
	href="${sessionScope.portalContextPath}/content/assets/css/jquery-ui.min.css" />
<body>
	<div class="page-content">
		<div id="toolbar" class="toolbar">
			班级：<input class="easyui-combobox"
				style="width: 200px; height: 25px; line-height: 25px;"
				name="classesId" id="classesId"
				data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=15&gradeId=1,2,3&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'">
		</div>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					
						<div class="space"></div>

						<!-- #section:plugins/data-time.calendar -->
						<div id="calendar"></div>

					

					
				</div>

				<!-- PAGE CONTENT ENDS -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->

	</div>
	<jsp:include page="../../common/footer-1.jsp" />

	<script
		src="${sessionScope.portalContextPath}/content/assets/js/fuelux/fuelux.spinner.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/bootstrap-timepicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/uncompressed/date-time/moment.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/daterangepicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/bootstrap-colorpicker.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/jquery.knob.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/jquery.autosize.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/jquery.maskedinput.min.js"></script>
	<script
		src="${sessionScope.portalContextPath}/content/assets/js/bootstrap-tag.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/content/js/service/growth/fullcalendar.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/growth/controller.js"></script>


	<div id="dialog-message" class="hide">
	<form id="fm-growth">
		<table  class="table table-striped table-bordered table-hover">
			<tr>
				<td align="center">考勤请假(10分)</td>
				<td align="center">卫生包干区(5分)</td>
				<td align="center">教师厕所工具(10分)</td>
				<td align="center">文明礼仪(10分)</td>
				<td align="center">升国旗仪式(3分)</td>
			</tr>
			<tr>
				<td align="center"><input type="text" name="attendance" value="10"/></td>
				<td align="center"><input type="text" name="hygieneAreas" value="5"/></td>
				<td align="center"><input type="text" name="hygieneTools" value="10"/></td>
				<td align="center"><input type="text" name="ceremony" value="10"/></td>
				<td align="center"><input type="text" name="liveFlag" value="3"/></td>
			</tr>
			<tr>
				<td align="center">课间操(4分)</td>
				<td align="center">眼保健操(3分)</td>
				<td align="center">安全(10分)</td>
				<td align="center">违禁品检查(10分)</td>
				<td align="center">周末违禁品检查(5分)</td>
			</tr>
			<tr>
				<td align="center"><input type="text" name="exerciseBody" value="4"/></td>
				<td align="center"><input type="text" name="exerciseEye" value="3"/></td>
				<td align="center"><input type="text" name="security" value="10"/></td>
				<td align="center"><input type="text" name="contraband" value="10"/></td>
				<td align="center"><input type="text" name="contrabandWeekend" value="5"/></td>
			</tr>
			<tr>
				<td align="center">宿舍管理(20分)</td>
				<td align="center">学风建设(10分)</td>
				<td align="center">加分项目(0~10分)</td>
				<td align="center">备注</td>
				<td align="center">总分(110分)</td>
			</tr>
			<tr>
				<td align="center"><input type="text" name="dormitory" value="20"/></td>
				<td align="center"><input type="text" name="construction"value="10" /></td>
				<td align="center"><input type="text" name="bonus" value="0"/></td>
				<td align="center"><input type="text" name="remark" /></td>
				<td align="center"><input type="text" name="totalScore" readOnly="true" value="0"/></td>
			</tr>
			<tr>
				<td align="center">考核人A</td>
				<td align="center">考核人B</td>
				<td align="center">考核人C</td>
				<td align="center">考核人D</td>
				<td align="center">考核时间</td>
			</tr>
			<tr>
				<td align="center"><input type="text" name="checkeraName"  readOnly="true"/></td>
				<td align="center"><input type="text" name="checkerbName"  readOnly="true"/></td>
				<td align="center"><input type="text" name="checkercName"  readOnly="true"/></td>
				<td align="center"><input type="text" name="checkerdName"  readOnly="true"/></td>
				<td align="center"><input type="text" name="checkDate" value="${now}" readOnly="true"/></td>
			</tr>
		</table>
		<input name="start" type="hidden" value="${star }"/> <input name="end" type="hidden" value="${end }"/>
		</form>
	</div>
	<link rel="stylesheet" type="text/css"
		href="${sessionScope.portalContextPath}/content/jquery-easyui-1.3.6/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="${sessionScope.portalContextPath}/content/jquery-easyui-1.3.6/themes/icon.css">
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/jquery-easyui-1.3.6/gz/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${sessionScope.portalContextPath}/content/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
	src="${sessionScope.portalContextPath}/content/js/common/jquery.form.js"></script>
<style>
.input{
	height:15px;
}
</style>
</body>
</html>