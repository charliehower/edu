
var _colNames = [ '编号', '请假人', '请假类型', '开始时间', '截止时间', '请假天数', '状态', '申请时间','销假', '备注'];
var _colModel = function() {
	return [

	{
		name : 'leaveId',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	},

	{
		name : 'leaveUserName',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	},

	{
		name : 'categoryName',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	},

	{
		name : 'timeStart',
		width : 130,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	},

	{
		name : 'timeEnd',
		width : 130,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	},

	{
		name : 'leaveDates',
		width : 80,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		},
		renderer : function(value) {
			var n=value/8;
			n=Math.round(n*100)/100;
			return n;
		}
	}, {
		name : 'status',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		},
		renderer : function(value) {
			return rsd(value, "STATIC_DATA_29");
		}
	},

	{
		name : 'applicationTime',
		width : 130,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	}, {
		name : 'auditStatus',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		},
		renderer : function(value) {
			return value=="1"?"是":"否";
		}
	}, {
		name : 'auditRemark',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		},
		renderer : function(value) {
			return value;
		}
	} ];
}
function aceSwitch(cellvalue, options, cell) {
	console.log('aceSwitch');
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