<%@page import="org.platform.snail.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	
%>
<script type="text/javascript">
	var dataTeamGrade = {};
	<c:forEach var="item" items="${listTeamGrade}" varStatus="status">
	dataTeamGrade['gtl${item.grade_id}'] = '${item.name}';
	</c:forEach>
</script>
<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form action="index.jsp" id="fm-search">
					<input type="hidden" name="active" value="gtl">
					学年：<input class="easyui-combobox"
					style="width: 200px; height: 25px; line-height: 25px;"
					name="year" value="${year }"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=19&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">

						<button class="btn btn-info" id="btn-search-teamGrade"
							authority="false">
							年级组长查询<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					
				</div>
			</div>
		</div>
<table style="width: 100%" id="grid-teamGrade"
	class="table table-striped table-bordered table-hover">

	<thead>
		<tr>
			<th align="left" width="100px">年级</th>
			<th align="left" width="200px">年级组长</th>
			
			<th align="left"> 备注</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="item" items="${grade}" varStatus="status">
			<tr>
				<td align="center">${item.grade_name}</td>
				

					<td align="center">
						<button authority="false"
							id="gtl${item.grade_id}"
							onclick="saveOrUpdateTeamGrade('${item.grade_id}',dataTeamGrade['gtl${item.grade_id}'],'gtl${item.grade_id}')"
							type="button" class="btn btn-info">
							<script>
								document
										.write(dataTeamGrade['gtl${item.grade_id}']
												|| '设置');
								if (!dataTeamGrade['gtl${item.grade_id}']) {
									$('#gtl${item.grade_id}')
											.removeClass("btn-info");
									$('#gtl${item.grade_id}')
											.addClass("btn-warning");
								}
							</script>
						</button>

					</td>
					
					<td>
					</td>
	
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="dialog-message-teamGrade" class="hide">
	<form id="fm-teacher-search-teamGrade">

		<div class="profile-info-row">
			<div class="profile-info-name">所属部门</div>

			<div class="profile-info-value">

					<input name="search-departmentId-teamGrade" class="easyui-combotree" data-options="url:'${sessionScope.portalContextPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false," style='width:200px;line-height: 30px;height: 30px;'>

			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name">所属年级</div>

			<div class="profile-info-value">
				<input class="easyui-combobox"
					style="width: 200px; height: 25px; line-height: 25px;"
					name="search-gradeId-teamGrade"
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
					name="search-disciplineId-teamGrade"
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
				<select class="easyui-combogrid" id="cc-teacher-teamGrade" 
					style="width: 400px; height: 25px; line-height: 25px;" name="cc-teacher-teamGrade"
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