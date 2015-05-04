<%@ page pageEncoding="TIS-620" contentType="text/html; charset=TIS-620"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=TIS-620">
<title>Request Character Encoding Problem: Example</title></head>
<body>
<ol>
  <li>กรุณาป้อนข้อมูลภาษาไทยแล้วกดปุ่ม submit จะเห็นว่าภาษาไทยไม่ถูกต้อง</li>
  <li>กรุณาแก้ไขไฟล์ WEB-INF/web.xml เพื่อใช้ filter แล้วทดลองข้อ 1 ใหม่อีกครั้ง</li>
</ol>
<p>
  <%
out.println("Request Character Encoding="+request.getCharacterEncoding()+"<br>");
out.println("Response Character Encoding="+response.getCharacterEncoding()+"<br>");
String submit=request.getParameter("submit");
if(submit!=null || 1==1){
	String user_name=request.getParameter("name");	
%>
Your input is:<%=user_name%> 
  <%
}
%>
</p>
<form method="post" action="Thai.jsp" accept-charset="TIS-620">
  <table>
    <tr> 
      <td colspan="2" align="center"><font size='3'><b>ป้อนข้อมูล</b></font></td>
    </tr>
    <tr> 
      <td>ชื่อ</td>
      <td><input type="text" name="name" size=25></td>
    </tr>
    <tr> 
      <td colspan=2><center>
          <input type="submit" name="submit" value="submit">
        </center></td>
    </tr>
  </table>
</form></body></html>