<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <title>login</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace.onpage-help.css" />
		<script type="text/javascript">
		if (!window.console)
			window.console = {};
		if (!window.console.log)
			window.console.log = function() {
			};
		</script>
		<!--[if lte IE 8]>
			<script type="text/javascript" src="${pageContext.request.contextPath}/content/assets/js/gz/jquery1x.min.js"></script>
		<![endif]-->
	    <script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/content/assets/js/gz/jquery.min.js'>"+"<"+"/script>");
		</script>
<script
	src="${pageContext.request.contextPath}/content/assets/js/gz/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/content/assets/js/bootbox.min.js"></script>
		<style type="text/css">
	body {
		background-color: #FFFFFF;
		
		font-family: "微软雅黑";
		font-size: 1.2em;
	}
	</style>
	</head>
	<body  class="login-layout">
<%
	request.setAttribute("ch", "");
javax.servlet.http.Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
if(cookies!=null){
	for(javax.servlet.http.Cookie cookie : cookies){
		if(cookie.getName().equals("username")||cookie.getName().equals("password")||cookie.getName().equals("ch")){
	request.setAttribute((String)cookie.getName(), (String)cookie.getValue());	
		}
	}
}
%>
<script src="${pageContext.request.contextPath}/content/login.js"></script>

<div class="main-container" id="main_container">
	<div class="main-content">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
			<div class="center">
						<h1>
							<i class="ace-icon fa fa-leaf green"></i> <span class="red"></span>
							<span class="white" id="id-text2">${cfg.sys_name }</span>
						</h1>
						<h4 class="blue" id="id-company-text">&copy; ${cfg.sys_unit }</h4>
					</div>

					<div class="space-6"></div>
			</div>
		</div>
	</div>
	<div style="background-image: url('${cfg.sys_login_bg_img}');norepeat;height:400px">
	<div class="main-content"
		>
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div class="login-container" >
					<div class="position-relative">
						<div id="login-box"  class="login-box visible widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header blue lighter bigger">
										<i class="ace-icon fa fa-coffee green"></i> 请输入您的信息
									</h4>

									<div class="space-6"></div>

									<form:form method="post" id="fm1" cssClass="fm-v clearfix"
										commandName="${commandName}" htmlEscape="true">

										<h4 class="heade lighter bigger">
											<form:errors path="*" id="msg"
												cssClass="ace-icon fa fa-info-circle red" element="i" />
										</h4>
										<input type="hidden" name="lt" value="${loginTicket}" />
										<input type="hidden" name="execution"
											value="${flowExecutionKey}" />
										<input type="hidden" name="_eventId" value="submit" />
										<fieldset>
											<label class="block clearfix"> <span
												class="block input-icon input-icon-right"> <input
													type="text" class="form-control" name="username"
													placeholder="用户名" value="${username}" /> <i
													class="ace-icon fa fa-user"></i>
											</span>
											</label> <label class="block clearfix"> <span
												class="block input-icon input-icon-right"> <input
													type="password" class="form-control" name="password"
													placeholder="密码" value="${password}" /> <i
													class="ace-icon fa fa-lock"></i>
											</span>
											</label>

											<div class="space"></div>

											<div class="clearfix">
												<label class="inline"> <!--[if lte IE 8]>
													<input type="checkbox"  name="ch" ${ch} /> 
												<![endif]--> <!--[if gte IE 9]>
													<input type="checkbox" class="ace" name="ch" ${ch} /> 
												<![endif]--> <![if !IE]> <input type="checkbox" class="ace"
													name="ch" ${ch} /> <![endif]> <span class="lbl">
														十天内免登录 </span>
												</label>

												<button type="submit" id="btn-login-submit"
													class="width-35 pull-right btn btn-sm btn-primary">
													<i class="ace-icon fa fa-key"></i> <span class="bigger-110">登录</span>
												</button>
											</div>

											<div class="space-4"></div>
										</fieldset>
									</form:form>
									<div class="space-6"></div>
								
								</div>
								<!-- /.widget-main -->

								<div class="toolbar clearfix">
									<div>
										<a href="#" data-target="#forgot-box"
											class="forgot-password-link"> <i
											class="ace-icon fa fa-arrow-left"></i> 忘记密码了
										</a>
									</div>

									<div>
										
									</div>
								</div>
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.login-box -->
						<div id="progress-bar-box" class="forgot-box widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header red lighter bigger">
										<i class="ace-icon fa fa-key"></i> 系统提示
									</h4>

									<div class="space-6"></div>

									<p>
										<font color="#000033"><b> 系统信息：</b></font><b><font
											color="#FF0000">登录中……</font></b>
									</p>


								</div>
								<!-- /.widget-main -->

								<div class="toolbar center">
									<a href="#" data-target="#login-box" class="back-to-login-link">
										Back to login <i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</div>
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.progress-bar-box -->

						<div id="forgot-box" class="forgot-box widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header red lighter bigger">
										<i class="ace-icon fa fa-key"></i>找回密码
									</h4>

									<div class="space-6"></div>
									<p>输入注册的Email</p>
									<div class="space-6"></div>
									<form>
										<fieldset>
											<label class="block clearfix"> <span
												class="block input-icon input-icon-right"> <input
													type="email" name="email" id="email" class="form-control"
													placeholder="Email" /> <i class="ace-icon fa fa-envelope"></i>
											</span>
											</label>



											<div class="clearfix">
												<button type="button" id="btn-login-rp"
													class="width-35 pull-right btn btn-sm btn-danger">
													<i class="ace-icon fa fa-lightbulb-o"></i> <span
														class="bigger-110">发送</span>
												</button>
											</div>
										</fieldset>
									</form>
								</div>
								<!-- /.widget-main -->

								<div class="toolbar center">
									<a href="#" data-target="#login-box" class="back-to-login-link">
										返回登录 <i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</div>
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.forgot-box -->

						<div id="signup-box" class="signup-box widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									
							
								</div>

								<div class="toolbar center">
									<a href="#" data-target="#login-box" class="back-to-login-link">
										<i class="ace-icon fa fa-arrow-left"></i> Back to login
									</a>
								</div>
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.signup-box -->
					</div>
					<!-- /.position-relative -->

					<div class="navbar-fixed-top align-right">
						<br /> &nbsp; <a id="btn-login-dark" href="#">Dark</a> &nbsp; <span
							class="blue">/</span> &nbsp; <a id="btn-login-blur" href="#">Blur</a>
						&nbsp; <span class="blue">/</span> &nbsp; <a id="btn-login-light"
							href="#">Light</a> &nbsp; &nbsp; &nbsp;
					</div>
				</div>
				<!--  end login-container-->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.main-content -->
	<div align="center"><h6>建议WIN7以上系统使用IE9以上浏览器，XP、MAC系统使用<a href="http://rj.baidu.com/soft/detail/14744.html?ald" target="_blank">谷歌浏览器</a> 分辨率1024*768以上为佳</h6></div>
	</div>
</div>
<!-- /.main-container -->

</body>
</html>
<script type="text/javascript">
window.onload=function(){
	if(window.top!=window.self) window.top.window.location.href="${pageContext.request.contextPath}/login"; 
}
</script>
<%
//response.flushBuffer();
%>


