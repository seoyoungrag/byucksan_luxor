<%/* 관리자 --> 페이지 관리 */%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sds.acube.luxor.portal.vo.GroupPortalVO"%>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	TreeVO[] tree = (TreeVO[])request.getAttribute("tree");
	String treeId = CommonUtil.nullTrim((String)request.getAttribute("treeId"));
	String selectedNodeId = CommonUtil.nullTrim((String)request.getAttribute("selectedNodeId"));
	String currentPortalName = CommonUtil.nullTrim((String)request.getAttribute("currentPortalName"));
	String currentPortalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
	String selectedPortalId = CommonUtil.nullTrim((String)request.getAttribute("selectedPortalId"));
	TreeVO[] parentTree = null;
	String parentTreeId = null;			
	GroupPortalVO groupPortal = null;
	
	if(CommonUtil.isNullOrEmpty(selectedNodeId)) {
		selectedNodeId = "ROOT";
	}
	
	int parentPortalCount = (Integer)request.getAttribute("parentPortalCount") == null ? 0 : (Integer)request.getAttribute("parentPortalCount");
	String temp = CommonUtil.generateId();
%>

<!DOCTYPE HTML>

<html lang="ko">
<head>
<title><spring:message code="portal.label.2" text="페이지 관리" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js?<%=temp %>" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/content.js?<%=temp %>" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/contentManager.js?<%=temp %>" charset="utf-8"></script>
<script type="text/javascript">
	var _treeId = "<%=treeId%>";
	var searchDivId = "tree_search";
	var messageDivId = "tree_message_form_div";
	var currentPageId = '<%=selectedNodeId%>';
	var currentPortalId = '<%=currentPortalId%>';
	var currentPortalName = '<%=currentPortalName%>';
	var selectedPortalId = '<%=selectedPortalId%>';
	var currentMenuId = '';
	var themeImageUrl = '';
	var selectedThemeId = "";
	var isPersonal = false;
	var isTemplate = false;
	var message1 = "<spring:message code="portal.alert.msg.76" text="{1} 페이지의 구성요소(테마, 레이아웃, 컨텐츠)를 가져오시겠습니까?\\n현재 페이지 {2}에 기존에 추가되어있는 항목은 모두 지워집니다." />";
	var portal_page_label_6 = "<spring:message code="portal.page.label.6" text="BODY" />";
	var portal_content_label_6 = "<spring:message code="portal.content.label.6" text="좌측" />";
	var portal_content_label_7 = "<spring:message code="portal.content.label.7" text="중앙" />";
	var portal_content_label_8 = "<spring:message code="portal.content.label.8" text="우측" />";
	var portal_content_label_18 = "<spring:message code="portal.content.label.18" text="우측중앙" />";
	var portal_content_label_19 = "<spring:message code="portal.content.label.18" text="좌측중앙" />";

	var onloadingPage = false;
	var parentPortalCount = <%=parentPortalCount%>;
	
	// 현재 선택된 노드
	var selectedNodeId = '<%=selectedNodeId%>';
	content.refer = 'PM';

	function removeAllContents() {
		$('#wrap ul > li').each(function() {
			$(this).remove();
		});
	}
	
	function loadContents(pageId) {
		removeAllContents();
		callJson("portalPageZoneController", "getContentsOnPage", "pageId="+pageId, function(data) {
			content.setMenuList(['reload','min','max','help','edit','setup','del']);
			content.setPreview(true);
			content.setDraggable(true);
			content.style.setWyswyg(true);
			content.load(data, function() { resizeTreeHeight(); });
		});
	}

	// 트리에서 페이지 선택시 호출
	var selectCallback = function(nodeId, isParentPage, portalId) {
		//부모포탈을 호출한게 아니라면 자신의 포탈 ID를 부여한다.
		if(isParentPage == null) {
			isParentPage = "";
		}
		if(selectedPortalId != "" && portalId == null && isParentPage.indexOf("PARENT") > -1) {
			portalId = selectedPortalId;
		} else if(portalId == null) {
			portalId = "<%=CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"))%>";
			selectedPortalId = portalId;
		} else {
			selectedPortalId = portalId;
		}
		//처리중 페이지 호출 중복으로 들어오는 경우 실행안함
		if(onloadingPage == true) {
			return false;
		}
		onloadingPage = true;
		
		selectedNodeId = nodeId;
		currentPageId = nodeId;

		// 루트 선택하는 경우에는 루트 하위의 첫번째 페이지 선택시키고 리턴 
		if(nodeId=='ROOT') {
			onloadingPage = false;
			return false;
		}
		// 기존에 로딩된 포틀릿용 CSS는 제거
		$('link[name="portletCSS"]').remove();

		//부모포탈 페이지인지 자기 페이지인지 구분
		if(isParentPage == "Y" || isParentPage.indexOf("PARENT") > -1) {
			$('#layout_list').hide();
			$('#theme_list').hide();
			$('.title-sub .aright').hide();
		} else {
			$('#layout_list').show();
			$('#theme_list').show();
			$('.title-sub .aright').show();
		}
		
		// 사용함/사용안함 버튼 텍스트 셋팅
		if($('#'+nodeId).attr("isactive")=='N') {
			$("#btn_active_setup").text("<spring:message code="portal.btn.label.47" text="사용함" />");
		} else {
			$("#btn_active_setup").text("<spring:message code="portal.btn.label.46" text="사용안함" />");
		}
		
		var linkUrl = "<a href='/ep/page/index.do?pageId="+selectedNodeId+"' target='_blank'><spring:message code="portal.label.3" text="미리보기" /></a>"
		$('#page_title').html(_getNodeName(selectedNodeId) + " - " + linkUrl);
		
		// 레이아웃 목록가져와서 보여주기
		$.get('/ep/page/layout/collection.do?pageId='+nodeId+'&portalId='+portalId, function(data) {
			$('#layout_list').html(data);

			// 해당 페이지의 레이아웃을 찾기
			var selectedLayoutId = "";
			$("#layoutCollection .smain-btn a").each(function() {
				if($(this).attr('isSelected')=='Y') {
					selectedLayoutId = $(this).attr('id');
				}
			});

			// 찾은 레이아웃을 페이지에 셋팅
			setLayoutCss(selectedLayoutId);
		});

		// 테마 목록가져와서 보여주기
		$.get('/ep/page/theme/collection.do?pageId='+nodeId+'&portalId='+portalId, function(data) {
			$('#theme_list').html(data);
			
			// 해당 페이지의 테마 찾기
			$("#themeCollection .smain-btn a").each(function() {
				if($(this).attr('isSelected')=='Y') {
					selectedThemeId = $(this).attr('id');
				}
			});

			// 찾은 테마를 페이지에 셋팅
			setThemeCss(selectedThemeId);

			// 페이지에 기존에 추가된 컨텐츠 불러와서 각각의 위치에 끼워넣음
			loadContents(nodeId);
		});
		
		// 선택된 페이지 로딩
		$.get('/ep/page/setup.do?pageId='+nodeId+'&portalId='+portalId, function(data) {
			$('#page_setup').html(data);
			if($('#isPersonal').val() == "Y") { 
				$('#btn_personalize').html("<spring:message code="portal.label.7.1" text="개인화 해제" />");
			} else {
				$('#btn_personalize').html("<spring:message code="portal.label.7" text="개인화 허용" />");
			} 
			if($('#isTemplate').val() == "Y") {
				$('#btn_template').html("<spring:message code="portal.btn.label.49.1" text="템플릿 해제" />");
			} else { 
				$('#btn_template').html("<spring:message code="portal.btn.label.49" text="템플릿으로 지정" />");
			}
			onloadingPage = false;
		});
		
	};

	// 새로 노드(페이지) 추가시 insertPage 해줌
	var insertCallBack = function(nodeId) {
		var param = "pageId="+nodeId;
		callJson("portalPageController", "insertPage", param, function(data) {
			if(eval(data)==true) {
				var totalPageCount = eval(luxor.removeSpecialChar($('#total_page_count').text(),'1'));
				$('#total_page_count').text('('+(totalPageCount+1)+')');
				_selectNode(nodeId);
				alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
			} else {
				_deleteFolder(nodeId, false);
				alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			}
			Message.reset();
		});
	};
	
	// 노드 이름 변경시 호출
	var updateCallBack = function(nodeId) {
		Message.reset();
	};
	
	// 노드 삭제시 호출
	var deleteCallBack = function(nodeId) {
		var param = "pageId="+nodeId;
		callJson("portalPageController", "deletePage", param, function(data) {
			if(eval(data)==true) {
				var totalPageCount = eval(luxor.removeSpecialChar($('#total_page_count').text(),'1'));
				$('#total_page_count').text('('+(totalPageCount-1)+')');
				alert("<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />");
			} else {
				alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			}
		});
	};
	
	var moveCallBack = function(fromNodeId, toNodeId, moveType) {
		// 드래그해서 위치 이동시 호출
	};

	$(window).scroll(function() {
		resizeTreeHeight();
	});
	$(window).resize(function() {
		setTimeout("resizeTreeHeight()",400);
	});
	
	function initPageImportTree() {
		// 페이지 가져오기 트리 초기화
		$("#import_<%=treeId%>").tree({
	    	ui: {
				theme_name : "classic"
			},
			types: {
				'default': {
					draggable : false
				}
			},
			callback: {
				// 페이지 가져오기 트리에서 페이지 선택시 해당 페이지의 구성요소를 가져옴
				onselect: function(node, tree_obj) {
					if($(node).attr("id").substring(7)=='ROOT') {
						alert("<spring:message code="portal.alert.msg.35" text="최상위 페이지는 가져올 수 없습니다." />");
						return false;
					}
					if($(node).attr("id").substring(7)==selectedNodeId) {
						alert("<spring:message code="portal.alert.msg.36" text="같은 페이지는 가져올 수 없습니다." />");
						return false;
					}
					
					var nodeName = _getNodeName($(node).attr("id"));
					message1 = message1.replace("{1}", nodeName).replace("{2}", _getNodeName(selectedNodeId));
					if(confirm(message1)) {
						var param = "fromPageId="+$(node).attr("id").substring(7)+"&toPageId="+selectedNodeId;
						callJson("portalPageController", "copyPage", param, function(data) {
					        if(data._errorCode=='-9999') {
					            alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
					        } else {
					        	$('#'+selectedNodeId+' a:first').click();
					        	$('#import_<%=treeId%>').dialog('close');
					            alert("<spring:message code="portal.alert.msg.16" text="적용되었습니다." />");
					        }
						});
					}
				}
			}
		});

		$.tree.reference($('#import_ROOT')).open_branch("#import_ROOT");
	}
	
	//drag시 previous treewidth
	var prevTreeWidth = 0;
	
	$(document).ready(function() {
		// 첫번째 페이지 자동 클릭
		selectFirstPage();
		
		// 페이지 가져오기 팝업창 초기화
		initPageImportTree();
		$('#import_<%=treeId%>').dialog({
			autoOpen:false,
			resizable:false,
			modal:true,
			width:250,
			height:350
		});
		
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
		
		// 레이아웃 선택시 해당 레이아웃의 CSS정보를 가져와서 페이지에 셋팅함
		$('#layoutCollection .smain-btn a').live('click', function() {
			var layoutId = $(this).attr('id');

			// 페이지에 CSS 셋팅
			setLayoutCss(layoutId);

			// Ajax로 페이지 정보 업데이트
			callJson("portalPageController", "updatePage", "pageId="+selectedNodeId+"&pageLayoutId="+layoutId); 
		});
		
		// 테마 선택시 해당 레이아웃의 CSS정보를 가져와서 페이지에 셋팅함
		$('#themeCollection .smain-btn a').live('click', function() {
			var themeId = $(this).attr('id');

			// 페이지에 CSS 셋팅
			setThemeCss(themeId);
			// 컨텐츠 로드
			loadContents(selectedNodeId);
			// Ajax로 페이지 정보 업데이트
			callJson("portalPageController", "updatePage", "pageId="+selectedNodeId+"&pageThemeId="+themeId); 
		});

		// 홈으로 설정 버튼 클릭
		$("#btn_set_home").click(function() {
			var param = "pageId="+selectedNodeId;
			var alertMessage = "";
			var isRegister = false;
			if($('#PORTAL_PAGE li[id='+selectedNodeId+'] img[id=init_home]').attr('title') !=null) {
				param += "&isHome=N";
				alertMessage = _getNodeName(selectedNodeId)+"<spring:message code="portal.page.alert.2" text="페이지를 홈에서 해제하시겠습니까?" />";
			} else {
				alertMessage = _getNodeName(selectedNodeId)+"<spring:message code="portal.page.alert.3" text="페이지를 홈으로 설정하시겠습니까?" />";
				isRegister = true;
			}
			if(confirm(alertMessage)) {
				if(isRegister == true) {
					if(confirm("로그인 후의 홈으로 설정하시겠습니까? \n\n취소 선택시 로그인 전의 홈으로 설정됩니다.")) {
						param += "&isHome=L";
					} else {
						param += "&isHome=Y";
					}
				}
				callJson("portalPageController", "setHome", param, function(data) {
					if(data._errorCode=='-9999') {
						alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
					} else {
						alert("<spring:message code="portal.alert.msg.16" text="적용되었습니다." />");
						document.location.href = '/ep/page/manager.do?nodeId='+selectedNodeId;
					}
				});
			}
			return false;
		});

		// 페이지 가져오기 버튼 클릭
		$("#btn_import_page").click(function(e) {
			$('#import_<%=treeId%>').dialog({position:[e.pageX-150,e.pageY+10]});
			$('#import_<%=treeId%>').dialog('open');
			return false;
		});

		// 개인화 설정 버튼 클릭
		$("#btn_personalize").click(function(e) {
			var msg = $('#isPersonal').val()=="Y" 
					? "<spring:message code="portal.alert.msg.33" text="페이지에 설정된 개인화를 해지하시겠습니까?\\n\\n사용자가 설정한 개인화 설정은 모두 초기화 됩니다." />" 
					: "<spring:message code="portal.alert.msg.34" text="개인화 페이지로 설정하시겠습니까?" />";
			var isPersonalVal = $('#isPersonal').val()=="Y" ? "N" : "Y";
			var param = "isPersonal="+isPersonalVal+"&pageId="+selectedNodeId;
			if(confirm(msg)) {
				callJson("portalPageController", "setPersonalize", param, function(data) {
			        if(data._errorCode=='-9999') {
			        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			        } else {
			        	$('#isPersonal').val(isPersonalVal);
			            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
			        	document.location.href = '/ep/page/manager.do?nodeId='+selectedNodeId;
			        }
				});
			}
			return false;
		});
		
		// 템플릿 설정 버튼 클릭
		$("#btn_template").click(function(e) {
			var msg = $('#isTemplate').val()=="Y" 
				? "<spring:message code="portal.alert.msg.80" text="이 페이지를 템플릿 페이지에서 해제 하시겠습니까?" />" 
				: "<spring:message code="portal.alert.msg.74" text="개인화 페이지로 설정하시겠습니까?" />";
			var isTemplateVal = $('#isTemplate').val()=="Y" ? "N" : "Y";
			var param = "isTemplate="+isTemplateVal+"&pageId="+selectedNodeId;
			if(confirm(msg)) {
				callJson("portalPageController", "setTemplate", param, function(data) {
			        if(data._errorCode=='-9999') {
			        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			        } else {
			            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
			        	document.location.href = '/ep/page/manager.do?nodeId='+selectedNodeId;
			        }
				});
			}
			return false;
		});
		
		// 권한설정 버튼 클릭
		$("#btn_acl_setup").click(function(e) {
			luxor.popup("/ep/acl/aclSetupUser.do?resourceID="+selectedNodeId, {width:850,height:600});
			return false;
		});

		// 홈으로 설정시 트리에 홈 이미지 삽입
		callJson("portalPageController", "getHome", "", function(data) {
			
			for(var i=0 ; i < data.length ; i++) {
				if(data[i]!=null) {
					if(data[i].isHome == 'Y') {
						$('#tree_wrap #'+data[i].pageId+'> a').append("&nbsp<img id='init_home' src='/ep/luxor/ref/image/admin/tree_icon05.gif' style='vertical-align:text-bottom;' alt='[홈]' title='포탈 홈 설정된 페이지' rel='tooltip'/>");
					} else if(data[i].isHome == 'L') {
						$('#tree_wrap #'+data[i].pageId+'> a').append("&nbsp<img id='init_home' src='/ep/luxor/ref/image/admin/tree_icon07.gif' style='vertical-align:text-bottom;' alt='[로그인홈]' title='로그인 후 홈으로 설정된 페이지' rel='tooltip'/>");
					}
				}
			}
		});

		// 개인화 페이지 이미지 삽입
		callJson("portalPageController", "getPersonalPages", "", function(data) {
			for(var i=0; i<data.length; i++) {
				$('#tree_wrap #'+data[i].pageId+'> a').append("&nbsp<img src='/ep/luxor/ref/image/admin/tree_icon04.gif' style='vertical-align:text-bottom;' alt='[P]' title='사용자 개인화 적용 페이지' rel='tooltip'/>");
			}
		});

		// 템플릿 페이지 이미지 삽입
		callJson("portalPageController", "getTemplatePages", "", function(data) {
			for(var i=0; i<data.length; i++) {
				$('#tree_wrap #'+data[i].pageId+'> a').append("&nbsp<img src='/ep/luxor/ref/image/admin/tree_icon06.gif' style='vertical-align:text-bottom;' alt='[temp]' title='사용자 개인화 작업시 템플릿으로 호출 가능한  페이지' rel='tooltip'/>");
			}
		});
		
		// 사용함/사용안함 버튼 클릭
		$("#btn_active_setup").click(function(e) {
			var msg = $('#'+selectedNodeId).attr('isactive')=='N' 
						? "<spring:message code="portal.alert.msg.52" text="사용함으로 설정시 기존에 등록된 모든 메뉴 및 페이지를 바로 사용할 수 있습니다. 계속하시겠습니까?" />"
						: "<spring:message code="portal.alert.msg.51" text="사용안함으로 설정시 기존에 등록된 모든 메뉴 및 페이지는 사용할 수 없습니다. 계속하시겠습니까?" />";
						
			if(confirm(msg)) {
				var isActive = $('#'+selectedNodeId).attr('isactive')=='N' ? "Y" : "N";
				var param = "pageId="+selectedNodeId+"&isActive="+isActive;
				callJson("portalPageController", "updatePageActiveStatus", param, function(data) {
			        if(data._errorCode=='-9999') {
			        	alert("<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />");
			        } else {
			            alert("<spring:message code="portal.alert.msg.13" text="저장되었습니다." />");
			            document.location.reload();
			        }
				});
			}
			
			return false;
		});

		// 타겟 설정된 페이지 이미지 삽입
		callJson("portalPageController", "getTargetedPages", "", function(data) {
			for(var i=0; i<data.length; i++) {
				var targetZone = data[i].targetZone;
				var strZoneName = "";
				if(targetZone == "body") {
					strZoneName = portal_page_label_6;
				} else if(targetZone == "left") {
					strZoneName = portal_content_label_6;
				} else if(targetZone == "content") {
					strZoneName = portal_content_label_7;
				}else if(targetZone == "content-right"){
					strZoneName = portal_content_label_18;
				}else if(targetZone == "content-left"){
					strZoneName = portal_content_label_19;
				}else if(targetZone == "right") {
					strZoneName = portal_content_label_8;
				} 
				var pageId = data[i].menuId;
				$('#tree_wrap #'+pageId+'> a').append("&nbsp<img name='targetedPageIcon' class='"+targetZone+"' src='/ep/luxor/ref/image/admin/tree_icon_"+targetZone+".gif' style='vertical-align:text-bottom;' alt='[tp]' title='"+data[i].messageName+" 페이지를 대상으로 "+data[i].messageId+"에서 "+strZoneName+" 부분 영역으로 호출된 페이지' rel='tooltip'/>");
			}
		});

		// 타겟 설정된 페이지 이미지 연결시 해당 존 보이기
		$('#tree_wrap img[name="targetedPageIcon"]').live('mouseover mouseout', function(event) {
			if (event.type == 'mouseover') {
				var target = $(this).attr("class");
				showTargetedZone(target);
			} else if (event.type == 'mouseout') {
				var target = $(this).attr("class");
				hideTargetedZone(target);
			}
		});

		$( "#page_tree_accordion" ).accordion({
			autoHeight: true,
			navigation: true,
			fillspace: true,
			event: "click",
			minHeight:$(window).height()-(60+(20*parentPortalCount))
		});
		
		$(".ui-accordion-content").attr("style","padding:0px;border: 0px;");
		$(".ui-accordion-header").attr("style","padding:0px;border: 0px;");
		$('#tree_wrap .page-tree-wrap').height($(document).height()-(20*parentPortalCount));
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
					if($(node).attr("id").indexOf("_ROOT_") > -1) {
						selectCallback("ROOT","Y",$(node).closest('ul').attr('name'));
					} else {
						selectCallback($(node).attr("id"),"Y",$(node).closest('ul').attr('name'));
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
		<% } %>

		// 부모 포탈 페이지 트리 검색 처리
        $('.parent-tree-search a').click(function(e) {
        	$.tree.reference($('#'+$(this).attr('class'))).search($('.parent-tree-search :text').val());
        	return false;
        });
        
		//다 불러와졌으면 tree frame scroll 설정
		$(".page-tree-wrap").attr('style','overflow-y: auto');
	});

	function setTargetedImageIcon() {
		// 타겟 설정된 페이지 이미지 삽입
		$('#tree_wrap img[name=targetedPageIcon]').remove();
		callJson("portalPageController", "getTargetedPages", "", function(data) {
			for(var i=0; i<data.length; i++) {
				var targetZone = data[i].targetZone;
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
				var pageId = data[i].menuId;
				$('#tree_wrap #'+pageId+'> a').append("<img name='targetedPageIcon' class='"+targetZone+"' src='/ep/luxor/ref/image/admin/tree_icon_"+targetZone+".gif' style='vertical-align:text-bottom;' alt='[tp]' title='"+data[i].messageName+" 페이지를 대상으로 "+data[i].messageId+"에서 "+strZoneName+" 부분 영역으로 호출된 페이지' rel='tooltip'/>");	
			}
		});
	}
    
	function showTargetedZone(target) {
		var luxorMainWidth = $('.luxor-main').width();
		$('.luxor-main').width(luxorMainWidth+4);
		if(target=='body') {
			var contentHeight = 0;
			$('.luxor-body ul.ui-sortable').each(function() {
				if(contentHeight < $(this).height()) {
					contentHeight = $(this).height();
				}
			});
			contentHeight += 25;
			$('.luxor-body').attr('style','height:'+contentHeight+'px;');
		}
		$('.luxor-'+target).attr('class','luxor-'+target+' targeted-zone-border');
	}

	function hideTargetedZone(target) {
		var luxorMainWidth = $('.luxor-main').width();
		$('.luxor-main').width(luxorMainWidth-4);
		if($(this).attr("class")=='body') {
			$('.luxor-body').attr('style','');
		} 
		$('.luxor-'+target).attr('class','luxor-'+target);
	}
	
	function resizeTreeHeight() {
		$('#resize_bar').height($(document).height()-20);
		$("#page_tree_accordion").attr('style','clear:both;padding:0px;visibility:visible;');
	}
	
	/**
	 * 트리에서 첫번째 노드(페이지) 선택
	 */
	function selectFirstPage() {
		if(selectedNodeId=='ROOT') {
			selectedNodeId = $('#ROOT ul li').first().attr('id');
		}
		$.tree.focused().select_branch("#"+selectedNodeId);
	}
	
	/**
	 * 레이아웃 CSS를 페이지에 셋팅
	 */
	function setLayoutCss(layoutId) {
		// 레이아웃이 없는 경우 디폴트 레이아웃을 찾음
		if(luxor.isNullOrEmpty(layoutId)) {
			// find default layoutId
			$('#layoutCollection .smain-btn a').each(function() {
				if($(this).attr('isDefault')=='Y') {
					layoutId=$(this).attr('id');
				}
			});
		}

		// 기존에 선택된건 선택해제
		$('#layoutCollection .smain-btn a').each(function() {
			$(this).html($(this).text());
		});
		
		// 선택된 레이아웃 표시
		$('#'+layoutId).html("<b>"+$('#'+layoutId).text()+"</b>");

		// Ajax로 선택된 레이아웃의 CSS를 가져와서 페이지에 적용
		var param = "layoutId="+layoutId;
		callJson("portalPageLayoutController", "getPageLayoutAjax", param, function(data) {
			var css = data.layoutCss;
			$('#layout_css').html("<style tyle='text/css'>"+css+"</style>");
		});
	}

	/**
	 * 테마 CSS를 페이지에 셋팅
	 */
	var lastThemeCss;
	function setThemeCss(themeId) {
		// 테마가 없는 경우 디폴트 테마를 찾음
		if(luxor.isNullOrEmpty(themeId)) {
			// find default themeId
			$('#themeCollection .smain-btn a').each(function() {
				if($(this).attr('isDefault')=='Y') {
					themeId=$(this).attr('id');
				}
			});
		}
		// 기존에 선택된건 선택해제
		$('#themeCollection .smain-btn a').each(function() {
			$(this).html($(this).text());
		});
		
		// 선택된 레이아웃 표시
		$('#'+themeId).html("<b>"+$('#'+themeId).text()+"</b>");

		// Ajax로 선택된 테마 CSS를 가져와서 페이지에 적용
		var param = "themeId="+themeId;
		callJson("portalPageThemeController", "getPageThemeAjax", param, function(data) {
			themeImageUrl = data.themeImageUrl;
			var cssUrl = data.themeCssUrl;
			
			if($('link[href='+lastThemeCss+']').is('link')) {
				$('link[href='+lastThemeCss+']').attr('href',cssUrl);
			} else {
				$('<link>').appendTo('head').attr({
					rel: 'stylesheet',
					type: 'text/css',
					href: cssUrl
				});
			}

			lastThemeCss = cssUrl;
		});
	}
</script>
</head>

<body>
<div id="window_scroll_wrap">
	<div id="admin_wrap" class="admin-wrap">
		<div id="tree_wrap" class="tree-wrap">
			<div class="page-tree-wrap" id="page_tree_accordion" style="clear:both;padding:0px;visibility:hidden;">
				<h3><a href="#" id="main_page_tree"><%=currentPortalName %></a></h3>
				<div>
					<%-- 폴더 추가/수정/삭제 버튼 --%>
					<div class="admin-tree-btn-group">
						<a href="#" onclick="_showCreateForm();return false;"><img src="/ep/luxor/ref/image/admin/icon_06.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.9" text="페이지 추가" />" title="<spring:message code="portal.btn.label.9" text="페이지 추가" />"></a>
						<a href="#" onclick="_showModifyForm();return false;"><img src="/ep/luxor/ref/image/admin/icon_07.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.10" text="페이지 수정" />" title="<spring:message code="portal.btn.label.10" text="페이지 수정" />"></a>
						<a href="#" onclick="_deleteFolder();return false;"><img src="/ep/luxor/ref/image/admin/icon_08.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.11" text="페이지 삭제" />" title="<spring:message code="portal.btn.label.11" text="페이지 삭제" />"></a>
						<span class="smain-btn" style="position: relative; top: 2px;"><a href="javascript:openAll();"><spring:message code="portal.page.label.22" text="모두펼치기" /></a></span>
					</div>
					<%-- 폴더 추가/수정/삭제 버튼 --%>
				
					<%-- 트리 검색 --%>
					<div id="tree_search" class="admin-tree-btn-group">
						<input type="text" class="input-txtfield w100" />
						<span class="main-btn" style="position: relative; top: 2px;"><span class="search"></span><a href="#"><spring:message code="portal.btn.label.8" text="검색" /></a></span>		
					</div>
					<%-- 트리 검색 --%>
			
					<%-- 왼쪽 페이지 트리 --%>
					<div id="<%=treeId%>" class="page_tree admin-tree-padding" style="margin-right:15px;">
					    <ul>
					    	<li id="ROOT" class="open">
					    		<a href='#'><ins></ins><spring:message code="portal.label.4" text="페이지트리" /></a>
					    		<span id="total_page_count" style="color:#990000;font-size:11px;">(<%=tree.length%>)</span>
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
									boolean isActive = !tree[i].getIsActive().equals("N");
	
									if(!isActive) {
										nodeName = "<strike class='inactive'>"+nodeName+"</strike>";	
									}
	
									String _class = "";
									if(nodeId.equals("root")) {
										_class = "open";
									}
									
									if(depth > prevDepth) {
										out.println("<ul>");
									}
									if(prevDepth > depth) {
										for(int j=0; j < (prevDepth - depth); j++) {
											out.println("</ul></li>");
										}
									}
									
									out.println("<li id='"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' class='"+_class+"' isactive='"+tree[i].getIsActive()+"'><a href='#'><ins></ins>"+nodeName+"</a>");
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
				<%
				for(int j=0 ; j < parentPortalCount ;j++ ) { 
					parentTree = (TreeVO[])request.getAttribute("parentTree_"+j);
					parentTreeId = (String)request.getAttribute("parentTreeId_"+j);		
					groupPortal = (GroupPortalVO)request.getAttribute("groupPortal_"+j);
				%>
				<h3><a href="#"><%=groupPortal.getPortalName() %></a></h3>
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
					    	<li id="PARENT_ROOT_<%=j%>" class="open">
					    		<a href='#'><ins></ins><spring:message code="portal.label.4" text="페이지트리" /></a>
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
									boolean isActive = !parentTree[i].getIsActive().equals("N");
	
									if(!isActive) {
										nodeName = "<strike class='inactive'>"+nodeName+"</strike>";	
									}
	
									String _class = "";
									if(nodeId.equals("root")) {
										_class = "open";
									}
									
									if(depth > prevDepth) {
										out.println("<ul name="+groupPortal.getPortalId()+">");
									}
									if(prevDepth > depth) {
										for(int k=0; k < (prevDepth - depth); k++) {
											out.println("</ul></li>");
										}
									}
									
									out.println("<li id='"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' class='"+_class+"' isactive='"+parentTree[i].getIsActive()+"'><a href='#'><ins></ins>"+nodeName+"</a>");
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
	
		<%-- 페이지 구성 --%>
		<div id="container" class="admin-content-width w1000 ml15" style="height:100%;">
			<div class="title-sub">
				<span class="title-sub-text"><spring:message code="portal.label.2" text="페이지 관리" /></span>
				<span id="page_title" class="admin-title-side-text"></span>
				<div class="aright">
					<span class="main-btn"><a href="#" id="btn_set_home"><spring:message code="portal.label.5" text="홈 설정" /></a></span>
					<span class="main-btn"><a href="#" id="btn_import_page"><spring:message code="portal.label.6" text="페이지 가져오기" /></a></span>
					<span class="main-btn"><a href="#" id="btn_personalize"><spring:message code="portal.label.7" text="개인화 설정" /></a></span>
					<span class="main-btn"><a href="#" id="btn_template"><spring:message code="portal.btn.label.49" text="템플릿 설정" /></a></span>
					<span class="main-btn"><a href="#" id="btn_acl_setup"><spring:message code="portal.btn.label.38" text="권한 설정" /></a></span>
					<span class="main-btn"><a href="#" id="btn_active_setup"><spring:message code="portal.btn.label.46" text="사용안함" /></a></span>
				</div>
			</div>
			<div id="layout_list" class="admin-layout-theme-search" style="clear:both;"></div>
			<div id="theme_list" class="admin-layout-theme-search"></div>
			<div id="page_setup" class="main"></div>
		</div>
	</div>
</div>

<%-- 페이지 가져오기용 트리 --%>
<div id="import_<%=treeId%>" title="<spring:message code="portal.label.6" text="페이지 가져오기" />">
    <ul>
    	<li id="import_ROOT" class="open"><a href='#'><ins></ins><spring:message code="portal.label.4" text="페이지트리" /></a> 
		<%
			int import_prevDepth = 0;
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
				
				if(depth > import_prevDepth) {
					out.println("<ul>");
				}
				if(import_prevDepth > depth) {
					for(int j=0; j < (import_prevDepth - depth); j++) {
						out.println("</ul></li>");
					}
				}
				
				out.println("<li id='import_"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' "+_class+"><a href='#'><ins> </ins>"+nodeName+"</a>");
				if(!hasChild) {
					out.println("</li>");
				}
				import_prevDepth = depth;
			}
		%>
		</li>
    </ul>
</div>
<%-- 트리 --%>

<%-- 컨텐츠관리 팝업 --%>
<div id="content_manager" title="<spring:message code="portal.label.32" text="컨텐츠 선택" />"></div>

<%-- 페이지 생성/수정시 다국어 관련 DIV --%>
<div id="tree_message_form_div" title="<spring:message code="portal.label.58" text="페이지명" />">
	<form id="tree_form">
	<table class="tablelist">
		<tr>
			<th><spring:message code="portal.label.58" text="페이지명" /></th>
			<td width="50%">
				<jsp:include page="/luxor/common/message.jsp">
					<jsp:param name="message_type" value="PORTAL.PAGE.TREE" />
					<jsp:param name="check_duplicate" value="Y" />
				</jsp:include>
			</td>
			<td>
				<span class="main-btn"><a href="#" onclick="_treesave();return false;"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
			</td>
		</tr>
	</table>
	</form>
</div>
<%-- 폴더 생성/수정시 다국어 관련 DIV --%>

<%-- 스타일 매니저 --%>
<%@ include file="../../content/contentStyleManager.jsp" %>
<%-- 스타일 매니저 --%>

<%-- 기타 숨김용 --%>
<div id="layout_css"></div>

</body>
</html>
