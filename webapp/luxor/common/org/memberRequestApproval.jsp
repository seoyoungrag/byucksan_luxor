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
<title><spring:message code="portal.memberRequest.msg.1" text="계정신청 목록" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	var adminUserType = "<%=adminUser.getAdminType()%>";
	
	$(document).ready(function() {
		var url = "/ep/memberRequest/getApprovalList.do";
		var param = "";

		$('#requestMemberList').load(url,param);

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
	
		// 승인
		$('#btn_approval').click(function() {
			var checkCnt = $("input:checkbox:checked").length;
			if(checkCnt == 0) {	
				alert('<spring:message code="portal.alert.msg.24" text="선택을 해주세요." />');
				return;
			}
			var url = "/ep/memberRequest/getApprovalList.do";
			var requestId = "";
			var param = "";
			if(confirm('<spring:message code="portal.memberRequest.msg.2" text="승인하시겠습니까?"/>')) {
				$(".box:checked").each(function() {
					requestId = $(this).val();
					if($(this).val() != "approved") {
						param += "&requestIds="+requestId;
					}
				});
				if(param == "") {
					alert('<spring:message code="portal.memberRequest.msg.3" text="이미 승인된 목록만을 선택하셨습니다." />');
					return;
				}
				param += "&requestStatus=1"; //승인
				param = param.substring(1); 
				postJson("memberRequestController", "updateMemberRequestStatus", param, function(data) {	
					alert('<spring:message code="portal.memberRequest.msg.4" text="승인되었습니다." />');
					top.location.reload();
				});
			}
		});

		// 반려
		$('#btn_disapproval').click(function() {
			var checkCnt = $("input:checkbox:checked").length;
			if(checkCnt == 0) {	
				alert('<spring:message code="portal.alert.msg.24" text="선택을 해주세요." />');
				return;
			}
			var param = "";
			var url = "/ep/memberRequest/getApprovalList.do";
			var requestId = "";
			if(confirm('<spring:message code="portal.memberRequest.msg.5" text="반려하시겠습니까?"/>')) {
				$(".box:checked").each(function() {
					requestId = $(this).val();
					if($(this).val() != "approved") {
						param += "&requestIds="+requestId;
					}
				});
				if(param == "") {
					alert('<spring:message code="portal.memberRequest.msg.6" text="이미 승인된 목록은 반려할 수 없습니다." />');
					return;
				}
				param += "&requestStatus=2"; //반려
				param = param.substring(1); 
				postJson("memberRequestController", "updateMemberRequestStatus", param, function(data) {			
					alert('<spring:message code="portal.memberRequest.msg.7" text="반려되었습니다." />');
					top.location.reload();
				});
			}
		});
		
	});

	/**
	 * 검색
	 */
	function goSearch() {
		var searchTxt = $("#searchTxt").val();
		var searchType = $("#searchType").val();
		var url = "/ep/memberRequest/getApprovalList.do";
		var param = "";
		
		if(searchTxt=="") {
			searchType = "";
		} else {
			param += searchType+"="+encodeURIComponent(searchTxt);
		}
		$('#requestMemberList').load(url,param);
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

		var url = "/ep/memberRequest/getApprovalList.do";
		var param = "";
		
		param += searchType+"="+encodeURIComponent(searchTxt);
		param += "&cPage="+p;
		
		$('#requestMemberList').load(url,param);
	}

</script>	
</head>

<body>
	<div class="admin-content-width w1200 ml15" name="zone">
		<!-- subtitle -->
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.memberRequest.msg.1" text="계정신청 목록" /></span>
			<!-- button -->
			<div class="aright">
				<span class="main-btn"><a href="#" id="btn_approval"><spring:message code="portal.memberRequest.msg.8" text="승인" /></a></span>
				<span class="main-btn"><a href="#" id="btn_disapproval"><spring:message code="portal.memberRequest.msg.9" text="반려" /></a></span>
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
					    <option value='requestKey'><spring:message code="portal.memberRequest.msg.10" text="신청키" /></option>
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
			<div id="requestMemberList">
			</div>
		</div>
		<!-- //table-body-wrap -->
	</div>
</body>
</html>