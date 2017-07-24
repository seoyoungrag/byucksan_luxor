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
    $(document).ready(function() {
    	luxor.popup("b.jsp", {width:100,height:100});
    });
</script>
</head>

<body>

</body>
</html>