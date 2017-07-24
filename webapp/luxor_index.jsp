<%@page import="com.sds.acube.luxor.security.UtilSSO"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>
<%@ page import="com.sds.acube.luxor.portal.service.GroupPortalService"%>
<%@ page import="com.sds.acube.luxor.portal.vo.GroupPortalVO"%>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>
<%@page import="com.sds.acube.luxor.common.vo.UserProfileVO"%>
<%@page import="com.sds.acube.luxor.common.ConstantList"%>
<%@page import="com.sds.acube.luxor.common.util.UtilRequest"%>
<%
	// 20130329 icaha, the line below was originally in the second if block
	String tenantId = LuxorConfig.getString("BASIC.TENANT_ID");

	// 20130329 icaha, ClinicalS, they want to find portal home by portal ID
	String thePortalId = UtilRequest.getString(request, "portalId");
	if (!CommonUtil.isNullOrEmpty(thePortalId)) {
		// when parameter portalId exists -> insert this into session -> it will be bind to DMO and query condition of home
		session.setAttribute("TENANT_ID", tenantId);
		session.setAttribute("PORTAL_ID", thePortalId);
	} else {
		if(CommonUtil.isNullOrEmpty((String)session.getAttribute("PORTAL_ID"))) {
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
			GroupPortalService service = (GroupPortalService)ctx.getBean("groupPortalService");
			
			GroupPortalVO param = new GroupPortalVO();
			param.setTenantId(tenantId);
			param = service.getDefault(param);	// get default portal

			// 포탈이 생성이 안된 경우 
			if(param==null) {
				CommonUtil.script(response, "document.location.href='/ep/luxor/admin/login.jsp';");
				return;
			}
			
			session.setAttribute("TENANT_ID", tenantId);
			session.setAttribute("PORTAL_ID", param.getPortalId());
		}
		
	}

	String initHome = CommonUtil.nullTrim((String)session.getAttribute(ConstantList.US_CODE_INITIAL_PAGE));
	String isUsingLoginPage = LuxorConfig.getEnvString(request, "MAIN_LOGIN_PAGE");
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	if(userProfile != null) {
		isUsingLoginPage = "N";
	}
	
	String loginRedirectScript = LuxorConfig.getEnvString(request, "LONGIN_REDIRECT");
	
	/***로그인 세션처리 ***/
	String encodeAlgorithm ="";
	String D1 ="";
	encodeAlgorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
	D1 = UtilSSO.encodeSession(request, encodeAlgorithm); 
%>

<!DOCTYPE HTML>



<html lang="ko">
<head>
<title>ACUBE Portal</title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	var loginRedirectScript = "<%=loginRedirectScript%>";
	var initHome = "<%=initHome%>";
	$(document).ready(function() {
		if("<%=isUsingLoginPage%>" == "Y") {
			document.location.href = "/ep/login/goCommonLogin.do";
		} else {
			$.ajax({
				type:'post',
				timeout: 5000,
				dataType:'json',
				url:'/luxor_collaboration/wrk/wrkWork.do?method=init&D1=<%=D1%>', 
				success:function(data) {
					goPage();
				},
				error:function(data,sataus,err) {						
					alert("init_luxor_collabo fail");
				}
			});  	
		}
	});
	
	function goPage(){
		callJson("portalPageController", "getHome", "", function(data) {

			if(initHome != "") {
				document.location.href = "/ep/page/main.do?pageId="+initHome;
	        	return false;
			}
			if(data==null && loginRedirectScript == "") {
	        	alert("<spring:message code="portal.alert.msg.39" text="홈으로 등록된 페이지가 없습니다. 관리자에게 문의하세요." />");
	        	return false;
	        } else if(data==null && loginRedirectScript != "") {
	        	eval(loginRedirectScript);
	        	return false;
	        }  
			

	        var homePageId = "";
			//로그인한 유저인 경우, 로그인 후 홈(isHome='L')을 먼저 찾고, 없으면 일반 홈(isHome='Y')으로 이동한다.
			<% if(userProfile !=null) { %>
				for(var i=0 ; i < data.length ; i++) {
					if(data[i]!=null) {
						if(data[i].isHome == 'Y') {
							homePageId = data[i].pageId;
						} else if(data[i].isHome == 'L') {
							document.location.href = "/ep/page/main.do?pageId="+data[i].pageId;
							return false;
						}
					}
				}
				if(homePageId != "") {
					document.location.href = "/ep/page/main.do?pageId="+homePageId; 
				} else {
					alert("<spring:message code="portal.alert.msg.39" text="홈으로 등록된 페이지가 없습니다. 관리자에게 문의하세요." />");
		        	return false;
				}
			<% } else { %>
				for(var i=0 ; i < data.length ; i++) {
					if(data[i]!=null) {
						if(data[i].isHome == 'Y') {
							document.location.href = "/ep/page/main.do?pageId="+data[i].pageId;
							return false;
						}
					}
				}
				alert("<spring:message code="portal.alert.msg.39" text="홈으로 등록된 페이지가 없습니다. 관리자에게 문의하세요." />");
	        	return false;
			<% } %>
		});
	}
</script>
</head>
<body></body>
</html>