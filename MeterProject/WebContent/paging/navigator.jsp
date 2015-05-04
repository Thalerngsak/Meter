<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<script language="javascript" type="text/javascript">
// Modify support Get method for refresh

	function todo(act){
		var form = document.forms[0];
		//var url = form.action.split("?",1);
		//form.action = url + "?act=" + act;
		form.act.value = act;
		form.submit();
	}
	
	function goto(lastPage,field) {
		var form = document.forms[0];
		if(validateNavigate(document.forms[0].displayCurrentPage, lastPage)) {
			//var url = form.action.split("?",1);
			//form.action = url+='?act=gotoPage&page='+field.value;
			form.act.value ="gotoPage";
			form.page.value =field.value;
	 		form.submit();
		}
	}
	
	function gotoMod(val) {
		var form = document.forms[0];
			form.act.value ="gotoPage";
			form.page.value =val;
	 		form.submit();
	}
	
	
	function validateNavigate(field1, field2){
		curPg = field1.value;
		lastPg = field2.value;
		if (curPg == "" || curPg <= 0) {
			alert("Page number can not null.");
			field1.select();
			return false;
		}
		if (lastPg == "" || lastPg == 0) return false;
		if (isNaN(curPg)) {
			alert("Please input number only.");
			field1.select();
			return false;
		}
		if (Number(curPg) > Number(lastPg)) {
			alert("Page "+curPg+" can not more than last page "+lastPg);
			field1.select();
			return false;
		}
		return true;
	}
</script>
<input type="hidden"  name="act"/>
<input type="hidden"  name="page"/>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="navBar"> 
  <td><strong> &nbsp; <fmt:formatNumber  value="${pageObj.listSize}"/> รายการ </strong></td>
    <td height="16" align="right"><table width="100" border="0" cellspacing="0" cellpadding="2">
        <tr align="right" valign="bottom">
        	<bean:define id="xPaging" name="pageObj"></bean:define>
        	<logic:equal name="xPaging" property="hasFirst" value="true">
		  		<td><img src="paging/first1.gif" alt="First" onclick="todo('first');" border="0" onmouseover="this.style.cursor='hand'"></td>
		    </logic:equal>
		    <logic:equal name="xPaging" property="hasFirst" value="false">
		    	<td><img src="paging/disFirst1.gif" border="0"></td>
		 	</logic:equal>
		    <logic:equal name="xPaging" property="hasPrevious" value="true">
		    	<!-- <td><img src="paging/left1.gif" alt="Previous" onclick="todo('previous');" border="0" onmouseover="this.style.cursor='hand'"></td> -->
		    	<td><img src="paging/left1.gif" alt="Previous" onclick="gotoMod('${xPaging.currentPage }');" border="0" onmouseover="this.style.cursor='hand'"></td>
			</logic:equal>
			<logic:equal name="xPaging" property="hasPrevious" value="false">
		    	<td><img src="paging/disLeft1.gif" border="0"></td>
		 	</logic:equal>
		  		<td><html:text property="displayCurrentPage" styleClass="Textbox" value="${xPaging.currentPage + 1}"  size="2" maxlength="3" onkeydown="if(window.event.keyCode == '13') goto('${xPaging.lastPage}',this);"></html:text></td>
		    	<td valign="middle">/</td>
                <td valign="middle"><html:text property="lastPage" styleClass="disTextbox" value="${xPaging.lastPage}" size="2" maxlength="3" readonly="true"/></td>
            <logic:equal name="xPaging" property="hasNext" value="true">
		    	<!-- <td><img src="paging/right1.gif" alt="Next" name="imgNext" onclick="todo('next');" border="0" onmouseover="this.style.cursor='hand'"></td> -->
		    	 <td><img src="paging/right1.gif" alt="Next" name="imgNext" onclick="gotoMod('${xPaging.currentPage + 2}');" border="0" onmouseover="this.style.cursor='hand'"></td> 
			</logic:equal>
		    <logic:equal name="xPaging" property="hasNext" value="false">
		  		<td><img src="paging/disRight1.gif" border="0" ></td>
		   	</logic:equal>
			<logic:equal name="xPaging" property="hasLast" value="true">
		  		<td><img src="paging/last1.gif" alt="Last" onclick="todo('last');" border="0" onmouseover="this.style.cursor='hand'"></td>
		   	</logic:equal>
			<logic:equal name="xPaging" property="hasLast" value="false">
		   		<td><img src="paging/disLast1.gif" border="0" ></td>
			</logic:equal>			
		</tr>
      </table>
     </td>
  </tr>
</table>
