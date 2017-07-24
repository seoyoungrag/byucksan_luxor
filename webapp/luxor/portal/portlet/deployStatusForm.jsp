<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="org.anyframe.pagination.Page" %>
<%@ page import="com.sds.acube.luxor.common.vo.TreeVO" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.PortletConstant" %>
<%@page import="com.sds.acube.luxor.common.util.CommonUtil"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
	Page _page = (Page)request.getAttribute("_page");
	List<PortletManagementVO> list = (List<PortletManagementVO>)_page.getList();
	
	PortletManagementVO param = (PortletManagementVO)request.getAttribute("param");
	
	TreeVO[] tree = (TreeVO[])request.getAttribute("CategoryTree");
	String treeId = (String)request.getAttribute("treeId");
	
	
	int parentPortalCount = (Integer)request.getAttribute("parentPortalCount") == null ? 0 : (Integer)request.getAttribute("parentPortalCount");
	TreeVO[] parentTree = null;
	String parentTreeId = null;			
	GroupPortalVO groupPortal = null;
	String currentPortalName = CommonUtil.nullTrim((String)request.getAttribute("currentPortalName"));
	String isParentPoral = CommonUtil.nullTrim((String)request.getAttribute("isParentPortal"));
	
	TreeVO[] contentTree = (TreeVO[])request.getAttribute("contentTree");
	String contentTreeId = "PORTAL_CONTENT";
	
	TreeVO[] pageTree = (TreeVO[])request.getAttribute("pageTree");
	String pageTreeId = "PORTAL_PAGE";
	
	String directRegisterPortletId = CommonUtil.nullTrim((String)request.getAttribute("directRegisterPortletId"));
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="portal.label.104" text="포틀릿 관리"/></title>
<jsp:include page="/luxor/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/default_theme.css" />
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.form.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/content.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript">
	var parentPortalCount = <%=parentPortalCount%>;
	var isParentPortal = "<%=isParentPoral%>";
	var directRegisterPortletId = "<%=directRegisterPortletId%>";
	var _treeId = '<%=treeId%>';
	var searchDivId = 'tree_search'; // 검색사용시검색input, button을둘러싸고있는DIV ID
	var messageDivId = 'tree_message_form_div'; // 다국어사용시다국어입력폼부분을둘러싸고있는DIV ID

	var treeLoad = false;
	var currentNodeId = 'ROOT';

	//portletSearchKey
	var portletSearchKey = '<%=param.getSearchKey()%>';
	var portletSearchWay = '<%=param.getSearchWay()%>';
	
	// 콜백함수정의
	var selectCallback = function(nodeId) {
		// 노드선택시호출
		if(treeLoad==true) {
			if(nodeId == 'ROOT') nodeId = '';
			document.location.href = '/ep/portlet/deployStatusInfo.do?categoryId=' + nodeId+"&searchKey="+encodeURIComponent(portletSearchKey)+"&searchWay="+encodeURIComponent(portletSearchWay);
		}
		treeLoad = true;
	};

	function changePage(p) {
		if(currentNodeId == 'ROOT') { 
			currentNodeId = ''; 
		}
		if(isParentPortal != "") {
			document.location.href = "/ep/portlet/deployStatusInfo.do?parentPortalId="+isParentPortal+"&categoryId=" + currentNodeId+"&cPage="+p+"&searchKey="+encodeURIComponent(portletSearchKey)+"&searchWay="+encodeURIComponent(portletSearchWay);
		} else {
			document.location.href = "/ep/portlet/deployStatusInfo.do?categoryId=" + currentNodeId+"&cPage="+p+"&searchKey="+encodeURIComponent(portletSearchKey)+"&searchWay="+encodeURIComponent(portletSearchWay);
		}
	}
	
	function _showCategoryForm() {
		if(currentNodeId == 'ROOT') {
			luxor.popup('/ep/portlet/category.do', {width: 300, height:150});
		}else{
			alert('<spring:message code="portal.alert.msg.101" text="포틀릿 카테고리는 계층구조를 지원하지 않습니다."/>');
		}
	}

	function _createNode(id, name) {
		var t = $.tree.focused();
   		t.create( { attributes:{'id': id }, data:{'title' : name} } );
   		document.location.reload();
	}

	function _updateNode(id, name) {
		$('#' + id).html("<a href='#'><ins></ins>" + name + "</a>");
	}

	function _showModifyForm() {
		if(currentNodeId == 'ROOT') {
			alert('<spring:message code="portal.alert.msg.102" text="이 노드는 수정할 수 없습니다."/>');
		}else{
			luxor.popup('/ep/portlet/category.do?categoryId=' + currentNodeId, {width: 300, height:150});
		}
	} 

	function _deleteCategory() {
		if(currentNodeId == 'ROOT') {
			alert('<spring:message code="portal.alert.msg.103" text="이 노드는 삭제할 수 없습니다."/>');
		}else{
			if(<%=list.size()%> > 0) {
				alert('<spring:message code="portal.alert.msg.104" text="카테고리를 사용중인 포틀릿이 등록되어 있으므로 삭제할 수 없습니다."/>');
			}else{
				if(confirm('<spring:message code="portal.alert.msg.105" text="카테고리를 삭제하시겠습니까?"/>')) {
					var params = 'categoryId=' + currentNodeId;
					callJson("portletManagementController", "deletePortletCategory", params, function(json) {
						if(json.cPage > 0) _deleteFolder(currentNodeId, false);
						else alert('delete is failed');
					});
				}
			}
		}
	}
	
	$(document).ready(function() {
		$('#<%=treeId%>').tree({
			rules: {
				max_depth : 0
			},
	    	ui: {
				theme_name : 'classic'
			},
			types: {
				'default': {
					draggable : false
				},
			},
			callback: {
				// 트리 선택되었을때 호출
				onselect: function(node, tree_obj) {
					try {
						var parentId = tree_obj.parent(node).attr('id');
						selectCallback($(node).attr('id'), parentId);
					} catch(e) {
						selectCallback($(node).attr('id'));
					}
				},
				// 트리 검색시 호출
				onsearch : function (n,t) {
					t.container.find('.search').removeClass('search');
					n.addClass('search');
				},
				oncreate : function(node, parent, type, tree_obj, rollback) {
					try {
						insertCallBack($(node).attr('id'));
					} catch(e) {}
				},
				// 트리 삭제시 호출
				// Ajax로 삭제처리
				ondelete : function(node, tree_obj, rollback) {
					try {
						deleteCallBack(nodeId);
					} catch(e) {}
				}
			}
		});

		if('<%=param.getCategoryId()%>' != '') {
			currentNodeId = '<%=param.getCategoryId()%>';
			_selectNode('<%=param.getCategoryId()%>');
		} else {
			_selectNode('ROOT');
		}
	});
</script>
<script type="text/javascript">
	var addBtnFlag = true;
	var portletCategories = null;
	var typeOfPortlet = ['Generic', 'Iframe', 'Menu', 'Tab', 'Img'];
	var loginFlag = ['<spring:message code="portal.label.105" text="로그인 필요"/>', '<spring:message code="portal.label.106" text="로그인 불필요"/>'];

	function getDetailInfo(contextName) {
		var params = 'contextName=' + contextName;
		callJson("portletManagementController", "getPortletContextInfo", params, displayPortletInfo);
	}

	function displayPortletInfo(json) {
		var html = '';
		var scripts = '';
		var styles = '';
		
		$('#popupDiv1').dialog('close');
		$('#popupDiv2').dialog('close');
		$('#popupDiv3').dialog('close');
		$('#popupDiv4').dialog('close');
		
		for(var i=0;i<json.scripts.length;i++) {
			scripts += json.scripts[i] + '<br>\n';
		}
		for(var i=0;i<json.styles.length;i++) {
			styles += json.styles[i] + '<br>\n';
		}
		html += '<table class="table-write">\n';
		html += '<tr><th width="100"><spring:message code="portal.label.107" text="컨텍스트 명"/> </td><td>' + json.portletContextName + '</td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.108" text="제목"/> </td><td>' + json.title + '</td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.109" text="카테고리"/> </td><td>' + json.categoryName + '</td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.110" text="구분"/> </td><td>' + typeOfPortlet[json.typeOfPortlet] + '</td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.111" text="로그인"/> </td><td>' + loginFlag[json.loginFlag] + '</td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.112" text="SSO 정보"/> </td><td>' + json.ssoInfo + '</td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.121" text="View Url"/> </td><td><input type="text" value="' + json.viewUrl + '" style="width:100%;" /></td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.122" text="Edit Url"/> </td><td><input type="text" value="' + json.editUrl + '" style="width:100%;" /></td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.123" text="Help Url"/> </td><td><input type="text" value="' + json.helpUrl + '" style="width:100%;" /></td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.124" text="Go Url"/> </td><td><input type="text" value="' + json.goUrl + '" style="width:100%;" /></td></tr>\n';
		html += '<tr><th><spring:message code="portal.label.502" text="Img Url"/> </td><td><input type="text" value="' + json.imgUrl + '" style="width:100%;" /></td></tr>\n';

		if(json.typeOfPortlet != 1) {
			html += '<tr><th><spring:message code="portal.label.113" text="스크립트"/> </td><td>' + scripts + '</td></tr>\n';
			html += '<tr><th>CSS </td><td>' + styles + '</td></tr>\n';
		}
		html += '</table>\n';
		html += '<div class="admin-page-button-group">';
		html += '	<span class="main-btn"><a href="#" id="btnEdit" onclick="register(\'' + json.portletContextName + '\');return false;" title="<spring:message code="portal.btn.label.19" text="수정"/>"><spring:message code="portal.btn.label.19" text="수정"/></a></span>\n';// 수정 버튼
		html += '	<span class="main-btn"><a href="#" id="btnClose" onclick="$(\'#popupDiv2\').dialog(\'close\');return false;" title="<spring:message code="portal.btn.label.29" text="닫기"/>"><spring:message code="portal.btn.label.29" text="닫기"/></a></span>\n';// 닫기 버튼
		html += '</div>\n';
		$('#popupDiv2').html(html);
		$('#popupDiv2').dialog('open');
	}
	
	function register(contextName) {
		$('#popupDiv1').dialog('close');
		$('#popupDiv2').dialog('close');
		$('#popupDiv3').dialog('close');
		$('#popupDiv4').dialog('close');
		$.ajax({
			url : '/ep/portlet/loadRegisterForm.do', 
			data : '',
			dataType : 'html', 
			success : function(html) {
				
				$('#popupDiv').html(html);
				$('#tabs').tabs();
				$('#genericR').click(function(event) { // 일반 포틀릿 인 경우
					$('input[name*=Url]').attr('disabled', '');
					$('input[name=scriptRef]').attr('disabled', '');
					$('input[name=styleRef]').attr('disabled', '');
					addBtnFlag = true;
				});
				$('#iframeR').click(function(event) { // iframe 포틀릿인 경우
					$('input[name*=Url]').attr('disabled', '');
					$('input[name=scriptRef]').attr('disabled', 'disabled');
					$('input[name=styleRef]').attr('disabled', 'disabled');
					addBtnFlag = false;
				});
				$('#menuR').click(function(event) { // 메뉴 포틀릿인 경우
					$('input[name*=Url]').attr('disabled', '');
					$('input[name=scriptRef]').attr('disabled', '');
					$('input[name=styleRef]').attr('disabled', '');
					addBtnFlag = true;
				});
				
				$('#tabR').click(function(event) { // 탭 포틀릿인 경우
					$('input[name*=Url]').attr('disabled', '');
					$('input[name=scriptRef]').attr('disabled', '');
					$('input[name=styleRef]').attr('disabled', '');
					addBtnFlag = true;
				});
				
				$('#imgR').click(function(event) { // 메뉴 포틀릿인 경우
					$('input[name*=Url]').attr('disabled', '');
					$('input[name=scriptRef]').attr('disabled', '');
					$('input[name=styleRef]').attr('disabled', '');
					addBtnFlag = true;
				});				
				
				$('#selectCategory').bind('change', function(event) {
					if($(this).val() == 'add') {
						luxor.popup('/ep/portlet/category.do?callbackId=selectCategory', {width: 300, height:150});
						$(this).val('');
					}
				});

				if(contextName != '') {
					var params = 'contextName=' + contextName;
					callJson("portletManagementController", "getPortletContextInfo", params, function(json) {
						$('#contextName').val(json.portletContextName).attr('readonly' , true);
						$('#title').val(json.title);
						if(json.typeOfPortlet == 0) {
							$('#genericR').attr('checked', 'true');
						}else if(json.typeOfPortlet == 1) {
							$('#iframeR').attr('checked', 'true');
						}else if(json.typeOfPortlet == 2){
							$('#menuR').attr('checked', 'true');
						}else if(json.typeOfPortlet == 3){
							$('#tabR').attr('checked', 'true');
						}else if(json.typeOfPortlet == 4){
							$('#imgR').attr('checked', 'true');
						}
						
						if(json.loginFlag == 0) {
							$('#loginNeedR').attr('checked', 'true');
						}else{
							$('#loginFreeR').attr('checked', 'true');
						}
						if(json.ssoInfo=='D1') {
							$('#ssoInfo_d1').attr('checked','checked');
						}
						
						$('#selectCategory').val(json.categoryId);
						$('#outline').val(json.description);
						$('#viewUrl').val(json.viewUrl);
						$('#editUrl').val(json.editUrl);
						$('#helpUrl').val(json.helpUrl);
						$('#goUrl').val(json.goUrl);
						$('#imgUrl').val(json.imgUrl);

						for(var i=0;i<json.scripts.length;i++) {
							if(i != 0) {
								var html = '<br/><input type="text" name="scriptRef" id="script' + i + '" class="input-d" style="width:100%;" />';
								$('#scriptDiv').append(html);
							}
							$('#script' + i).val(json.scripts[i]);
						}
						
						for(var i=0;i<json.styles.length;i++) {
							if(i != 0) {
								var html = '<br/><input type="text" name="styleRef" id="style' + i + '" class="input-d" style="width:100%;" />';
								$('#styleDiv').append(html);
							}
							$('#style' + i).val(json.styles[i]);
						}
					});
				}
				
				$('#popupDiv').dialog('open');
			},
			error : function(e) {
				alert(e.message);
			}
		});
	}

	function registerXML() {
		$('#popupDiv1').dialog('close');
		$('#popupDiv2').dialog('close');
		$('#popupDiv3').dialog('close');
		$('#popupDiv4').dialog('close');
		$.ajax({
			url : '/ep/portlet/loadRegisterFormXML.do',
			data : '',
			dataType : 'html', 
			success : function(html) {
				$('#popupDiv3').html(html);
				$('#selectCategoryXML').bind('change', function(event) {
					if($(this).val() == 'add') {
						luxor.popup('/ep/portlet/category.do?callbackId=selectCategoryXML', {width: 300, height:150});
						$(this).val('');
					}
				});
				$('#popupDiv3').dialog('open');
			},
			error : function(e) {
				alert(e.message);
			}
		});
	}

	function redeployAll() {
		var params = '';
		if(!confirm('<spring:message code="portal.alert.msg.106" text="전부 redeploy 하시겠습니까?"/>')) {
			return;
		} 
		callJson("portletManagementController", "redeployAll", params, function(json) {
			if(json.exception != "") {
				alert(json.exception);
			}else{
				alert("Redeploy is complete!");
			}
			self.location.href = "/ep/portlet/deployStatusInfo.do";
		});
	}

	function redeploy(contextName) {
		var params = 'contextName=' + contextName;
		if(!confirm('<spring:message code="portal.alert.msg.107" text="redeploy 하시겠습니까?"/>')) {
			return;
		} 
		//luxor.loading(document.body, {type:'circle', size:'m', caption:"<div style='margin-top:10px;'>Loading...</div>"});
		callJson("portletManagementController", "redeploy", params, function(json) {
			//luxor.loadingClose($('#'+contentId+' > div.content_body'));
			alert('<spring:message code="portal.alert.msg.114" text="deploy 되었습니다."/>');
			self.location.href = "/ep/portlet/deployStatusInfo.do";
		});
	}

	function undeploy(contextName) {
		var params = 'contextName=' + contextName;
		if(!confirm('<spring:message code="portal.alert.msg.108" text="undeploy 하시겠습니까?"/>')) {
			return;
		} 
		//luxor.loading($('#'+contentId+' > div.content_body'), {type:'circle', size:'m', caption:"<div style='margin-top:10px;'>Loading...</div>"});
		callJson("portletManagementController", "undeploy", params, function(json) {
			//luxor.loadingClose($('#'+contentId+' > div.content_body'));
			alert('<spring:message code="portal.alert.msg.109" text="undeploy 되었습니다."/>');
			self.location.href = "/ep/portlet/deployStatusInfo.do";
		});
	}

	function modifyPortlet(contextName) {
		var params = 'contextName=' + contextName;
		callJson("portletManagementController", "getPortletContextInfo", params, function(json) {
			register(json.portletContextName);
		});
	}
	
	function deletePortlet(contextName) {
		var params = 'contextName=' + contextName;
		if(!confirm('<spring:message code="portal.alert.msg.110" text="삭제 하시겠습니까?"/>')) {
			return;
		} 
		//luxor.loading($('#'+contentId+' > div.content_body'), {type:'circle', size:'m', caption:"<div style='margin-top:10px;'>Loading...</div>"});
		callJson("portletManagementController", "delete", params, function(json) {
			//luxor.loadingClose($('#'+contentId+' > div.content_body'));
			alert('<spring:message code="portal.alert.msg.111" text="삭제 되었습니다."/>');
			self.location.href = "/ep/portlet/deployStatusInfo.do";
		});
	}

	function addScript() {
		var length = 0;
		var html = '';
		if(addBtnFlag) {
			length = document.registerForm["scriptRef"].length ? document.registerForm["scriptRef"].length : 1;
			html = '<br/><input type="text" name="scriptRef" id="script' + (length++) + '" class="input-d" style="width:100%;" />';
			//alert(html);
			$('#scriptDiv').append(html);
		}
	}

	function addStyle() {
		var length = 0;
		var html = '';
		if(addBtnFlag) {
			length = document.registerForm["styleRef"].length ? document.registerForm["styleRef"].length : 1;
			html = '<br/><input type="text" name="styleRef" id="style' + (length++) + '" class="input-d" style="width:100%;" />';
			$('#styleDiv').append(html);
		}
	}

	function apply() {
		if(luxor.isNullOrEmpty($('#selectCategory').val())) {
			alert("<spring:message code="portal.alert.msg.116" text="카테고리를 먼저 선택하세요."/>");
			return;
		}
		
		var param = 'contextName=' + $('#contextName').val();
		callJson('portletManagementController', 'checkDup', param, function(json){
			if(json.messageName == 'modify') {
				if(confirm('<spring:message code="portal.alert.msg.115" text="동일한 포틀릿 컨텍스트 명으로 기존에 등록된 정보가 있습니다. \\n정보를 덮어쓰시겠습니까?"/>')) {
					$('#registerForm').submit();
				}else{
					$('#contextName').focus();
					return;
				}
			} else if(json.messageName == 'success') {
				if(confirm('<spring:message code="portal.alert.msg.18" text="저장하시겠습니까?"/>')) {
					$('#registerForm').submit();
				}
			} else{
				alert('<spring:message code="portal.content.alert.12" text="다른 포탈에서 사용중인 컨텍스트명입니다." />');
			}
		});
	}
	
	function applyXML() {
		if(luxor.isNullOrEmpty($('#selectCategoryXML').val())) {
			alert("<spring:message code="portal.alert.msg.116" text="카테고리를 먼저 선택하세요."/>");
			return;
		}
		
		if(confirm('<spring:message code="portal.alert.msg.18" text="저장하시겠습니까?"/>')) {
			$('#popupDiv1').dialog('close');
			$('#popupDiv2').dialog('close');
			$('#popupDiv3').dialog('close');
			$('#popupDiv4').dialog('close');
			$('#registerXmlForm').ajaxSubmit({
				dataType : 'json',
				success : function(json) {
					var html = '';
					var scripts = '';
					var styles = '';

					html += '<div id="xmlTabs">\n';
					html += '<ul>\n';

					for(var k=0;k<json.length-1;k++) {
						html += '<li><a href="#tab_' + json[k].portletContextName + '" title="' + json[k].title + '">' + json[k].title + '</a></li>\n';
					}
					html += '</ul>\n';	
					for(var k=0;k<json.length-1;k++) {
						html += '<div id="tab_' + json[k].portletContextName + '">\n';
						scripts = '';
						for(var i=0;i<json[k].scripts.length;i++) {
							scripts += json[k].scripts[i] + '<br>\n';
						}
						styles = '';
						for(var i=0;i<json[k].styles.length;i++) {
							styles += json[k].styles[i] + '<br>\n';
						}
						html += '<table class="table-write">\n';
						html += '<tr><th width="100"><spring:message code="portal.label.107" text="컨텍스트 명"/> </td><td>' + json[k].portletContextName + '</td></tr>\n';
						html += '<tr><th><spring:message code="portal.label.108" text="제목"/> </td><td>' + json[k].title + '</td></tr>\n';
						html += '<tr><th><spring:message code="portal.label.109" text="카테고리"/> </td><td>' + $('#selectCategoryXML option:selected').html() + '</td></tr>\n';
						html += '<tr><th><spring:message code="portal.label.110" text="구분"/> </td><td>' + typeOfPortlet[json[k].typeOfPortlet] + '</td></tr>\n';
						html += '<tr><th><spring:message code="portal.label.111" text="로그인"/> </td><td>' + loginFlag[json[k].loginFlag] + '</td></tr>\n';
						html += '<tr><th><spring:message code="portal.label.121" text="View Url"/> </td><td><input type="text" class="input-d" name="viewUrl" style="width:100%;" readonly value="' + json[k].viewUrl + '"></td></tr>\n';
						html += '<tr><th><spring:message code="portal.label.122" text="Edit Url"/> </td><td><input type="text" class="input-d" name="editUrl" style="width:100%;" readonly value="' + json[k].editUrl + '"></td></tr>\n';
						html += '<tr><th><spring:message code="portal.label.123" text="Help Url"/> </td><td><input type="text" class="input-d" name="helpUrl" style="width:100%;" readonly value="' + json[k].helpUrl + '"></td></tr>\n';
						html += '<tr><th><spring:message code="portal.label.124" text="Go Url"/> </td><td><input type="text" class="input-d" name="goUrl" style="width:100%;" readonly value="' + json[k].goUrl + '"></td></tr>\n';
						if(json[k].typeOfPortlet != 1) {
							html += '<tr><th><spring:message code="portal.label.112" text="스크립트"/> </td><td>' + scripts + '</td></tr>\n';
							html += '<tr><th>CSS </td><td>' + styles + '</td></tr>\n';
						}
						html += '</table>\n';
						html += '</div>\n';
					}
					html += '<div><spring:message code="portal.alert.msg.112" text="이 포틀릿(들)을 등록하시겠습니까?"/></div>\n';
					html += '<div class="admin-page-button-group">';
					html += '	<span class="main-btn"><a href="#" id="btnEdit" onclick="applyXmlConfirm(\'' + json[json.length-1].portletContextName + '\', \'' + json[json.length-1].typeOfPortlet + '\', \'' + json[json.length-1].categoryId + '\');return false;"  title="<spring:message code="portal.btn.label.3" text="저장"/>"><spring:message code="portal.btn.label.3" text="저장"/></a></span>\n'; // 저장버튼
					html += '	<span class="main-btn"><a href="#" id="btnClose" onclick="$(\'#popupDiv4\').dialog(\'close\');return false;"  title="<spring:message code="portal.btn.label.29" text="닫기"/>"><spring:message code="portal.btn.label.29" text="닫기"/></a></span>\n';//닫기버튼
					html += '</div>\n';
					$('#popupDiv4').html(html);
					$('#xmlTabs').tabs();
					$('#popupDiv4').dialog('open');
				}
			});
		}
	}

	function applyXmlConfirm(filename, typeOfPortlet, categoryId) {
		var params = 'fileName=' + filename + '&typeOfPortlet=' + typeOfPortlet + '&categoryId=' + categoryId;
		var url = '/ep/portlet/portletRegisterXMLConfirm.do?' + params;
		self.location.href = url;
	}

	function applyXmlEdit(contextName) {
		var params = 'contextName=' + contextName + '&deployDescriptionXml=' + encodeURIComponent($('#txt_' + contextName).val());
		callJson("portletManagementController", "xmlEditApply", params, function(json) {
			alert('<spring:message code="portal.alert.msg.13" text="저장되었습니다."/>');
			$('#popupDiv2').dialog('close');
		});
	}

	function exportXML() {
		//alert('<spring:message code="portal.alert.msg.13" text="준비중입니다."/>');
		var params = $('#listForm').formSerialize();
		if($('input[name=checkbox]').fieldValue() == ''){
			alert('Please select portlets to export.');
			return;
		}
		alert('Category Information and Portlet Type will not be included in this XML.');
		$('#listForm').attr('target', 'hiddenFrame');
		$('#listForm').attr('action', '/ep/portlet/exportXML.do');
		$('#listForm').submit();
		
		//$('#hiddenFrame').attr('src','/ep/portlet/exportXML.do?' + params);
		//callJson('portletManagementController', 'exportXML', params, function(json){
		//	alert(json.messageName);
		//});
	}

	function editXML(contextName) {
		var html = '';
		var params = 'contextName=' + contextName;
		callJson("portletManagementController", "xmlEditForm", params, function(json) {
			html += '<table class="table-write">\n';
			html += '<tr><th width="100">XML </td><td><textarea id="txt_' + contextName + '" name="deployDescriptionXml" class="textarea-d" style="width:100%; height:280px;">' + json.deployDescriptionXml +  '</textarea></td></tr>\n';
			html += '</table>\n';
			html += '<div class="admin-page-button-group">';
			html += '	<span class="main-btn"><a href="#" id="btnXmlApply" onclick="applyXmlEdit(\'' + contextName + '\');return false;"  title="<spring:message code="portal.btn.label.3" text="저장"/>"><spring:message code="portal.btn.label.3" text="저장"/></a></span>\n';//저장
			html += '	<span class="main-btn"><a href="#" id="btnXmlClose" onclick="$(\'#popupDiv2\').dialog(\'close\');return false;"  title="<spring:message code="portal.btn.label.29" text="닫기"/>"><spring:message code="portal.btn.label.29" text="닫기"/></a></span>\n';//닫기
			html += '</div>\n';
			$('#popupDiv2').html(html);
			$('#popupDiv2').dialog('open');
		});
	}

	function cancel() {
		$('#popupDiv').dialog('close');
	}

	function resizeTreeHeight() {
		$('#resize_bar').height($(document).height()-20);
		$('#tree_wrap').height($(document).height()-20);
		$("#page_tree_accordion").attr('style','clear:both;padding:0px;visibility:visible;');
	}

	$(window).scroll(function() {
		resizeTreeHeight();
	});
	$(window).resize(function() {
		setTimeout("resizeTreeHeight()",400);
	});
	
	//drag시 previous treewidth
	var prevTreeWidth = '';
	
	$(document).ready(function() {
		$('#popupDiv').dialog({modal:true, resizable:false, autoOpen:false, width:600, height:380, position:['center',50]});
		$('#popupDiv2').dialog({modal:true, resizable:false, autoOpen:false, width:600, height:380, position:['center',50]});
		$('#popupDiv3').dialog({modal:true, resizable:false, autoOpen:false, width:600, height:170, position:['center',50]});
		$('#popupDiv4').dialog({modal:true, resizable:false, autoOpen:false, width:800, height:500, position:['center',50]});
		//callJson("portletManagementController", "getPortletCategoryList", '', function(json) {
		//	portletCategories = json;
		//});
		
		$('#ROOT').removeClass('closed').addClass('open');
		
		$('#checkboxAll').click(function(event) {
			if($('#checkboxAll').attr('checked')) {
				$('input[name=checkbox]').attr('checked', 'checked');
			}else{
				$('input[name=checkbox]').attr('checked', '');
			}
		});
		
		//$('tr[rowid]').click(function(event) {
		//	var checked = $('#checkbox_' + this.getAttribute('rowid')).attr('checked');
		//	$('#checkbox_' + this.getAttribute('rowid')).attr('checked', !checked);
		//});

		// 좌우 크기조정 드래그 바
		$('#resize_bar').draggable({
			axis: 'x',
			drag: function(e, ui) {
				var pos = $(this).offset();
				$('#tree_wrap').width(pos.left);
				$('#admin_wrap').width($('#admin_wrap').width()-prevTreeWidth+pos.left);
				prevTreeWidth=pos.left;
			},
			stop: function(e, ui) {
				$('#admin_wrap').width($('#tree_wrap').width()+$('#container').width()+50);
			}
		});

		setTimeout("resizeTreeHeight()",400);

		// 컨텐츠 관리 페이지 검색
		$('#input_portletManagerSearch').keypress(function(e) {
			if(e.keyCode==13) {
				$('#btn_portletManagerSearch').click();
			}
		});
		
		$('#btn_portletManagerSearch').click(function() {
			portletSearchKey = $('#input_portletManagerSearch').val();
			portletSearchWay = $('#select_portletManagerSearch').val();
			changePage(1);
			
			return false;
		});

		$('#select_portletManagerSearch option[value=<%=param.getSearchWay()%>]').attr('selected', 'selected');

		$( "#page_tree_accordion" ).accordion({
			autoHeight: true,
			navigation: true,
			fillspace: true,
			event: "click",
			minHeight:$(window).height()-(60+(20*parentPortalCount))
		});
		
		$(".ui-accordion-content").attr("style","padding:0px;border: 0px;");
		$(".ui-accordion-header").attr("style","padding:0px;border: 0px;");
		$('#tree_wrap #page_tree_accordion').height($(window).height()-(20*parentPortalCount));
		$(".ui-accordion-content").height($(window).height()-(60+(20*parentPortalCount)));
		
		// 부모 페이지 트리 초기화
		<%
		for(int index=0 ; index < parentPortalCount ;index++ ) { 
			parentTree = (TreeVO[])request.getAttribute("parentTree_"+index);
			parentTreeId = (String)request.getAttribute("parentTreeId_"+index);		
			groupPortal = (GroupPortalVO)request.getAttribute("groupPortal_"+index);
		%>
		$("#PARENT_<%=parentTreeId%>_<%=index%>").tree({
	    	ui: {
				theme_name : "classic"
			},
			types: {
				'default': {
					draggable : false
				}
			},
			callback: {
				//노드 선택시
				onselect: function(node, tree_obj) {
					var nodeId = $(node).attr("id");
					var parentPortalId = $(node).closest('ul').attr('name');
					if(treeLoad==true) {
						if($(node).attr("id").indexOf("_ROOT_") > -1) {
							 nodeId = '';
							 parentPortalId = $(node).attr('name');
						}
						document.location.href = '/ep/portlet/deployStatusInfo.do?parentPortalId='+parentPortalId+'&categoryId=' + nodeId+"&searchKey="+encodeURIComponent(portletSearchKey)+"&searchWay="+encodeURIComponent(portletSearchWay);
					}
					treeLoad = true;
				}
			}
		});

		$.tree.reference($('#PARENT_ROOT_<%=index%>')).open_branch('#PARENT_ROOT_<%=index%>');

		//검색
		$('.INPUT_ROOT_<%=index%>').keypress(function(e) {
            if(e.keyCode==13) {
            	$('.PARENT_<%=parentTreeId%>_<%=index%>').click();
            }
	    });
	    
		<% } %>

		// 부모 포탈 페이지 트리 검색 처리
        $('.parent-tree-search a').click(function(e) {
        	$.tree.reference($('#'+$(this).attr('class'))).search($('.parent-tree-search :text').val());
        	return false;
        });

        if(isParentPortal != "") {
			$('h3[name='+isParentPortal+'] a').click();
			$('#btnRegister').closest('.main-btn') .hide();
			$('#btnRegisterXML').closest('.main-btn') .hide();
			$('#btnRedeployAll').closest('.main-btn') .hide();
			$('a[id*=btnUndeploy]').closest('.smain-btn') .hide();
			$('a[id*=btnModify]').closest('.smain-btn') .hide();
			$('a[id*=btnDelete]').closest('.smain-btn') .hide();
		}

      	//컨텐츠 등록시 사용되는 함수
		// 팝업 셋팅
		$('#content_form_div').dialog({
			autoOpen:false,
			resizable:false,
			modal:true,
			width:830,
			position:['center',50],
			close:function(event, ui) { // 팝업창 닫을때 초기화
				$('#styleManager').dialog('close');
				$('#portletList > li').css('background','#FFFFFF');
				$('#portletId').val('');
				$('#input_portletSelectorSearch').val('');
			},
			resize:function(event, ui) {
			}
		});
		
		// 저장 버튼 클릭시
		$('#btn_form_save').click(function() {
			var method = mode +'Content';
			$('#parentId').val($('#PORTAL_CONTENT').attr('name'));
			var param = $('#content_form').serialize();
			selectedNodeId = $('#parentId').val();
			callJson('portalContentController', method, param, function(data) {
				if(eval(data)) {
					alert('<spring:message code="portal.alert.msg.13" />');
				}
				
				// reset field
				cMessage.reset();
				$('textarea').val('');
				$('#btn_form_cancel').click();
			});
			
			return false;
		});

		
		// 취소 버튼 클릭시
		$('#btn_form_cancel').click(function() {
			$('#content_form_div').dialog('close');
			$('#content_preview > ul > li').remove();
			content.style.cancel();
			
			return false;
		});


		//컨텐츠 트리
		$("#CONTENT_ROOT").tree({
	    	ui: {
				theme_name : "classic"
			},
			types: {
				'default': {
					draggable : false
				}
			},
			callback: {
				//노드 선택시
				onselect: function(node, tree_obj) {
					$(node).closest('#PORTAL_CONTENT').attr('name',$(node).attr('id'));
				}
			}
		});

		//페이지 트리
		$("#PAGE_ROOT").tree({
	    	ui: {
				theme_name : "classic"
			},
			types: {
				'default': {
					draggable : false
				}
			},
			callback: {
				//노드 선택시
				onselect: function(node, tree_obj) {
					
				}
			}
		});
		$("#CONTENT_ROOT li[rel=first] a").click();

		if(directRegisterPortletId != "") {
			setTimeout(function() {
				if(confirm('<spring:message code="portal.content.alert.11" text="적용된 포틀릿을 컨텐츠로  등록하시겠습니까?"/>')) {
				$('#btnDirectRegister_'+directRegisterPortletId).click();
				}
			},1500);
		}
	});
	
	//컨텐츠 등록시 사용되는 함수
	var selectedNodeId = "ROOT";
	var mode = 'insert'
	var selectedContentId = 'PREVIEW_001';
	var currentPageId = "";
	var currentMenuId = "";
	// default theme로 보여줌
	var themeImageUrl = "/ep/luxor/ref/image/blue_theme";
	function directRegister(contextName, titleName, typeOfPortlet) {
		mode = 'insert';
		$('#content_form_div').dialog({title:'<spring:message code="portal.content.label.0" text="컨텐츠 바로등록" />'});
		$('#content_form_div').dialog('open');
		
		$('#portletId').val(contextName);
		var _data = [{"contentId":selectedContentId,"portletId":contextName,"messageName":titleName,"zoneId":"content_preview","contentStyle":"","contentStyleForEach":""}];
		var data = eval(_data);
		loadPreview(data, typeOfPortlet); // 미리보기 로딩
		
		// 제목 다국어 셋팅
		if($.browser.msie==true) {
			//동적 페이지 파싱하는데  딜레이 걸리는 IE만 좀 있다가 값 세팅
			setTimeout(function() {
				setContentMessage(titleName);
			}, 500);
		} else {
			setContentMessage(titleName);
		}
		return false;
	}
	/**
	 * 다국어 입력창에서 확인 버튼 클릭시 호출되는 콜백함수
	 */
	function _setMessageCallBack(message_input, message_input_all) {
		content.setTitle(selectedContentId, message_input);
		$('#content_form #inserted__messageId').val($('#c__messageId').val());
		$('#content_form #inserted__message_input_all').val(cMessage.getMessageNameAll());
		$('#content_form #inserted__messageType').val($('#c__messageType').val());
	}


	function setContentMessage(titleName) {
		$('#c__message_input').val(titleName);
		$('#c__message_multi_input :text').val(titleName);
		cMessage.save();
	}
	
	/**
	 * 컨텐츠 미리보기 로딩
	 */
	function loadPreview(data, typeOfPortlet) {
		$('#content_preview > ul').html('');
		if($('#block_layer').attr('src') == null) {
			$('#content_preview').closest('.list-box').prepend('<iframe id="block_layer" frameborder="0" allowTransparency="true" src="/ep/luxor/common/jsProxy/transparent.html" style="height: 50%;width: 50%;position: absolute;top: 125px;"></iframe>');
		}
		
		$.getJSON('/ep/luxor/common/jsProxy/checkPortletUrlInfo.jsp?portletId='+data[0].portletId+'&cacheTime='+new Date(), function(json) {
			var menuArr = ['reload','min','max','setup'];
			if(json.edit) menuArr.push('edit');
			if(json.help) menuArr.push('help');
			content.setMenuList(menuArr);
			content.setPreview(false);
			content.load(data);
			if(typeOfPortlet == 2) {
				$('#preview_text').html("<spring:message code="portal.content.alert.10" text=" * 메뉴 설정은 컨텐츠 관리, 페이지관리모드에서 입력할 수 있습니다." />");
			} else {
				$('#preview_text').html("");
			}
		});
	}

</script>
</head>

<body>
	<div id="alphabg"></div>
	<div id="popupDiv" title="<spring:message code="portal.label.114" text="포틀릿 등록/수정"/>"></div>
	<div id="popupDiv2" title="<spring:message code="portal.label.115" text="포틀릿 등록정보"/>"></div>
	<div id="popupDiv3" title="<spring:message code="portal.label.116" text="포틀릿 등록(XML)"/>"></div>
	<div id="popupDiv4" title="<spring:message code="portal.label.116" text="포틀릿 등록(XML) 확인"/>"></div>
<div id="window_scroll_wrap">
	<div id="admin_wrap" class="admin-wrap">
		<div id="tree_wrap" class="tree-wrap">
			<div class="page-tree-wrap" id="page_tree_accordion" style="clear:both;padding:0px;visibility:hidden;">
				<h3><a href="#" id="main_page_tree"><%=currentPortalName %></a></h3>
				<div>
					<%-- 폴더 추가/수정/삭제 버튼 --%>
					<div class="admin-tree-btn-group">
						<a href="#" onclick="_showCategoryForm();return false;"><img src="/ep/luxor/ref/image/admin/icon_06.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.5" text="폴더추가"/>" title="<spring:message code="portal.btn.label.5" text="폴더추가"/>" /></a>
						<a href="#" onclick="_showModifyForm();return false;"><img src="/ep/luxor/ref/image/admin/icon_07.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.6" text="폴더수정"/>" title="<spring:message code="portal.btn.label.6" text="폴더수정"/>" /></a>
						<a href="#" onclick="_deleteCategory();return false;"><img src="/ep/luxor/ref/image/admin/icon_08.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.7" text="폴더삭제"/>" title="<spring:message code="portal.btn.label.7" text="폴더삭제"/>" /></a>
					</div>
					<%-- 폴더 추가/수정/삭제 버튼 --%>
				
					<%-- 트리 검색 --%>
					<div id="tree_search" class="admin-tree-btn-group">
						<input type="text" class="input-txtfield w100" style="WIDTH: 100px;" />
						<span class="main-btn"><span class="search"></span><a href="#" title="<spring:message code="portal.btn.label.8" text="검색"/>"><spring:message code="portal.btn.label.8" text="검색"/></a></span>
					</div>
					<%-- 트리 검색 --%>
			
					<%-- 트리 --%>
					<div id="<%=treeId%>" class="admin-tree-padding">
					    <ul>
					    	<li id="ROOT" class="open"><a href='#'><ins></ins><spring:message code="portal.label.117" text="전체"/></a>
							<%
								int prevDepth = 0;
								for(int i=0; i < tree.length; i++) {
									String nodeId = tree[i].getNodeId();
									String nodeName = tree[i].getNodeName();
									String nodeNameId = tree[i].getNodeNameId();
									String parentId = tree[i].getParentId();
									int depth = tree[i].getDepth();
									int seq = tree[i].getSeq();
									boolean hasChild = tree[i].getHasChild().equals("Y");
									
									String _class = "";
									if(nodeId.equals("root")) {
										_class = "class='open'";
									}
									
									if(depth > prevDepth) {
										out.println("<ul>");
									}
									if(prevDepth > depth) {
										out.println("</ul></li>");
									}
									out.println("<li id='"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' "+_class+"><a href='#'><ins></ins>"+nodeName+"</a>");
									if(!hasChild) {
										out.println("</li>");
									}
									prevDepth = depth;
								}
							%>
							</li>
					    </ul>
					</div>
				</div>
				<%-- 트리 --%>
				<%
				for(int j=0 ; j < parentPortalCount ;j++ ) { 
					parentTree = (TreeVO[])request.getAttribute("parentTree_"+j);
					parentTreeId = (String)request.getAttribute("parentTreeId_"+j);		
					groupPortal = (GroupPortalVO)request.getAttribute("groupPortal_"+j);
				%>
				<h3 name="<%=groupPortal.getPortalId() %>"><a href="#"><%=groupPortal.getPortalName() %></a></h3>
				<div>
					<%-- 트리 검색 --%>
					<div class="admin-tree-btn-group parent-tree-search">
						<input type="text" class="input-txtfield w100 INPUT_ROOT_<%=j%>" />
						<span class="main-btn"><span class="search"></span><a href="#" class="PARENT_<%=parentTreeId%>_<%=j%>"><spring:message code="portal.btn.label.8" text="검색" /></a></span>		
					</div>
					<%-- 트리 검색 --%>
			
					<%-- 왼쪽 페이지 트리 --%>
					<div id="PARENT_<%=parentTreeId%>_<%=j%>" class="page_tree admin-tree-padding" style="margin-right:15px;">
					    <ul>
					    	<li id="PARENT_ROOT_<%=j%>" class="open" name="<%=groupPortal.getPortalId()%>">
					    		<a href='#'><ins></ins><spring:message code="portal.label.117" text="전체"/></a>
					    		<span id="total_page_count" style="color:#990000;font-size:11px;">(<%=parentTree.length%>)</span>
							<%
								prevDepth = 0;
								for(int i=0 ; i < parentTree.length; i++) {
									String nodeId = parentTree[i].getNodeId();
									String nodeName = parentTree[i].getNodeName();
									String nodeNameId = parentTree[i].getNodeNameId();
									String parentId = parentTree[i].getParentId();
									int depth = parentTree[i].getDepth();
									int seq = parentTree[i].getSeq();
									boolean hasChild = parentTree[i].getHasChild().equals("Y");
	
									String _class = "";
									if(nodeId.equals("root")) {
										_class = "class='open'";
									}
									
									if(depth > prevDepth) {
										out.println("<ul name="+groupPortal.getPortalId()+">");
									}
									if(prevDepth > depth) {
										out.println("</ul></li>");
									}
									out.println("<li id='"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' "+_class+"><a href='#'><ins></ins>"+nodeName+"</a>");
									if(!hasChild) {
										out.println("</li>");
									}
									prevDepth = depth; 
								}
							%>
							</li>
					    </ul>
					</div>
					<%-- 트리 --%>
				</div>
				<% } %>
			</div>
		</div>
		
		<%-- 왼쪽(트리), 오른쪽(페이지) 크기 리사이즈해주는 바 --%>
		<div id="resize_bar" class="resize-bar"></div>
		
		<form id="listForm" name="listForm" method="post">
		<!-- admin-content-width w1000 margin15-l -->
		<div id="container" class="admin-content-width w1000 ml15">
			 <!-- subtitle -->
			<div class="title-sub">
				<span class="title-sub-text"><spring:message code="portal.label.104" text="포틀릿 관리"/></span>
				<!-- button -->
				<div class="aright">
					<span class="main-btn"><a href="#" id="btnRegister" onclick="register('');return false;"  title="<spring:message code="portal.btn.label.28" text="등록"/>"><spring:message code="portal.btn.label.28" text="등록"/></a></span>
					<span class="main-btn"><a href="#" id="btnRegisterXML" onclick="registerXML();return false;" title="<spring:message code="portal.btn.label.101" text="XML 등록"/>"><spring:message code="portal.btn.label.101" text="XML 등록"/></a></span>
					<span class="main-btn"><a href="#" id="btnExportXML" onclick="exportXML();return false;" title="<spring:message code="portal.btn.label.102" text="XML Export"/>"><spring:message code="portal.btn.label.102" text="XML Export"/></a></span>
					<span class="main-btn"><a href="#" id="btnRedeployAll" onclick="redeployAll();return false;" title="<spring:message code="portal.btn.label.103" text="Redeploy All"/>"><spring:message code="portal.btn.label.103" text="Redeploy All"/></a></span>
				</div>
			 	<!-- //button -->
			</div>
			<!-- table_write -->
			<table class="table-search">
			<thead>
				<tr>
					<th width="90"><spring:message code="portal.label.21" text="검색어" /></th>
					<td colspan="3">
	   					<select id="select_portletManagerSearch" style="width:12%">
	   						<option value='displayName'>portletName</option>
	     					<option value='contextName'><spring:message code="portal.label.107" text="컨텍스트 명"/></option>
	   					</select>
						<input type="text" class="input-d" value="<%=param.getSearchKey() %>" id="input_portletManagerSearch" style="WIDTH: 87%;" />
					</td>
					<td width="62" rowspan="2">
						<span class="smain-btn">
							<a href="#" id="btn_portletManagerSearch"><span class="btnicon-01"></span><spring:message code="portal.btn.label.8" text="검색" /></a>
						</span>  
					</td>
				</tr>
			</thead>
			</table>
			<!-- //table_write -->     
			<!-- content -->
			<div class="table-body-wrap">
				<table class="table-body" id="htmlGrid">
					<thead>
		 				<tr>
		 					<th width="20px"><input name="checkboxAll" type="checkbox" id="checkboxAll" /></th>
							<th width="50px"><spring:message code="portal.label.118" text="구분"/></th>
							<th width="90px"><spring:message code="portal.label.109" text="카테고리"/></th>
							<th width="150px"><spring:message code="portal.label.107" text="컨텍스트 명"/></th>
							<th width="150px">portletName</th>
							<th width="70px"><spring:message code="portal.label.119" text="상태"/></th>
							<th width="120px"><spring:message code="portal.label.120" text="변경일"/></th>
							<th width="*"><spring:message code="portal.label.31" text="기능" /></th>
						</tr>
					</thead>
					<tbody>
	<%	
		if(list.size() > 0) {
			for(int i=0; i < list.size(); i++) {
				PortletManagementVO vo = (PortletManagementVO)list.get(i);
	%>	
						<tr>
							<td id="check_box"><input name="checkbox" type="checkbox" id="checkbox_<%=vo.getContextName()%>" value="<%=vo.getContextName()%>" /></td>
							<td><%=PortletConstant.TYPE_OF_PORTLET[vo.getTypeOfPortlet()]%></td>
							<td><%=vo.getCategoryName()%></td>
							<td class="body-left"><a href="#" onclick="getDetailInfo('<%=vo.getContextName()%>');return false;"><%=vo.getContextName()%></a></td>
							<td class="body-left"><%=vo.getTitle()%></td>
							<td><%=PortletConstant.PORTLET_STATUS[vo.getStatusCode()]%></td>
							<td><%=vo.getLastUpdateToString()%></td>
							<td>
								<span class="smain-btn"><a href="#" title="<spring:message code="portal.content.label.12" text="포틀릿을 컨텐츠로  바로 등록" />" id="btnDirectRegister_<%=vo.getContextName()%>" onclick="directRegister('<%=vo.getContextName()%>','<%=vo.getTitle() %>','<%=vo.getTypeOfPortlet() %>');return false;"><spring:message code="portal.content.label.2" text="바로등록" /></a></span>					
								<span class="smain-btn"><a href="#" id="btnModify_<%=vo.getContextName()%>" onclick="modifyPortlet('<%=vo.getContextName()%>');return false;"><spring:message code="portal.btn.label.19" text="수정" /></a></span>
								<span class="smain-btn"><a href="#" id="btnDelete_<%=vo.getContextName()%>" onclick="deletePortlet('<%=vo.getContextName()%>');return false;"><spring:message code="portal.btn.label.30" text="삭제" /></a></span>
								<span class="smain-btn"><a href="#" id="btnXML_<%=vo.getContextName()%>" onclick="editXML('<%=vo.getContextName()%>');return false;">XML</a></span>
	<%
				if(vo.getStatusCode() == 1) {
	%>
								<span class="smain-btn"><a href="#" id="btnUndeploy_<%=vo.getContextName()%>" onclick="undeploy('<%=vo.getContextName()%>');return false;">Undeploy</a></span>
	<%
				}else{
	%>
								<span class="smain-btn"><a href="#" id="btnRedeploy_<%=vo.getContextName()%>" onclick="redeploy('<%=vo.getContextName()%>');return false;">Redeploy</a></span>
	<%
				}
	%>
							</td>
						</tr>
	<%	
			}
		}else{
	%>
						<tr>
							<td colspan="9"><spring:message code="portal.alert.msg.113" text="조회된 데이타가 없습니다."/></td>
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
	</form>
	</div>
</div>
<iframe id="hiddenFrame" name="hiddenFrame" style="width:800px;height:200px;display:none;border:solid #222222 1px;" src="about:blank" ></iframe>

<%-- 컨텐츠 추가 Hidden Form --%>
<div id="content_form_div" title="<spring:message code="portal.btn.label.22" text="컨텐츠 추가" />">
	<form id="content_form">
	<input type="hidden" id="contentStyle" name="contentStyle" />
	<input type="hidden" id="portletId" name="portletId" />
	<input type="hidden" id="parentId" name="parentId" />
	<input type="hidden" id="inserted__messageId" name="messageId" />
	<input type="hidden" id="inserted__message_input_all" name="messageNameAll" />
	<input type="hidden" id="inserted__messageType" name="messageType" />
	<div class="box-font-blue" style="padding:5px;">
		- <%=currentPortalName %> <spring:message code="portal.page.label.21" text="포탈의 컨텐츠로 등록합니다."/>
   	</div>
	<table class="table-write">
		<colgroup><col width="130px" /><col width="*" /></colgroup>
		<tbody>
		<tr>
			<th>포틀릿 선택</th>
			<td>
				<div id="portlet-wrap mt10">
					<div>
						<table border="0" width="100%">
							<colgroup><col width="200px" /><col width="20px" /><col width="*" /></colgroup>
							<tr> 
								<td class="list-box" style="padding-top:10px;">
									<span class="admin-sub-title mb10"><spring:message code="portal.content.label.10" text="컨텐츠카테고리" /></span>
									<div id="tree_wrap" class="tree-wrap" style="height:290px;">
										<div class="page-tree-wrap">
											<%-- 트리 --%>
											<div id="<%=contentTreeId%>" class="admin-tree-padding">
											    <ul>
											    	<li id="CONTENT_ROOT" class="open"><a href='#'><ins></ins>ROOT</a>
													<%
														prevDepth = 0;
														for(int i=0; i < contentTree.length; i++) {
															String nodeId = contentTree[i].getNodeId();
															String nodeName = contentTree[i].getNodeName();
															String nodeNameId = contentTree[i].getNodeNameId();
															String parentId = contentTree[i].getParentId();
															String usePersonal = contentTree[i].getUsePersonal();
															int depth = contentTree[i].getDepth();
															int seq = contentTree[i].getSeq();
															boolean hasChild = contentTree[i].getHasChild().equals("Y");
															
															String _class = "";
															if(nodeId.equals("root")) {
																//_class = "class='open'";
															}
															
															if(depth > prevDepth) {
																out.println("<ul>");
															}
															
															if(prevDepth > depth) {
																for(int j=0; j < (prevDepth - depth); j++) {
																	out.println("</ul></li>");
																}
															}
															
															if(i==0) {
																out.println("<li id='"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' "+_class+" rel='first'><a href='#'><ins></ins>"+nodeName+"</a>");
															} else {
																out.println("<li id='"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' "+_class+"><a href='#'><ins></ins>"+nodeName+"</a>");
															}
															if(!hasChild) {
																out.println("</li>");
															}
															
															prevDepth = depth;
														}
													%>
													</li>
											    </ul>
											</div>
											<%-- 트리 --%>
										</div>
									</div>
								</td>
								<td style="border:none">&nbsp;</td>
								<td class="list-box" style="padding-top:10px;">
									<div class="admin-sub-title mb10"><spring:message code="portal.label.3" text="미리보기" /></div>
									<% String userAgent = request.getHeader("USER-AGENT");
									if(userAgent.contains("MSIE")) { %>
										<iframe id="block_layer" frameborder="0" allowTransparency="true" src="/ep/luxor/common/jsProxy/transparent.html"></iframe>
									<% } %>
									<div id="content_preview" style="margin-top:10px;height:290px;overflow:auto;"><ul type="unfixed"></ul></div>
									<span id="preview_text"></span>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>설명</th>
			<td><textarea id="contentDesc" name="contentDesc" class="text" style="width:99%;height:100px;"></textarea></td>
		</tr>
		<tr>
			<th colspan="2" align="center" style="background-color: white;">
				<span class="main-btn"><a href="#" id="btn_form_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span>
				<span class="main-btn"><a href="#" id="btn_form_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span>
			</th>
		</tr>
		</tbody>
	</table>
	</form>
</div>
<%-- 컨텐츠 추가 Hidden Form --%>
<%-- 스타일 매니저 --%>
<%@ include file="/luxor/portal/content/contentStyleManager.jsp" %>
<%-- 스타일 매니저 --%>
</body>
</html>
