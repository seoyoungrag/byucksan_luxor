<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Locale" %>
<%
String portletContext = (String)request.getAttribute("portletContext");
Locale locale = (Locale)session.getAttribute("LANG_TYPE");
%>
<script type="text/javascript">

function selectNewsTab(value){
	$('#googleNewsTab li').each(function() {
		$(this).removeClass();
	});
	
	$('#'+value).addClass('selected');
	
	$('#googleNewsTab li').each(function() {
		if($(this).attr('class')=='selected'){
			var selectionId = $(this).attr('id');
			$('#googleNewsContent').attr('src','http://www.google.com/uds/modules/elements/newsshow/iframe.html?topic='+selectionId+'&rsz=large&format=300x250&hl=<%=locale%>');
		}
	});
}
$(document).ready(function() {
	$('#googleNewsTab li').each(function() {
		if($(this).attr('class')=='selected'){
			var selectionId = $(this).attr('id');
			$('#googleNewsContent').attr('src','http://www.google.com/uds/modules/elements/newsshow/iframe.html?topic='+selectionId+'&rsz=large&format=300x250&hl=<%=locale%>');
		}
	});
});
</script>

<div class="built-in-portlet-tab">
    <ul id="googleNewsTab">
        <li id="h" class="selected"><a href="javascript:selectNewsTab('h');"><spring:message code="portal.portlet.label.130" text="속보"/></a></li>
        <li id="p"><a href="javascript:selectNewsTab('p');"><spring:message code="portal.portlet.label.131" text="정치"/></a></li>
        <li id="b"><a href="javascript:selectNewsTab('b');"><spring:message code="portal.portlet.label.132" text="경제"/></a></li>
        <li id="t"><a href="javascript:selectNewsTab('t');"><spring:message code="portal.portlet.label.133" text="기술"/></a></li>
        <li id="e"><a href="javascript:selectNewsTab('e');"><spring:message code="portal.portlet.label.134" text="연예"/></a></li>
        <li id="s"><a href="javascript:selectNewsTab('s');"><spring:message code="portal.portlet.label.135" text="스포츠"/></a></li>
    </ul>
</div>
<iframe id="googleNewsContent" src="http://www.google.com/uds/modules/elements/newsshow/iframe.html?topic=s&rsz=large&format=300x250&hl=ko"
        frameborder="0" width="300" height="250" marginwidth="0" marginheight="0"></iframe>
