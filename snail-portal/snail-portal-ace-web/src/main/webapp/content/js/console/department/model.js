var _colNames = [ '部门编号', '上级部门编号', '部门名称', '部门描述', '门牌号', '部门级别', '部门负责人',
		'创建时间', '状态' ];
var _colModel = function() {
	return [ {
		name : 'departmentId',
		index : 'id',
		width : 100,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'parentDepartmentId',
		width : 100,
		editable : true,
		sorttype : "int",
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'departmentName',
		index : 'departmentName',
		width : 250,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'description',
		index : 'description',
		width : 150,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "250"
		}
	}, {
		name : 'hourceNumber',
		index : 'hourceNumber',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "50"
		}
	}, {
		name : 'departmentLevel',
		width : 60,
		editable : true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "STATIC_DATA_02");
		},
		editoptions : {
			value : odparse("STATIC_DATA_02")
		}
	}, {
		name : 'userId',
		width : 90,
		editable : true,
		edittype : "combogrid",
		sorttype : "int",
		dataoptions : {
			panelWidth : 400,
			idField : 'USER_ID',
			textField : 'NAME',
			url : contextPath + '/system/selectUsers.do',
			mode : 'remote',
			fitColumns : true,
			method : 'get',
			columns : [ [ {
				field : 'USER_ID',
				title : '用户编号',
				width : 80
			}, {
				field : 'NAME',
				title : '姓名',
				width : 80
			}, {
				field : 'ID_CARD',
				title : '身份证号',
				width : 250,
				align : 'right'
			}, {
				field : 'DEPARTMENT_NAME',
				title : '所属部门',
				width : 150,
				align : 'right'
			} ] ]
		},
		editoptions : {
			style : 'width:153px'
		},
		renderer : function(value, cur) {
			return $.jgrid.getAccessor(cur, 'userName');
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'createTime',
		width : 150,
		sortable : true,
		editable : false
	}, {
		name : 'stauts',
		index : 'stauts',
		width : 90,
		editable : true,
		edittype : "checkbox",
		editoptions : {
			value : "1:0"
		},
		unformat : aceSwitch,
		renderer : function(value) {
			// console.log(value);
			var rst = "";
			switch (value) {
			case '1':
				rst = "YES";
				break;
			case '0':
				rst = "NO";
				break;
			default:
				rst = "N/A";
			}
			return rst;
		}
	} ];
}
function aceSwitch(cellvalue, options, cell) {
	console.log('aceSwitch');
	setTimeout(function() {
		$(cell).find('input[type=checkbox]').addClass(
				'ace ace-switch ace-switch-5').after(
				'<span class="lbl"></span>');
	}, 0);
}
// enable datepicker
function pickDate(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=text]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}