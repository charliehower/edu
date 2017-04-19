var _colNames = [ '报表编号', '报表名称'];
var _colModel = function() {
	return [
			{
				name : 'reportId',
				width : 80,
				sorttype : "int",
				editable : true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'reportName',
				width : 500,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
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