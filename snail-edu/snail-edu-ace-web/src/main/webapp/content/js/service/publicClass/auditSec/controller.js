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
	$('#btn-view-audit').on('click', function() {
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:600,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 审核</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 审核",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						var params = {status:$('#dialog-message input[name="status"]:checked ').val(),remark:$('#dialog-message input[name="remark"]').val()};
						audit(params);
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
function audit(params) {
	console.log(params);
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
	if (!gr) {
		$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
		return;
	}
	var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
	if (r.status != '02') {
		alert("待审核的记录才可以审核！");
		return;
	}
	if (confirm("确定要审核码？")) {
		$
				.ajax({
					type : "post",
					url : contextPath + "/publicClass/updateAuditSec.do",
					data : {
						id : r.publicClassId,
						status : params.status,
						remark:params.remark
					},
					beforeSend : function(XMLHttpRequest) {
						style_ajax_button('ajax_button', true);
					},
					success : function(rst, textStatus) {
						style_ajax_button('ajax_button', false);
						jQuery(cfg.grid_selector).jqGrid('setGridParam', {})
								.trigger("reloadGrid");
						if (rst) {
							bootbox
									.dialog({
										title : '系统提示',
										message : rst.errorMessage,
										detail : rst.detail,
										buttons : {
											"success" : {
												"label" : "<i class='ace-icon fa fa-check'></i>确定",
												"className" : "btn-sm btn-success",
												"callback" : function() {
													 $( "#dialog-message" ).dialog( "close" );
												}
											}
										}
									});
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						style_ajax_button('ajax_button', false);
					},
					error : function() {
						style_ajax_button('ajax_button', true);
					}
				});
	}
}