<%/* 관리자 포탈 환경설정 페이지 */%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	PortalEnvironmentVO[] envs = (PortalEnvironmentVO[])request.getAttribute("envs");
	int cPage = (Integer)request.getAttribute("cPage");
	int totalCount = (Integer)request.getAttribute("totalCount");
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.35" text="포탈 환경설정" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/content.js"></script>
<script type="text/javascript">
function changePage(p) {
    document.location.href = "/ep/admin/environment/manager.do?cPage="+p;
}
var mode = 'insert';
$(document).ready(function() {
	$('#env_form_div').dialog({
		autoOpen:false,
		resizable:false,
		modal:true,
		width:620,
		position:['center',50],
		close:function(event, ui) { // 팝업창 닫을때 초기화
			$('#env_form input, #env_form textarea').each(function() { $(this).val(''); });
			$('#envId').removeAttr('readonly');
		}
	});

	// 등록버튼 클릭시
	$('#btn_env_add').click(function() {
		mode = 'insert';
		$('#envId').closest('td').html('<input id="envId" name="envId" class="input-d only-alphabet-number-underbar" maxlength="50" style="ime-mode:disabled;width:100%" />');
		$('#env_form_div').dialog({title:'<spring:message code="portal.label.40" text="환경변수 등록" />'});
		$('#valueSet').html('<input id="envValue" name="envValue" value="" maxlength="200" style="width:100%;"></textarea>');
		$('#env_form_div').dialog('open');
		$('#envId').focus();
		return false;
	});

	// 수정버튼 클릭시
	$('a[mode=mod]').click(function() {
		var param = "envId="+$(this).attr('envId');
		callJson('portalEnvironmentController', 'getEnvironment', param, function(data) {
			mode = 'update';
			$('#envId').closest('td').html("<span id='envId' value='"+data.envId+"'>"+data.envId+"</span>");
			if(data.envValueSet) {
				var envValueArray = data.envValueSet.split("|");
				var inputString = '<select id="envValue" name="envValue" style="width:100%">';
				for(var i = 0 ; i < envValueArray.length ; i++) {
					if(envValueArray[i] == data.envValue) {
						inputString += '<option value="'+envValueArray[i]+'" selected>'+envValueArray[i]+'</option>';
					} else {
						inputString += '<option value="'+envValueArray[i]+'">'+envValueArray[i]+'</option>';
					}
				}
				inputString += '</select>';
				$('#valueSet').html(inputString);
			} else {
				$('#valueSet').html('<input id="envValue" name="envValue" value="'+data.envValue+'" maxlength="200" style="width:100%;"></textarea>');
				 
			}
			$('#envDesc').val(data.envDesc);
			$('#env_form_div').dialog({title:'<spring:message code="portal.label.68" text="환경변수 수정" />'});
			$('#env_form_div').dialog('open');
		});
		return false;
	});

	// 등록/수정
	$('#btn_env_save').click(function() {
		var param = $('#env_form').serialize();
		if($('#envDesc').val().length > 1800) {
			alert('<spring:message code="portal.page.alert.5" text="설명 값은 1800자 이내로 등록해야 합니다." />');
			return false;
		}
		callJson('portalEnvironmentController', 'getEnvironment', param, function(data) {
			if(mode=='insert' && data!=null) {
				alert('<spring:message code="portal.alert.msg.1" text="이미 등록되어 있습니다." />');
			} else {
				if(($('#envId').val() == '' && mode=='insert') || $('#envDesc').val() == '') {
					alert('<spring:message code="portal.page.alert.6" text="변수와  설명은 반드시 넣어야 합니다." />');
				} else {
					if(mode!='insert') {
						param += "&envId="+$('#envId').text();
					}
					if($('input[name=envValue]').val()) {
						if(!confirm("정확한 값을 입력하지 않으면 시스템 에러를 일으킬 수 있습니다. 저장하시겠습니까?")) {
							return false;
						}
					}
					postJson('portalEnvironmentController', mode+'Environment', param, function(data) {
						alert('<spring:message code="portal.alert.msg.13" text="저장되었습니다." />');
						top.location.reload();
					});
				}
			}
		});
		return false;
	});
	
	// 삭제
	$('a[mode=del]').click(function() {
		if(confirm("<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?" />")) {
			var param = "envId="+$(this).attr('envId');
			callJson('portalEnvironmentController', 'deleteEnvironment', param, function(data) {
				if(eval(data)==true) {
					alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
					top.location.reload();
				}
			});
		}
		return false;
	});

	// 취소
	$('#btn_env_cancel').click(function() {
		$('#env_form_div').dialog('close');
		return false;
	});
	
});
</script>
</head>

<body>

<div class="admin-wrap">
	<div class="admin-content-width w1200 ml15" name="zone">
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.label.35" text="포탈 환경설정" /></span>
			<span class="admin-title-side-text"><%=session.getAttribute("PORTAL_NAME")%> [<%=session.getAttribute("PORTAL_ID")%>]</span>
			<div class="aright">
				<span class="main-btn"><a href="#" id="btn_env_add">등록</a></span>
			</div>
		</div> 
		<div class="table-body-wrap">
			<table width="1200" class="table-body">
			<thead>
				<tr>
					<th width="250"><spring:message code="portal.label.36" text="변수" /></th>
					<th width="200"><spring:message code="portal.label.38" text="값" /></th>
					<th width="590"><spring:message code="portal.label.37" text="설명" /></th>
					<th width="160"><spring:message code="portal.label.39" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
			<%
				for(PortalEnvironmentVO env : envs) {
					String envId = env.getEnvId();
					String envValue = CommonUtil.nullTrim(env.getEnvValue());
					String envValueSet = CommonUtil.nullTrim(env.getEnvValueSet());
					String envDesc = CommonUtil.nl2br(CommonUtil.nullTrim(env.getEnvDesc()));
			%>
				<tr>
					<td width="250"><%=envId%></td>
					<td width="200"><%=envValue%></td>
					<td width="590" style='text-align:left;padding:0 5px 0 5px;'><div style='width: 590px;'><%=envDesc%></div></td>
					<td width="160">
						<div class="acenter">
							<span class="smain-btn"><a href="#" mode="mod" envId="<%=envId%>" envValueSet="<%=envValueSet%>"><spring:message code="portal.btn.label.19" text="수정" /></a></span>
							<span class="smain-btn"><a href="#" mode="del" envId="<%=envId%>" envValueSet="<%=envValueSet%>"><spring:message code="portal.btn.label.30" text="삭제" /></a></span>
						</div>
					</td>
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
</div>

<div id="env_form_div" title="<spring:message code="portal.label.40" text="환경변수 등록" />">
	<div class="admin-content-pop">
		<div class="tb-write">
			<form id="env_form">
			<table class="table-write">
				<tr>
					<th width="100"><spring:message code="portal.label.36" text="변수" /></th>
					<td><input id="envId" name="envId" class="input-d only-alphabet-number-underbar" maxlength="50" style="ime-mode:disabled;width:100%" /></td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.38" text="값" /></th>
					<td id="valueSet"><input id="envValue" name="envValue" class="input-d" maxlength="200" style="width:100%;" /></td>
				</tr>
				<tr>
					<th width="100"><spring:message code="portal.label.37" text="설명" /></th>
					<td><textarea id="envDesc" name="envDesc" class="textarea-d" style="width:99%;height:100px;"></textarea></td>
				</tr>
			</table>
			</form>
		</div>
		<div class="aright"> 
			<span class="main-btn"><a href="#" id="btn_env_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span> 
			<span class="main-btn"><a href="#" id="btn_env_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span> 
		</div>
	</div>
</div>

</body>
</html>