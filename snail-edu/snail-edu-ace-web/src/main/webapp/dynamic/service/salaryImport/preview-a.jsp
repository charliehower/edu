<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.beans.*"%>
<%@page import="org.platform.snail.edu.model.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Logger logger = Logger.getLogger(this.getClass());
	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);
	SalaryaMapper salaryaMapper = (SalaryaMapper) webApplicationContext
			.getBean("salaryaMapper");
	SalarybMapper salarybMapper = (SalarybMapper) webApplicationContext
			.getBean("salarybMapper");
	SalaryImportMapper salaryImportMapper = (SalaryImportMapper) webApplicationContext
			.getBean("salaryImportMapper");
	Map<String, Object> params = new HashMap<String, Object>();
	Enumeration<String> e = request.getParameterNames();
	String key = "";
	while (e.hasMoreElements()) {
		key = (String) e.nextElement();
		params.put(key, request.getParameter(key));
		logger.info(key + "=" + request.getParameter(key));
	}
	List<Salarya> list =salaryaMapper.findList(params);
	/*for(Salarya obj:list){
		logger.info(obj);
	}*/
	request.setAttribute("list", list);
	String salaryImportId = request.getParameter("salaryImportId");
	request.setAttribute("o",
			salaryImportMapper.selectByPrimaryKey(salaryImportId));
	
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>工资</title>
</head>

<jsp:include page="../../common/common.jsp" />
<style>
table td,th{word-break: keep-all;white-space:nowrap;}
</style>

<body>
	<div class="page-content">
		
	
				

									
									<div class="table-header">
										临聘职工发放年度：${o.year} 发放月份：${o.month}
									</div>

									<!-- <div class="table-responsive"> -->

									<!-- <div class="dataTables_borderWrap"> -->
									<div>
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">
														<label class="position-relative">
															<input type="checkbox" class="ace" />
															<span class="lbl"></span>
														</label>
													</th>
													<th>序号</th>
								<th>姓名</th>
								<th>职工卡号</th>
								<th>基本工资</th>
								<th>绩效工资</th>
								<th>应发工资</th>
								<th>个人缴交社保</th>
								<th>个人缴交住房公积金</th>
								<th>所得税</th>
								<th>应扣合计</th>
								<th>单位社保</th>
								<th>房改住房补贴</th>
								<th>单位缴交住房公积金</th>
								<th>实发合计</th>
													
												</tr>
											</thead>

											<tbody>
									<c:forEach var="item" items="${list}" varStatus="status">
							<tr>
							<td class="center">
														<label class="position-relative">
															<input type="checkbox" class="ace" />
															<span class="lbl"></span>
														</label>
													</td>
								<td>${item.序号}</td>
								<td>${item.姓名}</td>
								<td>${item.职工卡号}</td>
								<td>${item.基本工资}</td>
								<td>${item.绩效工资}</td>
								
								<td>${item.应发工资}</td>
								<td>${item.个人缴交社保}</td>
								<td>${item.个人缴交住房公积金}</td>
								<td>${item.所得税}</td>
								<td>${item.应扣合计}</td>
								
								<td>${item.单位社保}</td>
								<td>${item.房改住房补贴}</td>
								<td>${item.单位缴交住房公积金}</td>
								<td>${item.实发合计}</td>
								</tr>
							</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							






	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
<script
	src= "${sessionScope.portalContextPath}/content/assets/js/gz/jquery.dataTables.min.js"></script>
	<script
	src= "${sessionScope.portalContextPath}/content/assets/js/gz/jquery.dataTables.bootstrap.js"></script>
<script type="text/javascript">

			jQuery(function($) {
				var oTable1 = 
				$('#sample-table-2')
				.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.dataTable( {
					bAutoWidth: false,
					"aoColumns": [
					  { "bSortable": false },
					 null, null, null, null, null, null, null, null, null, null, null, null, null,
					  null
					]
			
					
					,
					"sScrollY": "300px",
					//"bPaginate": false,
			
					"sScrollX": "100%",
					"sScrollXInner": "120%",
					"bScrollCollapse": true
					//Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
					//you may want to wrap the table inside a "div.dataTables_borderWrap" element
			
					//"iDisplayLength": 50
			    } );
				
			
			
				$(document).on('click', 'th input:checkbox' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
				});
			
			
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			
			})
		</script>
</body>
</html>