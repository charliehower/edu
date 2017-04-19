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

	/*$('#btn-view-add').on(
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
			});*/
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
	$( "#btn-view-add" ).on('click', function(e) {
		e.preventDefault();
		reset_uploader();
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:750,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 流程部署</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 重置",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						reset_uploader();				
						
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

	$( "#btn-view-da-del" ).on('click', function(e) {
		var gr = jQuery('#myrole-grid-table').jqGrid('getGridParam','selrow');
		if (!gr) {
			bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i>请选择要移除的角色!</h4></div>", function(result) {
				
			});
			return;
		}
		var srows=jQuery('#myrole-grid-table').jqGrid('getGridParam','selarrrow');		
		$.each(srows,function(i,o){
			jQuery("#myrole-grid-table").jqGrid('delRowData',o);
		});
		$.each(srows,function(i,o){
			jQuery("#myrole-grid-table").jqGrid('delRowData',o);
		});
	});
	/*function style_ajax_button(btn,status){
		//console.log(status);
		if(status){
			$('#'+btn).find('i').removeClass('fa-check');
			$('#'+btn).find('i').addClass('fa-spinner fa-spin');
		}else{
			$('#'+btn).find('i').removeClass('fa-spinner');
			$('#'+btn).find('i').removeClass('fa-spin');
			$('#'+btn).find('i').addClass('fa-check');
		}
		$('#'+btn).disabled=status;
	}*/
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

jQuery(function($) {
	init_uploader();
});

function init_uploader(){
	 $("#uploader").pluploadQueue({
			runtimes : 'html5,flash,silverlight,html4',
	        chunk_size : '1mb',
	        unique_names : true,
	        multipart_params:{id:'EC12395876'}, 
	        filters : {
	            max_file_size : '10mb',
	            mime_types: [
	                //{title : "Image files", extensions : "jpg,gif,png"},
	                {title : "Zip files", extensions : "zip"}
	            ]
	        },
	 
	        // Resize images on clientside if we can
	        resize : {width : 320, height : 240, quality : 90},
	 
	        url : contextPath+'/workflow/deploy.do',
	    	flash_swf_url : portalContextPath+'/content/plupload-2.1.2/js/Moxie.swf',
	    	silverlight_xap_url : portalContextPath+'/content/plupload-2.1.2/js/Moxie.xap',
	    });
	 	var uploader = $('#uploader').pluploadQueue();
	    uploader.bind("UploadComplete", function () {

	    });
	    uploader.bind("FileUploaded", function (uploader,file,responseObject) {
   			console.log(responseObject.response);
   			var rst=JSON.parse(responseObject.response);
   			if (!rst.state) {
   				
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
								 
							}
						}
					}
				});
		
			}else{
				
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : {}
				}).trigger("reloadGrid");
				alert(rst.errorMessage);
			}
	    });
}

function reset_uploader(){
	var uploader = $('#uploader').pluploadQueue();
	uploader.splice();
	uploader.refresh();
	init_uploader();
}
    /*
var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'pickfiles', // you can pass in id...
	container: document.getElementById('container'), // ... or DOM Element itself
	url : '${pageContext.request.contextPath}/workflow/deploy.do',
	flash_swf_url : '${sessionScope.portalContextPath}/content/plupload-2.1.2/js/Moxie.swf',
	silverlight_xap_url : '${sessionScope.portalContextPath}/content/plupload-2.1.2/js/Moxie.xap',
	
	filters : {
		max_file_size : '10mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,gif,png"},
			{title : "Zip files", extensions : "zip"}
		]
	},

	init: {
		PostInit: function() {
			document.getElementById('filelist').innerHTML = '';

			document.getElementById('uploadfiles').onclick = function() {
				uploader.start();
				return false;
			};
		},

		FilesAdded: function(up, files) {
			plupload.each(files, function(file) {
				document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
			});
		},

		UploadProgress: function(up, file) {
			document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
		},

		Error: function(up, err) {
			document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
		}
	}
});

uploader.init();
*/