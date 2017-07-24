<%-- 페이지 구성에서 보여지는 레이아웃 선택 부분 (Ajax로 호출됨) --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	String selectedThemeId = (String)request.getAttribute("selectedThemeId");
	PortalPageThemeVO[] themeList = (PortalPageThemeVO[])request.getAttribute("theme");
%>

<%-- 레이아웃 목록 --%>
<div id="themeCollection">
	<span style="margin-right:40px;"><spring:message code="portal.page.label.1" text="테마 선택" /></span>
<%
	for(PortalPageThemeVO theme : themeList) {
		String themeName = theme.getMessageName();
		String themeCssUrl = theme.getThemeCssUrl();
		String themeImageUrl = theme.getThemeImageUrl();
		String regDate = theme.getRegDate2String();
		String isDefault = theme.getIsDefault();
		String themeId = theme.getThemeId();
		
		String isSelected = themeId.equals(selectedThemeId) ? "Y" : "";
%>
	<%-- <button type="button" id="<%=theme.getThemeId()%>" isSelected="<%=isSelected%>" isDefault="<%=isDefault%>" class="admin-layout-theme-btn-action"><%=themeName%></button>--%>
	<span class="smain-btn"><a href="#" id="<%=theme.getThemeId()%>" style="font-weight: normal;min-width: 100px; text-align: center;" isSelected="<%=isSelected%>" isDefault="<%=isDefault%>"><%=themeName%></a></span>
<%		
	}
%>
</div>
