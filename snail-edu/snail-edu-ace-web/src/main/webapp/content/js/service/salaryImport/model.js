var _colNames = [ '序号', '类别', '发放年度', '发放月份', '文件', '操作人员', '入库时间' ];
var _colModel = function() {
	return [ {
		name : 'salaryImportId',
		width : 100,
		hidden : false,
		sortable : true,
		editable : false
	}, {
		name : 'category',
		width : 100,
		sortable : true,
		editable : false,
		renderer : function(value) {
			if(value=="b"){
				return "正式职工";
			}else if(value=="c"){
				return "正式职工（新）";
			}else{
				return "临聘职工";
			}
			
		}
	}, {
		name : 'year',
		width : 60,
		sortable : true,
		editable : false
	}, {
		name : 'month',
		width : 60,
		sortable : true,
		editable : false
	}, {
		name : 'title',
		width : 200,
		sortable : true,
		editable : false
	}, {
		name : 'name',
		width : 100,
		sortable : true,
		editable : false
	}, {
		name : 'createTime',
		width : 150,
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