<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.service.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.service.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());

	TreeService treeService = (TreeService)ctx.getBean("treeService");
	PortalPageService portalPageService = (PortalPageService)ctx.getBean("portalPageService");
	
	for(int i=0; i < 100; i++) {
		String nodeId = CommonUtil.generateId("N");
		
		TreeVO treeVO = new TreeVO();
		treeVO.setTenantId("T00001");
		treeVO.setPortalId("P00005");
		treeVO.setTreeId("PORTAL_PAGE");
		treeVO.setNodeId(nodeId);
		treeVO.setMessageNameAll("ko^Page_Test_111"+i+"|en^Page_Test_111"+i);
		treeVO.setParentId("N201011011132526022360");
		String messageId = treeService.insertTreeNode(treeVO);
		
		PortalPageVO portalPageVO = new PortalPageVO();
		portalPageVO.setTenantId("T00001");
		portalPageVO.setPortalId("P00005");
		portalPageVO.setPageId(nodeId);
		
		boolean result = portalPageService.insertPage(portalPageVO);
		
	}
	
%>

