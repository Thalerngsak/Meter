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
</script>
<!-- content -->
<tr>
	<td align="center">
		<br>
		<strong>Import ·ºπ°“√®¥‡≈¢¡‘‡µÕ√Ï</strong>
		<html:form action="/importplan" enctype="multipart/form-data">
			<html:hidden property="act" value="main" />
			<table border="0" align="center" cellpadding="6">
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						‰ø≈Ï
					</td>
					<td align="left">
					<html:file property="importPlanFile" styleClass="browse" />
					<%-- 
						<c:if test='${state ne "checked"}'>
							<html:file property="assignFile" styleClass="browse" />
						</c:if>
						<c:if test='${state eq "checked"}'>
							<html:text property="filename"  styleClass="browsecomplete" readonly="true"/>
						</c:if>
					--%>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
					</td>
					<td>
					<html:button property="xsubmit" value="¬◊π¬—π " onclick="javascript:return doSubmit() " />
					<%-- 
						<c:if test='${state ne "checked"}'>
							<html:button property="xsubmit" styleClass="submit" value="µ√«® Õ∫ " onclick="javascript:return doCheck() " />
						</c:if>
						<c:if test='${state eq "checked"}'>
							<html:button property="xsubmit" value="¬◊π¬—π " onclick="javascript:return doSubmit() " />
						</c:if>
				    --%>
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
