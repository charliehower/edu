<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="http://10.1.118.54:9100/socket.io/socket.io.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function $(id){
	return document.getElementById(id);
}
var socket=io.connect('http://10.1.118.54:9100');
socket.on('news',function(data){
	$('chatinfo').innerHTML+=data+"<br>"
;});
function sendinfo(){
	var info_data={};
	info_data.chat_name=$('myname').value;
	info_data.words=$('words').value;
	info_data=JSON.stringify(info_data);
	socket.emit('data',info_data);
}
</script>
<div id="chatinfo"></div>
<input type="text" id="words"/>
<input type="text" id="myname" value="陈晓克"/>
<input type="button" id="button" value="say" onClick="sendinfo()"/>
</body>
</html>