<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.*"%>
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
		<strong> รายงาน: <%=((String[]) WebConst.getInstance().getAbNormalTypeMap().get(session.getAttribute("abNomalType")))[0]%></strong>
		<br>
	</td>
</tr>
<tr>
	<!--  emty form for pagging-->
	<html:form action="/reportAbNormal" method="get">
		<td>
			<jsp:include page="paging/navigator.jsp" />
		</td>
	</html:form>
</tr>
<tr>
	<!--  header table-->
	<td>
		<table width="100%" class="reportmain">
			<tr>
				<td>
					<strong>วันที่อ่านมาตรตั้งแต่:</strong> &nbsp;
					<c:out value="${startdate} - ${todate}" />
					<br>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td>
		<table class="reportdetail" width="1000" height="50" cellspacing="0" cellpadding="0" border="1">
			<tr align="center" class="reporthead">
				<td width="5%" height="15">
					พนักงาน
				</td>
				<td width="5%" height="15">
					เส้นทาง/ลำดับ
				</td>
				<td width="5%">
					หมายเลขผู้ใช้น้ำ
				</td>
				<td width="5%">
					หมายเลขมาตร
				</td>
				<td width="5%">
					ขนาดมาตร
				</td>
				<td width="15%">
					ชื่อ-นามสกุลผู้ใช้น้ำประปา
				</td>
				<td width="15%">
					ที่อยู่
				</td>
				<td width="10%">
					อ่านครั้งนี้/อ่านครั้งก่อน
				</td>
				<td width="5%">
					ยอดเงิน
				</td>
				<td width="5%">
					หน่วยน้ำปัจจุบัน
				</td>
				<td width="5%">
					หน่วยน้ำ AVG
				</td>
				<td width="5%">
					%
				</td>
				<td width="5%">
					Latitude
				</td>
				<td width="5%">
					Longitude
				</td>
			</tr>
			<!--  data here-->
			<logic-el:present name="pageObj" property="pageList">
				<logic-el:iterate name="pageObj" property="pageList" id="reportView" indexId="idx" type="com.pwa.web.view.ReportNormalView">
					<tr align="center" class="reportdetail">
						<td>
							<c:out value="${reportView.mtrrdrname}" />
						</td>
						<td>
							<c:out value="${reportView.rte}/${reportView.seq }" />
						</td>
						<td>
							<c:out value="${reportView.custcode}" />
						</td>
						<td>
							<c:out value="${reportView.meterno}" />
						</td>
						<td>
							<c:out value="${reportView.metersize}" />
						</td>
						<td>
							<c:out value="${reportView.name}" />
						</td>
						<td>
							<c:out value="${reportView.addr}" />
						</td>
						<td>
							<c:out value="${reportView.prsmtrcnt}/${reportView.lstmtrcnt}" />
						</td>
						<td>
							<fmt:formatNumber value="${reportView.tottrfwt}" type="number" maxFractionDigits="2" minFractionDigits="2" />
						</td>
						<td>
							<c:out value="${reportView.newcons}" />
						</td>
						<td>
							<c:out value="${reportView.avgwtusg}" />
						</td>
						<td>
							<fmt:formatNumber value="${reportView.diff}" type="number" maxFractionDigits="2" minFractionDigits="2" />
						</td>
						<td>
							<c:out value="${reportView.latitude}" />
						</td>
						<td>
							<c:out value="${reportView.longitude}" />
						</td>
					</tr>
				</logic-el:iterate>
			</logic-el:present>

		</table>
	</td>
</tr>
<jsp:include page="footer.jsp"></jsp:include>

