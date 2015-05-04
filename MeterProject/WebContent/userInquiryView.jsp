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
		<td>
			เลขที่ใบแจ้งหนี้:
			<font class="invVal"><c:out value="${view.invoicecnt}" /> </font>
		</td>
		<td>
			เลขที่ผู้ใช้น้ำ:
			<font class="invVal"><c:out value="${view.custcode}" /> </font>
		</td>
		<td>
			<div align="right">
				หน่วยงาน:
				<font class="invVal"><c:out value="${br}-${view.zn}" /> </font>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			วันที่แจ้งหนี้:
			<font class="invVal"><c:out value="${view.prsmtrrddtStr} (${view.time})" /> </font>
		</td>
		<td colspan="2">
			เส้นทาง:
			<font class="invVal"><c:out value="${view.rte}.${view.seq}" /> </font>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			ชื่อ:
			<font class="invVal"><c:out value="${view.name}" /> </font>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			ที่อยู่:
			<font class="invVal"><c:out value="${view.addr}" /> </font>
		</td>
	</tr>
	<tr>
		<td>
			เลขในมาตร:
			<!-- Show (E) if coment ==1 -->
			<font class="invVal"> <c:if test="${view.comment == '01'}">(E) </c:if> <c:out value="${view.prsmtrcntcal}" /> </font>
		</td>
		<td>
			จำนวนที่ใช้:
			<!--  Show (avg) if comment == 15,1 -->
			<font class="invVal"> <fmt:formatNumber value="${view.prswtusgcal}" /> ลิตร <c:if test="${view.comment == '15'}">(เฉลี่ย)</c:if> </font>
		</td>
        <td>
           แผนที่:
            <!--  Show (avg) if comment == 15,1 -->
            <a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=getServletContext().getContextPath()%>/map.jsp?lat=${view.latitude}&long=${view.longitude}" style="">คลิ๊ก</a>
        </td>
        </tr>
</table>
<br>
<table width="90%" border="1" cellspacing="0" align="center" class="invSub" bordercolordark="#333333" bordercolorlight="#333333">
	<tr>
		<td width="75%">
			<table width="100%" border="0" cellpadding="0" align="center" class="invtitle" height="100">
				<tr>
					<td>
						&nbsp;ค่าน้ำ
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;(ส่วนลด)
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;ค่าบริการทั่วไป
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;ภาษีมูลค่าเพิ่ม
					</td>
				</tr>
			</table>
		</td>
		<td>
			<table width="100%" border="0" cellpadding="0" align="center" class="invVal" height="100">
				<tr align="right">
					<td>
						<fmt:formatNumber value="${view.nortrfwt}" minFractionDigits="2" maxFractionDigits="2" />
					</td>
				</tr>
				<tr align="right">
					<td>
						<c:if test="${view.discntamt == 0}"> &nbsp; </c:if>
						<c:if test="${view.discntamt != 0}">
							<!--   if water supply has a discount -> show the wording "2(ลด" before the discount amount -->
							<c:if test="${view.discntcode == 2}">2(ลด </c:if>
							<c:if test="${view.discntcode != 2}">(</c:if><fmt:formatNumber value="${view.discntamt}" minFractionDigits="2" maxFractionDigits="2" />)
						</c:if>
					</td>
				</tr>
				<tr align="right">
					<td>
						<fmt:formatNumber value="${view.srvfee}" minFractionDigits="2" maxFractionDigits="2" />
					</td>
				</tr>
				<tr align="right">
					<td>
						<fmt:formatNumber value="${view.vat}" minFractionDigits="2" maxFractionDigits="2" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;รวมเงินค่าน้ำประจำเดือน
		</td>
		<td class="invVal" align="right">
			<fmt:formatNumber value="${view.tottrfwt}" minFractionDigits="2" maxFractionDigits="2" />
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;ค้างชำระ&nbsp;&nbsp;&nbsp;
			<font class="invVal"><c:out value="${view.nomonth}" /> </font>&nbsp;&nbsp;&nbsp;เดือน
		</td>
		<td class="invVal" align="right">
			<c:if test="${view.billamt == 0}"> &nbsp; </c:if>
			<c:if test="${view.billamt != 0}">
				<fmt:formatNumber value="${view.billamt}" minFractionDigits="2" maxFractionDigits="2" />
			</c:if>
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;รวมเงินที่ต้องชำระทั้งสิ้น
		</td>
		<td class="invVal" align="right">
			<fmt:formatNumber value="${view.alltoprice}" minFractionDigits="2" maxFractionDigits="2" />
		</td>
	</tr>
</table>

<!-- billamt criteria -->
<c:if test="${view.billamt == 0}">
	<!--  show bank date -->
	<c:if test="${view.printflag == '2'}">
		<table width="90%" align="center" class="invVal">
			<tr>
				<td>
					<font class="invVal">&nbsp;&nbsp;&nbsp;หักผ่านธนาคารภายในวันที่ <c:out value="${view.payBankDateStr}" /> </font>
				</td>
			</tr>
			<tr>
				<td>
					<font class="invVal">&nbsp;&nbsp;&nbsp;โปรดตรวจสอบยอดเงินในบัญชีของท่านด้วย </font>
				</td>
			</tr>
		</table>
	</c:if>
</c:if>
<c:if test="${view.billamt != 0}">
	<table width="90%" align="center" class="invVal">
		<tr>
			<td>
				<font class="invVal">&nbsp;&nbsp;&nbsp;โปรดชำระที่สำนักงานประปา </font>
			</td>
		</tr>
	</table>
</c:if>
<!--  show code -->
<table width="90%" align="center" class="invVal">
	<tr>
		<td>
			<font class="invVal">&nbsp;&nbsp;&nbsp; ID.<c:out value="${view.codeid}  (${view.comment})" /> </font>
		</td>
	</tr>
</table>
<table width="90%" align="center" class="invNotice">
	<tr>
		<td>
			* โปรดชำระค่าน้ำทั้งหมดภายในวันที่
			<font class="invVal">&nbsp;&nbsp;&nbsp;<c:out value="${view.duepaydateStr}" /> </font>
		</td>
	</tr>
	<tr>
		<td>
			ถ้าเกินกำหนด ท่านจะถูกระงับการใช้น้ำในวันที่
			<font class="invVal">&nbsp;&nbsp;&nbsp; <c:out value="${view.suspenddateStr}" /> </font>
		</td>
	</tr>
	<tr>
		<td>
			* หากท่านชำระเงินดังกล่าวแล้วต้องขออภัยด้วย
		</td>
	</tr>


	<tr align="center">
		<td>
			<br>
			<a href="index.jsp">กลับหน้าหลัก</a>
		</td>
	</tr>

</table>

<jsp:include page="footerRoot.jsp" />
