jQuery(function($) {
	$( "#btn-view-remove-two" ).on('click', function(e) {
		e.preventDefault();
		$('#task-content-two').html('');
	});
	$( "#btn-view-remove-last-two" ).on('click', function(e) {
		e.preventDefault();
		$('#task-content-two').find('div:last').remove();
	});

	$( "#btn-view-select-tmp-two" ).on('click', function(e) {
		e.preventDefault();
		selectItemstwo();
		
	});
	$('#combogrid-tmp-two').combogrid({
		onSelect: function(index,row){
			selectItemstwo();
		}
	});
	function selectItemstwo(){
		var html = new Array();
		var g = $('#combogrid-tmp-two').combogrid('grid');	// get datagrid object
		var r = g.datagrid('getSelected');	// get the selected row
		var isExit=false;
		if(r&&r.USER_ID){
			$.each($('usertwo'),function(i,obj){
				if($(obj).attr("data")==r.USER_ID){
					alert("重复的编号。");
					isExit=true;
					return;
				}
			});
			html.push('<div class="layout-user" >');
			html.push('<usertwo data="'+r.USER_ID+'" class="badge badge-'+cssColor9[0]+'">');
			html.push(r.NAME);
			html.push('</usertwo>');
			html.push('</div>');
			if(!isExit){
				$('#task-content-tmp-two').html($('#task-content-tmp-two').html()+html.join(''));
			}
			
		}else{
			alert("请选择人员且编号不能为空。");
		}
	}
	$( "#btn-view-select-two" ).on('click', function(e) {
		e.preventDefault();
		
		var dialog = $( "#dialog-message-two" ).removeClass('hide').dialog({
			modal: true,
			width:610,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>社长</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						
						$('#task-content-two').html($('#task-content-two').html()+$('#task-content-tmp-two').html());
						$('#task-content-tmp-two').html('');
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
	
	
	$( "#btn-view-remove-tmp-two" ).on('click', function(e) {
		e.preventDefault();
		$('#task-content-tmp-two').html('');
	});
	$( "#btn-view-remove-tmp-last-two" ).on('click', function(e) {
		
		$('#task-content-tmp-two').find('div:last').remove();
	});

});