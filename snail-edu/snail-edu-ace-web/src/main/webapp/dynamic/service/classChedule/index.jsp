<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include  file="java.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>arrangement</title>
<script type="text/javascript">
	var data = {};
	<c:forEach var="item" items="${list}" varStatus="status">
	data['${item.week_id}${item.section_id}${classesId}'] = '${item.discipline_name}';
	</c:forEach>
</script>
</head>
<jsp:include page="../../common/common.jsp" />
<jsp:include page="../../common/footer-1.jsp" />

	<div class="page-content">

	
<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
				<input type="hidden" name="time" id="time">
					<form action="index.jsp" id="fm-search">
			
						年级：<input class="easyui-combobox"
					style="width: 200px; height: 25px; line-height: 25px;"
					name="gradeId" value="${gradeId }"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=14&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">

						班级：<input class="easyui-combobox"
					style="width: 200px; height: 25px; line-height: 25px;"
					name="classesId" value="${classesId }"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=15&gradeId=1,2,3&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
						<button class="btn btn-info" id="btn-search"
							authority="false">
							课表查询<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					
				</div>
			</div>
		</div>
<table style="width: 100%" id="grid-arr"
	class="table table-striped table-bordered table-hover">

	<thead>
		<tr>
			<th align="left">节次</th>
			<c:forEach var="item" items="${week}" varStatus="status">
				<th align="center">${item.week_name}</th>
			</c:forEach>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="item" items="${section}" varStatus="status">
			<tr>
				<td align="center">${item.section_name}</td>
				<c:forEach var="o" items="${week}" varStatus="status">

					<td align="center">
						<button authority="false"
							id="${o.week_id}${item.section_id}${classesId}"
							onclick="saveOrUpdateClassChedule('${o.week_id}','${item.section_id}','${classesId}',data['${o.week_id}${item.section_id}${classesId}'],'${o.week_id}${item.section_id}${classesId}')"
							type="button" class="btn btn-info">
							<script>
								document
										.write(data['${o.week_id}${item.section_id}${classesId}']
												|| '设置');
								if (!data['${o.week_id}${item.section_id}${classesId}']) {
									$('#${o.week_id}${item.section_id}${classesId}')
											.removeClass("btn-info");
									$('#${o.week_id}${item.section_id}${classesId}')
											.addClass("btn-warning");
								}
							</script>
						</button>

					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="dialog-message" class="hide">
	<form id="fm-search">
		<div class="profile-info-row">
			<div class="profile-info-name">学科</div>

			<div class="profile-info-value">
				<input class="easyui-combobox" 
					style="width: 200px; height: 25px; line-height: 25px;"
					name="search-disciplineId",
					id="cc"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=16',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
			</div>
		</div>
	</form>
</div>
	</div>

	<div class="login-container" style="display:none">
					

					<div class="position-relative">
						
						<div  class="forgot-box widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header red lighter bigger">
										<i class="ace-icon fa fa-key"></i> 系统提示
									</h4>

									<div class="space-6"></div>

									<p>
										<font color="#000033"><b> 系统信息：</b></font><b><font
											color="#FF0000">loading……</font></b>
									</p>


								</div>
								<!-- /.widget-main -->

								<div class="toolbar center">
									
								</div>
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.progress-bar-box -->

						
						
						
					</div>
					<!-- /.position-relative -->

					
				</div>
			<!--  end login-container-->

<script
		src="${pageContext.request.contextPath}/content/js/service/classChedule/controller.js"></script>
		
	<jsp:include page="../../common/footer-2.jsp" />
	
	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$("#grid-arr").css("width", $(".page-content").width());
			$("#grid-arr").css("height", window.innerHeight - layoutTopHeight);
		}
	</script>
</body>
</html>

