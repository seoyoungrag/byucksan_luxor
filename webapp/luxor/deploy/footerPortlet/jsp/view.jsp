<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	String currentPath = (String)request.getAttribute("currentPath");
	String portletContext = (String)request.getAttribute("portletContext");
	String themeImageUrl = UtilRequest.getString(request, "themeImageUrl");
%>
<!-- 푸터 -->
<script type="text/javascript">
//IE7 이하의 경우 width100% 상속이 끊기는 현상이 있음
$(document).ready(function() {
	if($.browser.msie && ($.browser.version=='6.0' || $.browser.version=='7.0')) {
		$('#<%=portletContext%>').width($('#<%=portletContext%>').closest('.luxor-footer').width());
	}
});
</script>
<div id="<%=portletContext%>" class="subfooter" style="float:left; text-align:center; width:100%; height:50px; line-height:50px; font-size:11px; background:#ffffff; margin-top:0px;">
	<div style="width:1350px; margin:10px auto 0 auto">
		<span style="float:left"><img src="/ep/luxor/ref/img/footer03.jpg"/></span>
		<span style="float:right"><img src="/ep/luxor/ref/img/footer04.jpg"/></span>
	</div>
</div>
<!-- 푸터 //-->
