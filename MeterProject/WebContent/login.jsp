<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<jsp:include page="headerRoot.jsp" />
<!-- script -->
<script type="text/javascript">
	function doSubmit() {
		document.forms[0].act.value = "submit";
		document.forms[0].submit();
	}
</script>

            
<table width="100%" cellspacing="0" cellpadding="0" align="center" bgColor="#c0c0c0">
	<tr>
		<td></td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<br>
			<br>
			<strong>เข้าสู่ระบบ </strong>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">

	<tr>
		<td align="center">
			<html:errors />
			<html:form action="/login" >
				<html:hidden property="act" value="main" />
				<br>
				<p>
					<font size="3">ชื่อผู้ใช้ &nbsp;&nbsp;</font>
					<html:text property="username" />
				</p>
				<p>
					<font size="3">รหัสผ่าน&nbsp;</font>
					<html:password property="password" />
				</p>
				<p>
					<html:submit property="submit" onclick="javascript:return doSubmit() " />
					&nbsp;&nbsp;
					<html:reset></html:reset>
				</p>
			</html:form>

		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="5" align="center">
	<html:messages id="message" message="true">${message}</html:messages>
</table>

<jsp:include page="footerRoot.jsp" />
