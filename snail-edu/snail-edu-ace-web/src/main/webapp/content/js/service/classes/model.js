var _colNames = [ '编号','所属年级', '名称', '创建时间', '备注' ];
var _colModel = function() {
	return [ {
		name : 'classesId',
		index : 'classesId',
		width : 60,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : false
		}
	}, {
		name : 'gradeId',
		index : 'gradeId',
		width : 100,
		editable : true,
		edittype : "combobox",
		dataoptions:{
			 url: portalContextPath +'/dict/findListByCategoryId.do?categoryId=14',
		        method: 'get',
		        valueField:'code',
		        textField:'name'
		},
		editoptions : {
			style:'width:176px;line-height: 30px;height: 30px;'
		},
		renderer : function(value, cur) {
			return $.jgrid.getAccessor(cur, 'gradeName');
		}
	},{
		name : 'classesName',
		index : 'classesName',
		width : 150,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	}, {
		name : 'createTime',
		width : 150,
		sortable : true,
		editable : false
	}, {
		name : 'remark',
		index : 'remark',
		width : 150,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
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