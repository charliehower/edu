var _colNames = [ '公文编号', '标题','状态', '批复','批复人','批复时间','发布时间'];
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
					var id=$.jgrid.getAccessor(cur, "docFlowId");
					return '<a href="javascript:window.open(\'../docFlow/preview.jsp?docFlowId='+id+'\')">'+value+'</a>';
				}
			},
			{
				name : 'piStatus',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_57");
				},
				editoptions : {
					value : odparse("STATIC_DATA_57")
				}
			},{
				name : 'piContent',
				width : 120
			},{
				name : 'name',
				width : 60
			}, {
				name : 'createTime',
				width : 120,
				sortable : true,
				editable : false
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