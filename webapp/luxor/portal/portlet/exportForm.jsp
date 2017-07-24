<%@ page contentType="text/xml; charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.portal.vo.PortletManagementVO" %>

<%
	response.reset();
	response.setHeader("Content-Disposition", "attachment;filename=portlet.xml");	
	response.setContentType("text/xml;charset=UTF-8");
	PortletManagementVO xml = (PortletManagementVO)request.getAttribute("xml");
	out.print(xml.getOutline());
%>
