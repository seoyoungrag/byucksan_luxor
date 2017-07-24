<%/* 로그인 팝업 리스트 */%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>
<%@ page import="org.springframework.context.support.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="org.springframework.context.MessageSource"%>
<%
	Page _page = (Page)request.getAttribute("page");
	LoginPopupVO[] popupList = (LoginPopupVO[])request.getAttribute("popups");

	String todayDate = CommonUtil.getNow();
	String now_year_item  = todayDate.substring(0,4);
	String now_month_item = todayDate.substring(4,6);
	String now_date_item  = todayDate.substring(6,8);
	
	String weekLater = CommonUtil.getWeekAgo(-1);
	String week_year_item  = weekLater.substring(0,4);
	String week_month_item = weekLater.substring(4,6);
	String week_date_item  = weekLater.substring(6,8);
	
	String datePattern1 = LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT");
	String datePattern2 = LuxorConfig.getEnvString(request, "DATE_PATTERN_FOR_DATEPICKER");
	
	String defaultStartDate = CommonUtil.formatDate(todayDate, datePattern1);
	String defaultEndDate = CommonUtil.formatDate(weekLater, datePattern1);

	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	MessageSource messageSource = (MessageSource)ctx.getBean("messageSource");
	Locale langType = (Locale)session.getAttribute("LANG_TYPE");
%>

<!DOCTYPE HTML>

<html lang="ko">
<head>
<title><spring:message code="portal.label.81" text="로그인 팝업" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/content.js"></script>
<script type="text/javascript">
var mode = 'insert';
var now_year = "<%=now_year_item%>";
var now_month = "<%=now_month_item%>";
var now_date = "<%=now_date_item%>";
var datePattern = "<%=datePattern2%>";
var defaultStartDate = "<%=defaultStartDate%>";
var defaultEndDate = "<%=defaultEndDate%>";

$.datepicker.setDefaults({
    dateFormat: datePattern,
    buttonImageOnly: true,
    buttonText: "달력",
    buttonImage: "/ep/luxor/ref/image/admin/icon_cal.gif"
});

function init() {
    $("#datepicker1").val("<%=defaultStartDate%>"); 
	$("#datepicker2").val("<%=defaultEndDate%>"); 
    $("#startDate").val("<%=defaultStartDate%>"); 
	$("#endDate").val("<%=defaultEndDate%>"); 
}

$(document).ready(function() {
	init();
	
    $("#datepicker1").datepicker({
        defaultDate: new Date(now_year, now_month - 1, now_date),
        showOn: "both", 
        showAnim: "blind", 
        showOptions: {direction: 'horizontal'},
        duration: 200,
        onSelect: function(dateText, inst) {
        	$('#startDate').val(dateText);
        }
    });
    
    $("#datepicker2").datepicker({
        defaultDate: new Date(now_year, now_month - 1, now_date),
        showOn: "both",
        showAnim: "blind", // blind, clip, drop, explode, fold, puff, slide, scale, size, pulsate, bounce, highlight, shake, transfer
        showOptions: {direction: 'horizontal'},
        duration: 200,
        onSelect: function(dateText, inst) {
        	$('#endDate').val(dateText);
        }
    });
	
	$('#popup_form_div').dialog({
		autoOpen:false,
		modal:true,
		resizable:false,
		width:620,
		position:['center',50],
		close:function(event, ui) { // 팝업창 닫을때 초기화
			$('#popup_form input').each(function() { $(this).val(''); });
		}
	});

	// 등록버튼 클릭시
	$('#btn_popup_add').click(function() {
		mode = 'insert';
		$('#popup_form_div').dialog({title:'<spring:message code="portal.label.82" text="로그인 팝업 등록" />'});
		$('#popup_form_div').dialog('open');
		$('#popupTitle').focus();
		init();
	});

	// 등록/수정
	$('#btn_popup_save').click(function() {
		if(luxor.isNullOrEmpty($('#popupTitle').val())) {
			alert("<spring:message code="portal.alert.msg.62" text="제목을 입력하세요." />");
			$('#popupTitle').focus();
			return false;
		}
		if(luxor.isNullOrEmpty($('#popupUrl').val())) {
			alert("<spring:message code="portal.alert.msg.63" text="URL을 입력하세요." />");
			$('#popupUrl').focus();
			return false;
		}
		if(luxor.isNullOrEmpty($('#startDate').val())) {
			alert("<spring:message code="portal.alert.msg.64" text="시작 날짜를 선택하세요." />");
			return false;
		}
		if(luxor.isNullOrEmpty($('#endDate').val())) {
			alert("<spring:message code="portal.alert.msg.65" text="종료 날짜를 선택하세요." />");
			return false;
		}
		if(luxor.isNullOrEmpty($('#windowWidth').val())) {
			alert("<spring:message code="portal.alert.msg.66" text="팝업창 넓이를 입력하세요." />");
			$('#windowWidth').focus();
			return false;
		}
		if(luxor.isNumber($('#windowWidth').val())==false) {
			alert("<spring:message code="portal.alert.msg.67" text="팝업창 넓이는 숫자로 입력하세요." />");
			$('#windowWidth').focus();
			return false;
		}
		if(luxor.isNullOrEmpty($('#windowHeight').val())) {
			alert("<spring:message code="portal.alert.msg.68" text="팝업창 높이를 입력하세요." />");
			$('#windowHeight').focus();
			return false;
		}
		if(luxor.isNumber($('#windowHeight').val())==false) {
			alert("<spring:message code="portal.alert.msg.69" text="팝업창 높이는 숫자로 입력하세요." />");
			$('#windowHeight').focus();
			return false;
		}
		if(luxor.isNullOrEmpty($('#positionTop').val())) {
			alert("<spring:message code="portal.alert.msg.70" text="팝업창 상단 위치를 입력하세요." />");
			$('#positionTop').focus();
			return false;
		}
		if(luxor.isNumber($('#positionTop').val())==false) {
			alert("<spring:message code="portal.alert.msg.71" text="팝업창 상단 위치는 숫자로 입력하세요." />");
			$('#positionTop').focus();
			return false;
		}
		if(luxor.isNullOrEmpty($('#positionLeft').val())) {
			alert("<spring:message code="portal.alert.msg.72" text="팝업창 왼쪽 위치를 입력하세요." />");
			$('#positionTop').focus();
			return false;
		}
		if(luxor.isNumber($('#positionLeft').val())==false) {
			alert("<spring:message code="portal.alert.msg.73" text="팝업창 왼쪽 위치는 숫자로 입력하세요." />");
			$('#positionLeft').focus();
			return false;
		}
		
		var param = $('#popup_form').serialize();
		postJson('loginPopupController', mode+'Popup', param, function(data) {
			alert('<spring:message code="portal.alert.msg.13" text="저장되었습니다." />');
			top.location.reload();
		});
	});

	// 수정버튼 클릭시
	$('a[mode=mod]').click(function() {
		var param = "popupId="+$(this).attr('popupId');
		callJson('loginPopupController', 'getPopup', param, function(data) {
			mode = 'update';
			$('#popupId').val(data.popupId);
			$('#popupTitle').val(data.popupTitle);
			$('#popupUrl').val(data.popupUrl);
			
			if(data.popupType=='E') {
				$('#popupTypeE').attr('checked','checked');
			} else {
				$('#popupTypeO').attr('checked','checked');
			}
			
			$('#windowWidth').val(data.windowWidth);
			$('#windowHeight').val(data.windowHeight);
			$('#positionTop').val(data.positionTop);
			$('#positionLeft').val(data.positionLeft);
			
			$("#datepicker1").val(data.startDate); 
			$("#datepicker2").val(data.endDate); 
			$('#popup_form_div').dialog({title:'<spring:message code="portal.label.83" text="로그인 팝업 수정" />'});
			$('#popup_form_div').dialog('open');
		});
	});
	
	// 삭제
	$('a[mode=del]').click(function() {
		if(confirm("<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?" />")) {
			var param = "popupId="+$(this).attr('popupId');
			callJson('loginPopupController', 'deletePopup', param, function(data) {
				if(eval(data)==true) {
					alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
					top.location.reload();
				}
			});
		}
	});

	// 권한설정
	$('a[mode=priv]').click(function() {
		var popupId = $(this).attr('popupId');
		luxor.popup("/ep/acl/aclSetupUser.do?resourceID="+popupId, {width:850,height:600});
	});
	
	// 취소
	$('#btn_popup_cancel').click(function() {
		$('#popup_form_div').dialog('close');
	});
});

function changePage(p) {
	document.location.href = "/ep/loginpopup/list.do?cPage="+p;
}
</script>
</head>

<body>

<div class="admin-wrap">
	<div class="admin-content-width w1200 ml15" name="zone">
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.label.81" text="로그인 팝업" /></span>
			<div class="aright">
				<span class="main-btn"><a href="#" id="btn_popup_add"><spring:message code="portal.btn.label.28" text="등록" /></a></span>
			</div>
		</div> 
		<div class="table-body-wrap">
			<table class="table-body">
			<thead>
				<tr>
					<th width="250px"><spring:message code="portal.btn.label.12" text="제목" /></th>
					<th width="*"><spring:message code="portal.label.84" text="팝업 URL" /></th>
					<th width="130px"><spring:message code="portal.label.85" text="종류" /></th>
					<th width="120px"><spring:message code="portal.label.86" text="시작일" /></th>
					<th width="120px"><spring:message code="portal.label.87" text="종료일" /></th>
					<th width="200px"><spring:message code="portal.label.31" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
			<%
				for(LoginPopupVO popup : popupList) {
					String popupId = popup.getPopupId();
					String popupTitle = popup.getPopupTitle();
					String popupUrl = popup.getPopupUrl();
					String popupType = "E".equals(popup.getPopupType()) ? messageSource.getMessage("portal.label.88", null, langType) : messageSource.getMessage("portal.label.89", null, langType);
					String startDate = CommonUtil.formatDate(CommonUtil.toDate(popup.getStartDate(), "yyyyMMdd"), datePattern1);
					String endDate = CommonUtil.formatDate(CommonUtil.toDate(popup.getEndDate(), "yyyyMMdd"), datePattern1);
			%>
				<tr>
					<td><%=popupTitle%></td>
					<td><%=popupUrl%></td>
					<td><%=popupType%></td>
					<td><%=startDate%></td>
					<td><%=endDate%></td>
					<td>
						<div class="acenter">
							<span class="smain-btn"><a href="#" mode="priv" popupId="<%=popupId%>"><spring:message code="portal.btn.label.38" text="권한 설정" /></a></span>
							<span class="smain-btn"><a href="#" mode="mod" popupId="<%=popupId%>"><spring:message code="portal.btn.label.19" text="수정" /></a></span>
							<span class="smain-btn"><a href="#" mode="del" popupId="<%=popupId%>"><spring:message code="portal.btn.label.30" text="삭제" /></a></span>
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
</div>

<div id="popup_form_div" title="<spring:message code="portal.label.82" text="로그인 팝업 등록" />">
	<div class="admin-content-pop">
		<div class="tb-write">
			<form id="popup_form">
			<table class="table-write">
				<tr>
					<th><spring:message code="portal.btn.label.12" text="제목" /></th>
					<td><input type="text" id="popupTitle" name="popupTitle" class="input-d" maxlength="200" style="width:100%" /></td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.84" text="팝업 URL" /></th>
					<td><input type="text" id="popupUrl" name="popupUrl" class="input-d" style="width:100%;" /></td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.85" text="종류" /></th>
					<td>
						<div style="margin-top:5px;">
						<spring:message code="portal.label.88" text="매번" /> <input type="radio" id="popupTypeE" name="popupType" checked="checked" value="E" style="margin-bottom:5px;" /> / 
						<spring:message code="portal.label.89" text="하루에 한번" /> <input type="radio" id="popupTypeO" name="popupType"  value="O" style="margin-bottom:5px;" />
						</div>
					</td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.22" text="기간" /></th>
					<td>
						<input type="text" class="input-d" id="datepicker1" style="width:80px;" /> ~
						<input type="text" class="input-d" id="datepicker2" style="width:80px;" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.90" text="팝업창 크기" /></th>
					<td>
						<spring:message code="portal.label.91" text="넓이" /> : <input type="text" class="input-d" id="windowWidth" name="windowWidth" style="width:40px;" value="300" /> px&nbsp;&nbsp;
						<spring:message code="portal.label.92" text="높이" /> : <input type="text" class="input-d" id="windowHeight" name="windowHeight" style="width:40px;" value="500" /> px
					</td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.93" text="팝업창 위치" /></th>
					<td>
						<spring:message code="portal.label.94" text="상단" /> : <input type="text" class="input-d" id="positionTop" name="positionTop" style="width:40px;" value="0" /> px&nbsp;
						<spring:message code="portal.label.95" text="왼쪽" /> : <input type="text" class="input-d" id="positionLeft" name="positionLeft" style="width:40px;" value="0" /> px
					</td>
				</tr>
			</table>
			<input type="hidden" id="startDate" name="startDate" value="<%=defaultStartDate%>" />
			<input type="hidden" id="endDate" name="endDate" value="<%=defaultEndDate%>" />
			<input type="hidden" id="popupId" name="popupId" />
			</form>
		</div>
		<div class="aright"> 
			<span class="main-btn"><a href="#" id="btn_popup_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span> 
			<span class="main-btn"><a href="#" id="btn_popup_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span> 
		</div>
	</div>
</div>

</body>
</html>