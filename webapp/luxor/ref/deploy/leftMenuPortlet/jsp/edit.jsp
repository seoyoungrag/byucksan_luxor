<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.MessageSource"%>
<%@page import="java.util.Locale"%>

<%
WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
MessageSource messageSource = (MessageSource)ctx.getBean("messageSource");

String message1 = messageSource.getMessage("portal.page.label.26", null, (Locale)session.getAttribute("LANG_TYPE"));
%>
<!DOCTYPE HTML>

<script type="text/javascript">
	$(document).ready(function() {
		$('#_leftMenuSetup a').click(function() {
			var _pageId = selectedNodeId;
			if(content.refer == 'CM') {
				_pageId = 'ROOT';
			}
			var _contentId = $(this).closest('li.content-wrap').attr('id');
			if(_contentId==null || _contentId=="") {
				_contentId= $(this).closest('li.content-wrap-hide').attr('id');
			}
			var _zoneId = $(this).attr('zoneId');
			luxor.popup('/ep/page/selector.do?pageId='+_pageId+'&contentId='+_contentId+'&zoneId='+_zoneId, {
				width:920,
				height:600,
				scrollbars:'yes'
			});
			return false;
		});
	});
</script>

<div id="_leftMenuSetup">
	<a href="#" zoneId="mzVertical"><%=message1 %></a>
</div>