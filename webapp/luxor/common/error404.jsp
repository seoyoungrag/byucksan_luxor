<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>

<%@ page import="com.sds.acube.luxor.common.*" %>
<%@ page import="org.apache.commons.logging.*" %>

<%
	response.setStatus(200);
	String errorFrom = (String)request.getAttribute("javax.servlet.error.request_uri");
	
	Log logger = LogFactory.getLog(ConstantList.class);
	logger.debug("Error code 404 not found : " + errorFrom);
%>
	
<!DOCTYPE HTML>
<html lang="ko">
<head>
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/default.css" />
<title><spring:message code="portal.authorization.msg.7" text="요청하신 페이지를 찾을 수 없습니다." /></title>
</head>
<body>
	<input type="hidden" id="RESPONSE_CODE" value="404" />
	<!-- tb-write -->
	<div class="tb-write mt10">
	    <!-- table-error -->
	      <div class="table-error">
	        <p class="error-title"><spring:message code="portal.authorization.msg.7" text="요청하신 페이지를 찾을 수 없습니다." /></p>
	        <div class="error-text">
	          <p><spring:message code="portal.authorization.msg.8" text="방문하시려는 페이지의 주소가 잘못 입력되었거나 <br />페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다." /></p>
	          <p class="mt10"><b><spring:message code="portal.authorization.msg.9" text="호출 URI" /> : <% out.println(errorFrom); %></b></p>
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