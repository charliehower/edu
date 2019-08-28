<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include  file="java.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>arrangement</title>
</head>
<jsp:include page="../../common/common.jsp" />
<jsp:include page="../../common/footer-1.jsp" />
<script type="text/javascript">
var year='<%=year%>';
</script>
<body>
	<div class="page-content">

		<!-- #section:elements.tab.option -->
		<div class="tabbable">
			<ul class="nav nav-tabs padding-16" id="myTab4">
				<li class="${cfgt.arrangement}"><a data-toggle="tab" href="#arrangement">
						<i class="green ace-icon glyphicon glyphicon-user bigger-125"></i>
						任课安排

				</a></li>
				<li class="${cfgt.headmaster}"><a data-toggle="tab" id="tab-group-headmaster"
					href="#headmaster"> <i
						class="green ace-icon fa fa-users bigger-125"></i> 班主任安排
				</a></li>
				<li class="${cfgt.ptl}"><a data-toggle="tab" id="tab-group-discribline" href="#ptl">
						<i class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
						备课组长安排
				</a></li>
				<li class="${cfgt.ttl}"><a data-toggle="tab" id="tab-group-discribline"
					href="#ttl"> <i
						class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
						教研组长安排
				</a></li>
				<li class="${cfgt.gtl}"><a data-toggle="tab" id="tab-group-discribline"
					href="#gtl"> <i
						class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
						年级组长安排
				</a></li>
			</ul>

			<div class="tab-content">
				<div id="arrangement" class="tab-pane ${cfgc.arrangement}">
					<%@include  file="arrangement.jsp"%>
				</div>
				<div id="headmaster" class="tab-pane ${cfgc.headmaster}">
					<%@include  file="headmaster.jsp"%>
				</div>
				<div id="ptl" class="tab-pane ${cfgc.ptl}">
				<%@include  file="teamPrepare.jsp"%>
				</div>
				<div id="ttl" class="tab-pane ${cfgc.ttl}">
					<%@include  file="teamTr.jsp"%>
				</div>
				
				<div id="gtl" class="tab-pane ${cfgc.gtl}">
				<%@include  file="teamGrade.jsp"%>
				</div>
			</div>
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
		src="${pageContext.request.contextPath}/content/js/service/arrangement/controller-arrangement.js"></script>
<script
		src="${pageContext.request.contextPath}/content/js/service/arrangement/controller-headmaster.js"></script>
		<script
		src="${pageContext.request.contextPath}/content/js/service/arrangement/controller-teamPrepare.js"></script>
<script
		src="${pageContext.request.contextPath}/content/js/service/arrangement/controller-teamTr.js"></script>
<script
		src="${pageContext.request.contextPath}/content/js/service/arrangement/controller-teamGrade.js"></script>
		
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

