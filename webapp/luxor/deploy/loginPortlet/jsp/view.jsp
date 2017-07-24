<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>

<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>

<%
	String contextPath = request.getContextPath();
	String[] PORTLET_STATUS = {"Stand by", "deployed", "undeployed by user", "undeployed by fault"};
	
	String _portletContext = (String)request.getAttribute("portletContext");
	String _currentPath = (String)request.getAttribute("currentPath");
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	
	String[] localeLanguage = LuxorConfig.getString("LOCALE.LANG").split(",");
	String[] displayName = LuxorConfig.getString("LOCALE.DISPLAY-NAME").split(",");
	
	boolean useHttps = "Y".equals(LuxorConfig.getString("BASIC.USE_HTTPS"));
    boolean useAES = "Y".equals(LuxorConfig.getString("BASIC.USE_AES"));
	
	String loginRedirectScript = LuxorConfig.getEnvString(request, "LONGIN_REDIRECT");	
	String isUsingLoginPage = LuxorConfig.getEnvString(request, "MAIN_LOGIN_PAGE");
	
%>

<script type="text/javascript">
	var useHttps = <%=useHttps%>;
	var useAES = <%=useAES%>;
	
	var localeLanguage_length = <%=localeLanguage.length%>;
	var loginRedirectScript = "<%=loginRedirectScript%>";
	var isUsingLoginPage = "<%=isUsingLoginPage%>";
	var isUsingMemeberRequest = "<%=LuxorConfig.getEnvString(request, "USE_MEMBER_REQUEST")%>";
	var login_label_170 = "<spring:message code="portal.alert.msg.170" text="이미 다른 PC에서 로그인중입니다."/>";
	var login_label_171 = "<spring:message code="portal.alert.msg.171" text="로그인 시에는 아이디가 반드시 필요합니다."/>";
	var login_label_172 = "<spring:message code="portal.alert.msg.172" text="로그인 시에는 패스워드가 반드시 필요합니다."/>";
	var login_label_173 = "<spring:message code="portal.alert.msg.173" text="아이디가 존재하지 않습니다."/>";
	var login_label_174 = "<spring:message code="portal.alert.msg.174" text="패스워드가 일치하지 않습니다."/>";
	var login_label_175 = "<spring:message code="portal.alert.msg.175" text="데이타베이스 연결에 문제가 발생했습니다."/>";
	var login_label_176 = "<spring:message code="portal.alert.msg.176" text="데이타베이스 검색에 문제가 발생했습니다."/>";
	var login_label_177 = "<spring:message code="portal.alert.msg.177" text="관리자 권한이 없는 계정입니다."/>";
	var login_label_178 = "<spring:message code="portal.alert.msg.178" text="계정 조회 권한 또는 라이센스가 만료되었습니다."/>";
	var login_label_179 = "<spring:message code="portal.alert.msg.179" text="로그인중 오류가 발생했습니다."/>";
	var login_label_180 = "<spring:message code="portal.alert.msg.180" text="로그아웃 하시겠습니까?"/>";
	var login_label_181 = "<spring:message code="portal.login.alert.1" text="계정이 잠금설정된 상태입니다. 관리자에게 문의바랍니다."/>";
	var login_label_182 = "<spring:message code="portal.login.alert.2" text="연속으로 잘못된 비밀번호를 입력하여 계정이 잠금설정되었습니다. 관리자에게 문의바랍니다."/>";
	var portal_member_request_msg_34 = "<spring:message code="portal.memberRequest.msg.34" text="등록 신청중인 계정입니다."/>";
	var portal_member_request_msg_35 = "<spring:message code="portal.memberRequest.msg.35" text="신청 반려된 계정입니다."/>";
</script>
<%
	if(userProfile == null || (userProfile.getLoginResult() != 0)) {
%>
<!-- Login box-->
<form id="adminLoginform" name="adminLoginform">
<input type="hidden" id="roundkey" name="roundkey" />
<input type="hidden" id="loginId" name="loginId" />
<input type="hidden" id="password" name="password" />
<div class="login-main">
   	<p>
     	<input id="id" tabindex="1" class="input-login w120" type="text" onfocus="this.style.background='white'" 
     		title="<spring:message code="portal.label.213" text="아이디" />" />
     	<input id="saveid" name="saveid" tabindex="3" type="checkbox"
     		title="<spring:message code="portal.label.139" text="아이디저장" />" />
     	<span class="id-save"><spring:message code="portal.label.139" text="아이디저장" /></span>
	</p>
   	<p>
     	<input id="pw" type="password" tabindex="2" class="input-pw-login w120" type="text" value="" onfocus="this.style.background='white'" 
     		title="<spring:message code="portal.label.212" text="비밀번호" />" />
     	<input name="submit_img" id="submit_img" class="login-btn" type="button" tabindex="4" 
     		value="<spring:message code="portal.label.111" text="로그인" />"
     		title="<spring:message code="portal.label.111" text="로그인" />" />
   	</p>
</div>
<div class="login-account loginsub">
   	<dl>
     	<dt class="text-right mt5" id="language_wrap">
     		<% if(LuxorConfig.getEnvString(request, "USE_MEMBER_REQUEST").equals("Y")) { %>
    			<a id="btn_member_request" href="javascript:;"><spring:message code="portal.memberRequest.msg.36" text="계정신청"/></a> 
			<% } %>
     		<a href="javascript:;" style="cursor:default;"><spring:message code="portal.label.140" text="다국어선택" /></a>
       		<select name="language" id="language" tabindex="4" onkeydown=""
       			title="<spring:message code="portal.label.140" text="다국어선택" />">
      		<%	for(int i=0; i < localeLanguage.length; i++) {	%>
				<option value='<%=localeLanguage[i]%>'><%=displayName[i]%></option>
			<%	} %> 
       		</select>
     	</dt>
   </dl>
</div>
</form>
<!-- //Login box-->
<%
	} else {
%>
<div class="login-main">
  <p class="login-person"><%=userProfile.getUserName()%><spring:message code="portal.label.145" text="님 안녕하세요!"/></p>
</div>
<div class="login-account">
  	<dl>
    	<dt class="logintitle loginsub">
    		<a id="btn_change_personalinfo" href="#"><spring:message code="portal.label.143" text="개인정보 수정" /></a> 
    		<a id="btn_change_password" href="#"><spring:message code="portal.label.144" text="비밀번호 변경" /><br /></a>
    		<a id="btn_set_home" href="#"><spring:message code="portal.label.146" text="초기화면 설정" /></a>
    		<a id="btn_set_timezone" href="#"><spring:message code="portal.label.147" text="시간대 설정" /><br /></a>
    		<a id="btn_set_theme" href="#"><spring:message code="portal.label.148" text="테마 설정" /></a>
    	</dt>
  	</dl>
    <input name="btnLOGOUT" id="btnLOGOUT" class="logout-btn mt5" type="button" tabindex="3" 
    	value="<spring:message code="portal.label.211" text="로그아웃" />" 
    	title="<spring:message code="portal.label.211" text="로그아웃" />" />
 	<div class="clear"> </div>
</div>
<%
	}
%>
