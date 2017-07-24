<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Locale" %>
<%
Locale langType = (Locale)session.getAttribute("LANG_TYPE");
%>
<script type="text/javascript">

function selectGenericPortletsearchTab(value){
	
	$('#genericPortletsearchTab li').each(function() {
		$(this).removeClass();
	});
	
	$('#'+value).addClass('selected');
	
	$('#genericPortletsearchTab li').each(function() {
		if($(this).attr('class')=='selected') {
			var selectionId = $(this).attr('id');
			$('#genericPortletsearchImage').attr('src','/ep/luxor/ref/image/portlet/browsing/'+value+'.gif');
			$('#genericPortletsearchImage').attr('alt',$('#'+value+' > a').text());
			$('#genericPortletsearchImage').attr('title',$('#'+value+' > a').text());
		}
	});
}

function goSearchByGenericPortletSearch(){
	var strBrower = $('#genericPortletsearchTab .selected').attr('id');
	var searchText = encodeURIComponent($('#genericPortletsearchInput').val());
	var winWidth = 800;
	var winheight = 600;
	
	if(strBrower=="browsing_01") {
		luxor.popup("http://search.naver.com/search.naver?where=nexearch&query="+searchText+"&sm=top_hty&fbm=1",
				{width:winWidth,height:winheight});
	}
	else if(strBrower=="browsing_02") {
		luxor.popup("http://search.daum.net/search?w=tot&t__nil_searchbox=btn&q="+searchText,
				{width:winWidth,height:winheight,scrollbars:'yes',resizable:'yes',toolbar:'yes',status:'yes',location:'yes',menubar:'yes'});
	}
	else if(strBrower=="browsing_03") {
		luxor.popup("http://search.nate.com/search/all.html?nq=&s=&sc=&afc=&j=&thr=sbma&q="+searchText,
				{width:winWidth,height:winheight,scrollbars:'yes',resizable:'yes',toolbar:'yes',status:'yes',location:'yes',menubar:'yes'});
	}
	else if(strBrower=="browsing_04") {
		luxor.popup("http://www.google.com/search?q="+searchText,
				{width:winWidth,height:winheight,scrollbars:'yes',resizable:'yes',toolbar:'yes',status:'yes',location:'yes',menubar:'yes'});
	}
	else if(strBrower=="browsing_05") {
		if("<%=langType%>"=='en'){
			luxor.popup("http://search.yahoo.com/search?p="+searchText,
				{height:winheight,scrollbars:'yes',resizable:'yes',toolbar:'yes',status:'yes',location:'yes',menubar:'yes'});
		}else{
			luxor.popup("http://kr.search.yahoo.com/search?p="+searchText,
					{height:winheight,scrollbars:'yes',resizable:'yes',toolbar:'yes',status:'yes',location:'yes',menubar:'yes'});
		}
	}
}

$(document).ready(function() {
	$('.browsing-main input').keypress(function(e) {
		if(e.keyCode==13) {
			goSearchByGenericPortletSearch();
			return false;
		}
	});	
});

</script>
<div class="built-in-portlet-tab">
	<ul id="genericPortletsearchTab">
		<li id="browsing_01" class="selected"><a href="javascript:selectGenericPortletsearchTab('browsing_01')"><spring:message code="portal.portlet.label.140" text="네이버"/></a></li>
		<li id="browsing_02"><a href="javascript:selectGenericPortletsearchTab('browsing_02')"><spring:message code="portal.portlet.label.141" text="다음"/></a></li>
		<li id="browsing_03"><a href="javascript:selectGenericPortletsearchTab('browsing_03')"><spring:message code="portal.portlet.label.142" text="네이트"/></a></li>
		<li id="browsing_04"><a href="javascript:selectGenericPortletsearchTab('browsing_04')"><spring:message code="portal.portlet.label.143" text="구글"/></a></li>
		<li id="browsing_05"><a href="javascript:selectGenericPortletsearchTab('browsing_05')"><spring:message code="portal.portlet.label.144" text="야후"/></a></li>
	</ul>
</div>
<div class="browsing-main">
	<img id="genericPortletsearchImage" src="/ep/luxor/ref/image/portlet/browsing/browsing_01.gif" alt="Search Engine" width="80" height="30"/> 
	<input id='genericPortletsearchInput' type="text" class="input-d" title="<spring:message code="portal.label.21" text="검색어" />" />
	<span class="smain-btn" style="vertical-align:middle;"><a id="genericPortletSearchInput" href="javascript:goSearchByGenericPortletSearch()"><span class="browsing-icon"></span><spring:message code="portal.portlet.label.145" text="검색"/></a>
	</span>
</div>