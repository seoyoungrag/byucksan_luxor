<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>
 
<%
	String userGroupId = "";
	String userCompId = "";
	String userDeptId = "";
	String userUid = "";
	
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	Department adminTree = (Department)request.getAttribute("adminTree");	// 포탈 관리자 설정에서 관리자 추가 시 사용
	
	if(userProfile == null) {
		out.println("<script type=\"text/javascript\">alert('조회 권한이 없습니다.');window.close();</script>");
		return;
	}else{
		userUid = userProfile.getUserUid();
		if(!userUid.equals("ADMIN")){
			userGroupId = userProfile.getGroupId();
			userCompId = userProfile.getCompId();
			userDeptId = userProfile.getDeptId();
		}else{	// 포탈 관리자 설정에서 관리자 추가 시 사용
			userDeptId = adminTree.getOrgID();
		}
	}
	
	String treeId = "orgTreeLst";
	Departments tree = (Departments)request.getAttribute("tree");
	String searchName = CommonUtil.nullTrim(UtilRequest.getString(request, "searchName"));
	String srchType = CommonUtil.nullTrim(UtilRequest.getString(request, "searchType"));
	String nsrchType = CommonUtil.nullTrim(UtilRequest.getString(request, "nType"));
	String strReturnMethod = CommonUtil.nullTrim(UtilRequest.getString(request, "returnMethod"));
	String viewType = CommonUtil.nullTrim(UtilRequest.getString(request, "viewType"));
	String selectType = CommonUtil.nullTrim(UtilRequest.getString(request, "selectType"));
	String isSearch = CommonUtil.nullTrim(UtilRequest.getString(request, "isSearch"));
	String treeType = CommonUtil.nullTrim(UtilRequest.getString(request, "treeType"));
	
	if(viewType == null) 
		viewType = "0";	
	if(selectType == null) 
		selectType = "multi";

%>

<html lang="ko">
<head>
<title><spring:message code="user.label.1" text="사용자/조직" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/tree.js"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/user.js"></script>
<script type="text/javascript">
	var _treeId = "<%=treeId%>";	
	var searchDivId = "tree_search"; // 검색 사용시 검색 input, button을 둘러싸고 있는 DIV ID
	var nodeObj = new Object();
	tree_draggable = false;
	var viewType = "<%=viewType%>";
	var deptId = "<%=userDeptId%>";
	var selectType = "<%=selectType%>";
	var strReturnMethod = "<%=strReturnMethod%>";
	var isSearch = "<%=isSearch%>";
	var subtreeArr = new Array();
	var listLoadType = "firstPage";
	var displayCheckbox = "true";

<%	if(tree != null){
		String[] strNodeId = new String[tree.getDepartmentList().size()];
		for(int j=0; j < tree.getDepartmentList().size(); j++) {
			strNodeId[j] = tree.getDepartmentList().get(j).getOrgID();
			if(tree.getDepartmentList().get(j).getHasChild().equals("Y")){
%>				subtreeArr.push("<%=strNodeId[j]%>");
<%			}
		}
	}
%>	
	
	if(viewType=="0" || viewType=="2"){
		displayCheckbox = "true";
	}else if(viewType=="1" || viewType=="3"){
		displayCheckbox = "false";
	}

	// 현재 선택된 노드
	var selectedNodeId = "";
	
	if("<%=userDeptId%>"!=""){
		selectedNodeId = "<%=userDeptId%>";
	}
	
	var openCallback = function(openNodeId) {
		openNodeObj = getSubTree(openNodeId);
		if(subtreeArr.indexOf(openNodeId) == -1) {
			drawSubTree(openNodeId, openNodeObj);
			subtreeArr.push(openNodeId);

			$('#'+openNodeId+' li').removeClass('leaf');
			$('#'+openNodeId+' li').addClass('closed');
		}
	};

	var selectCallback = function(nodeId) {
		selectedNodeId = nodeId;
		var orgType = $('#'+nodeId).attr('orgType');
		$('#orgType').val(orgType);
		$('#selectedOrgId').val(nodeId);
		listLoadType = "selectCallback";
		if(viewType != "4" && viewType != "5"){
			$('#user_list').load('/ep/user/getUserList.do?list_type=byDeptIDWithPaging&deptID='+nodeId+'&orgType='+orgType+'&selectType='+selectType+'&displayCheckbox='+displayCheckbox);
		}
	};

	window.onload = function() {
		resizeTreeHeight();
	};
	
	$(document).ready(function() {
				
		var txtSrch = "<%=searchName%>";
		$('#selectedOrgId').val(deptId);

		var divWidth = $('#user_list_wrap').width() + 54;
		$('#rightSmallDiv').val(divWidth);

		if(viewType == "4" || viewType== "5"){
			$.tree.focused().select_branch("#"+deptId);
			$('#select_list_wrap').hide();
			$('#user_list_wrap').hide();
			$('#div_btn_add_ok').hide();
			$('#div_btn_add_ok2').show();
			$('#btn_org_tree_min').hide();

		}else{
			if(isSearch=="Y"){
				goSearch(1);
			}else{
				var url = "/ep/user/getUserList.do";
				var param = "";

				if(viewType=="0"){
					$('#btn_show_tree').hide();
					$.tree.focused().select_branch("#"+deptId);
				}else if(viewType=="1"){	
					$('#btn_show_tree').hide();
					$('#select_list_wrap').hide();
					$('#page_sbutton_group').hide();
					$.tree.focused().select_branch("#"+deptId);
				}else if(viewType=="2"){
					hideTree();	
				}else if(viewType=="3"){	
					hideTree();	
					$('#select_list_wrap').hide();
					$('#page_sbutton_group').hide();
				}
				param += "list_type=byDeptIDWithPaging";
				param += "&deptID="+deptId;
				param += "&selectType="+selectType;
				param += "&displayCheckbox="+displayCheckbox;
				$('#user_list').load(url, param);
			}
		}	
		
		// 회원정보 dialog 초기화
		$('#userLst').dialog({
			autoOpen:false,
			modal:true,
			width:600,
			height:400,
			resizable:false
		});

		// 칼럼선택 dialog 초기화
		$('#selectColumn').dialog({
			autoOpen:false,
			modal:true,
			width:270,
			resizable:false,
			height:235
		});
		
		// 회원목록 회원명 클릭시
		$('#userList_table a').live('click', function(e) {
			var userId = $(this).attr("id");
			$('#userLst').dialog({title:'사용자정보'});
			$('#userLst').dialog('open');
			$('#getUserView').load('/ep/user/getUser.do?userID='+ userId);
		});

		// 회원목록 tr 클릭시
		$('#userList_table tr').live('click ', function(event) {
			var trId = $(this).attr('trId');
			if(trId != 'chkAll'){
				if (event.target.type != 'checkbox') {
					$(':checkbox', this).trigger('click');
				}
			}
			if (event.target.type != 'radio') {
				$(':radio', this).trigger('click');
			}
		});

		// 회원목록 tr 더블클릭시
		$('#userList_table tr').live('dblclick ', function(event) {
			var trId = $(this).attr('trId');
			if(trId != 'chkAll'){
				if (event.target.type != 'checkbox') {
					$(':checkbox', this).trigger('click');
				}
				var chkVal = $('#'+trId).val();
			setDblClickLst(chkVal);
			}
			  
		});
		
		// 회원 추가 버튼 클릭시
		$('#btn_add_user').click(function() {
			setSelectedLst();
		});

		// 회원 삭제 버튼 클릭시
		$('#btn_del_user').click(function() {
			setDelLst();
		});

		// selectbox 더블클릭시 선택된 값 삭제
		$('#chkdList').dblclick(function(event) {
			setDelLst();
		});
		
		// 체크박스 전체선택
		$('#chkAll').live('click', function() {
			selectAll();
		});

		// 검색 버튼 클릭시
		$('#btn_search_user').click(function() {
			goUserSearch(1);
		});

		// 조직도 버튼 클릭시
		$('#btn_show_tree').click(function() {
			viewTree();
		});

		// 칼럼선택 버튼 클릭시
		$('#btn_user_list_col').click(function() {
			$('#selectColumn').dialog('open');
		});

		// 칼럼선택확인 버튼 클릭시
		$('#btn_sel_col').click(function() {
			var checkedCnt = $(".col:checked").size();
			if(checkedCnt > 4){
				alert('<spring:message code="user.alert.msg.1" text="칼럼은 6개까지만 선택가능합니다."/>');
				return;
			}
			
			var disTd ="";
			var listSize = $('#userListSize').val();
			var checked = $("#chkAll").attr("checked");
			$(".col").each(function(){
				var colChecked = $(this).attr("checked");
			    if (colChecked == true){
			    	$("#"+$(this).val()).remove();  
					 var title = $(this).val()+"Tt";
					 var td = $(this).val()+"Td";
					 for(var i=0;i<listSize;i++){
						 disTd = i+td;
						 $("#"+disTd).show();
						 $("#"+title).show();
					 }
			    }else{
					$("#"+$(this).val()).remove();  
					 var title = $(this).val()+"Tt";
					 var td = $(this).val()+"Td";
					 for(var i=0;i<listSize;i++){
						 disTd = i+td;
						 $("#"+disTd).hide();
						 $("#"+title).hide();
					 }
			    }
			});
			$('#selectColumn').dialog('close');
		});

		// OK 버튼 클릭시
		$('#btn_add_ok').click(function() {
			onOK();
		});

		// 확인2 버튼 클릭시-조직선택
		$('#btn_add_ok2').click(function() {
			onTreeOK();
		});
		
		// 조직트리 최소화 버튼 클릭시
		$('#btn_org_tree_min').click(function() {
			hideTree();
		});

		// 검색
		$('#userTxtSearch').keypress(function(event) {
			if(event.keyCode==13){
				goUserSearch(1);
			}
		});

	});
	
	function resizeTreeHeight() {
		$('#treeM').height($(document).height());
	}

	function hideTree() {	// 트리감추기
		var docWidth = 0;
		docWidth = $(document).width();
		$('#btn_show_tree').show();
		$('#treeM').hide();
		$('#user_list_wrap').width(docWidth);
		
		if(viewType=="2" || viewType=="3"){
			$('#btn_show_tree').hide();
		}
	}

	function viewTree() {	// 트리 보이기
		$.tree.focused().select_branch("#"+deptId);
		var divWidth = $('#rightSmallDiv').val() + "px";
		listLoadType = "viewTree";
		
		if(viewType=="0"){
			$('#user_list').load('/ep/user/getUserList.do?list_type=byDeptIDWithPaging&deptID='+deptId+'&selectType='+selectType+'&displayCheckbox=true');
		}if(viewType=="1"){
			$('#user_list').load('/ep/user/getUserList.do?list_type=byDeptIDWithPaging&deptID='+deptId+'&selectType='+selectType+'&displayCheckbox=false');
		}if(viewType=="2"){
			$('#user_list').load('/ep/user/getUserList.do?list_type=byDeptIDWithPaging&deptID='+deptId+'&selectType='+selectType+'&displayCheckbox=true');
		}else if(viewType=="3"){	
			$('#user_list').load('/ep/user/getUserList.do?list_type=byDeptIDWithPaging&deptID='+deptId+'&selectType='+selectType+'&displayCheckbox=false');
		}

		$('#btn_show_tree').hide();
		$('#treeM').show();
		$('#user_list_wrap').width(divWidth);
		
	}
	
	function getSubTree(nodeId) {
		var result = null;
		var treeType = "<%=treeType%>";
		var param = 
		$.ajaxSetup({async:false});
		callJson("orgController","getOrgSubTreeAjax","orgID="+nodeId+"&treeType="+treeType, function(data) {
			result = data;
		});
		return result;
	}
	
	// 파라미터로 검색
	function goSearch(p) {
		listLoadType = "paramSearch";
		
		var txtSearch = "<%=searchName%>";
		var nType = "<%=nsrchType%>";
		var searchType = "<%=srchType%>";
		var param = "";
		var url = "";
		
		if(viewType=="1" || viewType=="3"){
			$('#select_list_wrap').hide();
			$('#page_sbutton_group').hide();
		}

		if(nType==""){
			nType = 0;
		}

		var orgId = "";
		if (nType == 1) {			// 회사내 검색
			orgId = "<%=userCompId%>";
		} else if (nType == 2) {		// 부서 내 검색
			orgId = deptId;
		} else {						// 그룹 내 검색
			orgId = "<%=userGroupId%>";
		}
		
		hideTree();	
		
		param += "cPage="+p;
		if(searchType=='byId') {
			param += "&userID="+txtSearch;
			param += "&selectType="+selectType;
			param += "&displayCheckbox="+displayCheckbox;
			url = "/ep/user/getOneUser.do";
		}else {
			param += "&list_type=ByNameByOrgId";
			param += "&deptID="+orgId;
			param += "&nType="+nType;
			param += "&userName="+encodeURIComponent(txtSearch);
			param += "&selectType="+selectType;
			param += "&displayCheckbox="+displayCheckbox;
			url = "/ep/user/getUserList.do";
		}
		$('#user_list').load(url, param);
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

	/**
	 * tr 더블클릭시 값을 리스트에 세팅하기
	 */
	function setDblClickLst(chkVal) {
		var result = "";
		var optVal = "";
		var tmpUserCnt = $('#userCntVal').val();
		var addOK = 0;
		var ckeckCnt = $("input:checkbox:checked").length;
		var tempInt = 0;

		if(luxor.isNullOrEmpty(chkVal)) {
			return;	
		}
		
		var meminfoArray = chkVal.split("\^");
		var memId = meminfoArray[0];
		
		if(ckeckCnt == 0){
			alert('<spring:message code="portal.alert.msg.24" text="선택을 해주세요."/>');
			return;
		}

		if(selectType == "single"){
			if(tmpUserCnt > 0){
				alert('<spring:message code="portal.alert.msg.25" text="1명만 선택해주세요."/>');
				return;
			}
		}
		
		if(tmpUserCnt==0){
			addOK = 1;
		}

		addSelectBox(meminfoArray);
	}

	/**
	 * 선택된 체크박스 값을 리스트에 세팅하기
	 */
	function setSelectedLst() {
		var result = "";
		var optVal = "";
		var tmpUserCnt = $('#userCntVal').val();
		var addOK = 0;
		var ckeckCnt = $("input:checkbox:checked").length;

		if(ckeckCnt == 0){
			alert('<spring:message code="portal.alert.msg.24" text="선택을 해주세요."/>');
			return;
		}

		if(selectType == "single"){
			if(tmpUserCnt > 0){
				alert('<spring:message code="portal.alert.msg.25" text="1명만 선택해주세요."/>');
				return;
			}
		}

		$(".box:checked").each(function() {
			var tempInt = 0;
			addOK = 0;
			result = $(this).val();

			var meminfoArray = result.split("\^");
			var memId = meminfoArray[0];

			addSelectBox(meminfoArray);
		});
	}

	/**
	 * 선택된 값 리스트에 추가
	 */
	function addSelectBox(meminfoArray) {
		var optVal = "";
		var tmpUserCnt = $('#userCntVal').val();
		var tempInt = 0;
		var memId = meminfoArray[0];
		var addOK = 0;

		if(tmpUserCnt==0){
			addOK = 1;
		}
		
		for(var i=0;i<tmpUserCnt;i++){
			optVal = $("#chkdList option:eq(" + i + ")").val();

			if(memId != optVal){
				tempInt += 0;		//동일인물없음
			}else{
				tempInt += 1;
			}
		}
		if(tempInt == 0){		//동일인물없을때
			addOK = 1;
		}			
		if(addOK == 1){	
			tmpUserCnt++;
			var boxText = memId + "/" + meminfoArray[1] + "/" + meminfoArray[2] + "/" + meminfoArray[3] + "/" + meminfoArray[4] + "/" + meminfoArray[5];
			$("#chkdList").append("<option value='"+memId+"'>"+boxText+"</option>");
		}
		$('#userCnt').html(tmpUserCnt);
		$('#userCntVal').val(tmpUserCnt);
	}
	
	/**
	 * 선택된 값 리스트에서 삭제
	 */
	function setDelLst() {
		var selectedCnt = $("#chkdList option:selected").size();
		var listVal = $("#chkdList option:selected").val();
		if(listVal != null){
			var tmpUserCnt = $('#userCntVal').val() - selectedCnt;
			$('#userCntVal').val(tmpUserCnt);
			$('#userCnt').html(tmpUserCnt);
			$("#chkdList option:selected").remove();
		}else{
			alert('<spring:message code="portal.alert.msg.24" text="선택을 해주세요."/>');
			return;
		}
	}

	// 회원검색
	function goUserSearch(p){
		listLoadType = "userSearch";
		
		var txtSearch = $("#userTxtSearch").val();
		var nType = $("#selSearchScope").val();
		var searchType = $("#selSearchType").val();
		var param = "";
		var url = "";

		var orgId = "";
		if (nType == 1) {			// 회사내 검색
			orgid = "<%=userCompId%>";
		} else if (nType == 2) {		// 부서 내 검색
			orgid = deptId;
		} else {						// 그룹 내 검색
			orgid = "<%=userGroupId%>";
		}

		if(txtSearch=="") {
			alert('<spring:message code="portal.alert.msg.26" text="검색어를 입력하세요."/>');
			return;
		}

		param += "cPage="+p;
		if(searchType=='byName') {
			param += "&list_type=ByNameByOrgId";
			param += "&deptID="+orgid;
			param += "&nType="+nType;
			param += "&userName="+encodeURIComponent(txtSearch);
			param += "&selectType="+selectType;
			param += "&displayCheckbox="+displayCheckbox;
			url = "/ep/user/getUserList.do";
		}else if(searchType=='byId') {
			param += "&userID="+txtSearch;
			param += "&selectType="+selectType;
			param += "&displayCheckbox="+displayCheckbox;
			url = "/ep/user/getOneUser.do";
		}
		$('#user_list').load(url, param);
		hideTree();	
	}

	// 페이징
    function changePage(p) {
		var param = "";
		var url = "";

		url = "/ep/user/getUserList.do";
		param += "cPage="+p;

		if(listLoadType=="firstPage"){
			if(isSearch=="Y"){
				goSearch(p);
			}else{
				param += "&list_type=byDeptIDWithPaging";
				param += "&deptID="+deptId;
				param += "&selectType="+selectType;
				param += "&displayCheckbox="+displayCheckbox;
				$('#user_list').load(url, param);
			}	
		}else if(listLoadType=="selectCallback"){
			var orgType = $('#orgType').val();
			var orgId = $('#selectedOrgId').val();
			param += "&list_type=byDeptIDWithPaging";
			param += "&deptID="+orgId;
			param += "&selectType="+selectType;
			param += "&displayCheckbox="+displayCheckbox;
			param += "&orgType="+orgType;
			$('#user_list').load(url, param);
		}else if(listLoadType=="viewTree"){
			param += "&list_type=byDeptIDWithPaging";
			param += "&deptID="+deptId;
			param += "&selectType="+selectType;
			param += "&displayCheckbox="+displayCheckbox;
			$('#user_list').load(url, param);
		}else if(listLoadType=="paramSearch"){
			goSearch(p);
		}else if(listLoadType=="userSearch"){
			goUserSearch(p);
		}
	}
	
	// 확인버튼
	function onOK() {
		var lstCnt = $('#userCntVal').val();
		var idLst = new Array();
		var optVal = "";
		for(var i=0;i<lstCnt;i++){
			optVal = $("#chkdList option:eq(" + i + ")").val();
			idLst.push(optVal);
		}
		var result = user.getIUsers(idLst);
		if(strReturnMethod!=""){
			callBack(result);
		}
		self.close();
	}

	// 조직선택 확인버튼
	function onTreeOK() {
		var selectedOrgId = $('#selectedOrgId').val();
		var selectedOrgCompId = $('#'+selectedOrgId).closest('li[orgType=1]').attr('id');
		var result = selectedOrgId;
		if(viewType == "5") {
			result = [selectedOrgId,$('#'+selectedOrgId).attr('orgName'),$('#orgType').val(),selectedOrgCompId,$('#'+selectedOrgCompId).attr('orgName')];
		} 
		if(strReturnMethod!=""){
			callBack(result);
		}
		self.close();
	}

	// callBack메소드 호출
	function callBack(result) {
		if(opener.<%=strReturnMethod%> != null)
		opener.<%=strReturnMethod%>(result);
	}
	
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<div class="pop_title02">
	<h3><span><a href="javascript:self.close();" class="icon_close02" title="닫기"></a></span></h3>	
</div>

	<div class="tree-pop-wrap w250" name="zone" id="treeM" style="width:29%;">
		<div class="tree-width">
			<div class="admin-pop-button mr10">
	        	<a href="#" id="btn_org_tree_min"><img src="/ep/luxor/ref/image/admin/icon_01.gif" width="18" height="18" alt="<spring:message code="user.label.44" text="조직트리 숨기기" />" title="<spring:message code="user.label.44" text="조직트리 숨기기" />" /></a>
	    	</div>
			<div class="title-pop">
  				<span class="title-pop-text"><spring:message code="user.label.2" text="조직트리" /></span>
	    	</div>
			
			<%-- 트리 --%>
			<div id="orgTreeLst" class="admin-org-tree">
			    <ul>
<%	
	if(adminTree != null){		// 포탈 관리자 설정에서 관리자 추가 시 사용
		String _class = "class='closed'";
		String nodeId = adminTree.getOrgID();
		String nodeName = adminTree.getOrgName();
		String orgType = Integer.toString(adminTree.getOrgType());
		out.println("<ul><li id='"+nodeId +"' orgType='"+orgType+"' "+_class+"><a href='#'><ins></ins>"+nodeName+"</a></li>");
	}else{
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
	
				int intDepth = 0;
				
				if(depth>prevDepth) { 
					intDepth = depth - prevDepth;
					for(int j=0; j<intDepth;j++){
						out.println("</ul></li>");					
					}
				}
				
				if(depth < prevDepth) {
					out.println("<ul>");
				}
	
				out.println("<li id='"+nodeId+"' orgType='"+orgType+"' "+_class+" orgName='"+nodeName+"'><a href='#'><ins></ins>"+nodeName+"</a>");
				if(!hasChild) {
					out.println("</li>");
				}
				prevDepth = depth;
			}
		}else{
%>
    		<li id="treeROOT" class="open" orgType="0"><a href='#'><ins></ins><spring:message code="user.label.22" text="조직도" /></a>
<%		}
	}
%>
								</ul>
							</li>
					    </ul>
					</li>
			    </ul>
			</div>
			<%-- 트리 --%>
		</div>
		<div class="admin-tree-btn-group float-r">
			<span class="main-btn" id="div_btn_add_ok2" style="display: none;"><a href="#" id="btn_add_ok2">
			<spring:message code="portal.btn.label.1" text="확인" /></a></span>
		</div>
	</div>	
	<%-- container (사용자목록)--%>
	<div class="admin-content-pop" id="user_list_wrap" style="width:71%;">
		<!-- START Title -->
		<div class="title-pop">
			<span class="title-pop-text"><spring:message code="user.label.52" text="임직원찾기" /></span>
		</div>
		<!-- table_write -->
		<table class="table-search" id="search02">
		<thead>
			<tr>
	  			<th class="wleft"> 
					<select id="selSearchScope" name="select" style="WIDTH: 70px;">
						<OPTION value="0"><spring:message code="user.label.5" text="그룹 내" /></OPTION>
						<OPTION value="1"><spring:message code="user.label.6" text="회사 내" /></OPTION>
						<OPTION value="2"><spring:message code="user.label.7" text="부서 내" /></OPTION>
					</select>
				
					<select id="selSearchType" name="select2" style="WIDTH: 80px;">
						<OPTION value="byName"><spring:message code="user.label.8" text="성명" /></OPTION>
						<OPTION value="byId"><spring:message code="user.label.9" text="ID" /></OPTION>
					</select>
					<input type="text" id="userTxtSearch" class="input-d" style="WIDTH: 140px;" />
					<span class="smain-btn" id="btn_search_user"><a href="#">검색</a></span>
					<span class="smain-btn" id="btn_user_list_col"><a href="#"><spring:message code="user.label.21" text="칼럼선택" /></a></span>
					<span class="smain-btn" id="btn_show_tree"><a href="#"><spring:message code="user.label.22" text="조직도" /></a></span>
				</th>
			</tr>
		</thead>
		</table>
		<!-- //table_write -->
	
		<div id="user_list"></div>
			
		<!-- //버튼 -->
		<div style="margin-top:0px;" class="aright mt10" id="page_sbutton_group">
			<span class="smain-btn"><a href="#" id="btn_add_user" ><spring:message code="portal.btn.label.34" text="추가" /></a></span>
			<span class="smain-btn"><a href="#" id="btn_del_user" ><spring:message code="portal.btn.label.30" text="삭제" /></a></span>
		</div>
		<!-- //버튼 -->
	
		<div class="h25"></div>
		<div id="select_list_wrap">
		<!-- 선택목록 -->
			<!-- START Title -->
			<div class="title-pop">
				<span class="title-pop-text"><spring:message code="user.label.4" text="선택목록" /></span>
				<span id="userCnt" class="text-red" value="0">0</span>
				<span class="text-black"> <spring:message code="user.label.27" text="명" /></span>
			</div>
		
		    <table class="table-search">
			<tbody>
			    <tr>
					<td><select id="chkdList" class="input-d" style="width:100%; height:70px;" name="chkdListNm" multiple></select></td>
			    </tr>
			</tbody>
			</table>
		</div>	
			<div align="center">
				<span class="main-btn" id="div_btn_add_ok"><a href="#" id="btn_add_ok"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
			</div>
		<!-- //선택목록-->
		
		<input type="hidden" name="userCntVal" id="userCntVal" value=0 />
		<input type="hidden" name="selectedOrgId" id="selectedOrgId" value="" />
		<input type="hidden" name="orgType" id="orgType" value=0 />
		<input type="hidden" name="rightSmallDiv" id="rightSmallDiv" value=0 />
		
		<div id="alphabg"></div>
		
		<!-- 회원정보 팝업 -->
		<div id ="userLst">
			<!-- userInfo -->
			<div id="getUserView"></div>
			<div class="mb5"></div>
		</div>
		<!-- //회원정보 팝업 -->
	
		<%-- 칼럼선택 팝업 --%>
		<div id="selectColumn" title="칼럼 선택">
			<div class="pop-line">
				<table class="table-search">
				<thead>
					<tr>
						<td width="100">
							<input type='checkbox' id='chkCol' name='chkCol' checked="checked" disabled="disabled" />
							<spring:message code="user.label.8" text="성명" />
						</td>
						<td width="100">
							<input type='checkbox' id='chkCol' name='chkCol' checked="checked" disabled="disabled" />
							<spring:message code="user.label.9" text="ID" />
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input type='checkbox' id='chkCol' name='chkCol' checked="checked"  value='compName' class="col" />
							<spring:message code="user.label.10" text="회사" />
						</td>
						<td>
							<input type='checkbox' id='chkCol' name='chkCol' checked="checked"  value='deptName' class="col" />
							<spring:message code="user.label.11" text="부서" />
						</td>
					</tr>
					<tr>
						<td>
							<input type='checkbox' id='chkCol' name='chkCol' checked="checked"  value='grade' class="col" />
							<spring:message code="user.label.12" text="직급" />
						</td>
						<td>
							<input type='checkbox' id='chkCol' name='chkCol' checked="checked"  value='officeTel' class="col" />
							<spring:message code="user.label.16" text="회사전화" />
						</td>
					</tr>
					<tr>
						<td>
							<input type='checkbox' id='chkCol' name='chkCol' value='mobile' class="col" />
							<spring:message code="user.label.17" text="휴대폰" />
						</td>
						<td>
							<input type='checkbox' id='chkCol' name='chkCol' value='email' class="col" />
							<spring:message code="user.label.18" text="email" />
						</td>
					</tr>
					<tr>
						<td>
							<input type='checkbox' id='chkCol' name='chkCol' value='officeAddr' class="col" />
							<spring:message code="user.label.19" text="회사주소" />
						</td>
						<td>
							<input type='checkbox' id='chkCol' name='chkCol' value='officeFax' class="col" />
							<spring:message code="user.label.20" text="회사FAX" />
						</td>
					</tr>
				</tbody>
				</table>
				<div class="mt5"></div>
				<div align="center">
					<span class="main-btn"><a href="#" id="btn_sel_col"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
				</div> 
			</div>
		</div>
		<%-- 칼럼선택 팝업--%>
	</div>
</body>
</html>
