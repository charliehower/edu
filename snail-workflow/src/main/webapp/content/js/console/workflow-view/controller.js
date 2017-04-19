jQuery(function($) {
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
				console.log(rd);
				var key=rd.ID_;
				key=key.split(".")[0];
				var url=contextPath+'/dynamic/console/workflow-view/index-view-'+key+'.jsp?instanceId='+rd.ID_;
				
				parent.addPanel(rd.OBJNAME_+rd.ID_,url,true);
			});
});



