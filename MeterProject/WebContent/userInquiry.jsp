<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@page import="com.pwa.common.DateUtil"%>
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
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<br>
			<br>
			<strong>ตรวจสอบค่าน้ำ  </strong>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">

	<tr>
		<td align="center">
			<html:errors />
			<html:form action="/userInquiry">
				<html:hidden property="act" value="submit" />
                <br/>
                <table border="0" align="center" cellpadding="6">
                <tr>
                    <td align="right">
                        <font size="3">รหัสผู้ใช้ประปา &nbsp;&nbsp;</font>
                    </td>
                    <td align="left">
                        <html:text property="custcode" maxlength="7" />
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <font size="3">เดือน &nbsp;&nbsp;</font>
                    </td>
                    <td align="left">
                        <html:select property="month">
                            <html:option value=""></html:option>
                            <html:options collection="monthList" property="value" labelProperty="desc" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                      <font size="3">ปี &nbsp;&nbsp;</font>
                    </td>
                    <td align="left">
                        <html:select property="year">
                            <html:option value=""></html:option>
                            <html:options collection="yearList" property="value" labelProperty="desc" />
                        </html:select>
                    </td>
                </tr>
            </table>
				<br>
				<p>
					<html:button property="xsubmit" value="ยืนยัน "  onclick="javascript:return doSubmit() " />
					&nbsp;&nbsp;
				</p>
			</html:form>

		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="5" align="center">
	<html:messages id="message" message="true">${message}</html:messages>
</table>
<jsp:include page="footerRoot.jsp" />
