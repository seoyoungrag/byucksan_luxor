<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>

<%
	UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
	UserSettingVO userSetting = (UserSettingVO)request.getAttribute("userSetting");
	PortalPageThemeVO[] themes = (PortalPageThemeVO[])request.getAttribute("themeList");
	
	String selectedId = userSetting==null ? "" : CommonUtil.nullTrim(userSetting.getSettingValue());
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.148" text="테마 설정" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	$("#themes_search input").keypress(function() {
		$("#themes_search span").click();	
	});
	
	$("#themes_search span").click(function() {
		$("#themes div").removeClass('bold red');
		$("#themes div").each(function() {
			if(luxor.isNullOrEmpty($("#themes_search input").val())) {
				return;
			}
			if($(this).attr('name').indexOf($("#themes_search input").val()) > -1) {
				$(this).addClass('bold red');
			}
		});
	});
	
	$("#themes div").click(function() {
		if(confirm("<spring:message code="portal.alert.msg.48" text="기본 테마로 설정하겠습니까?" />")) {
			var param = "themeId="+$(this).attr('id');
			callJson("userController", "setTheme", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
		            document.location.reload();
		            opener.location.reload();
		        }
			});
		}
	});
});
</script>
</head>

<body>
	<div class="popup-wrap">
		<div class="title-pop">
			<span class="title-pop-text"><spring:message code="portal.label.148" text="테마 설정" /></span>
		</div>
	</div>
	
	<div id="tree_wrap" class="tree-wrap" style="height:400px">
		<%-- 트리 검색 --%>
		<div id="themes_search" class="admin-tree-btn-group">
			<input type="text" class="input-txtfield w100" />
			<span class="main-btn"><span class="search"></span><a href="#"><spring:message code="portal.btn.label.8" /></a></span>		
		</div>
		<%-- 트리 검색 --%>
	
		<div id="themes" class="admin-tree-padding">
		<%
			for(int i=0; i < themes.length; i++) {
				String themeId = themes[i].getThemeId();
				String themeName = themes[i].getMessageName();
				String selected = "";
				if(selectedId.equals(themeId)) {
					selected = "hover";
				}
				out.println("<div id="+themeId+" name="+themeName+" class='link padding border margin "+selected+"'>- "+themeName+"</div>");
			}
		%>
		</div>
	</div>
</body>
</html>
