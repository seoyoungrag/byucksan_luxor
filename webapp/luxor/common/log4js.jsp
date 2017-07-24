<%@ page import="com.sds.acube.luxor.common.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="org.apache.commons.logging.*" %>

<%
	Log logger = LogFactory.getLog(ConstantList.class);
	String msg = UtilRequest.getString(request, "msg");
	logger.info("[Javascript Log] "+msg);
%>
