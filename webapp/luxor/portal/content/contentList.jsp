<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.common.service.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="com.sds.acube.luxor.portal.context.PortletContextRegistry"%>
<%

WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
UserService userService = (UserService)ctx.getBean("userService");
PortletContextVO[] portlets = PortletContextRegistry.getPortletList();
int cPage = (Integer)request.getAttribute("cPage");
int totalCount = (Integer)request.getAttribute("totalCount");
PortalContentVO[] contents = (PortalContentVO[])request.getAttribute("contents");
String currentPortalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));

%>
<div class="admin-content-width w1000">
	<table class="table-body">
		<thead>
			<tr>
				<th width="140px"><spring:message code="portal.label.26" text="컨텐츠ID" /></th>
				<th width="150px"><spring:message code="portal.label.28" text="컨텐츠명" /></th>
				<th width="*"><spring:message code="portal.label.27" text="포틀릿ID" /></th>
				<th width="50px"><spring:message code="portal.label.29" text="등록자" /></th>
				<th width="120px"><spring:message code="portal.label.30" text="등록일" /></th>
				<th width="50px"><spring:message code="portal.label.215" text="개인화" /></th>
				<th><spring:message code="portal.label.31" text="기능" /></th>
			</tr>
		</thead>
		<tbody>
<%
	
	for(PortalContentVO content : contents) {
		int typeOfPortlet = 0;
		for(PortletContextVO portlet : portlets) {
			if(portlet.getPortletContextName().equals(content.getPortletId())) {
				typeOfPortlet = portlet.getTypeOfPortlet();
			}
		}
		String contentId = content.getContentId();
		String portletId = content.getPortletId();
		String portalId = content.getPortalId();
		String contentName = content.getMessageName();
		String regUserId = content.getRegUserId();
		String regDate = content.getRegDate2String();
		String regUserName = "ADMIN";
		if(!"ADMIN".equals(regUserId)) {
			try {
				UserProfileVO userVO = userService.getUserByUID(regUserId);
				regUserName = userVO.getUserName();
			} catch(Exception e) {
				regUserName = "ADMIN";
			}
		}
%>
			<tr>
				<td><%=contentId%></td>
				<td mode="drag"><span id="<%=contentId%>" class="move"><%=contentName%></span></td>
				<td><%=portletId%></td>
				<td><%=regUserName%></td>
				<td><%=regDate%></td>
				<td><%=content.getUsePersonal()%></td>
				<td>
					<%if(currentPortalId.equals(portalId)) { %>
						<span class="smain-btn">
							<a href='#' name='priv' contentId='<%=contentId%>'><spring:message code="portal.btn.label.38" text="권한 설정" /></a>
						</span>
						<span class="smain-btn">
							<a href='#' name='personal' contentId='<%=contentId%>' usePersonal='<%=content.getUsePersonal()%>'><spring:message code="portal.label.214" text="개인화" /></a>
						</span>
						<span class="smain-btn">
							<a href='#' name='directRegister' contentId='<%=contentId%>' title="<spring:message code="portal.content.label.1" text="컨텐츠를 페이지로 바로등록" />"><spring:message code="portal.content.label.2" text="바로등록" /></a>
						</span>
						<span class="smain-btn">
							<a href='#' name='mod' typeOfPortlet='<%=typeOfPortlet%>' contentId='<%=contentId%>'><spring:message code="portal.btn.label.37" text="수정" /></a>
						</span>
						<span class="smain-btn">
							<a href='#' name='del' contentId='<%=contentId%>'><spring:message code="portal.btn.label.30" text="삭제" /></a>
						</span>
					<% } else { %>
						<span class="smain-btn">
							<a href='#' name='copyContent' portletId='<%=portletId %>' contentId='<%=contentId%>' portalId='<%=portalId %>' typeOfPortlet='<%=typeOfPortlet%>' title="<spring:message code="portal.content.alert.1" text="현재 포탈의 컨텐츠로 복사합니다." />"><spring:message code="portal.content.label.3" text="컨텐츠복사" /></a>
						</span>
					<% } %>
				</td>
			</tr>
<%		
	}
%>
		</tbody>
	</table>
	
	<!-- 페이징 -->
	<jsp:include page="/luxor/common/paging.jsp">
		<jsp:param name="cPage" value="<%=cPage%>" />
		<jsp:param name="totalCount" value="<%=totalCount%>" />
	</jsp:include>
	<!-- //페이징 -->
</div>