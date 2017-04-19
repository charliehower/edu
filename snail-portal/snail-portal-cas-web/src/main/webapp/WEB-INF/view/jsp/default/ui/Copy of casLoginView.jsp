<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:directive.include file="includes/top.jsp" />
<%
javax.servlet.http.Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
if(cookies!=null){
for(javax.servlet.http.Cookie cookie : cookies){
	if(cookie.getName().equals("username")||cookie.getName().equals("password")){
	
	request.setAttribute((String)cookie.getName(), (String)cookie.getValue());

	}
}
}
%>
<style>
/*全局定义*/
*{margin:0; padding:0;}
ul,li,ol{list-style:none;}
img{border:0;}
.blue{ color:#1f5daa;text-decoration:underline;}
/*登陆*/
body{font-family:"宋体"; font-size:12px; background:#7fc1ea url(${pageContext.request.contextPath}/content/images/dlbj.jpg) repeat-x;}
/*常用按钮、输入框、文本框*/
.inputtext{ width:380px; height:24px;line-height:24px; border:1px solid #cfcfcf; padding:2px 0px 0px 2px;font-size:14px;}
.inputtext2{ width:183px; height:16px; border:1px solid #B5AFA0; padding:2px 0px 0px 2px;}
.xgb{ width:380px; height:24px;line-height:24px; padding:2px 0px 0px 2px;font-size:14px; border:1px solid #6688AA;
	border-color: #778899 #AABBCC #AABBCC #8899AA;background:#fefbbd;}

#denglumain {
    margin: 0 auto;
    width: 1024px;height:568px;background:url(${pageContext.request.contextPath}/content/images/dlbj2.jpg) no-repeat 0 0;	
	font-family: 'Microsoft Yahei';
	color:#797979;

}
#main{width: 680px;margin-left:340px;padding-top: 270px;}

.denglu_center_left {
    float: left;
    height: auto;
    padding: 6px 0 10px 19px;
    width: 380px;
}
.denglu_center_left ul li {
    float: left;
    padding-bottom: 6px;
    width: 380px;font-size:14px;
}

</style>
<form:form method="post" id="fm1" cssClass="fm-v clearfix"
	commandName="${commandName}" htmlEscape="true">
	<div id="denglumain">
		<div id="main">
		<div class="denglu_center_left">
		<div id="login_msg">
			<c:if test="${not pageContext.request.secure}">
							${sessionScope.codeErrorMsg}
					  	</c:if>
			<form:errors path="*" id="msg" cssClass="errors" element="div" />
		</div>
		
			<ul>
				<li>
					<span class="left">用户名：</span>
					<span>
						<input type="text" name="username" class="inputtext" value="${username}" maxlength="20" onFocus="this.className='xgb'" onBlur="this.className='inputtext'" />
					</span>
				</li>
				<li><span class="left">密 &nbsp; &nbsp;码：</span>
						<span>
							<input type="password" name="password" class="inputtext" maxlength="20" onFocus="this.className='xgb'" onBlur="this.className='inputtext'" />
						</span>
				</li>
				
				<li style="padding-bottom: 16px;"><span class="left">&nbsp;</span><span><input
						name="ch" type="checkbox" checked="true" id="ch"/> <font color="#9D9D9A">记住用户名</font>&nbsp;&nbsp;<a
						href="#" class="blue">忘记密码</a> </span></li>
				 <li><span class="left" style="padding-left:250px;"><input  type="image" style="border-width:0px;" src="${pageContext.request.contextPath}/content/images/dlan.jpg" name="btnSubmit" ></span>
			</li>
			</ul>
		</div>
	</div>
</div>
	<div>
		<input type="hidden" name="lt" value="${loginTicket}" /> 
		<input	type="hidden" name="execution" value="${flowExecutionKey}" /> <input type="hidden" name="_eventId" value="submit" />
		
	</div>
</form:form>


<jsp:directive.include file="includes/bottom.jsp" />
<script type="text/javascript">
function valid(){
	document.getElementById("login_msg").style.display="none";
	if(document.getElementById("login_msg").innerText&&document.getElementById("login_msg").innerText!=""){
		alert(document.getElementById("login_msg").innerText);
	}
}
$(document).ready(
		function() {
			valid();
		}
);
</script>


