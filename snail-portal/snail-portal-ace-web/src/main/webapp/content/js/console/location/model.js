var _colNames = [ '位置编号', '上级位置编号', '位置名称','位置全称',  '创建时间' ];
var _colModel = function() {
	return [ {
		name : 'locationId',
		index : 'id',
		width : 100,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'parentId',
		width : 100,
		editable : true,
		sorttype : "int"
	}, {
		name : 'name',
		index : 'name',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	}, {
		name : 'fullName',
		index : 'fullName',
		width : 350,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "250"
		}
	}, {
		name : 'createTime',
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