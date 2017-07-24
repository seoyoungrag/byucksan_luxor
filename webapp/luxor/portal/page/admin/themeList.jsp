<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	Page _page = (Page)request.getAttribute("page");
	List list = (List)_page.getList();
	PortalPageThemeVO[] themeList = new PortalPageThemeVO[list.size()];
	list.toArray(themeList);
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.60" text="페이지 테마 관리" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#theme_form_div').dialog({
			autoOpen:false,
			resizable:false,
			modal:true,
			width:620,
			position:['center',50],
			close:function(event, ui) { // 팝업창 닫을때 초기화
				$('#theme_form input').each(function() { $(this).val(''); });
			}
		});
		
		// 테마 추가 버튼 클릭시
		$('#btn_theme_add').click(function() {
			$('#themeId').val('');
			$('#actionMode').val('');
			$('#theme_form_div').dialog('open');
		});

		$('#btn_theme_cancel').click(function() {
			$('#theme_form_div').dialog('close');
		});

		/* 수정/삭제 버튼 클릭시 */
		$('table a').click(function() {
			if($(this).attr('mode')=='mod') {
				var param = "themeId="+$(this).attr('themeId');
				callJson("portalPageThemeController", "getPageTheme", param, function(data) {
					Message.setMessageId(data.themeNameId);
					$('#themeId').val(data.themeId);
					$('#themeCssUrl').val(data.themeCssUrl);
					$('#themeImageUrl').val(data.themeImageUrl);
					$('#actionMode').val('mod');
				});
				$('#theme_form_div').dialog({title:'<spring:message code="portal.label.66" text="테마 수정" />'});
				$('#theme_form_div').dialog('open');
				return false;
			}
			else if($(this).attr('mode')=='setDefault') {
				if(!confirm("<spring:message code="portal.alert.msg.48" text="기본 테마로 설정하겠습니까?" />")) {
					return false;
				}
				var param = "themeId="+$(this).attr('themeId')+"&isDefault=Y";
				callJson("portalPageThemeController", "setDefaultPageTheme", param, function(data) {
					alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
					document.location.reload();
					return false;
				});
			} 
			else if($(this).attr('mode')=='del') {
				if(!confirm("<spring:message code="portal.alert.msg.110" text="삭제하시겠습니까?" />")) {
					return false;
				}

				var param = "themeId="+$(this).attr('themeId')+"&messageId="+$(this).attr('messageId');
				callJson("portalPageThemeController", "deletePageTheme", param, function(data) {
			        if(data._errorCode=='-9999') {
			        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			        } else {
			            alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
			        }
			        top.location.reload();
				});
			}
		});
		
		$('#btn_theme_save').click(function() {
			if(luxor.isNullOrEmpty(Message.getMessageNameAll())) {
				alert("<spring:message code="portal.alert.msg.45" text="테마명을 입력하세요." />");
				return false;
			}
			if(luxor.isNullOrEmpty($('#themeCssUrl').val())) {
				alert("<spring:message code="portal.alert.msg.46" text="테마 스타일시트 경로를 입력하세요." />");
				$('#themeCssUrl').focus();
				return false;
			}
			if(luxor.isNullOrEmpty($('#themeImageUrl').val())) {
				alert("<spring:message code="portal.alert.msg.47" text="테마 이미지 폴더 경로를 입력하세요." />");
				$('#themeImageUrl').focus();
				return false;
			}

			if(!confirm("<spring:message code="portal.alert.msg.186" text="정상적인 경로를 등록하지 않을 경우 화면이 제대로 표시되지 않을 수 있습니다. 저장하시겠습니까?" />")) {
				return false;
			}
			
			var method = $('#actionMode').val()=='mod' ? 'updatePageTheme' : 'insertPageTheme';
			postJson("portalPageThemeController", method, $('#theme_form').serialize(), function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
		        }
		        top.location.reload();
			});
		});
	});

	function changePage(p) {
		document.location.href = "/ep/page/theme/list.do?cPage="+p;
	}
</script>
</head>

<body>

	<div class="admin-content-width w1200 ml15" name="zone">
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.label.60" text="페이지 테마 관리" /></span>
			<div class="aright">
				<span class="main-btn"><a href="#" id="btn_theme_add"><spring:message code="portal.btn.label.28" text="등록" /></a></span>
			</div>
		</div> 
		<div class="table-body-wrap">
			<table class="table-body">
			<thead>
				<tr>
					<th width="180px"><spring:message code="portal.label.61" text="테마 ID" /></th>
					<th><spring:message code="portal.label.62" text="테마명" /></th>
					<th><spring:message code="portal.label.63" text="테마 CSS 경로" /></th>
					<th><spring:message code="portal.label.64" text="테마 Image 경로" /></th>
					<th width="110px"><spring:message code="portal.label.67" text="기본 테마" /></th>
					<th width="150px"><spring:message code="portal.label.54" text="수정일" /></th>
					<th width="180px"><spring:message code="portal.label.39" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
	<%
		for(PortalPageThemeVO theme : themeList) {
			String themeName = theme.getMessageName();
			String themeNameId = theme.getThemeNameId();
			String themeCssUrl = theme.getThemeCssUrl();
			String themeImageUrl = theme.getThemeImageUrl();
			String regDate = theme.getRegDate2String();
			String modDate = theme.getModDate2String();
			String themeId = theme.getThemeId();
			String isDefault = theme.getIsDefault();
			if(!"Y".equals(isDefault)) {
				isDefault = "N";
			}
			String trClass = "";
			if("Y".equals(isDefault)) {
				trClass = "class='hover'";
			}
	%>
					<tr <%=trClass%>>
						<td><%=themeId%></td>
						<td><%=themeName%></td>
						<td><%=themeCssUrl%></td>
						<td><%=themeImageUrl%></td>
						<td><%=isDefault%></td>
						<td><%=modDate%></td>
						<td>
							<div class="acenter">
								<span class="smain-btn"><a href="#" mode="setDefault" themeId="<%=themeId%>" messageId="<%=themeNameId%>"><spring:message code="portal.btn.label.45" text="기본으로" /></a></span>
								<span class="smain-btn"><a href="#" mode="mod" themeId="<%=themeId%>" messageId="<%=themeNameId%>"><spring:message code="portal.btn.label.19" text="수정" /></a></span>
								<span class="smain-btn"><a href="#" mode="del" themeId="<%=themeId%>" messageId="<%=themeNameId%>"><spring:message code="portal.btn.label.30" text="삭제" /></a></span>
							</div>
						</td>
					</tr>
	<%		
		}
	%>
				</tbody>
			</table>
		</div>
		<!-- 페이징 -->
		<jsp:include page="/luxor/common/paging.jsp">
			<jsp:param name="cPage" value="<%=_page.getCurrentPage()%>" />
			<jsp:param name="totalCount" value="<%=_page.getTotalCount()%>" />
		</jsp:include>
		<!-- //페이징 -->
	</div>
	
<div id="theme_form_div" title="<spring:message code="portal.label.65" text="테마 등록" />">
	<div class="admin-content-pop">
		<div class="tb-write">
			<form id="theme_form">
			<input type="hidden" id="themeId" name="themeId" />
			<input type="hidden" id="actionMode" />
			<table class="table-write">
				<tr>
					<th width="150"><spring:message code="portal.label.62" text="테마명" /></th>
					<td><jsp:include page="/luxor/common/message.jsp" /></td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.63" text="테마 CSS 경로" /></th>
					<td><input id="themeCssUrl" name="themeCssUrl" class="textarea-d" style="width:99%;" /></td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.64" text="테마 Image 경로" /></th>
					<td><input id="themeImageUrl" name="themeImageUrl" class="textarea-d" style="width:99%;" /></td>
				</tr>
			</table>
			</form>
		</div>
		<div class="aright"> 
			<span class="main-btn"><a href="#" id="btn_theme_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span> 
			<span class="main-btn"><a href="#" id="btn_theme_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span> 
		</div>
	</div>
</div>

<div id="hidden_div" class="hidden"></div>

</body>
</html>
