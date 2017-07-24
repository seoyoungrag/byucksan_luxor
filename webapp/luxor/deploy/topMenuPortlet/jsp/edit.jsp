<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.MessageSource"%>
<%@page import="java.util.Locale"%>

<!DOCTYPE HTML>
<%
WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
MessageSource messageSource = (MessageSource)ctx.getBean("messageSource");

String message1 = messageSource.getMessage("portal.page.label.23", null, (Locale)session.getAttribute("LANG_TYPE"));
String message2 = messageSource.getMessage("portal.page.label.24", null, (Locale)session.getAttribute("LANG_TYPE"));
String message3 = messageSource.getMessage("portal.page.label.25", null, (Locale)session.getAttribute("LANG_TYPE"));
%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#_topMenuSetup a').click(function() {
			
			var _pageId = selectedNodeId;
			if(content.refer == 'CM') {
				_pageId = 'ROOT';
			}
			var _contentId = $(this).closest('li[class^=content-wrap]').attr('id');
			var _zoneId = $(this).attr('zoneId');
			luxor.popup('/ep/page/selector.do?pageId='+_pageId+'&contentId='+_contentId+'&zoneId='+_zoneId, {
				width:920,
				height:600,
				scrollbars:'yes',
				resizable:'yes'
			});
			return false;
		});
	});
</script>

<div id="_topMenuSetup">
	<a href="#" zoneId="mzTop"><%=message1 %></a> |
	<a href="#" zoneId="mzMiddle"><%=message2 %></a> |
	<a href="#" zoneId="mzBottom"><%=message3 %></a>
</div>