<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.context.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>

<%
	out.print("[");
	try {
		String portalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
		String tenantId = CommonUtil.nullTrim((String)session.getAttribute("TENANT_ID"));
		String type = CommonUtil.nullTrim(UtilRequest.getString(request, "type"));
		String portletId = CommonUtil.nullTrim(UtilRequest.getString(request, "portletId"));
		
		if(type.equals("")) {
			type = "js";
		}
		
		PortletContextVO portlet = PortletContextRegistry.getPortlet(portalId, tenantId, portletId);
		if(portlet==null) {
			return;
		}
		
		List resources = type.equals("js") ? portlet.getScripts() : portlet.getStyles();
		
		for(int i=0; i < resources.size(); i++) {
			String resourcePath = "/ep/luxor/deploy/"+portletId;
			resourcePath += type.equals("js") ? "/js/" : "/css/";
			String resource = (String)resources.get(i);
			resourcePath += resource.startsWith("/") ? resource.substring(1) : resource;
			
			out.print("'"+resourcePath+"'");
			if(i < (resources.size()-1)) {
				out.print(",");
			}
		}
	} catch(Exception e) {
	} finally {
		out.print("]");
	}
%>
