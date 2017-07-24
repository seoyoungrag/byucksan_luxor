<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>

<%
	UserProfileVO userProfile = (UserProfileVO)request.getAttribute("userProfile");
	String loginId = userProfile.getLoginId();
	
	String compId = userProfile.getCompId();	
	String orgType = (String)session.getAttribute("ORG_TYPE");
	String userList = userProfile.getUserUid();
	String portalUrl = LuxorConfig.getString("WEB-APP.URL");
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="user.label.45" text="개인정보 수정" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	$('#btn_save').click(function() {
		var param = $("#userinfo_form").serialize();
		postJson("userController", "changeUserInformation", param, function(data) {
	        if(data._errorCode=='-9999') {
	        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
	        } else {
	            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
	            document.location.reload();
	        }
		});
	});
	
	$('#btn_cancel').click(function() {
		self.close();
	});
	
	$('#btn_changePassword').click(function() {
			
		var compId = "<%=compId%>";
		var orgType = "<%=orgType%>";
		var userList = "<%=userList%>";
		
		if(orgType = null){
			orgType = "";
		}
		
		var win = window.open("<%=portalUrl%>/acube/jsp/DisplayPasswordChange.jsp?orgId="+compId+"&orgType="+orgType+"&userList="+userList,"pop","width=400,height=250,scrollbars=yes,resizable=yes");
		win.focus();

	});

});
</script>
</head>



	<div class="popup-wrap">
		 <!-- content-width -->
		<div class="content-width margin15-l w99percent" name="zone">
	        
	    <!-- title-sub -->
  <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >


	    <div class="title-sub">
	        <span class="title-sub-text"><spring:message code="user.label.45" text="개인정보 수정" /></span>
	    </div> 
	    <!-- //title-sub -->
	    <form id="userinfo_form">
			<input type="hidden" name="userUID" value="<%=userProfile.getUserUid()%>" />
		    <!-- tb-write -->
		    <div class="tb-write">   
		    
		        <!-- table_body -->
		        <table class="table-write">
		            <tr>
		                <th width="180"><spring:message code="user.label.8" text="성명" /></th>
		                <td><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getUserName()%>" /></td>
		                <th width="180"><spring:message code="user.label.9" text="ID" /></th>
		                <td><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getLoginId()%>" /></td>
		            </tr>
		        </table>
		        <table class="table-write">
		            <tr>
		            <th width="90" rowspan="3"><spring:message code="user.label.47" text="전화번호" /></th>
		                <td width="90" class="tit02"><spring:message code="acl.label.12" text="회사" /></td>
		                <td><input type="text" name="officeTel" class="input-d" style="width:100%" value="<%=userProfile.getOfficeTel()%>" /></td>
		            </tr>
		            <tr>
		                <td class="tit02"><spring:message code="user.label.46" text="자택" /></td>
		                <td><input type="text" name="homeTel" class="input-d" style="width:100%" value="<%=userProfile.getHomeTel()%>" /></td>
		            </tr>
		            <tr>
		                <td class="tit02"><spring:message code="user.label.17" text="휴대폰" /></td>
		                <td><input type="text" name="mobile" class="input-d" style="width:100%" value="<%=userProfile.getMobile()%>" /></td>
		            </tr>
		        </table>
		        <table class="table-write">
		            <tr>
		                <th width="180"><spring:message code="acl.label.12" text="회사" /></th>
		                <td colspan="3"><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getCompName()%>" /></td>
		            </tr>
		            <tr>
		                <th><spring:message code="user.label.11" text="부서" /></th>
		                <td><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getDeptName()%>" /></td>
		                <th width="180"><spring:message code="user.label.48" text="파트" /></th>
		                <td><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getPartName()%>" /></td>
		                </tr>
		            <tr>
		                <th><spring:message code="user.label.12" text="직급" /></th>
		                <td width="170" ><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getGradeName()%>" /></td>
		                <th><spring:message code="user.label.15" text="직위" /></th>
		                <td><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getPositionName()%>" /></td>
		            </tr>
		            <tr>
		                <th><spring:message code="user.label.14" text="직책" /></th>
		                <td width="170" ><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getTitleName()%>" /></td>
		                <th><spring:message code="user.label.13" text="직무" /></th>
		                <td><input type="text" class="input-readonly" style="width:100%" value="<%=userProfile.getDutyName()%>" /></td>
		            </tr>
		            <tr>
		                <th><spring:message code="user.label.49" text="팩스" /></th>
		                <td colspan="3"><input type="text" name="officeFax" class="input-d" style="width:100%" value="<%=userProfile.getOfficeFax()%>" /></td>
		                </tr>
		            <tr>
			            <th><spring:message code="user.label.50" text="내부메일" /></th>
			                <td colspan="3"><input type="text" class="input-readonly" name="sysMail" class="input-d" style="width:100%" value="<%=userProfile.getSysMail()%>" /></td>
			        </tr>
			        <tr>
		           		<th><spring:message code="user.label.51" text="외부메일" /></th>
		                <td colspan="3"><input type="text" name="email" class="input-d" style="width:100%" value="<%=userProfile.geteMail()%>" /></td>
		            </tr>
		        </table>
		        <!-- //table_body -->   
		                    
		    </div>          
		    <!-- //table-write -->
		    
		    <div class="aright">
				<span class="main-btn"><a href="#" id="btn_changePassword">비밀번호 변경</a></span>
				<span class="main-btn"><a href="#" id="btn_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span>
				<span class="main-btn"><a href="#" id="btn_cancel"><spring:message code="portal.btn.label.29" text="닫기" /></a></span>
			</div>
	    </form>  
	    </div>
    	<!-- //content-width -->
    </div>
</body>
</html>
