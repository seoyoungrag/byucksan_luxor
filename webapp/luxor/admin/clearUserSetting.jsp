<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.78" text="개인설정 초기화" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
var selectedUserId = null;
function getUserId(userObj) {
	selectedUserId = userObj[0].userUID;
}

$(document).ready(function() {
	$('#userName').keypress(function(e) {
		if(e.keyCode==13) {
			$('#btn_search').click();
		}
	});
	
	$('#btn_search').click(function() {
		var txtSrch = $("#userName").val();
		var nType = $("#selSearchScope3").val();
		var searchType = $("#selSearchType3").val();
		
		if(luxor.isNullOrEmpty(txtSrch)) {
			alert("<spring:message code="portal.alert.msg.26" text="검색어를 입력하세요." />");
			$("#userName").focus();
			return false;
		}
		
		var url = "<%=contextPath%>/user/userMng.do?returnMethod=getUserId&treeType=0&nType=1&searchType=byName&searchName="+txtSrch+"&viewType=2&isSearch=Y";
		luxor.popup(url,{width:860,height:570});
		return false;
	});

	$('#btn_reset').click(function() {
		if(luxor.isNullOrEmpty(selectedUserId)) {
			alert("<spring:message code="portal.alert.msg.58" text="초기화할 사용자를 먼저 선택하세요." />");
			$('#userName').focus();
		}
		if(confirm("<spring:message code="portal.alert.msg.59" text="선택된 사용자의 설정을 초기화 하시겠습니까?" />")) {
			var param = "userId="+selectedUserId;
			callJson("userSettingController", "deleteUserAllSetting", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.61" text="초기화 되었습니다." />");
		        }
			});
		}
		return false;
	});

	$('#btn_reset_all').click(function() {
		if(confirm("<spring:message code="portal.alert.msg.60" text="모든 사용자의 설정을 초기화 하시겠습니까?" />")) {
			callJson("userSettingController", "deleteAllSetting", "", function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.61" text="초기화 되었습니다." />");
		        }
			});
		}
		return false;
	});
});
</script>
</head>

<body>
	<div class="admin-content-width w1200 ml15" name="zone">
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.label.78" text="개인설정 초기화" /></span>
		</div>
		<div style="margin-top:10px;margin-left:10px;">
			- <spring:message code="portal.label.79" text="모든 사용자의 개인 설정" />
			<span class="main-btn"><a href="#" id="btn_reset_all"><spring:message code="portal.btn.label.21" text="초기화" /></a></span>
			<br /><br />
			- <spring:message code="portal.label.80" text="선택된 사용자 개인 설정" />
			<input type="text" id="userName" />
			<span class="main-btn"><a href="#" id="btn_search"><spring:message code="portal.btn.label.8" text="검색" /></a></span>
			<span class="main-btn"><a href="#" id="btn_reset"><spring:message code="portal.btn.label.21" text="초기화" /></a></span>
		</div>
	</div>
</body>
</html>
