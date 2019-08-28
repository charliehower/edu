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
	$('#cc-teacher').combogrid({
		onBeforeLoad : function(param) {
			param.gradeId = $('input[name=search-gradeId]').val();
			// param.departmentId = $('input[name=search-departmentId]').val();
			param.disciplineId = $('input[name=search-disciplineId]').val();
		}
	});
	$('input[name=search-gradeId]').combobox({
		onSelect : function(record) {
			var g = $('#cc-teacher').combogrid('grid');
			g.datagrid('reload');
		}
	});
	$('input[name=search-departmentId]').combotree({
		onSelect : function(node) {
			// alert(node.id);
			var g = $('#cc-teacher').combogrid('grid');
			g.datagrid('reload', {
				departmentId : node.id
			});
		}
	});
	$('input[name=search-disciplineId]').combobox({
		onSelect : function(record) {
			var g = $('#cc-teacher').combogrid('grid');
			g.datagrid('reload');
		}
	});
	$('#btn-search').on('click', function() {
		$('.page-content').css("display","none");
		$('.login-container').css("display","display");
	});
	$('#btn-search-teamTr').on('click', function() {
		$('.page-content').css("display","none");
		$('.login-container').css("display","display");
	});
	$('#btn-search-teamPrepare').on('click', function() {
		$('.page-content').css("display","none");
		$('.login-container').css("display","display");
	});
	$('#btn-search-teamGrade').on('click', function() {
		$('.page-content').css("display","none");
		$('.login-container').css("display","display");
	});
	$('#btn-search-headmaster').on('click', function() {
		$('.page-content').css("display","none");
		$('.login-container').css("display","display");
	});
});

function saveOrUpdateTeacher(gradeId, classesId, disciplineId, name,btn) {
	var dialog = $("#dialog-message-arr")
			.removeClass('hide')
			.dialog(
					{
						modal : true,
						width : 600,
						title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i> 任课安排</h4></div>",
						title_html : true,
						buttons : [ {
							text : "Clear",
							"class" : "btn btn-warning",
							click : function() {
								$('#fm-teacher-search').form('clear');
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
								var g = $('#cc-teacher').combogrid('grid');	
								var r = g.datagrid('getSelected');	
								$("#"+btn).html(r.NAME);
								$("#"+btn).removeClass("btn-info btn-warning");
								$("#"+btn).addClass("btn-purple");
								//$(this).dialog("close");
								var params={year:year,gradeId:gradeId,classesId:classesId,disciplineId:disciplineId,teacherId:r.TEACHER_ID};
								$.ajax({
									type : "post",
									url : contextPath + "/arrangement/saveOrUpdateArrangement.do",
									data:{jsons:JSON.stringify(params)},
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