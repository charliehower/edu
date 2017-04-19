	function initDwmReport(){
		initDwmReportList('1');
		initDwmReportList('2');
		initDwmReportList('3');
	}	
jQuery(function($) {
			initWorkflowList();
			initNoticeTopList();
			initDutyTodayList();
			setTimeout(initDwmReport,3000);
			$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
				_title: function(title) {
					var $title = this.options.title || '&nbsp;'
					if( ("title_html" in this.options) && this.options.title_html == true )
						title.html($title);
					else title.text($title);
				}
			}));
			
		});
	

		function initWorkflowList() {
			
			$
					.ajax({
						type : "post",
						url : "/workflow/workflow/findWorkflowList.do",
						data : {start:0,limit:5},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {

							if (rst) {
								var html = new Array();
								var k=0;
								for ( var i in rst.list) {
									var o=rst.list[i];
									if(o.key!='quit'){
										k++;

										html.push('<tr>');
										html.push('<td>');
										html.push('<span class="badge badge-'+cssColor9[GetRandomNumSeq(k,8)]+'">'+k+'</span> ');
										html.push('</td>');
										
										html.push('<td>');
										html.push(o.name);
										html.push('</td>');
										
										html.push('<td>');
										html.push(o.version);
										html.push('</td>');
										
										html.push('<td>');
										html.push('<a href="javascript:addWorkflow(\''+o.key+'\',\''+o.name+'\');" class="blue">');
										html.push('<span class="lbl">新申请</span>');
										html.push('</a>');
										html.push('</td>');
										
										
										html.push('<td>');
										html.push('<a href="javascript:proxyWorkflow(\''+o.key+'\',\''+o.name+'\');" class="blue">');
										html.push('<span class="lbl">代理</span>');
										html.push('</a>');
										html.push('</td>');
										
										html.push('<td>');
										html.push('<a href="javascript:viewWorkflow(\''+o.key+'\',\''+o.name+'\');" class="blue">');
										html.push('历史<i class="ace-icon  bigger-130"></i>');
										html.push('</a>');
										html.push('</td>');
										
										
										html.push('</tr>');
									}
									
									
									//console.log(rst.list[i]);
								}
								$('#workflow-list-grid').html(html.join(''));
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {

						}
					});
		}
		function addWorkflow(key,name,proxy){
			$( "#dialog-confirm" ).removeClass('hide').dialog({
				resizable: false,
				modal: false,
				title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认开启电子工作流程吗?</h4></div>",
				title_html: true,
				buttons: [
					{
						html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 开启电子工作流程",
						"class" : "btn btn-info btn-xs",
						id:'btn-add-workflow',
						click: function() {
							 
							$.ajax({
								type : "post",
								url : "/workflow/workflow/startProcessInstanceByKey.do",
								data : {key:key,proxy:proxy},
								beforeSend : function(XMLHttpRequest) {
									style_ajax_button('btn-add-workflow',true);
								},
								success : function(rst, textStatus) {
									style_ajax_button('btn-add-workflow',false);
									$( "#dialog-confirm" ).dialog( "close" );
									if (rst.state) {
										parent.initWorkflowList();
										var id=rst.response.id;
										console.log(rst);
										parent.addPanel(name,rst.response.formResourceName+'?taskId='+id,true);
									}else{
										alert(rst.errorMessage);
									}
									
								},
								complete : function(XMLHttpRequest, textStatus) {
									style_ajax_button('btn-add-workflow',false);
								},
								error : function() {
									style_ajax_button('btn-add-workflow',false);
								}
							});
						}
					}
					,
					{
						html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
						"class" : "btn btn-xs",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
				]
			});
			
		}
		function viewWorkflow(key,name){
			parent.addPanel(name,'/workflow/dynamic/console/workflow-view/index.jsp?key='+key,true);
			
		}
		function proxyWorkflow(key,name){
			var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
				modal: true,
				width:600,
				title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i> 代理设置</h4></div>",
				title_html: true,
				buttons: [ 
					{
						text: "Cancel",
						"class" : "btn btn-xs",
						click: function() {
							$( this ).dialog( "close" ); 
						} 
					},
					{
						text: "OK",
						"class" : "btn btn-primary btn-xs",
						click: function() {
							$( this ).dialog( "close" ); 
							var proxy=$('input[name=proxy]').val();
							//alert(proxy);
							addWorkflow(key,name,proxy);
						} 
					}
				]
			});
		}
function initNoticeTopList() {
			
			$
					.ajax({
						type : "post",
						url : contextPath+"/notice/findListTop.do",
						data : {},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {

							if (rst) {
								var html = new Array();
								var k=0;
								for ( var i in rst.list) {
									var o=rst.list[i];
									o.url=contextPath+"/dynamic/console/notice/preview.jsp?noticeId="+o.noticeId+"&taskId=0"
									k++;

									html.push('<tr>');
									html.push('<td>');
									html.push('<span">■</span> ');
									
									html.push('</td>');
									
									html.push('<td >');
									html.push('<a href="javascript:parent.addPanel(\''+o.category+'\',\''+o.url+'\',true)">');
									
									html.push(o.title);
									
									
									html.push('</a>');
									
									if(o.top=='1'){
										html.push('  <span class="badge badge-yellow">置顶</span> ');
								
									}
									html.push('</td>');
								
									
									
									html.push('<td width="150px" align="right">');
									html.push(o.publishTime);
									html.push('</td>');
									
									
									html.push('</tr>');
									
									//console.log(rst.list[i]);
								}
								$('#notice-list-grid').html(html.join(''));
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {

						}
					});
		}

function initDutyTodayList() {
	
	$
			.ajax({
				type : "post",
				url : "/edu/dutyDetail/findDutyDetailListToday.do",
				data : {},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {

					if (rst) {
						var html = new Array();
						var k=0;
						for ( var i in rst.list) {
							var o=rst.list[i];
						
							
							html.push('<div class="layout-user" >');
							html.push('<span class="badge badge-'+cssColor9[GetRandomNumSeq(k,8)]+'">');
							html.push(o.name+"  "+o.tel);
							html.push('</span>');
							html.push('</div>');
							
							//console.log(rst.list[i]);
						}
						$('#duty-tab').html(html.join(''));
					}
				},
				complete : function(XMLHttpRequest, textStatus) {

				},
				error : function() {

				}
			});
}
function initDwmReportList(categoryId) {
	
	$
			.ajax({
				type : "post",
				url : "/edu/dwmReport/findListTop.do",
				data : {categoryId:categoryId,limit:8},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {

					if (rst) {
						var html = new Array();
						var k=0;
						for ( var i in rst.list) {
							var o=rst.list[i];
							o.url="/edu/dynamic/service/dwmReport/preview.jsp?dwmReportId="+o.dwmReportId+"&taskId=0"
							k++;

							html.push('<tr>');
							html.push('<td align="left" width="20">');
							html.push('<span">■</span> ');
							
							html.push('</td>');
							
							html.push('<td align="left">');
							html.push('<a href="javascript:parent.addPanel(\''+o.categoryName+'\',\''+o.url+'\',true)">');
							
							html.push(o.title);
							
							
							html.push('</a>');
							
							if(o.top=='1'){
								html.push('  <span class="badge badge-yellow">置顶</span> ');
						
							}
							html.push('</td>');
						
							
							
							html.push('<td width="150px" align="right">');
							html.push(o.publishTime);
							html.push('</td>');
							
							
							html.push('</tr>');
							
							//console.log(rst.list[i]);
						}
						html.push('<td width="30" align="right">');
						
						html.push('</td>');
						html.push('<td  align="right">');
						html.push('</td>');
						html.push('<td width="150px" align="right">');
						html.push('<a href="javascript:dwmReportList(\''+categoryId+'\')">更多</a>');
						html.push('</td>');
						
						
						html.push('</tr>');
						$('#dwmReport-list-grid-'+categoryId).html(html.join(''));
					}
				},
				complete : function(XMLHttpRequest, textStatus) {

				},
				error : function() {

				}
			});
}
function dwmReportList(categoryId){
	var name="日查日报";
	var url="/edu/dynamic/service/dwmReport/dwmReportList.jsp?categoryId="+categoryId;
	if(categoryId=="2"){
		name="周结周报";
	}
	if(categoryId=="3"){
		name="部门总结";
	}
	parent.addPanel(name,url,true)
}