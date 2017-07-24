<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	String userUid = userProfile.getUserUid();
	String deptId = userProfile.getDeptId();
	String compId = userProfile.getCompId();
	String serviceId = "";

%>
<html lang="ko">
<head>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">

	var deptId= "<%=deptId%>";
	var compId= "<%=compId%>";
	var viewType ="3";
	var isSearch ="Y";
	var treeType = "0";	//트리 Type  *0 : From User Dept Code to Root Code  *1 : From User Dept Code to Institution Code *2 : From User Dept Code to Company Code *3 : From User Dept Code to Root Code (Exclude Other Company) 
	
	function goSrch(){
		var url = "";
		var searchName = "김*";
		var nType = "0";
		var searchType = "byName";
		
		url = "/ep/user/userMng.do?returnMethod=getUserId&treeType="+treeType+"&viewType="+viewType+"&nType="+nType+"&searchType="+searchType+"&searchName="+searchName+"&isSearch="+isSearch;

		luxor.popup(url,{width:860,height:570});
	}

	function goSrch0(){
		var url = "";
		
		url = "/ep/user/userMng.do?returnMethod=getUserId&viewType=0&treeType="+treeType;

		luxor.popup(url,{width:860,height:570});
	}

	function goSrch1(){
		var url = "";
		
		url = "/ep/user/userMng.do?returnMethod=getUserId&viewType=1&treeType="+treeType;

		luxor.popup(url,{width:860,height:570});
	}

	function goSrch2(){
		var url = "";
		var txtSrch = document.getElementById("userTxtSearch2").value;
		var nType = $("#selSearchScope2").val();
		var searchType = $("#selSearchType2").val();

		url = "/ep/user/userMng.do?returnMethod=getUserId&treeType="+treeType+"&nType="+nType+"&searchType="+searchType+"&searchName="+txtSrch+"&viewType=2&isSearch=Y";

		luxor.popup(url,{width:860,height:570});
	}

	function goSrch3(){
		var url = "";
		var txtSrch = document.getElementById("userTxtSearch3").value;
		var nType = $("#selSearchScope3").val();
		var searchType = $("#selSearchType3").val();

		url = "/ep/user/userMng.do?returnMethod=getUserId&treeType="+treeType+"&nType="+nType+"&searchType="+searchType+"&searchName="+txtSrch+"&viewType=3&isSearch=Y";

		luxor.popup(url,{width:860,height:570});
	}

	function goSrch4(){
		var url = "";
		var txtSrch = document.getElementById("userTxtSearch4").value;
		var nType = $("#selSearchScope4").val();
		var searchType = $("#selSearchType4").val();

		url = "/ep/user/userMng.do?returnMethod=getUserId&treeType="+treeType+"&selectType=single&nType="+nType+"&searchType="+searchType+"&searchName="+txtSrch+"&viewType=2&isSearch=Y";

		luxor.popup(url,{width:860,height:570});
	}
	
	function getUserId(users){
		for(var i=0; i < users.length; i++) {
			$('#getViewDiv').append(users[i].userID+":"+users[i].userName+"<br />");
		}
	}

	function getOrgId(orgId){
		$('#getViewOrg').append(orgId);
	}

	function getGradeVal(gradeVal){
		$('#getViewGrd').append(gradeVal);
	}

	function getDutyVal(dutyVal){
		$('#getViewDuty').append(dutyVal);
	}

	function getPositionVal(positionVal){
		$('#getViewPostn').append(positionVal);
	}

	function getTitleVal(titleVal){
		$('#getViewTitle').append(titleVal);
	}

	function goOrg(){
		var uid= "<%=userUid%>";
		var url = "/ep/user/userMng.do?returnMethod=getOrgId&treeType="+treeType+"&viewType=4";
		luxor.popup(url,{width:250,height:600});
	}
	
	function goGrd(){
		luxor.popup("/ep/org/getGradeList.do?returnMethod=getGradeVal&compID="+compId,{width:250,height:600});
	}

	function goDuty(){
		luxor.popup("/ep/org/getDutyList.do?returnMethod=getDutyVal&compID="+compId,{width:250,height:600});
	}

	function goPostn(){
		luxor.popup("/ep/org/getPositionList.do?returnMethod=getPositionVal&compID="+compId,{width:250,height:600});
	}

	function goTitle(){
		luxor.popup("/ep/org/getTitleList.do?returnMethod=getTitleVal&compID="+compId,{width:250,height:600});
	}

	function goAcl(){
		var resourceId = "test0816_2";
		luxor.popup("/ep/acl/aclSetupUser.do?resourceID="+resourceId,{width:860,height:570});
	}

	function viewAcl(){
		var resourceId = "test0816_2";
		luxor.popup("/ep/acl/aclView.do?resourceID="+resourceId,{width:400,height:400});
	}
		
	</script>
</head>
<body>


<div class="admin-wrap ml15 mt10">

	0. 권한설정 화면
	&nbsp;&nbsp;<button type="submit" id="btn1" onclick="goAcl()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">보기</button>
	<br><br>
	
	0-1. 권한조회 화면
	&nbsp;&nbsp;<button type="submit" id="btn1" onclick="viewAcl()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">보기</button>
	<br><br>
	
	**. 모든테스트- type
	&nbsp;&nbsp;<button type="submit" id="btn1" onclick="goSrch()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">보기</button>
	<br><br>
	
	1. 트리 + 회원목록 + SelectBox 화면- type0
	&nbsp;&nbsp;<button type="submit" id="btn1" onclick="goSrch0()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">보기</button>
	<br><br>
	2. 트리 + 회원목록 화면- type1
	&nbsp;&nbsp;<button type="submit" id="btn1" onclick="goSrch1()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">보기</button>
	<br><br>
	3. 회원목록 + SelectBox 화면- type2<br><br>
	<div class="search02" id ="search02">
		<select id="selSearchScope2" name="select" style="WIDTH: 70px;">
			<OPTION value="0">그룹 내</OPTION>
			<OPTION value="1">회사 내</OPTION>
			<OPTION value="2">부서 내</OPTION>
		</select>
	
		<select id="selSearchType2" name="select2" style="WIDTH: 80px;">
			<OPTION value="byName">성명</OPTION>
			<OPTION value="byId">ID</OPTION>
		</select>
		<input type="text" id="userTxtSearch2" class="input-txtfield01" style="WIDTH: 140px;" value="박기안" />
	 	<button type="submit" id="btn1" onclick="goSrch2()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">검색</button>
	</div>
<br>
	4. 회원 1명 선택 화면- type2<br><br>
	<div class="search02" id ="search02">
		<select id="selSearchScope4" name="select" style="WIDTH: 70px;">
			<OPTION value="0">그룹 내</OPTION>
			<OPTION value="1">회사 내</OPTION>
			<OPTION value="2">부서 내</OPTION>
		</select>
	
		<select id="selSearchType4" name="select2" style="WIDTH: 80px;">
			<OPTION value="byName">성명</OPTION>
			<OPTION value="byId">ID</OPTION>
		</select>
		<input type="text" id="userTxtSearch4" class="input-txtfield01" style="WIDTH: 140px;" value="이결재" />
	 	<button type="submit" id="btn1" onclick="goSrch4()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">검색</button>
	</div>
<br>
	5. 회원목록 화면- type3<br><br>
	<div class="search02" id ="search02">
		<select id="selSearchScope3" name="select" style="WIDTH: 70px;">
			<OPTION value="0">그룹 내</OPTION>
			<OPTION value="1">회사 내</OPTION>
			<OPTION value="2">부서 내</OPTION>
		</select>
	
		<select id="selSearchType3" name="select2" style="WIDTH: 80px;">
			<OPTION value="byName">성명</OPTION>
			<OPTION value="byId">ID</OPTION>
		</select>
		<input type="text" id="userTxtSearch3" class="input-txtfield01" style="WIDTH: 140px;" value="박기안" />
	 	<button type="submit" id="btn1" onclick="goSrch3()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">검색</button>
	</div>

	<br><br>
	<div class="title02">
		<span class="title-sub-text">선택된 회원 ID 리스트 :</span>
    </div>
	<div id ="getViewDiv" ></div>
	<br><br>
	
	6. 트리 선택 화면- type4 
	&nbsp&nbsp<button type="submit" id="btn1" onclick="goOrg()" class="admin-org-btn-action" onmouseover="this.className='admin-org-btn-action-over';" onmouseout="this.className='admin-org-btn-action';">보기</button>
	<br><br>
	<div class="title02">
		<span class="title-sub-text">선택된 조직 ID :</span>
    </div>
		<div id ="getViewOrg" ></div>
		
		
	<br><br><br><br>
	<a href="#" id="btn2" onclick="goGrd()">직급선택GO!</a>
	
	<br><br>
		선택된 직급 : <br>
		<div id ="getViewGrd" ></div>
		
		
	<br><br><br><br>
	<a href="#" id="btn3" onclick="goDuty()">직무선택GO!</a>
	
	<br><br>
		선택된 직무 : <br>
		<div id ="getViewDuty" ></div>
		
		
	<br><br><br><br>
	<a href="#" id="btn4" onclick="goPostn()">직위선택GO!</a>
	
	<br><br>
		선택된 직위 : <br>
		<div id ="getViewPostn" ></div>
		
	<br><br><br><br>
	<a href="#" id="btn5" onclick="goTitle()">직책선택GO!</a>
	
	<br><br>
		선택된 직책 : <br>
		<div id ="getViewTitle" ></div>
		
</div>		
</body>
</html>