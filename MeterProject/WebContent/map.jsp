<%@ page language="java" import="java.util.*" pageEncoding="TIS-620"%>
<%@ page contentType="text/html; charset=TIS-620"%>
<%@page import="com.pwa.web.helper.WebUtil"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
String lat = (String)request.getParameter("lat");
String longs = (String)request.getParameter("long");

%>
<jsp:include page="headerRoot.jsp" />
<!-- script -->
<style type="text/css">
div#map_container{
    width:100%;
    height:350px;
}
</style>
<script type="text/javascript" 
   src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
 
<script type="text/javascript">
  function loadMap() {
    var latlng = new google.maps.LatLng(<%=lat%>,<%=longs%>);
    var myOptions = {
      zoom: 15,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
 
    var marker = new google.maps.Marker({
      position: latlng, 
      map: map, 
      title:"Map!"
    }); 
 
  }
</script>

<body onload="loadMap()">
<input type="hidden" name="latitude" id="latitude" value="<%=lat%>">
<input type="hidden" name="longtitude" id="longtitude" value="<%=longs%>">
<div id="map_container"></div>
</body>

<jsp:include page="footerRoot.jsp" />
