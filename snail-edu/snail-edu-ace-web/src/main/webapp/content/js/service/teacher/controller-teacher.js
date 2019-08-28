jQuery(function($) {
	$('#btn-teacher').on('click',function() {
		$('#fm-teacher').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
					
				});
				$.extend(params, {
					time : new Date()
				});
				params['soattr'] = $('#soattr').combobox('getValues').join(',');
				console.log(params);
				$.ajax({
					type : "post",
					url : contextPath + "/teacher/updateTeacher.do",
					data:{jsons:JSON.stringify(params)},
					beforeSend : function(XMLHttpRequest) {
						style_ajax_button('btn-teacher',true);
					},
					success : function(rst, textStatus) {
						style_ajax_button('btn-teacher',false);
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
						style_ajax_button('btn-teacher',false);
					},
					error : function() {
						style_ajax_button('btn-teacher',true);
					}
				});
				return false;
			}
		});	
	});
	$('#btn-teacher-group').on('click',function() {
		$('#fm-teacher-group').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
					
				});
				$.extend(params, {
					time : new Date()
				});
				params['teacherId'] = $('input[name=teacherId]').val();
				params['name'] = $('input[name=name]').val();
				params['classesTaught'] = $('#classesTaught').combobox('getValues').join(',');
				console.log(params);
				$.ajax({
					type : "post",
					url : contextPath + "/teacher/updateGroup.do",
					data:{jsons:JSON.stringify(params)},
					beforeSend : function(XMLHttpRequest) {
						style_ajax_button('btn-teacher-group',true);
					},
					success : function(rst, textStatus) {
						style_ajax_button('btn-teacher-group',false);
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
						style_ajax_button('btn-teacher-group',false);
					},
					error : function() {
						style_ajax_button('btn-teacher-group',true);
					}
				});
				return false;
			}
		});	
	});
	$('#btn-teacher-reset').on('click',function() {
		$('#fm-teacher').form('clear');
		
	});
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	$( "#btn-upload-add" ).on('click', function(e) {
		e.preventDefault();
		reset_uploader();
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
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
						reset_uploader();				
						
					} 
				}
				
			]
		});
		
	});
	
	$( "#btn-upload-view" ).on('click', function(e) {
		e.preventDefault();
		var dialog = $( "#dialog-message-photo" ).removeClass('hide').dialog({
			modal: true,
			width:500,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 照片</h4></div>",
			title_html: true,
			buttons: [ 
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					click: function() {
						$( this ).dialog( "close" ); 
					} 
				}
				
			]
		});
		var fileName=$('input[name=photo]').val();
		if(!fileName||fileName==''){
			return;
		}
		var src=portalContextPath+'/files/download.do?collectionName=teacherPhoto&originalFilename='+fileName+'&fileName='+fileName;
			console.log(src);
			
		 var img = new Image();
		 $(img).attr("src", "");
		    //图片加载加载后执行
		    $(img).load(function() {
		        //图片默认隐藏
		        $(this).hide();
		        //移除小动画
		        $(".loading").removeClass("loading").append(this);
		        //使用fadeIn特效
		        $(this).fadeIn("slow");
		    })
		    .error(function() {
		        //加载失败时的处理
		    })
		    //最后设置src
		    .attr("src", src);
		
	});
	setTimeout("loadForm('"+teacherId+"')",200);
	//loadForm(teacherId);
	$('#tab-group').on('click',function() {
		setTimeout("loadForm('"+teacherId+"')",200);
		
	});
});

function loadForm(id){
	$.ajax({
		type : "get",
		url : contextPath + "/teacher/selectTeacherByPrimaryKey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				if(!rst.response.soattr){
					rst.response.soattr='1';
				}
				$('#fm-teacher').form('load',rst.response);
				$('#fm-teacher-group').form('load',rst.response);
				
				$('#soattr').combobox('setValues',rst.response.soattr.split(','))
				if(rst.response.classesTaught){
					$('#classesTaught').combobox('setValues',rst.response.classesTaught.split(','))
				}
				
				
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



jQuery(function($) {
	init_uploader();
});

function init_uploader(){
	 $("#uploader").pluploadQueue({
			runtimes : 'html5,flash,silverlight,html4',
	        chunk_size : '1mb',
	        unique_names : true,
	        multipart_params:{collectionName:'teacherPhoto'}, 
	        filters : {
	            max_file_size : '10mb',
	            mime_types: [
	                {title : "Image files", extensions : "jpg,gif,png"}
	            ]
	        },
	 
	        // Resize images on clientside if we can
	        resize : {width : 320, height : 240, quality : 90},
	 
	        url : portalContextPath+'/files/uploadFile.do',
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
				$("input[name=photo]").val(rst.response);
				alert(rst.errorMessage);
				$( "#dialog-message" ).dialog( "close" );
			}
	    });
	    
	    
}

function reset_uploader(){
	var uploader = $('#uploader').pluploadQueue();
	uploader.splice();
	uploader.refresh();
	init_uploader();
}