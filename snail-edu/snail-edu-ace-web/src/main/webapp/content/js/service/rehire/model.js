var _colNames = [ '教职工编号', '流程编号','姓名','类别', '性别', '身份证号','返聘原因','返聘时间','审批完成时间','审批领导' ];
var _colModel = function() {
	return [
			{
				name : 'teacherId',
				width : 120,
				sorttype : "int",
				editable : false,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'instanceId',
				width : 120,
				sorttype : "int",
				editable : false,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'name',
				width : 120,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "25",
					readonly : true
				}
			},
			{
				name : 'category',
				width : 60,
				editable : false,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_07");
				},
				editoptions : {
					value : odparse("STATIC_DATA_07")
				}
			},
			{
				name : 'sex',
				width : 60,
				editable : false,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_01");
				},
				editoptions : {
					value : odparse("STATIC_DATA_01")
				}
			},
			{
				name : 'idCard',
				width : 180,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "18"
				}
			},
			{
				name : 'reasion',
				width : 100,
				editable : false
			},{
				name : 'rehireTime',
				width : 120,
				sortable : true,
				editable : false,
				renderer : function(value) {
					return value == null ? "" : value.substring(0, 10);
				}
			},{
				name : 'auditTime',
				width : 120,
				sortable : true,
				editable : false,
				renderer : function(value) {
					return value == null ? "" : value.substring(0, 10);
				}
			},{
				name : 'leader',
				width : 200,
				sortable : true,
				editable : false
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