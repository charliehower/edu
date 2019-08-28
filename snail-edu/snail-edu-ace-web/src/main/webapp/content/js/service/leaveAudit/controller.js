var noticeId;
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
	
	$('#fm-workflow').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			var params = {};
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			$.extend(params, {
				time : new Date()
			});
			console.log(params);
			$.ajax({
				type : "post",
				url : contextPath + "/leave/updateAuditById.do",
				data:params,
				beforeSend : function(XMLHttpRequest) {
					style_ajax_button('ajax_button',true);
				},
				success : function(rst, textStatus) {
					style_ajax_button('ajax_button',false);
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
					style_ajax_button('ajax_button',false);
				},
				error : function() {
					style_ajax_button('ajax_button',true);
				}
			});
			return false;
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
				var rd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				parent.addPanel('请假申请查询','/workflow/dynamic/console/workflow-view/index-view-leave.jsp?instanceId='+rd.leaveId,true);
			});
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	$( "#btn-view-audit" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		// console.log(r);
		noticeId=r.leaveId;
		loadAttach(noticeId);
		 initUpload();
		$("#fm-workflow").find("input[name=leaveId]").val(r.leaveId);
		var ajax_button;
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:500,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 销假</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						$("#fm-workflow").submit();
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
function loadAttach(noticeId){
	$.ajax({
		type : "get",
		url : portalContextPath + "/attach/findAttachList.do",
		data:{noticeId:noticeId},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				var html=[];
				$.each(rst.list, function(n, file) {
					
					html.push('<div id="' + file.fileUrl + '"><a href="'+contextPath +'/files/download.do?collectionName=notice&originalFilename='+file.fileName+'&fileName='+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.fileUrl+'\')"></a><b></b></div>');
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
				loadAttach(noticeId);
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
