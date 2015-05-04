<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<jsp:include page="headerRoot.jsp" />
<!-- script -->
<br>
<table width="90%" border="0" cellspacing="0" cellpadding="1" align="center" class="invTitle">
    <tr>
		<td colspan="3">
			��������Ţ���:
			<font class="invVal"><c:out value="${view.custcode}" /> </font>
		</td>
	</tr>
	<tr>
		<td>
			����:
			<font class="invVal"><c:out value="${view.name}" /> </font>
		</td>
		<td colspan="2">
			�������:
			<font class="invVal"><c:out value="${view.addr}" /> </font>
		</td>
	</tr>
	<tr>
		<td>
			ʶҹ��������§:
			<font class="invVal"><c:out value="${view.mlocation}" /> </font>
		</td>
		<td>
			�����Ţ�ҵ�:
			<font class="invVal"><c:out value="${view.meterno}" /> </font>
		</td>
		<td>
			��Ҵ:
			<font class="invVal"><c:out value="${view.metersize}" /> </font>
		</td>
	</tr>
	<tr>
		<td>
			��鹷ҧ��ҹ�ҵ�:
			<font class="invVal"><c:out value="${view.rte}" /> </font>
		</td>
		<td>
			�ӴѺ�ҵ�:
			<font class="invVal"><c:out value="${view.seq}" /> </font>
		</td>
		<td>
			�������������:
			<font class="invVal"><c:out value="${view.usetype}" /> </font>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			������:
			<font class="invVal"><c:out value="${view.mtrmkcode}" /> </font>
		</td>
	</tr>
</table>
<br>
<table class="reportdetail" width="1000" height="50" cellspacing="0" cellpadding="0" border="1">
			<tr align="center" class="reporthead">
				<td width="5%" height="10">
					����͹
				</td>
				<td width="5%">
					��Ҿ�ҵ�
				</td>
				<td width="5%">
					ʶҹ���Ҩ�
				</td>
				<td width="5%">
					�ѹ�����ҹ�ҵ�
				</td>
				<td width="5%">
					�Ţ��ҹ
				</td>
				<td width="5%">
					˹��¹��
				</td>
				<td width="5%">
					�ʹ�����ҹ��
				</td>
				<td width="5%">
					�Ţ��������
				</td>
				<td width="15%">
					�����˵�
				</td>
			</tr>
			
			<!--  data here-->
			
			<logic-el:present name="viewList">
				<logic-el:iterate name="viewList"  id="reportView" indexId="idx" type="com.pwa.web.view.InvoiceView">
					<tr align="center" bgcolor="#ffffff" class="reportdetail">
			    <td>
			       <c:out value="${reportView.prsmtrrddty}" />/<c:out value="${reportView.prsmtrrddtm}" />
				</td>
				<td>
					<c:out value="${reportView.mtrstat}" />
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					<c:out value="${reportView.prsmtrrddt}" />
				</td>
				<td>
					<c:out value="${reportView.prsmtrcnt}" />
				</td>
				<td>
					<c:out value="${reportView.newcons}" />
				</td>
				<td>
					<c:out value="${reportView.tottrfwt}" />
				</td>
				<td>
					<c:out value="${reportView.invoicecnt}" />
				</td>
				<td>
				    <c:out value="${reportView.commentdec}" />
				</td>
			</tr>
				</logic-el:iterate>
				
			</logic-el:present>
			
		</table>
		<table width="90%" align="center" class="invNotice">
	
			<tr align="center">
				<td>
					<br>
					<a href="index.jsp">��Ѻ˹����ѡ</a>
				</td>
			</tr>

		</table>
		
<jsp:include page="footerRoot.jsp" />
