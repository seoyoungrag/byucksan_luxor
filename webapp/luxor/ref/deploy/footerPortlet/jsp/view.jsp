<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	String currentPath = (String)request.getAttribute("currentPath");
	String portletContext = (String)request.getAttribute("portletContext");
	String themeImageUrl = UtilRequest.getString(request, "themeImageUrl");
%>
<script type="text/javascript">
//IE7 이하의 경우 width100% 상속이 끊기는 현상이 있음
$(document).ready(function() {
	if($.browser.msie && ($.browser.version=='6.0' || $.browser.version=='7.0')) {
		$('#<%=portletContext%>').width($('#<%=portletContext%>').closest('.luxor-footer').width());
	}
});
</script>
<div id="<%=portletContext%>" class="subfooter">
    <!-- 푸터 -->
     <div id="footer" type="zone" class="luxor-footer">
       <div style="width:1350px; margin:10px auto 0 auto">
       <span style="float:left"><img src="/ep/luxor/ref/img/footer01.jpg" width="391" height="32" /></span>
       <span style="float:right"><img src="/ep/luxor/ref/img/footer02.jpg" width="209" height="29" /></span></div>
     </Rdiv>
    <!-- 푸터 //-->
</div>