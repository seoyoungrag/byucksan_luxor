<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/luxor/common/header.jsp" />

<title>ACUBE</title>
<script type="text/javascript">

	// --------------------------------
	// 계정신청 단계 진행
	// --------------------------------
	function fncGoOpen() {
		// 약관동의 여부 체크
		if(!fncCheckAllAgrrement()){
			alert("<spring:message code='portal.memberRequest.msg.61' text='계정을 신청하시려면 약관에 동의하셔야합니다.' />");
			return false;
		}

		$('#mainForm').submit();
	}
	
	// --------------------------------
	// 약관동의 여부 체크
	// --------------------------------
	function fncCheckAllAgrrement() {
		var bResult = true;
		
		$('input:checkbox').each(function(i) {
			if(!$('input:checkbox:eq(' + i +')').is(':checked')){
				bResult = false;
				return false;
			}
		});
		
		return bResult;
	}
		
	// --------------------------------
	// 취소
	// --------------------------------
	function fncClose() {
		self.close();
	}
</script>
</head>
<body>
    
	<div class="member_request_title">
		<h3><spring:message code="portal.memberRequest.msg.62" text="계정신청에 관한 동의" /></h3>
	</div>

    <!-- main Form-->
	<form method="post" id="mainForm" name="mainForm" action="/ep/memberRequest/getRequestView.do">
	<input type="hidden" name="isTermsAgree" value="Y"></input>
    <div class="member_request_popup_wrap">
        <div class="title_sub_small"> 
			<h3><spring:message code="portal.memberRequest.msg.63" text="계정신청 이용약관" /></h3>
		</div>
		<div class="table_border" style="height:130px;">
			<h3>
				코드작성부분<br>
				코드작성부분<br>
				코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>
			</h3>
		</div>
		<div class="page_text">
			<p style="float:right;"><spring:message code="portal.memberRequest.msg.64" text="약관에 동의하십니까?" />&nbsp&nbsp<spring:message code="portal.memberRequest.msg.65" text="예" />:<input type="checkbox" name="openAgreement1" id="openAgreement1"/>
		</div>
		<div class="title_sub_small"> 
			<h3><spring:message code="portal.memberRequest.msg.66" text="정보 보호 이용 약관" /></h3>
		</div>
		<div class="table_border" style="height:130px;">
			<h3>
				코드작성부분<br>
				코드작성부분<br>
				코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>
			</h3>
		</div>
		<div class="page_text">
			<p style="float:right;"><spring:message code="portal.memberRequest.msg.64" text="약관에 동의하십니까?" />&nbsp&nbsp<spring:message code="portal.memberRequest.msg.65" text="예" />:<input type="checkbox" name="openAgreement2" id="openAgreement2"/>
		</div>
		<div class="title_sub_small"> 
			<h3><spring:message code="portal.memberRequest.msg.67" text="데이터 수집에 대한 이용 약관" /></h3>
		</div>
		<div class="table_border" style="height:130px;">
			<h3>
				코드작성부분<br>
				코드작성부분<br>
				코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>코드작성부분<br>
			</h3>
		</div>
		<div class="page_text">
			<p style="float:right;"><spring:message code="portal.memberRequest.msg.64" text="약관에 동의하십니까?" />&nbsp&nbsp<spring:message code="portal.memberRequest.msg.65" text="예" />:<input type="checkbox" name="openAgreement3" id="openAgreement3"/>
		</div>
        
        <div class="btn-center">
			<span class="main-btn"><a href="javascript:;" onclick="fncGoOpen(); return false;"><spring:message code="portal.memberRequest.msg.68" text="다음단계" /></a></span>
			<span class="main-btn"><a href="javascript:;" onclick="fncClose(); return false;" ><spring:message code="portal.memberRequest.msg.69" text="닫기" /></a></span>
		</div>
    </div>
    </form>
    <!-- //main Form-->

</body>
</html>
