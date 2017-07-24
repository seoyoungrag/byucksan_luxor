<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.PortletConstant" %>
<%@ page import="com.sds.acube.luxor.common.vo.UserProfileVO" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>


<%
	String currentPath = (String)request.getAttribute("currentPath");
	String portletContext = (String)request.getAttribute("portletContext");
	String themeImageUrl = UtilRequest.getString(request, "themeImageUrl");
	String contentId = UtilRequest.getString(request, "contentId");
	String minHeight =  UtilRequest.getString(request, "minHeight","150");

	if(Integer.parseInt(minHeight) < 150){
		minHeight = "150";
	}
%>
<script type="text/javascript">
function showTabList(tabPortletId) {
	var selectParam = "tabPortletId="+tabPortletId+"&isSetting=N";
	$('#TabPortletList_'+tabPortletId).html('');
	callJson("generalPortletController", "getTabContentsList", selectParam, function(data) {
		for(var i=0;i<data.length;i++) {
			$('#TabPortletList_'+tabPortletId).append('<li id="tab_'+data[i].contentId+'" style="padding-top:6px;padding-bottom:4px;">'
					+'<a style="display:inline;padding-bottom:4px;cursor:pointer;">'+data[i].messageName+'</a></li>');
			if(i==0){
				$('#TabPortletList_'+tabPortletId+' #tab_'+data[i].contentId).addClass('selected');
				showTab(tabPortletId,data[i].contentId);
			}
		}
	});
}

function selectTab(tabPortletId,contentId) {
	$('#TabPortletList_'+tabPortletId+' li').each(function() {
		$(this).removeClass();
	});
	$('#TabPortletList_'+tabPortletId+' #tab_'+contentId).addClass('selected');
	showTab(tabPortletId,contentId);
}

function showTab(tabPortletId,contentId) {
	
	$('#TabContents_'+tabPortletId).html('');
	param = 'tabPortletId='+tabPortletId+'&contentId='+contentId;
	callJson("generalPortletController", "getTabContentsUrl", param, function(data) {
		$("#TabContents_"+tabPortletId).load("/ep"+data[0]);
		callPorltetFunc(contentId, "load");
		loadPortletJs(data[1]) ;
		loadPortletCss(data[1]) ;

	});
}

function callPorltetFunc(contentId, action) {
	try {
		var portletId = this.getPortletId(contentId);
		eval(portletId+".on"+action+"()");
	} catch(e) {}
}

/**
 * 포틀릿에서 제공하는 Javascript 로딩
 */
function loadPortletJs(portletId) {
	var url = "/ep/luxor/common/jsProxy/getPortletResource.jsp?portletId="+portletId+"&type=js&cacheTime="+new Date();
	$.get(url, function(resource) {
		var arr = eval(resource);
		for(var i=0; i < arr.length; i++) {
			$.getScript(arr[i]);
		}
	});
}

/**
 * 포틀릿에서 제공하는 CSS 로딩
 */
function loadPortletCss (portletId) {
	var url = "/ep/luxor/common/jsProxy/getPortletResource.jsp?portletId="+portletId+"&type=css&cacheTime="+new Date();
	$.get(url, function(resource) {
		var arr = eval(resource);
		var linkStr = "";
		for(var i=0; i < arr.length; i++) {
			if($('link[href="'+arr[i]+'"]').is('link')) {
				$('link[href="'+arr[i]+'"]').attr('href',arr[i]);
			} else {
				linkStr += "<link name='portletCSS' rel='stylesheet' type='text/css' href='"+arr[i]+"'>";
			}
		}
		$('head').append(linkStr);
	});
}

$(document).ready(function() {
	showTabList('<%=contentId%>');
	$('#TabPortletList_<%=contentId%> li').live('click', function(e) {
		selectTab('<%=contentId%>', $(this).attr('id').replace("tab_",""));
	});
});

</script>

<div id="TabPortlet_<%=contentId %>" style="overflow:hidden;" >
	<div class="built-in-portlet-tab">
	    <ul id="TabPortletList_<%=contentId %>"></ul>
	</div>
	<div id="TabContents_<%=contentId %>" style="height:<%=minHeight%>px;overflow:hidden;border:1px solid #c3d2e8;">
	</div>
	<div class="clear"></div>
</div>
