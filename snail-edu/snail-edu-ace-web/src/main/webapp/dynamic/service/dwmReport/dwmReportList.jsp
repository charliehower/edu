<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String categoryId = request.getParameter("categoryId");
	request.setAttribute("categoryId", categoryId);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>通知</title>
</head>

<jsp:include page="../../common/common.jsp" />


<body>
	<div class="page-content">
		<div class="widget-box transparent" id="recent-box">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">
					<i class="ace-icon glyphicon glyphicon-th-large green"></i><span id="_title"></span>
				</h4>

				<div class="widget-toolbar no-border"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-4">
					<table width="100%" >
					<thead>
											<tr style="font-size:14px;font-weight:800">
												<th height="25px">
													序号
												</th>
												<th>
													标题
												</th>
												<th width="150px">
													部门
												</th>
												<th width="80px">
													发布人
												</th>
												<th width="150px">
													发布时间
												</th>
												
											</tr>
										</thead>
						<tbody id="dwmReport-list-grid">
						</tbody>
					</table>
				</div>
			</div>


		</div>

	</div>






	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
	var categoryId = '${categoryId}';
	var name="日查日报";
	if(categoryId=="2"){
		name="周结周报";
	}
	if(categoryId=="3"){
		name="部门总结";
	}
	$("#_title").html(name);
</script>
	<script type="text/javascript">
		jQuery(function($) {

			initDwmReportList(categoryId);
		});
		function initDwmReportList(categoryId) {
			var dwmReport = "dwmReport";
			if (categoryId == "2") {
				dwmReport = "wReport";
			}
			if (categoryId == "3") {
				dwmReport = "mReport";
			}
			$
					.ajax({
						type : "post",
						url : "/edu/dwmReport/findListTop.do",
						data : {
							categoryId : categoryId,
							limit : 99999
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {

							if (rst) {
								var html = new Array();
								var k = 0;
								for ( var i in rst.list) {
									var o = rst.list[i];
									o.url = "/edu/dynamic/service/" + dwmReport
											+ "/preview.jsp?dwmReportId="
											+ o.dwmReportId + "&taskId=0"
									k++;

									html.push('<tr height="25px">');
									html.push('<td align="left" width="30">');
									html.push('<span class="badge badge-'+cssColor9[GetRandomNumSeq(k,8)]+'">'+k+'</span> ');

									html.push('</td>');

									html.push('<td align="left">');
									html
											.push('<a href="javascript:parent.addPanel(\''
													+ o.categoryName
													+ '\',\''
													+ o.url + '\',true)">');

									html.push(o.title);

									html.push('</a>');

									if (o.top == '1') {
										html
												.push('  <span class="badge badge-yellow">置顶</span> ');

									}
									html.push('</td>');
									html.push('<td width="150px" align="left">');
									html.push('<span class="badge badge-'+cssColor9[GetRandomNumSeq(0,8)]+'">');
									html.push(o.departmentName);
									html.push('</span>');
									html.push('</td>');
									html.push('<td width="80px" align="left">');
									html.push(o.name);
									html.push('</td>');

									html.push('<td width="150px" align="right">');
									html.push(o.publishTime);
									html.push('</td>');

									html.push('</tr>');

									//console.log(rst.list[i]);
								}

								$('#dwmReport-list-grid').html(html.join(''));
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {

						}
					});
		}
	</script>
</body>
</html>