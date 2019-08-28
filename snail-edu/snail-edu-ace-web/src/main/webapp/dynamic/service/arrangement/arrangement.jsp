<%@page import="org.platform.snail.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	
%>
<script type="text/javascript">
	var data = {};
	<c:forEach var="item" items="${list}" varStatus="status">
	data['${item.classes_id}${item.discipline_id}'] = '${item.name}';
	</c:forEach>
</script>
<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
				<input type="hidden" name="active" value="arrangement">
					<form action="index.jsp" id="fm-search">
					学年：<input class="easyui-combobox"
					style="width: 200px; height: 25px; line-height: 25px;"
					name="year" value="${year }"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=19&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
						年级：<input class="easyui-combobox"
					style="width: 200px; height: 25px; line-height: 25px;"
					name="gradeId" value="${gradeId }"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=14&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">

						<button class="btn btn-info" id="btn-search"
							authority="false">
							岗位查询<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					
				</div>
			</div>
		</div>
<table style="width: 100%" id="grid-arr"
	class="table table-striped table-bordered table-hover">

	<thead>
		<tr>
			<th align="left">班级</th>
			<c:forEach var="item" items="${dis}" varStatus="status">
				<th align="center">${item.discipline_name}</th>
			</c:forEach>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="item" items="${classes}" varStatus="status">
			<tr>
				<td align="center">${item.grade_name}${item.classes_name}</td>
				<c:forEach var="o" items="${dis}" varStatus="status">

					<td align="center">
						<button authority="false"
							id="${item.classes_id}${o.discipline_id}"
							onclick="saveOrUpdateTeacher('${item.grade_id}','${item.classes_id}','${o.discipline_id}',data['${item.classes_id}${o.discipline_id}'],'${item.classes_id}${o.discipline_id}')"
							type="button" class="btn btn-info">
							<script>
								document
										.write(data['${item.classes_id}${o.discipline_id}']
												|| '设置');
								if (!data['${item.classes_id}${o.discipline_id}']) {
									$('#${item.classes_id}${o.discipline_id}')
											.removeClass("btn-info");
									$('#${item.classes_id}${o.discipline_id}')
											.addClass("btn-warning");
								}
							</script>
						</button>

					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="dialog-message-arr" class="hide">
	<form id="fm-teacher-search">

		<div class="profile-info-row">
			<div class="profile-info-name">所属部门</div>

			<div class="profile-info-value">

					<input name="search-departmentId" class="easyui-combotree" data-options="url:'${sessionScope.portalContextPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false," style='width:200px;line-height: 30px;height: 30px;'>

			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">所属年级</div>

			<div class="profile-info-value">
				<input class="easyui-combobox"
					style="width: 200px; height: 25px; line-height: 25px;"
					name="search-gradeId"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=14&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
			</div>

		</div>


		<div class="profile-info-row">
			<div class="profile-info-name">所属学科</div>

			<div class="profile-info-value">
				<input class="easyui-combobox" 
					style="width: 200px; height: 25px; line-height: 25px;"
					name="search-disciplineId"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=16&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
			</div>
		</div>





		<div class="profile-info-row">
			<div class="profile-info-name">教职工</div>

			<div class="profile-info-value">
				<select class="easyui-combogrid" id="cc-teacher" 
					style="width: 400px; height: 25px; line-height: 25px;" name="cc-teacher"
					data-options="panelWidth: 500,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectTeacher.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'TEACHER_ID',title:'教职工编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'GRADE_NAME',title:'年级',width:80,align:'right'},
			{field:'DISCIPLINE_NAME',title:'学科',width:80,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select>
			</div>
		</div>
	</form>
</div>