<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>ACUBE Portal - <spring:message code="portal.label.98" text="플러그인 설치" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
</script>

<body>
<div class="admin-content-width w400 ml15" name="zone">
	<div class="title-sub">
		<span class="title-sub-text"><spring:message code="portal.label.98" text="플러그인 설치" /></span>
	</div>
	<div class="text-center mt10">
		<spring:message code="portal.label.99" />
		<br /><br />
		<a href="/ep/luxor/ref/plugins/npSeedClientX.msi"><spring:message code="portal.label.100" text="다운로드" /></a>
	</div>
</div>
</body>
</html>