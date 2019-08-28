var _colNames = ['值班时间开始','值班时间结束','值班人', '联系电话','操作' ];
var _colModel = function() {
	return [
			{
				name : 'dutyStart',
				width : 150,
				sortable : true,
				editable : false
			},{
				name : 'dutyEnd',
				width : 150,
				sortable : true,
				editable : false
			} ,{
				name : 'name',
				width : 100,
				sortable : true,
				editable : false,
				renderer : function(value,cur) {
					var id=$.jgrid.getAccessor(cur, "dutyDetailId");
					return '<a href="javascript:window.open(\'print.jsp?id='+id+'\')">'+value+'</a>';
				}
				
			},{
				name : 'tel',
				width : 100,
				sortable : true,
				editable : false
			},{
				name : 'dutyDetailId',
				width : 100,
				sortable : true,
				editable : false,
				renderer : function(value,cur) {
					var id=$.jgrid.getAccessor(cur, "dutyDetailId");
					return '<a href="javascript:window.open(\'print.jsp?id='+id+'\')">查看</a>';
				}
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