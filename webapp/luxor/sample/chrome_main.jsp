<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="ko">
<head>
<title></title>
<%--<jsp:include page="/luxor/common/header.jsp" />--%>
<script type="text/javascript">
</script>
</head>

<body>
<div class="admin-menu-box">
	<div class="admin-menu-bar">
		<div id='admin_menu_left'>
			<ul class="admin-menu admin-dropdown" id="ROOT">
				<li class='acube-logo'><a href="#"><img src="/ep/luxor/ref/image/admin/admin_logo.gif" alt="Logo" width="90" height="30" class="logo" /></a></li>
				<li id="groupPortal" class="admin-menu-padding"><a href="#"><%=(String)session.getAttribute("PORTAL_NAME")%></a></li>
				<li class='admin-bg-img'></li>
				<li id="setPortalAdmin" class="admin-menu-padding"><a href="#"><spring:message code="portal.label.57" text="관리자지정" /></a></li>
				<li class='admin-bg-img'></li>
			</ul>
		</div>
		<div id='admin_menu_right' class="admin-logout-right">
			<span class="admin-logout"><a id="adminManager" href="#">[<spring:message code="admin.label.1" text="관리자메뉴관리" />]</a></span>
			<span class="admin-icon-logout"><a id="logout" href="#"><spring:message code="admin.label.8" text="admin.label.8" /></a></span>
		</div>
	</div>
	<div class="div_bottom" id="div_bottom" >
		<iframe id="FR_BOTTOM" class="admin-body-wrap" name="FR_BOTTOM" src="chrome_iframe.jsp" frameborder="0" scrolling="yes" title="LUXOR_BOTTOM" width="100%" height="500px"></iframe>
	</div>	
</div>
</body>
</html>