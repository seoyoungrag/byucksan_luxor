<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>
<%@ page import="org.springframework.web.util.WebUtils" %>

<%
	String contextPath = request.getContextPath();

	UserProfileVO userProfile = (UserProfileVO)WebUtils.getSessionAttribute(request, "userProfile");
	PortalPageVO pageVO = (PortalPageVO)request.getAttribute("page"); 
	PortalPageVO bodyPage = (PortalPageVO)request.getAttribute("bodyPage") == null ? new PortalPageVO() : (PortalPageVO)request.getAttribute("bodyPage");
	PortalPageVO contentPage = (PortalPageVO)request.getAttribute("contentPage") == null ? new PortalPageVO() : (PortalPageVO)request.getAttribute("contentPage");
	PortalPageLayoutVO layout = (PortalPageLayoutVO)request.getAttribute("layout");
	PortalPageThemeVO theme = (PortalPageThemeVO)request.getAttribute("theme");
	String helpButtonPopup = LuxorConfig.getEnvString(request, "HELP_BTN_POPUP") == null ? "N" : LuxorConfig.getEnvString(request, "HELP_BTN_POPUP");
	String helpPopupOption = LuxorConfig.getEnvString(request, "HELP_POPUP_OPTION") == null ? "" : LuxorConfig.getEnvString(request, "HELP_POPUP_OPTION");

	if(pageVO==null || layout==null) {
		throw new Exception("Error to retreive page!!");
	}
	
	String pageId = pageVO.getPageId();
	String pageName = pageVO.getMessageName();
	String menuId = CommonUtil.nullTrim(UtilRequest.getString(request, "menuId"));
	
	// 권한없는 페이지인 경우 리다이렉트
	if("NO_PERMISSION".equals(pageId)) {
		String redirectionPage = LuxorConfig.getEnvString(request, "NO_PERMISSION_PAGE");
		request.getRequestDispatcher(redirectionPage).forward(request, response);
		return;
	}

	if("N".equals(pageVO.getIsActive())) {
		out.println("<h3>현재 사용이 중지된 페이지입니다.</h3>");
		return;
	}
	
	String portalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID")); // 세션의 포탈 아이디
	String pagePortalId = CommonUtil.nullTrim(pageVO.getPortalId()); // 페이지가 속한 포탈 아이디
	
	// 로그인을 A포탈로 한 후 B포탈의 페이지로 이동할 경우 B포탈 아이디로 셋팅해줌
	if(!portalId.equals(pagePortalId)) {
		session.setAttribute("PORTAL_ID", pagePortalId);
	}
					
	String themeImageUrl = theme.getThemeImageUrl() ;
	boolean useHttps = "Y".equals(LuxorConfig.getString("BASIC.USE_HTTPS"));
    boolean useAES = "Y".equals(LuxorConfig.getString("BASIC.USE_AES"));
	
	boolean isPersonal = "Y".equals(pageVO.getIsPersonal()) && userProfile!=null;
	String currentPortalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
	
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.8" /> - <%=pageVO.getMessageName()%></title>
<jsp:include page="/luxor/common/header.jsp" />
<%  if(isPersonal) { %>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/tree.js"></script>
<%	} %>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/content.js"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/contentManager.js"></script>
<%	if(!useHttps) { %>
<%		if(useAES){%>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/util.js"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/sec/aes.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/sec/pbkdf2.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/sec/AesUtil.js" charset="utf-8"></script>
<%		}else{ %>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/seed.js"></script>
<%		} %>
<%	} %>
<script type="text/javascript">
	var currentPortalId = '<%=currentPortalId%>';
	var selectedPortalId = '<%=currentPortalId%>';
	var currentPageId = '<%=pageId%>';
	var currentPageName = '<%=pageName%>';
	var selectedNodeId = '<%=pageId%>';
	var currentMenuId = '<%=menuId%>';
	var layoutId = '<%=layout.getLayoutId()%>';
	var themeImageUrl = '<%=theme.getThemeImageUrl()%>';
	var isPersonal = <%=isPersonal%>;
	var helpButtonPopup = '<%=helpButtonPopup%>';
	var helpPopupOption = '<%=helpPopupOption%>';
	var bodyPage = '<%=CommonUtil.nullTrim(bodyPage.getPageId())%>';
	var contentPage = '<%=CommonUtil.nullTrim(contentPage.getPageId())%>';
	//호출될 페이지가 홈으로 설정되어있는지 여부 체크
	var homePageId = "";
	/**
	 * 각 Zone에 속한 컨텐츠를 가져온다
	 */
	$(document).ready(function() {
		content.refer = 'UM';
		//컨텐츠 호출이 완료될때까지는 luxor-main가 안보이게 한다.
		$('.luxor-main').attr('style','visibility:hidden;');
		callJson("portalPageZoneController", "getContentsOnPage", "pageId=<%=pageId%>&refer=UM", function(data) {
			if(bodyPage != "") {
				for(var i=0; i < data.length; i++) {
					if(data[i].zoneId != 'header' && data[i].zoneId != 'footer') {
						data.splice(i,1);
					} 
				}
			} else if(contentPage != "") {
				for(var i=0; i < data.length; i++) {
					if(data[i].zoneId == 'content') {
						data.splice(i,1);
					}
				}
			}
			content.pageId = '<%=pageId%>';
			content.setMenuList(['max','min','help','edit','reload']);
			content.setDraggable(<%=isPersonal%>);
			content.load(data, function() {
				if(bodyPage != "") {
					if(contentPage != "") {
						targetFrameLoad('/ep/page/index.do?pageId='+bodyPage+'&contentPage='+contentPage, bodyPage, 'body', 'header');
					} else {
						targetFrameLoad('/ep/page/index.do?pageId='+bodyPage, bodyPage, 'body', 'header');
						setTimeout(function() {
							$('.luxor-main').attr('style','visibility:visible;');
							parent.$('.luxor-main').attr('style','visibility:visible;');
						},1000);
					}
				} else if(contentPage != "") {
					targetFrameLoad('/ep/page/index.do?pageId='+contentPage, contentPage, 'content', 'side');
					setTimeout(function() {
						$('.luxor-main').attr('style','visibility:visible;');
						parent.$('.luxor-main').attr('style','visibility:visible;');
						parent.parent.$('.luxor-main').attr('style','visibility:visible;');
					},1000);
				} else {
					$('.luxor-main').attr('style','visibility:visible;');
				}
			});
		});
		
		// IE6 처리
		if($.browser.msie==true && $.browser.version=="6.0") {
			$('#body').children('div[type=zone]').each(function() {
				$(this).width($(this).width()-8);
			});
		}

		// 홈으로 설정시 트리에 홈 이미지 삽입
		callJson("portalPageController", "getHome", "", function(data) {
			if(data!=null) {
				homePageId = data.pageId;
			}
		});
	});
	
	// heemun.20140225. 스크롤 리사이징.
	function setIfrHeight(h){
	 
	h += 10;
	 
	luxor.log("iframe height="+h);
	 
	// iframe content_
	var content_ifr = parent.$("iframe");
	content_ifr.height(h);
	content_ifr.parents().find("#content").height(h);
	content_ifr.parents().find("#body").height(h);
	content_ifr.parents().find("#left").height(h);
	content_ifr.parents().find("#wrap").height(h);
	 
	// iframe body_
	var body_ifr = parent.parent.$("iframe")
	body_ifr.height(h);
	body_ifr.parents().find("#content").height(h);
	body_ifr.parents().find("#body").height(h);
	body_ifr.parents().find("#left").height(h); 
	body_ifr.parents().find("#wrap").height(h);
	 
	/*
	$("#wrap").height(h);
	$("#body").height(h);
	$("#content").height(h);
	$("#body").find('iframe').height(h);
	$("#content").find('iframe').height(h);*/
	}

	
</script>
<style type="text/css"><%=layout.getLayoutCss()%></style>
<link rel="stylesheet" type="text/css" href="<%=theme.getThemeCssUrl()%>" />
</head>

<body class="luxor-body-tag">
	<div id="wrap" class="luxor-main">
		<div id="header" type="zone" class="luxor-top"><ul class="unfixed" type="unfixed"></ul></div>
		<div id="body" class="luxor-body">
			<div class="luxor-inner-body">
				<div id="left" type="zone" class="luxor-left">
					<ul class="fixed" type="fixed"></ul>
					<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
				</div>
				<div id="content-left" type="zone" class="luxor-content-left">
					<ul class="fixed" type="fixed"></ul>
					<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
				</div>					
				<div id="content" type="zone" class="luxor-content">
					<ul class="fixed" type="fixed"></ul>
					<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
				</div>
				<div id="content-right" type="zone" class="luxor-content-right">
					<ul class="fixed" type="fixed"></ul>
					<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
				</div>				
				<div id="right" type="zone" class="luxor-right">
					<ul class="fixed" type="fixed"></ul>
					<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
				</div>
			</div>
		</div>
		<div id="footer" type="zone" class="luxor-footer"><ul class="unfixed" type="unfixed"></ul></div>
	</div>
	<div id="alphabg"></div>
	<div id="content_manager" title="<spring:message code="portal.label.32" text="컨텐츠 선택" />"></div>
</body>

</html>