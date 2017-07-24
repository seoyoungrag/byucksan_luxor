<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.ConstantList"%>
<%@ page import="java.util.Locale"%>
<%@ page import="com.sds.acube.luxor.portal.vo.AdminUserVO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sds.acube.luxor.portal.vo.UserPortalVO"%>
<%/*
	상단 메뉴를 구성하는 페이지
	기본적으로 상단 메뉴는 기본 3단으로 구성되지만 
	CSS 수정을 통하여 1단, 2단, 3단으로 구성 가능함
	
	Javascript는 js/portlet_main.js, css는 css/portlet_main.css 에 정의되어있음
	imagePath = 표현할 페이지의 테마경로/portletId/
*/
%>

<%
	boolean usePersonalMenu = "Y".equals(LuxorConfig.getEnvString(request, "USE_PERSONAL_MENU"));
	String contextPath = request.getContextPath();
	String currentPath = (String)request.getAttribute("currentPath");
	String portletContext = (String)request.getAttribute("portletContext");
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	List<UserPortalVO> userPortalList = (List<UserPortalVO>)session.getAttribute("JOIN_PORTAL_IDS");
	String userUid = ""; 
	String deptId = "";
	if(userProfile != null) {
		userUid = userProfile.getUserUid();
		deptId = userProfile.getDeptId();
	}
	String themeImageUrl = UtilRequest.getString(request, "themeImageUrl");
	String initHome = CommonUtil.nullTrim((String)session.getAttribute(ConstantList.US_CODE_INITIAL_PAGE));
	Locale langType = (Locale)session.getAttribute("LANG_TYPE");

	//어드민여부판단
	AdminUserVO[] admins = LuxorConfig.getAdminList();
	boolean isAdmin = false;
	String adminUserUid = "";
	String isIgnoreAcl = "";
	String portalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
	List<String> accessIdList = (List<String>)session.getAttribute("ACCESS_ID_LIST");
	if(admins != null) {
		for(int j = 0 ; j < admins.length ; j++) {
			if(admins[j].getAdminType() == 0) {
				if(accessIdList.contains(admins[j].getUserUid())) {
					portalId = "_ROOT_";
					adminUserUid = admins[j].getUserUid();
					isIgnoreAcl = admins[j].getIsIgnoreAcl();
					isAdmin = true;
				}
			} else if(admins[j].getAdminType() == 1) { 
				if(accessIdList.contains(admins[j].getUserUid()) && admins[j].getPortalId().equals(CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID")))) {
					portalId = admins[j].getPortalId();
					adminUserUid = admins[j].getUserUid();
					isIgnoreAcl = admins[j].getIsIgnoreAcl();
					isAdmin = true;
				}
			} 
		}
	}
%>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/luxor/ref/bics/css/common.css" />
<script type="text/javascript">
	var portal_label_76 = "<spring:message code="portal.label.76" />";
	var portal_label_77 = "<spring:message code="portal.label.77" text="컨텐츠 추가" />";
	var portal_alert_msg_56 = "<spring:message code="portal.alert.msg.56" text="검색 결과가 없습니다." />";
	var user_alert_msg_9 = "<spring:message code="user.alert.msg.9" text="초기화면으로 설정하시겠습니까?" />";
	var portal_alert_msg_9 = "<spring:message code="portal.alert.msg.9" text="오류가 발생했습니다." />";
	var portal_alert_msg_13 = "<spring:message code="portal.alert.msg.13" text="저장되었습니다." />";
	var portal_authorization_msg_2 = "<spring:message code="portal.authorization.msg.2" text="로그인이 필요합니다." />";
	var portal_tooltip_msg_01 = "<spring:message code="portal.mymenu.alert.1" text="마이메뉴에서 해제하시려면 클릭하세요" />";
	var portal_tooltip_msg_02 = "<spring:message code="portal.mymenu.alert.2" text="마이메뉴에 등록하시려면 클릭하세요" />";
	var portal_tooltip_msg_03 = "<spring:message code="portal.mymenu.alert.3" text="초기 페이지로 등록된 페이지입니다." />";
	var portal_tooltip_msg_04 = "<spring:message code="portal.mymenu.alert.4" text="이 페이지를 초기페이지로 등록하시려면 클릭하세요" />";
	var userUid = "<%=userUid%>";
	var initHome = "<%=initHome%>";
	var langType = "<%=langType%>";
	luxor.usePersonalMenu = <%=usePersonalMenu%>;
	luxor.isLoggedIn = <%= (userProfile != null) %>
	
	$(document).ready(function(){
		if(userUid!=''){
			setImage(userUid);
		
		}	
	});

	function goAdminPage() {
		window.open("<%=contextPath%>/luxor/admin","luxor_admin","");
	}

	function setAclIgnore(type) {
		postJson('adminController','setAclIgnoreType','isIgnoreAcl='+type+'&portalId=<%=portalId%>&userUid=<%=adminUserUid%>',function(data) {
			self.location.reload();
		});	
	}
	
	// 사용자 이미지 호출
	function setImage(Uid) {
		var userUid = Uid;
		var param = 'userUid='+userUid;
		var image = "";
				//
			$("#viewimage1").attr("src","/acube/iam/identity/userinfoimage/Select.go?requestImage=picture&userId="+userUid);
			$("#viewimage2").attr("src","/acube/iam/identity/userinfoimage/Select.go?requestImage=picture&userId="+userUid);
					
	 /* 	  $.ajax({
			type:'post',
			timeout: 5000,
			async:false,
			dataType:'json',
		 url:'/ep/loginimage/getImage.do?isMain=Y&'+param,  
			url:'/acube/iam/identity/userinfoimage/Select.go?requestImage=picture&userId='+userUid,
					
			success:function(data) {
				image = data.pictureType;
				alert(data);
				/* if(image == ""){
				$("#viewimage").attr("src","/ep/luxor/ref/img/user_img.jpg");
				}else{
				$("#viewimage").attr("src","/acube/iam/identity/userinfoimage/Select.go?requestImage=picture&userId="+userUid);
				}; 
		
			},
			error:function(data,sataus,err) {
					
					alert("유저 이미지 조회 실패");
			} 
		}); 	 
		 */
	}
	
	// 로그아웃 이미지 클릭시 호출
	function logoutProcess(){
	 if(confirm("로그아웃 하시겠습니까?")) {
		callJson("logoutSysController", "getList", "", function(data) {
			for(var i=0; i < data.length; i++) {
				$('body').append("<iframe id='logoutHelpIframe"+i+"' class='dialog'></iframe>");
				$('#logoutHelpIframe'+i).attr('src',data[i].logoutUrl);
			}
			
			callJson("loginController", "logoutProcessByPortlet", "", function(data) {
				setTimeout(function() {
					
						top.location.href="/ep/bics_login.jsp";
					
				}, 300);
			});
		});	
	 }
	}
	
</script>

<div id="<%=portletContext%>" class="main-header">
    <div id="header" type="zone" class="luxor-top">
         <div class="toplogo">
             <span style="float:left; margin-top:10px"><a href="<%=contextPath%>"><img src="<%=contextPath%>/luxor/ref/bics/img/logo.jpg" alt="벽산 BICS"/></a></span>
             <span style="float:right; margin-top:5px">
             <table border="0" cellspacing="2" cellpadding="0">
                <tr>
                  <td><img src="<%=contextPath%>/luxor/ref/bics/img/user_img.jpg" alt="user" class="topphoto" id="viewimage1"/></td>
                  <td class="topname"><%=userProfile.getUserName()%></td>
                  <td><img src="<%=contextPath%>/luxor/ref/bics/img/user_arrow.jpg"/></td>
                  <td width="40" align="right"><img src="<%=contextPath%>/luxor/ref/bics/img/top_user_icon01.png"/></td>
                  <td width="40" align="right"><a href="javascript:;" onclick="goAdminPage();"><img src="<%=contextPath%>/luxor/ref/bics/img/top_user_icon02.png"/></a></td>
                  <td width="40" align="right"><a href="javascript:;" onclick="logoutProcess();"><img src="<%=contextPath%>/luxor/ref/bics/img/top_user_icon03.png"/></a></td>
                </tr>
              </table>
           </span>
         </div>
     <div style="clear:both">
       <table width="1350" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px">
            <tr>
              <td width="300" align="left" class="topblog">
	              <a href="/luxor_collaboration/blg/BlgHome.do?method=blgHome" target="_blank"><img src="<%=contextPath%>/luxor/ref/bics/img/icon_top01.jpg" alt="이미지"/>블로그</a> &nbsp;&nbsp;ㅣ&nbsp;&nbsp; 
	              <a href="/luxor_collaboration/tsp/tspSpacePage.do?method=getSpacePage&directGoSpaceDomain=main" target="_blank"><img src="<%=contextPath%>/luxor/ref/bics/img/icon_top02.jpg" alt="이미지"/>커뮤니티</a>
              </td>
              <td align="center">
                <table width="500" border="0" cellspacing="0" cellpadding="0" class="topmenu">
                <!-- 메뉴 구성 -->
             	      		<div class="mainCenter">
<!-- 상단메뉴 -->
<div class="etcinfo">
      <div class="main-menu">
	<ul id="mzTop" class="dropdown">
     	</div>
  </div>
 </div>
</div>
<div style="width:100%;float:left;height:34px;">
	<div class="main-middle-center">
		<!-- 중간메뉴 -->
<ul id="mzMiddle" class="top-menu dropdown" style="padding-left:3px;"></ul>
	</div>
</div>
                </table>
             </td>
              <td width="300" align="right">
              <table width="100" border="0" cellspacing="0" cellpadding="0">
                <tr>
	                <select id="searchWay" style="display: none;" title="<spring:message code="portal.label.210" text="검색조건" />">
								<option value="usersearch"><spring:message code="portal.label.74" text="인명검색" /></option>
								<option value="menusearch"><spring:message code="portal.label.75" text="메뉴검색" /></option>
					</select>
	                <td> <input type="text"  id="searchKey" class="topinput" placeholder="임직원찾기"/></td>
	                <td><a href="#" id="doSearch"><img src="<%=contextPath%>/luxor/ref/bics/img/btn_search_top.jpg" alt="search"/></a></td>
                </tr>
              </table></td>
         </tr>
       </table>
      </div>
    </div>
</div>

<%-- 검색결과 팝업 --%>
<div id="search_result_dialog" class="dialog" style="width:180px;border:1px solid #828282;">
	<div id="search_result" style="overflow:auto;padding:5px;height:200px;"></div>
	<div style="border-top:1px solid #c2c2c2;background:#F5F5F5;text-align:right;padding:3px;">
		<a href='#' onclick="$('#search_result_dialog').hide()"><span style="color:#454545"><b><spring:message code="portal.btn.label.29" text="닫기"/></b></span></a>
	</div>
</div>

<input type="hidden" id="userUid" value="<%=userUid%>" />
<input type="hidden" id="deptId" value="<%=deptId%>" />
