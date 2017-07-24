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
%>

<script type="text/javascript">
	var contentMaximum = "<%=contentMaximum%>";
	var portal_alert_msg_75 = "<spring:message code="portal.alert.msg.75" text="템플릿 선택시 기존에 등록된 내용은 모두 삭제됩니다. 계속하시겠습니까?" />";
	var portal_alert_msg_185 = "<spring:message code="portal.alert.msg.185" text="개 이상의 포틀릿은 등록하실 수 없습니다." />";
	var portal_alert_msg_76 = "<spring:message code="portal_alert_msg_76" text="개인이 설정한 화면 설정이 초기화됩니다. 계속하시겠습니까?" />";
	var portal_alert_msg_77 = "<spring:message code="portal.content.alert.7" text="화면 구성 레이아웃이 변경됩니다. 계속하시겠습니까?" />";
	var portal_alert_msg_79 = "<spring:message code="portal.content.alert.8" text="고정된 컨텐츠가 속한 영역은 제거하실 수 없습니다." />";
	
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

	$('#btn_initPersonalizedPage').click(function() {
		var param = "pageId="+selectedNodeId;
		if(confirm(portal_alert_msg_76)) {
			callJson("portalPageZoneController", "deleteAllContentPersonalizedPage", param , function(data) {
				$('#content_manager').dialog('close');
			});
		}
	});
	
	if(content.refer=='UM' || content.refer=='DD'){
		callJson("portalContentController", "getPersonalCatalog", "usePersonal=N", function(data) {
			for(var i=0; i<data.length; i++) {
				$('#cs_tree_wrap #'+data[i].nodeId).hide();
				$('#cs_tree_wrap #'+data[i].nodeId).attr('name','notUsePersonal');
			}
			$('#cs_tree_wrap #ROOT li[name=usepersonal]').last().attr('class','last leaf');
		});
	} else {
		$('#btn_initPersonalizedPage').closest('span').hide();
		$('#btn_close_content_manager').closest('span').hide();
	}
	
	$(function() {
		$( "#contentSelector_accordion" ).accordion({
			autoHeight: false,
			navigation: true,
			event: "click"
		});
	});
	
</script>

<div id="window_scroll_wrap">
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
								if(nodeId.equals("root")) {
									_class = "class='open'";
								}
								
								if(depth > prevDepth) {
									out.println("<ul>");
								}
								if(prevDepth > depth) {
									out.println("</ul></li>");
								}
								out.println("<li name='usepersonal' id='"+nodeId+"' parentId='"+parentId+"' nodeNameId='"+nodeNameId+"' "+_class+"><a href='#'><ins></ins>"+nodeName+"</a>");
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
			<div id="layoutList" class="mb5">
				<%	if(templates.length > 0) { %>
				<span class="mt5"><spring:message code="portal.label.97" text="템플릿 선택" /> : </span> 
				<select id="select_template" title="<spring:message code="portal.label.97" text="템플릿 선택" />">
					<option value=""><spring:message code="portal.label.97" text="템플릿 선택" /></option>
				<% 
					for(PortalPageVO template : templates) {
						String templateId = template.getPageId();
						String templateName = template.getMessageName();
				%>
					<option value="<%=templateId%>"><%=templateName%></option>
				<%	} %>
				</select>
				<%	} %>
				<span class="mt5"><spring:message code="portal.label.47" text="레이아웃 선택" /> : </span> 
				<select id="select_layout" title="<spring:message code="portal.label.47" text="레이아웃 선택" />">
				<% 
					for(PortalPageLayoutVO layout : layouts) {
						String layoutId = layout.getLayoutId();
						String layoutName = layout.getMessageName();
				%>
					<option value="<%=layoutId%>"><%=layoutName%></option>
				<%	} %>
				</select>	
			</div>
			<%-- 검색 --%>
			<div class="admin-tree-btn-group" style="float:right; padding:0px 0px 5px 0px;">
				<input id="input_contentSelectorSearch" type="text" class="input-txtfield w200" />
				<span class="main-btn"><span class="search"></span><a href="#" id="btn_contentSelectorSearch"><spring:message code="portal.btn.label.8" text="검색" /></a></span>		
				<span class="main-btn"><a href="#" id="btn_initPersonalizedPage"><spring:message code="portal.btn.label.48" text="initPage" /></a></span>
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
		    <span class="main-btn"><a id="btn_save_content_manager" href="#"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
		    <span class="main-btn"><a id="btn_close_content_manager" href="#"><spring:message code="portal.btn.label.2" text="취소" /></a></span>
		    </div>
		</div>
	</div>
	
</div>