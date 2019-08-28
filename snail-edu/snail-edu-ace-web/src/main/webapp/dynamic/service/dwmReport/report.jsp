<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>report</title>
</head>

<jsp:include page="../../common/common.jsp" />


<body>
	<div class="page-content">
		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form action="#" id="fm-search">

						时间: <input class="easyui-datebox" name="startDate"
							style="width: 200px; height: 25px; line-height: 25px;"> 至
						<input class="easyui-datebox" name="endDate"
							style="width: 200px; height: 25px; line-height: 25px;">
						类别：<select name="categoryId">
							<option value="1">日查日报</option>
							<option value="2">周结周报</option>
							<option value="3">部门总结</option>
						</select>
						<button class="btn btn-info" id="btn-search" authority="false">

							查询 <i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>

				</div>
			</div>
		</div>

		<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr style="font-size: 14px; font-weight: 800">
								<th height="25px">序号</th>
								<th>部门</th>
								<th>发布数量</th>

							</tr>
						</thead>
						<tbody id="dwmReport-list-grid">
						</tbody>
					</table>

	</div>






	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
	
	<script type="text/javascript">
		jQuery(function($) {
			initDwmReportList({
				categoryId : '1'
			});
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
						initDwmReportList(params);
						return false;
					}
				});
			});
		});
		function initDwmReportList(params) {

			$.ajax({
				type : "post",
				url : "/edu/dwmReport/getReportByCategoryIdAndTime.do",
				data : params,
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {

					if (rst) {
						var html = new Array();
						var k = 0;
						for ( var i in rst.list) {
							var o = rst.list[i];

							k++;

							html.push('<tr height="25px">');
							html.push('<td align="center" width="80">');
							html.push('<span>'
									+ k + '</span> ');
							html.push('</td>');
							html.push('<td align="center">');
							html.push(o.NAME);
							html.push('</td>');
							html.push('<td align="center">');
							html.push(o.COUNT);
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