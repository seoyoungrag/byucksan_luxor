<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/luxor/common/header.jsp" />

<title>ACUBE</title>
<script type="text/javascript">

	$(document).ready(function() {
		window.resizeTo(800, 340); 
		$('#validateRequestKey').click(function() {
			if($('input:text[name=requestKeyText]').val() == "") {
				alert("<spring:message code='portal.memberRequest.msg.40' text='등록 키를 먼저 입력하세요.' />");
				return false;
			}
			callJson("memberRequestController", "getMemberRequestKey", "requestKey="+$('input[name=requestKeyText]').val(), function(data) {			
				if(data == null) {
					alert("<spring:message code='portal.memberRequest.msg.41' text='계정신청 등록 키가 아닙니다.' />");
				} else if(data.countLimit <= data.useCount){
					alert("<spring:message code='portal.memberRequest.msg.42' text='해당 등록키 사용 횟수가 초과되었습니다.' />");
				} else {
					$('#compnay_info').show();
					$('#user_info').show();
					$('#compnay_info input[name=compName]').val(data.compName);
					$('#compnay_info input[name=deptName]').val(data.deptName);
					$('#user_info input[name=gradeName]').val(data.gradeName);
					$('#register_key_text').html("<spring:message code='portal.memberRequest.msg.43' text='계정 신청 키가 인증되었습니다.' />");
					$('input:text[name=requestKeyText]').attr('disabled','disabled');
					$('#memberRequestForm input[name=requestKeyValidation]').val('true');
					$('#memberRequestForm input[name=requestKey]').val($('input[name=requestKeyText]').val());
					window.resizeTo(800, 420); 
				}
			});
		});

		$('#checkIdForInsert').click(function() {
			if($('input:text[name=loginId]').val() == "") {
				alert("<spring:message code='portal.memberRequest.msg.44' text='로그인 ID를 먼저 입력하세요.' />");
				return false;
			}
			if($('input[name=requestKey]').val() == "") {
				alert("<spring:message code='portal.memberRequest.msg.45' text='등록 키를 먼저 검증하세요.' />");
				return false;
			}
			callJson("memberRequestController", "validateRequestId", "loginId="+$('input:text[name=loginId]').val(), function(data) {			
				if(data == null) {
					$('#login_id_text').html("<spring:message code='portal.memberRequest.msg.46' text='중복체크에 실패했습니다.' />");
				} else if(data.resultCode == 0 || data.resultCode == -1){
					$('#login_id_text').html("<spring:message code='portal.memberRequest.msg.47' text='이미 등록되어있는 ID입니다.' />");
				} else if(data.resultCode == 1) {
					$('#login_id_text').html("<spring:message code='portal.memberRequest.msg.48' text='사용할 수 있는 ID입니다.' />");
					$('#memberRequestForm input[name=idValidation]').val('true');
				} else {
					$('#login_id_text').html("<spring:message code='portal.memberRequest.msg.46' text='중복체크에 실패했습니다.' />");
				}
			});
		});
		
		$('#btn_save').click(function() {
			if($('#memberRequestForm input[name=requestKeyValidation]').val() == 'false') {
				alert("<spring:message code='portal.memberRequest.msg.49' text='등록키가 검증되지 않았습니다.' />");
				return false;
			}
			if($('#memberRequestForm input[name=idValidation]').val() == 'false') {
				alert("<spring:message code='portal.memberRequest.msg.50' text='ID가 검증되지 않았습니다.' />");
				return false;
			}
			if($('input:password[name=loginPassword]').val() != $('input:password[name=passwordCheck]').val()) {
				alert("<spring:message code='portal.memberRequest.msg.51' text='비밀번호 확인 오류입니다. 다시 확인해 주세요.' />");
				return false;
			}
			$('.notNull').each(function() {
				if($(this).val() == "") {
					alert("<spring:message code='portal.memberRequest.msg.52' text='필수 값이 입력되지 않았습니다.' />");
					return false;
				}
			});
			var param = $('#memberRequestForm').serialize();
			postJson("memberRequestController", "insertMemberRequest", param, function(data) {			
				if(data == true) {
					alert("<spring:message code='portal.memberRequest.msg.53' text='신청이 완료되었습니다.' />");
					self.close();
				} 
			});
		});

		$('#memberRequestForm input[name=loginId]').keydown(function() {
			$('#login_id_text').html("");
			$('#memberRequestForm input[name=idValidation]').val('false');
		});
	});
	
	//validateRequestId
	function init() {
		document.location.href = "/ep/memberRequest/getRequestView.do?isTermsAgree=Y";
	}
</script>
</head>
<body>
	<div class="popup-wrap" style="min-width:700px;">
		<div class="member_request_title">
			<h3><spring:message code='portal.memberRequest.msg.36' text='계정신청' /></h3>
		</div>
		<form id="memberRequestForm" name="memberRequestForm" method="post">
			<input type="hidden" name="requestKeyValidation" value="false"></input>
			<input type="hidden" name="requestKey" value=""></input>
			<input type="hidden" name="idValidation" value="false"></input>
			<table class="table-write" style="border-top:0px solid;">
				<colgroup><col width="15%" /><col width="35%" /><col width="15%" /><col width="35%" /></colgroup>
				<tbody>
				<tr>
					<th><spring:message code='portal.memberRequest.msg.17' text='계정 신청키' />*</th>
					<td colspan="3" style="padding:7px 0px;">
						<input type="text" name="requestKeyText" class="input-d notNull" style="width:40%;margin-left:10px;margin-bottom:2px;" value="" />
						<span class="smain-btn"><a href="javascript:;" id="validateRequestKey"><spring:message code='portal.memberRequest.msg.54' text='확인' /></a></span>
						<p id="register_key_text" style="margin-left:10px;margin-top:5px;"><spring:message code='portal.memberRequest.msg.55' text='계정 신청을 위해 등록받은 키를 입력해 주세요' /></p>
					</td>
				</tr>
				<tr id="compnay_info" style="display:none;">
					<th><spring:message code='portal.memberRequest.msg.13' text='회사' /></th>
					<td style="padding:7px 0px;">
						<input readonly="readonly" type="text" name="compName" class="input-d notNull" style="width:70%;margin-left:10px;margin-bottom:2px;" value="" />
					</td>
					<th><spring:message code='portal.memberRequest.msg.14' text='부서' /></th>
					<td style="padding:7px 0px;">
						<input readonly="readonly" type="text" name="deptName" class="input-d notNull" style="width:70%;margin-left:10px;margin-bottom:2px;" value="" />
					</td>
				</tr>
				<tr id="user_info" style="display:none;">
					<th><spring:message code='portal.memberRequest.msg.12' text='사용자명' />*</th>
					<td style="padding:7px 0px;">
						<input type="text" name="userName" class="input-d notNull" style="width:90%;margin-left:10px;margin-bottom:2px;" value="" />
					</td>
					<th><spring:message code='portal.memberRequest.msg.15' text='직급' /></th>
					<td style="padding:7px 0px;">
						<input readonly="readonly" type="text" name="gradeName" class="input-d" style="width:70%;margin-left:10px;margin-bottom:2px;" value="" />
					</td>
				</tr>
				<tr>
					<th><spring:message code='portal.memberRequest.msg.11' text='로그인ID' />*</th>
					<td colspan="3" style="padding:7px 0px;">
						<input type="text" name="loginId" class="input-d notNull" style="width:40%;margin-left:10px;margin-bottom:2px;" value="" />
						<span class="smain-btn"><a href="javascript:;" id="checkIdForInsert" title="중복체크"><spring:message code='portal.memberRequest.msg.56' text='중복체크' /></a></span>
						<p id="login_id_text" style="margin-left:10px;margin-top:5px;"></p>
					</td>
				</tr>
				<tr style="height:80px;">
					<th><spring:message code='portal.label.212' text='비밀번호' />*</th>
					<td style="padding:7px 0px;">
						<input type="password" name="loginPassword" class="input-d notNull" style="width:90%;margin-left:10px;margin-bottom:2px;" value="" />
						<p style="margin-left:10px;margin-top:5px;"><spring:message code='portal.memberRequest.msg.57' text='일회성으로 사용하는 임시 비밀번호 입니다.' /></p>
						<p style="margin-left:10px;margin-top:5px;"><spring:message code='portal.memberRequest.msg.58' text='등록완료 후 비밀번호를 다시 설정하게 됩니다.' /></p>
					</td>
					<th><spring:message code='portal.memberRequest.msg.26' text='비밀번호 확인' />*</th>
					<td style="padding:7px 0px;">
						<input type="password" name="passwordCheck" class="input-d notNull" style="width:90%;margin-left:10px;margin-bottom:2px;" value="" />
						<p style="margin-left:10px;margin-top:5px;"><spring:message code='portal.memberRequest.msg.59' text='입력한 비밀번호를 한 번 더 입력해 주세요.' /></p>
						<p style="margin-left:10px;margin-top:5px;">&nbsp</p>
					</td>
				</tr>
			</table>
		</form>
		<div class="btn-center">
			<span class="main-btn"><a href="javascript:;" id="btn_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span>
			<span class="main-btn"><a href="javascript:;" onclick="init();"><spring:message code="portal.btn.label.21" text="초기화" /></a></span>
			<span class="main-btn"><a href="javascript:;" onclick="self.close();" id="btn_cancel"><spring:message code="portal.btn.label.29" text="닫기" /></a></span>
		</div>
	</div>
</body>
</html>
