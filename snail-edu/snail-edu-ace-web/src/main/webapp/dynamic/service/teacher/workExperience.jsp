<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="dg-workExperience" class="easyui-datagrid" title="工作经历"
	style="width: 1110px; height: 400px"
	data-options="
iconCls: 'icon-edit',
rownumbers:true,
singleSelect: true,
toolbar: '#tb-workExperience',
url: '${pageContext.request.contextPath}/workExperience/findWorkExperienceListByTeacherId.do?teacherId=${teacherId}',
method: 'get',
onClickRow: onClickRowWorkExperience
">
	<thead>
		<tr>
			<th
				data-options="field:'workExperienceId',width:80,align:'center',hidden:false,editor:{type:'text', required:true}">编号</th>
			<th
				data-options="field:'teacherId',width:100,align:'center',hidden:false,editor:{type:'text', required:true}">教职工编号</th>

			<th
				data-options="field:'dateStart',width:120,align:'center',editor:{type:'datebox', required:true},formatter:Common.DateFormatter" >开始时间</th>
			<th
				data-options="field:'dateEnd',width:120,align:'center',editor:{type:'datebox', required:true},formatter:Common.DateFormatter">结束时间</th>

			<th
				data-options="field:'workUnit',width:200,align:'center',editor:{type:'text',maxlength:30, required:true}">工作单位</th>
			<th
				data-options="field:'position',width:100,align:'center',editor:{type:'text',maxlength:30, required:true}">职务</th>
			<th
				data-options="field:'performance',width:100,align:'center',editor:{type:'text',maxlength:30}">业绩</th>
			<th
				data-options="field:'reterence',width:100,align:'center',editor:{type:'text',maxlength:10}">证明人</th>
			<th data-options="field:'createTime',width:150,align:'center'">入库时间</th>

		</tr>
	</thead>
</table>

<div id="tb-workExperience" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true"
		onclick="appendWorkExperience()">添加</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true"
		onclick="removeitWorkExperience()">删除</a> 
		<a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-ok',plain:true"
		onclick="acceptWorkExperience()">确定</a>
		
		<a href="javascript:void(0)" id="btn-workExperience-save"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-save',plain:true"
		>保存</a>
		
		<a href="javascript:void(0)" onclick="$('#dg-workExperience').datagrid('reload')"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-reload',plain:true"
		>刷新</a>
</div>
<div style="height: 10px"></div>




<script type="text/javascript">
	var editIndex = undefined;
	function endEditingWorkExperience() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#dg-workExperience').datagrid('validateRow', editIndex)) {
			var ed = $('#dg-workExperience').datagrid('getEditor', {
				index : editIndex,
				field : 'xtype'
			});
			$('#dg-workExperience').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRowWorkExperience(index) {
		if (editIndex != index) {
			if (endEditingWorkExperience()) {
				$('#dg-workExperience').datagrid('selectRow', index).datagrid(
						'beginEdit', index);
				editIndex = index;
			} else {
				$('#dg-workExperience').datagrid('selectRow', editIndex);
			}
		}
	}
	function appendWorkExperience() {
		if (endEditingWorkExperience()) {
			$('#dg-workExperience').datagrid('appendRow', {teacherId:$("input[name='teacherId']").val() });
			editIndex = $('#dg-workExperience').datagrid('getRows').length - 1;
			$('#dg-workExperience').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
		}
	}
	function removeitWorkExperience() {
		if (editIndex == undefined) {
			return
		}
		if(!confirm("确定删除?")){
			return;
		}
		var r=$('#dg-workExperience').datagrid('getSelected');
		console.log(r.workExperienceId);
		var id=r.workExperienceId;
		$.ajax({
			type : "get",
			url : contextPath + "/workExperience/deleteWorkExperienceByWorkExperienceId.do",
			data:{id:id},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				$('#dg-workExperience').datagrid('cancelEdit', editIndex).datagrid(
						'deleteRow', editIndex);
				editIndex = undefined;
			},
			complete : function(XMLHttpRequest, textStatus) {
				
			},
			error : function() {
			}
		});
		
	}
	function acceptWorkExperience() {
		if (endEditingWorkExperience()) {
			$('#dg-workExperience').datagrid('acceptChanges');
		}
	}
</script>

