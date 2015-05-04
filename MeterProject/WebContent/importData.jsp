<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>
<!-- script -->
<script type="text/javascript">
	function doSubmit() {
		document.forms[0].act.value = "submit";
		document.forms[0].xsubmit.disabled = true; // name can't= "submit" for button
		startProgress();
		document.forms[0].submit();
	}
	function doCheck() {
		document.forms[0].act.value = "check";
		document.forms[0].xsubmit.disabled = true; // name can't= "submit" for button
		startProgress();
		document.forms[0].submit();
	}
	function doManual() {
		document.forms[0].act.value = "manual";
		document.forms[0].xsubmit.disabled = true; // name can't= "submit" for button
		startProgress();
		document.forms[0].submit();
	}
</script>
<!-- content -->
<tr>
	<td align="center">
		<br>
		<strong>Import ข้อมูลสถานะอ่านมาตรที่ส่งไม่สำเร็จ</strong>
		<html:form action="/importData" enctype="multipart/form-data">
			<html:hidden property="act" value="main" />
			<table border="0" align="center" cellpadding="6">
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						ไฟล์
					</td>
					<td align="left">
						<c:if test='${state ne "checked"}'>
							<html:file property="assignFile" styleClass="browse" />
						</c:if>
						<c:if test='${state eq "checked"}'>
							<html:text property="filename"  styleClass="browsecomplete" readonly="true"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
					</td>
					<td>
						<c:if test='${state ne "checked"}'>
							<html:button property="xsubmit" styleClass="submit" value="ตรวจสอบ " onclick="javascript:return doCheck() " />
						</c:if>
						<c:if test='${state eq "checked"}'>
							<html:button property="xsubmit" value="ยืนยัน " onclick="javascript:return doSubmit() " />
						</c:if>
						<c:if test='${role==1}'>
							<html:button property="xsubmit" styleClass="submit" value="Manual " onclick="javascript:return doManual() " />
						</c:if>
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
<jsp:include page="footer.jsp"></jsp:include>
