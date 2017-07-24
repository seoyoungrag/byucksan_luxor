<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.sds.acube.luxor.security.UtilSSO"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*, java.net.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>
<%@ page import="org.springframework.web.util.WebUtils" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.sds.acube.luxor.portal.service.AdminService" %>
<%@ page import="com.sds.acube.luxor.portal.vo.AdminUserVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>

<%
String userinfo = "";
String loginId ="";
Date tbbdate = new Date();
SimpleDateFormat tbbspreparation = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat schedule = new SimpleDateFormat("yyyyMMdd");
String initSDate = schedule.format(tbbdate).toString() + "0000";
request.setAttribute("userinfo","Y");		//request.getAttribute("userinfo")에서 값을 받아야 하는데 null이기 때문에 임시로 Y 값을 할당 하였다.
userinfo = (String) request.getAttribute("userinfo");
String userId = "";
String userName = "";
String challenge = "1";

String contextPath = request.getContextPath();
/* 	CommonUtil.sessionInit(session); // 세션 초기화
session.setAttribute("TENANT_ID", LuxorConfig.getString("BASIC.TENANT_ID"));
session.setAttribute("LANG_TYPE", new Locale("ko", "KR"));
session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale("ko","KR"));
*/	
UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");

boolean isLogin = false;
	
if(!(userProfile == null || (userProfile.getLoginResult() != 0))) {
	userId = userProfile.getUserUid();
	userName = userProfile.getUserName();
}
if(userProfile != null) {
  	isLogin = true;
	loginId = userProfile.getLoginId();
}


int adminCount = 1;

WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
AdminService service = (AdminService)ctx.getBean("adminService");
AdminUserVO param = new AdminUserVO();
param.setTenantId((String)session.getAttribute("TENANT_ID"));
adminCount = service.checkExistAdminUser(param);

String[] localeLanguage = LuxorConfig.getString("LOCALE.LANG").split(",");
String[] displayName = LuxorConfig.getString("LOCALE.DISPLAY-NAME").split(",");

/* boolean useHttps = "Y".equals(LuxorConfig.getString("BASIC.USE_HTTPS"));
boolean useAES = "Y".equals(LuxorConfig.getString("BASIC.USE_AES")); */
boolean useHttps = true;
boolean useAES = true;

if(adminCount == 0) {
	String installed = LuxorConfig.getString("BASIC.INSTALLED");
	if("Y".equals(installed)) {
		CommonUtil.scriptAlert(response, "You need to change INSTALLED option to N from Common.xml", "");
		return;
	}
	
	CommonUtil.script(response, "document.location.href = '"+request.getContextPath()+"/admin/getAdminUserList.do?option=init';");
	return;
}

/***로그인 세션처리 ***/
String encodeAlgorithm ="";
String D1 ="";
encodeAlgorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
D1 = UtilSSO.encodeSession(request, encodeAlgorithm);  
%>
<!DOCTYPE HTML>
<html lang="ko">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport"  content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<meta http-equiv="expires" content="-1"/>
	<meta http-equiv="Cache-Control" content="No-Cache"/>
	<meta http-equiv="Pragma" content="No-Cache"/>
	<title>Smart BICs Main</title>
	<link rel="shortcut icon" href="favicon.ico">
	<link rel="apple-touch-icon-precomposed" href="icon57.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="icon114.png">
	
	<link rel="stylesheet" type="text/css" href="/luxor_collaboration/collaboration/ref/mobile/css/common.css">
	<link rel="stylesheet" type="text/css" href="/luxor_collaboration/collaboration/ref/mobile/css/button.css">
	<link rel="stylesheet" type="text/css" href="/luxor_collaboration/collaboration/ref/mobile/css/main.css">
	<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/jquery-1.4.3.min.js" charset="utf-8"></script>

<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/jquery.slides.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/luxor.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/content.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/luxor/ref/js/jquery-ui.js" charset="utf-8"></script>

<script>
var userUid = "<%=userProfile.getUserUid()%>";
var userName = "<%=userProfile.getUserName()%>";
var getTodayFullDate = new Date();
var getTYear = getTodayFullDate.getFullYear();
var getTMonth = (getTodayFullDate.getMonth())+1;
var getTDate = getTodayFullDate.getDate();
	getTDate = parseInt(getTDate) < 10 ? ('0'+getTDate) : getTDate;
var getTFullDate = getTYear + "/" + getTMonth + "/" + getTDate;
var schSelectedStatus = 0;
var bbsTabVal = 0;
var approvalTabVal = 0;
var selectDate = "";
var d1 = "<%=D1%>";

function setImage(Uid) {
	if(Uid && Uid != ''){
		$("#viewimage2").attr("src","/acube/iam/identity/userinfoimage/Select.go?requestImage=picture&userId="+Uid);
	}
}

$(document).ready(function(){
	// 탑 메뉴와 레프트 영역에 사용자 이미지를 세팅한다.
	setImage(userUid);
});

</script>
	
</head>



<body>
	<div class="main">
		<div class="main_top group">
			<p class="logo"><img src="/luxor_collaboration/collaboration/ref/mobile/img/main/main_toplogo.png"></p>
			<img src="/ep/luxor/ref/img/my_img.jpg" alt="이미지" class="circleImg" id="viewimage2"/>
			<div class="user">
				<p class="user_name font_bold"><%=userProfile.getUserName()%></p>
				<p class="user_info"><%=userProfile.getDeptName()%> | <%=userProfile.getGradeName()%></p>
			</div>
		</div>
		
		<div class="msg">CEO 메세지 상반기 경영실적에 따른 경영방침</div>
		
		<ul class="shortcut group">
			<li class="msc01"><a href="#" title="전자메일"><span>23</span></a></li>
			<li class="msc02"><a href="#" title="work-in"></a></li>
			<li class="msc03"><a href="#" title="메신저"><span>9</span></a></li>
			<li class="msc04"><a href="/luxor_collaboration/mobile/bbs/bbsMyBoardSet.do?method=latestpostlst&D1=<%=D1%>" title="게시판"></a></li>
			<li class="msc05"><a href="#" title="문서관리"></a></li>
			<li class="msc06"><a href="#" title="전자결제"><span>9</span></a></li>
			<li class="msc07"><a href="/luxor_collaboration/mobile/sch/schSchedule.do?method=schedules&D1=<%=D1%>" title="일정관리"></a></li>
			<li class="msc08"><a href="#" title="자원관리"></a></li>
			<li class="msc09"><a href="#" title="주소록"></a></li>
		</ul>
		
		<ul class="bottom_shortcut">
			<li class="bsc01"><a href="#" title="커뮤니티"></a></li>
			<li class="bsc02"><a href="#" title="블로그"></a></li>
			<li class="bsc03"><a href="#" title="설문"></a></li>
			<li class="bsc04"><a href="#" title="설정"></a></li>
		</ul>
	</div>

    

</body>
</html>