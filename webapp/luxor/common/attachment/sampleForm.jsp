<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>
<%@ page import="com.sds.acube.luxor.common.ConstantList" %>
<%@page import="com.sds.acube.luxor.common.util.UtilRequest"%>

<%
	String contextPath = request.getContextPath();
	String attachmentId = UtilRequest.getString(request, "attachmentId") == null ? "" : UtilRequest.getString(request, "attachmentId");
	String lang = null;
	Locale locale = (Locale)session.getAttribute("LANG_TYPE");
	if(locale.getLanguage().indexOf("ko") != -1) lang = "K";
	else lang = "E";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>파일첨부 테스트</title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/jquery.form.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/logger.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/attachment/attachment.js" charset="utf-8"></script>
<script type="text/javascript">

	$(document).ready(function() {
		// Logger setting
		Logger.setLogger('loggerPane');
		Logger.setLoggable(false);
		LOG_PERMISSION = LOG_LEVEL_DEBUG;

		$('#attach01').attachment({
			'attachId' : '<%=attachmentId%>',
			'moduleDiv' : 'COMMON.TEST',
			'lang' : '<%=lang%>',
			'serverUrl' : '<%=LuxorConfig.getString("Common", "ATTACH.SFTP_SVR")%>',
			'serverPort' : '<%=LuxorConfig.getString("Common", "ATTACH.SFTP_PORT")%>',
			'tempDir' : '<%=LuxorConfig.getString("Common", "ATTACH.UPLOAD_TEMP")%>'
		});

		/*$('#attach02').attachment({
			'attachId' : '',
			'lang' : 'K',
			'serverUrl' : '70.7.33.150',
			'serverPort' : '7775',
			'tempDir' : 'D:/Temp/sftp_temp'
		});*/

	});
</script>
<script type="text/javascript">
	function apply() {
		$('#attach01').upload(applyConfirm);
	}

	function applyConfirm(json) {
		if(json.success) {
			self.location.href = '<%=contextPath%>/luxor/common/attachment/sampleForm.jsp?attachmentId=' + json.attachmentId;
		}
	}
</script>
</head>
<body>
<div id="loggerPane" title="Debug Console">This is Debug Console!!</div>
<form id="listForm" name="listForm" method="post">
<!-- admin-content-width w1000 margin15-l -->
<div class="admin-content-width w1200 ml15" name="zone">
	 <!-- subtitle -->
	<div class="title-sub">
		<span class="title-sub-text">파일첨부 테스트</span>
	</div>

	<!-- content -->
	<div class="table-body-wrap">
		<table class="table-write">
			<tr>
				<th width="100">포탈 ID</th>
				<td><input type="text" name="portalId" id="portalId" class="input-d" style="width:75%;" />
					&nbsp;<a href="#">중복확인</a>
				</td>
			</tr>
			<tr>
				<th>포탈 명</th>
				<td>
					<input type="text" name="portalName" id="portalName" class="input-d" style="width:100%;" />
				</td>
			</tr>
		</table>
		<div id="attach01">
			<table class="table-write">
				<tr>
					<th width="100">파일첨부</th>
					<td>
						<span class="smain-btn"><a href="#" id="btnAddFile" class="attach-addBtn">추가</a></span>
						<span class="smain-btn"><a href="#" id="btnDownload" class="attach-downBtn">저장</a></span>
					</td>
				</tr>
			</table>
			<div class="file-list-div"></div>
		</div>
		<!-- 
		<div id="attach02">
			<table class="table-write">
				<tr>
					<th width="100">파일첨부</th>
					<td>
						<span class="smain-btn"><a href="#" id="btnAddFile" class="attach-addBtn">추가</a></span>
						<span class="smain-btn"><a href="#" id="btnDownload" class="attach-downBtn">저장</a></span>
						<span class="smain-btn"><a href="#" id="btnCurInfo" onclick="displayCurrent('#attach01');">현재 상태</a></span>
					</td>
				</tr>
			</table>
			<div class="file-list-div"></div>
		</div>
		
		 -->
		<div class="mb5"></div>
		<div class="aright">
			<span class="main-btn"><a href="#" id="btnApply" onclick="apply();" title="<spring:message code="portal.btn.label.3" text="저장"/>"><spring:message code="portal.btn.label.3" text="저장"/></a></span><!-- 저장버튼 -->
			<span class="main-btn"><a href="#" id="btnCancel" onclick="cancel();" <spring:message code="portal.btn.label.2" text="취소"/>"><spring:message code="portal.btn.label.2" text="취소"/></a></span><!-- 취소버튼 -->
		</div>
	</div>
</div>
</form>
</body>
</html>
