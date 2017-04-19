<%@page import="org.platform.snail.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.jbpm.api.*"%>
<%@ page import="org.jbpm.api.task.*"%>
<%@ page import="org.jbpm.api.history.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.platform.snail.edu.dao.*"%>
<%@ page import="org.platform.snail.edu.vo.*"%>
<%@page import="org.platform.snail.workflow.service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	javax.servlet.ServletContext servletContext = request.getSession()
	.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
	.getRequiredWebApplicationContext(servletContext);
	WorkflowService workflowService = (WorkflowService) webApplicationContext
	.getBean("workflowService");
	RepairsMapper repairsMapper = (RepairsMapper) webApplicationContext
	.getBean("repairsMapper");
	String instanceId = request.getParameter("instanceId");
	request.setAttribute("o", workflowService
	.findHistoryTaskByProcessInstanceId(instanceId));
	//两者一致
	String  repairsId=instanceId;
	RepairsVo vo=repairsMapper.selectVoByPrimaryKey(repairsId);
	request.setAttribute("vo", vo);
	Map<String,?> p=workflowService.getVariablesByInstanceId(instanceId);
	request.setAttribute("p", p);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>entry</title>
</head>
<jsp:include page="../../common/common.jsp" />
<body>
	<div class="page-content">

		<table style="width: 100%"
			class="table table-striped table-bordered table-hover">
			<tr>
				<td align="center" colspan="4"><strong>${o.other.name }</strong>

				</td>
			</tr>
			<tr>
				<th>申请单号：</th>
				<td align="left">${o.other.instanceId }</td>
				<th>申请日期：</th>
				<td align="left">${o.other.date }</td>
			</tr>
			<tr>
				<th>申 请 人：</th>
				<td align="left">${o.other.assignee }${vo.tel}</td>
				<th>当前状态：</th>
				<td align="left">${o.other.activeTaskName}</td>
			</tr>
			<tr>
				<th>所属部门：</th>
				<td align="left">${o.other.dpFullName}</td>

				<th>部门负责人：</th>
				<td align="left">${o.other.leaderFullName}</td>
			</tr>
			<tr>
				<th>代理申请人：</th>
				<td align="left">${p.proxy_assignee}</td>
				<th>所属部门：</th>
				<td align="left">${p.proxy_dpFullName}</td>
			</tr>
		<tr>
				<td align="center" colspan="4"><strong>详细情况</strong>

				</td>
			</tr>
			<tr>

				<th>报修地址：</th>
				<td>${vo.fullName}</td>
				<th>报修时间：</th>
				<td><fmt:formatDate value="${vo.repairsTime}" type="both"/></td>
			</tr>
			<tr>
				<th>报修类型：</th>
				<td>${vo.repairsCategoryName}</td>
				<th>分项目：</th>
				<td>${vo.subCategoryName}</td>

			</tr>
			<tr>
				<th>具体情况：</th>
				<td>${vo.describtion}</td>
				<th>备注：</th>
				<td>${vo.remark}</td>
			</tr>


			<tr>
				<th>维修人员：</th>
				<td>${vo.repairsUserName}</td>
				<th>故障类型：</th>
				<td>${vo.faultCategoryName}</td>


			</tr>


			<tr>
				<th>故障情况说明：</th>
				<td>${vo.faultDescribtion}</td>
				<th>维修完成时间：</th>
				<td><fmt:formatDate value="${vo.responseTime}" type="both"/></td>
			</tr>

		</table>
		
		<table style="width: 100%"
			class="table table-striped table-bordered table-hover">
			<tr>
				<td align="center" colspan="4"><strong>日志</strong></td>
			</tr>
			<tr>
				<td width="60px">序号</td>
				<td width="200px">日期</td>
				<td width="120px" align="left">操作人</td>
				<td align="left">结果</td>
			</tr>
			<c:forEach var="item" items="${o.list}" varStatus="status">
				<tr>
					<td align="left">${status.index+1}</td>
					<td align="left">${item.createTime}</td>
					<td align="left">${item.variables.assignee}</td>
					<td align="left">${item.variables.taskName}<c:choose>
							<c:when test="${item.variables.rs=='1'}">  
    【通过】  
    </c:when>
							<c:when test="${item.variables.rs=='0'}">  
    【驳回】 
    </c:when>

							<c:otherwise>

							</c:otherwise>
						</c:choose> ${item.variables.evaluation}

					</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	

	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
	
</body>
</html>