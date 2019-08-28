var disciplineId;
var disciplineName;
jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	
	$('input[name=search-disciplineId]').combobox({
		onSelect : function(record) {
			disciplineId=record.code;
			disciplineName=record.name;
		}
	});
	$('#btn-search').on('click', function() {
		
		$('.page-content').css("display","none");
		$('.login-container').css("display","display");
	});
	
});

function saveOrUpdateClassChedule(weekId, sectionId,classesId, name,btn) {
	var dialog = $("#dialog-message")
			.removeClass('hide')
			.dialog(
					{
						modal : true,
						width : 600,
						title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i> 设置课表</h4></div>",
						title_html : true,
						buttons : [ {
							text : "Clear",
							"class" : "btn btn-warning",
							click : function() {
								$('#fm-search').form('clear');
							}
						}, {
							text : "Cancel",
							"class" : "btn btn-xs",
							click : function() {
								$(this).dialog("close");
							}
						}, {
							text : "OK",
							id:"ajax_button",
							"class" : "btn btn-primary btn-xs",
							click : function() {
								disciplineId=$('#cc').combobox('getValue');
								disciplineName=$('#cc').combobox('getText');
								$("#"+btn).html(disciplineName);
								$("#"+btn).removeClass("btn-info btn-warning");
								$("#"+btn).addClass("btn-purple");
								//$(this).dialog("close");
								$("#time").val(new Date());
								var params={weekId:weekId,sectionId:sectionId,classesId:classesId,disciplineId:disciplineId};
								$.ajax({
									type : "post",
									url : contextPath + "/classChedule/saveOrUpdateClassChedule.do",
									data:{jsons:JSON.stringify(params)},
									beforeSend : function(XMLHttpRequest) {
										style_ajax_button('ajax_button',true);
									},
									success : function(rst, textStatus) {
										style_ajax_button('ajax_button',false);
										dialog.dialog( "close" );
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
															dialog.dialog( "close" ); 
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
							}
						} ]
					});
}