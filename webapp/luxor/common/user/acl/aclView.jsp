<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.common.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.ConstantList" %>
<%@ page import="com.sds.acube.luxor.ws.client.aclservice.*" %>
<%@ page import="org.springframework.context.MessageSource" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %> 
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
 
<%
	String compId ="";
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	
	if(userProfile == null) {
		out.println("<script type=\"text/javascript\">alert('조회 권한이 없습니다.');window.close();</script>");
		return;
	}else{
		compId = userProfile.getCompId();
	}
	
    AccessList accessInfos = (AccessList)request.getAttribute("accessInfos");
    
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	MessageSource messageSource = (MessageSource)ctx.getBean("messageSource");
	
%>

<html lang="ko">
<head>
<title><spring:message code="acl.label.14" text="권한조회" /></title>
<jsp:include page="/luxor/common/header.jsp" />

<script type="text/javascript">

	$(document).ready(function() {

		// 닫기 버튼 클릭시
		$('#btn_close').click(function() {
			self.close();
		});
	});
		
</script>
</head>
<body>
	<div class="admin-popup-wrap mr10 ml10">
		<div class="title-pop"> 
			<span class="title-pop-text"><spring:message code="acl.label.14" text="권한조회" /></span>
		</div>
		
		<div class="box-font-blue mb5">
			<spring:message code="acl.label.4" text="- 권한은 위에서부터 순서대로 적용됩니다." />
		</div>
		<!-- container --> 
		<div class="pop-content clear"> 
			<!-- START Title --> 
			<table class="table-body"> 
				<thead> 
				<tr>
					<th width="30"></th> 
					<th width="100"><spring:message code="acl.label.17" text="Type" /></th> 
					<th width="150"><spring:message code="acl.label.15" text="권한대상" /></th> 
					<th width="50"><spring:message code="acl.label.16" text="권한" /></th> 
				</tr>	
				</thead>
<%
    String permissionString = "";
    String accessType = "";
    String accessTypeString = "";
    int accessCount = accessInfos.getAccessList().size();
    Access access;
    Permission p;

	if(accessCount>0){
	    for (int i=0; i < accessCount; i++){
	    	permissionString = "";
	        access = accessInfos.getAccessList().get(i);
	        p = access.getPermission();
	        if (p == null) {
	            permissionString = messageSource.getMessage("acl.label.6", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }else {
	        	if(p.isReadable()){
		        	permissionString = "R";
	    	    }
	        	if(p.isWriteable()){
	        		permissionString = "RW";
	        	}
	        }
	        if (permissionString.equals("")) {
	            permissionString = messageSource.getMessage("acl.label.6", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }
	        
	        accessType = access.getAccessType();
	        if (accessType.equals(ConstantList.TYPE_VIRTUAL_GROUP)) {
	        	accessTypeString = messageSource.getMessage("user.label.36", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }else if (accessType.equals(ConstantList.TYPE_ROLE)) {
	        	accessTypeString = messageSource.getMessage("user.label.37", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }else if (accessType.equals(ConstantList.TYPE_ACL_GROUP)) {
	        	accessTypeString = messageSource.getMessage("user.label.38", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }else if (accessType.equals(ConstantList.TYPE_DEPT)) {
	        	accessTypeString = messageSource.getMessage("user.label.11", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }else if (accessType.equals(ConstantList.TYPE_TITLE)) {
	        	accessTypeString = messageSource.getMessage("user.label.12", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }else if (accessType.equals(ConstantList.TYPE_USER)) {
	        	accessTypeString = messageSource.getMessage("user.label.35", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }else if (accessType.equals(ConstantList.TYPE_COMPANY)) {
	        	accessTypeString = messageSource.getMessage("user.label.10", new String[]{}, (Locale)session.getAttribute("LANG_TYPE"));
	        }else {
	            accessTypeString = "";
	        }
%>
			<tbody> 
				<tr>
				    <td>
				    	<%= i+1 %>
				    </td>
					<td>
						<%= accessTypeString %>
					</td>
					<td>
						<%= access.getAccessName() %>
					</td>
					<td>
						<%= permissionString %>
					</td>
				</tr>
			</tbody>
<%  	}
	}else{
%>				<tbody> 
					<tr>
						<td colspan="4">
							<spring:message code="portal.alert.msg.19" text="데이터가 없습니다."/>
						</td>
					</tr>
				</tbody>
<%	}
%>			</table>
        
	        <!-- 버튼 -->
			<div class="aright mt5">
				<span class="main-btn"><a href="#" id="btn_close"><spring:message code="portal.btn.label.29" text="닫기" /></a></span>
			</div>
			<!-- //버튼 -->
			
		</div>
		<!-- //container -->
		<div class="h25"></div>
	</div>
</body>
</html>
