<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>

<%
	AdminUserVO adminUser = (AdminUserVO)session.getAttribute("adminProfile");
	if(adminUser==null) {
		out.println("off");
	} else {
		out.println("on");
	}
%>
