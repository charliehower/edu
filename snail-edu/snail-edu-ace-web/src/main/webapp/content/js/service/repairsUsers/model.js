var _colNames = [ '编号','报修类型', '姓名', '电话','负责区域','创建时间' ];
var _colModel = function() {
	return [ {
		name : 'repairsUsersId',
		index : 'repairsUsersId',
		width : 80,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'categoryId',
		index : 'categoryId',
		width : 100,
		editable : true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "STATIC_DATA_24");
		},
		editoptions : {
			value : odparse("STATIC_DATA_24")
		}
	}, {
		name : 'userId',
		width : 80,
		editable : true,
		edittype : "combogrid",
		sorttype : "int",
		dataoptions:{
			panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: portalContextPath+'/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:180},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]
		},
		editoptions : {
			style:'width:175px'
		},
		renderer : function(value,cur) {
			return $.jgrid.getAccessor(cur,'name');
		}
	},{
		name : 'tel',
		index : 'tel',
		width : 80,
		editable : false,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	},{
		name : 'subCategory',
		width : 150,
		editable : true,
		edittype : "combotree",
		dataoptions : {
			url : portalContextPath + '/location/selectLocationTreeList.do?pid=0&load=all',
            lines:false
           
		},
		editoptions : {
			style:'width:350px;line-height: 30px;height: 30px;',
			multiple:''
		},
		renderer : function(value, cur) {
			var userId=$.jgrid.getAccessor(cur, 'userId');
			var name=$.jgrid.getAccessor(cur, 'name');
			return '<a href="javascript:showDetail(\''+userId+'\',\''+name+'\')">负责区域</a>';
		}
	}, {
		name : 'createTime',
		width : 150,
		sortable : true,
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