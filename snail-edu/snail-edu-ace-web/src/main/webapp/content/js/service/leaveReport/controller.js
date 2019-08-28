jQuery(function($) {

	$.fn.spin = function(opts) {
		this.each(function() {
			var $this = $(this), data = $this.data();

			if (data.spinner) {
				data.spinner.stop();
				delete data.spinner;
			}
			if (opts !== false) {
				data.spinner = new Spinner($.extend({
					color : $this.css('color')
				}, opts)).spin(this);
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
				query(params);
				return false;
			}
		});
	});

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

});

function query(params) {
	$.ajax({
		type : "get",
		url : contextPath + "/report/query.do",
		data : params,
		beforeSend : function(XMLHttpRequest) {
			$("#btn-search").attr("disabled", true);
		},
		success : function(rst, textStatus) {
			$("#btn-search").attr("disabled", false);
			if (rst && rst.state) {
				var html = [];
				var t=0;
				$.each(rst.list, function(i, o) {
					html.push('<tr>');
					html.push('<td height="25px">'+(i+1)+'</td>');
					html.push('<td>'+o.name+'</td>');
					html.push('<td>'+Math.round((o.dates/8)*100)/100+'</td>');
					html.push('</tr>');
					t+=o.dates;
				});
				html.push('<tr>');
				html.push('<td colspan="2" align="center">合计</td>');
				
				html.push('<td>'+Math.round((t/8)*100)/100+'</td>');
				html.push('</tr>');
				$('#gd-report').html(html.join(''));
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
		error : function() {
			$("#btn-search").attr("disabled", false);
		}
	});
}
