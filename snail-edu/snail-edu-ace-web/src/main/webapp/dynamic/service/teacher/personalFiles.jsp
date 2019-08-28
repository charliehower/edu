<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="dg-personalFiles" class="easyui-datagrid" title="个人文件档案"
	style="width: 1110px; height: 400px"
	data-options="
iconCls: 'icon-edit',
rownumbers:true,
singleSelect: true,
toolbar: '#tb-personalFiles',
url: '${pageContext.request.contextPath}/personalFiles/findPersonalFilesListByTeacherId.do?teacherId=${teacherId}',
method: 'get',
onClickRow: onClickRowpersonalFiles
">
	<thead>
		<tr>
			<th
				data-options="field:'personalFilesId',width:80,align:'center'">编号</th>
			
			<th
				data-options="field:'fileName',width:700,align:'left',formatter:_href">文件</th>
					
			<th data-options="field:'createTime',width:150,align:'center'">入库时间</th>

		</tr>
	</thead>
</table>

<div id="tb-personalFiles" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true"
		id="btn-upload-add-pf">添加</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true"
		onclick="removeitpersonalFiles()">删除</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-search',plain:true"
		onclick="viewPersonalFiles()">查看</a>
		
			<a href="javascript:void(0)" onclick="$('#dg-personalFiles').datagrid('reload')"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-reload',plain:true"
		>刷新</a>
</div>
</div>
<div style="height: 10px"></div>


<div id="dialog-message-pf" class="hide">
		
		<div id="uploader-pf">
    <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
</div>

	</div>

<script type="text/javascript">
	var editIndex = undefined;
	function endEditingpersonalFiles() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#dg-personalFiles').datagrid('validateRow', editIndex)) {
			var ed = $('#dg-personalFiles').datagrid('getEditor', {
				index : editIndex,
				field : 'xtype'
			});
			$('#dg-personalFiles').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRowpersonalFiles(index) {
		if (editIndex != index) {
			if (endEditingpersonalFiles()) {
				$('#dg-personalFiles').datagrid('selectRow', index).datagrid(
						'beginEdit', index);
				editIndex = index;
			} else {
				$('#dg-personalFiles').datagrid('selectRow', editIndex);
			}
		}
	}
	function appendpersonalFiles() {
		if (endEditingpersonalFiles()) {
			$('#dg-personalFiles').datagrid('appendRow', {teacherId:$("input[name='teacherId']").val() });
			editIndex = $('#dg-personalFiles').datagrid('getRows').length - 1;
			$('#dg-personalFiles').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
		}
	}
	function removeitpersonalFiles() {
		if (editIndex == undefined) {
			return
		}
		if(!confirm("确定删除?")){
			return;
		}
		var r=$('#dg-personalFiles').datagrid('getSelected');
		console.log(r.personalFilesId);
		var id=r.personalFilesId;
		$.ajax({
			type : "get",
			url : contextPath + "/personalFiles/deletePersonalFilesByPersonalFilesId.do",
			data:{id:id},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				$('#dg-personalFiles').datagrid('cancelEdit', editIndex).datagrid(
						'deleteRow', editIndex);
				editIndex = undefined;
			},
			complete : function(XMLHttpRequest, textStatus) {
				
			},
			error : function() {
			}
		});
		
	}
	function acceptpersonalFiles() {
		if (endEditingpersonalFiles()) {
			$('#dg-personalFiles').datagrid('acceptChanges');
		}
	}
	function viewPersonalFiles(){
		
		var r=$('#dg-personalFiles').datagrid('getSelected');
		console.log(r.personalFilesId);
		var id=r.personalFilesId;
		var url=portalContextPath+'/files/download.do?collectionName=personalFiles&originalFilename='+r.fileName+'&fileName='+r.fileUrl;
		if(!confirm("确定下载文件:"+r.fileName+"?")){
			return;
		}
		window.open(url);
	}
	function _href(value){
		return "<a href='javasrcipt:false' onClick='viewPersonalFiles()' style='color:#000000'>"+value+"</a>";
	}
</script>

