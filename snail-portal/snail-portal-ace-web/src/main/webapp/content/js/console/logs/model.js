var _colNames = [ '编号','用户IP','名称','内容', '操作人', '对象', '更改前','更改后','时间' ];
var _colModel = function() {
	return [ {
		name : 'ID',
		index : 'ID',
		width : 60,
		sortable : false,
		editable : false
	},{
		name : 'IP',
		index : 'IP',
		width : 150,
		sortable : false,
		editable : false
	},{
		name : 'NAME',
		index : 'NAME',
		width : 60,
		sortable : false,
		editable : false
	},{
		name : 'LOG',
		index : 'LOG',
		width : 200,
		sortable : false,
		editable : false
	},{
		name : 'USER_NAME',
		index : 'USER_NAME',
		width : 60,
		sortable : false,
		editable : false
	},{
		name : 'OBJECT_VALUE',
		index : 'OBJECT_VALUE',
		width : 60,
		sortable : false,
		editable : false
	},{
		name : 'OLD',
		index : 'OLD',
		width : 60,
		sortable : false,
		editable : false
	},{
		name : 'NEWS',
		index : 'NEWS',
		width : 60,
		sortable : false,
		editable : false
	}, {
		name : 'CREATE_TIME',
		width : 150,
		sortable : true,
		editable : false
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