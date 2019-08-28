jQuery(function($) {
	$( "#btn-view-remove" ).on('click', function(e) {
		e.preventDefault();
		$('#task-content').html('');
	});
	$( "#btn-view-remove-last" ).on('click', function(e) {
		e.preventDefault();
		$('#task-content').find('div:last').remove();
	});

	$( "#btn-view-select-tmp" ).on('click', function(e) {
		e.preventDefault();
		selectItems();
		
	});
	$('#combogrid-tmp').combogrid({
		onSelect: function(index,row){
			selectItems();
		}
	});
	function selectItems(){
		var html = new Array();
		var g = $('#combogrid-tmp').combogrid('grid');	// get datagrid object
		var r = g.datagrid('getSelected');	// get the selected row
		var isExit=false;
		if(r&&r.USER_ID){
			$.each($('user'),function(i,obj){
				if($(obj).attr("data")==r.USER_ID){
					alert("重复的编号。");
					isExit=true;
					return;
				}
			});
			html.push('<div class="layout-user" >');
			html.push('<user data="'+r.USER_ID+'" class="badge badge-'+cssColor9[0]+'">');
			html.push(r.NAME);
			html.push('</user>');
			html.push('</div>');
			if(!isExit){
				$('#task-content-tmp').html($('#task-content-tmp').html()+html.join(''));
			}
			
		}else{
			alert("请选择人员且编号不能为空。");
		}
	}
	$( "#btn-view-select" ).on('click', function(e) {
		e.preventDefault();
		
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:610,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>社团成员</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						
						$('#task-content').html($('#task-content').html()+$('#task-content-tmp').html());
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
	
	
	$( "#btn-view-remove-tmp" ).on('click', function(e) {
		e.preventDefault();
		$('#task-content-tmp').html('');
	});
	$( "#btn-view-remove-tmp-last" ).on('click', function(e) {
		
		$('#task-content-tmp').find('div:last').remove();
	});

});