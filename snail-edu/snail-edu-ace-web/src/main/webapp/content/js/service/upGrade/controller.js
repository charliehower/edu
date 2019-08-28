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
	$('#btn-upGrade').on('click', function() {
		if (confirm("确定要升级吗？")) {
			upGrade();
		}
	});
	$('#btn-upGrade-undo').on('click', function() {
		if (confirm("确定要回退吗？")) {
			undoUpGrade();
		}
	});

});
function style_ajax_button(btnId, status) {
	console.log(status);
	var btn = $('#' + btnId);
	if (status) {
		btn.attr('disabled', "true");
	} else {
		btn.removeAttr("disabled");
	}
}
function upGrade() {
	$.ajax({
		type : "post",
		url : contextPath + "/grade/updateUpgrade.do",
		data : {
			year : year
		},
		beforeSend : function(XMLHttpRequest) {
			style_ajax_button('btn-upGrade', true);
		},
		success : function(rst, textStatus) {
			style_ajax_button('btn-upGrade', false);
			if (rst) {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
								// 关闭窗口

							}
						}
					}
				});

			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			style_ajax_button('btn-upGrade', false);
		},
		error : function() {
			style_ajax_button('btn-upGrade', true);
		}
	});
}
function undoUpGrade() {
	$.ajax({
		type : "post",
		url : contextPath + "/grade/updateunDoUpgrade.do",
		data : {
			year : year
		},
		beforeSend : function(XMLHttpRequest) {
			style_ajax_button('btn-upGrade-undo', true);
		},
		success : function(rst, textStatus) {
			style_ajax_button('btn-upGrade-undo', false);
			if (rst) {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
								// 关闭窗口

							}
						}
					}
				});

			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			style_ajax_button('btn-upGrade-undo', false);
		},
		error : function() {
			style_ajax_button('btn-upGrade-undo', true);
		}
	});
}