<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="fm-teacher-group">

	<div class="profile-info-row">
		<div class="profile-info-name">所属部门</div>

		<div class="profile-info-value">

			<input name="departmentId" class="easyui-combotree"
				data-options="url:'${sessionScope.portalContextPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false,"
				style='width: 200px; line-height: 30px; height: 30px;'>

		</div>
	</div>
	<div class="profile-info-row" style="display:${display}">
		<div class="profile-info-name">所属年级</div>

		<div class="profile-info-value">
			<input class="easyui-combobox"
				style="width: 200px; height: 25px; line-height: 25px;"
				name="gradeId"
				data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=14',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
		</div>

	</div>
	<div class="profile-info-row" style="display:${display}">
		<div class="profile-info-name">任教班级</div>

		<div class="profile-info-value">
			<input class="easyui-combobox"
				style="width: 200px; height: 25px; line-height: 25px;"
				id="classesTaught" name="classesTaught"
				data-options="
					multiple:true,
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=15&gradeId=1,2,3',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
		</div>

	</div>

	<div class="profile-info-row" style="display:${display}">
		<div class="profile-info-name">所属学科</div>

		<div class="profile-info-value">
			<input class="easyui-combobox"
				style="width: 200px; height: 25px; line-height: 25px;"
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
		<div class="profile-info-name"></div>

		<div class="profile-info-value" style="width: 600px">
			<button class="btn btn-info" id="btn-teacher-group" authority="false">
				<i class="ace-icon fa fa-check bigger-110"></i> 保存
			</button>

			&nbsp; &nbsp;
			<button id="btn-teacher-reset" type="button" class="btn"
				authority="${pageContext.request.contextPath}/teacher/insertTeacher.do">
				<i class="ace-icon fa fa-undo bigger-110"></i> 重置
			</button>
		</div>
	</div>


</form>

