jQuery(function($) {
	loadAttach(id);
	loadDocFlow(id);
	$('#btn-docFlow-submit')
			.on(
					'click',
					function() {
						$('#fm-docFlow')
								.ajaxForm(
										{
											beforeSubmit : function(formData,
													jqForm, options) {
												var params = {};
												$
														.each(
																formData,
																function(n, obj) {
																	params[obj.name] = obj.value;

																});
												$.extend(params, {
													time : new Date()
												});
												if (params.piContent == '') {
													params.piContent = CKEDITOR.instances.piContent.getData();
												}
												params.piUser=$('#cc').combotree('getValues');
												var url = contextPath
														+ "/docFlowTask/insertDocFlowTask.do"
												
												console.log(params);
												//return;
												$
														.ajax({
															type : "post",
															url : url,
															data : {
																jsons : JSON
																		.stringify(params)
															},
															beforeSend : function(
																	XMLHttpRequest) {
																style_ajax_button(
																		'btn-docFlow-submit',
																		true);
															},
															success : function(
																	rst,
																	textStatus) {
																style_ajax_button(
																		'btn-docFlow-submit',
																		false);
																if (rst) {
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
																							// 关闭窗口
																							parent
																									.reloadGrid();
																							parent
																									.removePanel();
																						}
																					}
																				}
																			});

																}
															},
															complete : function(
																	XMLHttpRequest,
																	textStatus) {
																style_ajax_button(
																		'btn-docFlow-submit',
																		false);
															},
															error : function() {
																style_ajax_button(
																		'btn-docFlow-submit',
																		true);
															}
														});
												return false;
											}
										});

					});

});
function loadDocFlow(id) {
	$.ajax({
		type : "get",
		url : contextPath + "/docFlow/selectDocFlowByPrimaryKey.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				$.each(rst.response, function(key, value) {
					$('#' + key).html(value);
				});

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
		}
	});
}
function loadAttach(docFlowId) {
	$
			.ajax({
				type : "get",
				url : portalContextPath + "/attach/findAttachList.do",
				data : {
					noticeId : docFlowId
				},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {
					if (rst && rst.state) {
						var html = [];
						$
								.each(
										rst.list,
										function(n, file) {

											html
													.push('<div id="'
															+ file.fileUrl
															+ '" ><a  href="'
															+ portalContextPath
															+ '/files/download.do?collectionName=docFlow&originalFilename='
															+ file.fileName
															+ '&fileName='
															+ file.fileUrl
															+ '" target="_blank">'
															+ file.fileName
															+ '</a> ('
															+ parseInt(file.fileSize / 1024)
															+ 'kb) <b></b></div>');
										});
						// document.getElementById('filelist').innerHTML=html.join('');
						$('#filelist-history').html(html.join(''));
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
				error : function() {
				}
			});
}