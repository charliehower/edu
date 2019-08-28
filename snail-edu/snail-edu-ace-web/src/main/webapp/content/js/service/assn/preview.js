var limitMax=0;
jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	loadView(assnId);
	$( "#btn-view-reg" ).on('click', function(e) {
		e.preventDefault();
		
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:660,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>社团申请</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						if(limitMax<=0){
							alert("已经达到最大人数限制！");
							return;
						}
						if(confirm("确定参加社团"+$('#assnName').html()+"?")){
							var params = {};
							$.extend(params, {
								deiscri : $('#remark').val()
							});
							$.extend(params, {
								assnId : assnId
							});
							$.extend(params, {
								time : new Date()
							});
							var isReg=false;
							$.each($('user'),function(i,obj){
								if($(obj).attr("data")==systemUser.users.userId){
									
									isReg=true;
								}
							});
							if(isReg){
								alert("已经参加的社团！");
								return ;
							}
							$.ajax({
								type : "post",
								url : contextPath+"/assn/insertAssnSubReg.do",
								data:{jsons:JSON.stringify(params)},
								beforeSend : function(XMLHttpRequest) {
									style_ajax_button('btn-assn',true);
								},
								success : function(rst, textStatus) {
									style_ajax_button('btn-assn',false);
									if (rst) {
										$( dialog ).dialog( "close" );
										loadView(assnId);
										alert(rst.errorMessage);
									}
								},
								complete : function(XMLHttpRequest, textStatus) {
									style_ajax_button('btn-assn',false);
								},
								error : function() {
									style_ajax_button('btn-assn',true);
								}
							});
						}
						
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
});
function loadView(id) {
	$.ajax({
		type : "get",
		url : contextPath + "/assn/selectAssnByPrimaryKey.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			var html1 = new Array();
			var html2 = new Array();
			var assn=rst.response;
			if (rst && rst.state) {
				$.each(rst.response, function(key, value) {
					$('#'+key).html(value);
				});
				var j=0;
				var k=0;
				$.each(rst.list,
						function(i, o) {
							if (o.category_id == '1') {
								html1.push('<div class="layout-user" >');
								k+=1;
								if(o.status=='1'){
									j+=1;
									html1.push('<user data="' + o.student_id
											+ '" class="badge badge-info">');
									html1.push(o.name);
								}else{
									html1.push('<user data="' + o.student_id
											+ '" class="badge badge-'
											+ cssColor9[0] + '">');
									html1.push(o.name+"(待审核)");
								}
								
								html1.push('</user>');
								html1.push('</div>');
							} else {
								html2.push('<div class="layout-user" >');
								html2.push('<usertwo  data="' + o.student_id
										+ '"class="badge badge-info">');
								j+=1;
								k+=1;
								html2.push(o.name);
								html2.push('</usertwo>');
								html2.push('</div>');
							}
							
						});
				limitMax=assn.limitMax-k;
				if(limitMax<0){
					limitMax=0;
				}
				$('#task-content').html(html1.join(''));
				$('#task-content-two').html(html2.join(''));
				$('#limitMax').html(assn.limitMax+"目前已经审核"+j+"人,未审核"+(k-j)+"人,还可以申请<span class='badge badge-success'>"+limitMax+"</span>人");
				$('#reg-target').html(assn.assnName);
			} else {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
								// $( this ).dialog( "close" );
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
