<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.sds.acube.luxor.common.ConstantList" %>
<%@ page import="com.sds.acube.luxor.portal.vo.LogoutSysVO" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
	LogoutSysVO param = (LogoutSysVO)request.getAttribute("param");
	List<LogoutSysVO> list = null;
	list = (List<LogoutSysVO>)request.getAttribute("list");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="portal.label.201" text="세션 종료 URL 관리"/></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.form.js" charset="utf-8"></script>
<script type="text/javascript">
	var userList = null;

	function register(systemId) {
		if(luxor.isNullOrEmpty(systemId)) {
			$('#popupDiv').dialog('open');
			return false;
		}
		
		var params = 'systemId=' + systemId;
		callJson("logoutSysController", "get", params, function(json) {
			$('#popupDiv').dialog('open');
			Message.setMessageId(json.messageId);
			$('#systemId').val(json.systemId);
			$('#logoutUrl').val(json.logoutUrl);
		});
	}

	
	function apply() {
		if(luxor.isNullOrEmpty(Message.getMessageNameAll())) {
			alert("<spring:message code="portal.alert.msg.49" text="시스템명을 입력하세요." />");
			return false;
		}
		if(luxor.isNullOrEmpty($('#logoutUrl').val())) {
			alert("<spring:message code="portal.alert.msg.50" text="종료 URL을 입력하세요." />");
			$('#logoutUrl').focus();
			return false;
		}
		
		if(confirm('<spring:message code="portal.alert.msg.18" text="저장하시겠습니까?"/>')) {
			var params = $('#registerForm').formSerialize();
			callJson("logoutSysController", "apply", params, function(json) {
				if(json.messageName == 'success') {
					alert('<spring:message code="portal.alert.msg.13" text="저장되었습니다."/>');
					self.location.reload(true);
				}
			});
		}
	}

	function cancel() {
		$('#popupDiv').dialog('close');
	}

	function _delete(systemId) {
		if(confirm('<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?"/>')) {
			var params = 'systemId=' + systemId;
			callJson("logoutSysController", "delete", params, function(json) {
				if(json.messageName == 'success') {
					alert('<spring:message code="portal.alert.msg.111" text="삭제되었습니다."/>');
					self.location.reload(true);
				}else{
					alert('<spring:message code="portal.alert.msg.30" text="오류가 발생하였습니다.\\n시스템 담당자를 통해 로그를 확인해보시기 바랍니다."/>');
				}
			});
		}
	}

	
	function toEnable(systemId) {
		var params = 'systemId=' + systemId;
		if(!confirm('<spring:message code="portal.alert.msg.181" text="사용하시겠습니까?"/>')) {
			return;
		} 
		callJson("logoutSysController", "toEnable", params, function(json) {
			if(json.messageName == 'success') {
				alert('<spring:message code="portal.alert.msg.183" text="변경되었습니다."/>');
				self.location.reload(true);
			}else{
				alert('<spring:message code="portal.alert.msg.30" text="오류가 발생하였습니다.\\n시스템 담당자를 통해 로그를 확인해보시기 바랍니다."/>');
			}
		});
	}

	function toUnable(systemId) {
		var params = 'systemId=' + systemId;
		if(!confirm('<spring:message code="portal.alert.msg.182" text="비사용하시겠습니까?"/>')) {
			return;
		} 
		callJson("logoutSysController", "toUnable", params, function(json) {
			if(json.messageName == 'success') {
				alert('<spring:message code="portal.alert.msg.183" text="변경되었습니다."/>');
				self.location.reload(true);
			}else{
				alert('<spring:message code="portal.alert.msg.30" text="오류가 발생하였습니다.\\n시스템 담당자를 통해 로그를 확인해보시기 바랍니다."/>');
			}
		});
	}

	function test(url) {
		luxor.popup(url, {width:300, height:150});
	}

	function logoutAll() {
		callJson("logoutSysController", "getList", "", function(data) {
			for(var i=0; i < data.length; i++) {
				$('body').append("<iframe id='logoutHelpIframe"+i+"' class='dialog'></iframe>");
				$('#logoutHelpIframe'+i).attr('src',data[i].logoutUrl);
			}
			
			alert('<spring:message code="portal.alert.msg.184" text="호출되었습니다."/>');
		});
	}
	
	$(document).ready(function() {
		$('#popupDiv').dialog({
			modal:true, 
			resizable:false,
			autoOpen:false, 
			width:450, 
			height:150, 
			position:['center',50],
			close: function(e, ui) {
				$('#registerForm :text').each(function() {
					$(this).val('');
				});
			}
		});
	});
</script>
</head>

<body>

<form id="listForm" name="listForm" method="post">
<!-- admin-content-width w1000 -->
<div class="admin-content-width w1200 ml15" name="zone">
	 <!-- subtitle -->
	<div class="title-sub">
		<span class="title-sub-text"><spring:message code="portal.label.201" text="세션 종료 URL 관리"/></span>
		<!-- button -->
		<div class="aright">
			<span class="main-btn"><a href="#" id="btnRegister" onclick="register('');return false;" title="<spring:message code="portal.btn.label.28" text="등록" />"><spring:message code="portal.btn.label.28" text="등록" /></a></span><!--  등록버튼 -->
			<%--
			<span class="main-btn"><a href="#" id="btnRegister" onclick="logoutAll();return false;" title="<spring:message code="portal.btn.label.131" text="세션종료 호출" />"><spring:message code="portal.btn.label.131" text="세션종료 호출" /></a></span><!--  강제세션종료호출 -->
			--%>
		</div>
	 	<!-- //button -->
	</div>
	<!-- content -->
	<div class="table-body-wrap">
		<table class="table-body" id="htmlGrid">
			<thead>
 				<tr>
					<th width="250px"><spring:message code="portal.label.203" text="시스템 명"/></th>
					<th><spring:message code="portal.label.204" text="종료 URL"/> </th>
					<th width="250px"><spring:message code="portal.label.31" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
<%
	if(list.size() > 0) {
		for(int i=0; i < list.size(); i++) {
			LogoutSysVO item = (LogoutSysVO)list.get(i);
%>	
				<tr rowid="<%=item.getSystemId()%>">
					<td class="body-left"><%=item.getSystemName()%></td>
					<td class="body-left"><%=item.getLogoutUrl()%></td>
					<td>
						<span class="smain-btn"><a href="#" id="btnDel_<%=""%>" onclick="test('<%=item.getLogoutUrl()%>');return false;" title="<spring:message code="portal.btn.label.39" text="Test"/>"><spring:message code="portal.btn.label.39" text="Test"/></a></span>
<%
			if(item.getStatus() == 1) {
%>
							<span class="smain-btn"><a href="#" id="btnUnable_<%=item.getSystemId()%>" onclick="toUnable('<%=item.getSystemId()%>');return false;"><spring:message code="portal.btn.label.41" text="비사용"/></a></span>
<%
			} else {
%>
							<span class="smain-btn"><a href="#" id="btnEnable_<%=item.getSystemId()%>" onclick="toEnable('<%=item.getSystemId()%>');return false;"><spring:message code="portal.btn.label.40" text="사용"/></a></span>
<%
			}
%>
						<span class="smain-btn"><a href="#" onclick="register('<%=item.getSystemId()%>');return false;" title="<spring:message code="portal.btn.label.19" text="수정"/>"><spring:message code="portal.btn.label.19" text="수정"/></a></span>
						<span class="smain-btn"><a href="#" id="btnDel_<%=""%>" onclick="_delete('<%=item.getSystemId()%>');return false;" title="<spring:message code="portal.btn.label.30" text="삭제"/>"><spring:message code="portal.btn.label.30" text="삭제"/></a></span>
					</td>
				</tr>
<%	
		}
	}else{
%>
				<tr>
					<td colspan="5"><spring:message code="portal.alert.msg.113" text="조회된 데이타가 없습니다."/></td>
				</tr>
<%
	}
%>
 				</tbody>
		</table>
		
	</div>
</div>
</form>

<div id="alphabg"></div>
<div id="popupDiv" title="<spring:message code="portal.label.202" text="세션종료 URL 등록/수정" />">
	<form id="registerForm" name="registerForm" method="post">
	<input type="hidden" name="systemId" id="systemId" value="" />
	
	<table class="table-write">
		<tr>
			<th width="100"><spring:message code="portal.label.203" text="시스템 명"/></th>
			<td>
				<jsp:include page="/luxor/common/message.jsp">
					<jsp:param name="input_css_class" value="input-d"/>
					<jsp:param name="message_type" value="COMMON.LOGOUTSYS"/>
				</jsp:include>
			</td>
		</tr>
		<tr>
			<th><spring:message code="portal.label.204" text="종료 URL"/></th>
			<td><input type="text" name="logoutUrl" id="logoutUrl" class="input-d" style="width:98%;" /></td>
		</tr>
	</table>
	
	<div class="h5"></div>
	<div class="aright">
		<span class="main-btn"><a href="#" id="btnApply" onclick="apply();return false;"  title="<spring:message code="portal.btn.label.3" text="저장"/>"><spring:message code="portal.btn.label.3" text="저장"/></a></span><!-- 저장 -->
		<span class="main-btn"><a href="#" id="btnCancel" onclick="cancel();return false;"  title="<spring:message code="portal.btn.label.2" text="취소"/>"><spring:message code="portal.btn.label.2" text="취소"/></a></span><!-- 취소 -->
	</div>
	
	</form>
</div>

</body>
</html>
