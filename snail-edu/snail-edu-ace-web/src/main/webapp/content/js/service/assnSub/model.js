var _colNames = ['ID', '编号','所属班级', '社团', '学生姓名', '指导老师', '审核人', '审核时间', '状态', '申请时间', '备注' ];
var _colModel = function() {
	return [{
		name : 'assnSubId',
		index : 'assnSubId',
		hidden : true
	}, {
		name : 'studentId',
		index : 'studentId',
		width : 120
	}, {
		name : 'classesName',
		index : 'classesName',
		width : 100
	},{
		name : 'assnName',
		index : 'assnName',
		width : 100
	},{
		name : 'studentName',
		width : 80
	},
	{
		name : 'adviserName',
		width : 150
	},{
		name : 'auditor',
		width : 60
	}, {
		name : 'auditTime',
		width : 150
	}, {
		name : 'status',
		width : 50,
		renderer : function(value) {
			return value=="1"?"通过":'退回';
		}
	}, {
		name : 'createTime',
		width : 150,
		sortable : true,
		editable : false
	}, {
		name : 'deiscri',
		index : 'deiscri',
		width : 150
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