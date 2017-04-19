<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
session.invalidate();
String exit=org.platform.snail.utils.Config.getProperty("cas.login.url");
//response.sendRedirect(exit+"/logout");
%>
<!-- basic scripts -->
		<!--[if lte IE 8]>
			<script type="text/javascript" src="${pageContext.request.contextPath}/content/assets/js/gz/jquery1x.min.js"></script>
		<![endif]-->
	    <script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/content/assets/js/gz/jquery.min.js'>"+"<"+"/script>");
		</script>
<script>
jQuery(function($) {
		var basePath="/dynamic/common/exit.jsp"
		var url1="/edu"+basePath;
		var url2="/workflow"+basePath;
		exit(url1);
		exit(url2);
		location.href="<%=exit%>/logout";
});
function exit(url){
	$.ajax({
		type : "post",
		url : url,
		data:{time:new Date()},
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(rst, textStatus) {
			console.log(rst);
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
			
		}
	});
}
</script>