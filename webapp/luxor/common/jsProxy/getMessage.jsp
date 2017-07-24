<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.MessageSource"%>
<%@page import="java.util.Locale"%>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%
	String code = CommonUtil.nullTrim(UtilRequest.getString(request, "code"));
	String text = CommonUtil.nullTrim(UtilRequest.getString(request, "text"));
	
	ServletContext sc = request.getSession().getServletContext();
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
	MessageSource messageSource = (MessageSource)ctx.getBean("messageSource");
	Locale langType = (Locale)request.getSession().getAttribute("LANG_TYPE");
	String msg1 = messageSource.getMessage(UtilRequest.getString(request, "code"), null, langType);
	out.println(msg1);
%>