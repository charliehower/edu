jQuery(function($) {
	if(edit){
		loadForm(dwmReportId);
		loadAttach(dwmReportId);
	}
	$('#btn-view-top').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
				'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext)
		}
		var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		if(systemUser.users.userId!=gd.publisher){
			alert("非本人发布内容不能操作。");
			return;
		}
		updateForTopByPrimaryKey(gd.dwmReportId);
	});
	$('#btn-view-push').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
				'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		//var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		if(systemUser.users.userId!=gd.publisher){
			alert("非本人发布内容不能操作。");
			return;
		}
		if (gd.status=='1') {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					'已发布过的'+cfg.caption);
					return;
		}
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:550,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> "+gd.title+"</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'btn-dialog-push',
					click: function() {
						updateForStatusByPrimaryKey(gd.dwmReportId);
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
				parent.addPanel('编辑',contextPath+'/dynamic/service/dwmReport/add.jsp?id='+urlid,true);
			});
	$('#btn-view-edit').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				//var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				if(systemUser.users.userId!=gd.publisher){
					alert("非本人发布内容不能操作。");
					return;
				}
				parent.addPanel('编辑',contextPath+'/dynamic/service/dwmReport/add.jsp?id='+urlid+'&dwmReportId='+gd.dwmReportId,true);
			});
	
	
	$('#btn-view-del').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				if(systemUser.users.userId!=gd.publisher){
					alert("非本人发布内容不能操作。");
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
	$('#btn-dwmReport-submit').on('click',function() {
		$('#fm-dwmReport').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
					
				});
				$.extend(params, {
					time : new Date()
				});
				if(params.content==''){
					params.content=CKEDITOR.instances.content.getData();
				}
				var url=contextPath +"/dwmReport/insertDwmReport.do"
				if(edit){
					url=contextPath +"/dwmReport/updateDwmReport.do"
				}
				console.log(params);
				$.ajax({
					type : "post",
					url : url,
					data:{jsons:JSON.stringify(params)},
					beforeSend : function(XMLHttpRequest) {
						style_ajax_button('btn-dwmReport-submit',true);
					},
					success : function(rst, textStatus) {
						style_ajax_button('btn-dwmReport-submit',false);
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
											//关闭窗口
											parent.reloadGrid();
											parent.removePanel();
											
										}
									}
								}
							});
							
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						style_ajax_button('btn-dwmReport-submit',false);
					},
					error : function() {
						style_ajax_button('btn-dwmReport-submit',true);
					}
				});
				return false;
			}
		});	
	
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
function loadForm(id){
	$.ajax({
		type : "get",
		url : contextPath + "/dwmReport/selectDwmReportByPrimaryKey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				$('#fm-dwmReport').form('load',rst.response);
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
function loadAttach(dwmReportId){
	$.ajax({
		type : "get",
		url : portalContextPath + "/attach/findAttachList.do",
		data:{dwmReportId:dwmReportId},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				var html=[];
				$.each(rst.list, function(n, file) {
					
					html.push('<div id="' + file.fileUrl + '"><a href="'+portalContextPath +'/files/download.do?collectionName=dwmReport&originalFilename='+file.fileName+'&fileName='+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.fileUrl+'\')"></a><b></b></div>');
				});
				//document.getElementById('filelist').innerHTML=html.join('');
				$('#filelist-history').html(html.join(''));
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
function deleteAttach(fileName){
	$.ajax({
		type : "get",
		url : portalContextPath + "/attach/deleteAttachByFileName.do",
		data:{fileName:fileName},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				loadAttach(dwmReportId);
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
function updateForTopByPrimaryKey(dwmReportId){
	$.ajax({
		type : "get",
		url : contextPath + "/dwmReport/updateForTopByPrimaryKey.do",
		data:{dwmReportId:dwmReportId},
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(rst, textStatus) {
			
			if(rst&&rst.state){
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
function updateForStatusByPrimaryKey(dwmReportId){
	var groupId=$('#groupId').combobox('getValue');
	var departmentId=$('#departmentId').combotree('getValue');
	$.ajax({
		type : "get",
		url : contextPath + "/dwmReport/updateForStatusByPrimaryKey.do",
		data:{dwmReportId:dwmReportId,status:'1',groupId:groupId,departmentId:departmentId},
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
function dwmView(dwmReportId,title){
	parent.addPanel(title,contextPath+'/dynamic/service/dwmReport/preview.jsp?id='+urlid+"&dwmReportId="+dwmReportId,true);
}
function reloadGrid(){
	console.log('reloadGrid');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
	}).trigger("reloadGrid");
}