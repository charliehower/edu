var _colNames = [ '编号','所属班级', '姓名', '性别', '身份证号', '出生日期', '家长姓名', '电话', '创建时间', '备注' ];
var _colModel = function() {
	return [ {
		name : 'studentId',
		index : 'studentId',
		width : 100,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : false
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'classesId',
		index : 'classesId',
		width : 100,
		editable : true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "STATIC_DATA_15");
		},
		editoptions : {
			value : odparse("STATIC_DATA_15")
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	},{
		name : 'name',
		index : 'name',
		width : 200,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	},{
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
		width : 150,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "18"
		}
	},{
		name : 'birthday',
		width : 120,
		editable : true,
		edittype : "datebox",
		editoptions : {
			style : 'width:175px;height:30px'
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
		editrules : {
			required : false
		}
	}, {
		name : 'householder',
		width : 150,
		hidden:true,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "50"
		}
	}, {
		name : 'tel',
		width : 150,
		hidden:true,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "11"
		}
	}, {
		name : 'createTime',
		width : 150,
		sortable : true,
		editable : false
	}, {
		name : 'remark',
		index : 'remark',
		width : 150,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
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