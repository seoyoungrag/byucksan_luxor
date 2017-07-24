<%/* 개인 메뉴 설정 팝업창 */%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>

<%
	UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
	PortalMenuVO[] menus = (PortalMenuVO[])request.getAttribute("menus");
	
	String pageId = "MYMENU_"+userProfile.getUserUid();
	String contentId = UtilRequest.getString(request, "contentId");
	String zoneId = "mzMiddle";
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.33" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/content.js"></script>
<script type="text/javascript">
	// 현재 선택된 노드
	var actionFlag = false;
	
	function _getMenuName(menuId) {
		return $('#'+menuId).attr('name');
	}
	
	function insertRow(nodeId, displayName) {
		var menuName = "";
		if(displayName == '' || displayName == null) {
			menuName = _getMenuName(nodeId);
		} else {
			menuName = displayName;
		}
		$('#selected_page').append("<div id='sp_"+nodeId+"' class='sp_row link'><table><tr><td class='sp_row_text padding'>- "+menuName+"</td>"
				+"<td class='sp_row_conf' >"
				+"<a href='#none' mode='del'><spring:message code="portal.btn.label.30" text="삭제" /></a>"
				+"</td></tr></table></div>");
	}

	// 이전에 선택된 메뉴를 불러옴
	function loadSelectedMenu() {
		callJson("portalMenuController", "getMyMenus", "", function(data) {
			for(var i=0; i < data.length; i++) {
				var _nodeId = data[i].menuId;
				var _displayName = data[i].displayName;
				insertRow(_nodeId, _displayName);
			}
		});
	}
	
	function resizeTreeHeight() {
		$('#resize_bar').height($(document).height()-20);
		$('#tree_wrap').height($(document).height()-20);
	}

	$(window).scroll(function() {
		resizeTreeHeight();
	});
	
	$(window).resize(function() {
		setTimeout("resizeTreeHeight()",400);
	});

	var prevTreeWidth = 0;
	$(document).ready(function() {
		$("#tree_search input").keypress(function(e) {
			if(e.keyCode==13) {
				$("#tree_search span").click();
			}
		});
		
		$("#tree_search span").click(function() {
			$("#menus div").removeClass('bold red');
			$("#menus div").each(function() {
				if(luxor.isNullOrEmpty($("#tree_search input").val())) {
					return;
				}
				if($(this).attr('name').indexOf($("#tree_search input").val()) > -1) {
					$(this).addClass('bold red');
				}
			});
		});
		
		$('a').live('focus', function() {
			$(this).blur();
		});

		// 왼쪽 메뉴 클릭시 우측으로 추가
		$('#menus div').click(function() {
			var selectedMenuId = $(this).attr('id');
			var selectedMenuName = $(this).attr('name');
			// 중복체크
			if(luxor.isNullOrEmpty($('#sp_'+selectedMenuId).text())) {
				// DB 저장
				var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&parentId=ROOT&menuId="+selectedMenuId;
				callJson("portalMenuController", "insertMenu", param, function(data) {
					insertRow(selectedMenuId, selectedMenuName);
					actionFlag = true;
				});
			}
		});
		
		// 좌우 크기조정 드래그 바
		$('#resize_bar').draggable({
			axis: 'x',
			drag: function(e, ui) {
				var pos = $(this).offset();
				$('#tree_wrap').width(pos.left);
				$('#page_wrap').width($('#page_wrap').width()-prevTreeWidth+pos.left);
				prevTreeWidth=pos.left;
			},
			stop: function(e, ui) {
				$('#page_wrap').width($('#tree_wrap').width()+$('#selected_page_wrap').width()+50);
			}
		});
		
		// load initial data
		loadSelectedMenu();
		
		// make selected page list sortable 
		$("#selected_page").sortable({
			items: 'div',
			// 드래그하여 순서조정을 한 경우 Ajax 호출해서 DB저장
			update: function(event, ui) {
				var arr = $("#selected_page").sortable('toArray');
				for(var j=0; j < arr.length; j++) {
					if(luxor.isNullOrEmpty(arr[j])) {
						continue;
					}
					var _menuId = arr[j].substring(3);
					var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+_menuId+"&seq="+j;
					luxor.log(param);
					callJson("portalMenuController", "updateMenuSeq", param);
				}

				actionFlag = true;
			}
		});

		// 삭제버튼 클릭시
		$('div[id] a[mode=del]').live('click', function() {
			if(confirm("<spring:message code="portal.alert.msg.12" />")) {
				var menuId = $(this).closest('div[id]').attr('id').substring(3);
				var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+menuId;
				callJson("portalMenuController", "deleteMenu", param, function(data) {
					if(eval(data)) {
						$('#sp_'+menuId).remove();
					}
				});

				actionFlag = true;
			}
		});

		setTimeout("resizeTreeHeight()",400);
	});

	$(window).unload(function() {
		if(actionFlag) {
			opener.location.reload();
		}
	});
</script>

<body>
	<div id="page_wrap" class="menu_selector_wrap">
		<div id="tree_wrap" class="tree-wrap" style="height:480px">
			<%-- 트리 검색 --%>
			<div id="tree_search" class="admin-tree-btn-group">
				<input type="text" class="input-txtfield w100" title="<spring:message code="portal.label.21" text="검색어" />" />
				<span class="main-btn"><span class="search"></span><a href="#"><spring:message code="portal.btn.label.8" text="검색" /></a></span>		
			</div>
			<%-- 트리 검색 --%>
		
			<div id="menus" class="admin-tree-padding">
			<%
				for(int i=0; i < menus.length; i++) {
					String menuId = menus[i].getMenuId();
					String menuName = menus[i].getMessageName();
					
					String displayName = menus[i].getDisplayName();
					if(!displayName.equals("")) {
						menuName = displayName;
					}
					out.println("<div id="+menuId+" name='"+menuName+"' class='link padding border margin mr15'>- "+menuName+"</div>");
				}
			%>
			</div>
		</div>
		
		<%-- 왼쪽(트리), 오른쪽(페이지) 크기 리사이즈해주는 바 --%>
		<div id="resize_bar" class="resize-bar"></div>
		<div id="selected_page_wrap" class="selected-page-wrap" style="width:350px;">
			<h6 class="admin-sub-title"><spring:message code="portal.label.34" /></h6>
			<div id="selected_page"></div>
		</div>
	</div>
</body>
</head>
