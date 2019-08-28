jQuery(function($) {
	$.fn.spin = function(opts) {
		this.each(function() {
		  var $this = $(this),
			  data = $this.data();

		  if (data.spinner) {
			data.spinner.stop();
			delete data.spinner;
		  }
		  if (opts !== false) {
			data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
		  }
		});
		return this;
	};

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

	$('#btn-view-add').on(
			'click',
			function() {
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						'new',
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : false,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
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
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	$( "#btn-view-da" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
		'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		var groupId=r.groupId;
		
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:610,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>自由组成员</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 保存",
					"class" : "btn btn-info btn-xs",
					id:'btn-view-submit',
					click: function() {
						batchSaveGroupUsersByUserIds(groupId);
						
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
		selectFreeGroupUsersListByGorupId(groupId);
		
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

function batchSaveGroupUsersByUserIds(groupId){
	var users=new Array();
	$.each($('user'),function(i,obj){
		users.push($(obj).attr("id"));
	});
	
	var json={};
	json.groupId=groupId;
	json.list=users;
	var jsons=JSON.stringify(json);
	$.ajax({
		type : "post",
		url : contextPath + "/group/batchSaveGroupUsersByUserIds.do",
		data:{jsons:jsons},
		beforeSend : function(XMLHttpRequest) {
			style_ajax_button('btn-view-submit',true);
		},
		success : function(rst, textStatus) {
			style_ajax_button('btn-view-submit',false);
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
								$("#dialog-message").dialog( "close" ); 
							}
						}
					}
				});
		
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			style_ajax_button('btn-view-submit',false);
		},
		error : function() {
			style_ajax_button('btn-view-submit',true);
		}
	});
}
function selectFreeGroupUsersListByGorupId(groupId){
	$.ajax({
		type : "post",
		url : contextPath + "/group/selectFreeGroupUsersListByGorupId.do",
		data:{groupId:groupId},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var html=new Array();
			$.each($(rst.list),function(i,o){
				html.push('<div class="layout-user" >');
				html.push('<user id="'+o.USER_ID+'" class="badge badge-'+cssColor9[0]+'">');
				html.push(o.NAME);
				html.push('</user>');
				html.push('</div>');
			});
			$('#task-content-tmp').html(html.join(''));
			//alert(html.join(''));
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
}