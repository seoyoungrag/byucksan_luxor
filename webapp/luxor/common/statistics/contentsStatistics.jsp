<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.Date" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.framework.config.*" %>

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
<title><spring:message code="statistics.label.9" text="컨텐츠 사용 현황" /></title>
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

		var url = "/ep/statistics/getContentsStatisticsList.do";
		var param = "";
		var fromDate = now_year+"-"+now_month+"-"+now_date + " 00:00:01";
		var toDate = now_year+"-"+now_month+"-"+now_date + " 23:59:59";
		param += "fromDate="+fromDate;
		param += "&toDate="+toDate;
		
		$('#contentsStatisticsLst').load(url,param);

		// 목록내보내기 클릭시
		$('#makeFile').click(function() {
	
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

	});

	/**
	 * 검색
	 */
	function goSearch() {
		var searchTxt = $("#searchTxt").val();
		var url = "/ep/statistics/getContentsStatisticsList.do";
		var param = "";
		var fromDate = $("#datepicker1").val() + " 00:00:01";
		var toDate = $("#datepicker2").val() + " 23:59:59";
		
		param += "fromDate="+fromDate;
		param += "&toDate="+toDate;
		param += "&searchTxt="+encodeURIComponent(searchTxt);
		$('#contentsStatisticsLst').load(url,param);
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

		var url = "/ep/statistics/getContentsStatisticsList.do";
		var param = "";
		var searchTxt = $("#searchTxt").val();
		var fromDate = $("#datepicker1").val() + " 00:00:01";
		var toDate = $("#datepicker2").val() + " 23:59:59";
		
		param += "fromDate="+fromDate;
		param += "&toDate="+toDate;
		param += "&searchTxt="+encodeURIComponent(searchTxt);
		param += "&cPage="+p;

		$('#contentsStatisticsLst').load(url,param);
    }

</script>	
</head>

<body><div id="eee"></div><div id="datepicker" class="datepicker_div" style="display: none;"/></div>
	<!-- admin-content-width w1200 margin15-l -->
	<div class="admin-content-width w1200 ml15" name="zone">
		<!-- subtitle -->
		<div class="title-sub">
			<span class="title-sub-text"><spring:message code="statistics.label.9" text="컨텐츠 사용 현황" /></span>
			<!-- button -->
			<div class="aright">
				<%--<span class="main-btn"><a href="#" id="makeFile"><spring:message code="portal.btn.label.33" text="목록내보내기" /></a></span>--%>
			</div>
			<!-- //button -->
		</div>
		<!-- //subtitle -->

		<!-- table_write -->
		<table class="table-search">
		<thead>
			<tr>
				<th width="90"><spring:message code="statistics.label.11" text="컨텐츠명" /></th>
				<td colspan="3">
					<input type="text" class="input-d" id="searchTxt" style="WIDTH: 99%;" />
				</td>
				<td width="62" rowspan="2">
					<span class="smain-btn">
						<a href="#" id="goSearch"><span class="btnicon_01"></span><spring:message code="portal.btn.label.8" text="검색" /></a>
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
			<div id="contentsStatisticsLst">
			</div>
		</div>
		<!-- //table-body-wrap -->
	</div>
	<!-- //admin-content-width w1200 margin15-l -->

</body>
</html>