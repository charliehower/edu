var _colNames = [ '序号', '填报名称', '申报人', '开始时间', '截止时间', '应填报人数', '填报对象', '操作',
		 '创建时间' ];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 120,
				sorttype : "int",
				editable : true,
				editoptions : {
					size : "30",
					maxlength : "50",
					readonly : true,
					style : 'width:155px;height:30px'
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
				name : 'userName',
				width : 60,
				editable : false
			},
			{
				name : 'startTime',
				width : 100,
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
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'deadline',
				width : 100,
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
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'totals',
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
				name : 'targets',
				width : 120,
				editable : true,
				hidden:true,
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
				name : 'files',
				width : 150,
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
				},
				renderer:function(fileName,cur){
					 var self=portalContextPath+'/excelMain/exportXls.do?action=false&id='+$.jgrid.getAccessor(cur, 'id');
					 var src=portalContextPath+'/files/download.do?collectionName=excel&originalFilename='+fileName+'&fileName='+fileName;
					 //console.log("<a class='ace-icon glyphicon glyphicon-upload bigger-110' href='javascript:importXls(\'"+$.jgrid.getAccessor(cur, 'id')+"\')' target='_blank'>上报</a>  <a href="+src+" target='_blank'>下载模板</a>");
					 return "<a class='ace-icon glyphicon glyphicon-upload bigger-110' href='javascript:importXls(\""+$.jgrid.getAccessor(cur, 'id')+"\")' target='_blank'>上报</a>  <a class='ace-icon glyphicon glyphicon-dowlond bigger-110' href="+src+" target='_blank'>下载模板</a> <a class='ace-icon glyphicon glyphicon-dowlond bigger-110' href="+self+" target='_blank'>下载数据</a>";
				}
			},
			{
				name : 'createTime',
				width : 120,
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