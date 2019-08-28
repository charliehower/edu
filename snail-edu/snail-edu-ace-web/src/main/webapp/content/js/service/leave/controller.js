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

	$('#btn-view-detail').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				var rd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				parent.addPanel('请假申请查询','/workflow/dynamic/console/workflow-view/index-view-leave.jsp?instanceId='+rd.leaveId,true);
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
function showDetail(userId,name){

	var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
		modal: true,
		width:580,
		title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> "+name+" 负责区域</h4></div>",
		title_html: true,
		buttons: [ 
			
			{
				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
				"class" : "btn btn-info btn-xs",
				id:'ajax_button',
				click: function() {
					$( this ).dialog( "close" ); 
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
	 $('#tt').tree('options').url=contextPath+'/repairsUsers/selectLocationTreeList.do?userId='+userId
	 $("#tt").tree('reload');
}