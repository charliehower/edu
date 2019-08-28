var _colNames = [ '公开课编号', '共同体', '学年', '学科', '年级', '课时', '课型', '讲课时间','上课内容', '地点', '微题', '备课',
		'报名开始时间', '报名截止时间',  '申请老师', '状态', '创建时间' ];
var _colModel = function() {
	return [
			{
				name : 'publicClassId',
				width : 120,
				sorttype : "int",
				editable : true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'globleTitle',
				width : 120,
				hidden : true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			},
			{
				name : 'years',
				width : 100,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_19");
				},
				editoptions : {
					value : odparse("STATIC_DATA_19")
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				}
			},
			{
				name : 'discriplineId',
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
				}
			},
			{
				name : 'grade',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_14");
				},
				editoptions : {
					value : odparse("STATIC_DATA_14")
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				}
			},
			{
				name : 'ke',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_42");
				},
				editoptions : {
					value : odparse("STATIC_DATA_42")
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				}
			}
			,
			{
				name : 'model',
				width : 120,
				hidden : true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			},
{
			name : 'taskDate',
			width : 120,
			editable : true,
			edittype : "datebox",
			editoptions : {
				style : 'width:155px;height:30px'
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
				required : true
			},
			formoptions : {
				elmprefix : "",
				elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
			}
		},
		{
			name : 'title',
			width : 120,
			editable : true,
			editoptions : {
				size : "20",
				maxlength : "50"
			},
			editrules : {
				required : true
			},
			formoptions : {
				elmprefix : "",
				elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
			},
			edittype : "textarea",
			editoptions : {
				rows : "3",
				cols : "19"
			}
		}
	,
		{
			name : 'location',
			width : 120,
			editable : true,
			editoptions : {
				size : "20",
				maxlength : "25"
			},
			editrules : {
				required : true
			},
			formoptions : {
				elmprefix : "",
				elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
			},
			edittype : "textarea",
			editoptions : {
				rows : "3",
				cols : "19"
			}
		},
			{
				name : 'content',
				width : 120,
				hidden : true,
				editable : true,
				edittype : "textarea",
				editoptions : {
					rows : "3",
					cols : "19"
				}
			},
			{
				name : 'taskContent',
				width : 120,
				hidden : true,
				editable : true,
				edittype : "textarea",
				editoptions : {
					rows : "3",
					cols : "19"
				}
			},
			{
				name : 'regStartDate',
				width : 120,
				hidden : true,
				editable : true,
				edittype : "datebox",
				editoptions : {
					style : 'width:155px;height:30px'
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
					required : true
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				}
			},
			{
				name : 'regDeadline',
				width : 120,
				hidden : true,
				editable : true,
				edittype : "datebox",
				editoptions : {
					style : 'width:155px;height:30px'
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
					required : true
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				}
			},

			{
				name : 'teacherName',
				editable : false,
				width : 80
			}, {
				name : 'status',
				width : 60,
				editable : false,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_41");
				}
			}, {
				name : 'createTime',
				width : 200,
				sortable : true,
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