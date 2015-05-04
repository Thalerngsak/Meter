<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@page import="com.pwa.common.DateUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<jsp:include page="header.jsp"></jsp:include>
<!-- script -->
<script src="js/refresh.js" type="text/javascript"></script>
<!-- content -->
<tr>
	<!--  title table-->
	<td align="center">
		<br>
		<strong> รายงาน: สรุปข้อมูล</strong>
		<br>
	</td>
</tr>
<tr>
	<!--  emty form for pagging-->
	<html:form action="/reportSummary" method="get"> 
		<td>
			<jsp:include page="paging/navigator.jsp" />
		</td>
	</html:form>
</tr>

<!--  header table-->
<tr>
	<td>
		<table class="reportdetail" width="1000" height="50" cellspacing="0" cellpadding="0" border="1">
			<tr align="center" class="reporthead">
				<td width="5%" height="10">
					วันที่มอบหมายงาน
				</td>
				<td width="5%%" height="10">
					ชื่อพนักงานอ่านมาตร
				</td>
				<td width="5%">
					เส้นทาง
				</td>
				<td width="10%">
					จำนวนทั้งหมด
				</td> 
				<td width="5%">
					อ่านปกติ
				</td>
				<td width="5%">
					อ่านผิดปกติ
				</td>
				<td width="5%">
					ยังไม่อ่าน
				</td>
				<td width="5%">
					จำนวนใบแจ้งหนี้ที่ไม่ได้พิมพ์
				</td>
			</tr>
			<!--  data here-->
			<logic-el:present name="pageObj" property="pageList">
				<logic-el:iterate name="pageObj" property="pageList" id="reportView" indexId="idx" type="com.pwa.web.view.ReportSummaryView">
					<tr align="center" class="reportdetail">
						<td>
							<c:out value="${reportView.trandateStr}" />
						</td>
						<td>
							<c:out value="${reportView.mtrrdrname}" />
						</td>
						<td>
							<c:out value="${reportView.rte}" />
						</td>
						<c:set var="target" value="./reportSummary.do?act=submitToNormal&rte=${reportView.rte}&codeid=${reportView.mtrrdrname}&dateStr=${reportView.trandateStr}&reportType=${reportView.reportType}" />
						<td>
							<a href="<c:out value="${target}"/>"> <font size="2" color="blue"><c:out value="${reportView.custCount}" /> </font> </a>
						</td>
						<td>
							<a href="<c:out value="${target}&readflag=1"/>"> <font size="2" color="blue"><c:out value="${reportView.saveCount}" /> </font> </a>
						</td>
						<c:set var="targetAbnormal" value="./reportSummary.do?act=submitToNormal&rte=${reportView.rte}&codeid=${reportView.mtrrdrname}&dateStr=${reportView.trandateStr}&reportType=${reportView.reportType}&comment=99" />
						<td>
							<a href="<c:out value="${targetAbnormal}&readflag=1"/>"> <font size="2" color="blue"><c:out value="${reportView.abnormalCount}" /> </font> </a>
						</td>
						<td>
							<a href="<c:out value="${target}&readflag=0&comment=999"/>"> <font size="2" color="blue"><c:out value="${reportView.remainCount}" /> </font> </a>
						</td>
						<td>
							<a href="<c:out value="${target}&billflag=N"/>"> <font size="2" color="blue"><c:out value="${reportView.noBillCount}" /> </font> </a>
						</td>
					</tr>
				</logic-el:iterate>
			</logic-el:present>

		</table>
	</td>
</tr>
<jsp:include page="footer.jsp"></jsp:include>

