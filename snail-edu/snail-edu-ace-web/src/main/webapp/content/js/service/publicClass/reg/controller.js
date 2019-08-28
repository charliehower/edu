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

	$('#btn-view-edit').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
				}
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						gr,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	
	$('#btn-view-add').on(
			'click',
			function() {
				
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						'new',
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	
	
	$('#btn-view-del').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				jQuery(cfg.grid_selector).jqGrid(
						'delGridRow',
						gr,
						{
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	$('#btn-view-release').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				if(r.status!='02'){
					alert("审核过的申请才可以发布！");
					return;
				}
				if(confirm("确定要发布码？")){
					$.ajax({
						type : "post",
						url : contextPath + "/publicClass/updateRelease.do",
						data:{id:r.publicClassId,status:'03'},
						beforeSend : function(XMLHttpRequest) {
							style_ajax_button('btn-view-quit',true);
						},
						success : function(rst, textStatus) {
							style_ajax_button('btn-view-quit',false);
							jQuery(cfg.grid_selector).jqGrid('setGridParam', {
							}).trigger("reloadGrid");
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
							style_ajax_button('btn-view-quit',false);
						},
						error : function() {
							style_ajax_button('btn-view-quit',true);
						}
					});
				}
				
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
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
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
});