<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	String popupId = UtilRequest.getString(request, "popupId");
	String popupUrl = UtilRequest.getString(request, "popupUrl");
	String popupTitle = UtilRequest.getString(request, "popupTitle");
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><%=popupTitle%></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	$('#popupIframe').height($(window).height()-25);
	$('#onceperday').click(function() {
		luxor.setCookie('<%=popupId%>','on',1);
		self.close();
	})
});
</script>
</head>

<body style='background:#E0E0E0;margin:0'>
<div>
	<div>
		<iframe id="popupIframe" src="<%=popupUrl%>" style="background:#FFFFFF;width:100%"></iframe>
	</div>
	<div style='text-align:right;padding-top:5px;padding-right:5px;'>
		<input type="checkbox" id="onceperday" /> <spring:message code="portal.label.96" text="오늘 하루 이 창을 열지않음" />
	</div>
</div>
</body>
</html>