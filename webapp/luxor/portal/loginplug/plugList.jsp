<%/* 로그인플러그 리스트 */%>

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
	LoginPlugVO[] plugList = (LoginPlugVO[])request.getAttribute("plugs");

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
<title><spring:message code="portal.plug.label.1" text="로그인 플러그" /></title>
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
	
	$('#plug_form_div').dialog({
		autoOpen:false,
		modal:true,
		resizable:false,
		width:620,
		position:['center',50],
		close:function(event, ui) { // 플러그 등록창 닫을때 초기화
			$('#plug_form input').each(function() { $(this).val(''); });
			$('#isExternal').click();
		}
	});

	// 등록버튼 클릭시
	$('#btn_plug_add').click(function() {
		mode = 'insert';
		$('#plug_form_div').dialog({title:'<spring:message code="portal.plug.label.2" text="로그인 플러그 등록" />'});
		$('#plug_form_div').dialog('open');
		$('#plugTitle').focus();
		init();
	});

	// 등록/수정
	$('#btn_plug_save').click(function() {
		if(luxor.isNullOrEmpty($('#plugTitle').val())) {
			alert("<spring:message code="portal.alert.msg.62" text="제목을 입력하세요." />");
			$('#plugTitle').focus();
			return false;
		}
		if(luxor.isNullOrEmpty($('#plugUrl').val())) {
			alert("<spring:message code="portal.alert.msg.63" text="URL을 입력하세요." />");
			$('#plugUrl').focus();
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
		var param = $('#plug_form').serialize();
		postJson('loginPlugController', mode+'Plug', param, function(data) {
			alert('<spring:message code="portal.alert.msg.13" text="저장되었습니다." />');
			top.location.reload();
		});
	});

	// 수정버튼 클릭시
	$('a[mode=mod]').click(function() {
		var param = "plugId="+$(this).attr('plugId');
		callJson('loginPlugController', 'getPlug', param, function(data) {
			mode = 'update';
			$('#plugId').val(data.plugId);
			$('#plugTitle').val(data.plugTitle);
			$('#plugUrl').val(data.plugUrl);
			
			if(data.plugType=='E') {
				$('#plugTypeE').attr('checked','checked');
			} else {
				$('#plugTypeO').attr('checked','checked');
			}

			if(data.isExternal=='Y') {
				$('#isExternal').click();
			} else {
				$('#isInternal').click();
			}
			
			$("#datepicker1").val(data.startDate); 
			$("#datepicker2").val(data.endDate); 
			$('#plug_form_div').dialog({title:'<spring:message code="portal.plug.label.3" text="로그인 플러그 수정" />'});
			$('#plug_form_div').dialog('open');
		});
	});
	
	// 삭제
	$('a[mode=del]').click(function() {
		if(confirm("<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?" />")) {
			var param = "plugId="+$(this).attr('plugId');
			callJson('loginPlugController', 'deletePlug', param, function(data) {
				if(eval(data)==true) {
					alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
					top.location.reload();
				}
			});
		}
	});

	// 권한설정
	$('a[mode=priv]').click(function() {
		var plugId = $(this).attr('plugId');
		luxor.popup("/ep/acl/aclSetupUser.do?resourceID="+plugId, {width:850,height:600});
	});

	// 사용설정
	$('a[mode=active]').click(function() {
		var param = "plugId="+$(this).attr('plugId');
		if($(this).attr('isActive') == 'Y') {
			param += "&isActive=N";
		} else {
			param += "&isActive=Y";
		}
		callJson('loginPlugController', 'updateActiveInfo', param, function(data) {
			if(eval(data)==true) {
				alert('<spring:message code="portal.alert.msg.13" text="저장되었습니다." />');
				top.location.reload();
			}
		});
	});
	
	// 취소
	$('#btn_plug_cancel').click(function() {
		$('#plug_form_div').dialog('close');
	});

	//외부url체크
	$('#isExternal').click(function() {
		$('#context_path').html('');
	});
	//내부url체크
	$('#isInternal').click(function() {
		$('#context_path').html('ep/');
	});
	
});

function changePage(p) {
	document.location.href = "/ep/loginPlug/plugList.do?cPage="+p;
}
</script>
</head>

<body>

<div class="admin-wrap">
	<div class="admin-content-width w1200 ml15" name="zone">
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.plug.label.1" text="로그인 플러그" /></span>
			<div class="aright">
				<span class="main-btn"><a href="#" id="btn_plug_add"><spring:message code="portal.btn.label.28" text="등록" /></a></span>
			</div>
		</div> 
		<div class="table-body-wrap">
			<table class="table-body">
			<thead>
				<tr>
					<th width="150px"><spring:message code="portal.btn.label.12" text="제목" /></th>
					<th width="*"><spring:message code="portal.plug.label.4" text="플러그 URL" /></th>
					<th width="100px"><spring:message code="portal.label.85" text="종류" /></th>
					<th width="80px"><spring:message code="portal.label.86" text="시작일" /></th>
					<th width="80px"><spring:message code="portal.label.87" text="종료일" /></th>
					<th width="80px"><spring:message code="portal.plug.label.5" text="등록일" /></th>
					<th width="60px"><spring:message code="portal.plug.label.6" text="사용여부" /></th>
					<th width="230px"><spring:message code="portal.label.31" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
			<%
				for(LoginPlugVO plug : plugList) {
					String plugId = plug.getPlugId();
					String plugTitle = plug.getPlugTitle();
					String plugUrl = plug.getPlugUrl();
					String plugType = "E".equals(plug.getPlugType()) ? messageSource.getMessage("portal.label.88", null, langType) : messageSource.getMessage("portal.label.89", null, langType);
					String startDate = CommonUtil.formatDate(CommonUtil.toDate(plug.getStartDate(), "yyyyMMdd"), datePattern1);
					String endDate = CommonUtil.formatDate(CommonUtil.toDate(plug.getEndDate(), "yyyyMMdd"), datePattern1);
					String regDate = CommonUtil.formatDate(CommonUtil.toDate(plug.getRegDate()+"", "yyyyMMdd"), datePattern1);
					String isActive = plug.getIsActive();
			%>
				<tr>
					<td><%=plugTitle%></td>
					<td><%=plugUrl%></td>
					<td><%=plugType%></td>
					<td><%=startDate%></td>
					<td><%=endDate%></td>
					<td><%=regDate%></td>
					<td><%=isActive%></td>
					<td>
						<div class="acenter">
							<span class="smain-btn"><a href="#" mode="priv" plugId="<%=plugId%>"><spring:message code="portal.btn.label.38" text="권한 설정" /></a></span>
							<span class="smain-btn"><a href="#" mode="active" plugId="<%=plugId%>" isActive="<%=isActive%>"><spring:message code="portal.plug.label.7" text="사용변경" /></a></span>
							<span class="smain-btn"><a href="#" mode="mod" plugId="<%=plugId%>"><spring:message code="portal.btn.label.19" text="수정" /></a></span>
							<span class="smain-btn"><a href="#" mode="del" plugId="<%=plugId%>"><spring:message code="portal.btn.label.30" text="삭제" /></a></span>
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
		<div class="box-font-blue">
			<spring:message code="portal.plug.label.8" text="* 로그인 플러그가 같은 기간에 중복으로 등록되어있는 경우 나중에 등록한 플러그가 적용됩니다."/><br/>
			<spring:message code="portal.plug.label.9" text="* 권한설정을 통해 사용자/조직별로 다른 플러그를 연결시킬 수 있습니다."/>
		</div>
	</div>
</div>


<div id="plug_form_div" title="<spring:message code="portal.plug.label.2" text="로그인 플러그 등록" />">
	<div class="admin-content-pop">
		<div class="tb-write">
			<form id="plug_form">
			<table class="table-write">
				<tr>
					<th width="100"><spring:message code="portal.btn.label.12" text="제목" /></th>
					<td><input type="text" id="plugTitle" name="plugTitle" class="input-d" maxlength="200" style="width:100%" /></td>
				</tr>
				<tr>
					<th><spring:message code="portal.plug.label.4" text="플러그 URL" /></th>
					<td>
						<div style="margin-top:5px; float: left;width:100%;">
						<span id="context_path"></span>
						<input type="text" id="plugUrl" name="plugUrl" class="input-d" style="width:310px; margin-bottom:5px; margin-right: 10px" />
						<spring:message code="portal.plug.label.10" text="외부" /> <input type="radio" id="isExternal" name="isExternal" checked="checked" value="Y" style="margin-bottom:5px;" /> / 
						<spring:message code="portal.plug.label.11" text="내부" /> <input type="radio" id="isInternal" name="isExternal" value="N" style="margin-bottom:5px;" />
						</div>
					</td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.85" text="종류" /></th>
					<td>
						<div style="margin-top:5px;">
						<spring:message code="portal.label.88" text="매번" /> <input type="radio" id="plugTypeE" name="plugType" checked="checked" value="E" style="margin-bottom:5px;" /> / 
						<spring:message code="portal.label.89" text="하루에 한번" /> <input type="radio" id="plugTypeO" name="plugType"  value="O" style="margin-bottom:5px;" />
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
			</table>
			<input type="hidden" id="startDate" name="startDate" value="<%=defaultStartDate%>" />
			<input type="hidden" id="endDate" name="endDate" value="<%=defaultEndDate%>" />
			<input type="hidden" id="plugId" name="plugId" />
			</form>
		</div>
		<div class="aright"> 
			<span class="main-btn"><a href="#" id="btn_plug_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span> 
			<span class="main-btn"><a href="#" id="btn_plug_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span> 
		</div>
	</div>
</div>

</body>
</html>