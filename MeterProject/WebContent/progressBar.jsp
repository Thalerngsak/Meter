<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@page import="com.pwa.common.*"%>

<script src='js/progress.js'> </script>
<script src='dwr/interface/ProgressMonitor.js'> </script>
<script src='dwr/engine.js'> </script>
<script src='dwr/util.js'> </script>
        
<style type="text/css">
#progressBar {
	padding-top: 5px;
}

#progressBarBox {
	text-align: left;
	width: 350px;
	height: 20px;
	border: 1px inset;
	background: #eee;
}

#progressBarBoxContent {
	width: 0;
	height: 20px;
	border-right: 1px solid #444;
	background: #9ACB34;
}
</style>
<script language="javascript">
	var enableProgressbar = <%=Env.get("meter.enableprogressbar")%>;
</script>
<table width="100%" border="0" cellpadding="0" align="center">
	<tr align="center">
		<td>
			<span class='status-text' id='updateStatusMsg'></span>
			<div id="progressBar" style="display: none;">
				<div id="theMeter">
					<div id="progressBarText"></div>
					<div id="progressBarBox">
						<div id="progressBarBoxContent"></div>
					</div>
				</div>
			</div>

		</td>
	</tr>
</table>