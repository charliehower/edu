<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/pushlet_js/ajax-pushlet-client.js"></script>
</head>
<body>
	<script type="text/javascript">
		PL.userId = 'piero';
		PL._init();
		PL.joinListen('myevent1');
		function onData(event) {
			document.dataEventDisplay.event.value = document.dataEventDisplay.event.value+"\n"+event.toString();
			//alert(event.get("eventVal"));
		}

		function sendnews() {
			//PL.publish("myevent1","key1=dlut&ba=ls");   
			p_publish('myevent1', 'key1', 'dlut', 'ba', 'ls');
		}
	</script>
	<form name="dataEventDisplay">
		<textarea cols="60" rows="10" name="event">
	</textarea>
	</form>
</body>
</html>