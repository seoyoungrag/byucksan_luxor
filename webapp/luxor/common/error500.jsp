<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>

<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	response.setStatus(200);
	String errorMessage = "";
	if(exception != null) {
		errorMessage = exception.getMessage();
		exception.printStackTrace();
	}
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/default.css" />
<title><spring:message code="portal.authorization.msg.3" text="페이지 처리중 오류가 발생하였습니다." /></title>
</head>
<body>
	<input type="hidden" id="RESPONSE_CODE" value="500" />
	<!-- tb-write -->
	<div class="tb-write mt10">
	    <!-- table-error -->
	      <div class="table-error">
	        <p class="error-title"><spring:message code="portal.authorization.msg.3" text="페이지 처리중 오류가 발생하였습니다." /></p>
	        <div class="error-text">
	          <p><spring:message code="portal.authorization.msg.4" text="접근하려는 페이지에서  다음과 같은 오류가 발생하였습니다." /><br /></p>
	          <p class="mt10"><b><spring:message code="portal.authorization.msg.5" text="에러코드" /> : <% out.println(CommonUtil.escapeSpecialChar(errorMessage)); %></b></p>
	        </div>
	        <!-- button -->
	        <div class="btn-center">
	        <span class="main-btn"><a href="/ep"><spring:message code="portal.authorization.msg.6" text="홈으로" /></a></span>
	        </div>
	        <!-- //button -->
	      </div>
	    <!-- //table-error -->
	</div>
	<!-- //tb-write -->
</body>
</html>