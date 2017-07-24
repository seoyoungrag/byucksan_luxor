<%-- 페이지 구성에서 보여지는 레이아웃 선택 부분 (Ajax로 호출됨) --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	String selectedLayoutId = (String)request.getAttribute("selectedLayoutId");
	PortalPageLayoutVO[] layouts = (PortalPageLayoutVO[])request.getAttribute("layouts");
%>

<%-- 레이아웃 목록 --%>
<div id="layoutCollection">
	<span style="margin-right:17px;"><spring:message code="portal.label.47" text="레이아웃 선택" /></span>
<%
	for(PortalPageLayoutVO layout : layouts) {
		String layoutName = layout.getMessageName();
		String layoutCss = layout.getLayoutCss();
		String regDate = layout.getRegDate2String();
		String isDefault = layout.getIsDefault();
		String layoutId = layout.getLayoutId();
		String isSelected = layoutId.equals(selectedLayoutId) ? "Y" : "";
%>
	<%-- <button type="button"  class="admin-layout-theme-btn-action"><%=layoutName%></button> --%>
	<span class="smain-btn"><a href="#" id="<%=layout.getLayoutId()%>" style="font-weight: normal;min-width: 100px; text-align: center;" isSelected="<%=isSelected%>" isDefault="<%=isDefault%>"><%=layoutName%></a></span>
<%		
	}
%>
</div>
