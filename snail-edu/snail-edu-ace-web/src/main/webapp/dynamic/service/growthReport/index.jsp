<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>grade</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	
</script>
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
						考核日期: <input class="easyui-datebox" name="startDate"
							style="width: 200px; height: 25px; line-height: 25px;">


						<button class="btn btn-info" id="btn-search" authority="false">
							查询 <i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-excel" authority="false">
							导出EXCEL <i
								class="ace-icon fa fa-file-excel-o  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>

				</div>
			</div>
		</div>
		<div style="height: 50px"></div>
		<div id="tb-excel" style="text-align: center">
			<h2>坪山高级中学文明班级量化评比公告</h2>
			<div id="_time"></div>
			<br>
			<table border=1 style="text-align: center" align="center">
				<thead>
					<tr>
						<td rowspan=2>序号</td>
						<td rowspan=2>班级</td>
						<td rowspan=2>班主任</td>
						<td rowspan=2>考勤请假<br>（10分）
						</td>
						<td colspan=2>卫生<br>（15分）
						</td>
						<td rowspan=2>文明礼仪<br>（10分）
						</td>
						<td colspan=3>两操一礼<br>（10分）
						</td>
						<td rowspan=2>安全<br>（10分）
						</td>
						<td colspan=2>专项检查<br>（15分)
						</td>
						<td rowspan=2>宿舍管理<br>（20分 ）
						</td>
						<td rowspan=2>学风建设<br>（10分 ）
						</td>
						<td rowspan=2>加分项<br>（0~10分 ）
						</td>
						<td rowspan=2>合计</td>
					</tr>
					<tr>
						<td>包干区<br>（5分 ）
						</td>
						<td>教室厕所工具<br>（10分）
						</td>
						<td>升国旗仪式<br>（3分）
						</td>
						<td>课间操<br>（4分）
						</td>
						<td>眼保健操<br>（3分）
						</td>
						<td>违禁品检查<br>（10分）
						</td>
						<td>周末卫生检查<br>（5分）
						</td>
					</tr>
				</thead>

				<tbody id="grid-table-body"></tbody>
			</table>
		</div>

	</div>
	<jsp:include page="../../common/footer-1.jsp" />

	<script
		src="${pageContext.request.contextPath}/content/js/service/growthReport/controller.js"></script>

	<jsp:include page="../../common/footer-2.jsp" />

</body>
</html>