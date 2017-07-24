
<%
	/* 메뉴설정 팝업창 */
%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%@ page import="com.sds.acube.luxor.common.vo.*"%>
<%@ page import="com.sds.acube.luxor.common.util.*"%>
<%@page import="com.sds.acube.luxor.portal.vo.GroupPortalVO"%>

<%
	TreeVO[] tree = (TreeVO[]) request.getAttribute("tree");
	String treeId = (String) request.getAttribute("treeId");

	String pageId = UtilRequest.getString(request, "pageId");
	String contentId = UtilRequest.getString(request, "contentId");
	String zoneId = UtilRequest.getString(request, "zoneId");
	String currentPortalId = CommonUtil.nullTrim((String) session
			.getAttribute("PORTAL_ID"));
	String currentPortalName = CommonUtil.nullTrim((String) request
			.getAttribute("currentPortalName"));
	TreeVO[] parentTree = null;
	String parentTreeId = null;
	GroupPortalVO groupPortal = null;
	int parentPortalCount = (Integer) request
			.getAttribute("parentPortalCount") == null
			? 0
			: (Integer) request.getAttribute("parentPortalCount");
	String isParentPoral = CommonUtil.nullTrim((String) request
			.getAttribute("isParentPortal"));
%>

<!DOCTYPE HTML>


<html lang="ko">
<head>
<title><spring:message code="portal.label.18" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript"
	src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/content.js"></script>
<script type="text/javascript">
	var currentPortalId = "<%=currentPortalId%>";
	var isParentPortal = "<%=isParentPoral%>";
	var parentPortalCount = <%=parentPortalCount%>;
	var _treeId = "<%=treeId%>";
	var _pageId = "<%=pageId%>";
	var searchDivId = "tree_search";
	tree_draggable = false;
	var portal_page_label_6 = "<spring:message code="portal.page.label.6" text="BODY" />";
	var portal_content_label_6 = "<spring:message code="portal.content.label.6" text="좌측" />";
	var portal_content_label_7 = "<spring:message code="portal.content.label.7" text="중앙" />";
	var portal_content_label_8 = "<spring:message code="portal.content.label.8" text="우측" />";
	
	// 현재 선택된 노드
	var selectedNodeId = 'ROOT';
	
	function insertRow(nodeId, parentId, pageId, targetZone, portalId, displayName, displayNameId) {
		var _targetDIV = parentId=='ROOT' ? '#selected_page' : '#sp_'+parentId;
		var strDisplayNameId = displayNameId == null ? 'display_none' : displayNameId;
		var strPageType = "";
		var strTargetZone = "";
		var isParentPortal = "";
		if(luxor.isNullOrEmpty(pageId)) {
			pageId = '<%=pageId%>';
		}
		if(pageId == 'ALL' || pageId == 'ROOT') {
			strPageType = '<spring:message code="portal.page.label.13" text="전체적용" />';
		} else {
			strPageType = '<spring:message code="portal.page.label.12" text="현재페이지적용" />';
		}
		
		var strZoneName = "";
		if(targetZone == "body") {
			strZoneName = portal_page_label_6;
		} else if(targetZone == "left") {
			strZoneName = portal_content_label_6;
		} else if(targetZone == "content") {
			strZoneName = portal_content_label_7;
		} else if(targetZone == "right") {
			strZoneName = portal_content_label_8;
		} 

		var strDisplayName = "";
		if(displayName != null && displayName != '') {
			strDisplayName = "[<spring:message code="portal.page.label.3" text="메뉴명" /> : "+displayName+"]";
		}
		
		if(targetZone != "" && targetZone != null) {
			strTargetZone = '[<spring:message code="portal.page.label.11" text="타겟" />:'+strZoneName+']';
		}
			//자기가 선언한 메뉴가 아닌 경우
		if(currentPortalId != portalId && portalId != null) {
			$(_targetDIV).append("<div id='sp_"+nodeId+"' portalId='"+portalId+"' parentId='"+parentId+"' displayNameId='"+strDisplayNameId+"' pageId='"+pageId+"' class='sp_row' isFixed='Y'>"
					+"<table><tr><td class='sp_row_text'><span class='plusminus'>-</span> "+_getNodeName(nodeId)+" "+strDisplayName+"["+strPageType+"]"+strTargetZone+"</td>"
					+"<td class='sp_row_conf'>"
					+"<span style='color:red;'>[<spring:message code="portal.page.label.10" text="상위포탈에서 설정" />]</span>"
					+"</td></tr></table></div>");
			//컨텐츠관리에서 호출한 경우
		} else if(_pageId == "ROOT") {
			$(_targetDIV).append("<div id='sp_"+nodeId+"' portalId='"+portalId+"' parentId='"+parentId+"' displayNameId='"+strDisplayNameId+"' pageId='"+pageId+"' class='sp_row'>"
					+"<table><tr><td class='sp_row_text'><span class='plusminus'>-</span> "+_getNodeName(nodeId)+" "+strDisplayName+"["+strPageType+"]"+strTargetZone+"</td>"
					+"<td class='sp_row_conf'>"
					+"<a href='#none' mode='setDisplay'><spring:message code="portal.page.label.9" text="메뉴명설정" /></a> | "
					+"<a href='#none' mode='setup'><spring:message code="portal.btn.label.43" text="설정" /></a> | "
					+"<a href='#none' mode='del'><spring:message code="portal.btn.label.30" text="삭제" /></a>"
					+"</td></tr></table></div>");
			//그외 일반적인 경우
		} else {
			$(_targetDIV).append("<div id='sp_"+nodeId+"' portalId='"+portalId+"' parentId='"+parentId+"' displayNameId='"+strDisplayNameId+"' pageId='"+pageId+"' class='sp_row'>"
					+"<table><tr><td class='sp_row_text'><span class='plusminus'>-</span> "+_getNodeName(nodeId)+" "+strDisplayName+"["+strPageType+"]"+strTargetZone+"</td>"
					+"<td class='sp_row_conf'>"
					+"<a href='#none' mode='setDisplay'><spring:message code="portal.page.label.9" text="메뉴명설정" /></a> | "
					+"<a href='#none' mode='setup'><spring:message code="portal.btn.label.43" text="설정" /></a> | "
					+"<a href='#none' mode='targetZoneSetup'><spring:message code="portal.page.label.8" text="호출영역선택" /></a> | "
					+"<a href='#none' mode='del'><spring:message code="portal.btn.label.30" text="삭제" /></a>"
					+"</td></tr></table></div>");
		}
		

		// make selected page list sortable
		$(_targetDIV).sortable({
			items: 'div[parentId='+parentId+']',
			cancel: 'div[isFixed=Y]',
			// 드래그하여 순서조정을 한 경우 Ajax 호출해서 DB저장
			update: function(event, ui) {
				var arr = $(_targetDIV).sortable('toArray');
				for(var j=0; j < arr.length; j++) {
					if(luxor.isNullOrEmpty(arr[j])) {
						continue;
					}
					var _menuId = arr[j].substring(3);
					var _portalId = $('#'+arr[j]).attr('portalId');
					if(_portalId == null) {
						_portalId = currentPortalId;
					}
					var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+_menuId+"&seq="+j+"&portalId="+_portalId;
					luxor.log(param);
					callJson("portalMenuController", "updateMenuSeq", param);
				}
			}
		});
	}

	// 이전에 선택된 메뉴를 불러옴
	function loadSelectedMenu() {
		var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>";
		callJson("portalMenuController", "getMenusOnZone", param, function(data) {
			for(var i=0; i < data.length; i++) {
				var _nodeId = data[i].menuId;
				var _parentId = data[i].parentId;
				var _portalId = data[i].portalId;
				var _displayName = data[i].displayName;
				var _displayNameId = data[i].displayNameId;
				insertRow(_nodeId, _parentId, data[i].pageId, data[i].targetZone, _portalId, _displayName, _displayNameId);
			}
		});
	}
	
	// 트리에서 노드 선택시
	var selectCallback = function(nodeId, parentId) {
		if(nodeId=='ROOT') {
			return false;
		}
		if(luxor.isNullOrEmpty(parentId)) {
			parentId = 'ROOT';
		}
		
		// 페이지 트리의 중간 depth를 바로 추가할때 parentId를 ROOT로 변경
		if(luxor.isNullOrEmpty($('#sp_'+parentId).text())) {
			parentId = "ROOT";
		}

		selectedNodeId = nodeId;
		// 중복체크
		if(luxor.isNullOrEmpty($('#sp_'+nodeId).text())) {
			// DB 저장[default:해당컨텐츠가 존재하는 모든 페이지에 같은메뉴 구엇], pageId를 주면 개별구성
			var param = "pageId=ALL&contentId=<%=contentId%>&zoneId=<%=zoneId%>&parentId="+parentId+"&menuId="+nodeId;
			callJson("portalMenuController", "insertMenu", param, function(data) {
				if(eval(data)) {
					insertRow(nodeId, parentId, 'ALL');
				}
				if(_pageId == "ROOT") {
					var updateParam = "pageId=ALL&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+nodeId;
					callJson("portalMenuController", "updateMenuToAll", updateParam, function(data) {
					});
				}
			});
		} 
	};

	// 트리에서 하위노드 선택시
	var selectListCallback = function(nodeId, parentId) {
		if(nodeId=='ROOT') {
			return false;
		}
		if(luxor.isNullOrEmpty(parentId)) {
			parentId = 'ROOT';
		}
		selectedNodeId = nodeId;
		// 중복체크
		if(luxor.isNullOrEmpty($('#sp_'+nodeId).text())) {
			// DB 저장
			var param = "pageId=ALL&contentId=<%=contentId%>&zoneId=<%=zoneId%>&parentId="+parentId+"&menuId="+nodeId;
			callJson("portalMenuController", "insertMenu", param, function(data) {
				if(eval(data)) {
					insertRow(nodeId, parentId, 'ALL');
				}
				//default 입력되는 상태를 [전체적용]으로 함
				if(_pageId == "ROOT") {
					var updateParam = "pageId=ALL&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+nodeId;
					callJson("portalMenuController", "updateMenuToAll", updateParam, function(data) {
					});
				}
				
			});
			
		} 
	};

	function resizeTreeHeight() {
		$('#resize_bar').height($(document).height()-45);
	}

	$(window).scroll(function() {
		resizeTreeHeight();
	});
	
	$(window).resize(function() {
		resizeTreeHeight();
	});
	
	var sum = 0;
	var prevTreeWidth = 0;
	$(document).ready(function() {
		// load initial data
		loadSelectedMenu();
		
		$('a').live('focus', function() {
			$(this).blur();
		});
		
		$('#setupMenu').dialog({
			autoOpen:false,
			resizable:false,
			modal:true,
			width:400,
			height:80
		});

		$('#targetZoneSetupMenu').dialog({
			autoOpen:false,
			resizable:false,
			modal:true,
			width:550,
			height:80
		});

		$('#display_message_form_div').dialog({
			resizable:false,
			autoOpen:false,
			width:320,
			height:90
		});

		// Init Javascript Dialog
		$('#javascriptDialog').dialog({
			autoOpen:false,
			resizable:false,
			modal:true,
			width:550,
			height:330,
			close:function() {
				$('#javascript').val('');
			}
		});

		// 좌우 크기조정 드래그 바
		$('#resize_bar').draggable({
			axis: 'x',
			drag: function(e, ui) {
				var pos = $(this).offset();
				$('#tree_wrap').width(pos.left);
				$('#page_wrap').width($('#page_wrap').width()-prevTreeWidth+pos.left);
				prevTreeWidth=pos.left;
			},
			stop: function(e, ui) {
				$('#page_wrap').width($('#tree_wrap').width()+$('#selected_page_wrap').width()+50);
			}
		});
		
		// 선택된 페이지 클릭시 폴더 열고 닫기
		$('div.sp_row').live('click', function(e) {
			e.stopPropagation();
			if($(this).children('div.sp_row').is(':visible')) {
				$(this).children('div.sp_row').hide();
				$(this).find('span.plusminus').first().text('+');
			} else {
				$(this).children('div.sp_row').show();
				$(this).find('span.plusminus').first().text('-');
			}
		});
		
		// 모든 페이지에 적용 클릭시
		$('div[id] a[mode=add_all]').live('click', function() {
			if(confirm("<spring:message code="portal.alert.msg.17" text="컨텐츠가 추가된 모든 페이지에서 이 메뉴가 보여집니다. 계속하시겠습니까?" />")) {
				var _menuId = $('#setupMenu').attr('selectedMenuId');
				var param = "pageId=ALL&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+_menuId;
				var aObj = this;
				callJson("portalMenuController", "updateMenuToAll", param, function(data) {
					alert("<spring:message code="portal.alert.msg.16" text="적용되었습니다." />");
					$(aObj).text('<spring:message code="portal.label.19" text="모든 페이지 적용취소" />');
					$(aObj).last().attr('mode','add_all_cancel');
					if(opener.setTargetedImageIcon != null) {
						opener.setTargetedImageIcon();
					}
					location.reload();
				});
			}
		});

		// 모든 페이지에 적용 취소 클릭시
		$('div[id] a[mode=add_all_cancel]').live('click', function() {
			if(_pageId == 'ROOT') {
				alert("<spring:message code="portal.alert.msg.79" text="개별 페이지 적용은 페이지빌더에서만 가능합니다." />");
				return;
			}
			if(confirm("<spring:message code="portal.page.alert.1" text="개별 페이지 적용시 영역 설정 및 다른 페이지에서 해당 메뉴는 사라집니다. 계속하시겠습니까?" />")) {
				var _menuId = $('#setupMenu').attr('selectedMenuId');
				var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+_menuId+"&targetZone=null";
				var aObj = this;
				callJson("portalMenuController", "updateMenuToAll", param, function(data) {
					alert("<spring:message code="portal.alert.msg.16"  text="적용되었습니다." />");
					$(aObj).text('<spring:message code="portal.label.20" text="모든 페이지 적용" />');
					$(aObj).last().attr('mode','add_all');
					if(opener.setTargetedImageIcon != null) {
						opener.setTargetedImageIcon();
					}
					location.reload();
				});
			}
		});
		
		// 하위포함 클릭시
		$('div[id] a[mode=add_child]').live('click', function() {
			var _tnodeId = $('#setupMenu').attr('selectedMenuId');
			
			var i = 1;
			var totalCount = 0;
			$('#'+_tnodeId+' li').each(function() {
				totalCount++;
			});

			// 5개 이상 추가시에만 진행바 표시
			if(totalCount > 5) {
				luxor.dialog($('#progressbarWrap'));
			}
			
			$('#setupMenu').dialog('close');

			$('#'+_tnodeId+' li').each(function() {
				var _nodeId = $(this).attr('id');
				var _parentId = $(this).attr('parentid');
				var _nodeName = _getNodeName(_nodeId);
				
				selectListCallback(_nodeId, _parentId);
				
				if(totalCount > 10) {
					var percentage = ((i++) * 100) / totalCount;
					$('#progressbar').progressbar({value:percentage});
					$('#progressbarCaption').text(i+"/"+totalCount);
				}
			});

			$('#progressbar').progressbar({value:0});
			$('#progressbarCaption').text("");
			luxor.dialogClose($('#progressbarWrap'));
		});

		// 같은 레벨포함 클릭시
		$('div[id] a[mode=add_sibling]').live('click', function() {
			var _tnodeId = $('#setupMenu').attr('selectedMenuId');
			$('#setupMenu').dialog('close');
			$('#'+_tnodeId).siblings().each(function() {
				var _nodeId = $(this).attr('id');
				var _parentId = $('#'+_nodeId).attr('parentId');
				selectCallback(_nodeId,_parentId);
			});
		});

		// 타겟 존 클릭시
		$('#targetZoneSetupMenu a').live('click', function() {
			var _menuId = $('#targetZoneSetupMenu').attr('selectedMenuId');
			var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+_menuId+"&targetZone="+$(this).attr('mode');
			var aObj = this;
			callJson("portalMenuController", "updateMenu", param, function(data) {
				alert("<spring:message code="portal.alert.msg.16" text="적용되었습니다." />");
				if(opener.setTargetedImageIcon != null) {
					opener.setTargetedImageIcon();
				}
				location.reload();
			});
		});

		// 메뉴명설정 클릭시
		$('div[id] a[mode=setDisplay]').live('click', function(e) {
			var displayNameId = $(this).closest('div[id]').attr('displayNameId');
			var selectedConfPage = $(this).closest('div[id]').attr('id');
			var _menuId = selectedConfPage.substring(3);
			var mode = "insert";
			if(displayNameId != 'display_none') {
				mode = "update";
			}
			if(mode == 'update') {
				Message.setMessageId(displayNameId);
			}
			$('#display_message_form_div').attr('selectedMenuId',_menuId);
			$('#display_message_form_div').attr('displayNameId', displayNameId);
			$('#display_message_form_div').attr('mode', mode);
			$('#display_message_form_div').dialog({position:[e.pageX-330,e.pageY+10-$(document).scrollTop()]});
			$('#display_message_form_div').dialog('open');
		});

		
		// Javascript 셋팅 클릭
		$('div[id] a[mode=set_js]').live('click', function() {
			var spId = $('#setupMenu').attr('selectedMenuId');
			
			// 기존에 저장된 javascript 가져옴
			var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+spId;
			callJson("portalMenuController", "getMenu", param, function(data) {
				//하위메뉴 연결에 대한 추가사항 -------------
				$('#childCnt').val(data.childCnt);
				$('#childId').val(data.childId);
				if(data.childCnt == "0"){
					$('#directChildMenu').hide();
				}else{
					$('#directChildMenu').show();
				}
				//-------------------------------------
				$('#javascript').val(data.script);
				$('#javascriptDialog').dialog('open');
			});
		});

		// URL 열기 스크립트 추가
		$('#js_gen_link').click(function() {
			var spId = $('#setupMenu').attr('selectedMenuId');
			var popupJS = "document.location.href = 'http://domain/url';";
			$('#javascript').val('');
			$('#javascript').val(popupJS);
		});
		
		// 하위메뉴바로연결 -- 첫번째 하위메뉴를 연결
		$('#js_child_link').click(function() {
			var spId = $('#setupMenu').attr('selectedMenuId');
			var popupJS = "document.location.href = '/ep/page/index.do?pageId="+$('#childId').val()+"&directChildMenu=Y&childCnt="+$('#childCnt').val()+"'";
			$('#javascript').val('');
			$('#javascript').val(popupJS);
		});
		
		// 팝업창 스크립트 추가
		$('#js_gen_popup').click(function() {
			var spId = $('#setupMenu').attr('selectedMenuId');
			var popupJS = "// Modify bellow script\n"
						+ "luxor.popup('/ep/page/index.do?pageId="+spId+"', {\n"
						+ "    width:700,\n"
						+ "    height:500,\n"
						+ "    scrollbars:'no',\n"
						+ "    resizable:'no',\n"
						+ "    toolbar:'no',\n"
						+ "    status:'no'\n"
						+ "});\n"

			$('#javascript').val('');				
			$('#javascript').val(popupJS);
		});

		// 폴더 스크립트 추가
		$('#js_gen_folder').click(function() {
			var spId = $('#setupMenu').attr('selectedMenuId');
			var popupJS = "if(obj.attr('mode')=='open_child') {\n"
						+ "    mzVertical_open(obj);\n"
						+ "} else {\n"
						+ "    mzVertical_close(obj);\n"
						+ "}\n";
			$('#javascript').val('');				
			$('#javascript').val(popupJS);
		});
		
		// 스크립트 저장
		$('#save_js').click(function() {
			var spId = $('#setupMenu').attr('selectedMenuId');
			$("#javascriptForm input[name=menuId]").val(spId);

			var param = $('#javascriptForm').serialize();
			postJson("portalMenuController", "updateMenu", param, function(data) {
		        if(data._errorCode=='-9999') {
		        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
		        } else {
		            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
		        }
				$('#javascriptDialog').dialog('close');
			});			
		});

		// 취소
		$('#cancel_js').click(function() {
			$('#javascriptDialog').dialog('close');
		});

		// 설정 클릭
		$('div[id] a[mode=setup]').live('click', function(e) {
			var selectedConfPage = $(this).closest('div[id]').attr('id');
			var _menuId = selectedConfPage.substring(3);
			
			$('#setupMenu').attr('selectedMenuId',_menuId);

			var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+_menuId;
			callJson("portalMenuController", "getMenu", param, function(data) {
				if(data.pageId=='ALL') {
					$('#setupMenu a[mode^=add_all]').text('<spring:message code="portal.label.19" text="모든 페이지 적용취소" />');
					$('#setupMenu a[mode^=add_all]').attr('mode','add_all_cancel');
				} else {
					$('#setupMenu a[mode^=add_all]').text('<spring:message code="portal.label.20" text="모든 페이지 적용" />');
					$('#setupMenu a[mode^=add_all]').attr('mode','add_all');
				}

				$('#setupMenu').dialog({position:[e.pageX-330,e.pageY+10-$(document).scrollTop()]});
				$('#setupMenu').dialog('open');
			});
		});

		// 타겟 존 설정 클릭
		$('div[id] a[mode=targetZoneSetup]').live('click', function(e) {
			var selectedConfPage = $(this).closest('div[id]').attr('id');
			var _menuId = selectedConfPage.substring(3);
			
			$('#targetZoneSetupMenu').attr('selectedMenuId',_menuId);
			$('#targetZoneSetupMenu').dialog({position:[e.pageX-330,e.pageY+10-$(document).scrollTop()]});
			$('#targetZoneSetupMenu').dialog('open');
		});
		
		// 삭제버튼 클릭시
		$('div[id] a[mode=del]').live('click', function() {
			if(confirm("<spring:message code="portal.alert.msg.4" text="삭제 시 하위 폴더까지 삭제됩니다. 정말 삭제하시겠습니까?" />")) {
				var menuId = $(this).closest('div[id]').attr('id').substring(3);
				var pageId = $('#sp_'+menuId).attr('pageId');
				
				if(pageId=='ALL') {
					if(!confirm("<spring:message code="portal.alert.msg.31" text="모든 페이지에 적용된 메뉴입니다. 삭제시 모든 페이지에서 삭제됩니다. 삭제하시겠습니까?" />")) {
						return false;
					}
				}
				
				$('#sp_'+menuId+' div[id]').each(function() {
					var spId = $(this).attr('id').substring(3);
					var param = "pageId="+pageId+"&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+spId;
					luxor.log(param);
					callJson("portalMenuController", "deleteMenu", param, function(data) {
					});
				});
				
				var param = "pageId="+pageId+"&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+menuId;
				callJson("portalMenuController", "deleteMenu", param, function(data) {
					if(eval(data)) {
						$('#sp_'+menuId).remove();
						if(opener.setTargetedImageIcon != null) {
							opener.setTargetedImageIcon();
						}
					}
				});
			}
		});

		// 타겟 설정된 페이지 이미지 연결시 해당 존 보이기
		$('#targetZoneSetupMenu a').live('mouseover mouseout', function(event) {
			if (event.type == 'mouseover') {
				opener.showTargetedZone($(this).attr('mode'));
			} else if (event.type == 'mouseout') {
				opener.hideTargetedZone($(this).attr('mode'));
			}
		});

		$('#resize_bar').height(555);

		var param = "contentId=<%=contentId%>";
		callJson("portalContentController", "getContent", param, function(data) {
			$('#pageName').html("<spring:message code="portal.menu.label.1" text="메뉴 관리" />  <span style='font-weight:normal;'>(<spring:message code="portal.menu.label.2" text="페이지" /> : "+$('#<%=pageId%> a').first().text()+ " / <spring:message code="portal.menu.label.3" text="포틀릿" /> : "+data.messageName + " / <spring:message code="portal.menu.label.4" text="영역" /> : <spring:message code="<%=zoneId%>" text="중단" />)</span>");
		});
		$( "#page_tree_accordion" ).accordion({
			autoHeight: true,
			navigation: true,
			fillspace: true,
			event: "click",
			minWidth:190,
			minHeight:600-(60+(25*parentPortalCount))
		});

		$(".ui-accordion-content").attr("style","padding:0px;border: 0px;overflow:hidden;");
		$(".ui-accordion-header").attr("style","padding:0px;border: 0px;");
		$('.page_tree').height(580-(102+(25*parentPortalCount)));
		$(".ui-accordion-content").height(580-(60+(25*parentPortalCount)));
		// 부모 페이지 트리 초기화
		<%for (int index = 0; index < parentPortalCount; index++) {
				parentTree = (TreeVO[]) request.getAttribute("parentTree_"
						+ index);
				parentTreeId = (String) request.getAttribute("parentTreeId_"
						+ index);
				groupPortal = (GroupPortalVO) request
						.getAttribute("groupPortal_" + index);%>
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
					var parentId = $(node).attr('parentId');
					var nodeId = $(node).attr('id');
					if($(node).attr('id').indexOf('ROOT') > -1) {
						return false;
					}
					selectedNodeId = nodeId;
					// 중복체크
					if(luxor.isNullOrEmpty($('#sp_'+nodeId).text())) {
						// DB 저장
						var param = "pageId=ALL&contentId=<%=contentId%>&zoneId=<%=zoneId%>&parentId="+parentId+"&menuId="+nodeId;
						callJson("portalMenuController", "insertMenu", param, function(data) {
							if(eval(data)) {
								insertRow(nodeId, parentId, 'ALL');
							}
							if(_pageId == "ROOT") {
								var updateParam = "pageId=ALL&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+nodeId;
								callJson("portalMenuController", "updateMenuToAll", updateParam, function(data) {
								});
							}
						});
					} 
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
		<%}%>

		// 부모 포탈 페이지 트리 검색 처리
        $('.parent-tree-search a').click(function(e) {
        	$.tree.reference($('#'+$(this).attr('class'))).search($('.parent-tree-search :text').val());
        	return false;
        });
	});

	function saveDisplayName() {
		var messageNameAll = Message.getMessageNameAll();
		var messageType = Message.getMessageType();
		var _menuId = $('#display_message_form_div').attr('selectedMenuId');
		var _displayNameId = $('#display_message_form_div').attr('displayNameId');
		var _mode = $('#display_message_form_div').attr('mode');
		var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+_menuId;
		param += "&messageNameAll="+encodeURIComponent(messageNameAll)+"&messageType="+messageType+"&mode="+_mode;
		if(_displayNameId != 'display_none' && _mode == 'update' ) {
			param += "&messageId="+_displayNameId;
		}
		callJson("portalMenuController", "setMenuDisplayName", param, function(data) {
			if(data.result == true) {
				alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			} else {
				alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
			}
			document.location.reload();
		});
	}

	function deleteDisplayName() {
		var _menuId = $('#display_message_form_div').attr('selectedMenuId');
		var _displayNameId = $('#display_message_form_div').attr('displayNameId');
		var param = "pageId=<%=pageId%>&contentId=<%=contentId%>&zoneId=<%=zoneId%>&menuId="+_menuId+"&messageId="+_displayNameId+"&mode=delete";
		callJson("portalMenuController", "setMenuDisplayName", param, function(data) {
			if(data.result == true) {
				alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			} else {
				alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
			}
			document.location.reload();
		});
	}
</script>
</head>
<body>
<div id="window_scroll_wrap">
<div id="page_wrap" class="menu_selector_wrap">
<div class="title-sub" style="margin-top:0px;">
	<span id="pageName" class="title-sub-text"></span>
</div>
<div class="title" style="text-indent: 7px"></div>
<div id="tree_wrap" class='tree-wrap'
	style="min-height: 480px; width: 200px; position: relative; float: left; margin-left: 0">
<div id="page_tree_accordion" style="width: 100%;">
<h3><a href="#" id="main_page_tree"><%=currentPortalName%></a></h3>
<div><%-- 트리 검색 --%>
<div id="tree_search" class="admin-tree-btn-group"
	style="min-width: 164px;"><input type="text"
	class="input-txtfield" style="width: 90px;" /> <span class="main-btn"><span
	class="search"></span><a href="#"><spring:message
	code="portal.btn.label.8" text="검색" /></a></span></div>
<%-- 트리 검색 --%> <%-- 트리 --%>
<div id="<%=treeId%>" class="page_tree admin-tree-padding"
	style="margin-right: 5px; overflow-x: auto;">
<ul>
	<li id="ROOT" class="open"><a href='#'><ins></ins><spring:message
		code="portal.label.4" text="페이지트리" /></a> <%
 	int prevDepth = 0;
 	for (int i = 0; i < tree.length; i++) {
 		String nodeId = tree[i].getNodeId();
 		String nodeName = tree[i].getNodeName();
 		String nodeNameId = tree[i].getNodeNameId();
 		String parentId = tree[i].getParentId();
 		int depth = tree[i].getDepth();
 		int seq = tree[i].getSeq();
 		boolean hasChild = tree[i].getHasChild().equals("Y");

 		String _class = "";
 		if (nodeId.equals("root")) {
 			_class = "class='open'";
 		}

 		if (depth > prevDepth) {
 			out.println("<ul>");
 		}
 		if (prevDepth > depth) {
 			for (int j = 0; j < (prevDepth - depth); j++) {
 				out.println("</ul></li>");
 			}
 		}
 		out.println("<li id='" + nodeId + "' parentId='" + parentId
 				+ "' nodeNameId='" + nodeNameId + "' " + _class
 				+ "><a href='#'><ins></ins>" + nodeName + "</a>");
 		if (!hasChild) {
 			out.println("</li>");
 		}
 		prevDepth = depth;
 	}
 %>
	</li>
</ul>
</div>
<%-- 트리 --%></div>

<%
	for (int j = 0; j < parentPortalCount; j++) {
		parentTree = (TreeVO[]) request.getAttribute("parentTree_" + j);
		parentTreeId = (String) request.getAttribute("parentTreeId_"
				+ j);
		groupPortal = (GroupPortalVO) request
				.getAttribute("groupPortal_" + j);
%>
<h3><a href="#"><%=groupPortal.getPortalName()%></a></h3>
<div>
<div class="admin-tree-btn-group parent-tree-search"><input
	type="text" class="input-txtfield w100 INPUT_ROOT_<%=j%>" /> <span
	class="main-btn"><span class="search"></span><a href="#"
	class="PARENT_<%=parentTreeId%>_<%=j%>"><spring:message
	code="portal.btn.label.8" text="검색" /></a></span></div>
<div id="PARENT_<%=parentTreeId%>_<%=j%>"
	class="page_tree admin-tree-padding" style="margin-right: 15px;">
<ul>
	<li id="PARENT_ROOT_<%=j%>" class="open"
		name="<%=groupPortal.getPortalId()%>"><a href='#'><ins></ins><spring:message
		code="portal.label.4" text="페이지트리" /></a> <span id="total_page_count"
		style="color: #990000; font-size: 11px;">(<%=parentTree.length%>)</span>
	<%
		prevDepth = 0;
			for (int i = 0; i < parentTree.length; i++) {
				String nodeId = parentTree[i].getNodeId();
				String nodeName = parentTree[i].getNodeName();
				String nodeNameId = parentTree[i].getNodeNameId();
				String parentId = parentTree[i].getParentId();
				String usePersonal = parentTree[i].getUsePersonal();
				int depth = parentTree[i].getDepth();
				int seq = parentTree[i].getSeq();
				boolean hasChild = parentTree[i].getHasChild().equals("Y");

				String _class = "";
				if (nodeId.equals("root")) {
					//_class = "class='open'";
				}

				if (depth > prevDepth) {
					out.println("<ul name=" + groupPortal.getPortalId()
							+ ">");
				}

				if (prevDepth > depth) {
					for (int k = 0; k < (prevDepth - depth); k++) {
						out.println("</ul></li>");
					}
				}
				out.println("<li id='" + nodeId + "' parentId='" + parentId
						+ "' nodeNameId='" + nodeNameId + "' " + _class
						+ "><a href='#'><ins></ins>" + nodeName + "</a>");
				if (!hasChild) {
					out.println("</li>");
				}

				prevDepth = depth;
			}
	%>
	</li>
</ul>
</div>
</div>
<%
	}
%>
</div>
</div>
<%-- 왼쪽(트리), 오른쪽(페이지) 크기 리사이즈해주는 바 --%>
<div id="resize_bar" class="resize-bar" style="left: 205px; top: 30px;"></div>
<div id="selected_page_wrap" class="selected-page-wrap">
<h6 class="admin-sub-title"><spring:message code="portal.label.17"
	text="선택된 페이지" /></h6>
<div id="selected_page"></div>
</div>
</div>
</div>

<%-- 설정메뉴창 --%>
<div id="setupMenu"
	title="<spring:message code="portal.label.16" text="설정메뉴" />"><a
	href='#' mode='add_child'><spring:message code="portal.label.15"
	text="하위포함" /></a> | <a href='#' mode='add_sibling'><spring:message
	code="portal.label.14" text="같은레벨 포함" /></a> | <a href='#' mode='add_all'><spring:message
	code="portal.label.13" text="모든 페이지 적용" /></a> | <a href='#' mode='set_js'><spring:message
	code="portal.label.12" text="자바스크립트" /></a></div>

<%-- target zone 설정메뉴창 --%>
<div id="targetZoneSetupMenu"
	title="<spring:message code="portal.page.label.7" text="선택된 페이지를 해당 지역에 부분적으로 호출" />">
<a href='#' mode=''><spring:message code="portal.page.label.5"
	text="타겟해제" /></a> | <a href='#' mode='body'><spring:message
	code="portal.page.label.6" text="BODY" /></a> | <a href='#' mode='left'><spring:message
	code="portal.content.label.6" text="좌측" /></a> | <a href='#'
	mode='content'><spring:message code="portal.content.label.7"
	text="중앙" /></a> | <a href='#' mode='right'><spring:message
	code="portal.content.label.8" text="우측" /></a></div>

<%-- 자바스크립트 입력창 --%>
<div id="javascriptDialog" title="Javascript">
<form id="javascriptForm"><input type="hidden" name="pageId"
	value="<%=pageId%>" /> <input type="hidden" name="contentId"
	value="<%=contentId%>" /> <input type="hidden" name="zoneId"
	value="<%=zoneId%>" /> <input type="hidden" name="menuId" value="" />
<div style="text-align: center; margin-bottom: 5px;"><span
	class="main-btn"><a href="#none" id="js_gen_folder"><spring:message
	code="portal.label.10" text="폴더만 열고 닫기" /></a></span> <span class="main-btn"><a
	href="#none" id="js_gen_popup"><spring:message
	code="portal.label.11" text="팝업으로 띄우기" /></a></span> <span class="main-btn"><a
	href="#none" id="js_gen_link"><spring:message
	code="portal.label.69" text="URL 열기" /></a></span>  <span class="main-btn" id="directChildMenu" style="display:none;"><a
	href="#none" id="js_child_link"><spring:message
	code="portal.label.219" text="하위메뉴바로연결" /></a></span></div>
<textarea id="javascript" name="script"
	style="color: #000000; font-family: 'Lucida Console', courier, fixedsys; width: 100%; height: 200px; margin-right: 5px;"></textarea>
<div style="text-align: center; margin-top: 10px;"><span
	class="main-btn"><a href="#none" id="save_js"><spring:message
	code="portal.btn.label.1" text="확인" /></a></span> <span class="main-btn"><a
	href="#none" id="cancel_js"><spring:message
	code="portal.btn.label.2" text="취소" /></a></span></div>
<input type="hidden" id="childId" value="">
<input type="hidden" id="childCnt" value="0">
</form>
</div>

<%-- 대량 메뉴 추가시 진행바 --%>
<div id="progressbarWrap" class="dialog"
	style="width: 300px; padding: 10px; border: 1px solid #828282;">
<div id="progressbar"></div>
<div id="progressbarCaption"
	style="text-align: center; margin-top: 10px;"></div>
</div>

<%-- 메뉴명 설정시 다국어 관련 DIV --%>
<div id="display_message_form_div"
	title="<spring:message code="portal.page.label.4" text="대체 메뉴명" />">
<form id="tree_form">
<table class="tablelist">
	<tr>
		<th><spring:message code="portal.page.label.3" text="메뉴명" /></th>
		<td width="50%"><jsp:include page="/luxor/common/message.jsp">
			<jsp:param name="message_type" value="PORTAL.PAGE.MENU" />
			<jsp:param name="check_duplicate" value="N" />
		</jsp:include></td>
		<td><span class="main-btn"><a href="#"
			onclick="saveDisplayName();return false;"><spring:message
			code="portal.btn.label.1" text="확인" /></a></span> <span class="main-btn"><a
			href="#" onclick="deleteDisplayName();return false;"><spring:message
			code="portal.page.label.2" text="제거" /></a></span></td>
	</tr>
</table>
</form>
</div>
<%-- 메뉴명 설정시 다국어 관련 DIV --%>

<div id="alphabg"></div>
</body>