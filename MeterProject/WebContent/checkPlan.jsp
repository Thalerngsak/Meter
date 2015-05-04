<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="header.jsp"></jsp:include>
<!-- script -->
<script type="text/javascript">

</script>
<!-- content -->
<tr>
	<!--  title table-->
	<td align="center">
		<br>
		<strong> แผนการจดเลขมิเตอร์</strong>
		<br>
	</td>
</tr>
<tr>
	<!--  emty form for pagging-->
	<html:form action="/checkplan" method="get">
		<td>
			&nbsp;
		</td>
	</html:form>
</tr>

<!--  header table-->

<tr>
	<td>
		<table class="reportdetail" width="1000" height="50" cellspacing="0" cellpadding="0" border="1">
			<tr align="center" class="reporthead">
				<td width="5%" height="10">
					รหัสสาขา
				</td>
				<td width="5%">
					เส้นทาง/ลำดับ
				</td>
				<td width="5%">
					พนักงาน
				</td>
				<td width="5%">
					วันที่มอบหมายงาน 
				</td>
			</tr>
			<!--  data here-->
			      <logic:notEmpty name="headViewList">
                        <c:forEach var="empDomain" items="${headViewList}">
                            <tr align="center" bgcolor="#ffffff" class="reportdetail">
                                <td>
                                     <c:out value="${empDomain.br}"></c:out> 
                                </td>
                                <td>
                                  <c:out value="${empDomain.rte}"></c:out> 
                                </td>
                                <td>
                                   <c:out value="${empDomain.readerid}"></c:out>
                                </td>
                                <td>
                                   <c:out value="${empDomain.assigndate}"></c:out>
                                </td>
                            </tr>
                        </c:forEach>
                    <br />
                </logic:notEmpty>
		</table>
	</td>
</tr>		
<jsp:include page="footer.jsp"></jsp:include>
