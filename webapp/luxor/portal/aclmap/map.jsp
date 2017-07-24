<%/* 관리자 --> 권한맵 */%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.aclservice.*" %>
<%@ page import="org.springframework.context.support.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="org.springframework.context.MessageSource"%>

<%
	TreeVO[] pageList = (TreeVO[])request.getAttribute("pageList");
	PortalContentVO[] contentList = (PortalContentVO[])request.getAttribute("contentList");
	Map aclMap = (Map)request.getAttribute("aclMap");
	
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	MessageSource messageSource = (MessageSource)ctx.getBean("messageSource");
	
	String message1 = messageSource.getMessage("portal.label.73", null, (Locale)session.getAttribute("LANG_TYPE"));
%>

<!DOCTYPE HTML>

<html lang="ko">
<head>
<title><spring:message code="portal.label.70" text="권한맵"/></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/content.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/contentManager.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.disable.text.select.min.js"></script>
<script type="text/javascript">
var message2 = "<spring:message code="portal.alert.msg.55" text="[{1}]의 권한을 [{2}]로 복사하시겠습니까?\n기존에 [{3}]에 설정된 권한은 삭제됩니다."/>";

function reload() {
	document.location.reload();
}

$(document).ready(function() {
	// Disable Text Select
	//$('.admin-wrap').disableTextSelect();
	
	// drag 처리
	var draggingObj = null;
	$('div[name]').draggable({
		revert: true,
		helper: "clone",
		start: function(event, ui) {
			draggingObj = $(this).attr('id');
			$(this).css('z-index','1000');
		},
		stop: function(event, ui) {
			$(this).css('z-index','0');
		}
	});

	// drop 되었을때 처리
	$('div[name]').droppable({
		hoverClass: "acl-map-active",
		over: function(event, ui) {
			$('.ui-draggable-dragging .acl-title').html($('#'+draggingObj).attr('name')+" -> "+$(this).attr('name'));
		},
		out: function(event, ui) {
			$('.ui-draggable-dragging .acl-title').html($('#'+draggingObj).attr('name'));
		},
		drop: function(event, ui) {
			if($('#'+draggingObj).attr('aclsize')=='0') {
				alert("<spring:message code="portal.alert.msg.53" text="복사할 리소스의 권한정보가 없는 경우에는 복사할 수 없습니다."/>");
				return false;
			}

			// 다국어 셋팅
			messaage2 = message2.replace("{1}", $('#'+draggingObj).attr('name')).replace("{2}", $(this).attr('name')).replace("{3}", $(this).attr('name'));
			if(confirm(messaage2)) {
				callJson("accessCotrolController","aclCopy","resourceIdFrom="+draggingObj+"&resourceIdTo="+$(this).attr('id'), function(data) {
					if(eval(data)==true) {
						alert("<spring:message code="portal.alert.msg.54" text="복사되었습니다."/>");
						reload();
					} else {
						alert("<spring:message code="portal.alert.msg.9" text="오류가 발생하였습니다."/>");
					}
				});
			}
		}
	});
	
	// 각 리소스 클릭시 이벤트 및 효과 및 // 권한설정
	$('div[name]').click(function(e) {
		$('div[name]').removeClass('bold');
		$(this).addClass('bold');
	}).dblclick(function(e) {
		luxor.popup("/ep/acl/aclSetupUser.do?resourceID="+$(this).attr('id')+"&callback=reload", {width:850,height:600});
	});

	// 화면 초기화
	$('#btn_reset').click(function() {
		$('div[name]').removeClass('bold');
		$('div[name]').show();
	});
	
	// 페이지 선택시 페이지에 포함된 컨텐츠만 보여줌
	$('#pageAclMap div[name]').click(function() {
		callJson("portalPageZoneController", "getContentsOnPage", "pageId="+$(this).attr('id'), function(data) {
			$('#contentAclMap div[name]').hide();
			for(var i=0; i < data.length; i++) {
				$("#"+data[i].contentId).fadeIn('slow');
			}
		});	
	});

	// 컨텐츠 선택시 컨텐츠가 포함된 페이지만 보여줌
	$('#contentAclMap div[name]').click(function() {
		callJson("portalPageZoneController", "getPagesHasContent", "contentId="+$(this).attr('id'), function(data) {
			$('#pageAclMap div[name]').hide();
			for(var i=0; i < data.length; i++) {
				$("#"+data[i].pageId).fadeIn('slow');
			}
		});	
	});

	// 검색
	$('#query').keypress(function(e) {
		if(e.keyCode==13) {
			$('#btn_search').click();
		}
	});
	
	$('#btn_search').click(function() {
		$('div[name]').hide();
		$('div[name]').each(function() {
			if($(this).attr('name').toUpperCase().indexOf($('#query').val().toUpperCase()) > -1 
					|| $(this).text().toUpperCase().indexOf($('#query').val().toUpperCase()) > -1) {
				$(this).fadeIn('slow');
			}
		});
	});
	
});
</script>
</head>

<body>
<div class="admin-wrap">
	<div class="admin-content-width w1200 ml15">
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.label.70" text="권한맵"/></span>
			<div class="aleft">
				<div class="box-font-blue" style="float: left;padding:5px 10px;">
					<spring:message code="portal.page.label.20" text="- 상자를 드래그하여 다른 개체로 권한을 복사할 수 있습니다."/>
	        	</div>
			</div>
			<div class="aright">
				<input type="text" id="query" />
				<span class="main-btn"><a href="#" id="btn_search"><spring:message code="portal.btn.label.8" text="검색"/></a></span>
				<span class="main-btn"><a href="#" id="btn_reset"><spring:message code="portal.btn.label.48" text="화면 초기화"/></a></span>
			</div>
		</div>
		<div class="table-body-wrap">
			<div id="pageAclMap" class="page-acl-map">
				<div class="page-acl-map-title"><spring:message code="portal.label.71" text="페이지 권한"/></div>
			<%
				for(TreeVO _page : pageList) {
					String nodeId = _page.getNodeId();
					String nodeName = _page.getNodeName();
					AccessList aclList = (AccessList)aclMap.get(nodeId);
					List<Access> accessList = aclList.getAccessList();
					
					String styleCss = "";
					styleCss = "margin-bottom:5px;border-bottom:1px solid #828282;";
					
					out.println("<div id='"+nodeId+"' name='"+nodeName+"' aclsize='"+accessList.size()+"' class='page-acl-resource'>");
					out.println("<div class='acl-title' style='background-color:#f0f0f0;"+styleCss+"padding:5px;'>"+nodeName+"</div>");
					
					if(accessList.size() > 0) {
						out.println("<div class='page-acl-resource-p' style='min-height:10px;'>");
					}
					
					for(int i=0; i < accessList.size(); i++) {
						Access access = accessList.get(i);
						String accessName = access.getAccessName();
						Permission p = access.getPermission();
						
						String r = p.isReadable() ? "R" : "";
						String w = p.isWriteable() ? "W" : "";
						String d = p.isDeleteable() ? "D" : "";
						String m = p.isModifiable() ? "M" : "";
						String rwdm = "".equals(r+w+d+m) ? message1 : r+w+d+m;
						
						out.println("<p style='margin-bottom:2px;'>- "+accessName+" ["+rwdm+"]</p>");
					}
					
					if(accessList.size() > 0) {
						out.println("</div>");
					} else {
						out.println("<div class='page-acl-resource-p' style='min-height:10px;'></div>");
					}
					out.println("</div>");
				}
			%>
			</div>
			<div id="contentAclMap" class="content-acl-map">
				<div class="page-acl-map-title"><spring:message code="portal.label.72" text="컨텐츠 권한"/></div>
			<%
				for(PortalContentVO content : contentList) {
					String contentId = content.getContentId();
					String contentName = content.getMessageName();
					AccessList aclList = (AccessList)aclMap.get(contentId);
					List<Access> accessList = aclList.getAccessList();
					
					String styleCss = "";
					styleCss = "margin-bottom:5px;border-bottom:1px solid #828282;";
					
					out.println("<div id='"+contentId+"' name='"+contentName+"' aclsize='"+accessList.size()+"' class='page-acl-resource'>");
					out.println("<div class='acl-title' style='background-color:#f0f0f0;"+styleCss+"padding:5px;'>"+contentName+"</div>");
					
					if(accessList.size() > 0) {
						out.println("<div class='page-acl-resource-p' style='min-height:15px;'>");
					}
					
					for(int i=0; i < accessList.size(); i++) {
						Access access = accessList.get(i);
						String accessName = access.getAccessName();
						Permission p = access.getPermission();
						
						String r = p.isReadable() ? "R" : "";
						String w = p.isWriteable() ? "W" : "";
						String d = p.isDeleteable() ? "D" : "";
						String m = p.isModifiable() ? "M" : "";
						String rwdm = "".equals(r+w+d+m) ? message1 : r+w+d+m;
						
						out.println("<p style='margin-bottom:2px;'>- "+accessName+" ["+rwdm+"]</p>");
					}
					
					if(accessList.size() > 0) {
						out.println("</div>");
					} else {
						out.println("<div class='page-acl-resource-p' style='min-height:15px;'></div>");
					}
					out.println("</div>");
				}
			%>			
			</div>
		</div>
	</div>
</div>
</body>
</html>