var _colNames = [ '教职工编号','姓名','职工类别', '性别', '身份证号', '手机号','Email', '状态','创建时间'];
var _colModel = function() {
	return [
			{
				name : 'teacherId',
				width : 80,
				sorttype : "int",
				editable : true,
				editoptions : {
					readonly : false
				}
			},
			{
				name : 'name',
				width : 120,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "25"
				}
			},
			{
				name : 'category',
				width : 60,
				editable : true,
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
				editable : true,
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
				width : 250,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "18"
				}
			},
			{
				name : 'tel',
				width : 150,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "11"
				}
			},
			{
				name : 'email',
				width : 150,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			},{
				name : 'stauts',
				width : 60,
				editable : false,
				edittype : "select",
				renderer : function(value) {
					 return rsd(value, "STATIC_DATA_36");
				}
			}
			, {
				name : 'createTime',
				width : 200,
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