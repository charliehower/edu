var _colNames = [ '编号','报修时间','报修类型', '分类型','报修人', '部门','报修地点','具体情况','电话','状态','维修人','维修时间','故障类型','故障描述','评价' ];
var _colModel = function() {
	return [ {
		name : 'repairsId',
		index : 'repairsId',
		width : 130,
		sortable : false,
		editable : false
	}, {
		name : 'repairsTime',
		width : 150,
		sortable : true,
		editable : false
	}, {
		name : 'repairsCategoryName',
		index : 'repairsCategoryName',
		width : 100,
		sortable : false,
		editable : false
	}, {
		name : 'subCategoryName',
		index : 'subCategoryName',
		width : 80,
		sortable : false,
		editable : false
	}, {
		name : 'alertsUserName',
		index : 'alertsUserName',
		width : 80,
		sortable : false,
		editable : false
	}, {
		name : 'departmentName',
		index : 'departmentName',
		width : 80,
		sortable : false,
		editable : false
	}, {
		name : 'fullName',
		index : 'fullName',
		width : 300,
		sortable : false,
		editable : false
	}, {
		name : 'describtion',
		index : 'describtion',
		width : 100,
		hidden : true,
		editable : false
	}, {
		name : 'tel',
		index : 'tel',
		width : 100,
		hidden : true,
		editable : false
	}, {
		name : 'stautsName',
		index : 'stautsName',
		width : 100,
		sortable : false,
		editable : false
	}, {
		name : 'repairsUserName',
		width : 80,
		sortable : true,
		editable : false
	}, {
		name : 'responseTime',
		width : 150,
		sortable : true,
		editable : false
	},{
		name : 'faultCategoryName',
		width : 150,
		hidden : true,
		editable : false
	},{
		name : 'faultDescribtion',
		width : 150,
		hidden : true,
		editable : false
	}, {
		name : 'remark2',
		width : 150,
		hidden : true,
		editable : false
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