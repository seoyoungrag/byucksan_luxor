<%@page import="com.sds.acube.luxor.security.UtilSSO"%>
<%@page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- SSO 관련 클래스를 import 함 -->
<%@page import="com.sds.acube.luxor.security.UtilSSO" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	/***로그인 세션처리 ***/
	String encodeAlgorithm ="";
	String D1 ="";
	encodeAlgorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
	D1 = UtilSSO.encodeSession(request, encodeAlgorithm);
	
	String portalUrl = LuxorConfig.getString("WEB-APP.URL");
%>
<iframe src="<%=portalUrl%>/luxor_collaboration/bbs/bbsPostCommon.do?method=lst&id.boardId=201511201012266761003OD2ZdYmrubK&isMain=Y&D1=<%=D1 %>" ></iframe>