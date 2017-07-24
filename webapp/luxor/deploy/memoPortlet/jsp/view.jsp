<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>
<%
UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
if(userProfile == null || (userProfile.getLoginResult() != 0)) {
%>
	<spring:message code="portal.portlet.label.110" text="로그인이 필요합니다."/>
	<script type="text/javascript">
	var userUid = "";
	</script>
<%
} else {
%>
	<script type="text/javascript">
	var userUid = "<%=userProfile.getUserUid()%>";
	var msgError = "<spring:message code="portal.portlet.alert.3" text="시스템 문제로 등록이 되지 않았습니다."/>";
	var msgInitMemo = "<spring:message code="portal.portlet.label.111" text="메모를 입력하세요."/>";
	</script>
	<textarea id="memoText" class="memo-content default" style="border:0px;" rows="" cols=""></textarea>
<%
}
%>


