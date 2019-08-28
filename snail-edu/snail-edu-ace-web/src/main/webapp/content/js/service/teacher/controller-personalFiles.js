jQuery(function($) {
	$( "#btn-upload-add-pf" ).on('click', function(e) {
		e.preventDefault();
		reset_uploader_pf();
		var dialog = $( "#dialog-message-pf" ).removeClass('hide').dialog({
			modal: true,
			width:750,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 文件上传</h4></div>",
			title_html: true,
			buttons: [ 
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					click: function() {
						$( this ).dialog( "close" ); 
					} 
				},
				{
					html: "<i class='ace-icon glyphicon glyphicon-refresh bigger-110'></i>&nbsp; 重置",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						reset_uploader_pf();				
						
					} 
				}
				
			]
		});
		
	});
	$('#tab-personalFiles').on('click',function() {
		setTimeout("showgd_personalFiles()",200);
		
	});	
	
});

function showgd_personalFiles(){
	$('#dg-personalFiles').datagrid('selectRow', editIndex).datagrid('beginEdit', -1);
}



jQuery(function($) {
	init_uploader_pf();
});

function init_uploader_pf(){
	 $("#uploader-pf").pluploadQueue({
			runtimes : 'html5,flash,silverlight,html4',
	        chunk_size : '1mb',
	        unique_names : true,
	        multipart_params:{collectionName:'personalFiles',category:'01',teacherId:teacherId}, 
	        filters : {
	            max_file_size : '10mb',
	            mime_types: [
	                {title : "Image files", extensions : "jpg,gif,png"},
	                {title : "Office files", extensions : "doc,xls,ppt,docx,xlsx,pptx,pdf"},
	                {title : "zip files", extensions : "zip,rar,gzip"}
	            ]
	        },
	 
	        // Resize images on clientside if we can
	        resize : {width : 320, height : 240, quality : 90},
	 
	        url : contextPath + '/personalFiles/uploadFile.do',
	    	flash_swf_url : portalContextPath+'/content/plupload-2.1.2/js/Moxie.swf',
	    	silverlight_xap_url : portalContextPath+'/content/plupload-2.1.2/js/Moxie.xap',
	    });
	 	var uploader = $('#uploader-pf').pluploadQueue();
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
				//$("input[name=photo]").val(rst.response);
				$('#dg-personalFiles').datagrid('reload');
				alert(rst.errorMessage);
				$( "#dialog-message-pf" ).dialog( "close" );
			}
	    });
	    
	    
}

function reset_uploader_pf(){
	var uploader = $('#uploader-pf').pluploadQueue();
	uploader.splice();
	uploader.refresh();
	init_uploader_pf();
}
