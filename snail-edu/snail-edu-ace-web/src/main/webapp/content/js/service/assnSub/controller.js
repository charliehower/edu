jQuery(function($) {
	
	$.fn.spin = function(opts) {
		this.each(function() {
		  var $this = $(this),
			  data = $this.data();

		  if (data.spinner) {
			data.spinner.stop();
			delete data.spinner;
		  }
		  if (opts !== false) {
			data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
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
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});
	var selects=new Array();
	$( "#btn-view-audit" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
		'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
					
		}
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:660,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>入社审核</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						if(confirm("确定审核吗")){
							var params = {};
							var list=new Array();
							$.each(selects,function(i,o){
								console.log(o);
								list.push({assnSubId:o.assnSubId,status:$('#auditRs input[name="audit_radio"]:checked ').val(),studentName:o.studentName});
							})
							params['list']=list;
							$.ajax({
								type : "post",
								url : contextPath+"/assnSub/updateAuditByPrimaryKey.do",
								data:{jsons:JSON.stringify(params)},
								beforeSend : function(XMLHttpRequest) {
									style_ajax_button('btn-assn',true);
								},
								success : function(rst, textStatus) {
									style_ajax_button('btn-assn',false);
									reloadGrid();
									if (rst) {
										$( dialog ).dialog( "close" );
										
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
		selects=new Array();
		var html=new Array();
		var slt = $(cfg.grid_selector).jqGrid('getGridParam','selarrrow'); 
		html.push("<table class='table table-striped table-bordered table-hover'><thead><tr><th>序号</th><th>社团</th><th>申请人</th><th>班级</th></tr></thead><tbody>");
		for(var i=0; i<slt.length; i++){
		      var row=$(cfg.grid_selector).jqGrid('getRowData', slt[i]); 
		      selects.push(row);
		      html.push("<tr><td>"+(i+1)+"</td><td>"+row.assnName+"</td><td>"+row.studentName+"</td><td>"+row.classesName+"</td></tr>");
		     // html.push(row.classesName+row.studentName);
		} 
		html.push("</tbody></table>");
		$( '#student-target' ).html(html.join(''));
	});
	
	
	
	$('#btn-view-del').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				jQuery(cfg.grid_selector).jqGrid(
						'delGridRow',
						gr,
						{
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));

function style_ajax_button(btnId,status){
	console.log(status);
	var btn=$('#'+btnId);
	if(status){
		btn.find('i').removeClass('fa-check');
		btn.find('i').addClass('fa-spinner fa-spin');
		btn.attr('disabled',"true");
		
	}else{
		btn.find('i').removeClass('fa-spinner');
		btn.find('i').removeClass('fa-spin');
		btn.find('i').addClass('fa-check');
		btn.removeAttr("disabled");
	}
}
});
function reloadGrid(){
	console.log('reloadGrid');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
	}).trigger("reloadGrid");
}