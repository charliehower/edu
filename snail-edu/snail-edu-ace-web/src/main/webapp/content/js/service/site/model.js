var _colNames = [ '序号', '场地名称', '提前申请天数', '是否锁定', '锁定原因', '开放开始',
		'分钟', '开放结束', '分钟', '周末开放', '创建时间' ];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 120,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readonly : true
				}
			},
			{
				name : 'name',
				width : 200,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'advance',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			 {
				name : 'status',
				width : 60,
				editable : true,
				hidden:false,
				edittype : "checkbox",
				editoptions : {
					value : "1:0"
				},
				unformat : aceSwitch,
				renderer : function(value) {
					var rst = "";
					switch (value) {
					case '1':
						rst = "YES";
						break;
					case '0':
						rst = "NO";
						break;
					default:
						rst = "N/A";
					}
					return rst;
				}
			},
			{
				name : 'reason',
				width : 200,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			},
			{
				name : 'startHh',
				width : 80,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_54");
				},
				editoptions : {
					value : odparse("STATIC_DATA_54")
				}
			},
			{
				name : 'startMm',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_55");
				},
				editoptions : {
					value : odparse("STATIC_DATA_55")
				}
			},
			{
				name : 'endHh',
				width : 80,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_54");
				},
				editoptions : {
					value : odparse("STATIC_DATA_54")
				}
			},
			{
				name : 'endMm',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_55");
				},
				editoptions : {
					value : odparse("STATIC_DATA_55")
				}
			},
			{
				name : 'flag',
				width : 80,
				editable : true,
				hidden:false,
				edittype : "checkbox",
				editoptions : {
					value : "1:0"
				},
				unformat : aceSwitch,
				renderer : function(value) {
					var rst = "";
					switch (value) {
					case '1':
						rst = "YES";
						break;
					case '0':
						rst = "NO";
						break;
					default:
						rst = "N/A";
					}
					return rst;
				}
			},
			{
				name : 'createTime',
				width : 150,
				editable :false
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