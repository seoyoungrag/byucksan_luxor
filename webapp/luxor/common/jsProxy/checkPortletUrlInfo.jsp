<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>

<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.context.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>

<%
	try {
		String portalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
		String tenantId = CommonUtil.nullTrim((String)session.getAttribute("TENANT_ID"));
		String portletId = CommonUtil.nullTrim(UtilRequest.getString(request, "portletId"));
		
		PortletContextVO portlet = PortletContextRegistry.getPortlet(portalId, tenantId, portletId);
		if(portlet==null) {
			out.print("{\"edit\":false,\"help\":false}");
			return;
		}
		
		boolean editUrl = !CommonUtil.isNullOrEmpty(portlet.getEditUrl());
		boolean helpUrl = !CommonUtil.isNullOrEmpty(portlet.getHelpUrl());
		
		out.print("{\"edit\":"+editUrl+",\"help\":"+helpUrl+"}");
	} catch(Exception e) {
	} 
%>
