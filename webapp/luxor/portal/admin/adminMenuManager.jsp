<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.AdminMenuVO" %>

<%
	int cPage = UtilRequest.getInt(request, "cPage", 1);
%>

<html lang="ko">
<head>
<title></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	
	var mode = 'insert';
	var admin_label_5 = '<spring:message code="admin.label.5" text="메뉴 등록" />';

    function changePage(p) {
        document.location.href = "/ep/admin/manager.do?cPage="+p;
    }
	
	$(document).ready(function() {
		$('#div_menuList').load('/ep/admin/getAdminMenuList.do?cPage=<%=cPage%>');

		// dialog 초기화
		$('#category_manager_div').dialog({
			modal:true, 
			resizable:false,
			autoOpen:false, 
			width:450, 
			height:350, 
			position:['center',50],
			close:function() {
				parent.document.location.reload();
			}
		});
		
		$('#menu_form_div').dialog({
			modal:true, 
			resizable:false,
			autoOpen:false, 
			width:450, 
			height:200, 
			position:['center',50],
			close:function() {
				$('#category').html('');
			}
		});
		
		// 카테고리 관리 클릭시
		$('#btn_category_manager').click(function() {
			cMessage.reset();
			$('#category_list').load('/ep/admin/getAdminMenuCategoryList.do');
			$('#category_manager_div').dialog({title:'<spring:message code="portal.btn.label.35" text="카테고리 관리" />'});
			$('#category_manager_div').dialog('open');
			return false;
		});


		// 카테고리 관리 -> 저장 버튼 클릭시
		$('#btn_category_save').click(function() {
			var method = mode+"AdminMenu";
			var param = $('#category_form').serialize();
			callJson("adminController", method, param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />")
		        } else {
		            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
		        }

		        cMessage.reset();
		        $('#category_list').load('/ep/admin/getAdminMenuCategoryList.do');
			}); 
			return false;
		});

		// 카테고리 관리 -> 목록에서 삭제 버튼 클릭시
		$('#category_list a[mode=del]').live('click', function() {
			if(!confirm("<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?" />")) {
				return false;
			}
			
			var param = "menuId="+$(this).attr('menuId')+"&messageId="+$(this).attr('menuNameId');
			callJson("adminController", "deleteAdminMenu", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />")
		        } else {
			        if(data=='HAS_CHILD') {
			        	alert("<spring:message code="portal.alert.msg.23" text="하위 메뉴가 존재합니다. 하위 메뉴 삭제 후 삭제해주세요." />");
			        } else {
		            	alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
			        }
		        }

		        cMessage.reset();
		        $('#category_list').load('/ep/admin/getAdminMenuCategoryList.do');
			}); 
			
			return false;
		});
		
		// 카테고리 관리 -> 목록에서 수정 버튼 클릭시
		$('#category_list a[mode=mod]').live('click', function() {
			mode = "update";
			$('#menuId').val($(this).attr('menuId'));
			cMessage.setMessageId($(this).attr('menuNameId'));
			return false;
		});

		// 메뉴 등록 클릭시
		$('#menuRegist').click(function() {
			// 카테고리 정보 가져옴
			callJson('adminController','getAdminMenuCategories','',function(data) {
				$('#category').append("<option value='ROOT'>ROOT</option>");
				for(var i=0; i < data.length; i++) {
					$('#category').append("<option value='"+data[i].menuId+"'>"+data[i].messageName+"</option>");
				}
			});
			
			mMessage.reset();
			mode = 'insert';
			
			$('#menu_form_div').dialog({title:admin_label_5});
			$('#menu_form_div').dialog('open');
			$('#menuUrl').val('');
			return false;
		});

		// 메뉴 등록 -> 저장 버튼 클릭시
		$('#btn_form_save').click(function() {
			if(luxor.isNullOrEmpty($('#menuUrl').val())) {
				alert("메뉴 URL을 입력하세요.");
				return;
			}
			
			var method = mode +'AdminMenu';
			var param = $('#menu_form').serialize();
			if(mode=='update') {
				param += "&menuId="+$('#menuId').val();
			}

			callJson('adminController', method, param, function(data) {
				if(eval(data)) {
					alert('<spring:message code="portal.alert.msg.13" text="저장되었습니다."/>');
					parent.document.location.reload();
				}
			});
			return false;
		});

		// 메뉴목록 -> 홈으로 설정 버튼 클릭시
		$('#div_menuList a[mode=setHome]').live('click', function() {
			if(confirm('<spring:message code="portal.alert.msg.22" text="홈으로 설정하시겠습니까?" />')) {
				var menuId = $(this).attr('menuId');
				callJson("adminController", "setHome", "menuId="+menuId, function(data) {
					alert('<spring:message code="portal.alert.msg.16" text="적용되었습니다." />');
					$('#div_menuList').load('/ep/admin/getAdminMenuList.do');
				});
			}
			return false;
		});
		
		// 메뉴목록 -> 수정 버튼 클릭시
		$('#div_menuList a[mode=mod]').live('click', function() {
			mode = 'update';
			$('#menuId').val($(this).attr('menuId'));
			$('#seq').val($(this).attr('seq'));
			var param = 'menuId='+$(this).attr('menuId');
			callJson('adminController','getAdminMenu',param,function(data) {
				// 카테고리 정보 가져옴
				callJson('adminController','getAdminMenuCategories','',function(cdata) {
					$('#category').append("<option value='ROOT'>ROOT</option>");
					for(var i=0; i < cdata.length; i++) {
						var selected = cdata[i].menuId==data.parentId ? "selected" : "";
						$('#category').append("<option value='"+cdata[i].menuId+"' "+selected+">"+cdata[i].messageName+"</option>");
					}
				});
				
				mMessage.setMessageId(data.menuNameId);
				$('#menuUrl').val(data.menuUrl);
			});

			$('#menu_form_div').dialog({title:'<spring:message code="portal.btn.label.44" text="메뉴 수정" />'});
			$('#menu_form_div').dialog('open');
			return false;
		});

		
		// 메뉴목록 -> 삭제 버튼 클릭시
		$('#div_menuList a[mode=del]').live('click', function() {
			$('#menu_form_div').dialog('close');
			if(confirm('<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?"/>')) {
				var menuId = $(this).attr('menuId');
				var menuNameId = $(this).attr('menuNameId');
				callJson("adminController", "deleteAdminMenu", "menuId="+menuId+"&messageId="+menuNameId, function(data) {
					alert('<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />');
					parent.document.location.reload();
				});
			}
			return false;
		});

	});
</script>
</head>

<body>

<div class="admin-wrap">
	<!-- container02-->
    <div class="admin-content-width w1200 ml15">
	    <!-- subtitle -->
        <div class="title-sub">
            <span class="title-sub-text"><spring:message code="admin.label.1" /></span>
        	<!--  등록 버튼 -->
            <div class="aright">
            	<span class="main-btn"><a href="#" id="btn_category_manager" title="<spring:message code="portal.btn.label.35"/>"><spring:message code="portal.btn.label.35"/></a></span>
	        	<span class="main-btn"><a href="#" id="menuRegist" title="<spring:message code="portal.btn.label.28"/>"><spring:message code="portal.btn.label.28"/></a></span>
        	</div>
    	</div>
     	<!-- //subtitle -->
    	<div id="div_menuList"></div>
	</div>
	<!-- //container02-->
</div>

<%-- 카테고리 등록폼 --%>
<div id="category_manager_div">
	<form id="category_form">
	<input type="hidden" id="menuId" name="menuId" />
	<input type="hidden" name="parentId" value="CATEGORY" />
		<table class="table-write" width="100%">
			<tr>
				<td><spring:message code="portal.label.103" text="카테고리 명" /></td>
				<td><jsp:include page="/luxor/common/message.jsp"><jsp:param name="namespace" value="c" /></jsp:include></td>
				<td><span class="main-btn"><a href="#" id="btn_category_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span></td>
			</tr>
		</table>
	</form>
	<div id="category_list"></div>
</div>

<%-- 메뉴 등록폼 --%>
<div id="menu_form_div">
	<form id="menu_form">
		<table class="table-write">
			<colgroup><col width="80px" style="background:#c0c0c0" /><col width="*" /></colgroup>
			<tr>
				<th><spring:message code="portal.label.109" text="카테고리" /></th>
				<td colspan="2"><select id="category" name="parentId"></select></td>
			</tr>
			<tr>
				<th><spring:message code="admin.label.2" text="메뉴명" /></th>
				<td colspan="2"><jsp:include page="/luxor/common/message.jsp"><jsp:param name="namespace" value="m" /></jsp:include></td>
			</tr>
			<tr>
				<th><spring:message code="admin.label.4" text="메뉴URL" /></th>
				<td colspan="2"><input type="text" class="input-d" id="menuUrl" name="menuUrl" value="" style="WIDTH:99%;" /></td>
			</tr>
			<tr>
				<th><spring:message code="portal.page.label.16" text="sequence" /></th>
				<td colspan="2"><input type="text" class="input-d" id="seq" name="seq" value="" style="WIDTH:99%;" /></td>
			</tr>
		</table>
		<!-- button -->
		<div class="aright">
			<span class="main-btn"><a href="#" id="btn_form_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span>
		</div>
	</form>
</div>

<div id="alphabg"></div>

</body>
</html>