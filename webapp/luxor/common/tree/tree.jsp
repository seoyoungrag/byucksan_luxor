<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	TreeVO[] tree = (TreeVO[])request.getAttribute("tree");
	String treeId = (String)request.getAttribute("treeId");
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript">
	var _treeId = "<%=treeId%>";
	var searchDivId = "tree_search"; // 검색 사용시 검색 input, button을 둘러싸고 있는 DIV ID
	var messageDivId = "tree_message_form_div"; // 다국어 사용시 다국어 입력폼 부분을 둘러싸고 있는 DIV ID

	// 콜백함수 정의
	var selectCallback = function(nodeId) {
		// 노드 선택시 호출
		//alert("selected node id is "+nodeId); 
	};
	var insertCallBack = function(nodeId) {
		// 새로 노드 추가시 호출
		//alert("inserted node id is "+nodeId); 
	};
	var updateCallBack = function(nodeId) {
		// 노드 이름 변경시 호출
		//alert("updated node id is "+nodeId); 
	};
	var deleteCallBack = function(nodeId) {
		// 노드 삭제시 호출
		//alert("deleted node id is "+nodeId); 
	};
	var moveCallBack = function(fromNodeId, toNodeId, moveType) {
		// 드래그해서 위치 이동시 호출
		//alert("Node moved from "+fromNodeId+" to "+toNodeId+" [Type:"+moveType+"]");
	};
</script>
</head>

<body>

<input type="button" onclick="openAll()" value="모두 펼치기" />
<input type="button" onclick="closeAll()" value="모두 닫기" />
<input type="button" onclick="openNode('N26F2A0AA3AEFB24CC6BBE92312E2628584BE')" value="특정 폴더 열기" />

<%-- 폴더 추가/수정/삭제 버튼 --%>
<div class="admin-tree-btn-group">
	<input type="button" onclick="_showCreateForm()" value="<spring:message code="portal.btn.label.5" />" />
	<input type="button" onclick="_showModifyForm()" value="<spring:message code="portal.btn.label.6" />" />
	<input type="button" onclick="_deleteFolder()" value="<spring:message code="portal.btn.label.7" />" />
</div>
<%-- 폴더 추가/수정/삭제 버튼 --%>


<%-- 트리 검색 --%>
<div id="tree_search">
	<input type="text" />
	<input type="button" value=<spring:message code="portal.btn.label.8" /> />
</div>
<%-- 트리 검색 --%>


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


<%-- 폴더 생성/수정시 다국어 관련 DIV --%>
<div id="tree_message_form_div" class="hidden">
	<form id="tree_form">
	<spring:message code="portal.label.1" /> : <jsp:include page="/luxor/common/message.jsp" />
	<input type="button" onclick="_treesave()" value="<spring:message code="portal.btn.label.1" />" />
	</form>
</div>
<%-- 폴더 생성/수정시 다국어 관련 DIV --%>


</body>

</html>
