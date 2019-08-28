var _colNames = [ '公文编号', '标题','状态', '发布者','发布时间'];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 100
			},
			{
				name : 'title',
				width : 300,
				renderer : function(value,cur) {
					var id=$.jgrid.getAccessor(cur, "id");
					return '<a href="javascript:window.open(\'../docFlow/preview.jsp?docFlowId='+id+'\')">'+value+'</a>';
				}
			},
			{
				name : 'status',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_56");
				},
				editoptions : {
					value : odparse("STATIC_DATA_56")
				}
			},{
				name : 'name',
				width : 60
			}, {
				name : 'deployDate',
				width : 120,
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