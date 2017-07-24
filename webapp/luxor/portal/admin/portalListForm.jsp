<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.portal.vo.GroupPortalVO" %>
<%@page import="com.sds.acube.luxor.common.util.CommonUtil"%>
<%
	GroupPortalVO param = (GroupPortalVO)request.getAttribute("param");
	GroupPortalVO[] list = null;
	list = (GroupPortalVO[])request.getAttribute("list");
	String[] DEFAULT_FLAG = {"", "Yes"};
	
	int cPage = (Integer)request.getAttribute("cPage");
	int totalCount = (Integer)request.getAttribute("totalCount");
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	String userUid = userProfile.getUserUid();
	
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="portal.label.161" text="그룹 포탈 관리"/></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.form.js" charset="utf-8"></script>
<script type="text/javascript">
	var mode = 'insert';

	function changePage(p) {
	    document.location.href = "/ep/admin/groupportal/getPortalList.do?cPage="+p;
	}
	
	function setRelatedCompInfo(nodeInfo){
		$('#relatedCompid').val(nodeInfo.substring(0,nodeInfo.indexOf("/")));
		$('#relatedCompname').val(nodeInfo.substring(nodeInfo.indexOf("/")+1));
		
	}
	
	function register(portalId) {
		$.ajax({
			url : '/ep/admin/groupportal/loadRegisterForm.do', 
			data : '',
			dataType : 'html', 
			success : function(html) {
				
				$('#popupDiv').html(html);
	
				if(portalId != '') {
					mode = 'update';
					var params = 'portalId=' + portalId;
					callJson("groupPortalController", "getPortalInfo", params, function(json) {
						$('#portalId').val(json.portalId);
						$('#portalName').val(json.portalName);
						$('#relatedCompid').val(json.relatedCompid);
						$('#relatedCompname').val(json.relatedCompname);
						$("input:radio[name=relatedPortalinfo]:input[value='"+json.relatedPortalinfo+"']").attr("checked","checked");										

						//$('#parentId').val(json.parentId);
						for(var i=0; i<json.portalList.length; i++){
							checkedOption = "";
							if(json.portalList[i].portalId != json.portalId){
								if(json.portalList[i].portalId == json.parentId) checkedOption ="selected";
								$('#parentId').append("<option value='"+json.portalList[i].portalId+"' "+checkedOption+">"+json.portalList[i].portalName+" ("+json.portalList[i].portalId+")</option>");
							}

						}
						$('#portalId').attr("readonly","readonly");
					});
				} else {					
					$('#portalId').removeAttr("readonly");
				}
				
				$('#popupDiv').dialog('open');
			},
			error : function(e) {
				alert(e.message);
			}
		});
	}

	function apply() {
		if(confirm('<spring:message code="portal.alert.msg.18" text="저장하시겠습니까?"/>')) {
			if(mode=='insert' && checkDup(true)==false) {
				return false;
			}

			if($('#relatedCompname').val() == "" ){
				$('#relatedCompid').val("");
			}
			
			var parentId = "portalId="+$('#parentId').val();
			callJson("groupPortalController", "getPortalInfo", parentId, function(data) {
				if(data!=null || $('#parentId').val() == '_ROOT_') {
					var param = $('#registerForm').serialize();
					postJson("groupPortalController", "portalRegister", param, function(data) {
				        if(data._errorCode=='-9999') {
				        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />")
				        } else {
				            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
				            <%	if("init".equals(param.getOption())) { %>
			        		document.location.href = "/ep/luxor/admin/login.jsp?cacheTime="+new Date();
					    	<%	} else { %>
					    		document.location.href = "/ep/admin/groupportal/getPortalList.do";
					    	<%	} %>
				        }
					});
					result = true;
				} else {
					alert('상위포탈 ID가 잘 못 연결되었습니다.');
					result = false;
				}
			});
		}
	}

	function changePortal(portalId) {
		if(!confirm('<spring:message code="portal.alert.msg.151" text="포탈을 변경하시겠습니까?"/>')) {
			return;
		}
		var params = 'portalId=' + portalId;
		
		callJson("groupPortalController", "changePortal", params, function(json) {
			if(json.messageName == 'success') {
				alert('<spring:message code="portal.alert.msg.152" text="변경되었습니다."/>');
				parent.location.reload(true);
			}else{
				alert('<spring:message code="portal.alert.msg.153" text="오류가 발생했습니다.\n 로그를 확인해주십시오."/>');
			}
		});
		
		$.ajaxSetup({async:true});
	}

	function deletePortal(portalId,childPortal) {
		if(childPortal != "") {
			alert('<spring:message code="portal.groupportal.alert.1" text="하위 포탈이 있으므로 삭제할 수 없습니다."/>');
			return;
		}
		if(!confirm('<spring:message code="portal.alert.msg.154" text="포탈을 삭제하시겠습니까?"/>')) {
			return;
		}
		var params = 'portalId=' + portalId;
		callJson("groupPortalController", "deletePortal", params, function(json) {
			if(json.messageName == 'success') {
				alert('<spring:message code="portal.alert.msg.111" text="삭제되었습니다."/>');
				parent.location.reload(true);
			}else{
				alert('<spring:message code="portal.alert.msg.153" text="오류가 발생했습니다.\n 로그를 확인해주십시오."/>');
			}
		});
	}
	
	// 우측 컨텐츠 목록 권한설정 화면 로딩
	function ACLPortal(portalId) {
		luxor.popup("/ep/acl/aclSetupUser.do?selectAclType=DV&resourceID="+portalId+"&treeType=0", {width:850,height:600});
		return false;
	}

	function setDefaultPortal(portalId) {
		if(!confirm('<spring:message code="portal.alert.msg.155" text="기본 포탈로 지정하시겠습니까?"/>')) {
			return;
		}
		var params = 'portalId=' + portalId;
		callJson("groupPortalController", "setDefaultPortal", params, function(json) {
			if(json.messageName == 'success') {
				alert('<spring:message code="portal.alert.msg.152" text="변경되었습니다."/>');
				self.location.reload(true);
			} else {
				alert('<spring:message code="portal.alert.msg.153" text="오류가 발생했습니다.\n 로그를 확인해주십시오."/>');
			}
		});
	}
	
	function cancel() {
		$('#popupDiv').dialog('close');
	}

	function checkDup(alertOnlyDup) {
		if(!alertOnlyDup) {
			alertOnlyDup = false;
		}
		
		var result = false;
		if(luxor.isNullOrEmpty($('#portalId').val())) {
			alert('<spring:message code="portal.alert.msg.156" text="포탈 아이디를 입력하세요." />');
			return result;
		}
		if($('#portalId').val() == 'GROUP') {
			alert('GROUP을 포탈ID로 쓸 수  없습니다.');
			return result;
		}
		$.ajaxSetup({async:false});
		var param = "portalId="+$('#portalId').val();
		callJson("groupPortalController", "checkDuplication", param, function(data) {
			if(data==null) {
				if(alertOnlyDup==false) {
					alert('<spring:message code="portal.alert.msg.2" text="사용가능합니다." />');
				}
				result = true;
			} else {
				alert('<spring:message code="portal.groupportal.alert.2" text="이미 등록되어있거나, 잘못된 ID입니다.\n\n입력한 문자열이 다른 포탈ID에 수렴되면 안됩니다." />');
				result = false;
			}
			
			$.ajaxSetup({async:true});
		});

		return result;
	}
	
	function orgPopup(){
		luxor.popup("/ep/org/getOrgTree.do?userUid=<%=userUid%>&treeType=0&returnMethod=setRelatedCompInfo", {width:400,height:420});
		return false;
	}
	
	
	$(document).ready(function() {
		var param = $('#registerForm').serialize();
		callJson("groupPortalController", "getPortalHierarchy", param, function(data) {
			for(var i=0 ; i< data.length; i++) {
				callJson("groupPortalController", "setPortalHierarchy", "portalId="+data[i].portalId+"&childPortal="+data[i].childPortal, function(data) {
				});
			}
		});
		
		$('#popupDiv').dialog({modal:true, resizable:false,autoOpen:false, width:400, height:250, position:['center',50]});
<%	if("init".equals(param.getOption())) { %>
		register('');
<%	} %>

	});

</script>
</head>

<body>
<div id="alphabg"></div>
<div id="popupDiv" title="<spring:message code="portal.label.162" text="그룹 포탈 등록/수정"/>"></div>
<form id="listForm" name="listForm" method="post">
<!-- admin-content-width w1000 margin15-l -->
<div class="admin-content-width w1200 ml15" name="zone">
	 <!-- subtitle -->
	<div class="title-sub">
		<span class="title-sub-text"><spring:message code="portal.label.161" text="그룹 포탈 관리"/></span>
		<!-- button -->
		<div class="aright">
			<span class="main-btn"><a href="#" id="btnRegister" onclick="register('');return false;" title="<spring:message code="portal.btn.label.28" text="등록"/>"><spring:message code="portal.btn.label.28" text="등록"/></a></span><!--  등록버튼 -->
		</div>
	 	<!-- //button -->
	</div>

	<!-- content -->
	<div class="table-body-wrap">
		<table class="table-body" id="htmlGrid">
			<thead>
 				<tr>
					<th width="110"><spring:message code="portal.label.168" text="상위포탈" /></th>
					<th><spring:message code="portal.label.164" text="포탈 정보" /></th>
					<th width="110"><spring:message code="portal.label.165" text="연결 조직 정보" /></th>
					<th width="90"><spring:message code="portal.label.166" text="기본 포탈 여부" /></th>
					<th width="130"><spring:message code="portal.label.167" text="변경일" /></th>
					<th width="400"><spring:message code="portal.label.31" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
<%
	if(list.length > 0) {
		for(int i=0; i < list.length; i++) {
			GroupPortalVO vo = (GroupPortalVO)list[i];
			String relatedPortalinfo = vo.getRelatedPortalinfo();
			String checkRPP = "";
			String checkRPC = " *";
			if("P".equals(relatedPortalinfo)){
				checkRPP =" *";
				checkRPC = "";
			}
%>	
				<tr rowid="<%=vo.getPortalId()%>">
					<td><%=vo.getParentId()+checkRPP%></td>		
					<td><a href="#" onclick="register('<%=vo.getPortalId()%>');return false;"><%=vo.getPortalName()+"["+vo.getPortalId()+checkRPC+"]"%></a></td>
					<td><%=vo.getRelatedCompname()+"["+vo.getRelatedCompid()+"]"%></td>
					<td><%=DEFAULT_FLAG[vo.getDefaultFlag()]%></td>
					<td><%=vo.getLastUpdateToString()%></td>
					<td>
						<span class="smain-btn"><a href="#" id="btnEdit_<%=vo.getPortalId()%>" onclick="register('<%=vo.getPortalId()%>');return false;" title="<spring:message code="portal.btn.label.19" text="수정"/>"><spring:message code="portal.btn.label.19" text="수정"/></a></span>
						<span class="smain-btn"><a href="#" id="btnDelete_<%=vo.getPortalId()%>" onclick="deletePortal('<%=vo.getPortalId()%>','<%=CommonUtil.nullTrim(vo.getChildPortal())%>');return false;" title="<spring:message code="portal.btn.label.30" text="삭제"/>"><spring:message code="portal.btn.label.30" text="삭제"/></a></span>
						<span class="smain-btn"><a href="#" id="btnACL_<%=vo.getPortalId()%>" onclick="ACLPortal('<%=vo.getPortalId()%>');return false;" title="<spring:message code="portal.btn.label.38" text="권한 설정" />"><spring:message code="portal.btn.label.38" text="권한 설정" /></a></span>
						<span class="smain-btn"><a href="#" id="btnChange_<%=vo.getPortalId()%>" onclick="changePortal('<%=vo.getPortalId()%>');return false;" title="<spring:message code="portal.btn.label.116" text="관리포탈변경"/>"><spring:message code="portal.btn.label.116" text="관리포탈변경"/></a></span>
						<span class="smain-btn"><a href="#" id="btnDefault_<%=vo.getPortalId()%>" onclick="setDefaultPortal('<%=vo.getPortalId()%>');return false;" title="<spring:message code="portal.btn.label.117" text="기본포탈지정"/>"><spring:message code="portal.btn.label.117" text="기본포탈지정"/></a></span>
					</td>
				</tr>
<%	
		}
	} else {
%>
				<tr>
					<td colspan="9"><spring:message code="portal.alert.msg.113" text="조회된 데이타가 없습니다."/></td>
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
	<spring:message code="portal.page.label.17" text="* 상위포탈 지정시 상위포탈의 포틀릿을 사용할 수 있습니다."/>
</div>
</form>
</body>
</html>
