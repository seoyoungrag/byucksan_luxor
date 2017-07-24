<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>

<%
	Positions tree = (Positions)request.getAttribute("positionList");
	Positions treeId = (Positions)request.getAttribute("treeId");
	String positionId = "";
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	
	if(userProfile == null) {
		out.println("<script type=\"text/javascript\">alert('조회 권한이 없습니다.');window.close();</script>");
		return;
	}else{
		positionId = userProfile.getPositionCode();
	}
	
	String compId = "DEFAULT";//userProfile.getCompId();
	String strReturnMethod = CommonUtil.nullTrim(UtilRequest.getString(request, "returnMethod"));	
%>

<html lang="ko">
<head>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/user.js"></script>
<script type="text/javascript">
	var _treeId = "<%=treeId%>";	
	var searchDivId = "tree_search"; // 검색 사용시 검색 input, button을 둘러싸고 있는 DIV ID
	tree_draggable = false;
	var nodeObj = new Object();
	var strReturnMethod = "<%=strReturnMethod%>";
	
	// 현재 선택된 노드
	var selectedNodeId = "ROOT";
	
	if("<%=positionId%>"!=""){
		selectedNodeId = "<%=positionId%>";
	}

	var openCallback = function(openNodeId) {	
		openNodeObj = getSubTree(openNodeId);
		drawSubTree(openNodeId, openNodeObj);

		$('#'+openNodeId+' li').removeClass('leaf');
		$('#'+openNodeId+' li').addClass('closed');
	};

	var selectCallback = function(nodeId) {
		selectedNodeId = nodeId;
		var treeNodeName = $('#'+nodeId).attr('treeNodeName');
		$('#positionValue').val(nodeId+"/"+treeNodeName);

		var subDepth = $('#'+nodeId).attr('depth');
		$('#depth').val(subDepth);
	};
	
	window.onload = function() {
		resizeTreeHeight();
	};
	
	$(document).ready(function() {

		var positionId = "<%=positionId%>";

		$.tree.focused().select_branch("#"+selectedNodeId);

		// OK 버튼 클릭시
		$('#btn_add_ok').click(function() {
			onOK();
		});

	});

	function resizeTreeHeight() {
		$('#treeM').height($(document).height());
	}

	function getSubTree(nodeId) {
		var result = null;
		var compId = "<%=compId%>";
		var depth =  $('#depth').val();
		callJson("orgController","getPositionAjax","positionID="+nodeId+"&compID="+compId+"&depth="+depth, function(data) {
			result = data;
		});
		return result;
	}
	
	// 확인버튼
	function onOK(){
		var positionValue = $('#positionValue').val();
		if(strReturnMethod!=""){
			callBack(positionValue);
		}
	}

	// callBack메소드 호출
	function callBack(positionValue){
		if(opener.<%=strReturnMethod%> != null)
		opener.<%=strReturnMethod%>(positionValue);	
	}

</script>
</head>
<body>
<input type="hidden" name="positionValue" id="positionValue" />
	<div id="alphabg"></div> 
	
	<div class="admin-tree-pop-wrap" name="zone" id="treeM">
		<div class="tree-width">
			<div class="admin-tree-padding">
				<div class="title02">
				 	<span class="title-sub-text"><spring:message code="user.label.26" text="직위트리" /></span>
			    </div>
					
				<%-- 트리 --%>
				<div id="<%=treeId%>" class="admin-org-tree">
				    <ul>
				    	<li id="ROOT" class="open"><a href='#'><ins></ins><spring:message code="user.label.15" text="직위" /></a>
<%
	int prevDepth = 0;
	if(tree != null){
		for(int i=0; i < tree.getPositionList().size(); i++) {
			String nodeId = tree.getPositionList().get(i).getPositionID();
			String nodeName = tree.getPositionList().get(i).getPositionName();
			String parentId = tree.getPositionList().get(i).getPositionParentID();
			int depth = 1;
			boolean hasChild = false;
			
			String _class = "";
			if(nodeId.equals("root")) {
				_class = "class='open'";
			}else{
				_class = "class='closed'";
			}
			
			if(depth > prevDepth) {
				out.println("<ul>");
			}
			if(prevDepth > depth) {
				out.println("</ul></li>");
			}
			out.println("<li id='"+nodeId+"' parentId='"+parentId+"' "+_class+" treeNodeName='"+nodeName+"'><a href='#'><ins></ins>"+nodeName+"</a>");
			if(!hasChild) {
				out.println("</li>");
			}
			prevDepth = depth;
		}
	}
%>
						</li>
				    </ul>
				</div>
			<%-- 트리 --%>
			</div>	
		</div>	
		<div class="admin-tree-btn-group float-r">
			<span class="main-btn" id="div_btn_add_ok"><a href="#" id="btn_add_ok"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
		</div>
	</div>	
<input type="hidden" name="depth" id="depth" value=1 />
</body>

</html>
