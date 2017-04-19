<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/jquery-ui.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/content/assets/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/assets/js/jquery-ui.min.js"></script>
</head>
<body>
<div id="dialog" title="Basic dialog">
  <p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
</div>
<script>
  $(function() {
    $( "#dialog" ).dialog();
  });
  </script>
</body>

</html>