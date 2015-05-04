<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.*"%>
<%@page import="com.pwa.common.DateUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<jsp:include page="header.jsp"></jsp:include>

<!-- script -->
<!-- content -->
	<!--  emty form for token?-->
	<html:form action="/reportNormal"></html:form>
<tr>
	<td align="center">
		&nbsp;
	</td>
</tr>

<jsp:include page="footer.jsp"></jsp:include>