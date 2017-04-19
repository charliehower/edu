<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="org.platform.snail.model.Resources"%>
<%@page import="org.platform.snail.utils.CommonKeys"%>
<%@page import="java.util.*"%>
function breadcrumbs(){
	var html = [];
	html.push('<div class="breadcrumbs" id="breadcrumbs">');					
					html.push('<ul class="breadcrumb">');
						html.push('<li>');
							html.push('<i class="ace-icon fa fa-home home-icon"></i>');
							html.push('<a href="#">主页</a>');
						html.push('</li>');
						<% 
						List<Resources> items=(List<Resources>)request.getAttribute(CommonKeys.CWebPath);
						int i=1;
						for(Resources r:items){	
							if(i==items.size()){
						%>
							html.push('<li>');
								//html.push('<i class="ace-icon <%=r.getResourcesIcon()%>"></i>');
							html.push('<%=r.getResourcesName()%>');
							html.push('</li>');
						<%
							}else{
								%>
								html.push('<li>');
									//html.push('<i class="ace-icon <%=r.getResourcesIcon()%>"></i>');
								html.push('<a href="#"><%=r.getResourcesName()%></a>');
								html.push('</li>');
								<%
							}
							i++;
						}
						%>
					html.push('</ul>');
				html.push('</div>');
	//return html.join('');
	return '';
}