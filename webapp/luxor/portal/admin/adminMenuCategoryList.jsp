<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>

<table class="table-body">
	<thead>
		<tr>
			<th><spring:message code="portal.label.109" text="카테고리"/></th>
			<th width="100"></th>
		</tr>
	</thead>
	<tbody>
<%
	int totalCnt = (Integer)request.getAttribute("totalCnt");
		AdminMenuVO[] menus = (AdminMenuVO[])request.getAttribute("menus");
	
		if(menus.length==0) {
%>
			<tr>
				<td>
					<spring:message code="portal.alert.msg.19" text="데이터가 없습니다."/>
				</td>
			</tr>
<%
	} else {
	for(AdminMenuVO menu : menus) {
		String menuId = menu.getMenuId();
		String menuNameId = menu.getMenuNameId();
		String menuName = menu.getMessageName();
		int seq = menu.getSeq();
%>
			<tr>
				<td><%=menuName%></td>
				<td>
					<span class="smain-btn"><a href='#' mode='mod' menuId='<%=menuId%>' seq='<%=seq %>' menuNameId='<%=menuNameId%>'><spring:message code="portal.btn.label.37" /></a></span>
					<span class="smain-btn"><a href='#' mode='del' menuId='<%=menuId%>' seq='<%=seq %>' menuNameId='<%=menuNameId%>'><spring:message code="portal.btn.label.30" /></a></span>
				</td>
			</tr>
<%			}
		}
%>
	</tbody>
</table>
