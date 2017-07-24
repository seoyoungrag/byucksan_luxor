<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8" %>

<%
String currentPath = (String)request.getAttribute("currentPath");
String portletContext = (String)request.getAttribute("portletContext");
%>
<script type="text/javascript">
//엔터키입력시
$(document).ready(function() {
	$('#ifr_map').height($('#ifr_map').closest('.content-body').height());
});
</script>
<iframe id="ifr_map" name="ifr_map" src="<%=currentPath%>/jsp/map.jsp" frameborder="0" style="z-index:1;background-color:#FFFFFF;width:100%;"></iframe>

