<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Locale" %>
<%
Locale langType = (Locale)session.getAttribute("LANG_TYPE");
%>
<script type="text/javascript">
var myData;
var strSearchNotFound = "<spring:message code="portal.alert.msg.56" text="검색 결과가 없습니다." />";
var strSearchSystemError = "<spring:message code="portal.portlet.alert.8" text="일시적으로 데이터를 처리하지 못했습니다." />";

function openSearchWindow(link) {
	var option = {width:800,height:600,scrollbars:'yes',resizable:'yes',toolbar:'yes',status:'yes',location:'yes',menubar:'yes'};
	var screenW = screen.availWidth;
	var screenH = screen.availHeight;
	var windowW = option.width;
	var windowH = option.height;
	var left = (screenW - windowW)/2;
	var top = (screenH - windowH)/2;
	
	if(typeof(option.resizable)=='undefined') option.resizable = "no";
	if(typeof(option.toolbar)=='undefined') option.toolbar = "no";
	if(typeof(option.status)=='undefined') option.status = "no";
	if(typeof(option.scrollbars)=='undefined') option.scrollbars = "no";
	if(typeof(option.location)=='undefined') option.location = "no";
	if(typeof(option.menubar)=='undefined') option.menubar = "no";
	var opt = "toolbar="+option.toolbar+",scrollbars="+option.scrollbars+",resizable="+option.resizable+",status="+option.status+",location="+option.location+",menubar="+option.menubar+",width="+windowW+",height="+windowH+",left="+left+",top="+top;
	window.open(link,"luxor_search",opt);
}

function dictionaryXMLPars(xml) {
	if ($(xml).find("item").find("title").length > 0) {
		$('#dictionary_result').html("");
		myData = new Array($(xml).find("item").find("title").length);
		$(xml).find("item").each(function(idx) {
			myData[idx] = ['', '', '', ''];
			
			myData[idx][0] = ($(this).find("title").text());
			myData[idx][1] =($(this).find("link").text());
			myData[idx][2] =($(this).find("description").text());
			myData[idx][3] =($(this).find("thumbnail").text());
			$('#dictionary_result').append("<div class='menu-search-result' title='"+myData[idx][2]+"'><a href='javascript:;' onclick='openSearchWindow(\""+myData[idx][1]+"\");return false;'>"+myData[idx][0]+"</a></div>");
		});
		
	} 
	
	var pos = $('#genericPortletDictionaryInput').position();
	
	$('#dictionary_result_dialog').css('top', pos.top+19);
	$('#dictionary_result_dialog').css('left', pos.left);
	$('#dictionary_result_dialog').show();
}

function goSearchByDictionaryPortlet(){
	var searchText = encodeURIComponent($('#genericPortletDictionaryInput').val());
	
	$.ajax({
		type:'post',
		url: '/ep/luxor/common/jsProxy/getDictionaryRSS.jsp?searchText='+searchText+'&cacheTime='+new Date(), 
		dataType:'xml',
		success: function(data) {
			if($(data).find("item").find("title").length == 0) {
				$('#dictionary_result').html("<div style='padding:3px;text-align:center;'>"+strSearchNotFound+"</div>");
				var pos = $('#genericPortletDictionaryInput').position();
				$('#dictionary_result_dialog').css('top', pos.top+19);
				$('#dictionary_result_dialog').css('left', pos.left);
				$('#dictionary_result_dialog').show();
			} else {
				dictionaryXMLPars(data);
			}
		}, 
		error: function(xhr, status, error) {
			$('#dictionary_result').html("<div style='padding:3px;text-align:center;'>"+strSearchSystemError+"</div>");
			var pos = $('#genericPortletDictionaryInput').position();
			$('#dictionary_result_dialog').css('top', pos.top+19);
			$('#dictionary_result_dialog').css('left', pos.left);
			$('#dictionary_result_dialog').show();
		}
	});	 
}

//엔터키입력시
$(document).ready(function() {
	$('.dictionary-main input').keypress(function(e) {
		if(e.keyCode==13) {
			goSearchByDictionaryPortlet();
			return false;
		}
	});	
});
</script>
<div id="dictionaryPortlet" class="dictionary-main" style="margin:auto;width:100%">
	<input id='genericPortletDictionaryInput' type="text" class="input-d" style="width:70%;" /> 
	<span class="smain-btn" style="position:relative;top:2px;"><a id="dictionaryPortletSearchInput" href="javascript:goSearchByDictionaryPortlet()"><span class="browsing-icon" style="float:left;"></span><spring:message code="portal.portlet.label.145" text="검색"/></a>
	</span>
</div>

<div id="dictionary_result_dialog" class="dialog" style="width:70%;border:1px solid #828282;">
	<div id="dictionary_result" style="overflow:auto;padding:5px;height:200px;"></div>
	<div style="border-top:1px solid #c2c2c2;background:#F5F5F5;text-align:right;padding:3px;">
		<a href='#' onclick="$('#dictionary_result_dialog').hide()"><span style="color:#454545"><b><spring:message code="portal.btn.label.29" text="닫기"/></b></span></a>
	</div>
</div>