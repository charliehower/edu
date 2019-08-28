jQuery(function($) {
	if(edit){
		loadForm(id);
		loadAttach(id);
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
		var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr)
		updateForTopByPrimaryKey(gd.docFlowId);
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
				parent.addPanel('公文编辑',contextPath+'/dynamic/service/docFlow/add.jsp?urlid='+urlid,true);
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
				var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr)
				parent.addPanel('公文编辑',contextPath+'/dynamic/service/docFlow/add.jsp?urlid='+urlid+'&id='+gd.id,true);
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
	$('#btn-docFlow-submit').on('click',function() {
		$('#fm-docFlow').ajaxForm({
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
				var url=contextPath +"/docFlow/insertDocFlow.do"
				if(edit){
					url=contextPath +"/docFlow/updateDocFlow.do"
				}
				console.log(params);
				$.ajax({
					type : "post",
					url : url,
					data:{jsons:JSON.stringify(params)},
					beforeSend : function(XMLHttpRequest) {
						style_ajax_button('btn-docFlow-submit',true);
					},
					success : function(rst, textStatus) {
						style_ajax_button('btn-docFlow-submit',false);
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
						style_ajax_button('btn-docFlow-submit',false);
					},
					error : function() {
						style_ajax_button('btn-docFlow-submit',true);
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
		url : contextPath + "/docFlow/selectDocFlowByPrimaryKey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				$('#fm-docFlow').form('load',rst.response);
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
function loadAttach(docFlowId){
	$.ajax({
		type : "get",
		url : portalContextPath + "/attach/findAttachList.do",
		data:{noticeId:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				var html=[];
				$.each(rst.list, function(n, file) {
					
					html.push('<div id="' + file.fileUrl + '" class="fileList0"><a href="'+portalContextPath +'/files/download.do?collectionName=docFlow&originalFilename='+file.fileName+'&fileName='+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.fileUrl+'\')"></a><b></b></div>');
					//html.push('<hr>');
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
				loadAttach(docFlowId);
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