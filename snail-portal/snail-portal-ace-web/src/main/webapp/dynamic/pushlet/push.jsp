<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="nl.justobjects.pushlet.core.Dispatcher"%>
<%@page import="nl.justobjects.pushlet.core.Event"%>
<%@page import="nl.justobjects.pushlet.core.SessionManager"%>
<%@page import="java.util.*"%>
<%
	Event event = Event.createDataEvent("myevent1");
	event.setField("key1", new Date().toLocaleString());
	if (SessionManager.getInstance().hasSession("piero")) {
		Dispatcher.getInstance().unicast(event, "piero"); //向ID为piero的用户推送  
		System.out.println("success....");
	} else {
		System.out.println("piero do not login");
	}
	String q=request.getParameter("q")==null?"":request.getParameter("q");
	if(q.equals("m")){
		Dispatcher.getInstance().multicast(event);
	}
	if(q.equals("b")){
		Dispatcher.getInstance().broadcast(event);
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>push</title>
</head>
<body>
pushed
</body>
</html>