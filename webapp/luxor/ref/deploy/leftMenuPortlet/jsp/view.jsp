<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%/* 
	사이드(세로) 메뉴
	로직은 javascript, 디자인은 css 파일을 참고할 것
*/%>

<%
	String currentPath = (String)request.getAttribute("currentPath");
	String portletContext = (String)request.getAttribute("portletContext");
	String themeImageUrl = UtilRequest.getString(request, "themeImageUrl");
%>

<script type="text/javascript">
	var portletContext = "<%=portletContext%>";
	var portal_btn_label_57 = "<spring:message code="portal.btn.label.57" text="펼침" />";
	var portal_btn_label_58 = "<spring:message code="portal.btn.label.58" text="닫힘" />";
</script>

<div id="leftMenuPortlet">
	<div class="left-top-right">
		<div class="left-top"></div>
	</div>
	<div id="mzVertical" class="left-menu"></div>
</div>