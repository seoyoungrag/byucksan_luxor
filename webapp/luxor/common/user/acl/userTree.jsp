<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>
 
<%
	String userDeptId = "";
	String treeId = "orgTreeLst";
	Departments tree = (Departments)request.getAttribute("userTree");
    String treeType = CommonUtil.nullTrim(UtilRequest.getString(request, "treeType"));

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
	var treeType = "<%=treeType%>";
	
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
		var orgType = $('#'+nodeId).attr('orgType');
		$('#user_list').load('/ep/user/getUserList.do?list_type=byDeptID&displayCheckbox=true&isAcl=Y&deptID='+nodeId+'&orgType='+orgType);
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
		$('#user_list').load('/ep/user/getUserList.do?list_type=byDeptID&deptID=&displayCheckbox=true&isAcl=Y');

		$.tree.focused().select_branch("#"+selectedNodeId);
		
		// 회원목록 회원명 클릭시
		$('#userList_table a').live('click', function(e) {
			var userId = $(this).attr("id");
			var url = '/ep/user/getAclUser.do?userID='+ userId;
			luxor.popup(url,{width:600,height:410});
		});

		// 체크박스 전체선택
		$('#chkAll').live('click', function() {
			selectAll();
		});

		// 검색 버튼 클릭시
		$('#btn_search_user').click(function() {
			goUserSearch();
		});

		// 체크 박스 클릭시
		$('#test').click(function() {
			toggleRead();
		});    

		// 검색
		$('#userTxtSearch').keypress(function(event) {
			if(event.keyCode==13){
				goUserSearch();
			}
		});

		// 회원목록 tr 클릭시
		$('#userList_table tr').live('click ', function(event) {
			var trId = $(this).attr('trId');
			if(trId != 'chkAll'){
				if (event.target.type != 'checkbox') {
					$(':checkbox', this).trigger('click');
				}
			}
		});
		
	});

	function resizeTreeHeight() {
		$('#treeM').height($(document).height()-10);
	}
	
	/**
	 * 체크박스 전체선택
	 */
	function selectAll() {
		var checked = $("#chkAll").attr("checked");
		$(".box").each(function(){
			var subChecked = $(this).attr("checked");
		    if (subChecked != checked)
		    (this.checked == true) ? this.checked = false : this.checked = true;
		});
	} 
	
	// 회원검색
	function goUserSearch(){
		var txtSearch = $("#userTxtSearch").val();
		var param = "";
		var url = "";

		if(txtSearch=="") {
			alert('<spring:message code="portal.alert.msg.26" text="검색어를 입력하세요." />');
			return;
		}

		param += "list_type=ByNameByOrgId";
		param += "&deptID=";
		param += "&nType=0";
		param += "&userName="+txtSearch;
		param += "&displayCheckbox=true";
		param += "&isAcl=Y";
		url = "/ep/user/getUserList.do";
		$('#list_type').val("ByNameByOrgId");
		
		$('#user_list').load(url, param);
	}

</script>
</head>

<body class="box-body">
	
	<div class="box-border-small h200">
		<%-- 검색--%>
		<div class="mb10">
			<input type="text" id="userTxtSearch" class="input-d w150" />
			<span class="smain-btn" id="btn_search_user"><a href="#">검색</a></span>
		</div>
		<!-- //검색 -->
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
				out.println("<li id='"+nodeId+"' orgType='"+orgType+"' "+_class+"><a href='#'><ins></ins>"+nodeName+"</a>");
				if(!hasChild) {
					out.println("</li>");
				}
				prevDepth = depth;
			}
		}else{
%>
	    	<li id="treeROOT" class="open" orgType="0"><a href='#'><ins></ins><spring:message code="user.label.22" text="조직도" /></a>
<%		}
%>
							</ul>
						</li>
				    </ul>
				</li>
		    </ul>
		</div>
	</div>
	<%-- 트리 --%>
	<!-- box-border-small -->
	<div class="box-border-small" style="height:184px;">  
		<div id="user_list"></div>
	</div>
	
	<div id="alphabg"></div>
</body>
</html>
