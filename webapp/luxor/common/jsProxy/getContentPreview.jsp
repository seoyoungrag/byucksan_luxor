<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>
<%@ page import="org.springframework.web.util.WebUtils" %>
 
<%
	UserProfileVO userProfile = (UserProfileVO)WebUtils.getSessionAttribute(request, "userProfile");
	String contentId = (String)request.getAttribute("contentId");
	String themeImageUrl = (String)request.getAttribute("themeImageUrl");
	PortalPageThemeVO theme = (PortalPageThemeVO)request.getAttribute("theme");
	
	PortalPageVO pageVO = (PortalPageVO)request.getAttribute("page"); 
	if(pageVO==null) {
		throw new Exception("Error to retreive page!!");
	}
	
	String pageId = pageVO.getPageId();
	String menuId = CommonUtil.nullTrim(UtilRequest.getString(request, "menuId"));
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.btn.label.25" text="컨텐츠 관리" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script>
var themeImageUrl = "<%=themeImageUrl%>";
var currentPageId = '<%=pageId%>';
var selectedNodeId = '<%=pageId%>';
var currentMenuId = '<%=menuId%>';

$(document).ready(function() {
	var contentId = "<%=contentId%>";
	callJson('portalContentController','getContent','contentId='+contentId,function(data) {
		data.zoneId = 'content_preview';
		loadPreview([data]);
	});
	
	function loadPreview(data) {
		$('#content_preview > ul').html('');
		$.getJSON('/ep/luxor/common/jsProxy/checkPortletUrlInfo.jsp?portletId='+data[0].portletId+'&cacheTime='+new Date(), function(json) {
			var menuArr = [''];
			content.setMenuList(menuArr);
			content.setPreview(false);
			content.load(data);
		});
	}
	
	$("#block_layer").attr('style','position:absolute;width:100%;height:100%;visibility:visible;background-color:transparent;z-index:1;' ); 
});
</script>
<script type="text/javascript" src="/ep/luxor/ref/js/content.js"></script>
<link rel="stylesheet" type="text/css" href="<%=theme.getThemeCssUrl()%>" />
</head>
	<body>
		<iframe id="block_layer" frameborder="0" allowTransparency="true" src="/ep/luxor/common/jsProxy/transparent.html"></iframe>
		<div id="content_preview" style="margin-top:10px;overflow:auto;height:100%;"><ul type="unfixed"></ul></div>
	</body>
</html>