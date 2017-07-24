<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>
<%@page import="java.util.List"%>
<%@page import="com.sds.acube.luxor.ws.client.orgservice.PolicyInfo"%>
<%@page import="com.sds.acube.luxor.common.util.CommonUtil"%>

<%
	UserProfileVO userProfile = (UserProfileVO)request.getSession().getAttribute("userProfile");
    List<PolicyInfo> policyInfoList = (List<PolicyInfo>)request.getAttribute("policyInfoList");
    String loginId = "";
    if(userProfile != null) {
		loginId = userProfile.getUserUid();
    }

	boolean useHttps = "Y".equals(LuxorConfig.getString("BASIC.USE_HTTPS"));
	String isExpired = CommonUtil.nullTrim((String)request.getAttribute("isExpired"));
	String isNewAccount = CommonUtil.nullTrim((String)request.getAttribute("isNewAccount"));
%>

<!DOCTYPE HTML>

<html lang="ko">
<head>
<title><spring:message code="user.label.40" text="비밀번호 변경" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<%	if(useHttps==false) { %>
<script type="text/javascript" src="/ep/luxor/ref/js/seed.js"></script>
<%	} %>
<script type="text/javascript">
var isExpired = "<%=isExpired%>";
var isNewAccount = "<%=isNewAccount%>";
var loginId = "<%=loginId%>";
function preareSeed() {
    var currRoundKey = document.getElementById("seedOCX").GetCurrentRoundKey();
    if (currRoundKey == "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0") {
        var userkey = document.getElementById("seedOCX").GetUserKey();
        currRoundKey = document.getElementById("seedOCX").GetRoundKey(userkey);
    }
    var roundkey_c = document.getElementById("seedOCX").SeedEncryptRoundKey(currRoundKey);
    $('#roundkey').val(roundkey_c);
}

function validatePassword(oldPassword, newPassword) {
	// ID를 포함하는 비번은 안됨
	if(newPassword.indexOf("<%=loginId%>") > -1) {
		alert("<spring:message code="user.alert.msg.7" text="비밀번호에 로그인 ID를 포함할 수 없습니다." />");
		return false;
	}
	
	// 기존 비번과 동일한 문자열 체크 및 연속되는 문자열 체크는
	// 구포탈의 UM_Pswd.jsp 파일을 참고할 것
	
	return true;
}

$(document).ready(function() {
	if(loginId == "") {
		self.close();
	}
	
	if(isExpired == "Y" && loginId != "") {
		callJson("loginController", "logoutProcessByPortlet", "");
	}
	
	$('#btn_form_save').click(function() {
		if(luxor.isNullOrEmpty($('#password1').val())) {
			alert("<spring:message code="user.alert.msg.2" text="기존 비밀번호를 입력하세요." />");
			$('#password1').focus();
			return;
		}
		if(luxor.isNullOrEmpty($('#password2').val())) {
			alert("<spring:message code="user.alert.msg.3" text="새로운 비밀번호를 입력하세요." />");
			$('#password2').focus();
			return;
		}
		if(luxor.isNullOrEmpty($('#password3').val())) {
			alert("<spring:message code="user.alert.msg.3" text="새로운 비밀번호를 입력하세요." />");
			$('#password3').focus();
			return;
		}
		if($('#password1').val()==$('#password2').val()) {
			alert("<spring:message code="user.alert.msg.4" text="기존 비밀번호와 다르게 입력하세요." />");
			return;
		}
		if($('#password2').val()!=$('#password3').val()) {
			alert("<spring:message code="user.alert.msg.5" text="입력하신 새로운 비밀번호가 서로 일치하지 않습니다." />");
			return;
		}
		
		<%	if(useHttps==false) { %>
		preareSeed();
		// Seed Encrypt
		$('#oldPassword').val(document.getElementById("seedOCX").SeedEncryptData($('#password1').val()));
		$('#newPassword').val(document.getElementById("seedOCX").SeedEncryptData($('#password2').val()));
		<%	} else { %>
		$('#oldPassword').val($('#password1').val());
		$('#newPassword').val($('#password2').val());
		<%	} %>
		
		var param = $('#password_form').serialize();
		callJson('userController', 'changePassword', param, function(data) {
	        if(data._errorCode=='-9999') {
	        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
	        } else {
				if(data=='0') {
					alert("<spring:message code="user.alert.msg.6" text="비밀번호를 변경하였습니다." />");
					if(isExpired == "Y" || isNewAccount == "Y") {
						opener.confirmChangedPageword($('#password3').val());
						$('#btn_form_cancel').click();
					} else {
						$('#btn_form_cancel').click();
					}
				} else if(data=='1') {
					alert("<spring:message code="user.alert.msg.8" text="변경실패 : 이전 비밀번호가 일치하지 않습니다." />");
				} else if(data=='3') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 기존 비밀번호와 패턴이 유사합니다." />");
				} else if(data=='4') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 비밀번호에 금칙어가 포함되어 있습니다." />");
				} else if(data=='5') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 비밀번호에 사용자ID가 포함되어 있습니다." />");
				} else if(data=='6') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 비밀번호에 개인정보가 포함되어 있습니다." />");
				} else if(data=='7') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 비밀번호에 생일이 포함되어 있습니다." />");
				} else if(data=='8') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 연속된 3자리 이상의 문자열을 사용하셨습니다." />");
				} else if(data=='9') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 연속된 3자리 이상의 숫자를 사용하셨습니다." />");
				} else if(data=='10') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 같은 문자/숫자를 3자리 이상 반복적으로 사용하셨습니다." />");
				} else if(data=='11') {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 숫자/소문자/대문자를 최소 2개 이상 함께 사용해야 합니다." />");
				} else {
					alert("<spring:message code="sjs.messgae" text="변경실패 : 시스템 오류가 발생했습니다." />");
				}
	        }
			return;
		});
	});
	
	$('#btn_form_cancel').click(function() {
		self.close();
	});
});

</script>
</head>

<body>
	<div class="popup-wrap">
		<div class="title-pop">
			<span class="title-pop-text"><spring:message code="user.label.40" text="비밀번호 변경" /></span>
		</div>
		<%
		if(isNewAccount.equals("Y")) {
		%>
		<div class="font-blue h5"><b>- <spring:message code="portal.memberRequest.msg.33" text="신규 계정 등록 완료되었습니다. 새로운 비밀번호를 입력해 주십시오." /></b></div>
		<%
		}
		for(int i =0 ; i < policyInfoList.size() ; i++ ) {
			//System.out.println("==========================================");
			//System.out.println(policyInfoList.get(i).getPolicyId());
			//System.out.println(policyInfoList.get(i).getPolicyValue());
			//System.out.println(policyInfoList.get(i).getPolicyName());
			//System.out.println("==========================================");
			/* 암호 유효기간 */ 
			String value = policyInfoList.get(i).getPolicyValue();
			String reservedValue = policyInfoList.get(i).getReserved1();
			if(policyInfoList.get(i).getPolicyId().equals("PI3") && isExpired.equals("Y")) {
				if(value.equals("30") || value.equals("60") || value.equals("90") || value.equals("180")) {
					%>
					<div class="font-blue h5"><b>- <%=value%><spring:message code="portal.password.alert.1" text="일 암호 휴효기간이 만료되었습니다. 새로운 암호로 변경해주십시오." /></b></div>
					<% 
				} else {
					%>
					<div class="font-blue h5"><b>- <%=reservedValue%><spring:message code="portal.password.alert.1" text="일 암호 휴효기간이 만료되었습니다. 새로운 암호로 변경해주십시오." /></b></div>
					<% 
				}
			}
			
			/* 이전 비밀번호 재사용 금지 */ 
			if(policyInfoList.get(i).getPolicyId().equals("PI5")) {
				if(value.equals("1")) {
					%>
					<div class="font-blue h5">- <spring:message code="portal.password.alert.2" text="이전 비밀번호는 재사용 하실 수 없습니다." /></div>
					<% 
				} 
			}
			/* 암호 금지어 사용금지*/
			if(policyInfoList.get(i).getPolicyId().equals("PI6")) {
				if(value.equals("1")) {
					%>
					<div class="font-blue h5">- <spring:message code="portal.password.alert.3" text="불허 단어가 포함된 비밀번호는 사용할 수 없습니다.(ex; 회사명, 비속어)" /></div>
					<% 
				} 
			}
			/* 개인정보 사용금지*/ 
			if(policyInfoList.get(i).getPolicyId().equals("PI7")) {
				if(value.equals("1")) {
					%>
					<div class="font-blue h5">- <spring:message code="portal.password.alert.4" text="암호에 개인정보가 들어갈 수 없습니다. (ex 생일, 전화번호)" /></div>
					<% 
				} 
			}
			/* 동일하거나 연속된 문자(숫자) 사용금지*/
			if(policyInfoList.get(i).getPolicyId().equals("PI8")) {
				if(value.equals("1")) {
					%>
					<div class="font-blue h5">- <spring:message code="portal.password.alert.5" text="동일하거나 연속된 문자 또는 숫자는 사용을 할 수 없습니다." /></div>
					<% 
				} 
			}
			/* 암호 구성 방식*/
			if(policyInfoList.get(i).getPolicyId().equals("PI9")) {
				if(value.equals("8")) {
					%>
					<div class="font-blue h5">- <%=value%><spring:message code="portal.password.alert.6" text="자 이상, 3가지 이상의 대소문자,숫자,특수문자 조합이 필요합니다." /></div>
					<% 
				} else {
					%>
					<div class="font-blue h5">- <%=value%><spring:message code="portal.password.alert.6" text="자 이상, 2가지 이상의 대소문자,숫자,특수문자 조합이 필요합니다." /></div>
					<% 
				}
			}
		}
		%>
		<form id="password_form">
		<input type="hidden" id="roundkey" name="roundkey" />
		<input type="hidden" id="loginId" name="loginId" value="<%=loginId %>"/>
		<input type="hidden" id="oldPassword" name="oldPassword" />
		<input type="hidden" id="newPassword" name="newPassword" />
		<table class="table-write">
			<colgroup><col width="130px" /><col width="*" /></colgroup>
			<tbody>
			<tr>
				<th><spring:message code="user.label.41" text="기존 비밀번호 입력" /></th>
				<td><input type="password" id="password1" class="input-d" style="width:100%" /></td>
			</tr>	
			<tr>
				<th><spring:message code="user.label.42" text="새 비밀번호 입력" /></th>
				<td><input type="password" id="password2" class="input-d" style="width:100%" /></td>
			</tr>	
			<tr>
				<th><spring:message code="user.label.43" text="새 비밀번호 확인" /></th>
				<td><input type="password" id="password3" class="input-d" style="width:100%" /></td>
			</tr>
			</tbody>
		</table>
		<div class="btn-center">
			<span class="main-btn"><a href="#" id="btn_form_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span>
			<span class="main-btn"><a href="#" id="btn_form_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span>
		</div>
		
		</form>
		
	</div>
</body>
</html>
