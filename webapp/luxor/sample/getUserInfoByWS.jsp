<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.dao.*" %>
<%@ page import="com.sds.acube.luxor.common.service.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	UserService userService = (UserService)ctx.getBean("userService");
	UserProfileVO userVO = userService.getUserByUID("U1038220033134377680");
%>

<%=userVO.getUserName()%>
