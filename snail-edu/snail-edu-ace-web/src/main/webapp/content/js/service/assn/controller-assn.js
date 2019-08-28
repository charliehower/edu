jQuery(function($) {
	$('#btn-assn').on('click',function() {
		var url=cfg.grid_edit_data_url;
		if(assnId==''){
			url=cfg.grid_add_data_url;
		}
		$('#fm-assn').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
					
				});
				$.extend(params, {
					time : new Date()
				});
				var data=new Array();
				$.each($('user'),function(i,obj){
					data.push($(obj).attr("data")+","+$(obj).html());
				});
				params['data1']=data.join(';');
				
				var data2=new Array();
				$.each($('usertwo'),function(i,obj){
					data2.push($(obj).attr("data")+","+$(obj).html());
				});
				params['data2']=data2.join(';');
				$.ajax({
					type : "post",
					url : url,
					data:{jsons:JSON.stringify(params)},
					beforeSend : function(XMLHttpRequest) {
						style_ajax_button('btn-assn',true);
					},
					success : function(rst, textStatus) {
						style_ajax_button('btn-assn',false);
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
						style_ajax_button('btn-assn',false);
					},
					error : function() {
						style_ajax_button('btn-assn',true);
					}
				});
				return false;
			}
		});	
	});
	
	$('#btn-assn-reset').on('click',function() {
		$('#fm-assn').form('clear');
		
	});
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	setTimeout("loadForm('"+assnId+"')",200);
	
});


function loadForm(id){
	$.ajax({
		type : "get",
		url : contextPath + "/assn/selectAssnByPrimaryKey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(rst, textStatus) {
			var html1 = new Array();
			var html2 = new Array();
			if(rst&&rst.state){
				$('#fm-assn').form('load',rst.response);	
				//console.log(rst);
				  $.each(rst.list,function(i,o) {
					  if (o.category_id == '1') {
							html1.push('<div class="layout-user" >');
							html1.push('<user data="'+o.student_id+'" class="badge badge-' + cssColor9[0]
									+ '">');
							html1.push(o.name);
							html1.push('</user>');
							html1.push('</div>');
						} else {
							html2.push('<div class="layout-user" >');
							html2.push('<usertwo  data="'+o.student_id+'"class="badge badge-' + cssColor9[0]
									+ '">');
							html2.push(o.name);
							html2.push('</usertwo>');
							html2.push('</div>');
						}
						
					
				  });
					$('#task-content').html(html1.join(''));
					$('#task-content-two').html(html2.join(''));
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
		error : function(XMLHttpRequest, textStatus) {
			alert(textStatus);
		}
	})
}
