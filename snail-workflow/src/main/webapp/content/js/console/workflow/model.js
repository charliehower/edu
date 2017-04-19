var _colNames = [ '编号','key', '名称','版本'];
var _colModel = function() {
	return [
			{
				name : 'deploymentId',
				width : 80,
				sorttype : "int",
				editable : false,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'key',
				width : 100,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
			},{
				name : 'name',
				width : 400,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
			},{
				name : 'version',
				width : 150,
				editable : false,
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