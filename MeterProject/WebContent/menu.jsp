<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center"  >
	<tr>
		<td>
			<c:if test="${role==1}">
			<script type="text/javascript" language="JavaScript1.2" src="menu_js/billmenu.js"></script>
			</c:if>
			<c:if test="${role==2}">
			<script type="text/javascript" language="JavaScript1.2" src="menu_js/billstaffmenu.js"></script>
			</c:if>
			<c:if test="${role==3}">
			<script type="text/javascript" language="JavaScript1.2" src="menu_js/billusermenu.js"></script>
			</c:if>
		</td>
	</tr>
</table>
