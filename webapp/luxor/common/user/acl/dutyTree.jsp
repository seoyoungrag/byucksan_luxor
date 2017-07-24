<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>

<%
	Duties tree = (Duties)request.getAttribute("dutyTree");
	Duties treeId = (Duties)request.getAttribute("treeId");
	String compId = "";
	String dutyId = "";

	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	
	if(userProfile == null) {
		out.println("<script type=\"text/javascript\">alert('조회 권한이 없습니다.');window.close();</script>");
		return;
	}else{
		dutyId = userProfile.getDutyCode();
		compId = userProfile.getCompId();
	}
%>

<html lang="ko">
<head>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript">
	var _treeId = "<%=treeId%>";	
	var searchDivId = "tree_search"; // 검색 사용시 검색 input, button을 둘러싸고 있는 DIV ID
	tree_draggable = false;
	var nodeObj = new Object();
	
	// 현재 선택된 노드
	var selectedNodeId = "ROOT";
	
	if("<%=dutyId%>"!=""){
		selectedNodeId = "<%=dutyId%>";
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
    	parent.$('#strg3').val(nodeId+'/'+treeNodeName);

		var subDepth = $('#'+nodeId).attr('depth');
		$('#depth').val(subDepth);
	};

	$(document).ready(function() {
		$(window).resize(function(){
			parent.resizeTo(250,600);
		}); 

		$.tree.focused().select_branch("#"+selectedNodeId);
	});

	function getSubTree(nodeId) {
		var result = null;
		var compId = "<%=compId%>";

		var depth = $('#depth').val();
		callJson("orgController","getDutyAjax","dutyID="+nodeId+"&compID="+compId+"&depth="+depth, function(data) {
			result = data;
		});
		return result;
	}
	
</script>
</head>

<body class="box-body">
<div class="box-border-small h400">
	<%-- 트리 --%>
	<div id="<%=treeId%>">
	    <ul>
	    	<li id="ROOT" class="open"><a href='#'><ins></ins><spring:message code="user.label.13" text="직무" /></a>
<%
	int prevDepth = 0;
	if(tree != null){
		for(int i=0; i < tree.getDutyList().size(); i++) {
			String nodeId = tree.getDutyList().get(i).getDutyID();
			String dutyOtherName = tree.getDutyList().get(i).getDutyOtherName();
			String nodeName = tree.getDutyList().get(i).getDutyName();
			String parentId = tree.getDutyList().get(i).getDutyParentID();
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
			out.println("<li id='"+nodeId +"' parentId='"+parentId+"' "+_class+" treeNodeName='"+nodeName+"'><a href='#'><ins></ins>"+nodeName+"</a>");
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
<input type="hidden" name="depth" id="depth" value=1 />
</div>
</body>

</html>
