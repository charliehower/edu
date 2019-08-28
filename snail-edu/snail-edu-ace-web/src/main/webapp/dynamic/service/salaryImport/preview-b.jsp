<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@page import="org.platform.snail.beans.*"%>
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
		System.out.println(key + "=" + request.getParameter(key));
	}
	request.setAttribute("list", salarybMapper.findList(params));
	String salaryImportId=request.getParameter("salaryImportId");
	request.setAttribute("o", salaryImportMapper.selectByPrimaryKey(salaryImportId));
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


<body>
	<div class="page-content">
		<div class="table-header">正式职工发放年度：${o.year} 发放月份：${o.month}</div>
								<div>
					<table id="sample-table-2" class="table table-striped table-bordered table-hover">
						<thead>
<!-- 							<tr > -->
<!-- 							<th class="center"> -->
<!-- 														<label class="position-relative"> -->
<!-- 															<input type="checkbox" class="ace" /> -->
<!-- 															<span class="lbl"></span> -->
<!-- 														</label> -->
<!-- 													</th> -->
													
<!-- 								<th >导入序号</th> -->
								
<!-- 								<th >职工姓名</th> -->
<!-- 								<th>职务级别</th> -->
<!-- 								<th>职务工资</th> -->
<!-- 								<th>级别薪级工资级别</th> -->
<!-- 								<th>级别薪级工资档次</th> -->
<!-- 								<th>级别薪级工资金额</th> -->
<!-- 								<th>见习工资</th> -->
<!-- 								<th>特区津贴档次</th> -->
<!-- 								<th>特区津贴金额</th> -->
<!-- 								<th>保留津贴档次</th> -->
<!-- 								<th>保留津贴金额</th> -->
<!-- 								<th>奖金工作津贴</th> -->
<!-- 								<th>房补改革津贴</th> -->
<!-- 								<th>物补生活津贴</th> -->
<!-- 								<th>基础津贴</th> -->
<!-- 								<th>活工资金额</th> -->
<!-- 								<th>考核工资</th> -->
<!-- 								<th>独生子女费</th> -->
<!-- 								<th>特岗津贴</th> -->
<!-- 								<th>特区增资</th> -->
<!-- 								<th>全国增资</th> -->
<!-- 								<th>应发合计</th> -->
<!-- 								<th>补扣金额</th> -->
<!-- 								<th>养老保险</th> -->
<!-- 								<th>个人所得税</th> -->
<!-- 								<th>个人缴交住房公积金</th> -->
<!-- 								<th>医疗保险</th> -->
<!-- 								<th>单位社保</th> -->
<!-- 								<th>应扣合计</th> -->
<!-- 								<th>单位缴交住房公积金</th> -->
<!-- 								<th>单位医保</th> -->
<!-- 								<th>实发合计</th> -->
<!-- 								<th>房改住房补贴</th> -->
<!-- 								<th>职业年金</th> -->
<!-- 							</tr> -->
						<tr >
							<th class="center">
														<label class="position-relative">
															<input type="checkbox" class="ace" />
															<span class="lbl"></span>
														</label>
													</th>
													
								<th >导入序号</th>
								
								<th >职工姓名</th>
								<th>职务级别</th>
								<th>职务工资</th>
								<th>级别薪级工资级别</th>
								<th>级别薪级工资档次</th>
								<th>级别工资</th>
								<th>见习工资</th>
								<th>特区津贴档次</th>
								<th>特区津贴金额</th>
								<th>保留津贴档次</th>
								<th>保留津贴金额</th>
								<th>奖金工作津贴</th>
								<th>房补改革津贴</th>
								<th>物补生活津贴</th>
								<th>岗位津贴</th>
								<th>活工资金额</th>
								<th>绩效工资</th>
								<th>独生子女费</th>
								<th>特岗津贴</th>
								<th>特区增资</th>
								<th>全国增资</th>
								<th>应发合计</th>
								<th>补扣金额</th>
								<th>养老保险</th>
								<th>个人所得税</th>
								<th>个人缴交住房公积金</th>
								<th>医疗保险</th>
								<th>单位社保</th>
								<th>单位养老</th>
								<th>单位缴交住房公积金</th>
								<th>单位医保</th>
								<th>实发合计</th>
								<th>房改住房补贴</th>
								<th>职业年金</th>
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
								<td>${item.职务级别}</td>
								<td>${item.职务工资}</td>
								<td>${item.级别薪级工资级别}</td>
								<td>${item.级别薪级工资档次}</td>
								<td>${item.级别薪级工资金额}</td>
								<td>${item.见习}</td>
								<td>${item.特区津贴档次}</td>
								<td>${item.特区津贴金额}</td>
								<td>${item.保留津贴档次}</td>
								<td>${item.保留津贴金额}</td>
								<td>${item.奖金工作津贴}</td>
								<td>${item.房补改革津贴}</td>
								<td>${item.物补生活津贴}</td>
								<td>${item.基础津贴}</td>
								<td>${item.活工资}</td>
								<td>${item.考核工资}</td>
								<td>${item.独生子女费}</td>
								<td>${item.特岗津贴}</td>
								<td>${item.特区增资}</td>
								<td>${item.全国增资}</td>
								<td>${item.应发合计}</td>
								<td>${item.补扣金额}</td>
								<td>${item.养老保险}</td>
								<td>${item.所得税}</td>
								<td>${item.个人缴交住房公积金}</td>
								<td>${item.医疗保险}</td>
								<td>${item.单位社保}</td>
								<td>${item.应扣合计}</td>
								<td>${item.单位缴交住房公积金}</td>
								<td>${item.单位医保}</td>
								<td>${item.实发合计}</td>
								<td>${item.房改住房补贴}</td>
								<td>${item.职业年金}</td>
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
$(document).ready(function(){
				var oTable1 = 
				$('#sample-table-2')
				.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.dataTable( {
					bAutoWidth: false	
				,
				"sScrollY": "300px",
				//"bPaginate": false,
		
				"sScrollX": "300%"
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