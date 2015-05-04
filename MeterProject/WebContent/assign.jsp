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
	var text = 'ท่านกำลัง Import ข้อมูลมอบหมายเส้นทาง:'+'<%=((String[]) WebConst.getInstance().getAssignTypeMap().get(session.getAttribute("type")))[0]%>'+' ยืนยันหรือไม่ ?';
		document.forms[0].act.value = "submit";
		if(!confirm(text)){
		  return false;
		}
		document.forms[0].xsubmit.disabled = true; // name can't= "submit" for button
		startProgress();
		document.forms[0].submit();
	}
   function doSubmitAll() {	
		var text = 'ท่านกำลัง Import ข้อมูลมอบหมายเส้นทาง:'+'<%=((String[]) WebConst.getInstance().getAssignTypeMap().get(session.getAttribute("type")))[0]%>'+' ยืนยันหรือไม่ ?';
			document.forms[0].act.value = "submitAll";
			if(!confirm(text)){
			  return false;
			}
			document.forms[0].xsubmit.disabled = true; // name can't= "submit" for button
			startProgress();
			document.forms[0].submit();
		}

</script>
<!-- content -->
<tr>

	<td align="center">
		<br>
		<strong>มอบหมายเส้นทาง: <%=((String[]) WebConst.getInstance().getAssignTypeMap().get(session.getAttribute("type")))[0]%></strong>
		<html:form action="/assign" enctype="multipart/form-data" >
		    <!-- <input type="hidden" name="usetoken" value="true"/>  -->
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
				<%
     			String type = (String)session.getAttribute("type");
				if(type.equals("1")){
                 %>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td align="left">
					    <html:button property="xsubmit" value="มอบหมายทั้งหมด"  onclick="javascript:return doSubmitAll() " />
					</td>
				</tr>
				<%} %>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						ไฟล์
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
						เลือกชื่อพนักงาน
					</td>
					<td align="left">
						<html:select property="codeid" >
							<html:option value=""> -เลือกชื่อพนักงาน -</html:option>
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
						<html:button property="xsubmit" value="มอบหมายบางส่วน "  onclick="javascript:return doSubmit() " />
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