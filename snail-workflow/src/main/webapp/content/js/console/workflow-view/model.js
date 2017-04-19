var _colNames = [ 'ID', '名称','参与者','开始时间','结束时间','状态'];
var _colModel = function() {
	return [
			{
				name : 'ID_',
				width : 150,
				sorttype : "string",
				editable : false,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'OBJNAME_',
				width : 200,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
			},{
				name : 'NAME',
				width : 100,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
			},{
				name : 'START_',
				width : 150,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
			},{
				name : 'END_',
				width : 150,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
			},{
				name : 'STATE_',
				width : 100,
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