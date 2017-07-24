<%/* 
	이 페이지를 호출하는 페이지 : /portal/page/admin/contentManager.jsp 
*/%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	PortalContentVO[] contents = (PortalContentVO[])request.getAttribute("contents");
	String usePersonal = (String)request.getAttribute("usePersonal");
	
	for(PortalContentVO content : contents) {
		String contentId = content.getContentId();
		String contentName = content.getMessageName();
		int type = content.getTypeOfPortlet();
		if(usePersonal.equals("Y")) {
			if(content.getUsePersonal().equals("Y")) {
				%>
				 <div id="<%=contentId%>" class="link pop_content_div"><a href='#'> - <%=contentName%></a></div>
				<%		
			}
		} else {
			%>
			 <div id="<%=contentId%>" class="link pop_content_div" type="<%=type%>"><a href='#'> - <%=contentName%></a></div>
			<%	
		}
	}
%>
