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
	loadView(publicClassId);
	$("#btn-view-reg")
			.on(
					'click',
					function(e) {
						e.preventDefault();

						var dialog = $("#dialog-message")
								.removeClass('hide')
								.dialog(
										{
											modal : true,
											width : 660,
											title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>公开课报名</h4></div>",
											title_html : true,
											buttons : [

													{
														html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
														"class" : "btn btn-info btn-xs",
														id : 'ajax_button',
														click : function() {

															if (confirm("确定报名?")) {
																var params = {};
																$
																		.extend(
																				params,
																				{
																					remark : $(
																							'#remark')
																							.val()
																				});
																$
																		.extend(
																				params,
																				{
																					id : publicClassId
																				});
																$
																		.extend(
																				params,
																				{
																					time : new Date()
																				});
																var isReg = false;
																$
																		.each(
																				$('user'),
																				function(
																						i,
																						obj) {
																					if ($(
																							obj)
																							.attr(
																									"data") == systemUser.users.userId) {

																						isReg = true;
																					}
																				});
																if (isReg) {
																	alert("已经报名！");
																	return;
																}
																$
																		.ajax({
																			type : "post",
																			url : contextPath
																					+ "/publicClass/insertJoin.do",
																			data : params,
																			beforeSend : function(
																					XMLHttpRequest) {
																				style_ajax_button(
																						'btn-assn',
																						true);
																			},
																			success : function(
																					rst,
																					textStatus) {
																				style_ajax_button(
																						'btn-assn',
																						false);
																				if (rst) {
																					$(
																							dialog)
																							.dialog(
																									"close");
																					loadView(publicClassId);
																					alert(rst.errorMessage);
																				}
																			},
																			complete : function(
																					XMLHttpRequest,
																					textStatus) {
																				style_ajax_button(
																						'btn-assn',
																						false);
																			},
																			error : function() {
																				style_ajax_button(
																						'btn-assn',
																						true);
																			}
																		});
															}

														}
													},
													{
														html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
														"class" : "btn btn-xs",
														click : function() {
															$(this).dialog(
																	"close");
														}
													} ]
										});

					});
});
function loadView(id) {
	$
			.ajax({
				type : "get",
				url : contextPath
						+ "/publicClass/selectPublicClassByPrimaryKey.do",
				data : {
					id : id
				},
				beforeSend : function(XMLHttpRequest) {

				},
				success : function(rst, textStatus) {
					var html1 = new Array();
					var html2 = new Array();
					console.log(rst);
					var obj = rst.response;
					if (rst && rst.state) {
						$.each(rst.response, function(key, value) {
							$('#' + key).html(value);
						});

						$
								.each(
										rst.list,
										function(i, o) {
											html2
													.push('<div class="layout-user" >');
											html2
													.push('<usertwo  data="'
															+ o.student_id
															+ '"class="badge badge-info">');

											html2.push(o.name);
											html2.push('</usertwo>');
											html2.push('</div>');

											html1.push('<tr>');
											html1
													.push('<td class="center"><label class="position-relative">');
											html1
													.push('<input type="checkbox" class="ace" /> <span class="lbl"></span>');
											html1.push('</label></td>');
											html1.push('<td>'+(i+1)+'</td>');
											html1.push('<td>'+o.name+'</td>');
											html1.push('<td>'+(o.score==null?"":o.score)+'</td>');
											html1.push('<td>'+(o.remark==null?"":o.remark)+'</td>');
											html1.push('<td>'+(o.create_time==null?"":o.create_time)+'</td>');
											html1.push('</tr>');
										});
						html1.push('<tr>');
						html1
								.push('<td class="center"><label class="position-relative">');
						html1
								.push('<input type="checkbox" class="ace" /> <span class="lbl"></span>');
						html1.push('</label></td>');
						html1.push('<td align="right">合计</td>');
						html1.push('<td align="right">人次:</td>');
						html1.push('<td>'+obj.total+'</td>');
						html1.push('<td align="right">平均分:</td>');
						html1.push('<td>'+obj.scoreAvg+'</td>');
						html1.push('</tr>');
						$('#task-content').html(html2.join(''));
						$('#tb-list').html(html1.join(''));
					} else {
						bootbox
								.dialog({
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
