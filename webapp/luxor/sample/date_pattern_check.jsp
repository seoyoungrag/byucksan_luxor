<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
    // Do something here
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	function check(str) {
		//var str = "2000-11-12 44:33:55";
		var rex = /\d{4}\-\d{2}\-\d{2} \d{2}\:\d{2}\:\d{2}/;
		return rex.test(str);
	}
	
	alert(foo());
</script>
</head>

<body>
</body>
</html>