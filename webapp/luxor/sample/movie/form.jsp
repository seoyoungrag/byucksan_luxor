<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.sample.vo.MovieVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>

<%
	boolean isReg = false;
	MovieVO movie = (MovieVO)request.getAttribute("movie");
	if(movie==null) {
		isReg = true;
		movie = new MovieVO();
	}
	String callMethod = isReg ? "insertMovie" : "updateMovie";
	
	String todayDate = CommonUtil.getNow();
	
	String now_year_item  = todayDate.substring(0,4);
	String now_month_item = todayDate.substring(4,6);
	String now_date_item  = todayDate.substring(6,8);
	
	String datePattern = LuxorConfig.getEnvString(request, "DATE_PATTERN_FOR_DATEPICKER").toLowerCase();
	
	String attachmentId = movie.getAttachmentId();
	String moduleDiv = "COMMON.TEST";
	Locale locale = (Locale)session.getAttribute("LANG_TYPE");
	String attachType = "write";
	String lang = (locale.getLanguage().indexOf("ko") != -1) ? "K" : "E";
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>Movie Reg</title>
<jsp:include page="/luxor/common/header.jsp" />
<link rel="stylesheet" href="/ep/luxor/ref/js/attachment/jquery/css/smoothness/jquery-ui-1.8.14.custom.css" id="theme">
<script type="text/javascript" src="/ep/luxor/ref/js/logger.js" charset="utf-8"></script>
<script type="text/javascript">
var now_year = "<%=now_year_item%>";
var now_month = "<%=now_month_item%>";
var now_date = "<%=now_date_item%>";
var datePattern = "<%=datePattern%>";
var uploadDone= false;

$.datepicker.setDefaults({
    dateFormat: datePattern,
    buttonImageOnly: true,
    buttonText: "달력",
    buttonImage: "/ep/luxor/ref/image/admin/icon_cal.gif"
}); 

$(document).ready(function() {
	<%	if(isReg==false) { %>
	$('#code').attr('readonly','readonly');
	$('#code').removeClass('input-d');
	$('#code').addClass('input-readonly');
	<%	} %>
	
	//luxor.editor.setSimpleToolbar(false);
	
	$("#datepicker1").val(now_year+"/"+now_month+"/"+now_date);
	
	$("#datepicker").datepicker({
		inline: true,
		defaultDate: new Date(now_year, now_month - 1, now_date),
		onSelect: function(date) { 
			$("#datepicker1").val(date); 
		}
	});

	$("#datepicker1").datepicker({
		defaultDate: new Date(now_year, now_month - 1, now_date),
		showOn: "both", 
		showAnim: "blind", 
		showOptions: {direction: 'horizontal'},
		duration: 200
	});
	
	var interval;
	$('#btn_save').click(function() {
		startFileUpload(function(data){
			$('#attachmentId').val(data);
		});
		interval = setInterval(function(){
			if(uploadDone) {
				clearInterval(interval);
				luxor.editor.setContent();
				var param = $('#reg_form').serialize();
				postJson("movieController", "<%=callMethod%>", param, function(data) {
					alert("저장되었습니다.");
					opener.location.reload();
					self.close();
				});
				return;
			}
		  }, 1000);
	});
	
	
	$('#btn_cancel').click(function() {
		self.close();
	});
});
</script>
</head>

<body style="padding:10px">
	<div class="admin-content-width w100percent">
		<div class="title-sub" style="margin-top:0">
			<span class="title-sub-text">영화등록</span>
			<div class="aright"></div>
		</div>
		<div class="tb-write" id="attach01">
			<form id="reg_form">
			<input type="hidden" id="attachmentId" name="attachmentId" />
			<table class="table-write">
				<tr>
					<th width="100">코드</th>
					<td><input type="text" id="code" name="code" class="input-d" style="width:100%" value="<%=movie.getCode()%>" /></td>
				</tr>
				<tr>
					<th width="100">제목</th>
					<td><input type="text" name="title" class="input-d" style="width:100%" value="<%=movie.getTitle()%>" /></td>
				</tr>
				<tr>
					<th width="100">출시일</th>
					<td><input type="text" class="input-d" id="datepicker1" name="releaseDateString" style="width:80px;" /></td>
				</tr>
				<tr>
					<th width="100">줄거리</th>
					<td>
						<jsp:include page="/luxor/common/editor.jsp">
							<jsp:param name="EDITOR_TYPE" value="XINHA" />
							<jsp:param name="CONTENT" value="<%=movie.getContent()%>" />
						</jsp:include>
					</td>
				</tr>
				<tr>
					<td colspan="2"><div class="file-list-div">
					<%@ include file="/luxor/common/attachment/attachment.jsp" %>
					</div></td>
				</tr>
			</table>
			</form>
			<div id="datepicker" class="datepicker_div" style="display:none;"/>
		</div>
		<div class="aright"> 
			<span class="main-btn"><a href="#" id="btn_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span> 
			<span class="main-btn"><a href="#" id="btn_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span> 
		</div>
	</div>
</body>
</html>

