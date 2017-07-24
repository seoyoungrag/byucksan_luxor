<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.portal.service.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	int cPage = (Integer)request.getAttribute("cPage");
	int totalCnt = (Integer)request.getAttribute("totalCnt");
	AdminMenuVO[] menus = (AdminMenuVO[])request.getAttribute("menus");

	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	AdminService adminService = (AdminService)ctx.getBean("adminService");
	
	String tenantId = (String)session.getAttribute("TENANT_ID");
	String portalId = (String)session.getAttribute("PORTAL_ID");
%>

<table class="table-body">
	<thead>
		<tr>
			<th width="100"><spring:message code="portal.label.109" text="카테고리"/></th>
			<th width="200"><spring:message code="admin.label.2" text="메뉴명" /></th>
			<th width="150"><spring:message code="admin.label.3" text="메뉴ID" /></th>
			<th width="350"><spring:message code="admin.label.4" text="메뉴URL" /></th>
			<th width="50">seq</th>
			<th width="80"><spring:message code="portal.label.25" text="홈페이지" /></th>
			<th width="200"></th>
		</tr>
	</thead>
	<tbody>
<%
	if(menus.length==0) {
%>
			<tr>
				<td colspan="5">
					<spring:message code="portal.alert.msg.19" text="데이터가 없습니다."/>
				</td>
			</tr>
<%
	} else {
		for(AdminMenuVO menu : menus) {
	String parentId = menu.getParentId();
	String menuId = menu.getMenuId();
	String menuNameId = menu.getMenuNameId();
	String menuName = menu.getMessageName();
	String menuURL = menu.getMenuUrl();
	String isHome = menu.getIsHome(); 
	int seq = menu.getSeq(); 
	
	String categoryName = "";
	if(!"ROOT".equals(parentId)) {
		AdminMenuVO temp = new AdminMenuVO();
		temp.setTenantId(tenantId);
		temp.setPortalId(portalId);
		temp.setMenuId(parentId);
		temp.setLangType("ko");
		temp = adminService.getAdminMenu(temp);
		if(temp!=null) {
			categoryName = temp.getMessageName();
		}
	}
%>
			<tr>
				<td><%=categoryName%></td>
				<td><%=menuName%></td>
				<td><%=menuId%></td>
				<td><%=menuURL%></td>
				<td><%=seq%></td>
				<td><%=isHome%></td>
				<td>
					<span class="smain-btn"><a href='#' mode='setHome' menuId='<%=menuId%>' seq='<%=seq %>' menuNameId='<%=menuNameId%>'><spring:message code="portal.label.5" /></a></span>
					<span class="smain-btn"><a href='#' mode='mod' menuId='<%=menuId%>' seq='<%=seq %>' menuNameId='<%=menuNameId%>'><spring:message code="portal.btn.label.37" /></a></span>
					<span class="smain-btn"><a href='#' mode='del' menuId='<%=menuId%>' seq='<%=seq %>' menuNameId='<%=menuNameId%>'><spring:message code="portal.btn.label.30" /></a></span>
				</td>
			</tr>
<%			
		}
	}
%>
	</tbody>
</table>

<%-- 페이징 --%>
<jsp:include page="/luxor/common/paging.jsp">
    <jsp:param name="cPage" value="<%=cPage%>" />
    <jsp:param name="totalCount" value="<%=totalCnt%>" />
</jsp:include>
<%-- 페이징 --%>
