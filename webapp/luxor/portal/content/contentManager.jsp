<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.context.*" %>
 
<%
	TreeVO[] tree = (TreeVO[])request.getAttribute("tree");
	String treeId = (String)request.getAttribute("treeId");
	PortletCategoryVO[] portletCategories = (PortletCategoryVO[])request.getAttribute("portletCategories");
	String currentPortalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
	
	String currentPortalName = CommonUtil.nullTrim((String)request.getAttribute("currentPortalName"));
	TreeVO[] parentTree = null;
	String parentTreeId = null;			
	GroupPortalVO groupPortal = null;
	int parentPortalCount = (Integer)request.getAttribute("parentPortalCount") == null ? 0 : (Integer)request.getAttribute("parentPortalCount");
	String isParentPoral = CommonUtil.nullTrim((String)request.getAttribute("isParentPortal"));
	
	TreeVO[] pageTree = (TreeVO[])request.getAttribute("pageTree");
	String pageTreeId = "PORTAL_PAGE";
	
	TreeVO[] contentTree = (TreeVO[])request.getAttribute("contentTree");
	String contentTreeId = "PORTAL_CONTENT";
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><spring:message code="portal.btn.label.25" text="컨텐츠 관리" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/ep/luxor/ref/css/default_theme.css" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/content.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.disable.text.select.min.js"></script>
<script type="text/javascript">
	var currentPortalId = '<%=currentPortalId%>';
	var currentPortalName = '<%=currentPortalName%>';
	var selectedPortalId = "";
	var isParentPortal = "<%=isParentPoral%>";
	var parentPortalCount = <%=parentPortalCount%>;
	var mode = 'insert';
	var selectedContentId = 'PREVIEW_001';
	var _treeId = "<%=treeId%>";
	var searchDivId = "tree_search";
	var messageDivId = "tree_message_form_div";

    var currentPageId = "";
	var currentMenuId = "";
	// default theme로 보여줌
	var themeImageUrl = "/ep/luxor/ref/image/blue_theme";
	// 현재 선택된 노드
	var selectedNodeId = 'ROOT';

	//contentsearchKey
	var contentSearchKey = '';
	var contentSearchWay = '';

	// 트리의 각 노드에 우측의 컨텐츠를 드래그해서 놓을수있게 Drop Zone 셋팅
	var dragOverNodeId = ''; // Drop했을때 해당 노드 ID
	$(function() {
		$('#<%=treeId%> li a').droppable({
			over: function(e, ui) {
				$(this).addClass('bold hover');
				var nodeId = $(this).closest('li').attr('id');
				dragOverNodeId = nodeId;
			},
			out: function(e, ui) {
				$(this).removeClass('bold hover');
				dragOverNodeId = '';
			},
			deactivate: function(e, ui) {
				$(this).removeClass('bold hover');
			}
        });
	});
	
	var selectCallback = function(nodeId) {
		$('#btn_add_content').closest('.aright').show();
		isParentPortal="";
		selectedNodeId = nodeId;
		changePage(1);
	};
	
	var insertCallBack = function(nodeId) {
		document.location.reload();
	};
	
	function changePage(p) {
		var param = "";
		if(isParentPortal != "") {
			param = 'parentPortalId='+isParentPortal+'&parentId='+selectedNodeId+'&cPage='+p+'&searchKey='+ encodeURIComponent(contentSearchKey)+'&searchWay='+contentSearchWay;
		} else {
			param = 'parentId='+selectedNodeId+'&cPage='+p+'&searchKey='+ encodeURIComponent(contentSearchKey)+'&searchWay='+contentSearchWay;
		}
		$('#content_list').load('/ep/content/list.do?'+param, function() {
			// 컨텐츠 목록에서 컨텐츠명을 Drag할수 있게 셋팅함
			$('td[mode=drag] span').each(function() {
				$(this).draggable({
					opacity: 0.3,
					helper: 'clone',
					stop: function(e, ui) {
						if(!luxor.isNullOrEmpty(dragOverNodeId)) {
							var contentId = $(this).attr('id');
							var param = "contentId="+contentId+"&parentId="+dragOverNodeId;
							callJson("portalContentController","updateContentParentId",param,function(data) {
								alert("<spring:message code="portal.alert.msg.37" text="이동되었습니다." />");
								_selectNode(dragOverNodeId);
								dragOverNodeId = "";
							});
						}
					}
				});
			});
		});
	}
	
	/**
	 * 컨텐츠 미리보기 로딩
	 */
	function loadPreview(data,typeOfPortlet,viewType) {
		if(viewType == null) {
			viewType = mode;
		} 
		$('#content_preview > ul').html('');
		if($('#block_layer').attr('src') == null) {
			$('#content_preview').closest('.list-box').prepend('<iframe id="block_layer" frameborder="0" allowTransparency="true" src="/ep/luxor/common/jsProxy/transparent.html" style="height: 50%;width: 50%;position: absolute;top: 135px;"></iframe>');
		}
		if((typeOfPortlet >= 2 && typeOfPortlet <=4)  && viewType == 'update') {
			$('#block_layer').remove();
		}
		
		$.getJSON('/ep/luxor/common/jsProxy/checkPortletUrlInfo.jsp?portletId='+data[0].portletId+'&cacheTime='+new Date(), function(json) {
			var menuArr = ['reload','min','max','setup'];
			//포틀릿에 edit와 help url이 있어야만 추가가 됩니다.
			if(json.edit) menuArr.push('edit');
			if(json.help) menuArr.push('help');
			content.setMenuList(menuArr);
			
			//menu포틀릿이나 tab포틀릿의 경우는 수정화면에서 컨텐츠나 메뉴를 설정할 수 있어야 한다. 
			if((typeOfPortlet >= 2 && typeOfPortlet <=4 && viewType =='insert')) {
				content.setPreview(false);
			} else {
				content.setPreview(true);
				
			}
			content.load(data);
			if(typeOfPortlet == 2 && viewType == 'insert') {
				$('#preview_text').html("<spring:message code="portal.content.alert.2" text=" * 메뉴 설정은 컨텐츠 등록 후에 수정모드에서 입력할 수 있습니다." />");
			}else if(typeOfPortlet == 3 && viewType == 'insert'){
				$('#preview_text').html("<spring:message code="portal.content.alert.13" text=" * 메뉴 설정은 컨텐츠 등록 후에 수정모드에서 입력할 수 있습니다." />");
			}else {
				$('#preview_text').html("");
			}
		});
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

	$(window).scroll(function() {
		resizeTreeHeight();
	});
	$(window).resize(function() {
		setTimeout("resizeTreeHeight()",400);
	});

	//drag시 previous treewidth
	var prevTreeWidth = '';
	var prevPageTreeWidth = 0;
	
	$(document).ready(function() {
		content.refer='CM';
		var usePersonalParam = 'usePersonal=Y';
		callJson("portalContentController", "getPersonalCatalog", usePersonalParam, function(data) {
			for(var i=0; i<data.length; i++) {
				$('#tree_wrap #'+data[i].nodeId+'> a').append("&nbsp<img src='/ep/luxor/ref/image/admin/tree_icon04.gif' style='vertical-align:bottom;' alt='[P]' title='사용자 개인화 페이지에서 사용가능한 카테고리' rel='tooltip'/>");
				$('#tree_wrap #'+data[i].nodeId).attr('usepersonal','Y');
			}
		});

		$.tree.focused().select_branch("#ROOT");

		// 컨텐츠 추가 -> 포틀릿 목록 hover 적용
		$('#portletList > li').live('mouseover mouseout', function(e) {
			if(e.type=='mouseover') {
				$(this).addClass('hover');
			} else {
				$(this).removeClass('hover');
			}
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
		
		// 바로추가 dialog좌우 크기조정 드래그 바
		$('#page_div_resize_bar').draggable({
			axis: 'x',
			drag: function(e, ui) {
				var pos = $(this).offset();
				$('#page_tree_wrap').width(pos.left-380);
				$('#page_wrap').width($('#page_wrap').width()-prevPageTreeWidth+pos.left);
				prevPageTreeWidth=pos.left;
			},
			stop: function(e, ui) {
				$('#page_wrap').width($('#page_tree_wrap').width()+$('#selected_page_wrap').width()+50);
			}
		});

		//상위 포탈 컨텐츠에서 컨텐츠 복사 클릭시
		$('#content_list tr a[name=copyContent]').live('click', function() {
			//메뉴포틀릿인 경우 메뉴를 공유하기위해 컨텐츠ID를 동일하게 가져갈 수 있도록 한다.(대신 디자인 수정 불가)
			var contentId = $(this).attr('contentId');
			$('#portletId').val($(this).attr('portletId'));
			var typeOfPortlet = $(this).attr('typeOfPortlet');
			var portalId = $(this).attr('portalId');
			if(luxor.isNullOrEmpty(contentId)) {
				return;
			}
			selectedContentId = contentId;
			//if($(this).attr('typeOfPortlet') == 2) {
			//	if(confirm('<spring:message code="//portal.content.alert.3" text="해당 컨텐츠의 메뉴를 함께 공유하시겠습니까?" />\n\n' +
			//			'<spring:message code="portal.content.alert.4" text="공유된 메뉴는 상위포탈 관리자에 의해 변경될 수 있습니다." />\n\n' +
			//			'<spring:message code="portal.content.alert.5" text="공유를 원치 않으시면 취소를 눌러주세요" />')) {
			//TODO
			//	} 
			//}
			mode = 'insert';
			$('#content_form_div').dialog({title:'<spring:message code="portal.content.label.3" text="컨텐츠복사" />'});
			$('#content_form_div .admin-tree-btn-group').hide();
			$('#content_form_div').dialog('open');
			$('#content_copy').show();
			$('#content_register').hide();
			callJson('portalContentController','getContent','contentId='+contentId+'&portalId='+portalId,function(data) {
				$('#'+data.portletId).css('background','#c0c0c0');
				$('#contentDesc').val(data.contentDesc);
				data.zoneId = 'content_preview';
				loadPreview([data],typeOfPortlet);
				cMessage.setMessageId(data.contentNameId, function() { cMessage.save(); });
			});	
		});
		
		// 컨텐츠 추가 버튼 클릭시 
		$('#btn_add_content').click(function() {
			mode = 'insert';
			$('#contentDesc').val('');
			$('#content_form_div').dialog({title:'<spring:message code="portal.btn.label.22" text="컨텐츠 추가" />'});
			$('#content_form_div .admin-tree-btn-group').show();
			$('#content_form_div').dialog('open');
			$('#content_copy').hide();
			$('#content_register').show();
			return false;
		});
	
		// 우측 컨텐츠 목록 삭제 클릭시
		$('#content_list tr a[name=del]').live('click', function(event) {
			if(confirm('<spring:message code="portal.alert.msg.11" />')) {
				var contentId = $(this).attr('contentId');
				callJson("portalContentController", "deleteContent", "contentId="+contentId, function(data) {
					if(eval(data)==true) {
						alert('<spring:message code="portal.alert.msg.8" />');
						selectCallback(selectedNodeId);
					} else {
						alert('<spring:message code="portal.alert.msg.9" />');
					}
				});
			}
			
			return false;
		});

		// 우측 컨텐츠 목록 권한설정 화면 로딩
		$('#content_list tr a[name=priv]').live('click', function(e) {
			var contentId = $(this).attr('contentId');
			luxor.popup("/ep/acl/aclSetupUser.do?resourceID="+contentId, {width:850,height:600});

			return false;
		});
		
		// 우측 컨텐츠 목록 수정 화면 로딩
		$('#content_list tr a[name=mod]').live('click', function(e) {
			mode = 'update';
			var contentId = $(this).attr('contentId');
			var typeOfPortlet = $(this).attr('typeOfPortlet');
			if(luxor.isNullOrEmpty(contentId)) {
				return;
			}
			selectedContentId = contentId;

			$('#content_form_div').dialog({title:'<spring:message code="portal.btn.label.23" text="컨텐츠 수정" />'});
			$('#content_form_div .admin-tree-btn-group').show();
			$('#content_form_div').dialog('open');
			$('#content_copy').hide();
			$('#content_register').show();
			callJson('portalContentController','getContent','contentId='+contentId,function(data) {
				$('#'+data.portletId).css('background','#c0c0c0');
				
				$('#contentDesc').val(data.contentDesc);
				data.zoneId = 'content_preview';
				loadPreview([data],typeOfPortlet);
				cMessage.setMessageId(data.contentNameId, function() { cMessage.save(); });
			});
			
			return false;
		});
		
		
		// 저장 버튼 클릭시
		$('#btn_form_save').click(function() {
			var method = mode +'Content';
			$('#parentId').val(selectedNodeId);
			var param = $('#content_form').serialize();
			if(mode=='update') {
				param += '&contentId='+selectedContentId;
			}
			callJson('portalContentController', method, param, function(data) {
				if(eval(data)) {
					alert('<spring:message code="portal.alert.msg.13" />');
				}
				
				// reset field
				cMessage.reset();
				$('textarea').val('');
				$('#portletId').val('');
				$.tree.focused().select_branch("#"+selectedNodeId);
				$('#btn_form_cancel').click();
			});
			return false;

		});

		
		// 취소 버튼 클릭시
		$('#btn_form_cancel').click(function() {
			$('#content_copy').hide();
			$('#content_register').show();
			$('#content_form_div').dialog('close');
			$('#content_preview > ul > li').remove();
			content.style.cancel();
			
			return false;
		});

		
		// 전체 포틀릿 목록 로딩
		$('#portletList').load("/ep/luxor/portal/content/portletList.jsp?categoryId=all", function() {
			$('#portletList > li:first').css('margin-top','5px');
		});
		
		// 카테고리 선택시 해당 포틀릿만 로딩
		$('#portletCategory').change(function() {
			$('#portletList').load("/ep/luxor/portal/content/portletList.jsp?categoryId="+$(this).val(), function() {
				$('#portletList > li:first').css('margin-top','5px');
			});
		});

		// 포틀릿목록 클릭시 미리보기 로딩
		$('#portletList > li').live('click', function() {
			$(this).siblings().css('background','#FFFFFF');
			$(this).css('background','#c0c0c0');
			var selectedId = $(this).attr('id');
			var selectedPortletType = $(this).attr('name');
			
			$('#portletId').val(selectedId);
			var previewUrl = content.getPortletPreviewUrl(selectedId);

			var _data = [{"contentId":selectedContentId,"portletId":selectedId,"messageName":$('#'+selectedId.replace(/\./g,'\\.')).text(),"zoneId":"content_preview","contentStyle":"","contentStyleForEach":""}];
			var data = eval(_data);
			loadPreview(data,selectedPortletType); // 미리보기 로딩

			// 제목 다국어 셋팅
			if($.browser.msie==true) {
				//동적 페이지 파싱하는데  딜레이 걸리는 IE만 좀 있다가 값 세팅
				setTimeout(function() {
					setContentMessage(selectedId.replace(/\./g,'\\.'));
				}, 500);
			} else {
				setContentMessage(selectedId.replace(/\./g,'\\.'));
			}
			
			return false;
		});
		
		// 컨텐츠 관리 페이지 검색
		$('#input_contentManagerSearch').keypress(function(e) {
			if(e.keyCode==13) {
				$('#btn_contentManagerSearch').click();
			}
		});
		
		$('#btn_contentManagerSearch').click(function() {
			contentSearchKey = $('#input_contentManagerSearch').val();
			contentSearchWay = $('#select_contentManagerSearch').val();
			changePage(1);
			
			return false;
		});

		// 컨텐츠 추가/수정 페이지 검색
		$('#input_portletSelectorSearch').keypress(function(e) {
			if(e.keyCode==13) {
				$('#btn_portletSelectorSearch').click();
			}
		});
		
		$('#btn_portletSelectorSearch').click(function() {
			$('#portletList li').hide();
			$('#portletList li').each(function() {
				if($(this).html().toUpperCase().indexOf($('#input_portletSelectorSearch').val().toUpperCase()) > -1 
						|| $(this).text().toUpperCase().indexOf($('#input_portletSelectorSearch').val().toUpperCase()) > -1) {
					$(this).fadeIn('slow');
				}
			});
			
			return false;
		});

		setTimeout("resizeTreeHeight()",400);

		//개인화 용도로 사용할지 여부
		$('#btn_use_personal_category').live('click', function() {
			var t = $.tree.reference($('#'+_treeId));
	    	if(t.selected) {
	        	if(t.selected.attr("id")=="ROOT") {
	            	alert(portal_alert_msg_6);
	            	return;
	        	}
	        	var strUsePersonal = 'Y';
	        	if(t.selected.attr("usepersonal")=='Y') {
		        	strUsePersonal = 'N'
	        	}
	        	var param = "nodeId="+t.selected.attr("id")+"&usePersonal="+strUsePersonal;
	        	callJson("portalContentController","setCategoryUsePersonal",param,function(data) {
					alert("<spring:message code="portal.alert.msg.16" text="적용되었습니다." />");
					selectCallback($.tree.reference($('#'+_treeId)).selected.attr("id"));
					var usePersonalParam = 'usePersonal=Y';
					$('#tree_wrap .ui-droppable img').remove();
					$('#tree_wrap .ui-droppable').closest('li').attr('usepersonal','N');
					callJson("portalContentController", "getPersonalCatalog", usePersonalParam, function(data) {
						for(var i=0; i<data.length; i++) {
							$('#tree_wrap #'+data[i].nodeId+'> a').append("<img src='/ep/luxor/ref/image/admin/tree_icon04.gif' style='vertical-align:bottom;' alt='[P]' title='사용자 개인화 페이지에서 사용가능한 카테고리' rel='tooltip'/>");
							$('#tree_wrap #'+data[i].nodeId).attr('usepersonal','Y');
						}
					});
				});
	    	} else {
	        	alert(portal_alert_msg_7);
	    	}
		});

		// 개인화 설정
		$('#content_list tr a[name=personal]').live('click', function(e) {
			var strUsePersonal = 'Y';
        	if($(this).attr('usePersonal')=='Y') {
	        	strUsePersonal = 'N'
        	}
			var param = "contentId="+$(this).attr('contentId')+"&usePersonal="+strUsePersonal;
			callJson("portalContentController","setContentUsePersonal",param,function(data) {
				var pageNum = $('.paging strong').html();
				if(pageNum == null) {
					pageNum = 1;
				}
				changePage(pageNum);
			});
		});

		$("#block_layer").attr('style','position:absolute;width:50%;height:50%;visibility:visible;background-color:transparent;margin-top:35px;' ); 

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
					$('#btn_add_content').closest('.aright').hide();
					if($(node).attr('id').indexOf('ROOT') > -1) {
						isParentPortal=$(node).attr('name');
						selectedNodeId = 'ROOT';
					} else {
						isParentPortal=$(node).closest('ul').attr('name');
						selectedNodeId = $(node).attr('id');
					}
					changePage(1);
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

		// 팝업 셋팅
		$('#content_form_div').dialog({
			autoOpen:false,
			modal:true,
			resizable:false,
			width:830,
			position:['center',50],
			close:function(event, ui) { // 팝업창 닫을때 초기화
				$('#styleManager').dialog('close');
				$('#portletList > li').css('background','#FFFFFF');
				$('#portletId').val('');
				$('#input_portletSelectorSearch').val('');
			}
		});

		$('#page_form_div').dialog({
			autoOpen:false,
			resizable:false,
			modal:true,
			width:930,
			position:['center',50]
		});


		//페이지에  바로등록 버튼 클릭시
		$('#content_list tr a[name=directRegister]').live('click', function() {
			$('#page_form_div').dialog({title:'<spring:message code="portal.content.label.4" text="페이지에 바로등록" />'});
			$('#page_form_div').dialog('open');
			selectedContentId = $(this).attr('contentId');
			$('#selected_page').html('');
			callJson("portalPageZoneController", "getContentIncludingPages", "contentId="+selectedContentId, function(data) {
				for(var i = 0 ; i < data.length ; i++ ) {
					insertRow(data[i].pageId, data[i].zoneId, data[i].messageName);
				}
			});
			return false;
		});
		
		//페이지에 바로등록시 페이지 트리
		if("<%=pageTree.length%>" != "0") {
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
						$(node).closest('#PORTAL_PAGE').attr('name',$(node).attr('id'));
						var nodeId = $(node).attr('id');
						var parentId = $(node).attr('parentId');
						
						if(nodeId=='PAGE_ROOT') {
							return false;
						}
						if(luxor.isNullOrEmpty(parentId)) {
							parentId = 'ROOT';
						}
						
						// 페이지 트리의 중간 depth를 바로 추가할때 parentId를 ROOT로 변경
						if(luxor.isNullOrEmpty($('#sp_'+parentId).text())) {
							parentId = "ROOT";
						}
						
						// 중복체크
						if(luxor.isNullOrEmpty($('#sp_'+nodeId).text())) {
							var targetedZone = $('input[name=defaultTargetZone]:checked').val();
							insertRow(nodeId, targetedZone, "");
							callJson("portalPageZoneController", "insertContentOnZone", "pageId="+nodeId+"&zoneId="+targetedZone+"&contentId="+selectedContentId, function(data) {
								if(eval(data)==true) {
								} else {
									alert('<spring:message code="portal.alert.msg.9" />');
								}
							});
						} 
					}
				}
			});
		} else {
			$("#PAGE_ROOT").append('<br> no page found');
		}
		$("#PAGE_ROOT li[rel=first] a").click();

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
		
		$('.PAGE_INPUT').keypress(function(e) {
	        if(e.keyCode==13) {
	        	$('#page_tree_search a').click();
	        }
	    });
	    
		// 부모 포탈 페이지 트리 검색 처리
	    $('#page_tree_search a').click(function(e) {
	    	$.tree.reference($('#PAGE_ROOT')).search($('#page_tree_search :text').val());
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
					selectedNodeId =  $(node).attr('id');
				}
			}
		});
	}); // $(document).ready
	
	// 제목 다국어 셋팅
	function setContentMessage(selectedId) {
		$('#c__message_input').val($('#'+selectedId).text());
		$('#c__message_multi_input :text').val($('#'+selectedId).text());
		cMessage.save();
	}
	
	function resizeTreeHeight() {
		$('#resize_bar').height($(document).height()-20);
		$('#tree_wrap').height($(document).height()-20);
		$("#page_tree_accordion").attr('style','clear:both;padding:0px;visibility:visible;');
	}

	//페이지에 바로등록시 선택된 페이지 그리기
	function insertRow(nodeId, targetedZone, messageName) {
		var _targetDIV = '#selected_page';
		var header = "", footer = "", left = "", content = "", right = "";
		if(targetedZone == 'header') {
			header="class='clicked'";
		} else if(targetedZone == 'left') {
			left="class='clicked'";
		} else if(targetedZone == 'content') {
			content="class='clicked'";
		} else if(targetedZone == 'right') {
			right="class='clicked'";
		} else if(targetedZone == 'footer') {
			footer="class='clicked'";
		}
		if(messageName == "") {
			messageName = _getNodeName(nodeId);
		}
		$(_targetDIV).append("<div id='sp_"+nodeId+"' contentId='"+selectedContentId+"' class='sp_row'>"
			+"<table><tr><td class='sp_row_text'><span class='plusminus'>-</span> "+ messageName
			+"<td class='sp_row_conf'>"
			+"<a href='#none' mode='header' "+header+"><spring:message code="portal.content.label.5" text="상단" /></a> | "
			+"<a href='#none' mode='left' "+left+"><spring:message code="portal.content.label.6" text="좌측" /></a> | "
			+"<a href='#none' mode='content' "+content+"><spring:message code="portal.content.label.7" text="중앙" /></a> | "
			+"<a href='#none' mode='right' "+right+"><spring:message code="portal.content.label.8" text="우측" /></a> | "
			+"<a href='#none' mode='footer' "+footer+"><spring:message code="portal.content.label.9" text="하단" /></a> | "
			+"<a href='#none' mode='del'><spring:message code="portal.btn.label.30" text="삭제" /></a>"
			+"</td></tr></table></div>");
	}

	// 옵션버튼 클릭시
	$('div[id] a').live('click', function() {
		var nodeId = $(this).closest('div[id]').attr('id').substring(3);
		var contentId = $('#sp_'+nodeId).attr('contentId');
		if($(this).attr('mode')=='del') {
			var param = "pageId="+nodeId+"&contentId="+contentId;
			callJson("portalPageZoneController", "deleteContentOnZone", param, function(data) {
				if(eval(data)) {
					$('#sp_'+nodeId).remove();
				}
			});
		} else {
			var param = "pageId="+nodeId+"&contentId="+contentId;
			var zoneId = $(this).attr('mode');
			$(this).siblings('a').attr('class','');
			$(this).attr('class','clicked');
			callJson("portalPageZoneController", "deleteContentOnZone", param, function(data) {
				if(eval(data)) {
					callJson("portalPageZoneController", "insertContentOnZone", "pageId="+nodeId+"&zoneId="+zoneId+"&contentId="+contentId, function(data) {
						if(eval(data)==true) {
						} else {
							alert('<spring:message code="portal.alert.msg.9" />');
						}
					});
				}
			});
		}
	});
</script>
</head>

<body>
<div id="window_scroll_wrap">
	<div id="admin_wrap" class="admin-wrap">
		<div id="tree_wrap" class="tree-wrap">
			<div class="page-tree-wrap" id="page_tree_accordion" style="clear:both;padding:0px;">
				<h3><a href="#" id="main_page_tree"><%=currentPortalName %></a></h3>
				<div>
					<%-- 폴더 추가/수정/삭제 버튼 --%>
					<div class="admin-tree-btn-group">
						<a href="#" onclick="_showCreateForm();return false;"><img src="/ep/luxor/ref/image/admin/icon_06.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.5" />" title="<spring:message code="portal.btn.label.5" />"></a>
						<a href="#" onclick="_showModifyForm();return false;"><img src="/ep/luxor/ref/image/admin/icon_07.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.6" />" title="<spring:message code="portal.btn.label.6" />"></a>
						<a href="#" onclick="_deleteFolder();return false;"><img src="/ep/luxor/ref/image/admin/icon_08.gif" style="width:20px;" alt="<spring:message code="portal.btn.label.7" />" title="<spring:message code="portal.btn.label.7" />"></a>
						<span class="smain-btn" style="position: relative; top: 2px;"><a href="#" id="btn_use_personal_category" title="<spring:message code="portal.content.alert.6" text="카테고리를 선택후 이 버튼을 누르면 해당 카테고리에 속한 컨텐츠를 개인화에 사용할 수 있게 됩니다."/>"><spring:message code="portal.label.7" text="개인화설정"/></a></span>
					</div>
					
					
					<%-- 폴더 추가/수정/삭제 버튼 --%>
				
					<%-- 트리 검색 --%>
					<div id="tree_search" class="admin-tree-btn-group">
						<input type="text" class="input-txtfield w100"/>
						<span class="main-btn"><span class="search"></span><a href="#"><spring:message code="portal.btn.label.8" /></a></span>
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
									String usePersonal = tree[i].getUsePersonal();
									int depth = tree[i].getDepth();
									int seq = tree[i].getSeq();
									boolean hasChild = tree[i].getHasChild().equals("Y");
									
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
				<h3><a href="#"><%=groupPortal.getPortalName() %></a></h3>
				<div>
					
					<div class="admin-tree-btn-group parent-tree-search">
						<input type="text" class="input-txtfield w100 INPUT_ROOT_<%=j%>" />
						<span class="main-btn"><span class="search"></span><a href="#" class="PARENT_<%=parentTreeId%>_<%=j%>"><spring:message code="portal.btn.label.8" text="검색" /></a></span>		
					</div>
					
			
					
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
									String usePersonal = parentTree[i].getUsePersonal();
									int depth = parentTree[i].getDepth();
									int seq = parentTree[i].getSeq();
									boolean hasChild = parentTree[i].getHasChild().equals("Y");
									
									String _class = "";
									if(nodeId.equals("root")) {
										//_class = "class='open'";
									}
									
									if(depth > prevDepth) {
										out.println("<ul name="+groupPortal.getPortalId()+">");
									}
									
									if(prevDepth > depth) {
										for(int k=0; k < (prevDepth - depth); k++) {
											out.println("</ul></li>");
										}
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
				<% } %>
				
			</div>
		</div>
		
		<%-- 왼쪽(트리), 오른쪽(페이지) 크기 리사이즈해주는 바 --%>
		<div id="resize_bar" class="resize-bar"></div>
		
		<%-- 페이지 구성 --%>
		<div id="container" class="admin-content-width w1000 ml15">
			<%-- 타이틀 & 네비게이션 --%>
			<div class="title-sub">
				<span class="title-sub-text"><spring:message code="portal.btn.label.25" text="컨텐츠 관리"/></span>
				<%-- 컨텐츠 추가 버튼 --%>
				<div class="aright">
					<span class="main-btn"><a href="#" id="btn_add_content"><spring:message code="portal.btn.label.22" text="컨텐츠 추가"/></a></span>
				</div>
			</div>
			<!-- table_write -->
			<table class="table-search">
			<thead>
				<tr>
					<th width="90"><spring:message code="portal.label.21" text="검색어" /></th>
					<td colspan="3">
	   					<select id=select_contentManagerSearch style="width:10%">
	     					<option value='contentName'><spring:message code="portal.label.28" text="컨텐츠명" /></option>
	      					<option value='portletId'><spring:message code="portal.label.27" text="포틀릿ID" /></option>
	      					<option value='contentId'><spring:message code="portal.label.26" text="컨텐츠ID" /></option>
	   					</select>
						<input type="text" class="input-d" id="input_contentManagerSearch" style="WIDTH: 89%;"/>
					</td>
					<td width="62" rowspan="2">
						<span class="smain-btn">
							<a href="#" id="btn_contentManagerSearch"><span class="btnicon-01"></span><spring:message code="portal.btn.label.8" text="검색" /></a>
						</span>  
					</td>
				</tr>
			</thead>
			</table>
			<!-- //table_write -->     
			<%-- 컨텐츠 목록 --%>
			<div id="content_list" class="main"></div>
		</div>
	</div>
</div>

<%-- 컨텐츠 추가 Hidden Form --%>
<div id="content_form_div" title="<spring:message code="portal.btn.label.22" text="컨텐츠 추가" />">
	<div class="aleft">
		<div class="box-font-blue" style="float: left;padding:15px 10px 0px 0px;">
			- <%=currentPortalName %> <spring:message code="portal.page.label.21" text="포탈의 컨텐츠로 등록합니다."/>
	   	</div>
	</div>
	<%-- 검색 --%>
	<div class="admin-tree-btn-group" style="float:right; padding:5px 0px 5px 0px;">
		<input id="input_portletSelectorSearch" class="input-txtfield w120"/>
		<span class="main-btn"><span class="search"></span><a href="#" id="btn_portletSelectorSearch"><spring:message code="portal.btn.label.8" text="검색" /></a></span>		
	</div>
	<%-- 검색 --%>
	<form id="content_form">
	<input type="hidden" id="contentStyle" name="contentStyle" />
	<input type="hidden" id="portletId" name="portletId" />
	<input type="hidden" id="parentId" name="parentId" />
	<input type="hidden" id="inserted__messageId" name="messageId" />
	<input type="hidden" id="inserted__message_input_all" name="messageNameAll" />
	<input type="hidden" id="inserted__messageType" name="messageType" />
	<table class="table-write">
		<colgroup><col width="130px" /><col width="*" /></colgroup>
		<tbody>
		<tr>
			<th><spring:message code="portal.content.label.13" text="컨텐츠 설정" /></th>
			<td>
				<div id="portlet-wrap mt10">
					<div>
						<table border="0" width="100%">
							<colgroup><col width="200px" /><col width="20px" /><col width="*" /></colgroup>
							<tr> 
								<td class="list-box" style="padding-top:10px;" id="content_register">
									<span class="admin-sub-title mb10"><spring:message code="portal.label.42" text="포틀릿 목록" /></span>
									<select id="portletCategory" style="margin-top:10px;display:block;width:100%">
										<option value="all">All</option>
									<%	for(PortletCategoryVO portletCategory : portletCategories) { %>
										<option value="<%=portletCategory.getCategoryId()%>"><%=portletCategory.getCategoryName()%></option>
									<% 	} %>
									</select>
									<div id="portletList" style="margin-top:10px;height:260px;overflow:auto;">
										<ul>
										</ul>
									</div>
								</td>
								<td class="list-box" style="padding-top:10px;display:none;" id="content_copy">
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
									<span id="preview_text" ></span>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="portal.label.37" text="설명"/></th>
			<td><textarea id="contentDesc" name="contentDesc" class="text" style="width:99%;height:100px;"></textarea></td>
		</tr>
		<tr>
			<th colspan="2" align="center">
				<span class="main-btn"><a href="#" id="btn_form_save"><spring:message code="portal.btn.label.3" text="저장" /></a></span>
				<span class="main-btn"><a href="#" id="btn_form_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span>
			</th>
		</tr>
		</tbody>
	</table>
	</form>
</div>
<%-- 컨텐츠 추가 Hidden Form --%>

<%-- 폴더 생성/수정시 다국어 Hidden Form --%>
<div id="tree_message_form_div" title="<spring:message code="portal.label.43" text="폴더명 입력" />">
	<form id="tree_form">
	<table class="tablelist">
		<tr>
			<th><spring:message code="portal.label.1" text="폴더명" /></th>
			<td>
				<jsp:include page="/luxor/common/message.jsp">
					<jsp:param name="check_duplicate" value="Y" />
					<jsp:param name="message_type" value="PORTAL.CONTENT.TREE" />
				</jsp:include>
			</td>
			<td>
				<span class="main-btn"><a href="#" onclick="_treesave();return false;"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
			</td>
		</tr>
	</table>
	</form>
</div>
<%-- 폴더 생성/수정시 다국어 Hidden Form --%>

<%-- 페이지 바로등록 Hidden Form --%>
<div id="page_form_div" title="<spring:message code="portal.content.label.4" text="페이지에 컨텐츠 바로등록" />">
	<div id="page_wrap" class="menu_selector_wrap">
		<div class="title" style="text-indent:7px"><span id="pageName"></span></div>
		<div id="page_tree_wrap" class="tree-wrap" style="width:220px;height:480px">
			<%-- 트리 검색 --%>
			<div id="page_tree_search" class="admin-tree-btn-group">
				<input type="text" class="input-txtfield w100 PAGE_INPUT" />
				<span class="main-btn"><span class="search"></span><a href="#"><spring:message code="portal.btn.label.8" text="검색" /></a></span>		
			</div>
			<%-- 트리 검색 --%>
		
			<%-- 트리 --%>
			<div id="<%=pageTreeId%>" class="page_tree admin-tree-padding" style="margin-right:15px;overflow-x:auto;">
			    <ul>
			    	<li id="PAGE_ROOT" class="open"><a href='#'><ins></ins><spring:message code="portal.label.4" text="페이지트리" /></a>
					<%
						prevDepth = 0;
						for(int i=0; i < pageTree.length; i++) {
							String nodeId = pageTree[i].getNodeId();
							String nodeName = pageTree[i].getNodeName();
							String nodeNameId = pageTree[i].getNodeNameId();
							String parentId = pageTree[i].getParentId();
							int depth = pageTree[i].getDepth();
							int seq = pageTree[i].getSeq();
							boolean hasChild = pageTree[i].getHasChild().equals("Y");
							
							String _class = "";
							if(nodeId.equals("root")) {
								_class = "class='open'";
							}
							
							if(depth > prevDepth) {
								out.println("<ul>");
							}
							if(prevDepth > depth) {
								for(int j=0; j < (prevDepth - depth); j++) {
									out.println("</ul></li>");
								}
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
		<%-- 왼쪽(트리), 오른쪽(페이지) 크기 리사이즈해주는 바 --%>
		<div id="page_div_resize_bar" class="resize-bar" style="top:60px;left:220px;"></div>
		<div id="selected_page_wrap" class="selected-page-wrap">
			<h6 class="admin-sub-title"><spring:message code="portal.label.17.1" text="선택된 페이지" /></h6>
			<div style="float:right;margin-top:5px;margin-right:5px;"><spring:message code="portal.content.label.11" text="기본 등록 영역 : " />&nbsp&nbsp
			<spring:message code="portal.content.label.5" text="상단" />
			<input type="radio" name="defaultTargetZone" value="header" style="margin-right:5px;margin-left:0px;"/>
			<spring:message code="portal.content.label.6" text="좌측" />
			<input type="radio" name="defaultTargetZone" value="left" style="margin-right:5px;margin-left:0px;"/>
			<spring:message code="portal.content.label.7" text="중앙"/>
			<input type="radio" name="defaultTargetZone" value="content" checked="checked" style="margin-right:5px;margin-left:0px;"/>
			<spring:message code="portal.content.label.8" text="우측" />
			<input type="radio" name="defaultTargetZone" value="right" style="margin-right:5px;margin-left:0px;"/>
			<spring:message code="portal.content.label.9" text="하단" />
			<input type="radio" name="defaultTargetZone" value="footer" style="margin-right:5px;margin-left:0px;"/>
			</div>
			<div id="selected_page"></div>
		</div>
	</div>
</div>
<%-- 스타일 매니저 --%>
<%@ include file="contentStyleManager.jsp" %>
<%-- 스타일 매니저 --%>

<div id="alphabg"></div>

</body>
</html>