<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*"%>
<%@ page import="com.sds.acube.luxor.framework.config.*"%>
<%
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	AdminUserVO adminUser = (AdminUserVO)session.getAttribute("adminProfile");
%>

<html lang="ko">
<head>
<title><spring:message code="portal.memberRequest.msg.19" text="계정 신청 키 관리" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	var adminUserType = "<%=adminUser.getAdminType()%>";
	
	$(document).ready(function() {
	    $('#keyRegisterView').dialog({
		    title:'<spring:message code="portal.memberRequest.msg.27" text="계정 신청키 등록" />',
			autoOpen:false,
			modal:true,
			resizable:false,
			width:500,
			position:['center',50],
			close:function(event, ui) { // 팝업창 닫을때 초기화
				$('#keyRegisterView input').val('');
			}
		});
	    
		var url = "/ep/memberRequest/getMemberRequestKeyList.do";
		var param = "";

		$('#requestKeyList').load(url,param);

		// 검색 클릭시
		$('#goSearch').click(function() {
			goSearch();
		});

		// 체크박스 전체선택
		$('#checkAll').live('click', function() {
			selectAll();
		});

		$('#searchTxt').keypress(function(event) {
			if(event.keyCode==13){
				goSearch();
			}
		});
	
		// 선택삭제 클릭시
		$('#selectedDelete').click(function() {
			var requestKey = "";
			var param = "";
			var sessionId = "";
			var checkCnt = $("input:checkbox:checked").length;
			var url = "/ep/memberRequest/getMemberRequestKeyList.do";
			
			if(checkCnt == 0) {	
				alert('<spring:message code="portal.alert.msg.24" text="선택을 해주세요." />');
				return;
			}
			
			if(confirm('<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?"/>')) {
				$(".box:checked").each(function() {
					requestKey = $(this).val();
					param += "&requestKeys="+requestKey;
				});

				param = param.substring(1);
				callJson("memberRequestController", "deleteMemberRequestKey", param, function(data) {			
					alert('<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />');
					top.location.reload();
				});
			}
		});

		// 등록 클릭시
		$('#createRequestKeyView').click(function() {
			$('#keyRegisterView').dialog('open');
		});

		// 자동발행 클릭
		$('#getRequestKey').click(function() {
			$('#keyRegisterView input[name=requestKey]').val(luxor.generateId());
		});

		$('#btn_save').click(function() {
			$('.notNull').each(function() {
				if($(this).val() == "") {
					alert('<spring:message code="portal.memberRequest.msg.30" text="필수 값이 입력되지 않았습니다."/>');
					return false;
				}
			});
			var param = $('#keyRegisterForm').serialize();
			postJson("memberRequestController", "insertMemberRequestKey", param, function(data) {			
				alert('<spring:message code="portal.memberRequest.msg.20" text="등록되었습니다." />');
				top.location.reload();
			});
		});

		$('#btn_cancel').click(function() {
			$('#keyRegisterView').dialog('close');
		});

		
	});

	/**
	 * 검색
	 */
	function goSearch() {
		var searchTxt = $("#searchTxt").val();
		var searchType = $("#searchType").val();
		var url = "/ep/memberRequest/getMemberRequestKeyList.do";
		var param = "";
		
		if(searchTxt=="") {
			searchType = "";
		} else {
			param += searchType+"="+encodeURIComponent(searchTxt);
		}
		$('#requestKeyList').load(url,param);
	}
	
	/**
	 * 체크박스 전체선택
	 */
	function selectAll() {
		var checked = $("#checkAll").attr("checked");
		$(".box").each(function(){
			var subChecked = $(this).attr("checked");
		    if (subChecked != checked)
		    (this.checked == true) ? this.checked = false : this.checked = true;
		});
	} 

	// 페이징
    function changePage(p) {
		var searchTxt = $("#searchTxt").val();
		var searchType = $("#searchType").val();
		
		if(searchTxt=="") {
			searchType = "";
		}

		var url = "/ep/memberRequest/getMemberRequestKeyList.do";
		var param = "";
		
		param += searchType+"="+encodeURIComponent(searchTxt);
		param += "&cPage="+p;
		
		$('#requestKeyList').load(url,param);
	}

   	//트리 Type  *0 : From User Dept Code to Root Code  *1 : From User Dept Code to Institution Code *2 : From User Dept Code to Company Code *3 : From User Dept Code to Root Code (Exclude Other Company) 
    function goOrg(){
        var treeType = 3;
        //그룹 관리자인 경우
        if(adminUserType == 1) {
        	treeType = 0;
        }
		var url = "/ep/user/userMng.do?returnMethod=getOrgId&treeType="+treeType+"&viewType=5";
		luxor.popup(url,{width:250,height:600});
	}

	function getOrgId(result) {
		//그룹을 선택하는 경우는 아무 처리 안함
		if(result[2] == '0') {
			return false;
		} else if(result[2] == '1') {
			$('#keyRegisterView input[name=compId]').val(result[0]);
			$('#keyRegisterView input[name=compName]').val(result[1]);
		} else {
			$('#keyRegisterView input[name=compId]').val(result[3]);
			$('#keyRegisterView input[name=compName]').val(result[4]);
			$('#keyRegisterView input[name=deptId]').val(result[0]);
			$('#keyRegisterView input[name=deptName]').val(result[1]);
		}
	}

    function goGrd(){
        var compId = $('#keyRegisterView input[name=compId]').val();
        if(compId == "") {
            alert('<spring:message code="portal.memberRequest.msg.31" text="회사를 먼저 선택하세요"/>');
            return false;
        } 
		luxor.popup("/ep/org/getGradeList.do?returnMethod=getGradeVal&compID="+compId,{width:250,height:600});
	}

    function getGradeVal(gradeVal){
        alert(arrGrade);
    	var arrGrade = gradeVal.split("/");
    	if(arrGrade[0] != 'ROOT') {
	    	$('#keyRegisterView input[name=gradeId]').val(arrGrade[0]);
			$('#keyRegisterView input[name=gradeName]').val(arrGrade[1]);
    	}
	}
</script>	
</head>

<body>
	<div class="admin-content-width w1200 ml15" name="zone">
		<!-- subtitle -->
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.memberRequest.msg.19" text="계정 신청 키 관리" /></span>
			<!-- button -->
			<div class="aright">
				<span class="main-btn"><a href="#" id="createRequestKeyView"><spring:message code="portal.memberRequest.msg.21" text="등록" /></a></span>
				<span class="main-btn"><a href="#" id="selectedDelete"><spring:message code="portal.memberRequest.msg.22" text="선택삭제" /></a></span>
			</div>
			<!-- //button -->
		</div>
		<!-- //subtitle -->

		<!-- table_write -->
		<table class="table-search">
		<thead>
			<tr>
				<th width="90"><spring:message code="portal.label.21" text="검색어" /></th>
				<td colspan="3">
   					<select id=searchType style="width:100">
					    <option value='requestKey'><spring:message code="portal.memberRequest.msg.23" text="신청키" /></option>
   					</select>
					<input type="text" class="input-d" id="searchTxt" style="WIDTH: 70%;" />
				</td>
				<td width="62" rowspan="2">
					<span class="smain-btn">
						<a href="#" id="goSearch"><span class="btnicon-01"></span><spring:message code="portal.btn.label.8" text="검색" /></a>
					</span>  
				</td>
			</tr>
		</thead>
		</table>
		<!-- //table_write -->     
                 
		<!-- table-body-wrap -->
		<div class="table-body-wrap">  
			<div id="requestKeyList">
			</div>
		</div>
		<!-- //table-body-wrap -->
	</div>
	
	<div class="popup-wrap" id="keyRegisterView" style="display:none;">
		<form id="keyRegisterForm" name="keyRegisterForm" method="post">
			<input type="hidden" name="compId"></input>
			<input type="hidden" name="deptId"></input>
			<input type="hidden" name="gradeId"></input>
			<table class="table-write" style="border-top:0px solid;">
				<colgroup><col width="15%" /><col width="35%" /><col width="15%" /><col width="35%" /></colgroup>
				<tbody>
				<tr>
					<th><spring:message code="portal.memberRequest.msg.17" text="계정신청 키" />*</th>
					<td>
						<input type="text" name="requestKey" class="input-d notNull" style="width:70%;margin-left:10px;margin-bottom:2px;" value="" />
						<span class="smain-btn"><a href="javascript:;" id="getRequestKey"><spring:message code="portal.memberRequest.msg.24" text="자동발행" /></a></span>
					</td>
				</tr>
				<tr>
					<th><spring:message code="portal.memberRequest.msg.13" text="회사" />*</th>
					<td>
						<input readonly="readonly" type="text" name="compName" class="input-d notNull" style="width:70%;margin-left:10px;margin-bottom:2px;" value="" />
						<span class="smain-btn"><a href="javascript:goOrg(3);"><spring:message code="portal.memberRequest.msg.21" text="등록" /></a></span>
					</td>
				</tr>
				<tr>
					<th><spring:message code="portal.memberRequest.msg.14" text="부서" />*</th>
					<td>
						<input readonly="readonly" type="text" name="deptName" class="input-d notNull" style="width:70%;margin-left:10px;margin-bottom:2px;" value="" />
						<span class="smain-btn"><a href="javascript:goOrg(3);"><spring:message code="portal.memberRequest.msg.21" text="등록" /></a></span>
					</td>
				</tr>
				<tr>
					<th><spring:message code="portal.memberRequest.msg.15" text="직급" /></th>
					<td>
						<input readonly="readonly" type="text" name="gradeName" class="input-d" style="width:70%;margin-left:10px;margin-bottom:2px;" value="" />
						<span class="smain-btn"><a href="javascript:goGrd();"><spring:message code="portal.memberRequest.msg.21" text="등록" /></a></span>
					</td>
				</tr>
				<tr>
					<th><spring:message code="portal.memberRequest.msg.29" text="키 사용 가능 횟수" /> *</th>
					<td>
						<input type="text" name="countLimit" class="input-d only-number notNull" style="width:70%;margin-left:10px;margin-bottom:2px;" value="1" />
					</td>
				</tr>
			</table>
		</form>
		<div class="btn-center">
			<span class="main-btn"><a href="#" id="btn_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span>
			<span class="main-btn"><a href="#" id="btn_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span>
		</div>
	</div>

</body>
</html>