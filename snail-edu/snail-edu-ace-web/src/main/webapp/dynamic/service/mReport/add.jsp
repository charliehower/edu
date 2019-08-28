<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
	<%
String dwmReportId=request.getParameter("dwmReportId");
request.setAttribute("edit","true");
if(SnailUtils.isBlankString(dwmReportId)){
	Object obj = session.getAttribute(CommonKeys.SystemUser);
	SystemUser systemUser = (SystemUser) obj;
	dwmReportId=String.valueOf(new java.util.Date().getTime());
	request.setAttribute("edit","false");
}
request.setAttribute("dwmReportId",dwmReportId);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>基本信息</title>
</head>
<script type="text/javascript">
var dwmReportId='${dwmReportId}';
var edit=${edit};
</script>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet" href="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />


<body>
	<div class="page-content">

		<form id="fm-dwmReport">
		<div>
		<input type="hidden" name="dwmReportId" value="${dwmReportId }">
		<input type="hidden" name="status" value="0">
		<input type="hidden" name="categoryId" value="3">
		总结类型：
		<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="subCategoryId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=33',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
		学期：<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="type"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=32',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
            	周：<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="weekId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=34',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
            标题：<input class="easyui-validatebox textbox"
							style="width: 400px; height: 25px; line-height: 25px;"
							name="title" 
							data-options="required:false,validType:'length[1,200]'">
							发布单位：<input class="easyui-combobox"
							style="width: 100px; height: 25px; line-height: 25px;"
							name="departmentId"
							data-options="
                    url:'${pageContext.request.contextPath}/dwmReport/selectMyDeptUser.do?selected=true',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
		</div>					
					
			<div>
			<textarea rows="" cols="" name="content"></textarea>
			</div>
			<br />	
			<div id="filelist-history"></div>
					<div id="filelist">Your browser doesn't have Flash, Silverlight or HTML5 support.</div>
	
<div id="container">
    附件：<a id="pickfiles" href="javascript:;">[添加附件]</a> 
    <a id="uploadfiles" href="javascript:;">[上传]</a>
</div>
<br />
<pre id="service"></pre>
				<div>
					
		</form>
		<button class="btn btn-info" id="btn-dwmReport-submit" authority="false">
														<i class="ace-icon fa fa-check bigger-110"></i>
														保存
													</button>
				</div>
	
</div>

	<!-- /section:elements.tab.option -->



	
	
	
	<jsp:include page="../../common/footer-1.jsp" />
	<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
			<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
	
<script
		src="${pageContext.request.contextPath}/content/js/service/mReport/controller.js"></script>
		<script
		src="${pageContext.request.contextPath}/content/js/service/mReport/upload.js"></script>
		<jsp:include page="../../common/footer-2.jsp" />

<ckeditor:replaceAll basePath="/edu/ckeditor/" />
</body>
</html>