<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.common.ConstantList" %>
<%@ page import="com.sds.acube.luxor.ws.client.orgservice.*" %>
<%@ page import="com.sds.acube.luxor.ws.client.aclservice.*" %>
 
<%
	String compId ="";
	String userUid ="";
	String treeType = UtilRequest.getString(request,"treeType","3");	//트리 Type  *0 : From User Dept Code to Root Code  *1 : From User Dept Code to Institution Code *2 : From User Dept Code to Company Code *3 : From User Dept Code to Root Code (Exclude Other Company) 
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	
	if(userProfile == null) {
		out.println("<script type=\"text/javascript\">alert('조회 권한이 없습니다.');window.close();</script>");
		return;
	}else{
		//연결조직정보에 대해서 부서정보나 직급/직위/직책등의 정보를 가져온다.
		compId = (String)session.getAttribute("RELATED_COMP_ID");
		userUid = userProfile.getUserUid();
	}
	String groupUrl = "/ep/user/getVirtualGroupList.do?orgId="+compId;
	String roleUrl = "/ep/user/getRoleList.do?compId="+compId;
	String aclGroupUrl = "/ep/user/getAclGroupList.do?orgId="+compId;
	
	String deptUrl = "/ep/org/getOrgTree.do?userUid="+userUid+"&treeType="+treeType;
	String userUrl = "/ep/user/getUserTree.do?userUid="+userUid+"&treeType="+treeType;
	String gradeUrl = "/ep/org/getGradeTree.do?gradeID=ROOT&compID="+compId;
	String positionUrl = "/ep/org/getPositionTree.do?PositionID=ROOT&compID="+compId;
	String titleUrl = "/ep/org/getTitleTree.do?titleID=ROOT&compID="+compId;
	String dutyUrl = "/ep/org/getDutyTree.do?dutyID=ROOT&compID="+compId;
	
	String[] goUrl = new String[9];
	goUrl[0] = deptUrl;
	goUrl[1] = userUrl;
	goUrl[2] = groupUrl;
	goUrl[3] = roleUrl;
	goUrl[4] = gradeUrl;
	goUrl[5] = positionUrl;
	goUrl[6] = titleUrl;
	goUrl[7] = dutyUrl;
	goUrl[8] = aclGroupUrl;

    String callbackMethod = CommonUtil.nullTrim(UtilRequest.getString(request, "call_back_method"));
    String resourceId = CommonUtil.nullTrim(UtilRequest.getString(request, "resourceID"));
    boolean canBeEmpty = true;
    
    String selectAclType = CommonUtil.nullTrim(UtilRequest.getString(request, "selectAclType","all"));

    AccessList accessInfos = (AccessList)request.getAttribute("accessInfos");
    String callback = (String)request.getAttribute("callback");
%>

<html lang="ko">
<head>
<title><spring:message code="acl.label.1" text="권한설정" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jsTree/jquery.tree.js"></script>
<script type="text/javascript">
	var accesses = new Array();
	var goUrl = new Array();
	goUrl[0] = '<%=deptUrl%>';
	goUrl[1] = '<%=userUrl%>';
	goUrl[2] = '<%=groupUrl%>';
	goUrl[3] = '<%=roleUrl%>';
	goUrl[4] = '<%=gradeUrl%>';
	goUrl[5] = '<%=positionUrl%>';
	goUrl[6] = '<%=titleUrl%>';
	goUrl[7] = '<%=dutyUrl%>';
	goUrl[8] = '<%=aclGroupUrl%>';

	<%-- 권한 객체 정의 --%>
	function Permission(R, W, M, D){
	    this.READ = R;
	    this.WRITE = W;
	    this.MODIFY = M;
	    this.DELETE = D;

	    this.toString = permissionToString;
	    this.getText = permissionGetText;
	}
	
	function permissionToString(){
	    var buff = "";
	    if (this.READ) {
	        buff = "R";
	    }

	    if (this.WRITE) {
	        buff += "W";
	    }

	    return buff;
	}
	
	function permissionGetText(){
	    var permText = this.toString();
	    if (permText == "") {
	        permText = '<spring:message code="acl.label.6" text="권한없음" />';
	    }

	    return "[" + permText + "]";
	}

	<%-- Access ID 객체 정의 --%>
	function Access(comp_id, access_id, access_name, access_type){
	    this.comp_id = comp_id;
	    this.access_id = access_id;
	    this.access_name = access_name;
	    this.access_type = access_type;
	    this.permission = new Permission(true, false, false, false);
	    this.toString = accessToString;
	    this.getText = accessGetText;
	}
	
	function accessToString(){	
	    return (this.access_id + "<%= ConstantList.DELIM_IN %>"
	            + this.access_name + "<%= ConstantList.DELIM_IN %>"
	            + this.access_type + "<%= ConstantList.DELIM_IN %>"
	            + this.permission.toString() + "<%= ConstantList.DELIM_IN %>"
	            + this.comp_id);
	}
	
	function accessGetText(){
	    var buff = "";
	    if (this.access_type == "<%= ConstantList.TYPE_VIRTUAL_GROUP %>") {
	        buff = "<spring:message code="acl.label.msg.7" text="가상그룹 - "/>";
	    }
	    else if (this.access_type == "<%= ConstantList.TYPE_ACL_GROUP %>") {
	        buff = "<spring:message code="acl.label.msg.13" text="권한그룹 - "/>";
	    }
	    else if (this.access_type == "<%= ConstantList.TYPE_ROLE %>") {
	        buff = "<spring:message code="acl.label.msg.8" text="역할 - "/>";
	    }
	    else if (this.access_type == "<%= ConstantList.TYPE_DEPT %>") {
	        buff = "<spring:message code="acl.label.msg.9" text="부서 - "/>";
	    }
	    else if (this.access_type == "<%= ConstantList.TYPE_TITLE %>") {
	        buff = "<spring:message code="acl.label.msg.10" text="직급 - "/>";
	    }
	    else if (this.access_type == "<%= ConstantList.TYPE_USER %>") {
	        buff = "<spring:message code="acl.label.msg.11" text="사용자 - "/>";
	    }
	    else if (this.access_type == "<%= ConstantList.TYPE_COMPANY %>") {
	        buff = "<spring:message code="acl.label.msg.12" text="회사 - "/>";
	    }

	    buff += this.access_name + "   " + this.permission.getText();
	    return buff;
	}

	function addAccess(access)	{
		// duplicate check...
	    for (var i=0; i < accesses.length; i++)
	    {
	        if ((accesses[i].comp_id == "" || accesses[i].comp_id == access.comp_id) &&
	            accesses[i].access_id == access.access_id)
	        {
	        	 alert(access.access_name + '<spring:message code="portal.alert.msg.27" text="이미 추가되어있습니다."/>');
		         return;
	        }
	    }

	    var optionObj = document.createElement('option');

	    try {
			$("#access_id_list").append(optionObj,null);
	    } catch (e) {
			$("#access_id_list").append(optionObj); // For IE
		}
	    accesses[accesses.length] = access;
	}

	function deleteAccess(index){
	    accesses = accesses.slice(0, index).concat(accesses.slice(index+1));
	}

</script>

<script type="text/javascript">

	$(document).ready(function() {

		changeLeftCategory();
		var comp_id = "";
		var id = "";
		var name = "";
		var type = "";
<%
		int accessCount = accessInfos.getAccessList().size();
		Access vo;
	    Permission permInfo;
	    
		for (int i=0; i < accessCount; i++){
			vo = accessInfos.getAccessList().get(i);
			permInfo = vo.getPermission();
	        if (permInfo != null) {
%>				
				comp_id = "";
				id = "<%= vo.getAccessId() %>";
				name = "<%= vo.getAccessName() %>";
				type = "<%= vo.getAccessType() %>";
				var accessObj = new Access(comp_id, id, name, type);
				accessObj.permission = new Permission(<%= permInfo.isReadable() %>,
		                                          <%= permInfo.isWriteable() %>,
		                                          <%= permInfo.isModifiable() %>,
		                                          <%= permInfo.isDeleteable() %>);
				addAccess(accessObj);
<%			}  
		} 
%>
		displayAllAccess();

		// 확인 버튼 클릭시
		$('#btn_ok').click(function() {
			save();
		});

		// 취소 버튼 클릭시
		$('#btn_cancel').click(function() {
			self.close();
		});

		// 추가 버튼 클릭시
		$('#add_list').click(function() {
			addAccessID();
		});
		
		// 삭제 버튼 클릭시
		$('#del_list').click(function() {
			removeMember();
		});    

		// 읽기(R) 체크 버튼 클릭시
		$('#id_check_read').click(function() {
			toggleRead();
		});    
		
		// 쓰기(W) 체크 클릭시
		$('#id_check_write').click(function() {
			toggleWrite();
		});    

		// 쓰기(W) 체크 클릭시
		$('#id_check_delete').click(function() {
			toggleDelete();
		});    

		// 위쪽방향 화살표 버튼 클릭시
		$('#moveUp').click(function() {
			moveUp();
		});    

		// 아래쪽방향 화살표 버튼 클릭시
		$('#moveDown').click(function() {
			moveDown();
		});    

		// selectbox 선택에 따른 체크박스 변화
	    $('#access_id_list').change(function(){
	        var selectedVal = $("option:selected", this).val();
	        toggleCheck();
 		});

	    $('#input_virtualGroupSearch').keypress(function(e) {
			if(e.keyCode==13) {
				$('#btn_virtualGroupSearch').click();
			}
		});
		
		$('#btn_virtualGroupSearch').click(function() {
			$("#treeFrame").attr("src", "/ep/user/getVirtualGroupList.do?searchKey="+encodeURIComponent($('#input_virtualGroupSearch').val()));
		});
	});
	
	function changeLeftCategory(){
		$("#strg2").val("");
		$("#strg3").val("");
		var selectVal = goUrl[$("#left_category").val()];
		if( selectVal.indexOf("Virtual") != -1 ) {
			$('#virtualGroupSearch').show();
		} else {
			$('#virtualGroupSearch').hide();
			$('#input_virtualGroupSearch').val('');
		}
		$("#treeFrame").attr("src", selectVal);
	}
	
	function removeMember(){
	    var indexArray = new Array();
	    var listObj = document.aclForm.access_id_list;
	    for (var i=0; i < listObj.options.length; i++)
	    {
	        if (listObj.options[i].selected == false) {
	            continue;
	        }
	
	        listObj.options[i].selected = false;
	        indexArray[indexArray.length] = i;
	    }
	
	    for (var i=0; i < indexArray.length; i++) {
	        deleteAccess(indexArray[i] - i);
	    }
	
	    listObj.length -= indexArray.length;
	    displayAllAccess();
	}

	function addAccessID(){
	    var index = parseInt($('#left_category').val());
	    switch (index){
	        // department
	        case 0 :
	            var idName = $('#strg2').val().split("/");
	            addAccessToList("<%= compId %>", idName[0], idName[1], "<%= ConstantList.TYPE_DEPT %>");
	            break;
	        // user
	        case 1 :
		        document.getElementById('treeFrame').contentWindow.$(".box:checked").each(function() {
					$("#"+$(this).val()).remove();
					result = $(this).val();
					var meminfoArray = result.split("\^");
					var userUid = meminfoArray[6];
					var userName = meminfoArray[1];
					
		            addAccessToList("<%= compId %>", userUid, userName, "<%= ConstantList.TYPE_USER %>");
				});
	            break;
	        // virtual group
	        case 2 :
	            addMembersFromList("virtual_group_list", "<%= ConstantList.TYPE_VIRTUAL_GROUP %>");
	            break;
	        // role
	        case 3 :
	            addMembersFromList("role_list", "<%= ConstantList.TYPE_ROLE %>");
	            break;
	        // grade
	        case 4 :
	        	document.getElementById('treeFrame').contentWindow.$("#ROOT li .clicked").each(function() {
					var gradeName = $(this).closest('li').attr('treenodename');
					var gradeId = $(this).closest('li').attr('id');
		            addAccessToList("<%= compId %>", gradeId, gradeName, "<%= ConstantList.TYPE_GRADE %>");
				});
	            break;
	        // position
	        case 5 :
	        	document.getElementById('treeFrame').contentWindow.$("#ROOT li .clicked").each(function() {
					var positionName = $(this).closest('li').attr('treenodename');
					var positionId = $(this).closest('li').attr('id');
		            addAccessToList("<%= compId %>", positionId, positionName, "<%= ConstantList.TYPE_POSITION %>");
				});
	            break;
           // title
	        case 6 :
	        	document.getElementById('treeFrame').contentWindow.$("#ROOT li .clicked").each(function() {
					var titleName = $(this).closest('li').attr('treenodename');
					var titleId = $(this).closest('li').attr('id');
		            addAccessToList("<%= compId %>", titleId, titleName, "<%= ConstantList.TYPE_TITLE %>");
				});
	            break;
	      // duty
	        case 7 :
	        	document.getElementById('treeFrame').contentWindow.$("#ROOT li .clicked").each(function() {
					var dutyName = $(this).closest('li').attr('treenodename');
					var dutyId = $(this).closest('li').attr('id');
		            addAccessToList("<%= compId %>", dutyId, dutyName, "<%= ConstantList.TYPE_DUTY %>");
				});
	            break;
	    }

		displayAllAccess();
	}

	<%-- 권한 수정 함수 --%>
	function toggleRead(){
		if(!$('#id_check_read').attr('checked')) {
			$('#id_check_write').attr('checked', '');
			$('#id_check_write').attr('checked', '');
	    }
	    var i = 0;
		$('#access_id_list').each(function(){
			$('option', this).each(function() {
				if(this.selected == true){
					if($('#id_check_read').attr('checked')) {
			            accesses[i].permission.READ = true;
			        }
			        else {
			            accesses[i].permission.READ = false;
			            accesses[i].permission.WRITE = false;
			            accesses[i].permission.MODIFY = false;
			            accesses[i].permission.DELETE = false;
			        }
	
			        displayAccess(i);
			        $("#access_id_list option:eq("+i+")").attr('selected', 'selected');
				}
		        i++;
			})
		});
	}

	function toggleWrite(){
	    if ($('#id_check_write').attr('checked')) {         
	        $('#id_check_read').attr('checked', 'true');
	    }
	    else {
	        $('#id_check_mod_del').attr('checked', '');         
	    }

	    var i = 0;
		$('#access_id_list').each(function(){
			$('option', this).each(function() {
				if(this.selected == true){

					if($('#id_check_write').attr('checked')) {
			            accesses[i].permission.READ = true;
			            accesses[i].permission.WRITE = true;
			        }
			        else {
			            accesses[i].permission.WRITE = false;
			            accesses[i].permission.MODIFY = false;
			            accesses[i].permission.DELETE = false;
			        }

			        displayAccess(i);
			        $("#access_id_list option:eq("+i+")").attr('selected', 'selected');
				}
		        i++;
			})
		});
	}

	function toggleDelete() {
		if($('#id_check_mod_del').attr('checked')) {
			$('#id_check_read').attr('checked', '');
			$('#id_check_write').attr('checked', '');
	    }

	    var i = 0;
		$('#access_id_list').each(function(){
			$('option', this).each(function() {
				if(this.selected == true){
					if($('#id_check_mod_del').attr('checked')) {
			            accesses[i].permission.READ = true;
			            accesses[i].permission.WRITE = true;
			            accesses[i].permission.MODIFY = true;
			            accesses[i].permission.DELETE = true;
			        }
			        else {
			            accesses[i].permission.MODIFY = false;
			            accesses[i].permission.DELETE = false;
			        }
			        
			        displayAccess(i);
			        $("#access_id_list option:eq("+i+")").attr('selected', 'selected');
				}
		        i++;
			})
		});
	}

	function addMembersFromList(selectName, access_type){
		var divName = document.getElementById('treeFrame').contentWindow.$("#"+selectName);
		var index = divName.attr('selectedIndex');
		if (index < 0) {
			return;
		}

		divName.each(function(){
			$('option', this).each(function() {
				if(this.selected == true){
			        var arr = this.value.split(",");
			        addAccessToList("<%= compId %>"
			                    , arr[1]
			                    , this.text
			                    , access_type);
				}
			})
		});

	}
	
	function addAccessToList(comp_id, id, name, type){
	    if (id == null || id.length == 0 || id == "ROOT") {
	        return;
	    }
	    var accessObj = new Access(comp_id, id, name, type);
	    accessObj.permission = new Permission(
	                                    $('#id_check_read').attr('checked')
	                                  , $('#id_check_write').attr('checked')
	                                  , $('#id_check_mod_del').attr('checked')
	                                  , $('#id_check_mod_del').attr('checked'));         
	    addAccess(accessObj);
	}

	function displayAllAccess(){
	    for (var i=0; i < accesses.length; i++) {
	        displayAccess(i);
	    }
	}

	function displayAccess(index){
		$("#access_id_list option:eq("+index+")").text(accesses[index].getText());
	}

	function moveUp(){
		var selectedCnt = $("#access_id_list option:selected").size();
		if(selectedCnt>1){
			alert('<spring:message code="portal.alert.msg.28" text="1개만 선택해주세요."/>');
			return;
		}
		
	    var index = $('#access_id_list').attr('selectedIndex');
	    if (index < 1) {
	        return;
	    }

	    var tempAccess = accesses[index];
	    accesses[index] = accesses[index-1];
	    accesses[index-1] = tempAccess;
	    index = index - 1;
        $("#access_id_list option").attr("selected","");
	    displayAllAccess();
        $("#access_id_list option:eq("+index+")").attr("selected","selected");
	}

	function moveDown(){
		var selectedCnt = $("#access_id_list option:selected").size();
		if(selectedCnt>1){
			alert('<spring:message code="portal.alert.msg.28" text="1개만 선택해주세요." />');
			return;
		}
		
	    var index = $('#access_id_list').attr('selectedIndex');
	    if (index > (accesses.length-2) || index <0) {
	        return;
	    }

	    var tempAccess = accesses[index];
	    accesses[index] = accesses[index+1];
	    accesses[index+1] = tempAccess;
	    index = index + 1;
        $("#access_id_list option").attr("selected","");
	    displayAllAccess();
        $("#access_id_list option:eq("+index+")").attr("selected","selected");
	}

	// selectbox 선택된 값에 따른 체크박스 체크하기
	function toggleCheck(){

		var selectedCnt = $("#access_id_list option:selected").size();
		if(selectedCnt>1){
			return;
		}

	    var index = $('#access_id_list').attr('selectedIndex');
	    if (index > (accesses.length-1) || index <0) {
	        return;
	    }

		if(accesses[index].permission.READ && !accesses[index].permission.WRITE) {
			$('#id_check_read').attr('checked','true');
			$('#id_check_write').attr('checked','');
        }else if(accesses[index].permission.WRITE) {
			$('#id_check_read').attr('checked','true');
			$('#id_check_write').attr('checked','true');
        }else if(!accesses[index].permission.READ) {
			$('#id_check_read').attr('checked','');
			$('#id_check_write').attr('checked','');
        }
	}
	
	function save(){
		var resourceId = "<%=resourceId%>";
	    var allAccesses = "";
	    for (var i=0; i < accesses.length; i++){
	        if (i > 0) {
	            allAccesses += "<%= ConstantList.DELIM_OUT %>";
	        }
	        allAccesses += accesses[i].toString();
	    }

	    if (<%= canBeEmpty %> == false && allAccesses == ""){
			alert('<spring:message code="portal.alert.msg.24" text="선택을 해주세요."/>');
	        return;
	    }
		callJson("accessCotrolController","aclSave","resourceID="+resourceId+"&accessInfos="+encodeURIComponent(allAccesses), function(data) {
			if(eval(data)==true) {
				alert("<spring:message code="portal.alert.msg.16" text="적용되었습니다."/>");
			} else {
				alert("<spring:message code="portal.alert.msg.9" text="오류가 발생하였습니다."/>");
			}
			
			<%	if(!"".equals(callback)) { %>
			try {
				opener.<%=callback%>();
			} catch(e) {}
			<%	} %>
			
			self.close();
		});
	}
		
</script>
</head>
<body>
	<form id="aclForm" name="aclForm">
    <!-- admin-content-width -->
	<div class="admin-content-width w805 ml15" name="zone">
    	<!-- title-sub -->
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="acl.label.1" text="권한설정" /></span>
		</div>
		<!-- //subtitle -->
		
	    <!-- w100percent --> 
	    <div class="w100percent">
	        <!-- admin-content-width w330 -->
	        <div class="admin-content-width w330">
				<select id="left_category" onChange="changeLeftCategory();" style="float:left;margin-top:1px;">
				<%
				if(selectAclType.equals("all")){
				%>
					<option value="0" ><spring:message code="user.label.11" text="부서" /></option>				
					<option value="1" ><spring:message code="user.label.35" text="사용자" /></option>
					<option value="2" ><spring:message code="user.label.36" text="가상그룹" /></option>
					<option value="3" ><spring:message code="user.label.37" text="역할" /></option>
					<option value="4" ><spring:message code="user.label.12" text="직급" /></option>
					<option value="5" ><spring:message code="user.label.15" text="직위" /></option>
					<option value="6" ><spring:message code="user.label.14" text="직책" /></option>
					<option value="7" ><spring:message code="user.label.13" text="직무" /></option>
				<% }else{ %>
				
				<% if(selectAclType.indexOf("D") > -1){%><option value="0" ><spring:message code="user.label.11" text="부서" /></option><%} %>
				<% if(selectAclType.indexOf("U") > -1){%><option value="1" ><spring:message code="user.label.35" text="사용자" /></option><%} %>
				<% if(selectAclType.indexOf("V") > -1){%><option value="2" ><spring:message code="user.label.36" text="가상그룹" /></option></option><%} %>
				<% if(selectAclType.indexOf("R") > -1){%><option value="3" ><spring:message code="user.label.37" text="역할" /></option><%} %>
				<% if(selectAclType.indexOf("G") > -1){%><option value="4" ><spring:message code="user.label.12" text="직급" /></option><%} %>
				<% if(selectAclType.indexOf("P") > -1){%><option value="5" ><spring:message code="user.label.15" text="직위" /></option><%} %>
				<% if(selectAclType.indexOf("T") > -1){%><option value="6" <spring:message code="user.label.14" text="직책" /></option><%} %>
				<% if(selectAclType.indexOf("Y") > -1){%><option value="7" ><spring:message code="user.label.13" text="직무" /></option><%} %>
			
				<% 
				} 
				%>					
					<%--<option value="<%=aclGroupUrl%>"><spring:message code="user.label.38" text="권한그룹" /></option>--%>
				</select>
				<div id="virtualGroupSearch" style="float:right;margin-right:10px;">
					<input type="text" class="input-d" id="input_virtualGroupSearch" style="width:185px;"/>
				
					<span class="smain-btn">
					<a href="#" id="btn_virtualGroupSearch"><span class="btnicon-01"></span><spring:message code="portal.btn.label.8" text="검색" /></a>
					</span>
				</div>
		        <!-- box-border h400 -->
				<iframe name="treeFrame" id="treeFrame" class="box-border" style="overflow: hidden; height:425px"></iframe>

		        <!-- //box-border h400 -->
	        </div>
	        <!-- //admin-content-width w330 -->

	        <!-- admin-content-width w80 -->
	        <div class="admin-content-width w70 ml15 ">
	            <div class="box-btn">
	                <span class="smain-btn"><a href="#" id="add_list" style="height:19px;vertical-align:top;"><span class="btnicon-02" style="float:left;"></span>
	                <spring:message code="portal.btn.label.34" text="추가" /></a></span> 
	                <div class="mb3"></div>
	                <span class="smain-btn"><a href="#" id="del_list" style="height:19px;vertical-align:top;"><span class="btnicon-03" style="float:left;"></span>
	                <spring:message code="portal.btn.label.30" text="삭제" /></a></span> 
	            </div>
	        </div>
	        <!-- //admin-content-width w80 -->
	        
	        <!-- admin-content-width w330 -->
	        <div class="admin-content-width w330">
	            <input name="id_check_read" type="checkbox" id="id_check_read" checked="checked" />
	            <label for="id_check_read" id="id_check_read"><spring:message code="acl.label.2" text="읽기(R)" /></label>
	            <input name="id_check_write" type="checkbox" id="id_check_write" />
                <label for="id_check_write" id="id_check_write"><spring:message code="acl.label.3" text="쓰기(W)" /></label>
                <span style="display: none;">
					<input type="checkbox" name="check_perm" id="id_check_mod_del" />
					<label for="id_check_mod_del"  id="id_check_mod_del"><spring:message code="acl.label.5" text="권한수정/삭제" /></label>
	       		</span>
		        <!-- box-border -->
		        <div class="box-border" style="height:425px">
					<select name="access_id_list" id="access_id_list" multiple onDblClick="removeMember();" style = "height:426px;width:310px;">
					</select>
		        </div>
		        <!-- //box-border -->
	        </div>
	        <!-- //admin-content-width w330 -->
        
	        <!-- admin-content-width w50 -->
	        <div class="admin-content-width w50 ml10">
	            <div class="box-btn">
	                <span class="smain-btn"><a href="#" id="moveUp"><span class="btnicon-04"></span></a></span> 
	                <div class="mb3"></div>
	                <span class="smain-btn"><a href="#" id="moveDown"><span class="btnicon-05"></span></a></span> 
	            </div>
	        </div>
	        <!-- //admin-content-width w50 -->
	    </div>  
	    <!-- //w100percent -->   
        
		<!-- box-font-blue -->  
     	<div class="box-font-blue">
			<spring:message code="acl.label.4" text="- 권한은 위에서부터 순서대로 적용됩니다." />
	        <!-- button -->
	        <div class="aright">
		        <span class="main-btn"><a href="#" id="btn_ok"><spring:message code="portal.btn.label.1" text="확인" /></a></span>
		        <span class="main-btn"><a href="#" id="btn_cancel"><spring:message code="portal.btn.label.2" text="취소" /></a></span>
	        </div>
	        <!-- //button -->
	        <br/>
	        <spring:message code="portal.page.label.14" text="- 어떠한 설정도 되어있지 않은 경우 모든 사용자에게 접근권한이 부여됩니다." />
	    </div>
    	<!-- //box-font-blue -->          
	</div>
    <!-- //admin-content-width -->
	
    <input type="hidden" name="accesses" id="accesses" value="" />
    <input type="hidden" id="strg2" name="strg2" value="" />
    <input type="hidden" id="strg3" name="strg3" value="" />
    </form>
</body>
</html>
