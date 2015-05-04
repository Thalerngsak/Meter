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
		<strong>ข้อมูลหลัก: Config</strong>
		<html:form action="/config" enctype="multipart/form-data">
			<html:hidden property="act" value="main" />
			<table border="0" align="center" cellpadding="6">
				<tr>
					<td>
						BR:
					</td>
					<td align="left">
						<html:text property="br"  maxlength="7" size="7"/>
					</td>
				</tr>
				<tr>
					<td>
						HEAD:
					</td>
					<td align="left">
						<html:text property="head"  />
					</td>
				</tr>
				<tr>
					<td>
						TELNO:
					</td>
					<td align="left">
						<html:text property="telno"  />
					</td>
				</tr>
				<tr>
					<td>
						LOWTYPE1:
					</td>
					<td align="left">
						<html:text property="lowtype1"  maxlength="7" size="7"/>
					</td>
				</tr>
				<tr>
					<td>
						LOWTYPE2:
					</td>
					<td align="left">
						<html:text property="lowtype2"  maxlength="7" size="7"/>
					</td>
				</tr>
				<tr>
					<td>
						LOWTYPE3:
					</td>
					<td align="left">
						<html:text property="lowtype3"  maxlength="7" size="7"/>
					</td>
				</tr>
				<tr>
					<td>
						BANKDATE:
					</td>
					<td align="left">
						<html:text property="bankdate"  maxlength="2" size="2"/>
					</td>
				</tr>
				<tr>
					<td>
						PAYDATE:
					</td>
					<td align="left">
						<html:text property="paydate" maxlength="1" size="1"/>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="center">
						<html:submit property="submit"  value="ยืนยัน " onclick="javascript:return doSubmit() " />
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
