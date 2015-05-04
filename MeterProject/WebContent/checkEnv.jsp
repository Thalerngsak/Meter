<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.*"%>
<%@page import="com.pwa.common.*"%>
<%@page import="com.pwa.common.DateUtil"%>
<%@page import="java.io.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<jsp:include page="headerRoot.jsp" />
<%
   out.println(DateUtil.format(new Date(),"dd MMMM yyyy HH:mm:ss"));
   out.println("<br>");
	out.println("heap=" + Runtime.getRuntime().totalMemory() / 1000 + " KB, max=" + Runtime.getRuntime().maxMemory() / 1000 + " KB");
%>
<table class="reportdetail" cellspacing="0" cellpadding="0" border="1">
	<%
		ArrayList al = Env.getPropList();
		for (int i = 0; i < al.size(); i++) {
			String val = (String) al.get(i);
			String mark = "";
			if (val.contains("encod")) {
				mark = "class=\"readflag\"";
			}
			System.out.println(val + "=" + val);
			out.println("<tr  class=\"reportdetail\">");
			out.println("<td " + mark + ">" + val + "</td>");
			out.println("</tr>");
		}
	%>
</table>

<table class="reportdetail" cellspacing="0" cellpadding="0" border="1">
	<%
		Properties p = System.getProperties();
		Enumeration k = p.keys();
		while (k.hasMoreElements()) {
			String key = (String) k.nextElement();
			String mark = "";
			if (key.contains("encod")) {
				mark = "class=\"readflag\"";
			}
			System.out.println(key + "=" + p.getProperty(key));
			out.println("<tr  class=\"reportdetail\">");
			out.println("<td " + mark + ">" + key + "</td><td>" + p.getProperty(key) + "</td>");
			out.println("</tr>");
		}
	%>
</table>

<%
	// jdk1.4
	out.println("ByteArrayOutputStream()).getEncoding()=" + new java.io.OutputStreamWriter(new java.io.ByteArrayOutputStream()).getEncoding()
			+ "<br>");
	System.out.println("ByteArrayOutputStream()).getEncoding()="
			+ new java.io.OutputStreamWriter(new java.io.ByteArrayOutputStream()).getEncoding());
	// jdk1.5
	out.println("java.nio.charset.Charset.defaultCharset().name()=" + java.nio.charset.Charset.defaultCharset().name());
	System.out.println("java.nio.charset.Charset.defaultCharset().name()=" + java.nio.charset.Charset.defaultCharset().name());
%>
<jsp:include page="footerRoot.jsp" />