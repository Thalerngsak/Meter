<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.pwa.web.helper.*"%>
<jsp:include page="header.jsp"></jsp:include>
<!-- script -->
<script type="text/javascript">
	function doSubmit() {
		document.forms[0].act.value = "submit";
		document.forms[0].xsubmit.disabled = true; // name can't= "submit" for button
		startProgress();
		document.forms[0].submit();
	}
</script>

<!-- content -->
<tr>
	<td align="center">
		<br>
		<strong>Export ข้อมูลสถานะอ่านมาตร: </strong>
		<html:form action="/exportData">
			<html:hidden property="act" value="main" />
			<table border="0" align="center" cellpadding="6">
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						ระบุวันที่
					</td>
					<td align="left">
						<html:text property="dateStr" styleClass="date" readonly="true" />
						<html:img src="calendar/icon-appt.gif" styleClass="calendar" onclick="popUpCalendarRight(this, dateStr, 'dd mmm yyyy', '0', '0', 'left');" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						เลือกชื่อพนักงาน
					</td>
					<td align="left">
						<html:select property="codeid">
							<html:option value=""> - เลือกพนักงาน -</html:option>
							<html:options collection="codeIDList" property="value" labelProperty="desc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td align="left">
						<html:button property="xsubmit" value="ยืนยัน " onclick="javascript:return doSubmit() " />
					</td>
				</tr>
			</table>
		</html:form>
		<jsp:include page="progressBar.jsp"></jsp:include>
		<table width="100%" border="0" cellpadding="5" align="center">
			<html:messages id="message" message="true">${message}</html:messages>
		</table>
	</td>
</tr>
<c:if test="${success == '1'}">
	<script language="javascript" type="text/javascript">
     <!--
      window.setTimeout('window.location="./fileDownload.do"; ',250);
     // -->
 </script>
</c:if>
<jsp:include page="footer.jsp"></jsp:include>

