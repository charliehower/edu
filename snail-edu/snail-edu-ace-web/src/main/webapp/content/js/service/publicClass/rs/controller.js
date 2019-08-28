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

	$('#btn-view-detail').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
				parent.addPanel(r.title, contextPath
						+ '/dynamic/service/publicClass/preview.jsp?id=' + urlid+ "&publicClassId=" + r.publicClassId, true
						);
			});
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	$( "#btn-view-reg" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
		'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:660,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>评课</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						
						if(confirm("确定评课吗?")){
							var params = {};
							$.extend(params, {
								remark : $('#remark').val()
							});
							$.extend(params, {
								score : $('#score').val()
							});
							$.extend(params, {
								id : r.publicClassId
							});
							$.extend(params, {
								time : new Date()
							});
							$.ajax({
								type : "post",
								url : contextPath+"/publicClass/updateScore.do",
								data:params,
								beforeSend : function(XMLHttpRequest) {
									style_ajax_button('btn-assn',true);
								},
								success : function(rst, textStatus) {
									style_ajax_button('btn-assn',false);
									if (rst) {
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
									style_ajax_button('btn-assn',false);
								},
								error : function() {
									style_ajax_button('btn-assn',true);
								}
							});
						}
						
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
	function style_ajax_button(btnId, status) {
		console.log(status);
		var btn = $('#' + btnId);
		if (status) {
			btn.find('i').removeClass('fa-check');
			btn.find('i').addClass('fa-spinner fa-spin');
			btn.attr('disabled', "true");

		} else {
			btn.find('i').removeClass('fa-spinner');
			btn.find('i').removeClass('fa-spin');
			btn.find('i').addClass('glyphicon  glyphicon-check');
			btn.removeAttr("disabled");
		}
	}
});
