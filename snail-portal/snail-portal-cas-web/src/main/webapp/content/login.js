/**
 * 
 */
	jQuery(function($) {
		$(document).on('click', '.toolbar a[data-target]', function(e) {
			e.preventDefault();
			var target = $(this).data('target');
			$('.widget-box.visible').removeClass('visible');//hide others
			$(target).addClass('visible');//show target
		});
		//默认灰色样式
		$('body').attr('class', 'login-layout light-login');
		$('#id-text2').attr('class', 'grey');
		$('#id-company-text').attr('class', 'blue');
	});

	//you don't need this, just used for changing background
	jQuery(function($) {
		$('#btn-login-dark').on('click', function(e) {
			$('body').attr('class', 'login-layout');
			$('#id-text2').attr('class', 'white');
			$('#id-company-text').attr('class', 'blue');

			e.preventDefault();
		});
		$('#btn-login-light').on('click', function(e) {
			$('body').attr('class', 'login-layout light-login');
			$('#id-text2').attr('class', 'grey');
			$('#id-company-text').attr('class', 'blue');

			e.preventDefault();
		});
		$('#btn-login-blur').on('click', function(e) {
			$('body').attr('class', 'login-layout blur-login');
			$('#id-text2').attr('class', 'white');
			$('#id-company-text').attr('class', 'light-blue');

			e.preventDefault();
		});
		$('#btn-login-submit').on('click', function(e) {
			$('.widget-box.visible').removeClass('visible');//hide others
			$('#progress-bar-box').addClass('visible');
		});
		$('#btn-login-rp')
				.on(
						'click',
						function(e) {
							$
									.ajax({
										type : "post",
										url : "/portal/system/retrievePassword.do",
										data : {
											email : $('#email').val()
										},
										beforeSend : function(XMLHttpRequest) {
											style_ajax_button('btn-login-rp',
													true);
										},
										success : function(rst, textStatus) {
											style_ajax_button('btn-login-rp',
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

																	}
																}
															}
														});

											}
										},
										complete : function(XMLHttpRequest,
												textStatus) {
											//style_ajax_button('btn-login-rp',false);
										},
										error : function(XMLHttpRequest,
												textStatus, errorThrown) {
											style_ajax_button('btn-login-rp',
													false);
											bootbox
													.dialog({
														title : '系统提示',
														message : "<h2>"
																+ XMLHttpRequest.status
																+ " "
																+ textStatus
																+ "</h2>",
														buttons : {
															"success" : {
																"label" : "<i class='ace-icon fa fa-check'></i>确定",
																"className" : "btn-sm btn-success",
																"callback" : function() {

																}
															}
														}
													});
										}
									});
						});
		//e.preventDefault();
	});
	function style_ajax_button(btnId, status) {
		console.log(status);
		var btn = $('#' + btnId);
		if (status) {
			btn.find('i').removeClass('fa-check');
			btn.find('i').addClass('fa-spinner fa-spin');
			btn.attr('disabled', "true");

		} else {
			btn.find('i').removeClass('fa-spinner');
			btn.find('i').removeClass('fa-spin');
			btn.find('i').addClass('fa-check');
			btn.removeAttr("disabled");
		}
	}