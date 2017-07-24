<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>
<%@ page import="com.sds.acube.luxor.portal.service.AdminService" %>
<%@ page import="com.sds.acube.luxor.portal.vo.AdminUserVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>

<%
	int adminCount = 1;
	
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	AdminService service = (AdminService)ctx.getBean("adminService");
	AdminUserVO param = new AdminUserVO();
	param.setTenantId((String)session.getAttribute("TENANT_ID"));

	adminCount = service.checkExistAdminUser(param);
	
    String[] localeLanguage = LuxorConfig.getString("LOCALE.LANG").split(",");
    String[] displayName = LuxorConfig.getString("LOCALE.DISPLAY-NAME").split(",");
    
    boolean useHttps = "Y".equals(LuxorConfig.getString("BASIC.USE_HTTPS"));
    boolean useAES = "Y".equals(LuxorConfig.getString("BASIC.USE_AES"));
    

	if(adminCount == 0) {
		String installed = LuxorConfig.getString("BASIC.INSTALLED");
		if("Y".equals(installed)) {
			CommonUtil.scriptAlert(response, "You need to change INSTALLED option to N from Common.xml", "");
			return;
		}		
		CommonUtil.script(response, "document.location.href = '/ep/admin/getAdminUserList.do?option=init';");
		return;
	}
	
	String loginRedirectScript = LuxorConfig.getEnvString(request, "LONGIN_REDIRECT");

%>


<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>ACUBE Portal Admin Login</title>
<jsp:include page="/luxor/common/header.jsp" />

<%	if(!useHttps) { %>
<%		if(useAES){%>
<script type="text/javascript" src="/ep/luxor/ref/js/util.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/sec/aes.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/sec/pbkdf2.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/sec/AesUtil.js" charset="utf-8"></script>
<%		}else{ %>
<script type="text/javascript" src="/ep/luxor/ref/js/seed.js"></script>
<%		} %>
<%	} %>
<script type="text/javascript">

var loginRedirectScript = "<%=loginRedirectScript%>";
var LoginPortlet = {

	validateLogin : function () {
		//validation
		if(luxor.isNullOrEmpty($('#id').val())) {
			alert("<spring:message code="portal.alert.msg.171" text="로그인 시에는 아이디가 반드시 필요합니다."/>");
		} else if(luxor.isNullOrEmpty($('#pw').val())) {
			alert("<spring:message code="portal.alert.msg.172" text="로그인 시에는 패스워드가 반드시 필요합니다."/>");
		} else {
			<%	if (useAES) {%>$.getLogin();<%}%>		
			LoginPortlet.loginProcess();
		}
	},
		
	loginProcess : function() {

<%	if(useHttps==false) { %>
<%		if(useAES==false){%>
		// Seed Encrypt
		$('#loginId').val(document.getElementById("seedOCX").SeedEncryptData($('#id').val()));
		$('#password').val(document.getElementById("seedOCX").SeedEncryptData($('#pw').val()));
<%        }%>
<%	} else { %>
		$('#loginId').val($('#id').val());
		$('#password').val($('#pw').val());
<%	} %>

		var param = $('#adminLoginform').serialize();
		callJson("loginController", "loginProcessByPortlet", param, function(json){
			<%	if (useAES) { // 로그인 결과와 비교위해 암호화된 로그인 결과 정보 취득 %>$.getLoginResult(json);<%}%>
			if(json.loginResult == 0) {
				//아이디 저장
				if ($('#saveid').attr("checked")==true) {
					luxor.setCookie("luxor_common_login_saveid", $('#id').val());
				} else {
					luxor.setCookie("luxor_common_login_saveid", null);
				}
				
				// 로그인 팝업
				var loginPopupLength = luxor.isNullOrEmpty(json.loginPopup) ? 0 : json.loginPopup.length;
				for(var p=0; p < loginPopupLength; p++) {
					var popupId = json.loginPopup[p].popupId;
					var popupType = json.loginPopup[p].popupType;
					var popupTitle = json.loginPopup[p].popupTitle;
					var popupUrl = json.loginPopup[p].popupUrl;
					
					// 팝업이 하루에 한번이 띄우는 형식이고 이미 한번 띄웠다면 건너뜀
					if(popupType!='E' && luxor.trim(luxor.getCookie(popupId))=='on') {
						continue;
					}
					
					var url = popupType=='E' 
							? json.loginPopup[p].popupUrl 
							: '/ep/luxor/portal/loginpopup/popupWindow.jsp?popupId='+popupId
							  +'&popupUrl='+encodeURIComponent(popupUrl)+'&popupTitle='+encodeURIComponent(popupTitle);
			  		var opt = "toolbar=no,scrollbars=no,resizable=no,status=no,location=no,menubar=no,"
			  			+"width="+json.loginPopup[p].windowWidth+"px,height="+json.loginPopup[p].windowHeight+"px,"
			  			+"left="+json.loginPopup[p].positionLeft+"px,top="+json.loginPopup[p].positionTop+"px";
			  		window.open(url,"",opt);
				}
				
				// 초기화면(플러그 적용)
				var loginPlugLength = luxor.isNullOrEmpty(json.loginPlug) ? 0 : json.loginPlug.length;
				if(loginPlugLength > 0) {
					var plugId = json.loginPlug[0].plugId;
					var plugType = json.loginPlug[0].plugType;
					var plugTitle = json.loginPlug[0].plugTitle;
					var plugUrl = json.loginPlug[0].plugUrl;
					var isExternal = json.loginPlug[0].isExternal;
					if(plugType!='E' && luxor.trim(luxor.getCookie(plugId+$('#id').val()))=='on') {
						if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript != '') {
							eval(loginRedirectScript);
						} else if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript == '') { 
							document.location.href = "/ep"
						} else {
							document.location.href = "/ep/page/index.do?pageId="+json.initialPage;
						}
					} else {
						luxor.setCookie(plugId+$('#id').val(),'on',1);
						if(isExternal == 'Y') {
							document.location.href = plugUrl;
						} else {
							document.location.href = "/ep/"+plugUrl;
						}
					}
				} else if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript != '') {
					eval(loginRedirectScript);
				} else if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript == '') { 
					document.location.href = "/ep"
				} else {
					document.location.href = "/ep/page/index.do?pageId="+json.initialPage;
				}
			} else if(json.loginResult == 1) {
				//회원가입 신청중인 아이디인지 체크하여, 있다면 관련 메세지 호출
				<% if(LuxorConfig.getEnvString(request, "USE_MEMBER_REQUEST").equals("Y")) { %>
					callJson("memberRequestController", "getMemberRequest", "loginId="+$('#id').val()+"&loginPassword="+$('#pw').val(), function(data) {	
						if(data == null) { 
							alert('<spring:message code="portal.alert.msg.173" text="아이디가 존재하지 않습니다." />');
						} else if(data.requestStatus == -1){
							alert('<spring:message code="portal.alert.msg.174" text="패스워드가 일치하지 않습니다." />');
						} else if(data.requestStatus == 0){
							alert('<spring:message code="portal.memberRequest.msg.34" text="등록 신청중인 계정입니다." />');
						} else if(data.requestStatus == 2){
							alert('<spring:message code="portal.memberRequest.msg.35" text="신청 반려된 계정입니다." />');
						} else {
							alert('<spring:message code="portal.alert.msg.173" text="아이디가 존재하지 않습니다." />');
						}
					});
				<% } else { %>
					alert("<spring:message code="portal.alert.msg.173" text="아이디가 존재하지 않습니다."/>");
				<% } %>
			} else if(json.loginResult == 2) {
				alert("<spring:message code="portal.alert.msg.174" text="패스워드가 일치하지 않습니다."/>");
			} else if(json.loginResult == 3) {
				alert("<spring:message code="portal.alert.msg.175" text="데이타베이스 연결에 문제가 발생했습니다."/>");
			} else if(json.loginResult == 4) {
				alert("<spring:message code="portal.alert.msg.176" text="데이타베이스 검색에 문제가 발생했습니다."/>");
			} else if(json.loginResult == 5) {
				alert("<spring:message code="portal.login.alert.1" text="계정이 잠금설정된 상태입니다. 관리자에게 문의바랍니다."/>");
			} else if(json.loginResult == 6) {
				//비밀번호 변경으로 이동
				luxor.popup('/ep/user/getChangePasswordView.do?isExpired=Y', {
					width:500,
					height:300,
					scrollbars:'1',
					resizable:'1'
				});
			} else if(json.loginResult == 7) {
				alert("<spring:message code="portal.login.alert.2" text="연속으로 잘못된 비밀번호를 입력하여 계정이 잠금설정되었습니다. 관리자에게 문의바랍니다."/>");
			} else if(json.loginResult == 8) {
				alert("<spring:message code="portal.alert.msg.177" text="관리자 권한이 없는 계정입니다."/>");
			} else if(json.loginResult == 100) {
				alert("<spring:message code="portal.alert.msg.178" text="로그인 Object가 'Null' 상태 입니다."/>");
			} else if(json.loginResult == 101) {
				luxor.popup('/ep/user/getChangePasswordView.do?isNewAccount=Y', {
					width:500,
					height:300,
					scrollbars:'1',
					resizable:'1'
				});
			} else {
				alert("<spring:message code="portal.alert.msg.179" text="로그인중 오류가 발생했습니다."/>");
			}
		});
	},
	logoutProcess : function() {
		callJson("logoutSysController", "getList", "", function(data) {
			for(var i=0; i < data.length; i++) {
				$('body').append("<iframe id='logoutHelpIframe"+i+"' class='dialog'></iframe>");
				$('#logoutHelpIframe'+i).attr('src',data[i].logoutUrl);
			}
			
			callJson("loginController", "logoutProcessByPortlet", "", function(data) {
				setTimeout(function() {
					top.location.reload();
				}, 300);
			});
		});
	},
	prepareSeed: function() {
	    var currRoundKey = document.getElementById("seedOCX").GetCurrentRoundKey();
	    if (currRoundKey == "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0") {
	        var userkey = document.getElementById("seedOCX").GetUserKey();
	        currRoundKey = document.getElementById("seedOCX").GetRoundKey(userkey);
	    }
	    var roundkey_c = document.getElementById("seedOCX").SeedEncryptRoundKey(currRoundKey);
	    $('#roundkey').val(roundkey_c);
	}
};

$(document).ready(function() {
	$('body').height($(window).height());
	$('#id').focus();

<%	if(useHttps==false && useAES==false) { %>	
	LoginPortlet.prepareSeed();
<%	} %>

	//저장아이디 불러오기
	if(!luxor.isNullOrEmpty(luxor.getCookie("luxor_common_login_saveid"))) {
		$('#id').val(luxor.getCookie("luxor_common_login_saveid"));
		$('#saveid').attr("checked","checked");
		$('#id').focus();
		$('#pw').focus();
	}
	
	//다국어가 2개 미만일 경우 
	if(<%=localeLanguage.length%><2){
		 $("#language_wrap").hide();
	}
	
	// 일반 포틀릿 인 경우
	$('#btnLOGIN').click(function(e) {
		LoginPortlet.validateLogin();
	});
	
	// 엔터키입력시
	$('input').keypress(function(e) {
		if(e.keyCode==13) {
			$('#btnLOGIN').click();
			return false;
		}
	});

	// 회원가입
	$('#btn_member_request').click(function() {
		luxor.popup('/ep/memberRequest/getTermsView.do', {
			width:800,
			height:650,
			scrollbars:'1',
			resizable:'1'
		});
	});
});

function confirmChangedPageword(password) {
	<% if(LuxorConfig.getEnvString(request, "USE_MEMBER_REQUEST").equals("Y")) { %>
		postJson("memberRequestController", "confirmMemberRequest", "loginId="+$('#id').val(), function(data) {		
			$('#pw').val(password);
			$('#password').val(password);
			LoginPortlet.validateLogin();
		});
	<% } else { %>
		$('#pw').val(password);
		$('#password').val(password);
		LoginPortlet.validateLogin();
	<% } %>
}

</script>
</head>

<body>

<div class="common-login-box w440">
	<img src="/ep/luxor/ref/image/admin/admin_logo.gif" alt="Logo" title="Logo" />
	<form name="adminLoginform" id="adminLoginform">
	<input type="hidden" name="PARTNER" value="" />
	<input type="hidden" id="roundkey" name="roundkey" />
	<input type="hidden" id="loginId" name="loginId" />
	<input type="hidden" id="password" name="password" />
	<div class="login-common">
  		<p class="common-login-title"></p>
   		<div class="common-inner-login-box">
     		<div class="common-login-main">
         		<p>
           			<input type="text" id="id" class="common-input-login w150" tabindex="1" onfocus="this.style.background='white';" />
           			<input type="checkbox" name="saveid" id="saveid" tabindex="3" /><span class="common-id-save"><spring:message code="portal.label.139" text="아이디저장" /></span>
           		</p>
         		<p>
           			<input type="password" id="pw" class="common-input-pw-login w150" tabindex="2" onfocus="this.style.background='white';" />
           			<input type="button" id="btnLOGIN" class="common-login-btn" tabindex="4" value="<spring:message code="portal.label.111" text="로그인" />" style="cursor:pointer;" />
         		</p>
         		<p id="language_wrap">
         			Language <select name="language" class="w100" id="language" tabindex="12" onkeydown="" >
					<%	for(int i=0; i < localeLanguage.length; i++) {	%>
						<option value='<%=localeLanguage[i]%>'><%=displayName[i]%></option>
					<%	} %> 
         			</select>
         		</p>
         		<% if(LuxorConfig.getEnvString(request, "USE_MEMBER_REQUEST").equals("Y")) { %>
         		<p class="logintitle loginsub" style="border-top-width:0px;"><a href="#" id="btn_member_request" style="float:right;"><spring:message code="portal.memberRequest.msg.36" text="계정신청"/></a></p>
     			<% } %>
     		</div>
   		</div>
		<p class="common-login-text text-center mt5">
			Copyright 2012 Samsung. All rights reserved.
		</p>
  	</div>
  	</form>
</div>


</body>
</html>
