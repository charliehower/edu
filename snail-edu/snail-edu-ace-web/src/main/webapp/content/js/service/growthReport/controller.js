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
	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {
					start : 0,
					limit : 9999999
				};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				initTable(params);
				return false;
			}
		});
	});
	$('#btn-excel').on('click', function() {
		exportExcel('tb-excel');
	});
});

function initTable(params) {

	$.ajax({
		type : "get",
		url : contextPath + "/growth/findGrowthList.do",
		data : params,
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			var html = new Array();
			$.each(rst.list, function(i, data) {
				html.push('<tr>');
				html.push('<td>'+(i+1)+'</td>');
				html.push('<td>'+data.className+'</td>');
				html.push('<td>'+data.headerMaster+'</td>');
				html.push('<td>'+data.attendance+'</td>');
				html.push('<td>'+data.hygieneAreas+'</td>');
				html.push('<td>'+data.hygieneTools+'</td>');
				html.push('<td>'+data.ceremony+'</td>');
				html.push('<td>'+data.liveFlag+'</td>');
				html.push('<td>'+data.exerciseBody+'</td>');
				html.push('<td>'+data.exerciseEye+'</td>');
				html.push('<td>'+data.security+'</td>');
				html.push('<td>'+data.contraband+'</td>');
				html.push('<td>'+data.contrabandWeekend+'</td>');
				html.push('<td>'+data.dormitory+'</td>');
				html.push('<td>'+data.construction+'</td>');
				html.push('<td>'+data.bonus+'</td>');
				html.push('<td>'+data.totalScore+'</td>');
				html.push('</tr>');

			});
			$('#grid-table-body').html(html.join(''));
			$('#_time').html("时间："+params.startDate);
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}
