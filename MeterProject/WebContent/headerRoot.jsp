<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
%>

<html>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=TIS-620">
	<link href="style.css" rel="stylesheet" type="text/css">

	<head>
		<title>ระบบอ่านมาตรมือถือ</title>
	</head>
<script src="calendar/popcalendar_right_emp.js" type="text/javascript"></script>
<script type="text/javascript">
	st_jspath ="<%=getServletContext().getContextPath()%>"; // doble qutoe must enclose !!!!
	document.write('<script type="text/javascript" src="' + st_jspath + '/menu_js/stmenu.js"><\/script>');
</script>
	<body>
		<table width="800" height="400" border="1"  cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td valign="top" colspan="3">
					<table width="100%"    class="headerTitle"  align="center">
						<tr>
							<td valign="top">
								<img src="images/logo2.gif">
							</td>
							<td>
								<center>
									<font size="5" color="orange"><b>ระบบอ่านมาตรมือถือ</b> </font>
								</center>
							</td>
							<td width="150" valign="top" align="right">
								<c:if test="${role != null}">
									<a href="home.do?act=main" class="logout"><font color="red"  size="2" > ออกจากระบบ</font> </a>
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<%  if(!request.getRequestURI().contains("/home") && !request.getRequestURI().endsWith(getServletContext().getContextPath()+"/")){ %>
									<font size="2" color="orange">&nbsp;สำนักงานประปา: <c:out value="${br}/${head}"></c:out> </font>
								<% }%>
							</td>
						</tr>					
					</table>
