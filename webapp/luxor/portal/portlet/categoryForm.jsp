<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.PortletCategoryVO" %>

<%
	PortletCategoryVO category = (PortletCategoryVO)request.getAttribute("category");
	String callbackId = UtilRequest.getString(request, "callbackId");
	if(category == null) {
		category = new PortletCategoryVO();
		category.setCategoryId("");
		category.setCategoryName("");
	}
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.101" text="포틀릿 카테고리 등록/수정"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	function apply() {
		//var messageNameAll = Message.getMessageNameAll();
		//var messageType = Message.getMessageType();
		//var messageId = Message.getMessageId();
		// 위두변수값을submit할때같이넘겨줘야함
		// 파라미터이름도위의변수와같이
		// ex) '&messageNameAll='+Message.getMessageNameAll()+'&messageType='+Message.getMessageType();
		var params = $('#categoryForm').serialize();
		//alert(params);
		callJson("portletManagementController", "saveCategory", params, function(json) {
			if(opener) {
				if(opener.document.getElementById('<%=callbackId%>')) {
					var sel = opener.document.getElementById('<%=callbackId%>');
					var l = sel.options.length;
					//alert(sel.options[l-1].value + '/' + sel.options[l-1].text);
					sel.options.length = sel.options.length + 1;
					//sel.options[l] = new Option(sel.options[l-1].text, sel.options[l-1].value);
					sel.options[l].value = sel.options[l-1].value;
					sel.options[l].text = sel.options[l-1].text;
					//sel.options[l-1] = new Option(json.categoryName, json.categoryId);
					sel.options[l-1].value = json.categoryId;
					sel.options[l-1].text = json.categoryName;
					sel.value = json.categoryId;
				}else{
					if('<%=category.getCategoryId()%>' != '') {// 수정
						opener._updateNode(json.categoryId, json.categoryName);
					}else{// 등록
						opener._createNode(json.categoryId, json.categoryName);
					}
				}
			}else{
				alert('???');
			}
			window.close();
		});
	}

	$(document).ready(function() {
		if('<%=category.getCategoryId()%>' != '') {
			Message.setMessageId("<%=category.getMessageId()%>");
			$('#categoryId').attr('readonly', 'readonly');
			$('#categoryId').val('<%=category.getCategoryId()%>');
			//$('#categoryName').val('<%=category.getCategoryName()%>');
		}
	});
</script>
</head>

<body>
<form id="categoryForm" name="categoryForm" action="/ep/portlet/portletRegisterConfirm.do" method="post">
<div class="admin-content-pop" style="width:90%;">
	<!-- subtitle -->
	<div class="title-sub">
		<span class="title-sub-text"><spring:message code="portal.label.101" text="포틀릿 카테고리 등록/수정"/></span>
	</div> 
	<!-- //subtitle -->
	<div class="tb-write"> 
		<table class="table-write">
			<tr>
				<th width="100"><spring:message code="portal.label.102" text="카테고리 아이디"/></th>
				<td><input type="text" name="categoryId" id="categoryId" class="input-d" style="width:100%;" /></td>
			</tr>
			<tr>
				<th><spring:message code="portal.label.103" text="카테고리 명"/></th>
				<td>
					<!-- <input type="text" name="categoryName" id="categoryName" class="input-d" style="width:100%;" /> -->
					<jsp:include page="/luxor/common/message.jsp">
						<jsp:param name="input_css_class" value="input-d"/>
						<jsp:param name="message_type" value="PORTAL.PORTLET_CATEGORY"/>
					</jsp:include>
				</td>
			</tr>  
		</table>
	</div> 
	<div class="aright">
		<span class="main-btn"><a href="#" id="btn_add" onclick="apply();return false;" title="<spring:message code="portal.btn.label.3" text="등록"/>"><spring:message code="portal.btn.label.3" text="등록"/></a></span>&nbsp;<!-- 등록버튼 -->
		<span class="main-btn"><a href="#" id="btn_close" onclick="self.close();return false;" title="<spring:message code="portal.btn.label.29" text="닫기"/>"><spring:message code="portal.btn.label.29" text="닫기"/></a></span><!-- 닫기 -->
	</div> 
</div>

</form>
</body>
</html>

