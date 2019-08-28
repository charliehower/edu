var _colNames = [ '序号', '业务竞赛名称', '学段', '学科', '竞赛组织单位', '获奖作品名称', '获奖级别 ',
		'获奖等级', '获奖时间', '类别', '申报人', '金额', '证明材料', '备注', '创建时间' ];
var _colModel = function() {
	return [
			{
				name : 'performanceId',
				width : 120,
				sorttype : "int",
				editable : true,
				editoptions : {
					size : "30",
					maxlength : "50",
					readonly : true
				}
			},
			{
				name : 'name',
				width : 100,
				editable : true,
				editoptions : {
					size : "30",
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
				name : 'section',
				width : 100,
				hidden:true,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_50");
				},
				editoptions : {
					value : odparse("STATIC_DATA_50")
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
				name : 'subjectId',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_16");
				},
				editoptions : {
					value : odparse("STATIC_DATA_16")
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
				name : 'organization',
				width : 100,
				editable : true,
				editoptions : {
					size : "30",
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
				name : 'wEntries',
				width : 100,
				editable : true,
				editoptions : {
					size : "30",
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
				name : 'wLevel',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_51");
				},
				editoptions : {
					value : odparse("STATIC_DATA_51")
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
				name : 'wGrade',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_52");
				},
				editoptions : {
					value : odparse("STATIC_DATA_52")
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
				name : 'wTime',
				width : 100,
				editable : true,
				edittype : "datebox",
				editoptions : {
					style : 'width:225px;height:30px'
				},
				dataoptions : {
					formatter : function(date) {
						var y = date.getFullYear();
						var m = date.getMonth() + 1;
						var d = date.getDate();
						return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
								+ (d < 10 ? ('0' + d) : d);
					},
					parser : function(s) {
						if (!s)
							return new Date();
						var ss = (s.split('-'));
						var y = parseInt(ss[0], 10);
						var m = parseInt(ss[1], 10);
						var d = parseInt(ss[2], 10);
						if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
							return new Date(y, m - 1, d);
						} else {
							return new Date();
						}
					}
				},
				renderer : function(value) {
					return value == null ? "" : value.substring(0, 10);
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
				name : 'category',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_53");
				},
				editoptions : {
					value : odparse("STATIC_DATA_53")
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
				name : 'regUserName',
				width : 60,
				editable : false
			},
			{
				name : 'money',
				width : 60,
				editable : true,
				editoptions : {
					size : "30",
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
				name : 'file',
				width : 100,
				hidden:true,
				editable : true,
				editoptions : {
					size : "30",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			}, {
				name : 'remark',
				width : 60,
				editable : true,
				editoptions : {
					size : "30",
					maxlength : "50"
				}
			}, {
				name : 'createTime',
				width : 100,
				editable : false
			} ];
}
function aceSwitch(cellvalue, options, cell) {
	// console.log('aceSwitch');
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