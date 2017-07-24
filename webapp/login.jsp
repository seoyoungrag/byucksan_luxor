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

    //boolean useHttps = false;
    //boolean useAES = true;

%>


<!DOCTYPE HTML>
<html lang="ko">

<head>
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

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport"  content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<meta http-equiv="expires" content="-1"/>
	<meta http-equiv="Cache-Control" content="No-Cache"/>
	<meta http-equiv="Pragma" content="No-Cache"/>
	<title>Smart BICs 로그인</title>
	<link rel="shortcut icon" href="favicon.ico">
	<link rel="apple-touch-icon-precomposed" href="icon57.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="icon114.png">
	

	<link rel="stylesheet" type="text/css" href="/luxor_collaboration/collaboration/ref/mobile/css/common.css">
	<link rel="stylesheet" type="text/css" href="/luxor_collaboration/collaboration/ref/mobile/css/button.css">
	<link rel="stylesheet" type="text/css" href="/luxor_collaboration/collaboration/ref/mobile/css/login.css">


	
<script type="text/javascript">
var loginRedirectScript = "<%=loginRedirectScript%>";
var LoginPortlet = {

	validateLogin : function () {
		//validation
		if(luxor.isNullOrEmpty($('#id').val())) {
			alert("로그인 시에는 아이디가 반드시 필요합니다.");
		} else if(luxor.isNullOrEmpty($('#pw').val())) {
			alert("로그인 시에는 패스워드가 반드시 필요합니다.");
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
				//document.location.href = "main.jsp";
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
							document.location.href = "main.jsp";
						} else {
							document.location.href = "main.jsp";
						}
					} else {
						luxor.setCookie(plugId+$('#id').val(),'on',1);
						if(isExternal == 'Y') {
							document.location.href = plugUrl;
						} else {
							document.location.href = "main.jsp";
						}
					}
				} else if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript != '') {
					eval(loginRedirectScript);
				} else if(luxor.isNullOrEmpty(json.initialPage) && loginRedirectScript == '') { 
					document.location.href = "main.jsp";
				} else {
					
					document.location.href = "main.jsp";
				}
			} else if(json.loginResult == 1) {
				alert("아이디가 존재하지 않습니다.");
			} else if(json.loginResult == 2) {
				alert("패스워드가 일치하지 않습니다.");
			} else if(json.loginResult == 3) {
				alert("데이타베이스 연결에 문제가 발생했습니다.");
			} else if(json.loginResult == 4) {
				alert("데이타베이스 검색에 문제가 발생했습니다.");
			} else if(json.loginResult == 5) {
				alert("계정이 잠금설정된 상태입니다. 관리자에게 문의바랍니다.");
			} else if(json.loginResult == 6) {
				//비밀번호 변경으로 이동
				/*luxor.popup('/ep/user/getChangePasswordView.do?isExpired=Y', {
					width:500,
					height:300,
					scrollbars:'1',
					resizable:'1'
				});*/
			} else if(json.loginResult == 7) {
				alert("연속으로 잘못된 비밀번호를 입력하여 계정이 잠금설정되었습니다. 관리자에게 문의바랍니다.");
			} else if(json.loginResult == 8) {
				alert("관리자 권한이 없는 계정입니다.");
			} else if(json.loginResult == 100) {
				alert("로그인 Object가 'Null' 상태 입니다.");
			} else if(json.loginResult == 101) {
				/*luxor.popup('/ep/user/getChangePasswordView.do?isNewAccount=Y', {
					width:500,
					height:300,
					scrollbars:'1',
					resizable:'1'
				});*/
			} else {
				alert("로그인중 오류가 발생했습니다.");
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


</script>	
</head>

<body>

	<div class="login">
		<div class="top_logo"><img src="/luxor_collaboration/collaboration/ref/mobile/img/main/login_toplogo.png"></div>
  	<form name="adminLoginform" id="adminLoginform">	
		<input type="hidden" name="PARTNER" value="" />
		<input type="hidden" id="roundkey" name="roundkey" />
		<input type="hidden" id="loginId" name="loginId" />
		<input type="hidden" id="password" name="password" />      
		<input type="hidden" id="nextURI" name="nextURI" value="/bics_index.jsp"/>
		<!-- input type="hidden" id="D1" name="D1" value="b20e6e621ca92f8f4146d52b453cc66f3acec9affa7610a4997c75d5a75e17a1d3bc70b954cda3810760974a32d80da01d6605a80d4a5dff0ca54fce9f08c790b15f74d27d10a1be671ae1adb3aaa9e4839ee71c0505314b1e7ac19db3b533ec2181c48e14c6c82a536317c08b083a8e7d4a913d23904cad188e028e9e145224c0576981185b9ce7733277830a6fa5503b54e2264f04e759f1d836d1f10bad3be490b09f1dce7b758d2dc9f76632270ec3d161d50692e3461e7ac19db3b533ec8cdcbb61188a64b069182272e8ffe6e21237e2f14bb47996ba1c9edc2762cccd5cb2601dc5bffa89893ea7a0eddbf382b759f28f54db07d8671bf9391f77bd42ff35b8385a5a7ad270249cf515c5c39f41304fb40bb0513f41b76c4c82ddcaa1893ea7a0eddbf382f9eebf37180723734d4969250e4213db1444c7c314823a89d931e6c0e2b89147b617cf33842ec27cc86abf83f8e4a36718ee57aefdf34e1f02967bf0675b08c11ce1b03e1687cae07f655f57375de8f0df6423d72438d44e83c3ece1d78021b9b822195129264f13f9916f8b18b073ebc3f3fa5ebb692824b2dfc6f7012ef34b3b094e73d5050864a54a931a6ae2342548fadf2e3694d104b759f28f54db07d829c5f35e41a98b3b1d6c9459233922def0e47a4c88d70d33b0d56916ffdf64887615e2f67ba7d4aff1e966690a719cb23b44192799eee067"/>
		-->
  		
		<div class="login_input group">
			<div class="input_box">
				<p><input type="text" id="id" placeholder="ID를 입력하세요."><p>
				<p><input type="password" id="pw" placeholder="비밀번호를 입력하세요."></p>
			</div>
			<button type="button" class="button" id="btnLOGIN">로그인</button>
		</div>
		<p class="check_box">
			<label for="chk1" class="checkbox"><input type="checkbox" name="saveid" id="saveid">아이디저장하기</label>
			<label for="chk2" class="checkbox"><input type="checkbox" id="chk2">자동 로그인</label>
		</p>
		<p class="info_box">시스템 사용문의&nbsp;&nbsp;Tel.1899-1710</p>
	</form>	
	</div>


    
</body>
</html>