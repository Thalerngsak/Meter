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
<script language="javascript" type="text/javascript">
function doDownload(fileName) {

	window.setTimeout('window.location="./fileDownloadExport.do?fileName='+fileName+'"; ',250);
	
}
     
    
 </script>
<br>
<table class="reportdetail" width="1000" height="50" cellspacing="0" cellpadding="0" border="1">
			<tr align="center" class="reporthead">
				<td width="10%" height="10">
					วันที่
				</td>
				<td width="10%">
					รหัสพนักงาน
				</td>
				<td width="10%">
					เส้นทาง
				</td>
				<td width="70%" height="10">
					ชื่อไฟล์
				</td>
			</tr>
			
			<!--  data here-->

			<logic-el:present name="exportDataViewList">
				<logic-el:iterate name="exportDataViewList"  id="exportDataView" indexId="idx" type="com.pwa.web.view.ExportDataView">
					<tr align="center" bgcolor="#ffffff" class="reportdetail">
			    <td>
			       <c:out value="${exportDataView.trandate}" />
				</td>
				<td>
					<c:out value="${exportDataView.codeid}" />
				</td>
				<td>
					<c:out value="${exportDataView.rte}" />
				</td>
				<td>
				<a href="javascript:doDownload('${exportDataView.fileName}');" >
			       <c:out value="${exportDataView.fileName}" />
			      </a>
				</td>
			</tr>
				</logic-el:iterate>
				
			</logic-el:present>
			
		</table>
		<table width="90%" align="center" class="invNotice">
	
			<tr align="center">
				<td>
					<br>
					<a href="index.jsp">กลับหน้าหลัก</a>
				</td>
			</tr>

		</table>
		
<jsp:include page="footerRoot.jsp" />
