jQuery(function($) {
	$('#tree-dept').tree(
					{
						checkbox : true,
						url : '/edu/group/selectGroupDepTreeByPid.do?pid=0',
						onBeforeExpand : function(node, param) {
							$('#tree-dept').tree('options').url = '/edu/group/selectGroupDepTreeByPid.do?pid='
									+ node.id;
						},
						onClick : function(node) {

						}
					});

		$('#tree-free').tree(
				{
					checkbox : true,
					url : '/edu/group/selectFreeGroupTreeRoot.do',
					onBeforeExpand : function(node, param) {
						$('#tree-free').tree('options').url ='/edu/group/selectFreeGroupTreeByPid.do?pid='
								+ node.id;
					},
					onClick : function(node) {

					}
				});

});
$("#btn-view-select-tmp").on('click', function(e) {
	e.preventDefault();
	getUsers();

});

function getUsers() {
	var html = new Array();
	var g = $('#combogrid-tmp').combogrid('grid'); // get datagrid object
	var r = g.datagrid('getSelected'); // get the selected row
	var isExit = false;
	if (r && r.MOBILE) {
		$.each($('user'), function(i, obj) {
			if ($(obj).attr("tel") == r.MOBILE) {
				alert("重复的手机号。");
				isExit = true;
				return;
			}
		});
		html.push('<div class="layout-user" >');
		html.push('<user tel="' + r.MOBILE + '" class="badge badge-'
				+ cssColor9[0] + '">');
		html.push(r.NAME);
		html.push('</user>');
		html.push('</div>');
		if (!isExit) {
			$('#task-content-tmp').html(
					$('#task-content-tmp').html() + html.join(''));
		}

	} else {
		alert("请选择人员且手机号不能为空。");
	}
}

$("#btn-view-remove").on('click', function(e) {
	e.preventDefault();
	$('#task-content').html('');
});
$("#btn-view-remove-last").on('click', function(e) {

	$('#task-content').find('div:last').remove();
});
$("#btn-view-remove-tmp").on('click', function(e) {
	e.preventDefault();
	$('#task-content-tmp').html('');
});
$("#btn-view-remove-tmp-last").on('click', function(e) {

	$('#task-content-tmp').find('div:last').remove();
});
$( "#btn-view-select" ).on('click', function(e) {
	e.preventDefault();
	
	var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
		modal: true,
		width:610,
		title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>发送人员</h4></div>",
		title_html: true,
		buttons: [ 
			
			{
				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
				"class" : "btn btn-info btn-xs",
				id:'ajax_button',
				click: function() {
					var html = new Array();
					var nodes = $('#tree-dept').tree('getChecked');
					$.each($(nodes),function(i,o){
						if(o.href!=''){
							html.push('<div class="layout-user" >');
							html.push('<user tel="'+o.href+'" class="badge badge-'+cssColor9[0]+'">');
							html.push(o.text);
							html.push('</user>');
							html.push('</div>');
						}
						
					});
					nodes = $('#tree-free').tree('getChecked');
					$.each($(nodes),function(i,o){
						if(o.href!=''){
							html.push('<div class="layout-user" >');
							html.push('<user tel="'+o.href+'" class="badge badge-'+cssColor9[0]+'">');
							html.push(o.text);
							html.push('</user>');
							html.push('</div>');
						}
					});
					$('#task-content').html(html.join('')+$('#task-content-tmp').html());
					$('#task-content-tmp').html('');
					$( this ).dialog( "close" ); 
				} 
			},
			{
				html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click: function() {
					$( this ).dialog( "close" ); 
				} 
			}
		]
	});
	
});