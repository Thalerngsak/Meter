<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!-- script -->
<script type="text/javascript">
	function doSubmit() {
		document.forms[0].act.value = "submit";
		document.forms[0].submit();
	}
</script>
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
			<strong>���͡�Ң��ӹѡ�ҹ��л�����Ѻ���˹�ҷ��</strong>
		</td>
	</tr>
</table>
<html:form method="get">
			<html:hidden property="act" value="main" />
			<table border="0" align="center" cellpadding="6">
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						���͡�Ң�
					</td>
					<td align="left">
						<html:select property="branch" >
							<html:option value="">��س��к��Ң�</html:option>
						 	<html:options name ="branchList" collection="branchList" property="value" labelProperty="desc" /> 
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
						<html:submit property="submit"  value="�׹�ѹ " onclick="javascript:return doSubmit() " />
					</td>
				</tr>
			</table>
		</html:form>
		<table width="100%" border="0" cellpadding="5" align="center">
			<html:messages id="message" message="true">${message}</html:messages>
		</table>
 <%-- 
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<br>
			<a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=getServletContext().getContextPath()%>/login.jsp" style="">�ҧ����</a>
			<br>
			<a href="http://localhost:8080/meterWeb02/login.do?act=main">��Ҵ</a>
			<br>
			<a href="http://localhost:8080/meterWeb03/login.do?act=main">��Ҩչ����</a>
			<br>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<br>
			<strong>���͡�Ң��ӹѡ�ҹ��л�����Ѻ�١��� </strong>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<br>
			<a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=getServletContext().getContextPath()%>/userInquiry.jsp" >�ҧ����</a>
			<br>
			<a href="http://localhost:8080/meterWeb02/userInquiry.jsp">��Ҵ</a>
			<br>
			<a href="http://localhost:8080/meterWeb03/userInquiry.jsp">��Ҩչ����</a>
			<br>
		</td>
	</tr>
</table>
--%>
<jsp:include page="footerRoot.jsp" />