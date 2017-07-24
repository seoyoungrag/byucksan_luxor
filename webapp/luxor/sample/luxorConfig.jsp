<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.framework.config.*" %>

<%
	/*
	out.println("CONCURRENT_LOGIN_LIMIT="+LuxorConfig.getEnvString(request, "CONCURRENT_LOGIN_LIMIT")+"<br />");
	out.println("CONCURRENT_LOGIN_METHOD="+LuxorConfig.getEnvString(request, "CONCURRENT_LOGIN_METHOD")+"<br />");
	out.println("NO_PERMISSION_CONTENT="+LuxorConfig.getEnvString(request, "NO_PERMISSION_CONTENT")+"<br />");
	out.println("NO_PERMISSION_MENU="+LuxorConfig.getEnvString(request, "NO_PERMISSION_MENU")+"<br />");
	out.println("USE_PERSONAL_MENU="+LuxorConfig.getEnvString(request, "USE_PERSONAL_MENU")+"<br />");
	*/
	out.println("NO_PERMISSION_PAGE="+LuxorConfig.getEnvString(request, "NO_PERMISSION_PAGE")+"<br />");
%>
