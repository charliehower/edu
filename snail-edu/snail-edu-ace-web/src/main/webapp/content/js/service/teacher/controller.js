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
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
				var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
							
				}
				parent.addPanel('教职工信息变更',contextPath+'/dynamic/service/teacher/baseInfo.jsp?id='+urlid+"&teacherId="+r.teacherId,true);
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
	$('#btn-view-quit').on(
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
				if(confirm("确定要离职操作码？")){
					$.ajax({
						type : "post",
						url : contextPath + "/teacher/updateQuitByIdTeacherId.do",
						data:{teacherId:r.teacherId},
						beforeSend : function(XMLHttpRequest) {
							style_ajax_button('btn-view-quit',true);
						},
						success : function(rst, textStatus) {
							style_ajax_button('btn-view-quit',false);
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
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	$('#btn-view-add-account').on(
			'click',
			function() {
				var params={};
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				var selectedIds = jQuery(cfg.grid_selector).jqGrid("getGridParam", "selarrrow");
				var list=new Array();
				for(var i=0;i<selectedIds.length;i++){
					var rd = jQuery(cfg.grid_selector).jqGrid("getRowData", selectedIds[i]);		
					var obj={teacherId:rd.teacherId,name:rd.name};
					list.push(obj);
				}
				params.list=list;
				console.log(params);
				$( "#dialog-confirm" ).removeClass('hide').dialog({
					resizable: false,
					modal: false,
					title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认创建账户吗?</h4></div>",
					title_html: true,
					buttons: [
						{
							html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
							id:'btn-add-account',
							"class" : "btn btn-info btn-xs",
							click: function() {
								$( this ).dialog( "close" ); 
								$.ajax({
									type : "post",
									url : contextPath + "/teacher/createUsersByTeacherIds.do",
									data:{jsons:JSON.stringify(params)},
									beforeSend : function(XMLHttpRequest) {
										style_ajax_button('btn-add-account',true);
									},
									success : function(rst, textStatus) {
										style_ajax_button('btn-add-account',false);
										if (rst) {
											jQuery(cfg.grid_selector).jqGrid('setGridParam', {
												page : 1,
												postData : params
											}).trigger("reloadGrid");
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
										style_ajax_button('btn-add-account',false);
									},
									error : function() {
										style_ajax_button('btn-add-account',true);
									}
								});
							}
						}
						,
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
	$( "#btn-view-import" ).on('click', function(e) {
		e.preventDefault();
		reset_uploader();
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:750,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 教职工导入</h4></div>",
			title_html: true,
			buttons: [ 
				
				/*{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 重置",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						reset_uploader();				
						
					} 
				},*/
				{
					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 关闭",
					"class" : "btn btn-xs",
					click: function() {
						$( this ).dialog( "close" ); 
					} 
				}
			]
		});
		
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
});