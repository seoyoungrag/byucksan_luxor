<%@page import="com.sds.acube.luxor.security.UtilSSO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*, java.net.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>
<%@ page import="org.springframework.web.util.WebUtils" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.sds.acube.luxor.portal.service.AdminService" %>
<%@ page import="com.sds.acube.luxor.portal.vo.AdminUserVO" %>
<%@ page import="com.sds.acube.luxor.common.util.CommonUtil" %>
 
 <%
 
	String userinfo = "";
	String loginId ="";
	Date tbbdate = new Date();
	SimpleDateFormat tbbspreparation = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat schedule = new SimpleDateFormat("yyyyMMdd");
	String initSDate = schedule.format(tbbdate).toString() + "0000";
	request.setAttribute("userinfo","Y");		//request.getAttribute("userinfo")에서 값을 받아야 하는데 null이기 때문에 임시로 Y 값을 할당 하였다.
	userinfo = (String) request.getAttribute("userinfo");
	String userId = "";
	String userName = "";
	String challenge = "1";



	String contextPath = request.getContextPath();
/* 	CommonUtil.sessionInit(session); // 세션 초기화
	session.setAttribute("TENANT_ID", LuxorConfig.getString("BASIC.TENANT_ID"));
	session.setAttribute("LANG_TYPE", new Locale("ko", "KR"));
	session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale("ko","KR"));
 */	
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");

	boolean isLogin = false;
		
	if(!(userProfile == null || (userProfile.getLoginResult() != 0))) {
		userId = userProfile.getUserUid();
		userName = userProfile.getUserName();
	}
	if(userProfile != null) {
	  	isLogin = true;
		loginId = userProfile.getLoginId();
	}
	
	
	int adminCount = 1;
	
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	AdminService service = (AdminService)ctx.getBean("adminService");
	AdminUserVO param = new AdminUserVO();
	param.setTenantId((String)session.getAttribute("TENANT_ID"));
	adminCount = service.checkExistAdminUser(param);
	
    String[] localeLanguage = LuxorConfig.getString("LOCALE.LANG").split(",");
    String[] displayName = LuxorConfig.getString("LOCALE.DISPLAY-NAME").split(",");
    
    /* boolean useHttps = "Y".equals(LuxorConfig.getString("BASIC.USE_HTTPS"));
    boolean useAES = "Y".equals(LuxorConfig.getString("BASIC.USE_AES")); */
    boolean useHttps = true;
    boolean useAES = true;

	if(adminCount == 0) {
		String installed = LuxorConfig.getString("BASIC.INSTALLED");
		if("Y".equals(installed)) {
			CommonUtil.scriptAlert(response, "You need to change INSTALLED option to N from Common.xml", "");
			return;
		}
		
		CommonUtil.script(response, "document.location.href = '"+request.getContextPath()+"/admin/getAdminUserList.do?option=init';");
		return;
	}
	
	/***로그인 세션처리 ***/
	String encodeAlgorithm ="";
	String D1 ="";
	encodeAlgorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
	D1 = UtilSSO.encodeSession(request, encodeAlgorithm);  
%>
  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/luxor/common/header.jsp" />
<jsp:include page="/luxor/deploy/topMenuPortlet/jsp/view1.jsp"></jsp:include>
<title>벽산 Bics</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/luxor/ref/jquery/jquery/jquery-ui/themes/cupertino/jquery-ui-1.8.16.custom.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/luxor/ref/css/common.css" />

<style type="text/css">

/* .slidesjs-pagination {display:none;} */

</style>

<script type="text/javascript" src="/ep/luxor/ref/js/jquery-1.4.3.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.slides.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/luxor.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/content.js" charset="utf-8"></script>
<script type="text/javascript" src="/ep/luxor/ref/js/jquery-ui.js" charset="utf-8"></script>


<script type="text/javascript">

var pageId ="";
var boardIdData = "";
var tenantId = "ESIS000";
var portalId = "A10000";
/* var postId = ""; */
var bbsTabVal = 0;
var approvalTabVal = 0;
var workinStatus = 0;
//var userUid = "Ua67c9c6aa7ac4031ff9";
var tbbscontent = "";      
  
var userinfo = "<%=userinfo%>";
var isLogin = <%=isLogin%>;
<%
if(userProfile == null || userProfile.getLoginResult() != 0){
%>
	var userUid = "";
<%
}else{
%>
	var userUid = "<%=userProfile.getUserUid()%>";
	var userName = "<%=userProfile.getUserName()%>";
<%
}
%>


var getTodayFullDate = new Date();
var getTYear = getTodayFullDate.getFullYear();
var getTMonth = (getTodayFullDate.getMonth())+1;
var getTDate = getTodayFullDate.getDate();
getTDate = parseInt(getTDate) < 10 ? ('0'+getTDate) : getTDate;
var getTFullDate = getTYear + "/" + getTMonth + "/" + getTDate;
/* var receiveDataType = 0; */

$(document).ready(function(){
	// 화면 중앙 상단 업무공지 등등 리스트 가져오기(전체 리스트가 기본)

	if(userUid!=''){
		setImage(userUid);
	
		}	
	
	
	rightTabClick(2);
	
 /* 	 	getbyucksanBlogData();
 	 	
 		getBbsData('getBoardList');
 		commonEvent(); 		
 		getnoticeData();
 		getSurvey();
 		getlastestList();
 	
 		getSchData1();
 		getSchData2();
 		getSchData3(); */
 	
		$('#tab00').click(function() { // 
			/* pageId="N201401151407273471089"; */
			bbsTabVal = 0;
			boardIdData = "201511201012266761003OD2ZdYmrubK";			//업무공지사항
			getBbsData('getBoardList'); 
	
		});
		$('#tab01').click(function() { // 
			bbsTabVal = 1;
			boardIdData = "201511221620392701168cA85VOU4h7E";			//부서공지
			
			getBbsData('getBoardListsecond');
			
		});
		$('#tab02').click(function() { //
			bbsTabVal = 2;
			boardIdData = "";			//인사발령
			getBbsData('getBoardListfourth');
		});
 		
		$('#tab03').click(function() { //
			bbsTabVal = 3;
			boardIdData = "201511201018346541012XfkCx8EwKdd";			//경영혁신
			getBbsData('getBoardListthird');
		});
 		
		$('#tab04').click(function() { //
			bbsTabVal = 4;
			boardIdData = "";			//e-벽산
			getBbsData('getBoardListfifth');
		});
		
		$('#tab05').click(function() { //대기함
			approvalTabVal = 0;
			getApprovalList(approvalTabVal);
		});
		
		$('#tab06').click(function() { //진행함
			approvalTabVal = 1;
			getApprovalList(approvalTabVal);
		});
		
		$('#tab07').click(function() { //완료함
			approvalTabVal = 2;
			getApprovalList(approvalTabVal);
		});
		
		$('#tab08').click(function() { //공람함
			approvalTabVal = 3;
			getApprovalList(approvalTabVal);
		});
		
		$('#schInsert').click(function() { //
			
			/* document.location.href = '/ep/page/index.do?pageId=N201409260925254761061'; */
			
		});
		
		
		
		$('#btnLOGOUT').live('click', function(e) {
			 if(confirm("로그아웃 하시겠습니까?")) {
				 logoutProcess();
			 }
		});
 		
		$('#bbsmore').click(function() { // 
			switch(bbsTabVal){
			case 0:
				document.location.href = '/ep/page/index.do?pageId=N201511200949085951085';
				break;
			case 1:
				document.location.href = '/ep/page/index.do?pageId=N201511211512325931547';
				break;
			case 2:
				document.location.href = '/ep/page/index.do?pageId=N201511200949220321089';
				break;
			case 3:
				document.location.href = '/ep/page/index.do?pageId=N201511200949537701096';
				break;
			case 4:
				document.location.href = '/ep/page/index.do?pageId=N201511200949385291092';
				break;
			}	
		});
		
		$('#approvalMore').click(function() { // 
			goApprovalList(approvalTabVal);
		});
});

function goApprovalList(tabIndex){
	switch(tabIndex){
	case 0:
		// 포털의 대기함을 결재의 수신함으로 이동시킨다. 용어가 햇갈린다.
		document.location.href = '/ep/page/index.do?pageId=N201511211543189731838&junjaSign|toggleMenu=Approval&junjaSign|boxCode=linkMenu_OPT103&junjaSign|boxUrl=/ep/app/list/approval/ListApprovalWaitBox.do';
		break;
	case 1:
		document.location.href = '/ep/page/index.do?pageId=N201511211543189731838&junjaSign|toggleMenu=Approval&junjaSign|boxCode=linkMenu_OPT104&junjaSign|boxUrl=/ep/app/list/approval/ListProgressDocBox.do';
		break;
	case 2:
		document.location.href = '/ep/page/index.do?pageId=N201511211543189731838&junjaSign|toggleMenu=Approval&junjaSign|boxCode=linkMenu_OPT110&junjaSign|boxUrl=/ep/app/list/complete/ListApprovalCompleteBox.do';
		break;
	case 3:
		document.location.href = '/ep/page/index.do?pageId=N201511211543189731838&junjaSign|toggleMenu=PubRead&junjaSign|boxCode=linkMenu_OPT110&junjaSign|boxUrl=/ep/app/list/etc/ListDisplayNotice.do?readRange=DRS002';
		break;
	case 4:
		//document.location.href = '/ep/page/index.do?pageId=N201511211543189731838';
		break;
	} 
}


$(function() {
	
 	$("#datepicker2").datepicker({
		defaultDate: new Date(),
		showMonthAfterYear:true,
		 monthNames:['년 1월','년 2월','년 3월','년 4월','년 5월','년 6월','년 7월','년 8월','년 9월','년 10월','년 11월','년 12월'],
		dateFormat: "yy-mm-dd" ,
		dayNamesMin: ['일','월','화','수','목','금','토'], 
	/* 	prevText: '이전 달',
	    nextText: '다음 달', */
		showOn: "both",
		showAnim: "blind", // blind, clip, drop, explode, fold, puff, slide, scale, size, pulsate, bounce, highlight, shake, transfer
		showOptions: {direction: 'horizontal'},
		duration: 200,
		
		onChangeMonthYear: function (year, month, inst) {   
		// 년 또는 월이 변경시 이벤트 발생   
		EvtChangeMonthYear(year, month);   
		            },   
		  
		beforeShow: function (input, inst) {   
		// 일자 선택되기전 이벤트 발생   
		            },   
		 onSelect: function (dateText, inst) {   
		// 일자 선택된 후 이벤트 발생   
			getSchListbySelectDay(dateText);
		            }
		
	});
	
/* 
    $('#slides').slidesjs({
     width: 680,
     height: 190,
     navigation: false,
     play: {
       auto: true,
       interval: 5000
     }
   }); */
   
   
   
 });

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

function EvtChangeMonthYear(Year, Month) {   
    $(".ui-datepicker-current-day").attr("style", "background-color:#ff0000;");  // 선택된 날자에 테두리를 만든다.
 
var arrSplit = ($("#datepicker2").val()).split("-");        // 선택된 날자를 배열로 받음   

var vDt = new Date();   
    var Day = getStrDay(vDt.getDate());   
    var dtMin = new Date(Year, Month - 1, 1);   

    dtMin = new Date(Year, Month - 1, 1 - dtMin.getDay());       // 달력의 최초 날자를 구하기 위해   
    var strMin = dtMin.getFullYear() + "-" + getStrMonth(dtMin.getMonth() + 1) + "-" + getStrDay(dtMin.getDate());   
    var dtMax = new Date(new Date(Year, Month, 1) - 86400000);   

    var dtMax = new Date(Year, Month - 1, dtMax.getDate() + 6 - dtMax.getDay()); // 달력의 마지막 날자를 구하기 위해   
    var strMax = dtMax.getFullYear() + "-" + getStrMonth(dtMax.getMonth() + 1) + "-" + getStrDay(dtMax.getDate());   
}   

function getStrMonth(Month) {   
    Month = Month + "";   
    if (Month.length == 1) {   
        Month = "0" + Month;   
    }   
    return Month;   
}   

function getStrDay(Day) {   
    Day = Day + "";   
    if (Day.length == 1) {   
        Day = "0" + Day;   
    }   
    return Day;   
}

function logoutProcess(){
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

function getSchListbySelectDay(dateText){
	
	var dateData = dateText.split('-');
	var yearData = dateData[0];
	var monthData = dateData[1];
	var dayData = dateData[2];
	var sendDateData = yearData+monthData+dayData; 
	

	
		getSchData1(sendDateData);
		getSchData2(sendDateData);
		getSchData3(sendDateData);

}

function bbsTabClick(index){
	var pos = $(".tabmenu01 > ul > li").length;
	
	$(".tabmenu01 > ul > li").removeClass();
	$(".tabmenu01 > ul > li").eq(index-1).addClass("tab_on0"+index);
	$(".tabmenu01 > ul > li").eq(index-1).css("z-index",47);
	for(var i = 0; i < pos; i++){
		if(i == (index-1)){
			$(".tabmenu01 > ul > li").eq(i).css("z-index",20);
			continue;
		}else{
			$(".tabmenu01 > ul > li").eq(i).addClass("tab_off0"+ (i+1));
			$(".tabmenu01 > ul > li").eq(i).css("margin-left",(i*82)+"px");
			$(".tabmenu01 > ul > li").eq(i).css("z-index",pos-i);
		}
	}
 
}

function approvalTabClick(index){
	var pos = $(".tabmenu02 > ul > li").length;
	
	$(".tabmenu02 > ul > li").removeClass();
	$(".tabmenu02 > ul > li").eq(index-1).addClass("tab_on0"+index);
	$(".tabmenu02 > ul > li").eq(index-1).css("z-index",47);
	for(var i = 0; i < pos; i++){
		if(i == (index-1)){
			$(".tabmenu02 > ul > li").eq(i).css("z-index",20);
			continue;
		}else{
			$(".tabmenu02 > ul > li").eq(i).addClass("tab_off0"+ (i+1));
			$(".tabmenu02 > ul > li").eq(i).css("margin-left",(i*82)+"px");
			$(".tabmenu02 > ul > li").eq(i).css("z-index",pos-i);
		}
	}
	
}

function rightTabClick(index){
	
	var pos = $(".div_box_count > li").length;
		$(".div_box_count > li").text("");
 	 	$(".div_box_count > li").removeClass();		 
		$(".div_box_count > li").eq(index-2).addClass("counton0"+index);
		$(".div_box_count > li").eq(index-2).append("<p id='wrkCount'>0</p>")
		
		for(var i = 0; i < pos; i++){ 
			$(".div_box_count > li").eq(i).addClass("countoff0"+(i+2));	 
		}
		
		/* if(index == 1){
			getnoticeData();
		}  */		
		if(index == 2){
			getWorkinData(0);
			$('.tit_box_work').show();
		}
		if(index == 3){
			getcommunityData();
			$('.tit_box_work').hide()
		}
		if(index == 4){	
			getBlogData();
			$('.tit_box_work').hide()
		} 
 		
}

function getApprovalList(index){
	
	
	var methodName = "";
	var addHtml = "";
	var approvalPageId = "N201511211543189731838";
	
	if(index == 0){
		methodName = 'waitCount';
	}else if(index == 1){
		methodName = 'processCount';
	}else if(index == 2){
		methodName = 'approvalEndCount';
	}else if(index == 3){
		methodName = 'approvalDisplayCount';
	}

	 $.ajax({
			type:'post',
			timeout: 5000,
			async:false,
			dataType:'json',
			url:'/luxor_collaboration/bbs/approval.do?method='+methodName+'&userUid='+userUid,
			success:function(data) {
				$('#approvalList').text("");
				
				if(index == 0){
					$('#approvalWaitCount').text(data.count);
				}else if(index == 1){
					$('#approvalProcessCount').text(data.count);
				}else if(index == 2){
				}else if(index == 3){
					$('#approvalDisplayCount').text(data.count);
				}

				if(data.approvalList.length == 0 ){
					addHtml += "<tr><td class='con_list_tit'>";
					addHtml += "등록된 게시물이 없습니다." + "</td>";
					addHtml += "<td width='90' class='con_list_day'></td></tr>";
					
					$('#approvalList').append(addHtml);
				}
				else{			
					
					 for(var i = 0; i < data.approvalList.length && i < 5; i++){	
						
						var docId = data.approvalList[i].docId;
						var postId = "";
						var boardId = "";
						var dpdata = data.approvalList[i].title;
						var dpcreatorName = data.approvalList[i].drafterName;
						var dpcreatorDeptName = data.approvalList[i].drafterDeptName;
						
						if(index==3){
							addHtml += "<tr><td class='con_list_tit' style='cursor:pointer' onclick=\"goApprovalDirection('"+ docId +"','"+ data.approvalList[i].publishId +"');\">"+ dpdata;
							addHtml += "</td>";
							addHtml += "<td width='120' class='con_list_day'>"+data.approvalList[i].publishDeptName+"</td>";
							addHtml += "<td width='120' class='con_list_day'>"+data.approvalList[i].publisherName+"</td>";
							addHtml += "<td width='90' class='con_list_day'>"+data.approvalList[i].publishDate.substring(0,10)+"</td></tr>";
						}else{
							addHtml += "<tr><td class='con_list_tit' style='cursor:pointer' onclick=\"goApprovalDirection('"+ docId +"');\">"+ dpdata;
							addHtml += "</td>";
							addHtml += "<td width='120' class='con_list_day'>"+dpcreatorDeptName+"</td>";
							addHtml += "<td width='120' class='con_list_day'>"+dpcreatorName+"</td>";
							addHtml += "<td width='90' class='con_list_day'>"+data.approvalList[i].draftDate.substring(0,10)+"</td></tr>";
						}
					}
					$('#approvalList').append(addHtml); 
				};
				
				
				
			},
			error:function(data,sataus,err) {						
					alert("getApprovalList데 실패 했습니다.");
			}
		});  	
	
	
}

function getApprovalWaitCount(){
	
	
		 $.ajax({
				type:'post',
				timeout: 5000,
				async:false,
				dataType:'json',
				url:'/luxor_collaboration/bbs/approval.do?method=waitCount&userUid='+userUid,
				success:function(data) {
					$('#approvalWaitCount').text(data.count);
				},
				error:function(data,sataus,err) {						
						alert("결재 대기함 카운트를 가져 오는데 실패 했습니다.");
				}
			});  	
	
	
}

function getApprovalProcessCount(){
	
	
	 $.ajax({
			type:'post',
			timeout: 5000,
			async:false,
			dataType:'json',
			url:'/luxor_collaboration/bbs/approval.do?method=processCount&userUid='+userUid,
			success:function(data) {
				$('#approvalProcessCount').text(data.count);
			},
			error:function(data,sataus,err) {						
					alert("결재 진행함 카운트를 가져 오는데 실패 했습니다.");
			}
		});  	
}

function getApprovalDisplayCount(){
	
	
	 $.ajax({
			type:'post',
			timeout: 5000,
			async:false,
			dataType:'json',
			url:'/luxor_collaboration/bbs/approval.do?method=approvalDisplayCount&userUid='+userUid,
			success:function(data) {
				$('#approvalDisplayCount').text(data.count);
			},
			error:function(data,sataus,err) {						
					alert("공람함 카운트를 가져 오는데 실패 했습니다.");
			}
		});  	
}

function getApprovalEndCount(){
	
	
	 $.ajax({
			type:'post',
			timeout: 5000,
			async:false,
			dataType:'json',
			url:'/luxor_collaboration/bbs/approval.do?method=approvalEndCount&userUid='+userUid,
			success:function(data) {
				//$('#approvalDisplayCount').text(data.count);
			},
			error:function(data,sataus,err) {						
					alert("결재완료함 카운트를 가져 오는데 실패 했습니다.");
			}
		});  	


}

function getnoticeData(){

	var addHtml = "";
	var noticePageId = "N201511200949085951085";			//알림에 관한 페이지 정의가 없어 임시로 전사공지 게시판으로 지정함.
	$("#rightNoticeBox").html("");
	boardIdData = "201511201018102331009XulG6Dr5WaM";
	
	
	$.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		url:'/luxor_collaboration/bbs/bbsPostCommon.do?method=lst&id.boardId='+ boardIdData + '&isMain=Y',
		success:function(data) {
			
			if(data.bbsPostCommonList.length == 0){
				for(var i = 0; i < 5; i++){
					if(i == 0){
						addHtml += "<li><p>등록된 게시물이 없습니다.</p>";
						addHtml += "<p class='p_text11px_gray'></p></li>";
					}else{
						addHtml += "<li><p>&nbsp;</p>";
						addHtml += "<p class='p_text11px_gray'>&nbsp;</p></li>";
					}
				}
				
			}else{
				for(var i = 0; i < data.bbsPostCommonList.length; i++){
					
					var postId = data.bbsPostCommonList[i].postId;
					var boardId = data.boardId;
					var kdate = new Date(data.bbsPostCommonList[i].createDate);
					var nowdate = new Date();
					var nkcount = (nowdate.getTime() - kdate.getTime());
					var nkrealcount = Math.floor(nkcount/(24*3600*1000));
					var dpdate =  kdate.getFullYear() + "-" 
										  + ((kdate.getMonth()+1)<10 ? '0'+(kdate.getMonth()+1):(kdate.getMonth()+1)) + "-" 
						                       + ((kdate.getDate())<10 ? '0'+(kdate.getDate()):(kdate.getDate()));
					var dpdata = data.bbsPostCommonList[i].postTitle;
					var dpcreatorName = data.bbsPostCommonList[i].creatorName;
					
				addHtml += "<li style='width:270px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;'><p style='cursor:pointer;' onclick=\"goPostDirection('"+ postId +"', '"+ boardId +"', '"+ noticePageId +"')\">"+data.bbsPostCommonList[i].postTitle+"</p>";
				addHtml += "<p class='p_text11px_gray'>"+ dpcreatorName +"&nbsp;|&nbsp;" + dpdate +  "</p></li>";		
				}
			}
			
			$("#rightNoticeBox").append(addHtml);
		},
		error:function(data,sataus,err) {
				
				alert("알림 조회 실패");
		}
	}); 
	
}

function getWorkinData(receiveDataType){
	
	var workInPageId = "";

	$("#rightNoticeBox").html("");
	var methodType = "";
	if(receiveDataType == 0){
		methodType = "lst";
		workinStatus = 0;
		workInPageId = "N201511201522240011072";
		$("#workinlist").text("");
		$("#workinrlist").text("");
		$("#workinlist").append("<strong style='cursor:pointer;'>업무지시함</strong>");
		$("#workinrlist").append("<p style='cursor:pointer;'>업무접수함");
	}else{		
		methodType = "rlst";
		workinStatus = 1;
		workInPageId = "N201511201522396741074";
		$("#workinlist").text("");
		$("#workinrlist").text("");
		$("#workinrlist").append("<strong style='cursor:pointer'>업무접수함</strong>");
		$("#workinlist").append("<p style='cursor:pointer;'>업무지시함");
	}
	
	var addHtml = "";
	$.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		url:'/luxor_collaboration/wrk/wrkWork.do?method='+ methodType +'&isMain=Y&userProfile.userUid='+userUid,
		success:function(data) {
			$('#wrkCount').text(data.page.total);
			
 			if(data.list.length == 0){
				addHtml += "<li><p>등록된 게시물이 없습니다.</p>";
				addHtml += "<p class='p_text11px_gray'></p></li>";		
			}else{
				var style="";
				for(var i = 0; i < data.list.length; i++){
						if(i >=5){				//리스트가 5개를 넘을경우 화면에 표시 하지 않는다.
							continue;
						}	
						
				if(i == 0){
					style = " class=\"first\"";
				}else{
					style = "";	
				}
						
						
				addHtml += "<li "+style+"><p style='cursor:pointer; margin-top:5px;' onclick=\"goWorkInViewPage('"+receiveDataType+"', '"+data.list[i].id+"', '"+ workInPageId +" ')\">"+data.list[i].title+"</p>";
				addHtml += "<p class='p_text11px_gray' style='margin-top:3px;'>"+ data.list[i].wrkUserName +"&nbsp;|&nbsp;" + data.list[i].orderDate +  "</p></li>";
				
				}
			} 
			
			$("#rightNoticeBox").append(addHtml);
		},
		error:function(data,sataus,err) {
				
				alert("워크인 목록 조회 실패");
		}
	}); 
	
}


function getcommunityData(){
	
	$("#rightNoticeBox").html("");
	
	var addHtml = "";
	$.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		//url:'/luxor_collaboration/tsp/tspSpace.do?method=excellentSpaceListPortlet&isMain=Y&pageView=main',
		url:'/luxor_collaboration/tsp/bbsPost.do?method=latestlist&isMain=Y&pageView=main',
		
		success:function(data) {
			$('#wrkCount').text(data.resultPage.total);
			
			if(data.resultPage.list.length == 0){
				addHtml += "<li><p>신규 등록된 커뮤니티 게시물이 없습니다.</p>";
				addHtml += "<p class='p_text11px_gray'></p></li>";		
			}else{
				for(var i = 0; i < data.resultPage.list.length && i < 7; i++){
				addHtml += "<li><p style='cursor:pointer;' onclick=\"sbgoSpace('"+data.resultPage.list[i].id.spaceId + "', '"+data.resultPage.list[i].id.boardId + "', '"+data.resultPage.list[i].id.postId + "')\">"+data.resultPage.list[i].postTitle+"</p>";
				addHtml += "<p class='p_text11px_gray'>"+ data.resultPage.list[i].creatorName +"&nbsp;|&nbsp;" + data.resultPage.list[i].createDate1 +  "</p></li>";		
				}
			}
			
			$("#rightNoticeBox").append(addHtml);
		},
		error:function(data,sataus,err) {
				
				alert("커뮤니티 목록 조회 실패");
		}
	}); 
}

function getbyucksanBlogData(){
	
	$("#byucksanBlog").html("");
	var addHtml = "";

	$.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		url:'/luxor_collaboration/blg/BlgAdmin.do?method=blgAdminPopularBlogSetList&isMain=Y&tenantId=ESIS000&portalId=A10000',
		success:function(data) {
 			if(data.blogList.length == 0 ){
				addHtml += "<li><table border='0' cellspacing='0' cellpadding='0'><tr><td style='width:95px;'><img src='/ep/luxor/ref/img/blog_img.jpg'/></td><td>";
				addHtml += "<p>" + "등록된 인기블로그가 없습니다." + "</p>";
				addHtml += "</td></tr></table></li>";				
				$('#byucksanBlog').append(addHtml);
			}else{			
				for(var i = 0; i < data.blogList.length && i < 2; i++){	
					if(i == 0){
						addHtml += "<li class='first'><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td style='width:95px;'><img style='width:95px; height:62px;' src='/luxor_collaboration"+ data.blogList[i].blogImageName +"'/></td><td>";
						addHtml += "<p style='cursor:pointer;' class='p_text12px_blue' onclick=\"sbgoBlog('"+data.blogList[i].blogId + "')\">"+data.blogList[i].blogName+"<span class='p_text12px_red'></span></p>"
						addHtml += "<p class='p_text11px_gray'>" + data.blogList[i].userName+ "ㅣ" + data.blogList[i].blogCrtDate + "</p>";
						addHtml += "<p class='p_text12px_gray' style='width:165px; height:30px; overflow:hidden; text-overflow:ellipsis;'>"+ data.blogList[i].blogDescription +"</p></td></tr></table></li>";	
					}else{
						addHtml += "<li style='padding:15px 0;'><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td style='width:95px;'><img style='width:95px; height:62px;' src='/luxor_collaboration"+ data.blogList[i].blogImageName +"'/></td><td>";
						addHtml += "<p style='cursor:pointer;' class='p_text12px_blue' onclick=\"sbgoBlog('"+data.blogList[i].blogId + "')\">"+data.blogList[i].blogName+"<span class='p_text12px_red'></span></p>"
						addHtml += "<p class='p_text11px_gray'>" + data.blogList[i].userName+ "ㅣ" + data.blogList[i].blogCrtDate + "</p>";
						addHtml += "<p class='p_text12px_gray' style='width:165px; height:30px; overflow:hidden; text-overflow:ellipsis;'>"+ data.blogList[i].blogDescription +"</p></td></tr></table></li>";
					}
				}
				$('#byucksanBlog').append(addHtml);
			};	 	
		
		},
		error:function(data,sataus,err) {
				
				alert("error");
		}
	}); 
	
	
}


function getlastestList(){
	
	var addHtml = "";
	var addHtmlFirst = "";
	var latestPageId =    'N201511200948533991081';
	
	 $.ajax({
			type:'post',
			timeout: 5000,
			async:false,
			dataType:'json',
			url:'/luxor_collaboration/bbs/bbsMyBoardSet.do?D1=<%=D1%>&method=latestpostlst&isMain=Y',
			success:function(data) {
			
				$('#latestboardlist').text("");
				$('#latestListFirst').text("");
				if(data.bbsPostList.length == 0 ){
					for(var pos=0; pos < 4; pos++){
						if(pos == 0){
							addHtmlFirst += "<tr><td width='140' align='left'><img src='/ep/luxor/ref/img/blog_img.jpg' width='120' height='69' /></td><td><p class='p_text16px_blue'>";
							 addHtmlFirst += "<a href='#'>등록된 게시물이 없습니다.</a>";
							 addHtmlFirst += "<span class='p_text11px_gray' style='float:right'></span></p>";
							 addHtmlFirst += "<p></p></td></tr><tr><td height='10px' colspan='2' align='left'></td></tr>";
						}else{
							addHtml += "<tr><td class='con_list_tit'>";
							addHtml += "등록된 게시물이 없습니다." + "</td>";
							addHtml += "<td width='120' class='con_list_day'></td>";
							addHtml += "<td width='90' class='con_list_day'></td></tr>"
						}
					}
					$('#latestboardlist').append(addHtml);
				}
				else{			
					 for(var i = 0; i < data.bbsPostList.length; i++){
						 if(i >= 6){
							 continue;
						 }else{
							var postId = data.bbsPostList[i].id.postId;
							var boardId = data.bbsPostList[i].id.boardId;
							var dpContent = data.bbsPostList[i].content;
							/* alert(dpContent); */
							var StrippedString = dpContent.replace(/(<([^>]+)>)/ig,"");
							var kdate = new Date(data.bbsPostList[i].createDate);
							var nowdate = new Date();
							var nkcount = (nowdate.getTime() - kdate.getTime());
							var nkrealcount = Math.floor(nkcount/(24*3600*1000));
							var dpdate =  kdate.getFullYear() + "-" 
												  + ((kdate.getMonth()+1)<10 ? '0'+(kdate.getMonth()+1):(kdate.getMonth()+1)) + "-" 
								                       + ((kdate.getDate())<10 ? '0'+(kdate.getDate()):(kdate.getDate()));
							var dpdata = data.bbsPostList[i].postTitle;
							var dpcreatorName = data.bbsPostList[i].creatorName;
							var dpcreatorDeptName = data.bbsPostList[i].creatorDeptName;
							
							var splitDpDate = dpdate.split("-");
							var splitGetTFullDate = getTFullDate.split("/");
							
							if(splitGetTFullDate[0] == splitDpDate[0] && splitGetTFullDate[1] == splitDpDate[1] && splitGetTFullDate[2] == splitDpDate[2]){
								if(i == 0){ //최근 게시물의 첫번째 리스트 표시
									 addHtmlFirst += "<tr><td width='140' align='left'><img src='/ep/luxor/ref/img/blog_img.jpg' width='120' height='69' /></td><td><p class='p_text16px_blue'>";
									 addHtmlFirst += "<a href='#' style='cursor:pointer' onclick=\"goPostDirection('"+ postId +"', '"+ boardId+"', '"+ latestPageId +"  ');\">" + dpdata + "</a>";
									 addHtmlFirst += "<span class='p_text11px_gray' style='float:right; margin-right:10px;'>" + dpdate + "</span></p>";
									 addHtmlFirst += "<p style='height:30px; width:540px; overflow:hidden; text-overflow:ellipsis;'>"+StrippedString+"</p></td></tr><tr><td height='10px' colspan='2' align='left'></td></tr>"; 
									 }else{						
										addHtml += "<tr><td class='con_list_tit' style='cursor:pointer;' onclick=\"goPostDirection('"+ postId +"', '"+ boardId +"', '"+ latestPageId +"  ');\">"+ dpdata +"<img style='margin-left:8px; margin-top:-4px;' src='/ep/luxor/ref/img/icon_new.jpg' alt='new'/></td>";
										addHtml += "<td width='120' class='con_list_day'>"+dpcreatorDeptName+"</td>";
										addHtml += "<td width='120' class='con_list_day'>"+dpcreatorName+"</td>";
										addHtml += "<td width='90' class='con_list_day'>"+dpdate+"</td></tr>";
									 }	
							}else{
								if(i == 0){ //최근 게시물의 첫번째 리스트 표시
									 addHtmlFirst += "<tr><td width='140' align='left'><img src='/ep/luxor/ref/img/PS15112200343.jpg' width='120' height='69' /></td><td><p class='p_text16px_blue'>";
									 addHtmlFirst += "<a href='#' style='cursor:pointer' onclick=\"goPostDirection('"+ postId +"', '"+ boardId+"', '"+ latestPageId +"  ');\">" + dpdata + "</a>";
									 addHtmlFirst += "<span class='p_text11px_gray' style='float:right;  margin-right:10px;'>" + dpdate + "</span></p>";
									 addHtmlFirst += "<div style='height:30px; width:540px; overflow:hidden; text-overflow:ellipsis;'>"+StrippedString+"</div></td></tr><tr><td height='10px' colspan='2' align='left'></td></tr>"; 
									 }else{						
										addHtml += "<tr><td class='con_list_tit' style='cursor:pointer;' onclick=\"goPostDirection('"+ postId +"', '"+ boardId+"', '"+ latestPageId +"  ');\">"+ dpdata +"</td>";
										addHtml += "<td width='120' class='con_list_day'>"+dpcreatorDeptName+"</td>";
										addHtml += "<td width='120' class='con_list_day'>"+dpcreatorName+"</td>";
										addHtml += "<td width='90' class='con_list_day'>"+dpdate+"</td></tr>";
									 }
							}
							 
						 }
					}
					 $('#latestListFirst').append(addHtmlFirst);
					$('#latestboardlist').append(addHtml); 
				};
			},
			error:function(data,sataus,err) {
					
					alert("getlastestListerror");
			}
		}); 
}

function getBlogData(){
	
	$("#rightNoticeBox").html("");

	var addHtml = "";
	$.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		//url:'/luxor_collaboration/blg/BlgAdmin.do?method=blgAdminUserBlogAllList&isMain=Y',
		url:'/luxor_collaboration/blg/BlgPost.do?method=blgMainPostList&isMain=Y&pagePostCount=7',		
		
		success:function(data) {
			$('#wrkCount').text(data.resultPage.size);
			if(data.resultPage.list.length == 0 ){
				addHtml += "<li>";
				addHtml += "<p>" + "최근 등록된 블로그 게시물이 없습니다." + "</p>";
				addHtml += "<p></p></li>";				
				$('#rightNoticeBox').append(bbs1temp);
				}else{			
					for(var i = 0; i < data.resultPage.list.length && i < 7; i++){	
						addHtml += "<li><p style='cursor:pointer;' onclick=\"sbgoBlog('"+data.resultPage.list[i].blogId + "', '"+data.resultPage.list[i].blogPostId + "')\">"+data.resultPage.list[i].title+"</p>";
						addHtml += "<p class='p_text11px_gray'>"+ data.resultPage.list[i].postBlogName+ "&nbsp;|&nbsp;" +data.resultPage.list[i].createDate1 +"</p></li>";		
					}
					$('#rightNoticeBox').append(addHtml);
				};		
		
		},
		error:function(data,sataus,err) {
				
				alert("error");
		}
	}); 
	
}



function sbgoBlog(blogId){
	
	/* document.location.href = '/luxor_collaboration/blg/BlgMain.do?method=blgMain&blogId='+blogId; */
	luxor.popup('/luxor_collaboration/blg/BlgMain.do?method=blgMain&blogId='+blogId,{width:1520,height:900,scrollbars:"yes"});
	

}

function getSchData1(selectDate){
	var todayDateforSch = getTFullDate.split("/");
	var todayDateforSch1 = todayDateforSch[0]+todayDateforSch[1]+todayDateforSch[2]; 
	var getSchDataBySelectDate = "";
	var schedulePageId = "N201409260925254761061";
	var postId = "";
	var boardId = "";
	
	if(selectDate == null || selectDate == ""){
		getSchDataBySelectDate = '/luxor_collaboration/sch/schSchedule.do?method=getSchedulesAjax&calendarCategory=201404171355301921011vMGPSrwKe2b&changeOption=ALL&id.schId=&rangeType=PRIVATE&isMain=Y&schType=&schCategory=&listType=DAY&startDate='+ todayDateforSch1+'&endDate='+todayDateforSch1 +'&ownerUid='+userUid;
	}else{
		getSchDataBySelectDate = '/luxor_collaboration/sch/schSchedule.do?method=getSchedulesAjax&calendarCategory=201404171355301921011vMGPSrwKe2b&changeOption=ALL&id.schId=&rangeType=PRIVATE&isMain=Y&schType=&schCategory=&listType=DAY&startDate='+ selectDate+'&endDate='+selectDate +'&ownerUid='+userUid;
	}
	
	var tbodyhtml = "";
	
 	 $.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		url: getSchDataBySelectDate,	
		success:function(data) {
			$('#schboxtext1').text("");		
			
			if(data.schedule.length == 0){
				tbodyhtml += "<ul><li style='background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;'>등록된 일정이 없습니다.</li></ul>" ;	
				$('#schboxtext1').append(tbodyhtml);
			}
			else{
				tbodyhtml += "<ul>";
				for(var k = 0; k < data.schedule.length; k++) {
					if(k >=3){
						continue;
					}
					/* tbodyhtml += "<li style='cursor:pointer; background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;' name='viewContent' onclick=\"goPostDirection('"+ postId +"', '"+ boardId +"','"+data.schedule[k].id.schId +"');\">"+ data.schedule[k].title +"</li>"; */
					tbodyhtml += "<li style='cursor:pointer; background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;' name='viewContent' onclick=\"goSchDirection('"+ postId +"', '"+ boardId +"','"+ schedulePageId +"');\">"+ data.schedule[k].title +"</li>";
					}
				tbodyhtml += "</ul>";
				
				$('#schboxtext1').append(tbodyhtml);
			}
		
		
		},
		error:function(data,sataus,err) {
				
				alert("error");
		}
	});  	
	
}

function getSchData2(selectDate){
	var todayDateforSch = getTFullDate.split("/");
	var todayDateforSch1 = todayDateforSch[0]+todayDateforSch[1]+todayDateforSch[2]; 
	var getSchDataBySelectDate = "";
	var schedulePageId = "N201409260925254761061";
	var postId = "";
	var boardId = "";
	
	if(selectDate == null || selectDate == ""){
		getSchDataBySelectDate=  '/luxor_collaboration/sch/schSchedule.do?method=getSchedulesAjax&listType=&rangeType=PRIVATE&startDate='+todayDateforSch1+'&endDate='+todayDateforSch1+'&id.schId=&changeOption=ALL&ownerUid=&ownerName=&schCategory=&calendarCategory=COMP&schType=&title=&repeatId=';
		
	}else{
		getSchDataBySelectDate=  '/luxor_collaboration/sch/schSchedule.do?method=getSchedulesAjax&listType=&rangeType=PRIVATE&startDate='+selectDate+'&endDate='+selectDate+'&id.schId=&changeOption=ALL&ownerUid=&ownerName=&schCategory=&calendarCategory=COMP&schType=&title=&repeatId=';
	}
	
	var tbodyhtml = "";
	
 	 $.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		url: getSchDataBySelectDate,	
		success:function(data) {
			$('#schboxtext2').text("");		
			
			if(data.schedule.length == 0){
				tbodyhtml += "<ul><li style='background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;'>등록된 일정이 없습니다.</li></ul>" ;	
				$('#schboxtext2').append(tbodyhtml);
			}
			else{
				tbodyhtml += "<ul>";
				for(var k = 0; k < data.schedule.length; k++) {
					if(k >=3){
						continue;
					}
					/* tbodyhtml += "<li style='cursor:pointer; background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;' name='viewContent' onclick=\"goPostDirection('"+ postId +"', '"+ boardId +"','"+data.schedule[k].id.schId +"');\">"+ data.schedule[k].title +"</li>"; */
					tbodyhtml += "<li style='cursor:pointer; background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;' name='viewContent' onclick=\"goSchDirection('"+ postId +"', '"+ boardId +"','"+ schedulePageId +"');\">"+ data.schedule[k].title +"</li>";
					}
				tbodyhtml += "</ul>";
				
				$('#schboxtext2').append(tbodyhtml);
			}
		
		
		},
		error:function(data,sataus,err) {
				
				alert("error");
		}
	});  	
	
}

function getSchData3(selectDate){
	var todayDateforSch = getTFullDate.split("/");
	var todayDateforSch1 = todayDateforSch[0]+todayDateforSch[1]+todayDateforSch[2]; 
	var getSchDataBySelectDate = "";
	var schedulePageId = "N201409260925254761061";
	var postId = "";
	var boardId = "";
	
	if(selectDate == null || selectDate == ""){
		getSchDataBySelectDate=  '/luxor_collaboration/sch/schSchedule.do?method=getSchedulesAjax&listType=&rangeType=PRIVATE&startDate='+todayDateforSch1+'&endDate='+todayDateforSch1+'&id.schId=&changeOption=ALL&ownerUid=&ownerName=&schCategory=&calendarCategory=DEPT&schType=&title=&repeatId=';
		
	}else{
		getSchDataBySelectDate=  '/luxor_collaboration/sch/schSchedule.do?method=getSchedulesAjax&listType=&rangeType=PRIVATE&startDate='+selectDate+'&endDate='+selectDate+'&id.schId=&changeOption=ALL&ownerUid=&ownerName=&schCategory=&calendarCategory=DEPT&schType=&title=&repeatId=';
	}

	var tbodyhtml = "";
	
 	 $.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		url:getSchDataBySelectDate,	
		success:function(data) {
			$('#schboxtext3').text("");		
			
			if(data.schedule.length == 0){
				tbodyhtml += "<ul><li style='background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;'>등록된 일정이 없습니다.</li></ul>" ;	
				$('#schboxtext3').append(tbodyhtml);
			}
			else{
				tbodyhtml += "<ul>";
				for(var k = 0; k < data.schedule.length; k++) {
					if(k >=3){
						continue;
					}
					/* tbodyhtml += "<li style='cursor:pointer; background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;' name='viewContent' onclick=\"goPostDirection('"+ postId +"', '"+ boardId +"','"+data.schedule[k].id.schId +"');\">"+ data.schedule[k].title +"</li>"; */
					tbodyhtml += "<li style='cursor:pointer; background:url(/ep/luxor/ref/img/icon_list.jpg) no-repeat left center;' name='viewContent' onclick=\"goSchDirection('"+ postId +"', '"+ boardId +"','"+ schedulePageId +"');\">"+ data.schedule[k].title +"</li>";
					}
				tbodyhtml += "</ul>";
				
				$('#schboxtext3').append(tbodyhtml);
			}
		
		
		},
		error:function(data,sataus,err) {
				
				alert("error");
		}
	});  	
	
}


function getBbsData(functionName){
	
	var bbsPageId = "";
	if(functionName == 'getBoardList'){
		boardIdData = "201511201012266761003OD2ZdYmrubK";			//업무공지사항
		bbsPageId = 'N201511200949085951085';
	}
	if(functionName == 'getBoardListsecond'){
		boardIdData = "201511221620392701168cA85VOU4h7E";			//부서공지
		bbsPageId = 'N201511211512325931547&directChildMenu=Y&childCnt=24';
	}
	if(functionName == 'getBoardListthird'){
		boardIdData = "201511201018346541012XfkCx8EwKdd";			//경영혁신
		bbsPageId = 'N201511200949537701096';
	}
	if(functionName == 'getBoardListfourth'){
		boardIdData = "2015112010160012710061GJ6bF06bJv";			//인사발령
		bbsPageId = 'N201511200949220321089';
	}
	if(functionName == 'getBoardListfifth'){
		boardIdData = "201511201018102331009XulG6Dr5WaM";			//인사발령
		bbsPageId = 'N201511200949385291092';
	}

	var addHtml = "";
	
 	 $.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		url:'/luxor_collaboration/bbs/bbsPostCommon.do?method=lst&id.boardId='+ boardIdData + '&isMain=Y',
		success:function(data) {
		
			$('#boardlist').text("");
		
			if(data.bbsPostCommonList.length == 0 ){
				addHtml += "<tr><td class='con_list_tit'>";
				addHtml += "등록된 게시물이 없습니다." + "</td>";
				addHtml += "<td width='90' class='con_list_day'></td></tr>";
				
				$('#boardlist').append(addHtml);
			}
			else{			
				 for(var i = 0; i < data.bbsPostCommonList.length; i++){	
					if(i >=5){				//게시판에 5개 까지의 정보만 표시 한다.
						continue;
					}
					var postId = data.bbsPostCommonList[i].postId;
					var deptName = data.bbsPostCommonList[i].creatorDeptName;
					var boardId = data.boardId;
					var kdate = new Date(data.bbsPostCommonList[i].createDate);
					var nowdate = new Date();
					var nkcount = (nowdate.getTime() - kdate.getTime());
					var nkrealcount = Math.floor(nkcount/(24*3600*1000));
					var dpdate =  kdate.getFullYear() + "-" 
										  + ((kdate.getMonth()+1)<10 ? '0'+(kdate.getMonth()+1):(kdate.getMonth()+1)) + "-" 
						                       + ((kdate.getDate())<10 ? '0'+(kdate.getDate()):(kdate.getDate()));
					var dpdata = data.bbsPostCommonList[i].postTitle;
					var dpcreatorName = data.bbsPostCommonList[i].creatorName;
						
					var splitDpDate = dpdate.split("-");
					var splitGetTFullDate = getTFullDate.split("/"); 
					
					if(splitGetTFullDate[0] == splitDpDate[0] && splitGetTFullDate[1] == splitDpDate[1] && splitGetTFullDate[2] == splitDpDate[2]){
						addHtml += "<tr><td class='con_list_tit' style='cursor:pointer; overflow:hidden; text-overflow:ellipsis; white-space:nowrap; ' onclick=\"goPostDirection('"+ postId +"', '"+ boardId +"','"+ bbsPageId +"');\">"+ dpdata;
						addHtml += "<img style='margin-left:8px; margin-top:-4px;' src='/ep/luxor/ref/img/icon_new.jpg' alt='new'/></td>";
					}else{
						addHtml += "<tr><td class='con_list_tit' style='cursor:pointer; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;' onclick=\"goPostDirection('"+ postId +"', '"+ boardId +"','"+ bbsPageId +"');\">"+ dpdata;
						addHtml += "</td>";
					}
					addHtml += "<td width='120' class='con_list_day'>"+deptName+"</td>";
					addHtml += "<td width='120' class='con_list_day'>"+dpcreatorName+"</td>";
					addHtml += "<td width='90' class='con_list_day'>"+dpdate+"</td></tr>";
					
				}
				$('#boardlist').append(addHtml); 
			};
		},
		error:function(data,sataus,err) {
				
				alert("error");
		}
	});   
}


function commonEvent(){
	
	var eventPageId = 'N201511200951003971110';
	
	var bbs1temp = "";
	$("#comevent").html("");
	 $.ajax({
			type:'post',
			timeout: 5000,
			async:false,
			dataType:'json',
			url:'/luxor_collaboration/bbs/bbsPostCommon.do?method=lst&id.boardId=201511201021224221032dhThtdfBKDW&isMain=Y&pageSize=3',
			success:function(data) {
				 if(data.bbsPostCommonList.length == 0 ){
					for(var pos = 0; pos < 3; pos++){ 
					bbs1temp += "<li>";
					bbs1temp += "<p>" + "등록된 게시물이 없습니다." + "</p>";
					bbs1temp += "<p></p></li>";
					}
					$('#comevent').append(bbs1temp);
				}
			 else{			
					for(var i = 0; i < data.bbsPostCommonList.length && i<3; i++){
						/* bbs1temp += "<li><img style='float:left;' src='/ep/luxor/ref/img/icon_happy.jpg' alt='경사'/>"; */
						var kdate = new Date(data.bbsPostCommonList[i].updateDate);
						var nowdate = new Date();
						var nkcount = (nowdate.getTime() - kdate.getTime());
						var nkrealcount = Math.floor(nkcount/(24*3600*1000));
						var dpdate = kdate.getFullYear() + "-" 
											  + ((kdate.getMonth()+1)<10 ? '0'+(kdate.getMonth()+1):(kdate.getMonth()+1)) + "-" 
							                       + ((kdate.getDate())<10 ? '0'+(kdate.getDate()):(kdate.getDate()));
						var dpdata = data.bbsPostCommonList[i].postTitle;		 						
						bbs1temp += "<div style='text-align:left; cursor:pointer; padding:2px 0; width:265px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis;' onclick=\"goPostDirection('"+data.bbsPostCommonList[i].postId +"', '"+ data.boardId+"', '"+ eventPageId +"');\">"	 + dpdata + "</div>";				
						/* bbs1temp += "<p style='float:left;' onclick=\"goPostDirection('"+data[i].postId +"', '"+ data[i].boardId+"');\">" + dpdata + "</p>";
						bbs1temp += "<p style='text-align:right;' class='p_text11px_gray'></p>"; 
						bbs1temp += "</li>"; */
					}
					$('#comevent').append(bbs1temp);
				}
			
		},
			error:function(data,sataus,err) {
					
					alert("error");
			}
		});	
	
}

function getSurvey() {
	
	$("#surcontent").html("");

	var addHtml = "";
	$.ajax({
		type:'post',
		timeout: 5000,
		async:false,
		dataType:'json',
		url:'/luxor_collaboration/sur/surSurvey.do?method=progressSurveyList',
		success:function(data) {
			if(data.surSurveyList.length == 0 ){
				addHtml += "<p>" + "진행중인 설문이 없습니다." + "</p>";				
				$('#surcontent').append(addHtml);
			}else{			
				for(var i = 0; i < data.surSurveyList.length; i++){	
					if(i >=1){
						continue;
					}
					addHtml += "<p style='cursor:pointer;' onclick='goSurMain()' >"+ data.surSurveyList[i].title+ "</p>";		
				}
				$('#surcontent').append(addHtml);
			};		
		
		},
		error:function(data,sataus,err) {
				
				alert("error");
		}
	}); 
	
	
	
}

function goByuckSanBlog(){
	document.location.href = '/luxor_collaboration/blg/BlgHome.do?method=blgHome';
}

function scheduleInsert(){
	/* window.open('/luxor_collaboration/sch/schSchedule.do?method=createViewDetilSchedule',"popup","600","300"); */
	luxor.popup('/luxor_collaboration/sch/schSchedule.do?method=createViewDetilSchedule',{width:900, height:600, scrollbars:"yes"});
}

function goLatestList(){
	document.location.href = '/ep/page/index.do?pageId=N201511200948533991081';
}
function goApprovalDirection(docId, publishId){
	var lobCode = '';
	var linkUrl = "";
	
	if(approvalTabVal == 0){ // 대기함
		lobCode = 'LOB003';
		linkUrl = "http://211.168.82.16:8001/ep/app/approval/selectAppDoc.do?docId="+docId+"&lobCode="+lobCode;
	}else if(approvalTabVal == 1){ // 진행함
		lobCode = 'LOB004';
		linkUrl = "http://211.168.82.16:8001/ep/app/approval/selectAppDoc.do?docId="+docId+"&lobCode="+lobCode;
	} else if(approvalTabVal == 2){ // 완료함
		lobCode = 'LOB010';
		linkUrl = "http://211.168.82.16:8001/ep/app/approval/selectAppDoc.do?docId="+docId+"&lobCode="+lobCode;
	} else if(approvalTabVal == 3){ // 공람
		lobCode = 'LOB031';
		linkUrl = 'http://211.168.82.16:8001/ep/app/approval/selectAppDoc.do?publishId='+publishId+'&docId='+docId+'&lobCode='+lobCode;
		
	//publishId=B79A3F4A767E094815152EF29B2FFFF8&docId=APPB9504F29767E094815152EF29B2FFFF8&lobCode=LOB031
	}  
	
	
	
	luxor.popup(linkUrl,{width:800,height:768,scrollbars:"yes"}); 
	
}

function goPostDirection(postId,boardId,pageId)
{
	
	/* document.location.href = '/ep/page/index.do?pageId='+pageId; */
	/* targetFrameLoad('/luxor_collaboration/bbs/bbsPostCommon.do?method=vew&id.postId='+postId+'&id.boardId='+boardId, pageId, 'body', 'header'); */  	
	  
	 luxor.popup('/luxor_collaboration/bbs/bbsPostCommon.do?method=vew&id.boardId='+boardId +'&id.postId='+ postId + '&popup=1',{width:1000,height:800,scrollbars:"yes"}); 
}

function goSchDirection(postId,boardId,pageId){
	
	/* luxor.popup('/ep/page/index.do?pageId='+pageId,{width:1380,height:800,scrollbars:"yes"}); */
	
	luxor.popup('/luxor_collaboration/sch/schSchedule.do?method=mainSchedule',{width:1380,height:800,scrollbars:"yes"});
	
	
	/* document.location.href = '/ep/page/index.do?pageId='+pageId; */
}

function goWorkInViewPage(receiveDataType, target,pageId){
	if(receiveDataType == '0'){
		document.location.href = '/ep/page/index.do?pageId='+ $.trim(pageId) + '&wrkWorkList|wrkid='+ target;
	}else{
		document.location.href = '/ep/page/index.do?pageId='+ $.trim(pageId) + '&wrkWorkRlist|wrkid='+ target;
	}
}


function sbgoSpace(target){
	
	document.location.href = '/luxor_collaboration/tsp/tspSpace.do?method=spaceHomeView&id.spaceId='+target;

}

function userInfoChange(){
	
	window.open('/ep/user/getChangeUserInformationView.do',"popup","300","200");
	
}

function goScheduleMain(){
	
	/* document.location.href = '/ep/page/index.do?pageId=N201409260925254761061'; */
	luxor.popup('/luxor_collaboration/sch/schSchedule.do?method=mainSchedule',{width:1380,height:800,scrollbars:"yes"});
	
	
}

function goWorkIn(){
	var workInPageId = "";

	if(workinStatus == 0){
		workInPageId = "N201511201522240011072";
	}else{	
		workInPageId = "N201511201522396741074";
	}
	document.location.href = '/ep/page/index.do?pageId='+workInPageId;
}

function goApprovalMain(){
	document.location.href = '/ep/page/index.do?pageId=N201511211543189731838';
}

function gocommonEvent(){
	document.location.href = '/ep/page/index.do?pageId=N201511200951003971110';
}
function goSurMain(){
	document.location.href = '/ep/page/index.do?pageId=N201409260925408951066';
}
function sideMenuShow(swtc){
	if(swtc == 'On'){
		//$('#sideMenuOff').hide("slide", { direction: "left" }, 500);
		$('#sideMenuOn').show("slide", { direction: "right" }, 500);
	}else{
		$('#sideMenuOn').hide("slide", { direction: "right" }, 500);
		//$('#sideMenuOff').show("slide", { direction: "left" }, 500);
	}
	

}
</script>

</head>
<body>
<div class="quick_close" id="sideMenuOn" style="display:none;z-index:101;">
  <table width="100" border="0" cellspacing="0" cellpadding="0" >
    <tr>
      <td width="39" onclick="sideMenuShow('Off');" style="cursor:pointer;"><img src="/ep/luxor/ref/img/btn_layer_open.jpg" alt="열기버튼"/></td>
      <td class="quick_close_td"><img src="/ep/luxor/ref/img/layer_logo_01.jpg" alt="벽산로고" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td class="quick_close_td"><img src="/ep/luxor/ref/img/layer_quick_01.jpg" alt="퀵링크"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td  class="quick_close_td"><img src="/ep/luxor/ref/img/layer_mymenu_01.jpg" alt="마이메뉴"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td height="670" valign="top"  class="quick_close_td" style=" border-bottom:1px solid #dedede;"><img src="/ep/luxor/ref/img/layer_top_01.jpg" alt="top"/></td>
    </tr>
  </table>
</div>
<div class="quick_close" id="sideMenuOff" onclick="sideMenuShow('On');" style="cursor:pointer;z-index:100;">
  <table width="40" border="0" cellspacing="0" cellpadding="0" >
    <tr>
      <td width="39"><img src="/ep/luxor/ref/img/btn_layer_open.jpg" alt="열기버튼"/></td>
    </tr>
  </table>
</div>


 <div id="wrap" class="luxor-main">
        <!-- 탑메뉴 
                  <div id="header" type="zone" class="luxor-top">
                       <div class="toplogo">
                           <span style="float:left; margin-top:10px"><img src="/ep/luxor/ref/img/logo.jpg" alt="벽산 BICS"/></span>
                           <span style="float:right; margin-top:5px">
                           <table border="0" cellspacing="2" cellpadding="0">
                              <tr>
                                <td><img src="/ep/luxor/ref/img/user_img.jpg" style="width:31px; height:31px; border-radius:15px;" alt="user" class="topphoto" id="viewimage1"/></td>
                                <td class="topname"><%=userProfile.getUserName()%></td>
                                <td><img src="/ep/luxor/ref/img/user_arrow.jpg"/></td>
                                <td width="40" align="right"><img src="/ep/luxor/ref/img/top_user_icon01.png"/></td>
                                <td width="40" align="right"><img src="/ep/luxor/ref/img/top_user_icon02.png"/></td>
                                <td width="40" align="right"><img src="/ep/luxor/ref/img/top_user_icon03.png" style="cursor:pointer;"  id="btnLOGOUT"/></td>
                              </tr>
                            </table>
                         </span>
                       </div>
                   <div style="clear:both">
                     <table width="1350" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px">
                          <tr>
                            <td width="300" align="left" class="topblog"><img src="/ep/luxor/ref/img/icon_top01.jpg" alt="이미지"/> <a href="#">블로그</a> &nbsp;&nbsp;ㅣ&nbsp;&nbsp; <img src="/ep/luxor/ref/img/icon_top02.jpg" alt="이미지"/>커뮤니티</td>
                            <td align="center">
                              <table width="500" border="0" cellspacing="0" cellpadding="0" class="topmenu">
                                  <tr>
                                    <td>게시</td>
                                    <td>메일</td>
                                    <td>전자결재</td>
                                    <td>문서관리</td>
                                    <td><a href="/luxor_collaboration/wrk/wrkWork.do?method=lst">협업</a></td>
                                  </tr>
                                </table>
                           </td>
                            <td width="300" align="right">
                            <table width="100" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td> <input type="text" name="textfield" id="textfield" class="topinput" placeholder="임직원찾기"/></td>
                                <td><img src="/ep/luxor/ref/img/btn_search_top.jpg" alt="search"/></td>
                              </tr>
                            </table></td>
                       </tr>
                     </table>
                    </div>
                  </div>
                  탑메뉴 //-->
                   
                   <!-- 바디 -->
                   <div id="body" class="luxor-body" >
                       <div class="luxor-inner-body" >
                         
                            <!-- 레프트 단 -->
                            <div id="left" type="zone" class="luxor-leftmain">
                           
                           <!-- 마이데스크 -->
                              <div class="div_box_left">
                                    <div class="tit_box_left"><span class="tit">마이데스크</span><span class="more"><img src="/ep/luxor/ref/img/left_icon01.png" alt="새로고침"/></span></div>
                                            <div class="box_list_ptv_left">
                                             <div class="mydesk_pic"><img src="/ep/luxor/ref/img/my_img.jpg" style="width:63px; height:80px;" id="viewimage2" /></div>
                                             <div class="mydesk_info">
                                                 <p class="p01"><%=userProfile.getUserName()%></p>
                                                 <p class="p02"> (주) 벽산 <%=userProfile.getDeptName()%></p>
                                                 <p class="p03"><span><a href="#" onClick = "userInfoChange()">정보변경</a></span> <span style="cursor:pointer;" id="btnLOGOUT">로그아웃</span></p>
                                             </div>
                                             <div class="mydesk_logintime">로그인시간 : 2015.11.05 11/11 11:56</div>
                                  </div>
                                  			 <div class="box_list_ptv_leftw">
                                             <ul>
                                             <li class="list01"><span class="ptv_spanleft">전자메일</span><span class="ptv_spanright" id="mailCount">0</span></li>
                                             <li class="list02"><span class="ptv_spanleft">결재대기</span><span class="ptv_spanright" id="approvalWaitCount" style="cursor:pointer;" onClick="goApprovalList(0)"></span></li>
                                             <li class="list03"><span class="ptv_spanleft">결재진행</span><span class="ptv_spanright" id="approvalProcessCount" style="cursor:pointer;" onClick="goApprovalList(1)"></span></li>
                                             <li class="list04"><span class="ptv_spanleft">공람함</span><span class="ptv_spanright" id="approvalDisplayCount" style="cursor:pointer;" onClick="goApprovalList(3)"></span></li>
                                             </ul>
                                             </div>
                                </div>
                                <!-- 마이데스크 //-->
                            
                                <!-- 일정관리 -->
                              <div class="div_box_left">
                                    <div class="tit_box_left"><span class="tit">일정관리</span><span class="more"><img onClick="goScheduleMain()" style="cursor:pointer;" src="/ep/luxor/ref/img/left_icon02.png" alt="더보기" /></span></div>
                                         <div class="box_list_ptv_leftbtm">
                                         
                                         <!-- 달력 -->
                                         <div style="margin:5px 0" id="datepicker2">
                                         <!-- <table border="0" align="center" cellpadding="0" cellspacing="2" style="margin-bottom:5px">
                                          <tr>
                                            <td width="15" align="left"><img src="/ep/luxor/ref/img/no_back.gif" alt="이전" /></td>
                                            <td width="27"><img src="/ep/luxor/ref/img/no_year_2.gif" alt="2" /></td>
                                            <td width="27"><img src="/ep/luxor/ref/img/no_year_0.gif" alt="0" /></td>
                                            <td width="27"><img src="/ep/luxor/ref/img/no_year_1.gif" alt="1" /></td>
                                            <td width="27"><img src="/ep/luxor/ref/img/no_year_5.gif" alt="5" /></td>
                                            <td>&nbsp;</td>
                                            <td width="29"><img src="/ep/luxor/ref/img/no_month_1.gif" alt="1" /></td>
                                            <td width="29"><img src="/ep/luxor/ref/img/no_month_1.gif" alt="1" /></td>
                                            <td width="15" align="right"><img src="/ep/luxor/ref/img/no_next.gif" alt="다음" /></td>
                                          </tr>
                                        </table>
                                         <table width="85%" border="0" align="center" cellpadding="0" cellspacing="1" class="calendar01">
                                           <tr>
                                             <th>일</th>
                                             <th>월</th>
                                             <th>화</th>
                                             <th>수</th>
                                             <th>목</th>
                                             <th>금</th>
                                             <th>토</th>
                                           </tr>
                                           <tr>
                                             <td>&nbsp;</td>
                                             <td>&nbsp;</td>
                                             <td>&nbsp;</td>
                                             <td>&nbsp;</td>
                                             <td>&nbsp;</td>
                                             <td>1</td>
                                             <td>2</td>
                                           </tr>
                                           <tr>
                                             <td>3</td>
                                             <td>4</td>
                                             <td>5</td>
                                             <td>6</td>
                                             <td>7</td>
                                             <td>8</td>
                                             <td>9</td>
                                           </tr>
                                           <tr>
                                             <td>10</td>
                                             <td>11</td>
                                             <td>12</td>
                                             <td>13</td>
                                             <td>14</td>
                                             <td class="calendar01today">15</td>
                                             <td>16</td>
                                           </tr>
                                           <tr>
                                             <td>17</td>
                                             <td>18</td>
                                             <td>19</td>
                                             <td>20</td>
                                             <td>21</td>
                                             <td>22</td>
                                             <td>23</td>
                                           </tr>
                                           <tr>
                                             <td><span class="calendar01sp">24</span></td>
                                             <td>25</td>
                                             <td>26</td>
                                             <td>27</td>
                                             <td>28</td>
                                             <td>29</td>
                                             <td>30</td>
                                           </tr>
                                         </table> -->
                                         </div>
                                         <!-- 달력 //-->
                                             
                                             <!-- 일정 -->
                                             <div class="box_list_ptv_leftwbtm">
                                             <div class="left_day01" id="schboxtext1">
                                               <!--   <ul>
                                                   <li><a href="#">9월2주차 주간업무 보고를 넣어요</a></li>
                                                   <li>9월2주차 주간업무 보고를 넣어요</li>
                                                   <li>9월2주차 주간업무 보고를 넣어요</li>
                                                 </ul> -->
                                             </div>
                                             <div class="left_day02" id="schboxtext2">
                                                <!--  <ul>
                                                   <li>9월2주차 주간업무 보고를 넣어요</li>
                                                   <li>9월2주차 주간업무 보고를 넣어요</li>
                                                   <li>9월2주차 주간업무 보고를 넣어요</li>
                                                 </ul> -->
                                              </div>
                                              <div class="left_day03" id="schboxtext3">
                                                 <!--  <ul>
                                                   <li>9월2주차 주간업무 보고를 넣어요</li>
                                                   <li>9월2주차 주간업무 보고를 넣어요</li>
                                                   <li>9월2주차 주간업무 보고를 넣어요</li>
                                                 </ul> -->
                                              </div>
                                              <div class="left_day_btn">
           									  <span style="float:left"><img src="/ep/luxor/ref/img/btn_day.jpg" style="cursor:pointer;" id="schInsert" onclick="scheduleInsert();" alt="일정등록"/></span>
                                              <!-- <span style="float:right"><img src="/ep/luxor/ref/img/btn_day_before.jpg" alt="이전"/> <img src="/ep/luxor/ref/img/btn_day_next.jpg" alt="다음"/></span> -->
                                              </div>
                                             
                                             </div>
                                              <!-- 일정 //-->

                                             </div>
                                          </div>
                                <!-- 일정관리 //-->
                            
                            
                             
                            </div>
                            <!-- 레프트 단 //-->
                            
                            
                            <!-- 컨텐츠 단 -->
                            <div id="content" type="zone" class="luxor-content">
                            <!-- 이미지 -->
                            <div class="div_con_mainimg"><img src="/ep/luxor/ref/img/main_img.jpg" alt="KNOWLEDGE INTENSIVE"/> 
                             </div>
                            <!-- 이미지 //-->
                            
                            <!-- 전사공지 -->
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                               <tr>
                                            <td height="26" class="tabmenu01">
                                            	<ul>
                                                    <li class="tab_on01" style="cursor:pointer;" id="tab00" onClick="bbsTabClick(1)">전사공지</li>
                                                    <li class="tab_off01" style="cursor:pointer;" id="tab01" onClick="bbsTabClick(2)">부서공지</li>
                                                    <li class="tab_off02" style="cursor:pointer;" id="tab02" onClick="bbsTabClick(3)">인사발령</li>
                                                    <li class="tab_off03" style="cursor:pointer;" id="tab03" onClick="bbsTabClick(4)">경영혁신</li>
                                                    <li class="tab_off04" style="cursor:pointer;" id="tab04" onClick="bbsTabClick(5)">e-벽산</li>
                                                </ul>
                                            </td>
                                            <td class="tab_bg"><img style="cursor:pointer;" src="/ep/luxor/ref/img/pluse.png" id="bbsmore" alt="더보기"/></td>
                                      </tr>
                              </table>
                                    
                                    <div class="box_list_con" style="height:141px;">
                                      <table border="0" cellspacing="0" cellpadding="0" class="table_list" id="boardlist">
                                       <!--  <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 <img src="/ep/luxor/ref/img/icon_new.jpg" alt="new"/></td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">김태희</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr>
                                        <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 <img src="/ep/luxor/ref/img/icon_new.jpg" alt="new"/></td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">홈길동</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr>
                                        <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 </td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">춘향이</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr>
                                        <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 </td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">매염방</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr>
                                        <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 </td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">강감찬</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr> -->
                                      </table>
                                    </div>
                            <!-- 전사공지 //-->
                            
                            <!-- 대기함 -->
                             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                               <tr>
                                            <td height="26" class="tabmenu02">
                                             <ul>
                                                    <li class="tab_on01" id="tab05" onClick="approvalTabClick(1)">대기함</li>
                                                    <li class="tab_off01" id="tab06" onClick="approvalTabClick(2)">진행함</li>
                                                    <li class="tab_off02" id="tab07" onClick="approvalTabClick(3)">완료함</li>
                                                    <li class="tab_off03" id="tab08" onClick="approvalTabClick(4)">공람함</li>
                                                </ul>
                                                </td>
                                            <td class="tab_bg"><img src="/ep/luxor/ref/img/pluse.png" style="cursor:pointer;" alt="더보기" id="approvalMore"/></td>
                                      </tr>
                              </table>
                                    
                             <div class="box_list_con" style="height:141px;">
                               <table border="0" cellspacing="0" cellpadding="0" class="table_list" id="approvalList">
                                  <!-- <tr>
                                   <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 <img src="/ep/luxor/ref/img/icon_new.jpg" alt="new"/></td>
                                   <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                   <td width="90" class="con_list_day">김태희</td>
                                   <td width="90" class="con_list_day">2015-09-07</td>
                                 </tr>
                                 <tr>
                                   <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 <img src="/ep/luxor/ref/img/icon_new.jpg" alt="new"/></td>
                                   <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                   <td width="90" class="con_list_day">홈길동</td>
                                   <td width="90" class="con_list_day">2015-09-07</td>
                                 </tr>
                                 <tr>
                                   <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 </td>
                                   <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                   <td width="90" class="con_list_day">춘향이</td>
                                   <td width="90" class="con_list_day">2015-09-07</td>
                                 </tr>
                                 <tr>
                                   <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 </td>
                                   <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                   <td width="90" class="con_list_day">매염방</td>
                                   <td width="90" class="con_list_day">2015-09-07</td>
                                 </tr>
                                 <tr>
                                   <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 </td>
                                   <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                   <td width="90" class="con_list_day">강감찬</td>
                                   <td width="90" class="con_list_day">2015-09-07</td>
                                 </tr>  -->
                               </table>
                             </div>
                            <!-- 대기함 //-->
                            
                            <!-- 최근게시물 -->
                            <div class="div_box">
                                    <div class="tit_box"><span class="tit">최근게시물</span><span class="more"><img src="/ep/luxor/ref/img/pluse.png" alt="더보기" onClick="goLatestList()" style="cursor:pointer; margin-top:-17px;"/></span></div>
                                    <div class="box_list">
                                      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:15px; border-bottom:1px dotted #666" id="latestListFirst">
                                          <tr>
                                            <td width="140" align="left"><img src="/ep/luxor/ref/img/blog_img.jpg" width="120" height="69" /></td>
                                            <td>
                                            <p class="p_text16px_blue"><a href="#">벽산, 컬러 베이스패널 매출 '쑥쑥'</a><span class="p_text11px_gray" style="float:right">2015-09-17</span></p>
                                            <p>편리한 시공 아름다운 공간벽산 이지톤 블로그입니다.편리한 시공 아름다운 공간벽산 이지톤 블로그입니다.편리한 시공 아름다운 공간벽산 이지톤 블로그입니다.</p>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td height="10px" colspan="2" align="left"></td>
                                          </tr>
                                       </table>
                                      <table border="0" cellspacing="0" cellpadding="0" class="table_list" id="latestboardlist">
                                       <!--  <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 <img src="/ep/luxor/ref/img/icon_new.jpg" alt="new"/></td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr> -->
                                        <!-- <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 <img src="/ep/luxor/ref/img/icon_new.jpg" alt="new"/></td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr>
                                        <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 </td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr>
                                        <tr>
                                          <td class="con_list_tit">2015년도 3분기 학자금 신청의 건 </td>
                                          <td width="120" class="con_list_day">[벽산] 경영정보팀</td>
                                          <td width="90" class="con_list_day">2015-09-07</td>
                                        </tr> -->
                                      </table>
                              </div>
                              </div>
                            <!-- 최근게시물 //-->
                            
                         </div>
                            <!-- 컨텐츠 단 //-->
                            
                            
                            <!-- 오른쪽 -->
                            <div id="right" type="zone" class="luxor-right">
                                <!-- 알림 -->
                                <div class="div_box" style="height:403px;">
                                    <ul class="div_box_count">
                                      <!--   <li class="counton01" onClick="rightTabClick(01)"><p>1234</p></li> -->
                                        <li class="countoff02" onClick="rightTabClick(2)"></li>
                                        <li class="countoff03" onClick="rightTabClick(3)"></li>
                                        <li class="countoff04" onClick="rightTabClick(4)"></li>
                                    </ul>
                                    <!-- <ul class="div_box_count">
                                        <li class="countoff02"></li>
                                        <li class="countoff03"></li>
                                        <li class="counton04"><p>1234</p></li>
                                    </ul> -->
                                    <div class="tit_box_work"><span class="tit"><div style="margin-top:10px;"><p style="float:left;" style="cursor:pointer;" id="workinlist" onClick="getWorkinData(0)"><strong>업무지시함</strong></p> <p style="float:left;">ㅣ</p><p style="float:left;" style="cursor:pointer;" id="workinrlist" onClick="getWorkinData(1)"> 업무접수함</p></div></span><span class="more"><img src="/ep/luxor/ref/img/pluse.png" onClick="goWorkIn()" style="cursor:pointer;" alt="더보기"/></span></div>
                                    <div class="box_list" style="height:263px;">
                                      <ul title="설문조사"  class="rightNoticeBox" id="rightNoticeBox"> 
                                         <!-- <li class="first">
                                         <p><a href="#">전사 제안활성화 현황 집계현황 보고입니다.</a></p>
                                         <p class="p_text11px_gray">김해성 ㅣ 2015.09.05 11:30</p>
                                         </li>
                                         <li>
                                         <p>전사 제안활성화 현황 집계현황 보고입니다.</p>
                                         <p class="p_text11px_gray">김해성 ㅣ 2015.09.05 11:30</p>
                                         </li>
                                          <li>
                                         <p>전사 제안활성화 현황 집계현황 보고입니다.</p>
                                         <p class="p_text11px_gray">김해성 ㅣ 2015.09.05 11:30</p>
                                         </li>
                                          <li>
                                         <p>전사 제안활성화 현황 집계현황 보고입니다.</p>
                                         <p class="p_text11px_gray">김해성 ㅣ 2015.09.05 11:30</p>
                                         </li>
                                          <li>
                                         <p>전사 제안활성화 현황 집계현황 보고입니다.</p>
                                         <p class="p_text11px_gray">김해성 ㅣ 2015.09.05 11:30</p>
                                         </li> -->
                                        </ul>
                                    </div>
                                </div>
                                <!-- 알림 //-->
                               <!-- 벽산블로그 -->
                                <div class="div_box">
                                    <div class="tit_box"><span class="tit">벽산블로그</span><span class="more"><img onClick="goByuckSanBlog()" src="/ep/luxor/ref/img/pluse.png" style="cursor:pointer; margin-top:-17px;" alt="더보기"/></span></div>
                                    <div class="box_list" style="height:162px;">
                                      <ul title="벽산블로그" id="byucksanBlog"> 
                                       <!--   <li class="first">
                                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td><img src="/ep/luxor/ref/img/blog_img.jpg"/></td>
                                            <td>
                                                <p class="p_text12px_blue">벽산 이지톤 블로그입니다 <span class="p_text12px_red">[132]</span></p>
                                            <p class="p_text11px_gray">홍길동 ㅣ 15.09.01</p>
                                            <p class="p_text12px_gray">편리한 시공 아름다운 공간
                                            벽산 이지톤 블로그입니다.</p></td>
                                          </tr>
                                        </table>
                                        </li>
                                         <li >
                                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td><img src="/ep/luxor/ref/img/blog_img.jpg"/></td>
                                            <td>
                                            <p class="p_text12px_blue">벽산 이지톤 블로그입니다 <span class="p_text12px_red">[132]</span></p>
                                            <p class="p_text11px_gray">홍길동 ㅣ 15.09.01</p>
                                            <p class="p_text12px_gray">편리한 시공 아름다운 공간
                                            벽산 이지톤 블로그입니다.</p></td>
                                          </tr>
                                        </table>
                                        </li> -->
                                        </ul>
                                        <!-- <div class="boxclose"><img src="/ep/luxor/ref/img/btn_blog_before.jpg" alt="이전"/><img src="/ep/luxor/ref/img/btn_blog_next.jpg" alt="다음" /></div> -->
                                    </div>
                                </div>
                                <!-- 벽산블로그 //-->
                                
                                 <!-- 임직원동정 -->
                                <div class="div_box">
                                    <div class="tit_box"><span class="tit">임직원동정</span><span class="more"><img src="/ep/luxor/ref/img/pluse.png" onclick="gocommonEvent()" style="cursor:pointer; margin-top:-17px;" alt="더보기"/></span></div>
                                    <div class="box_list_ptv" style="height:60px;">
                                      <ul title="임직원동정"  id="comevent"> 
                                      	<!-- <li class="first"><img src="/ep/luxor/ref/img/icon_happy.jpg" alt="경사"/>경영정보팀 김채희 대리 결혼</li>
                                        <li><img src="/ep/luxor/ref/img/icon_happy.jpg" alt="경사"/>경영정보팀 김채희 대리 결혼</li>
                                        <li><img src="/ep/luxor/ref/img/icon_happy.jpg" alt="경사"/>경영정보팀 김채희 대리 결혼</li> -->
                                       </ul>
                                    </div>
                                </div>
                                <!-- 임직원동정 //-->
                                
                                <!-- 설문조사 -->
                                <div class="div_box">
                                    <div class="tit_box"><span class="tit">설문조사</span><span class="more"><img src="/ep/luxor/ref/img/pluse.png" onclick="goSurMain()" style="cursor:pointer; margin-top:-17px;" alt="더보기"/></span></div>
                                    <div class="box_list">
                                     <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:3px">
                                          <tr>
                                            <td style="width:30px;"><img src="/ep/luxor/ref/img/icon_sulmun.jpg"/></td>
                                            <td>
                                            <p id="surcontent" style="margin-left:15px;"></p>
                                            </td>
                                          </tr>
                                        </table>
                                        <div class="boxclose"><img style="cursor:pointer;" onclick="goSurMain()" src="/ep/luxor/ref/img/btn_sulmun.jpg" alt="참여하기"/></div>
                                    </div>
                                </div>
                                <!-- 설문조사 //-->

                            </div>
                            <!-- 오른쪽 //-->
                            
                   
                           
                       
                        </div>
                    </div>
                   
                    <!-- 푸터 -->
                     <div id="footer" type="zone" class="luxor-footer">
                       <div style="width:1350px; margin:10px auto 0 auto">
                       <span style="float:left"><img src="/ep/luxor/ref/img/footer01.jpg" width="391" height="32" /></span>
                       <span style="float:right"><img src="/ep/luxor/ref/img/footer02.jpg" width="209" height="29" /></span></div>
                     </div>
                    <!-- 푸터 //-->
<iframe src='/ep' style="display:none;">
</iframe>
</div>


</body>

<script type="text/javascript">
		
	 	getbyucksanBlogData(); 
		getBbsData('getBoardList');
		commonEvent(); 		
		getSurvey();
		getlastestList();
		getSchData1();
		getSchData2();
		getSchData3();
		//getWorkinData(0);
		getApprovalList(0);
		getApprovalProcessCount();
		//getApprovalWaitCount();
		getApprovalDisplayCount();
		getApprovalEndCount(); 
		
		
		
</script>
</html>