<%@ page import="com.sds.acube.luxor.portal.context.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	PortletContextRegistry.init(ctx);
%>
Luxor Context Reload OK!!