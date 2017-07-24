<%/* 
	이 페이지를 호출하는 페이지 : /portal/page/admin/pageManager.jsp 
*/%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>

<%
	PortalPageVO[] templates = (PortalPageVO[])request.getAttribute("templates");
	PortalPageLayoutVO[] layouts = (PortalPageLayoutVO[])request.getAttribute("layouts");
	TreeVO[] tree = (TreeVO[])request.getAttribute("tree");
	String treeId = (String)request.getAttribute("treeId");
	int contentMaximum = LuxorConfig.getEnvInt(request, "USE_CONTENTS_MAXIMUM");	
	String contentId = UtilRequest.getString(request,"contentId","");
	int typeOfPortlet = Integer.parseInt(UtilRequest.getString(request,"typeOfPortlet","3"));
	

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

	var contentMaximum = "<%=contentMaximum%>";
	var portal_alert_msg_75 = "<spring:message code="portal.alert.msg.75" text="템플릿 선택시 기존에 등록된 내용은 모두 삭제됩니다. 계속하시겠습니까?" />";
	var portal_alert_msg_185 = "<spring:message code="portal.alert.msg.185" text="개 이상의 포틀릿은 등록하실 수 없습니다." />";
	var portal_alert_msg_76 = "<spring:message code="portal_alert_msg_76" text="개인이 설정한 화면 설정이 초기화됩니다. 계속하시겠습니까?" />";
	var portal_alert_msg_77 = "<spring:message code="portal.content.alert.7" text="화면 구성 레이아웃이 변경됩니다. 계속하시겠습니까?" />";
	var portal_alert_msg_79 = "<spring:message code="portal.content.alert.8" text="고정된 컨텐츠가 속한 영역은 제거하실 수 없습니다." />";
	var portal_alert_msg_9 = "<spring:message code="portal.portlet.alert.9" text="탭포틀릿 컨텐츠를 추가할 수 없습니다." />";
	var portal_alert_msg_10= "<spring:message code="portal.portlet.alert.10" text="iframe포틀릿만 추가할 수 있습니다." />";
	
	var deletedAdminContentId = "";
	
	var selectCallback = function(nodeId) {
		$('#btn_add_content').closest('.aright').show();
		isParentPortal="";
		selectedNodeId = nodeId;
	};
	
	/**
	 * 트리에서 첫번째 노드(페이지) 선택
	 */
	function selectFirstPage() {
		if(selectedNodeId=='ROOT') {
			selectedNodeId = $('#ROOT ul li').first().attr('id');
		}
		$.tree.focused().select_branch("#"+selectedNodeId);
	}
	
	$(function() {
		$( "#contentSelector_accordion" ).accordion({
			autoHeight: false,
			navigation: true,
			event: "click"
		});
		
	
		// 컨텐츠 선택 팝업 로딩
			$('#content_manager').dialog({title:portal_btn_label_22, width:800,height:500});
			
			// 트리초기화
			$("#<%=treeId%>").tree({
		    	ui: {
					theme_name : "classic"
				},
				callback: {
					onselect: function(node, tree_obj) {
						$('#content_list').load('/ep/content/listSimple.do?parentId='+$(node).attr('id') , function() {
							$('#selected_content_list div').each(function() {
								var id = $(this).attr('sid');
								$('#content_list #'+id+' > a').attr('style' , 'color:red;');
							});
							if($(node).attr('id') != 'ROOT' && $(node).attr('hasChild') == 'Y'){
								if($(node).hasClass('open')){
									$.tree.reference($(node)).close_branch(node);
									$(node).removeClass('open');
									$(node).addClass('closed');

								}else{
									$.tree.reference($(node)).open_branch(node);
									$(node).removeClass('closed');
									$(node).addClass('open');
								}						
							}

						});
					}
				},
				types: {
					'default': {
						draggable : false
					}
				}
			});
			
			// 좌우 크기조정 드래그 바
			$('#resize_bar2').draggable({
				axis: 'x',
				drag: function(e, ui) {
					var pos = $(this).position();
					$('#cs_tree_wrap').width(pos.left);
					$('#cs_admin_wrap').width($('#cs_tree_wrap').width()+550);
					$(this).attr('style','position:absolute;top:55px;');
				},
				stop: function(e, ui) {
					var pos = $(this).position();
					$(this).attr('style','position:relative;height:450px;float:left;margin-left:-213px;');
					$('#cs_admin_wrap').width($('#cs_tree_wrap').width()+$('#container').width()+20);
				}
			});
			$('#resize_bar2').attr('style','top:55px;');		
			$('#content_manager').dialog('open');
			
			// 기존에 선택된 목록 로딩
			callJson("generalPortletController", "getTabContentsList", "pageId="+selectedNodeId+"&tabPortletId=<%=contentId%>", function(data) {
				initData = data;
				for(var i=0; i < data.length; i++) {
					$('#selected_content_list').append("<div sid='"+data[i].contentId+"' class='link pop_content_div'><a href='#'> - "+data[i].messageName+"</a><img did='"+data[i].contentId+"' class='delete' title='delete' alt='delete' src='/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif' style='height: 10px;padding-right: 5px;padding-top: 5px;float: right;'/></div>");
				}
				$.tree.focused().select_branch("#PORTAL_CONTENT #ROOT");
			});

			// 창닫기버튼 클릭
			$('#btn_close_content_manager').live('click', function() {
				window.close();
			});	
			
			
			// 검색
			$('#input_contentSelectorSearch').keypress(function(e) {
				if(e.keyCode==13) {
					$('#btn_contentSelectorSearch').click();
				}
			});
			
			$('#btn_contentSelectorSearch').click(function() {
				$('#content_list .pop_content_div').hide();
				$('#content_list .pop_content_div').each(function() {
					if($(this).html().toUpperCase().indexOf($('#input_contentSelectorSearch').val().toUpperCase()) > -1 
							|| $(this).text().toUpperCase().indexOf($('#input_contentSelectorSearch').val().toUpperCase()) > -1) {
						$(this).fadeIn('slow');
					}
				});
				return false;
			});			
			
			$.tree.reference($('#ROOT')).open_branch('#ROOT');
						
			var validation = false;

			//컨텐츠를 클릭하거나 더블클릭할때 컨텐츠 목록에 추가
			$('#content_list > div').live('click  dblclick', function(event) {
				
				if(<%=typeOfPortlet%> == 3){
					if($(this).attr('type') == 3){
						alert(portal_alert_msg_9);
						return false;
					}				
				}else{
					if($(this).attr('type') != 1){
						alert(portal_alert_msg_10);
						return false;
					}	
				}

				if(event.type == 'dblclick'){
					
					var selectedContentId = $(this).attr('id');					
					if(validation == false) {
						$('#content_list > div[id='+$(this).attr('id')+']').click();
					} else {
						// 추가
						var zoneIdLabel = "<img did='"+selectedContentId+"' class='delete' title='delete' alt='delete' src='/ep/luxor/ref/image/portlet/bookmark/ico_delete.gif' style='height: 10px;padding-right: 5px;padding-top: 5px;float: right;'/>";
						$('#selected_content_list').append($(this).clone().removeAttr('id').attr('sid',selectedContentId).removeClass('hover').append(zoneIdLabel));
							
						var insertParam = "pageId="+selectedNodeId+"&contentId="+selectedContentId+"&tabPortletId=<%=contentId%>";
						callJson("generalPortletController", "insertTabContents", insertParam);
					}
					validation = false;
				}
				
				if (event.type == 'click') {
					if($('#selected_content_list div').size() >= contentMaximum) {
						alert(contentMaximum+portal_alert_msg_185);
						return false;
					}
					
					var divId = $(this).attr('id');
					$(this).attr('style','position:relative;');
					$(this).closest('#content_list').css('overflow-x','hidden');
					$(this).animate( { position: 'relative', left: '+=100', opacity: 0}, 500, function() {
						$(this).attr('style','');
						$(this).closest('#content_list').css('overflow-x','auto');
						$('#content_list > div[id='+divId+'] > a').attr('style' , 'color:red;');
			        });
					
					var selectedContentId = $(this).attr('id');
					// 중복체크
					if($("div[sid="+selectedContentId+"]").is('div')) {
						alert(portal_alert_msg_10);
						return false;
					} 
					validation = true;
					$('#content_list > div[id='+selectedContentId+']').dblclick();
				} 
			});		
			
			// 우측 선택된 목록 클릭시 제거
			$('#selected_content_list div img').live('click', function(event) {
				var object = $(this).closest("div");
				if (event.type == 'click') {
					var deleteParam = "pageId="+selectedNodeId+"&contentId="+object.attr('sid')+"&tabPortletId=<%=contentId%>";
					callJson("generalPortletController", "deleteTabContents", deleteParam);

					$('#content_list > div[id='+object.attr('sid')+'] > a').attr('style' , 'color:black;');
					// 화면에서 제거
					object.remove();
				}
			});			
	});
	
</script>
</head>

<body>
<div id="content_manager" style='width:100%;margin:0;padding:5;border:0;'>
<div id="window_scroll_wrap" >
	<div id="cs_admin_wrap">
		<div id="cs_tree_wrap" class="tree-wrap" style='height:440px;position:relative;'>
			<div class="page-tree-wrap" style="height:100%;">
				<div class="title02">
					<span class="title-sub-text"><spring:message code="portal.label.44" text="컨텐츠 카테고리" /></span>
				</div>
				<%-- 트리 --%>
				<div id="<%=treeId%>">
				    <ul>
				    	<li id="ROOT" class="open"><a href='#'><ins></ins>ROOT</a>
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
								if(nodeId.equals("root") || hasChild ) {
									_class = "class='open'";
								}
								
								if(depth > prevDepth) {
									out.println("<ul>");
								}
								if(prevDepth > depth) {
									out.println("</ul></li>");
								}
								
								out.println("<li name='usepersonal' id='"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' "+_class+"  hasChild ='"+tree[i].getHasChild()+"'><a href='#'><ins></ins>"+nodeName+"</a>");
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
	
		<%-- 왼쪽(트리), 오른쪽(페이지) 크기 리사이즈해주는 바 --%>
		<div id="resize_bar2" class="resize-bar"></div>
	
		<div id="container" class="admin-content-width ml10" style="width:535px;">
			<%-- 검색 --%>
			<div class="admin-tree-btn-group" style="float:right; padding:0px 0px 5px 0px;">
				<input id="input_contentSelectorSearch" type="text" class="input-txtfield w200" />
				<span class="main-btn"><span class="search"></span><a href="#" id="btn_contentSelectorSearch"><spring:message code="portal.btn.label.8" text="검색" /></a></span>		
			</div>
			<%-- 검색 --%>
			<div id="contentSelector_accordion" style="clear:both;">
				<h3><a href="#"><spring:message code="portal.label.45" text="컨텐츠 목록" /></a></h3>
				<div>
					<table class="table-body except-hover">
						<thead>
					    <tr>
							<th width="23%"><spring:message code="portal.label.45" text="컨텐츠 목록" /></th>
							<th width="27%"><spring:message code="portal.label.46" text="선택된 목록" /></th>
				      	</tr>
					  	</thead>
					  	<tbody>
							<tr>
						  		<td valign="top"><div id="content_list" style="text-align:left;overflow:auto;height:285px;"></div></td>
						  		<td valign="top"><div id="selected_content_list" style="text-align:left;overflow:auto;height:285px;"></div></td>
						  	</tr>
					  	</tbody>
					</table>
				</div>
				<h3 style="display:none;"><a class="preview-layout" href="#"><spring:message code="portal.label.3" text="미리보기" /></a></h3>
				<div>
					<iframe src="" id="content_preview" style="margin-top:10px;overflow:auto;height:285px;width:100%;" frameborder="0">
					</iframe>
				</div>
			</div>
			<div class="mb5"></div>
		    <div align="center">
		    <span class="main-btn"><a id="btn_close_content_manager" href="#"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
		    </div>
		</div>
	</div>
	</div>
</div>
</body>
</html>