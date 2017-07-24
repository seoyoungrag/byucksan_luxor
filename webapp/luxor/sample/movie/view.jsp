<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.sample.vo.MovieVO" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>

<%
	MovieVO movie = (MovieVO)request.getAttribute("movie");
	Timestamp timestamp = movie.getReleaseDate();
	String releaseDate = CommonUtil.formatDate(timestamp, LuxorConfig.getEnvString(request, "DATE_PATTERN_SHORT"));
	String attachmentId = movie.getAttachmentId();
	String moduleDiv = "COMMON.TEST";
	String attachType = "read";
	Locale locale = (Locale)session.getAttribute("LANG_TYPE");
	String lang = (locale.getLanguage().indexOf("ko") != -1) ? "K" : "E";
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>Movie Reg</title>
<jsp:include page="/luxor/common/header.jsp" />
<link rel="stylesheet" href="/ep/luxor/ref/js/attachment/jquery/css/smoothness/jquery-ui-1.8.14.custom.css" id="theme">
<script type="text/javascript" src="/ep/luxor/ref/js/logger.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/attachment/attachment.js" charset="utf-8"></script>
<script type="text/javascript">
	
	$('#btn_close').click(function() {
		self.close();
	});
});
</script>
</head>

<body style="padding:10px">
	<div class="admin-content-width w100percent">
		<div class="title-sub" style="margin-top:0">
			<span class="title-sub-text">영화조회</span>
			<div class="aright"></div>
		</div>
		<div class="tb-write">
			<form id="reg_form">
			<input type="hidden" id="actionMode" />
			<table class="table-write">
				<tr>
					<th width="100">코드</th>
					<td><%=movie.getCode()%></td>
				</tr>
				<tr>
					<th width="100">제목</th>
					<td><%=movie.getTitle()%></td>
				</tr>
				<tr>
					<th width="100">출시일</th>
					<td><%=releaseDate%></td>
				</tr>
				<tr>
					<th width="100">등록일</th>
					<td><%=movie.getRegDate2String()%></td>
				</tr>
				<tr>
					<th width="100">수정일</th>
					<td><%=movie.getModDate2String()%></td>
				</tr>
				<tr>
					<th>줄거리</th>
					<td><%=UtilEditor.getContent(session, movie.getContent())%></td>
				</tr>
				<tr>
					<th>첨부</th>
					<td>
						<%@ include file="/luxor/common/attachment/attachment.jsp" %>
					</td>
				</tr>
			</table>
			</form>
		</div>
		<div class="text-center"> 
			<span class="main-btn"><a href="#" id="btn_close"><spring:message code="portal.btn.label.29" text="닫기" /></a></span> 
		</div>
	</div>
</body>
</html>

