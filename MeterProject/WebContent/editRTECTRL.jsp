<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="header.jsp"></jsp:include>
<!-- script -->
<script type="text/javascript">
   
   function doSubmit() {
	    document.forms[0].act.value = "submit";	
		document.forms[0].xsubmit.disabled = true; // name can't= "submit" for button
		startProgress();
		document.forms[0].submit();
	}
   function doSubmitAll() {	
			document.forms[0].xsubmit.disabled = true; // name can't= "submit" for button
			startProgress();
			document.forms[0].submit();
		}

</script>
<!-- content -->
<tr>

	<td align="center">
		<br>
		<strong>Upload File RTECTRL</strong>
		<html:form action="/editRTECTRL" enctype="multipart/form-data" >
		    <!-- <input type="hidden" name="usetoken" value="true"/>  -->
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
						<html:file property="assignFile" styleClass="browse"/>
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
						<html:button property="xsubmit" value="Upload "  onclick="javascript:return doSubmit() " />
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