<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>

<%
	UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
	UserSettingVO userSetting = (UserSettingVO)request.getAttribute("userSetting");
	PortalMenuVO[] menus = (PortalMenuVO[])request.getAttribute("menus");
	
	String selectedId = userSetting==null ? "" : CommonUtil.nullTrim(userSetting.getSettingValue());
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.146" text="초기화면 설정" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	$("#menu_search input").keypress(function(e) {
		if(e.keyCode==13) {
			$("#menu_search span").click();	
		}
	});
	
	$("#menu_search span").click(function() {
		$("#menus div").removeClass('bold red');
		$("#menus div").each(function() {
			if(luxor.isNullOrEmpty($("#menu_search input").val())) {
				return;
			}
			if($(this).attr('name').indexOf($("#menu_search input").val()) > -1) {
				$(this).addClass('bold red');
			}
		});
	});
	
	$("#menus div").click(function() {
		if(confirm("<spring:message code="user.alert.msg.9" text="초기화면으로 설정하시겠습니까?" />")) {
			var param = "pageId="+$(this).attr('id');
			callJson("userController", "setHome", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
		            opener.location.href ="/ep";
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
			<span class="title-pop-text"><spring:message code="portal.label.146" text="초기화면 설정" /></span>
		</div>
	</div>
	
	<div id="tree_wrap" class="tree-wrap" style="height:400px">
		<%-- 트리 검색 --%>
		<div id="menu_search" class="admin-tree-btn-group">
			<input type="text" class="input-txtfield w100" />
			<span class="main-btn"><span class="search"></span><a href="#"><spring:message code="portal.btn.label.8" /></a></span>		
		</div>
		<%-- 트리 검색 --%>
	
		<div id="menus" class="admin-tree-padding">
		<%
			for(int i=0; i < menus.length; i++) {
				String menuId = menus[i].getMenuId();
				String menuName = menus[i].getMessageName();
				String selected = "";
				if(selectedId.equals(menuId)) {
					selected = "hover";
				}
				out.println("<div id="+menuId+" name="+menuName+" class='link padding border margin "+selected+"'>- "+menuName+"</div>");
			}
		%>
		</div>
	</div>
</body>
</html>
