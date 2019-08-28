<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@include  file="java.jsp"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	 <fmt:setLocale value="zh_CN"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>值班表</title>
	<style media=print> 
.Noprint{display:none;} 
.PageNext{page-break-after: always;} 
</style>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	height:100%;
	overflow:hidden;
}
-->
.b {
	border-left: 1px #030303 solid;
	border-top: 1px #030303 solid;
	border-right: 1px #030303 solid;
	border-bottom: 1px #030303 solid;
}
.bYesLT {
	border-left: 1px #030303 solid;
	border-top: 1px #030303 solid;
}
.bYesLR {
	border-left: 1px #030303 solid;
	border-right: 1px #030303 solid;
}
.bYesLB {
	border-left: 1px #030303 solid;
	border-bottom: 1px #030303 solid;
}
.bYesTR {
	border-top: 1px #030303 solid;
	border-right: 1px #030303 solid;
}
.bYesTB {
	border-top: 1px #030303 solid;
	border-bottom: 1px #030303 solid;
}
.bYesRB {
	border-right: 1px #030303 solid;
	border-bottom: 1px #030303 solid;
}
.bYesL {
	border-left: 1px #030303 solid;
}
.bYesT {
	border-top: 1px #030303 solid;
}
.bYesR {
	border-right: 1px #030303 solid;
}
.bYesB {
	border-bottom: 1px #030303 solid;
}
.bNoL{
	border-top: 1px #030303 solid;
	border-right: 1px #030303 solid;
	border-bottom: 1px #030303 solid;
}
.bNoT {
	border-left: 1px #030303 solid;
	border-right: 1px #030303 solid;
	border-bottom: 1px #030303 solid;
}
.bNoB {
	border-left: 1px #030303 solid;
	border-top: 1px #030303 solid;
	border-right: 1px #030303 solid;
}
.bNoR{
	border-left: 1px #030303 solid;
	border-top: 1px #030303 solid;
	border-bottom: 1px #030303 solid;
}
</style>

</head>

<body>
	<center class="Noprint">
		<p>
			<OBJECT id=WebBrowser
				classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0>
			</OBJECT>
			<input type=button value=打印
				onclick=document.all.WebBrowser.ExecWB(6,1)> <input
				type=button value=直接打印 onclick=document.all.WebBrowser.ExecWB(6,6)>
			<input type=button value=页面设置
				onclick=document.all.WebBrowser.ExecWB(8,1)> <input
				type=button value=打印预览 onclick=document.all.WebBrowser.ExecWB(7,1)>
		</p>
		<hr align="center" width="100%" size="1" noshade>
	</center>
	<table width="800" align="left" cellpadding="0" cellspacing="0">
		<tr>
			<td height="50" align="center" valign="middle"
				style="font-size: 20px; font-weight: bold;">
				<fmt:formatDate value="${o.dutyStart}" type="time" timeStyle="default" pattern="yyyy年MM月dd日 E"/> 第${WEEK_OF_YEAR }周值班情况记载表&nbsp;&nbsp;&nbsp;签名：${o.name }
			</td>
		</tr>
		<tr>
			<td height="30" align="center" valign="middle" class="b"
				style="font-size: 16px; font-weight: bold;">早自习情况记载<input
				type="hidden" id="dayDutyid" name="dayDuty.dayDutyId"
				value="f4b93c9c4a9f5aed014ac8e13bb906a6" />
			</td>
		</tr>
		<tr>
			<td class="bYesLR">
				<table width="800" align="center" cellpadding="0" cellspacing="0"
					style="table-layout: fixed;">
					<tr>
						<td width="60" align="center" valign="middle" class="bYesRB"
							style="font-size: 14px; font-weight: bold; min-height: 60px;">
							学<br> 生
						</td>
						<td width="340" class="bYesRB" style="word-wrap: break-word">
							&nbsp;</td>
						<td width="60" align="center" valign="middle" class="bYesRB"
							style="font-size: 14px; font-weight: bold;">教<br> 师
						</td>
						<td width="340" class="bYesB" style="word-wrap: break-word">
							&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="30" align="center" valign="middle" class="bNoT"
				style="font-size: 16px; font-weight: bold;">上课时间情况记载</td>
		</tr>
		<tr>
			<td class="bYesLR">
				<table width="800" align="center" cellpadding="0" cellspacing="0"
					style="table-layout: fixed;">
					<tr>
						<td width="60" align="center" valign="middle" class="bYesRB"
							style="font-size: 14px; font-weight: bold; min-height: 60px;">
							学<br> 生
						</td>
						<td width="340" class="bYesRB" style="word-wrap: break-word">
							&nbsp;</td>
						<td width="60" align="center" valign="middle" class="bYesRB"
							style="font-size: 14px; font-weight: bold;">教<br> 师
						</td>
						<td width="340" class="bYesB" style="word-wrap: break-word">
							&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="30" align="center" valign="middle" class="bNoT"
				style="font-size: 16px; font-weight: bold;">晚自习情况记载</td>
		</tr>
		<tr>
			<td class="bYesLR"><table width="800" align="center"
					cellpadding="0" cellspacing="0" style="table-layout: fixed;">
					<tr>
						<td width="60" align="center" valign="middle" class="bYesRB"
							style="font-size: 14px; font-weight: bold; min-height: 60px;">
							学<br> 生
						</td>
						<td class="bYesRB" style="word-wrap: break-word">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td class="bYesLR"><table width="800" align="center"
					cellpadding="0" cellspacing="0" style="table-layout: fixed;">
					<tr>
						<td width="60" align="center" valign="middle" class="bYesRB"
							style="font-size: 14px; font-weight: bold; min-height: 60px;">学<br>
							校<br> 大<br> 事<br> 记<br> 载
						</td>
						<td class="bYesRB" style="word-wrap: break-word">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
	</table>

</body>
</html>