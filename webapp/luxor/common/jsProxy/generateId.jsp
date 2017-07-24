<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.sds.acube.luxor.common.util.*" %>

<%
	String prefix = CommonUtil.nullTrim(UtilRequest.getString(request, "prefix"));
	String id = CommonUtil.generateId(prefix);
	response.reset();
	out.println(id);
%>