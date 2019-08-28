<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="dg-learningExperience" class="easyui-datagrid" title="学习经历"
	style="width: 1110px; height: 400px"
	data-options="
iconCls: 'icon-edit',
rownumbers:true,
singleSelect: true,
toolbar: '#tb-learningExperience',
url: '${pageContext.request.contextPath}/learningExperience/findLearningExperienceListByTeacherId.do?teacherId=${teacherId}',
method: 'get',
onClickRow: onClickRowlearningExperience
">
	<thead>
		<tr>
			<th
				data-options="field:'learningExperienceId',width:80,align:'center',hidden:false,editor:{type:'text', required:true}">编号</th>
			<th
				data-options="field:'teacherId',width:100,align:'center',hidden:false,editor:{type:'text', required:true}">教职工编号</th>

			<th
				data-options="field:'dateStart',width:120,align:'center',editor:{type:'datebox', required:true},formatter:Common.DateFormatter" >开始时间</th>
			<th
				data-options="field:'dateEnd',width:120,align:'center',editor:{type:'datebox', required:true},formatter:Common.DateFormatter">结束时间</th>

			<th
				data-options="field:'schoolName',width:200,align:'center',editor:{type:'text',maxlength:30, required:true}">学校</th>
			<th
				data-options="field:'professional',width:100,align:'center',editor:{type:'text',maxlength:30, required:true}">专业</th>
			<th
				data-options="field:'education',width:100,align:'center',editor:{type:'text',maxlength:30}">业绩</th>
			
			<th data-options="field:'createTime',width:150,align:'center'">入库时间</th>

		</tr>
	</thead>
</table>

<div id="tb-learningExperience" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true"
		onclick="appendlearningExperience()">添加</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true"
		onclick="removeitlearningExperience()">删除</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-ok',plain:true"
		onclick="acceptlearningExperience()">确定</a>
		
		<a href="javascript:void(0)" id="btn-learningExperience-save"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-save',plain:true"
		>保存</a>
		
		<a href="javascript:void(0)" onclick="$('#dg-learningExperience').datagrid('reload')"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-reload',plain:true"
		>刷新</a>
</div>
<div style="height: 10px"></div>




<script type="text/javascript">
	var editIndex = undefined;
	function endEditinglearningExperience() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#dg-learningExperience').datagrid('validateRow', editIndex)) {
			var ed = $('#dg-learningExperience').datagrid('getEditor', {
				index : editIndex,
				field : 'xtype'
			});
			$('#dg-learningExperience').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRowlearningExperience(index) {
		if (editIndex != index) {
			if (endEditinglearningExperience()) {
				$('#dg-learningExperience').datagrid('selectRow', index).datagrid(
						'beginEdit', index);
				editIndex = index;
			} else {
				$('#dg-learningExperience').datagrid('selectRow', editIndex);
			}
		}
	}
	function appendlearningExperience() {
		if (endEditinglearningExperience()) {
			$('#dg-learningExperience').datagrid('appendRow', {teacherId:$("input[name='teacherId']").val() });
			editIndex = $('#dg-learningExperience').datagrid('getRows').length - 1;
			$('#dg-learningExperience').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
		}
	}
	function removeitlearningExperience() {
		if (editIndex == undefined) {
			return
		}
		if(!confirm("确定删除?")){
			return;
		}
		var r=$('#dg-learningExperience').datagrid('getSelected');
		console.log(r.learningExperienceId);
		var id=r.learningExperienceId;
		$.ajax({
			type : "get",
			url : contextPath + "/learningExperience/deleteLearningExperienceByLearningExperienceId.do",
			data:{id:id},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				$('#dg-learningExperience').datagrid('cancelEdit', editIndex).datagrid(
						'deleteRow', editIndex);
				editIndex = undefined;
			},
			complete : function(XMLHttpRequest, textStatus) {
				
			},
			error : function() {
			}
		});
		
	}
	function acceptlearningExperience() {
		if (endEditinglearningExperience()) {
			$('#dg-learningExperience').datagrid('acceptChanges');
		}
	}
</script>

