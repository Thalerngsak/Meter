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
		<strong> ��§ҹ: <%=((String[]) WebConst.getInstance().getReportTypeMap().get(session.getAttribute("type")))[0]%></strong>
		<br>
	</td>
</tr>
<tr>
	<!--  emty form for pagging-->
	<html:form action="/reportNormal" method="get">
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
					�ѹ����ͺ���§ҹ
				</td>
				<td width="5%">
					��ѡ�ҹ
				</td>
				<td width="5%">
					��鹷ҧ/�ӴѺ
				</td>
				<td width="5%">
					�����Ţ�������
				</td>
				<td width="12%">
					����-���ʡ�ż�����ӻ�л�
				</td>
				<td width="13%">
					�������
				</td>
				<td width="5%">
					�ѹ��訴˹���
				</td>
				<td width="5%">
					����
				</td>
				<td width="5%">
					�Ţ��ҹ���駹��
				</td>
				<td width="5%">
					�Ţ��ҹ���駡�͹
				</td>
				<td width="5%">
					˹��¹��
				</td>
				<td width="5%">
					˹��¹�� AVG
				</td>
				<td width="8%">
					�����˵�
				</td>
				<td width="5%">
					�ӹǹ�Թ����ͧ����
				</td>
				<td width="5%">
					�ӹǹ���駷������
				</td>
				<td width="5%">
					Latitude
				</td>
				<td width="5%">
					Longitude
				</td>
				<td width="5%">
					ʶҹ�
				</td>
				
			</tr>
			<!--  data here-->
			<logic-el:present name="pageObj" property="pageList">
				<logic-el:iterate name="pageObj" property="pageList" id="reportView" indexId="idx" type="com.pwa.web.view.ReportNormalView">
					<tr align="center" bgcolor="#ffffff" class="reportdetail">
						<td>
							<c:out value="${reportView.trandateStr}" />
						</td>
						<td>
							<c:out value="${reportView.mtrrdrname}" />
						</td>
						<td >
							<c:out value="${reportView.rte}" /><br>/<c:out value="${reportView.seq}" />
						</td>
						<td>
							<c:out value="${reportView.custcode}" />
						</td>
						<td>
							<c:out value="${reportView.name}" />
						</td>
						<td>
							<c:out value="${reportView.addr}" />
						</td>
						<td>
							<c:out value="${reportView.prsmtrrddtStr}" />
						</td>
						<td>
							<c:out value="${reportView.time}" />
						</td>
						<td>
							<c:out value="${reportView.prsmtrcnt}" />
						</td>
						<td>
							<c:out value="${reportView.lstmtrcnt}" />
						</td>
						<td>
							<c:out value="${reportView.newcons}" />
						</td>
						<td>
							<c:out value="${reportView.avgwtusg}" />
						</td>
						<td>
							<c:out value="${reportView.commentdec}" />
						</td>
						<td>
							<fmt:formatNumber value="${reportView.tottrfwt+reportView.billamt}" type="number" maxFractionDigits="2" minFractionDigits="2" />
						</td>
						<td>
							<c:out value="${reportView.notimes}" />
						</td>
						<td>
							<c:out value="${reportView.latitude}" />
						</td>
						<td>
							<c:out value="${reportView.longitude}" />
						</td>
						<c:if test='${reportView.readflag != "0"}'>
							<td class="readflag">
								��ҹ�ҵ�����
							</td>
						</c:if>
						<c:if test='${reportView.readflag == "0"}'>
							<td>
								�ʹ��Թ���
							</td>
						</c:if>
					</tr>
				</logic-el:iterate>
			</logic-el:present>
		</table>
	</td>
</tr>
<jsp:include page="footer.jsp"></jsp:include>

