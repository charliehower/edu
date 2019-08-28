jQuery(function($) {
	

	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	
	
});
function style_ajax_button(btnId,status){
	console.log(status);
	var btn=$('#'+btnId);
	if(status){
		btn.find('i').removeClass('fa-check');
		btn.find('i').addClass('fa-spinner fa-spin');
		btn.attr('disabled',"true");
		
	}else{
		btn.find('i').removeClass('fa-spinner');
		btn.find('i').removeClass('fa-spin');
		btn.find('i').addClass('fa-check');
		btn.removeAttr("disabled");
	}
}

function updateForStatusByPrimaryKey(docFlowId){
	var groupId=$('input[name=groupId]').val();
	var departmentId=$('input[name=departmentId]').val();
	$.ajax({
		type : "get",
		url : contextPath + "/docFlow/updateForStatusByPrimaryKey.do",
		data:{docFlowId:docFlowId,status:'1',groupId:groupId,departmentId:departmentId},
		beforeSend : function(XMLHttpRequest) {
			style_ajax_button('btn-dialog-push',true);
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				$("#dialog-message" ).dialog( "close" );
				style_ajax_button('btn-dialog-push',false);
				alert(rst.errorMessage);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
				}).trigger("reloadGrid");
			}else{
				bootbox.dialog({
					title:'系统提示',
					message:rst.errorMessage,
					detail:rst.detail,
					buttons: 			
					{
						"success" :
						 {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback": function() {
								//$( this ).dialog( "close" );
							}
						}
					}
				});
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
		}
	});
}
function reloadGrid(){
	console.log('reloadGrid');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
	}).trigger("reloadGrid");
}