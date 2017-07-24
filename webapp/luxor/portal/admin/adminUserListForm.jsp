<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.sds.acube.luxor.common.ConstantList" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.portal.vo.AdminUserVO" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
	AdminUserVO param = (AdminUserVO)request.getAttribute("param");
	AdminUserVO[] list  = (AdminUserVO[])request.getAttribute("userList");
	
	if("init".equals(param.getOption())) {
		UserProfileVO userProfile = new UserProfileVO();
		userProfile.setUserUid(null);
		session.setAttribute("userProfile", userProfile);
	}
	
	int cPage = (Integer)request.getAttribute("cPage");
	int totalCount = (Integer)request.getAttribute("totalCount");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="portal.label.151" text="포탈 관리자 설정"/></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.form.js" charset="utf-8"></script>
<script type="text/javascript">

	function changePage(p) {
	    document.location.href = "/ep/admin/getAdminUserList.do?cPage="+p;
	}
	
	var userList = new Array();

	function setAdminUser() {
		var params = '';
		callJson("groupPortalController", "getPortalList4JSON", params, function(json) {
			var html = '';
			html += '<table class="table-write">\n';
			html += '<tr><th width="100"><spring:message code="portal.label.152" text="포탈"/> </td><td>\n';
			html += '<select name="portalId" id="selectPortal">\n';
			html += '<option value="<%=ConstantList.ROOT_PORTAL_ID%>">Group</option>\n';
			for(var i=0;i<json.length;i++) {
				html += '<option value="' + json[i].portalId + '">' + json[i].portalName + '</option>\n';
			}
			html += '</select></td></tr>\n';
			html += '<tr><th><span class="smain-btn"><a href="#" id="btnEdit" onclick="addUserForm();return false;" title="<spring:message code="portal.btn.label.111" text="관리자 추가"/>"><spring:message code="portal.btn.label.111" text="관리자 추가"/></a></span> </td>';
			html += '<td><div id="adminUserList" style="width:100%;height:100px;overflow:auto;"></div>';
			html += '</table>\n';
			html += '<div class="admin-page-button-group">';
			html += '	<span class="main-btn"><a href="#" id="btnApply" onclick="applyAdminUser();return false;" title="<spring:message code="portal.btn.label.3" text="저장" />"><spring:message code="portal.btn.label.3" text="저장" /></a></span>\n'; //저장버튼
			html += '	<span class="main-btn"><a href="#" id="btnClose" onclick="$(\'#popupDiv\').dialog(\'close\');return false;" title="<spring:message code="portal.btn.label.29" text="닫기" />"><spring:message code="portal.btn.label.29" text="닫기" /></a></span>\n';//닫기
			html += '</div>\n';
			$('#popupDiv').html(html);
			$('#popupDiv').dialog('open');
		});
	}

	function addUserForm() {
		var viewType = 0;
		var url = "/ep/user/userMng.do?returnMethod=getUserInfo&viewType=" + viewType;
		luxor.popup(url, {width:880,height:570});
	}

	function getUserInfo(users) {
		userList =  $.makeArray($.extend(true, [], users));
		var html = '';
		for(var i=0; i < userList.length; i++) {
			if(userList[i] == null) continue;

			if($('tr[rowid='+userList[i].userID+']').is('tr')) {
				alert(userList[i].userName + "<spring:message code="portal.alert.msg.27" text="은(는) 이미 추가되어있습니다."/>");
				userList.remove(userList[i--]);
				continue;
			} 

			html += '<span>' + userList[i].userName + '/' + userList[i].deptName + '</span>&nbsp;<span class="smain-btn"><a href="#" id="btnApply" onclick="deleteUser(\'' + userList[i].userID + '\');return false;" title="삭제">삭제</a></span><br/>\n';
		}
		$('#adminUserList').html(html);
	}

	function deleteUser(userId) {
		for(var i=0;i<userList.length;i++) {
			if(userList[i].userID == userId) userList.remove(userList[i]);
		}
		getUserInfo(userList);
	}

	function applyAdminUser() {
		if(!userList || userList.length==0) {
			alert("추가할 관리자를 먼저 선택하세요");
			return;
		}
		
		if(!confirm('<spring:message code="portal.alert.msg.18" text="저장하시겠습니까?"/>')) return;
		
		var params = 'portalId=' + $('#selectPortal').val();
		for(var i=0;i<userList.length;i++) {
			params += '&adminIds=' + userList[i].userID + '&adminNames=' + encodeURIComponent(userList[i].userName) + '&adminUids='+userList[i].userUID;
		}

		callJson("adminController", "applyAdminUser", params, function(data) {
	        if(data._errorCode=='-9999') {
	        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
	        } else {
	            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
	        }
	        
			$('#popupDiv').dialog('close');
<%	if("init".equals(param.getOption())) { %>
			self.location.href = '/ep/admin/groupportal/getPortalList.do?option=init';
<%	} else { %>
			self.location.href = 'getAdminUserList.do';
<%	} %>
		});
	}

	function deleteAdminUser(portalId, userId) {
		if(!confirm('<spring:message code="portal.alert.msg.110" text="삭제하시겠습니까?" />')) return;
		
		var params = 'portalId='+portalId+'&userId='+userId;
		callJson('adminController', 'deleteAdminUser', params, function(json) {
			if(json.messageName == 'success') {
				alert('<spring:message code="portal.alert.msg.111" text="삭제되었습니다." />');
				self.location.href = 'getAdminUserList.do';
			} else {
				alert('<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />');
				return false;
			}
		});
	}

	$(document).ready(function() {
		$('#popupDiv').dialog({modal:true, resizable:false,autoOpen:false, width:400, height:250, position:['center',50]});
<%	if("init".equals(param.getOption())) { %>
		setAdminUser();
<%	} %>
	});
</script>
</head>

<body>
<div id="alphabg"></div>
<div id="popupDiv" title="<spring:message code="portal.label.151" text="포탈 관리자 설정"/>"></div>
<form id="listForm" name="listForm" method="post">
<!-- admin-content-width w1000 margin15-l -->
<div class="admin-content-width w1200 ml15" name="zone">
	 <!-- subtitle -->
	<div class="title-sub">
		<span class="title-sub-text"><spring:message code="portal.label.151" text="포탈 관리자 설정"/></span>
		<!-- button -->
		<div class="aright">
			<span class="main-btn"><a href="#" id="btnRegister" onclick="setAdminUser();return false;" title="<spring:message code="portal.btn.label.28" text="등록" />"><spring:message code="portal.btn.label.28" text="등록" /></a></span><!--  등록버튼 -->
		</div>
	 	<!-- //button -->
	</div>

	<!-- content -->
	<div class="table-body-wrap">
		<table class="table-body" id="htmlGrid">
			<thead>
 				<tr>
					<th width="120"><spring:message code="portal.label.156" text="관리자 구분"/></th>
					<th width="120"><spring:message code="portal.label.153" text="담당포탈"/> </th>
					<th><spring:message code="portal.label.154" text="사용자"/></th>
					<th width="150"><spring:message code="portal.label.154" text="사용자"/> Uid</th>
					<th width="150"><spring:message code="portal.label.155" text="변경일"/></th>
					<th width="120"><spring:message code="portal.label.31" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
<%
	if(list.length > 0) {
		for(int i=0; i < list.length; i++) {
			AdminUserVO vo = (AdminUserVO)list[i];
			String typeOfAdmin = vo.getPortalId().equals(ConstantList.ROOT_PORTAL_ID) ? "Group" : "Normal" ;
			String mainPortal = vo.getPortalId().equals(ConstantList.ROOT_PORTAL_ID) ? "-" : vo.getPortalName();
%>
				<tr rowid="<%=vo.getUserId()%>">
					<td><%=typeOfAdmin%></td>
					<td><%=mainPortal%></td>
					<td class="body-left"><%=vo.getUserName()%></td>
					<td><%=vo.getUserUid()%></td>
					<td><%=vo.getLastUpdateToString()%></td>
					<td>
						<span class="smain-btn"><a href="#" id="btnDel_<%=vo.getUserId()%>" onclick="deleteAdminUser('<%=vo.getPortalId()%>','<%=vo.getUserId()%>');return false;" title="<spring:message code="portal.btn.label.30" text="삭제"/>"><spring:message code="portal.btn.label.30" text="삭제"/></a></span>
					</td>
				</tr>
<%	
		}
	} else {
%>
				<tr>
					<td colspan="6"><spring:message code="portal.alert.msg.113" text="조회된 데이타가 없습니다."/></td>
				</tr>
<%
	}
%>
 				</tbody>
		</table>
		<!-- 페이징 -->
		<jsp:include page="/luxor/common/paging.jsp">
			<jsp:param name="cPage" value="<%=cPage%>" />
			<jsp:param name="totalCount" value="<%=totalCount%>" />
		</jsp:include>
		<!-- //페이징 -->
	</div>
</div>
</form>
</body>
</html>
