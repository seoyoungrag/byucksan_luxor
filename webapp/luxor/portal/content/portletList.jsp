<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.context.*" %>

<%
	String tenantId = CommonUtil.nullTrim((String)session.getAttribute("TENANT_ID"));
	String portalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
	String categoryId = CommonUtil.nullTrim(UtilRequest.getString(request, "categoryId"));

	PortletContextVO[] portlets = PortletContextRegistry.getPortletList();
	
	for(PortletContextVO portlet : portlets) {
		
		if(!tenantId.equals(portlet.getTenantId()) || !portalId.equals(portlet.getPortalId())) {
			continue;
		}

		String portletId = portlet.getPortletContextName();
		String portletName = portlet.getDisplayName();
		String portletCategoryId = portlet.getCategoryId();
		
		// NO_PERMISSION 포틀릿은 컨텐츠로 추가 못함
		if("NO_PERMISSION".equals(portletId)) {
			continue;
		}
		
		if(categoryId.equals("all") || categoryId.equals(portletCategoryId)) {
			out.println("<li id='"+portletId+"' name='"+portlet.getTypeOfPortlet()+"'>"+portletName+"</li>");
		}
	}
%>
