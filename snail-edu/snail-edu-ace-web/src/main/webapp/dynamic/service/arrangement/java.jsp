<%@page import="org.platform.snail.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="org.platform.snail.edu.dao.*"%>

<%
	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);
	ArrangementMapper arrangementMapper = (ArrangementMapper) webApplicationContext
			.getBean("arrangementMapper");
	
	HeadmasterMapper headmasterMapper = (HeadmasterMapper) webApplicationContext
			.getBean("headmasterMapper");
	TeamPrepareMapper teamPrepareMapper = (TeamPrepareMapper) webApplicationContext
			.getBean("teamPrepareMapper");
	TeamTrMapper teamTrMapper = (TeamTrMapper) webApplicationContext
			.getBean("teamTrMapper");
	TeamGradeMapper teamGradeMapper = (TeamGradeMapper) webApplicationContext
			.getBean("teamGradeMapper");
	String year = request.getParameter("year");
	String cyear = "";
	   Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH) + 1;
	    int years = cal.get(Calendar.YEAR);
		if(month<8){
			cyear=String.valueOf((years-1)+""+years);
		}else{
			cyear=String.valueOf((years)+""+(years+1));
		}
		request.setAttribute("cyear",
				cyear);
	
	if (year == null) {
		year = cyear;
	}
	request.setAttribute("year",
			year);
	String gradeId = request.getParameter("gradeId");
	if (gradeId == null) {
		gradeId = "1";
	}
	request.setAttribute("gradeId",
			gradeId);
	request.setAttribute("list",
			arrangementMapper.selectArrListByYear(year));
	request.setAttribute("classes",
			arrangementMapper.selectClassesListByGradeId(gradeId));
	request.setAttribute("dis",
			arrangementMapper.selectAllDisciplineList());
	request.setAttribute("grade",
			arrangementMapper.selectAllGradeList());
	request.setAttribute("listHeadmaster",
			headmasterMapper.selectHeadmasterListByYear(year));
	request.setAttribute("listTeamPrepare",
			teamPrepareMapper.selectTeamPrepareListByYear(year));
	request.setAttribute("listTeamTr",
			teamTrMapper.selectTeamTrListByYear(year));
	request.setAttribute("listTeamGrade",
			teamGradeMapper.selectTeamGradeListByYear(year));
%>
<%
Map<String,String> cfgt=new HashMap<String,String>();
Map<String,String> cfgc=new HashMap<String,String>();
cfgt.put("arrangement", "");
cfgc.put("arrangement", "");

cfgt.put("headmaster", "");
cfgc.put("headmaster", "");

cfgt.put("ptl", "");
cfgc.put("ptl", "");

cfgt.put("ttl", "");
cfgc.put("ttl", "");

cfgt.put("gtl", "");
cfgc.put("gtl", "");


String active=request.getParameter("active");
if(active==null){
	active="arrangement";
}
if(active.equals("arrangement")){
	cfgt.put("arrangement", "active");
	cfgc.put("arrangement", "in active");
}
if(active.equals("headmaster")){
	cfgt.put("headmaster", "active");
	cfgc.put("headmaster", "in active");
}
if(active.equals("ptl")){
	cfgt.put("ptl", "active");
	cfgc.put("ptl", "in active");
}
if(active.equals("ttl")){
	cfgt.put("ttl", "active");
	cfgc.put("ttl", "in active");
}
if(active.equals("gtl")){
	cfgt.put("gtl", "active");
	cfgc.put("gtl", "in active");
}
request.setAttribute("cfgt",
		cfgt);
request.setAttribute("cfgc",
		cfgc);
%>