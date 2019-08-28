var _colNames = [ '社团编号','类别','名称','指导老师', '人数', '开始时间', '截止时间','创建时间'];
var _colModel = function() {
	return [
			{
				name : 'assnId',
				width : 80,
				sorttype : "int",
				editable : true,
				editoptions : {
					readonly : false
				}
			},
			{
				name : 'categoryId',
				width : 100,
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_40");
				}
			},
			{
				name : 'assnName',
				width : 200
			},
			{
				name : 'adviserName',
				width : 60
			},
			{
				name : 'limitMax',
				width : 60
			},
			{
				name : 'regStartDate',
				width : 100
			},
			{
				name : 'regDeadline',
				width : 100
			}
			, {
				name : 'createTime',
				width : 150,
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