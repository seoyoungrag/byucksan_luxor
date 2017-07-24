<%@page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>
<%@page import="com.sds.acube.luxor.common.vo.UserProfileVO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	UserProfileVO userProfile = (UserProfileVO)session.getAttribute("userProfile");
	String userLoginId = userProfile.getLoginId();
	
	String mailUrl = LuxorConfig.getString("MAIL.URL");
%>
<iframe name="ifr_mailPortlet" width="100%" class="autoheight-content" id="ifr_mailPortlet" src="<%=mailUrl%>/mailGate.ds?id=<%=userLoginId%>&noPw=1&act=sso&hideTopMenu=1" frameborder="0" style="height: 442px; z-index: 1; min-height: 0px; background-color: rgb(255, 255, 255);" />
