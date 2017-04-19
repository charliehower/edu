<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>企业资源管理-users</title>

</head>
<jsp:include page="/dynamic/common/common.jsp" />
<style>
.layout-user {
 width: 150px;
 height: 30px;
 float: left;
 margin: 5px 5px 5px;
}
</style>


<body>
<!-- /section:basics/sidebar -->
			<div class="page-content">
		<div class=" widget-container-col">
									<div class="widget-box widget-color-blue">
										<!-- #section:custom/widget-box.options -->
										<div class="widget-header">
											<h5 class="widget-title bigger lighter">
												<i class="ace-icon fa fa-table"></i>
												电子工作流
											</h5>

											<div class="widget-toolbar widget-toolbar-light no-border">
												<select id="simple-colorpicker-1" class="hide">
													<option selected="" data-class="blue" value="#307ECC">#307ECC</option>
													<option data-class="blue2" value="#5090C1">#5090C1</option>
													<option data-class="blue3" value="#6379AA">#6379AA</option>
													<option data-class="green" value="#82AF6F">#82AF6F</option>
													<option data-class="green2" value="#2E8965">#2E8965</option>
													<option data-class="green3" value="#5FBC47">#5FBC47</option>
													<option data-class="red" value="#E2755F">#E2755F</option>
													<option data-class="red2" value="#E04141">#E04141</option>
													<option data-class="red3" value="#D15B47">#D15B47</option>
													<option data-class="orange" value="#FFC657">#FFC657</option>
													<option data-class="purple" value="#7E6EB0">#7E6EB0</option>
													<option data-class="pink" value="#CE6F9E">#CE6F9E</option>
													<option data-class="dark" value="#404040">#404040</option>
													<option data-class="grey" value="#848484">#848484</option>
													<option data-class="default" value="#EEE">#EEE</option>
												</select>
											</div>
										</div>

										<!-- /section:custom/widget-box.options -->
										<div class="widget-body">
											<div class="widget-main no-padding">
												<table class="table table-striped table-bordered table-hover">
													<thead class="thin-border-bottom">
														<tr>
															<th>
																
																序号
															</th>

															<th>
																电子流程名称
															</th>
															<th>版本</th>
															<th>申请</th>
															<th>代理</th>
															<th>查看</th>
														</tr>
													</thead>

													<tbody id="workflow-list-grid">
														
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div><!-- /.span -->
		</div>
		<!-- /.page-content -->
	<div id="dialog-confirm" class="hide">
			<div class="alert alert-info bigger-110">
				点击确定后，系统将开启一个电子工作流程.</div>

			<div class="space-6"></div>

			<p class="bigger-110 bolder center grey">
				<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> 您确定吗?
			</p>
		</div>
		
		<div id="dialog-message" class="hide">
			<div class="profile-info-row">
							<div class="profile-info-name">被代理人</div>

							<div class="profile-info-value">
								<select class="easyui-combogrid"
									style="width: 400px; height: 25px; line-height: 25px;"
									name="proxy" 
									data-options="panelWidth: 400,idField: 'USER_ID',textField: 'NAME',url: '${sessionScope.portalContextPath}/system/selectUsers.do',
			mode:'remote', 
			fitColumns:true,
			method: 'get',columns: [[
			{field:'USER_ID',title:'用户编号',width:150},
			{field:'NAME',title:'姓名',width:80},
			{field:'ID_CARD',title:'身份证号',width:200,align:'right'},
			{field:'DEPARTMENT_NAME',title:'所属部门',width:150,align:'right'}
			 ]]"></select>
							</div>
			</div>
		</div>
	<jsp:include page="/dynamic/common/footer-1.jsp" />
	<jsp:include page="/dynamic/common/footer-2.jsp" />
	<script type="text/javascript">
			jQuery(function($) {
				$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
					_title: function(title) {
						var $title = this.options.title || '&nbsp;'
						if( ("title_html" in this.options) && this.options.title_html == true )
							title.html($title);
						else title.text($title);
					}
				}));
				initWorkflowList();
				$('#simple-colorpicker-1').ace_colorpicker({pull_right:true}).on('change', function(){
					var color_class = $(this).find('option:selected').data('class');
					var new_class = 'widget-box';
					if(color_class != 'default')  new_class += ' widget-color-'+color_class;
					$(this).closest('.widget-box').attr('class', new_class);
				});
			
			
				// scrollables
				$('.scrollable').each(function () {
					var $this = $(this);
					$(this).ace_scroll({
						size: $this.data('height') || 100,
						//styleClass: 'scroll-left scroll-margin scroll-thin scroll-dark scroll-light no-track scroll-visible'
					});
				});
				$('.scrollable-horizontal').each(function () {
					var $this = $(this);
					$(this).ace_scroll(
					  {
						horizontal: true,
						styleClass: 'scroll-top',//show the scrollbars on top(default is bottom)
						size: $this.data('width') || 500,
						mouseWheelLock: true
					  }
					).css({'padding-top': 12});
				});
				
				$(window).on('resize.scroll_reset', function() {
					$('.scrollable-horizontal').ace_scroll('reset');
				});
		
			    $('.widget-container-col').sortable({
			        connectWith: '.widget-container-col',
					items:'> .widget-box',
					opacity:0.8,
					revert:true,
					forceHelperSize:true,
					placeholder: 'widget-placeholder',
					forcePlaceholderSize:true,
					tolerance:'pointer',
					start: function(event, ui){
						//when an element is moved, it's parent becomes empty with almost zero height.
						//we set a min-height for it to be large enough so that later we can easily drop elements back onto it
						ui.item.parent().css({'min-height':ui.item.height()})
						//ui.sender.css({'min-height':ui.item.height() , 'background-color' : '#F5F5F5'})
					},
					update: function(event, ui) {
						ui.item.parent({'min-height':''})
						//p.style.removeProperty('background-color');
					}
			    });
			   
			
			});
			 function initWorkflowList() {
					
					$
							.ajax({
								type : "post",
								url : "/workflow/workflow/findWorkflowList.do",
								data : {start:0,limit:999},
								beforeSend : function(XMLHttpRequest) {
								},
								success : function(rst, textStatus) {

									if (rst) {
										var html = new Array();
										var k=0;
										for ( var i in rst.list) {
											var o=rst.list[i];
											if(true){
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
		</script>
	
</body>
</html>