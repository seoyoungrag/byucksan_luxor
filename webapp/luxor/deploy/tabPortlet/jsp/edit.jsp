<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.MessageSource"%>
<%@page import="java.util.Locale"%>

<%
WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
MessageSource messageSource = (MessageSource)ctx.getBean("messageSource");

String message1 = messageSource.getMessage("portal.page.label.27", null, (Locale)session.getAttribute("LANG_TYPE"));
%>
<!DOCTYPE HTML>

<script type="text/javascript">
	$(document).ready(function() {
		$('#_tabPortletSetup a').click(function() {
			var _contentId = $(this).closest('li.content-wrap').attr('id');
			if(_contentId==null || _contentId=="") {
				_contentId= $(this).closest('li.content-wrap-hide').attr('id');
			}
			luxor.popup('/ep/content/manager.do?type=contentsSelector&contentId='+_contentId+"&typeOfPortlet=3", {
				width:820,
				height:520,
				scrollbars:'yes'
			});
			return false;
		});
	});
</script>

<div id="_tabPortletSetup">
	<a href="#" zoneId="mzVertical"><%=message1 %></a>
</div>