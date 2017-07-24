<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>
 
<%
	String userDeptId = "";
	String treeId = "orgTreeLst";
	Departments tree = (Departments)request.getAttribute("orgTree");
    String treeType = CommonUtil.nullTrim(UtilRequest.getString(request, "treeType"));
    String returnMethod = CommonUtil.nullTrim(UtilRequest.getString(request, "returnMethod",""));

	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	
	if(userProfile == null) {
		out.println("<script type=\"text/javascript\">alert('조회 권한이 없습니다.');window.close();</script>");
		return;
	}else{
		userDeptId = userProfile.getDeptId();
	}
	
%>

<html>
<head>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript">
	var _treeId = "<%=treeId%>";	
	var searchDivId = "tree_search"; // 검색 사용시 검색 input, button을 둘러싸고 있는 DIV ID
	var nodeObj = new Object();
	tree_draggable = false;
	var subtreeArr = new Array();
	var isUsed = false;
	
	// 현재 선택된 노드
	var selectedNodeId = "<%=userDeptId%>";

	var openCallback = function(openNodeId) {	
		openNodeObj = null;
		var treeType = "<%=treeType%>";
		callJson("orgController","getOrgSubTreeAjax","orgID="+openNodeId+"&treeType="+treeType, function(data) {
			openNodeObj = data;
			if(subtreeArr.indexOf(openNodeId) == -1) {
				drawSubTree(openNodeId, openNodeObj);
				subtreeArr.push(openNodeId);

				$('#'+openNodeId+' li').removeClass('leaf');
				$('#'+openNodeId+' li').addClass('closed');
			}
		});
	};

	var selectCallback = function(nodeId) {
		selectedNodeId = nodeId;
		var treeNodeName = $('#'+nodeId).attr('treeNodeName');
<%
		if(!"".equals(returnMethod)){
%>
		if(opener != null && $('#'+nodeId).attr('orgType') == '1'){
			opener.<%=returnMethod%>(nodeId+'/'+treeNodeName);
			self.close();			
		}else if(isUsed){
			alert('<spring:message code="user.alert.msg.11" text="회사만 선택이 가능합니다." />');
		}

<%
		}else{
%>
			parent.$('#strg2').val(nodeId+'/'+treeNodeName);
<%
		}
%>    	

    	isUsed = true;

	};
	
	$(window).scroll(function() {
		resizeTreeHeight();
	});
	
	$(window).resize(function() {
		resizeTreeHeight();
	});
	
	$(document).ready(function() {
		resizeTreeHeight();

<%		if(tree != null){
			String[] strNodeId = new String[tree.getDepartmentList().size()];	
			for(int j=0; j < tree.getDepartmentList().size(); j++) {
				strNodeId[j] = tree.getDepartmentList().get(j).getOrgID();
				if(tree.getDepartmentList().get(j).getHasChild().equals("Y")){
%>					subtreeArr.push("<%=strNodeId[j]%>");
<%				}
			}
		}
%>
		$.tree.focused().select_branch("#"+selectedNodeId);
	});

	function resizeTreeHeight() {
		$('#treeM').height($(document).height()-10);
	}
	
</script>
</head>

<body class="box-body">
<div class="box-border-small h400">
	<%-- 트리 --%>
	<div id="orgTreeLst">
	    <ul>
<%
	if(tree != null){
		int prevDepth = tree.getDepartmentList().get(0).getDepth()+1;
		for(int i=0; i < tree.getDepartmentList().size(); i++) {
			String nodeId = tree.getDepartmentList().get(i).getOrgID();
			String nodeName = tree.getDepartmentList().get(i).getOrgName();
			String parentId = tree.getDepartmentList().get(i).getOrgParentID();
			int depth = tree.getDepartmentList().get(i).getDepth();
			boolean hasChild = tree.getDepartmentList().get(i).getHasChild().equals("Y");
			String nodeNameId = tree.getDepartmentList().get(i).getOrgName() + "_id";
			String orgType = Integer.toString(tree.getDepartmentList().get(i).getOrgType());

			String _class = "class='closed'";
			
			if(depth < prevDepth) {
				out.println("<ul>");
			}
			int intDepth = 0;
			
			if(depth>prevDepth) { 
				intDepth = depth - prevDepth;
				for(int j=0; j<intDepth;j++){
					out.println("</ul></li>");					
				}
			}
			out.println("<li id='"+nodeId+"' orgType='"+orgType+"' "+_class+" treeNodeName='"+nodeName+"'><a href='#'><ins></ins>"+nodeName+"</a>");
			if(!hasChild) {
				out.println("</li>");
			}
			prevDepth = depth;
		}
	}else{
%>
    	<li id="treeROOT" class="open" orgType="0"><a href='#'><ins></ins><spring:message code="user.label.22" text="조직도" /></a>
<%	}
%>

						</ul>
					</li>
			    </ul>
			</li>
	    </ul>
	</div>
	<%-- 트리 --%>
	</div>
</body>
</html>
