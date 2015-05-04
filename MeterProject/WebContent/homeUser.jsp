<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<jsp:include page="headerRoot.jsp" />
<table width="100%" cellspacing="0" cellpadding="0" align="center" bgColor="#c0c0c0">
	<tr>
		<td></td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<br>
			<strong>เลือกสาขาสำนักงานประปาสำหรับลูกค้า </strong>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<br>
			<a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=getServletContext().getContextPath()%>/userInquiry.jsp" > บางคล้า</a>
			<br>
			<a href="http://localhost:8080/meterWeb02/userInquiry.jsp">ตราด</a>
			<br>
			<a href="http://localhost:8080/meterWeb03/userInquiry.jsp">ปราจีนบุรี</a>
			<br>
		</td>
	</tr>
</table>
<jsp:include page="footerRoot.jsp" />