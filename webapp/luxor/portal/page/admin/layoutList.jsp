<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	Page _page = (Page)request.getAttribute("page");
	List list = (List)_page.getList();
	PortalPageLayoutVO[] layouts = new PortalPageLayoutVO[list.size()];
	list.toArray(layouts);
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.label.50" text="페이지 레이아웃 관리" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#layout_form_div').dialog({
			autoOpen:false,
			resizable:false,
			modal:true,
			width:620,
			position:['center',50],
			close:function(event, ui) { // 팝업창 닫을때 초기화
				$('#layout_form input, #layout_form textarea').each(function() { $(this).val(''); });
			}
		});
		
		// 레이아웃 추가 버튼 클릭시
		$('#btn_layout_add').click(function() {
			$('#layoutId').val('');
			$('#actionMode').val('');
			$('#layout_form_div').dialog('open');
		});

		$('#btn_layout_cancel').click(function() {
			$('#layout_form_div').dialog('close');
		});

		/* 수정/삭제 버튼 클릭시 */
		$('table a').click(function() {
			if($(this).attr('mode')=='mod') {
				var param = "layoutId="+$(this).attr('layoutId');
				callJson("portalPageLayoutController", "getPageLayout", param, function(data) {
					Message.setMessageId(data.layoutNameId);
					$('#layoutId').val(data.layoutId);
					$('#layoutCss').val(data.layoutCss);
					$('#actionMode').val('mod');
				});
				$('#layout_form_div').dialog({title:'<spring:message code="portal.label.49" text="레이아웃 수정" />'});
				$('#layout_form_div').dialog('open');
				return false;
			}
			else if($(this).attr('mode')=='setDefault') {
				if(!confirm("<spring:message code="portal.alert.msg.38" text="기본 레이아웃으로 설정하겠습니까?" />")) {
					return false;
				}
				var param = "layoutId="+$(this).attr('layoutId')+"&isDefault=Y";
				callJson("portalPageLayoutController", "setDefaultPageLayout", param, function(data) {
			        if(data._errorCode=='-9999') {
			        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />")
			        } else {
			            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
			        }
			        document.location.reload();
			        return false;
				});
			} 
			else if($(this).attr('mode')=='del') {
				if(!confirm("<spring:message code="portal.alert.msg.110" text="삭제하시겠습니까?" />")) {
					return false;
				}

				var param = "layoutId="+$(this).attr('layoutId')+"&messageId="+$(this).attr('messageId');
				callJson("portalPageLayoutController", "deletePageLayout", param, function(data) {
			        if(data._errorCode=='-9999') {
			        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			        } else {
			            alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
			        }
			        top.location.reload();
				});
			}
		});
		
		$('#btn_layout_save').click(function() {
			if(luxor.isNullOrEmpty(Message.getMessageNameAll())) {
				alert("<spring:message code="portal.alert.msg.43" text="레이아웃명을 입력하세요." />");
				return false;
			}
			if(luxor.isNullOrEmpty($('#layoutCss').val())) {
				alert("<spring:message code="portal.alert.msg.44" text="레이아웃 CSS를 입력하세요." />");
				$('#layoutCss').focus();
				return false;
			}
			
			var method = $('#actionMode').val()=='mod' ? 'updatePageLayout' : 'insertPageLayout';
			postJson("portalPageLayoutController", method, $('#layout_form').serialize(), function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
		        }
		        top.location.reload();
			});
		});
	});

	function changePage(p) {
		document.location.href = "/ep/page/layout/list.do?cPage="+p;
	}
</script>
</head>

<body>

<div class="admin-wrap">
	<div class="admin-content-width w1200 ml15" name="zone">
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="portal.label.50" text="페이지 레이아웃 관리" /></span>
			<div class="aright">
				<span class="main-btn"><a href="#" id="btn_layout_add"><spring:message code="portal.btn.label.28" text="등록" /></a></span>
			</div>
		</div>
		<div class="table-body-wrap">
			<table class="table-body">
			<thead>
				<tr>
					<th width="180px"><spring:message code="portal.label.51" text="레이아웃 ID" /></th>
					<th width="*"><spring:message code="portal.label.52" text="레이아웃명" /></th>
					<th width="110px"><spring:message code="portal.label.53" text="기본 레이아웃" /></th>
					<th width="150px"><spring:message code="portal.label.54" text="수정일" /></th>
					<th width="180px"><spring:message code="portal.label.39" text="기능" /></th>
				</tr>
			</thead>
			<tbody>
	<%
		for(PortalPageLayoutVO layout : layouts) {
			String layoutName = layout.getMessageName();
			String layoutNameId = layout.getLayoutNameId();
			String layoutCss = layout.getLayoutCss();
			String regDate = layout.getRegDate2String();
			String modDate = layout.getModDate2String();
			String layoutId = layout.getLayoutId();
			String isDefault = layout.getIsDefault();
			if(!"Y".equals(isDefault)) {
				isDefault = "N";
			}
			String trClass = "";
			if("Y".equals(isDefault)) {
				trClass = "class='hover'";
			}
	%>
					<tr <%=trClass%>>
						<td><%=layoutId%></td>
						<td><%=layoutName%></td>
						<td><%=isDefault%></td>
						<td><%=modDate%></td>
						<td>
							<div class="acenter">
								<span class="smain-btn"><a href="#" mode="setDefault" layoutId="<%=layoutId%>" messageId="<%=layoutNameId%>"><spring:message code="portal.btn.label.45" text="기본으로" /></a></span>
								<span class="smain-btn"><a href="#" mode="mod" layoutId="<%=layoutId%>" messageId="<%=layoutNameId%>"><spring:message code="portal.btn.label.19" text="수정" /></a></span>
								<span class="smain-btn"><a href="#" mode="del" layoutId="<%=layoutId%>" messageId="<%=layoutNameId%>"><spring:message code="portal.btn.label.30" text="삭제" /></a></span>
							</div>
						</td>
					</tr>
	<%		
		}
	%>
				</tbody>
			</table>
		</div>
		<!-- 페이징 -->
		<jsp:include page="/luxor/common/paging.jsp">
			<jsp:param name="cPage" value="<%=_page.getCurrentPage()%>" />
			<jsp:param name="totalCount" value="<%=_page.getTotalCount()%>" />
		</jsp:include>
		<!-- //페이징 -->
	</div>
</div>
	
<div id="layout_form_div" title="<spring:message code="portal.label.55" text="레이아웃 등록" />">
	<div class="admin-content-pop">
		<div class="tb-write">
			<form id="layout_form">
			<input type="hidden" id="layoutId" name="layoutId" />
			<input type="hidden" id="actionMode" />
			<table class="table-write">
				<tr>
					<th width="100"><spring:message code="portal.label.52" text="레이아웃명" /></th>
					<td><jsp:include page="/luxor/common/message.jsp" /></td>
				</tr>
				<tr>
					<th><spring:message code="portal.label.56" text="레이아웃 CSS" /></th>
					<td><textarea id="layoutCss" name="layoutCss" class="textarea-d" style="width:99%;height:300px;"></textarea></td>
				</tr>
			</table>
			</form>
		</div>
		<div class="aright"> 
			<span class="main-btn"><a href="#" id="btn_layout_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span> 
			<span class="main-btn"><a href="#" id="btn_layout_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span> 
		</div>
	</div>
</div>

<div id="hidden_div" class="hidden"></div>

</body>
</html>
