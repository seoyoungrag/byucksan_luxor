<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/default.css" />
<title><spring:message code="portal.plug.label.12" text="플러그 페이지" /></title>
</head>
<body>
	<input type="hidden" id="RESPONSE_CODE" value="500" />
	<!-- tb-write -->
	<div class="tb-write mt10">
	    <!-- table-error -->
	      <div class="table-error" style="width:700px;border-top:0px;">
	        <p class="error-title"><spring:message code="portal.plug.label.13" text="플러그 페이지입니다." /></p>
	        <div class="error-text">
	          <p><spring:message code="portal.plug.label.14" text="플러그 페이지 샘플입니다." /><br /></p>
	          <p class="mt10"><b><spring:message code="portal.plug.label.15" text="샘플 페이지를 수정하시거나, 새로운 페이지 사용하십시오. 또는 외부 URL을 사용하실 수 있습니다." /></b></p>
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