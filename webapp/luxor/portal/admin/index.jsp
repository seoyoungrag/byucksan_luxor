<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="com.sds.acube.luxor.security.*"%>
<%@ page import="com.sds.acube.luxor.portal.vo.*"%>
<%@ page import="com.sds.acube.luxor.common.vo.*"%>
<%@ page import="com.sds.acube.luxor.common.util.*"%>
<%@ page import="com.sds.acube.luxor.common.ConstantList"%>

<%
	String selectedMenuId = CommonUtil.nullTrim(UtilRequest.getString(
			request, "menuId"));
	AdminUserVO adminUser = (AdminUserVO) session
			.getAttribute("adminProfile");
	if (adminUser == null) {
		CommonUtil.redirect(response, "/ep/luxor/admin/login.jsp");
		return;
	}

	String D1 = UtilSSO.encodeSession(request);
	SsoToken ssoToken = UtilSSO.getSsoToken(request, D1); //@add 2014.02.25
%>


<%@page import="com.sds.acube.luxor.framework.config.LuxorConfig"%><html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title><spring:message code="portal.label.41" text="관리자 포탈" /></title>
<link rel="stylesheet" type="text/css"
	href="/ep/luxor/ref/css/jquery-ui-default.css" />
<link rel="stylesheet" type="text/css"
	href="/ep/luxor/ref/css/jquery-ui-theme.css" />
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/hover.js"
	charset="utf-8"></script>
<script type="text/javascript">
	var firstMenuId = null;
	var lastMenuId = null;
	
	function resizeIFrameHeight() {
		$('#FR_BOTTOM').height($(window).height()-32);
	}
	
	/**
	 * 상단의 메뉴가 많은 경우 스크롤을 만들어 줌
	 */
	function setScrollMenu() {
		$('#scrollMenu').remove();
		
		var menubarWidth = $('#admin_menu_left').outerWidth(true) - $('#admin_menu_right').outerWidth(true);
		var menusWidth = 0;
		$('#ROOT > li').each(function() {
			$(this).show();
			menusWidth += $(this).outerWidth(true);
		});
		
		if(menusWidth > menubarWidth) {
			var showScroll = false;
			var tempWidth = 0;
			$('#ROOT > li').each(function() {
				tempWidth += $(this).outerWidth(true);
				if(tempWidth > menubarWidth) {
					$(this).hide();
					showScroll = true;
				}
			});
			
			if(showScroll) {
				$('#ROOT > li').not(':hidden').last().hide();
				$('#ROOT > li').not(':hidden').last().hide();
				$('#ROOT').append("<li id='scrollMenu' class='scroll admin-menu-padding'><a href='#scroll' id='toLeft'>◀</a><a href='#scroll' id='toRight'>▶</a></li>");
			}
		} 
	}

	// 2초에 한번씩 세션체크 함
	var canigoin = true;
	function checkSession() {
		if(canigoin==false) {
			return;
		}

		canigoin = false;
		$.get("/ep/luxor/common/jsProxy/checkSession.jsp?cacheTime="+new Date(), function(data) {
			if(luxor.trim(data)=='off') {
				alert("<spring:message code="portal.alert.msg.57" text="세션이 종료되어 자동 로그아웃됩니다." />");
				top.location.reload();
			}
			canigoin = true;
		});
		setTimeout("checkSession()", 2000);
	}
	
	$(window).resize(function() {
		setScrollMenu();
		resizeIFrameHeight();
	});

	$(document).ready(function() {
		checkSession();
		
		callJson("adminController", "getAdminMenus", "", function(data) {
			for(var i=0; i < data.length; i++) {
				var parentId = data[i].parentId;
				var menuId = data[i].menuId;
				var menuName = data[i].messageName;
				var menuUrl = data[i].menuUrl;
				var isHome = data[i].isHome;
				if(parentId=="ROOT") {
					$('#ROOT').append("<li id='"+menuId+"' class='admin-menu-padding'><a href='#'>"+menuName+"</a></li><li class='admin-bg-img'></li>");
				}
				else if(parentId=="CATEGORY") {
					$('#ROOT').append("<li id='"+menuId+"' class='admin-menu-padding'><a href='#category'>"+menuName+"</a></li><li class='admin-bg-img'></li>");
				} 
				else {
					if($('#'+parentId+' > ul').length==0) {
						$('#'+parentId).append("<br /><ul style='position:absolute;'></ul>");
					}
					$('#'+parentId+' > ul').append("<li style='float:none;display:inline;' id='"+menuId+"'><a href='#'>"+menuName+"</a></li>");
				}

				if(luxor.isNullOrEmpty('<%=selectedMenuId%>')) {
					if(isHome=='Y') {
						if($.browser.msie && ($.browser.version=='6.0' || $.browser.version=='7.0')) {
							if(menuUrl.indexOf("http://") > -1 ) {
								$('#indexForm').attr('target', menuId);
								$('#indexForm').attr('action', menuUrl);
								$('#indexForm').submit();
							} else {
								$('#FR_BOTTOM').attr('src',menuUrl);
							}
						} else {
							$('#indexForm').attr('target', "FR_BOTTOM");
							$('#indexForm').attr('action', menuUrl);
							$('#indexForm').submit();
						}
					}
				} else {
					if(menuId=='<%=selectedMenuId%>') {
						if($.browser.msie && ($.browser.version=='6.0' || $.browser.version=='7.0')) {
							if(menuUrl.indexOf("http://") > -1 ) {
								$('#indexForm').attr('target', menuId);
								$('#indexForm').attr('action', menuUrl);
								$('#indexForm').submit();
							} else {
								$('#FR_BOTTOM').attr('src',menuUrl);
							}
						} else {
							$('#indexForm').attr('target', "FR_BOTTOM");
							$('#indexForm').attr('action', menuUrl);
							$('#indexForm').submit();
						}
					}
					if('adminMenu'=='<%=selectedMenuId%>') {
						$('#FR_BOTTOM').attr('src','/ep/admin/manager.do');
					}
					if('approvalView'=='<%=selectedMenuId%>') {
						$('#FR_BOTTOM').attr('src','/ep/memberRequest/getApprovalView.do');
					}
					if('memberRequestKeyView'=='<%=selectedMenuId%>') {
						$('#FR_BOTTOM').attr('src','/ep/memberRequest/getMemberRequestKeyView.do');
					}
<%if (adminUser.getAdminType() == ConstantList.GROUP_ADMIN) {%>
					if('groupPortal'=='<%=selectedMenuId%>') {
						$('#FR_BOTTOM').attr('src','/ep/admin/groupportal/getPortalList.do');
					}
					if('setPortalAdmin'=='<%=selectedMenuId%>') {
						$('#FR_BOTTOM').attr('src','/ep/admin/getAdminUserList.do');
					}
<%}%>
				}
			}

			// 마지막 bar는 없앰
			$('#ROOT > li').last().remove();
			
			// 첫, 마지막 메뉴 아이디 셋팅
			firstMenuId = $('#ROOT > li').not('.acube-logo').first().attr('id');
			lastMenuId = $('#ROOT > li').last().attr('id');
			
		    try {
		    	// IE6 처리
		    	if($.browser.msie && $.browser.version=='6.0') {
			    	$("ul.admin-dropdown li").hoverIntent(function(e) {
			    		$('ul:first',this).css('visibility','visible');
			    	}, function() {
			            $('ul:first',this).css('visibility', 'hidden');
			    	});
		    	}
		    } catch(e) {}
		    
		    setScrollMenu();
		});

		// 메뉴 클릭 이벤트
		$('#ROOT li a').live('click', function(e) {
			var menuId = $(this).parent().attr('id');
			
			// <a href='#category'> 인 경우 $(this).attr('href') 값이 다른 브라우져에서는 #category를 리턴하지만
			// 지랄같은 IE7에서는 http://현재 브라우저 URL#category를 리턴해서 indexOf로 비교함
			// IE는 세상에서 사라져버려라!!
			if(luxor.isNullOrEmpty(menuId)) {
				document.location.href = "/ep/admin/index.do";
			} else if ($(this).attr('href').indexOf("#category") > -1) {
				// 카테고리일 경우는 link가 없음
			} else if ($(this).attr('href').indexOf("#scroll") > -1) {
				// ◀▶ 버튼 클릭시 좌우 스크롤 처리
				if($(this).attr('id')=='toRight') {
					var lastHiddenObject = $('#ROOT > li').not('.scroll, :hidden').last();
					if(lastHiddenObject.attr('id')!=lastMenuId) {
						$('#ROOT > li').not('.acube-logo, :hidden').first().hide();
						lastHiddenObject.next().show();
					}
				} else {
					var firstHiddenObject = $('#ROOT > li').not('.acube-logo, :hidden').first();
					if(firstHiddenObject.attr('id')!=firstMenuId) {
						firstHiddenObject.prev().show();
						$('#ROOT > li').not('.scroll, :hidden').last().hide();
					}
				}
			} else {
				document.location.href = "/ep/admin/index.do?menuId="+menuId;
			}
			e.stopImmediatePropagation();
		});
		
		// [관리자 MENU 관리] 클릭시
		$('#adminManager').click(function(event) {
			document.location.href = "/ep/admin/index.do?menuId=adminMenu";
			event.stopImmediatePropagation();
			return false;
		});

		// 계정신청목록 클릭시
		$('#setMemberRequest').click(function(event) {
			document.location.href = "/ep/admin/index.do?menuId=approvalView";
			event.stopImmediatePropagation();
			return false;
		});

		// 계정신청 키 관리 클릭시
		$('#setMemberRequestKey').click(function(event) {
			document.location.href = "/ep/admin/index.do?menuId=memberRequestKeyView";
			event.stopImmediatePropagation();
			return false;
		});

		$('#logout').click(function(event) {
			callJson("loginController", "logout", "", function(json) {
				document.location.href = "/ep/luxor/admin/login.jsp";
			});
			event.stopImmediatePropagation();
			return false;
		});
		
		resizeIFrameHeight();
	});

</script>
<style type="text/css">
/* For IE6/7 overflow bug */
html {
	overflow: hidden;
}
</style>
</head>

<body>
<form id="indexForm" method="post">
<input type="hidden" name="D0" value="SDS"></input>
<input type="hidden" name="D1" value="<%=D1%>"></input> 
<input type="hidden" name="D2" value=""></input> 
<input type="hidden" name="D3" value="DES"></input>
<input type="hidden" name="STM" value="<%= ssoToken.getSTM() %>"/><!-- @add 2014.02.25 -->
<input type="hidden" name="SHM" value="<%= ssoToken.getSHM() %>"/><!-- @add 2014.02.25 -->
</form>
<div class="admin-menu-box" style="position: relative; z-index: 0;">
<div class="admin-menu-bar">
<div id='admin_menu_left'>
<ul class="admin-menu admin-dropdown" id="ROOT">
	<li class='acube-logo'><a href="#"><img
		src="/ep/luxor/ref/image/admin/admin_logo.gif" alt="Logo" width="90"
		height="30" class="logo" /></a></li>
	<%
		if (adminUser.getAdminType() == ConstantList.GROUP_ADMIN) {
	%>
	<li id="groupPortal" class="admin-menu-padding"><a href="#"><%=(String) session.getAttribute("PORTAL_NAME")%></a></li>
	<li class='admin-bg-img'></li>
	<li id="setPortalAdmin" class="admin-menu-padding"><a href="#">
	<spring:message code="portal.label.57" text="관리자지정" /></a></li>
	<li class='admin-bg-img'></li>
	<%
		}
	%>
	<%
		if (LuxorConfig.getEnvString(request, "USE_MEMBER_REQUEST").equals("Y")) {
	%>
	<li id="" class="admin-menu-padding">
	<a href="javascript:;"><spring:message code="portal.memberRequest.msg.37" text="계정신청관리" /></a>
	<br>
		<ul style="position:absolute;">
			<li style="float:none;display:inline;" id="setMemberRequest">
				<a href="#"><spring:message code="portal.memberRequest.msg.38" text="계정신청목록" /></a>
			</li>
			<li style="float:none;display:inline;" id="setMemberRequestKey">
				<a href="#"><spring:message code="portal.memberRequest.msg.39" text="신청키관리" /></a>
			</li>
		</ul>
	</li>
	<li class='admin-bg-img'></li>
	<%
		}
	%>
</ul>
</div>
<div id='admin_menu_right' class="admin-logout-right"><span
	class="admin-logout"><a id="adminManager" href="#">[<spring:message
	code="admin.label.1" text="관리자메뉴관리" />]</a></span> <span
	class="admin-icon-logout"><a id="logout" href="#"><%=adminUser.getUserName()%><spring:message
	code="admin.label.8" text="admin.label.8" /></a></span></div>
</div>
<div class="div_bottom" id="div_bottom"><iframe id="FR_BOTTOM"
	class="admin-body-wrap" name="FR_BOTTOM" frameborder="0"
	scrolling="yes" title="LUXOR_BOTTOM" width="100%" height="0"></iframe>
</div>
</div>
</body>
</html>