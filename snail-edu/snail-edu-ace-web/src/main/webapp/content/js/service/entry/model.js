var _colNames = [ '教职工编号','姓名','职工类别', '性别', '身份证号',   '手机号','Email',  '创建时间','入职时间' ,'所属部门','所属年级','所属班级','所属学科','职务','备注'];
var _colModel = function() {
	return [
			{
				name : 'teacherId',
				width : 80,
				sorttype : "int",
				editable : true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'name',
				width : 120,
				editable : true,
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
				width : 250,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "18"
				}
			},
			{
				name : 'tel',
				width : 150,
				hidden:true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "11"
				}
			},
			{
				name : 'email',
				width : 150,
				hidden:true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}
			, {
				name : 'createTime',
				width : 200,
				sortable : true,
				editable : false
			},
			{
				name : 'enteryTime',
				width : 120,
				hidden:true,
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
				}
			},{
				name : 'departmentId',
				width : 150,
				editable : true,
				hidden:true,
				edittype : "combotree",
				dataoptions : {
					url : portalContextPath + '/system/selectDepartmentTreeList.do?id=01',
					animate: true,
	                 lines:false
				},
				editoptions : {
					style:'width:175px;line-height: 30px;height: 30px;'
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'departmentName');
				}
			},{
				name : 'gradeId',
				width : 175,
				editable : true,
				hidden:true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_14");
				},
				editoptions : {
					value : odparse("STATIC_DATA_14"),
					style : 'width:175px;height:30px'
				}
			},{
				name : 'classesTaught',
				width : 175,
				editable : true,
				hidden:true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_15");
				},
				editoptions : {
					value : odparse("STATIC_DATA_15"),
					style : 'width:175px;height:30px'
				}
			},{
				name : 'disciplineId',
				width : 175,
				editable : true,
				hidden:true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_16");
				},
				editoptions : {
					value : odparse("STATIC_DATA_16"),
					style : 'width:175px;height:30px'
				}
			},{
				name : 'position',
				width : 120,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "25",
					readonly : false
				}
			},{
				name : 'remark',
				width : 120,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "25",
					readonly : false
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