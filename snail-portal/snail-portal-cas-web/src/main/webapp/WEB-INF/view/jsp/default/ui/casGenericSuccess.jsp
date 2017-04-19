<%@page import="java.util.*"%>
<%
ResourceBundle resource=ResourceBundle.getBundle("cas");
response.sendRedirect(resource.getString("cas.casGenericSuccessUrl"));
%>

