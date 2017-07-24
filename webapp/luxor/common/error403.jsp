<%@page import="com.sds.acube.luxor.common.vo.UserProfileVO"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>
<%@page import="com.sds.acube.luxor.common.util.CommonUtil"%>
<%@page import="com.sds.acube.luxor.portal.vo.AdminUserVO"%>
<%@page import="java.util.List"%>

<%
AdminUserVO[] admins = LuxorConfig.getAdminList();
boolean isAdmin = false;
String userUid = "";
String portalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
List<String> accessIdList = (List<String>)session.getAttribute("ACCESS_ID_LIST");
if(admins != null) {
	for(int j = 0 ; j < admins.length ; j++) {
		if(admins[j].getAdminType() == 0) {
			if(accessIdList.contains(admins[j].getUserUid())) {
				portalId = "_ROOT_";
				userUid = admins[j].getUserUid();
				isAdmin = true;
			}
		} else if(admins[j].getAdminType() == 1) { 
			if(accessIdList.contains(admins[j].getUserUid()) && admins[j].getPortalId().equals(CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID")))) {
				portalId = admins[j].getPortalId();
				userUid = admins[j].getUserUid();
				isAdmin = true;
			}
		} 
	}
}

UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
%>
<!DOCTYPE HTML>




<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		resizeWidth();
	});
	
	function resizeWidth() {
		$('#author_fail > div').width(230);
		if($(this).height()<200) {
			$('#author_fail').height(50);
		} else {
			$('#author_fail').height($(this).height());
		}
	}

	function setAclIgnore() {
		postJson('adminController','setAclIgnoreType','isIgnoreAcl=Y&portalId=<%=portalId%>&userUid=<%=userUid%>',function(data) {
			self.location.reload();
		});	
	}
	
	function noAuthority(){
		if(<%=userProfile%>==null){
			alert('로그인페이지로 이동합니다.');			
			location.href='http://smart.bsco.co.kr';
		}
	}
</script>
<title><spring:message code="portal.authorization.msg.1" text="권한이 없습니다." /></title>
<link href="/ep/luxor/ref/css/default_theme.css" rel="stylesheet" type="text/css" />
</head>
<body id="author_fail" class="author-fail-body" onload="noAuthority();">
	<div class="author-message-center">
		<ul class="please-login">
			<li class="please-login-blue">
				<p class="please-login-gray"><spring:message code="portal.authorization.msg.1" text="권한이 없습니다." /></p>
				<% if(session.getAttribute("adminProfile") == null) { %>
				<spring:message code="portal.page.label.15" text="관리자에게 문의바랍니다." />
				<%=userProfile%>
				<% } else if(session.getAttribute("adminProfile") != null && isAdmin == true) { %>
					<span class="smain-btn"><a href="javascript:setAclIgnore();">권한 체크 해제</a></span>
				<% } %>
			</li>
		</ul>
	</div>
</body>
</html>
