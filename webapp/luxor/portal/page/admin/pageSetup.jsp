<%/*
	pageManager.jsp 에서 Ajax로 호출됨
	/portal/page/index.jsp와 같고 컨텐츠 관리라는 부분이 추가되어 있음
*/%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.portal.vo.PortalPageVO" %>

<%
	PortalPageVO _page = (PortalPageVO)request.getAttribute("page");
%>

<div id="wrap" class="luxor-main">
	<div id="header" type="zone" class="luxor-top">
		<h6 name="contentManager" class="admin-sub-title"><spring:message code="portal.content.label.5" text="상단" /> <spring:message code="portal.btn.label.22" text="컨텐츠 관리" /></h6>
		<ul class="unfixed" type="unfixed"></ul>
	</div>
	<div id="body" class="luxor-body">
		<div class="luxor-inner-body">
			<div id="left" type="zone" class="luxor-left">
				<h6 name="contentManager" class="admin-sub-title"><spring:message code="portal.content.label.6" text="좌측" /> <spring:message code="portal.btn.label.22" text="컨텐츠 추가" /></h6>
				<ul class="fixed" type="fixed"></ul>
				<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
			</div>
			<div id="content-left" type="zone" class="luxor-content-left">
				<h6 name="contentManager" class="admin-sub-title"><spring:message code="portal.content.label.19" text="좌측중앙" /> <spring:message code="portal.btn.label.22" text="컨텐츠 관리" /></h6>
				<ul class="fixed" type="fixed"></ul>
				<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
			</div>			
			<div id="content" type="zone" class="luxor-content">
				<h6 name="contentManager" class="admin-sub-title"><spring:message code="portal.content.label.7" text="중앙" /> <spring:message code="portal.btn.label.22" text="컨텐츠 관리" /></h6>
				<ul class="fixed" type="fixed"></ul>
				<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
			</div>
			<div id="content-right" type="zone" class="luxor-content-right">
				<h6 name="contentManager" class="admin-sub-title"><spring:message code="portal.content.label.18" text="우측중앙" /> <spring:message code="portal.btn.label.22" text="컨텐츠 관리" /></h6>
				<ul class="fixed" type="fixed"></ul>
				<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
			</div>				
			<div id="right" type="zone" class="luxor-right">
				<h6 name="contentManager" class="admin-sub-title"><spring:message code="portal.content.label.8" text="우측" /> <spring:message code="portal.btn.label.22" text="컨텐츠 관리" /></h6>
				<ul class="fixed" type="fixed"></ul>
				<ul class="unfixed" type="unfixed" style="height:100px;"></ul>
			</div>
		</div>
	</div>
	<div id="footer" type="zone" class="luxor-footer" style="margin-top:25px;">
		<h6 name="contentManager" class="admin-sub-title"><spring:message code="portal.content.label.9" text="하단" /> <spring:message code="portal.btn.label.22" text="컨텐츠 관리" /></h6>
		<ul class="unfixed" type="unfixed"></ul>
	</div>
</div>

<input type="hidden" id="isPersonal" value="<%=_page.getIsPersonal()%>" />
<input type="hidden" id="isTemplate" value="<%=_page.getIsTemplate()%>" />