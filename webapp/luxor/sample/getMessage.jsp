<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.service.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	MessageService messageService = (MessageService)ctx.getBean("messageService");
	
	MessageVO messageVO = new MessageVO();
	messageVO.setMessageId("M201006031543560051001");
	messageVO.setLangType("ko");
	
	messageVO = messageService.getMessageByIdLang(messageVO);
%>

<%=messageVO.getMessageName()%>
<br /><br /><br />
<%=messageService.toString()%>
