<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>
<!-- script -->
<script type="text/javascript">
	function doSubmit() {
		document.forms[0].act.value = "submit";
		document.forms[0].submit();
	}
</script>
<!-- content -->
<tr>
	<td align="center">
		<br>
		<strong>ปรับปรุงข้อมูลหลัก: <%=((String[]) WebConst.getInstance().getMasterTypeMap().get(session.getAttribute("type")))[0]%></strong>
		<html:form action="/importMasterData" enctype="multipart/form-data">
			<html:hidden property="act" value="main" />
			<table border="0" align="center" cellpadding="6">
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="center">
						<html:submit property="submit" value="ยืนยัน " onclick="javascript:return doSubmit() " />
					</td>
				</tr>
			</table>
		</html:form>
		<table width="100%" border="0" cellpadding="5" align="center">
			<html:messages id="message" message="true">${message}</html:messages>
		</table>
	</td>
</tr>
<jsp:include page="footer.jsp"></jsp:include>
