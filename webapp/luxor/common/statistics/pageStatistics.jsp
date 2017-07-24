<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*"%>
<%@ page import="com.sds.acube.luxor.framework.config.*"%>
<%@ page import="java.util.Date"%>
<%

	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	String todayDate = CommonUtil.getNow();
	
	String now_year_item  = todayDate.substring(0,4);
	String now_month_item = todayDate.substring(4,6);
	String now_date_item  = todayDate.substring(6,8);
	String datePattern = LuxorConfig.getEnvString(request, "DATE_PATTERN_FOR_DATEPICKER");
	
%>

<html lang="ko">
<head>
<title><spring:message code="statistics.label.8" text="페이지 사용 현황" /></title>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript">
	var now_year = "<%=now_year_item%>";
	var now_month = "<%=now_month_item%>";
	var now_date = "<%=now_date_item%>";
	var datePattern = "<%=datePattern%>";
	
	$.datepicker.setDefaults({
	    dateFormat: datePattern,
	    buttonImageOnly: true,
	    buttonText: "달력",
	    buttonImage: "/ep/luxor/ref/image/admin/icon_cal.gif"
	});
	
	$(document).ready(function() {
    	$("#datepicker1").val(now_year+"-"+now_month+"-"+now_date); 
    	$("#datepicker2").val(now_year+"-"+now_month+"-"+now_date); 
    	
	    $("#datepicker").datepicker({
	        inline: true,
	        defaultDate: new Date(now_year, now_month - 1, now_date),
	        onSelect: function(date) { 
	        	$("#datepicker1").val(date); 
	        	$("#datepicker2").val(date); 
	        }
	    });
	    $("#datepicker1").datepicker({
	        defaultDate: new Date(now_year, now_month - 1, now_date),
	        showOn: "both", 
	        showAnim: "blind", 
	        showOptions: {direction: 'horizontal'},
	        duration: 200
	    });
	    $("#datepicker2").datepicker({
	        defaultDate: new Date(now_year, now_month - 1, now_date),
	        showOn: "both",
	        showAnim: "blind", // blind, clip, drop, explode, fold, puff, slide, scale, size, pulsate, bounce, highlight, shake, transfer
	        showOptions: {direction: 'horizontal'},
	        duration: 200
	    });
	    
		var url = "/ep/statistics/getPageStatisticsList.do";
		var param = "";
		var fromDate = now_year+"-"+now_month+"-"+now_date + " 00:00:01";
		var toDate = now_year+"-"+now_month+"-"+now_date + " 23:59:59";
		param += "fromDate="+fromDate;
		param += "&toDate="+toDate;

		$('#pageStatisticsLst').load(url,param);

		// 목록내보내기 클릭시
		$('#makeFile').click(function() {
			var searchTxt = $("#searchTxt").val();
			var searchType = $("#searchType").val();
			var fromDate = $("#datepicker1").val() + " 00:00:01";
			var toDate = $("#datepicker2").val() + " 23:59:59";
			var param = "/ep/statistics/getPageStatisticsMkFile.do";
			
			if(searchTxt=="") {
				searchType = "";
			}
			
			param += "?fromDate="+fromDate;
			param += "&toDate="+toDate;
			param += "&searchType="+searchType;
			
			param += "&arg1="+encodeURIComponent('<spring:message code="statistics.label.10" text="페이지ID" />');
			param += "&arg2="+encodeURIComponent('<spring:message code="portal.label.58" text="페이지명" />');
			param += "&arg3="+encodeURIComponent('<spring:message code="statistics.label.12" text="호출UserID" />');
			param += "&arg4="+encodeURIComponent('<spring:message code="user.label.8" text="성명" />');
			param += "&arg5="+encodeURIComponent('<spring:message code="statistics.label.5" text="로그인IP" />');
			param += "&arg6="+encodeURIComponent('<spring:message code="statistics.label.13" text="호출일시" />');
			
			param += "&searchTxt="+encodeURIComponent(searchTxt);
			$('#hiddenMkFileFrame').attr('src',param);
		});

		// 검색 클릭시
		$('#goSearch').click(function() {
			goSearch();
		});

		// 체크박스 전체선택
		$('#checkAll').live('click', function() {
			selectAll();
		});

		$('#searchTxt').keypress(function(event) {
			if(event.keyCode==13){
				goSearch();
			}
		});

		// 전체삭제 클릭시
		$('#deleteAll').click(function() {
			var userStatus = $("input[name='userStatus']:checked").val();
			var fromDate = $("#datepicker1").val() + " 00:00:01";
			var toDate = $("#datepicker2").val() + " 23:59:59";
			var searchTxt = $("#searchTxt").val();
			var searchType = $("#searchType").val();
		
			if(searchTxt=="")  {
				searchType = "";
			}

			var param = "";
			param += "fromDate="+fromDate;
			param += "&toDate="+toDate;
			param += "&searchType="+searchType;
			param += "&searchTxt="+encodeURIComponent(searchTxt);

			if(confirm('<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?"/>')) {
				callJson("statisticsController", "deletePageStatisticsAll", param, function(data) {
					alert('<spring:message code="portal.alert.msg.8" text="삭제되었습니다."/>');
					top.location.reload();
				});
			}
		});
	
		// 선택삭제 클릭시
		$('#selectedDelete').click(function() {
			var statisticsId = "";
			var param = "";
			var sessionId = "";
			var fromDate = $("#datepicker1").val() + " 00:00:01";
			var toDate = $("#datepicker2").val() + " 23:59:59";
			var checkCnt = $("input:checkbox:checked").length;
			var url = "/ep/statistics/getLoginHistoryList.do";
			
			if(checkCnt == 0) {	
				alert('<spring:message code="portal.alert.msg.24" text="선택을 해주세요." />');
				return;
			}
			
			if(confirm('<spring:message code="portal.alert.msg.12" text="삭제하시겠습니까?"/>')) {
				$(".box:checked").each(function() {
					statisticsId = $(this).val();
					param += "&statisticsIds="+statisticsId;
				});

				param = param.substring(1);
				callJson("statisticsController", "deletePageStatistics", param, function(data) {			
					alert('<spring:message code="portal.alert.msg.8" text="삭제되었습니다." />');
					top.location.reload();
				});
			}
		});
	});

	/**
	 * 검색
	 */
	function goSearch() {
		var searchTxt = $("#searchTxt").val();
		var searchType = $("#searchType").val();
		var fromDate = $("#datepicker1").val() + " 00:00:01";
		var toDate = $("#datepicker2").val() + " 23:59:59";
		var url = "/ep/statistics/getPageStatisticsList.do";
		var param = "";
		
		if(searchTxt=="") {
			searchType = "";
		}
		
		param += "fromDate="+fromDate;
		param += "&toDate="+toDate;
		param += "&searchType="+searchType;
		param += "&searchTxt="+encodeURIComponent(searchTxt);
		$('#pageStatisticsLst').load(url,param);
	}
	
	/**
	 * 체크박스 전체선택
	 */
	function selectAll() {
		var checked = $("#checkAll").attr("checked");
		$(".box").each(function(){
			var subChecked = $(this).attr("checked");
		    if (subChecked != checked)
		    (this.checked == true) ? this.checked = false : this.checked = true;
		});
	} 

	// 페이징
    function changePage(p) {
		var searchTxt = $("#searchTxt").val();
		var searchType = $("#searchType").val();
		var fromDate = $("#datepicker1").val() + " 00:00:01";
		var toDate = $("#datepicker2").val() + " 23:59:59";
		
		if(searchTxt=="") {
			searchType = "";
		}

		var url = "/ep/statistics/getPageStatisticsList.do";
		var param = "";
		
		param += "fromDate="+fromDate;
		param += "&toDate="+toDate;
		param += "&searchType="+searchType;
		param += "&searchTxt="+encodeURIComponent(searchTxt);
		param += "&cPage="+p;
		
		$('#pageStatisticsLst').load(url,param);
	}
	
</script>	
</head>

<body><div id="eee"></div><div id="datepicker" class="datepicker_div" style="display: none;"/></div>
	<!-- admin-content-width w1200 margin15-l -->
	<div class="admin-content-width w1200 ml15" name="zone">
		<!-- subtitle -->
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="statistics.label.8" text="페이지 사용 현황" /></span>
			<!-- button -->
			<div class="aright">
				<span class="main-btn"><a href="#" id="makeFile"><spring:message code="portal.btn.label.33" text="목록내보내기" /></a></span>
				<span class="main-btn"><a href="#" id="deleteAll"><spring:message code="portal.btn.label.31" text="전체삭제" /></a></span>
				<span class="main-btn"><a href="#" id="selectedDelete"><spring:message code="portal.btn.label.32" text="선택삭제" /></a></span>
			</div>
			<!-- //button -->
		</div>
		<!-- //subtitle -->

		<!-- table_write -->
		<table class="table-search">
		<thead>
			<tr>
				<th width="90"><spring:message code="portal.label.21" text="검색어" /></th>
				<td colspan="3">
   					<select id=searchType style="width:100">
					    <option value='pageName'><spring:message code="portal.label.58" text="페이지명" /></option>
     					<option value='accessUserId'><spring:message code="statistics.label.12" text="호출UserID" /></option>
      					<option value='accessUserName'><spring:message code="user.label.8" text="성명" /></option>
						<option value='loginIp'><spring:message code="statistics.label.5" text="로그인IP" /></option>
   					</select>
					<input type="text" class="input-d" id="searchTxt" style="WIDTH: 70%;" />
				</td>
				<td width="62" rowspan="2">
					<span class="smain-btn">
						<a href="#" id="goSearch"><span class="btnicon-01"></span><spring:message code="portal.btn.label.8" text="검색" /></a>
					</span>  
				</td>
			</tr>
			<tr>
				<th width="90"><spring:message code="portal.label.22" text="기간" /></th>
				<td>
					<input type="text" class="input-d" id="datepicker1" style="WIDTH: 80px;" />
					 ~ 
					<input type="text" class="input-d" id="datepicker2" style="WIDTH: 80px;" />
				</td>
			</tr>
		</thead>
		</table>
		<!-- //table_write -->     
                 
		<!-- table-body-wrap -->
		<div class="table-body-wrap">  
			<div id="pageStatisticsLst">
			</div>
		</div>
		<!-- //table-body-wrap -->
	</div>
	<!-- //admin-content-width w1200 margin15-l -->
	<iframe id="hiddenMkFileFrame" name="hiddenMkFileFrame" style="width:800px;height:200px;display:none;border:solid #222222 1px;" src="about:blank" ></iframe>
</body>
</html>