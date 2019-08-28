var _colNames = ['序号','值班时间开始','值班时间结束','值班校长', '行政干部' ];
var _colModel = function() {
	return [{
		name : 'duty_id',
		width : 100,
		hidden:true,
		sortable : true,
		editable : false
	},
			{
				name : 'duty_start',
				width : 100,
				sortable : true,
				editable : false
			},{
				name : 'duty_end',
				width : 100,
				sortable : true,
				editable : false
			} ,{
				name : 'name1',
				width : 300,
				sortable : true,
				editable : false
			},{
				name : 'name2',
				width : 300,
				sortable : true,
				editable : false
			} ];
}
function aceSwitch(cellvalue, options, cell) {
	//console.log('aceSwitch');
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