var _colNames = [ '编号', '部门','标题','状态', '发布者', '发布时间',''];
var _colModel = function() {
	return [
			{
				name : 'dwmReportId',
				width : 100
			},
			{
				name : 'departmentName',
				width : 100
			},
			{
				name : 'title',
				width : 300,
				renderer : function(value,cur) {
					var id=$.jgrid.getAccessor(cur, 'dwmReportId');
					var title=$.jgrid.getAccessor(cur, 'title');
					return '<a href=javascript:dwmView(\''+id+'\',\''+title+'\')>'+value+'</a>';
				}
			},
			{
				name : 'status',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_22");
				},
				editoptions : {
					value : odparse("STATIC_DATA_22")
				}
			},{
				name : 'name',
				width : 60
			}, {
				name : 'publishTime',
				width : 120,
				sortable : true,
				editable : false
			},{
				name : 'publisher',
				hidden:true
			}];
	
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