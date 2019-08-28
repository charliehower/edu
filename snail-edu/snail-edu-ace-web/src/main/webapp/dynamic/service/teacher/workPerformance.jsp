<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="dg-workPerformance" class="easyui-datagrid" title="工作经历"
	style="width: 1110px; height: 400px"
	data-options="
iconCls: 'icon-edit',
rownumbers:true,
singleSelect: true,
toolbar: '#tb-workPerformance',
url: '${pageContext.request.contextPath}/workPerformance/findWorkPerformanceListByTeacherId.do?teacherId=${teacherId}',
method: 'get',
onClickRow: onClickRowWorkPerformance
">
	<thead>
		<tr>
			<th
				data-options="field:'workPerformanceId',width:80,align:'center',hidden:false,editor:{type:'text', required:true}">编号</th>
			<th
				data-options="field:'teacherId',width:100,align:'center',hidden:false,editor:{type:'text', required:true}">教职工编号</th>

			
			<th
				data-options="field:'classes',width:200,align:'center',editor:{type:'text',maxlength:30, required:true}">班级</th>
			<th
				data-options="field:'duty',width:100,align:'center',editor:{type:'text',maxlength:30, required:true}">职责</th>
			<th
				data-options="field:'description',width:100,align:'center',editor:{type:'text',maxlength:30}">在岗表现</th>
			<th
				data-options="field:'record',width:100,align:'center',editor:{type:'text',maxlength:10}">记录人</th>
			<th data-options="field:'createTime',width:150,align:'center'">入库时间</th>

		</tr>
	</thead>
</table>

<div id="tb-workPerformance" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true"
		onclick="appendWorkPerformance()">添加</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true"
		onclick="removeitWorkPerformance()">删除</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-ok',plain:true"
		onclick="acceptWorkPerformance()">确定</a>
		
		<a href="javascript:void(0)" id="btn-workPerformance-save"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-save',plain:true"
		>保存</a>
		
		<a href="javascript:void(0)" onclick="$('#dg-workPerformance').datagrid('reload')"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-reload',plain:true"
		>刷新</a>
</div>
<div style="height: 10px"></div>




<script type="text/javascript">
	var editIndex = undefined;
	function endEditingWorkPerformance() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#dg-workPerformance').datagrid('validateRow', editIndex)) {
			var ed = $('#dg-workPerformance').datagrid('getEditor', {
				index : editIndex,
				field : 'xtype'
			});
			$('#dg-workPerformance').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRowWorkPerformance(index) {
		if (editIndex != index) {
			if (endEditingWorkPerformance()) {
				$('#dg-workPerformance').datagrid('selectRow', index).datagrid(
						'beginEdit', index);
				editIndex = index;
			} else {
				$('#dg-workPerformance').datagrid('selectRow', editIndex);
			}
		}
	}
	function appendWorkPerformance() {
		if (endEditingWorkPerformance()) {
			$('#dg-workPerformance').datagrid('appendRow', {teacherId:$("input[name='teacherId']").val() });
			editIndex = $('#dg-workPerformance').datagrid('getRows').length - 1;
			$('#dg-workPerformance').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
		}
	}
	function removeitWorkPerformance() {
		if (editIndex == undefined) {
			return
		}
		if(!confirm("确定删除?")){
			return;
		}
		var r=$('#dg-workPerformance').datagrid('getSelected');
		console.log(r.workPerformanceId);
		var id=r.workPerformanceId;
		$.ajax({
			type : "get",
			url : contextPath + "/workPerformance/deleteWorkPerformanceByWorkPerformanceId.do",
			data:{id:id},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				$('#dg-workPerformance').datagrid('cancelEdit', editIndex).datagrid(
						'deleteRow', editIndex);
				editIndex = undefined;
			},
			complete : function(XMLHttpRequest, textStatus) {
				
			},
			error : function() {
			}
		});
		
	}
	function acceptWorkPerformance() {
		if (endEditingWorkPerformance()) {
			$('#dg-workPerformance').datagrid('acceptChanges');
		}
	}
</script>

