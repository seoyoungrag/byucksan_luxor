<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sds.acube.luxor.framework.cache.*" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.sds.acube.luxor.common.util.UtilRequest" %>

<%
 String value = UtilRequest.getString(request,"keyValue");
 if(!value.equals("CLEAR_ALL") && !value.equals("MONITORING_ON") && !value.equals("MONITORING_OFF")) {
 	MemoryCacheCenter.getInstance().clear(value);
 } else if(value.equals("CLEAR_ALL")) {
	 MemoryCacheCenter.getInstance().clearAll();
 } else if(value.equals("MONITORING_ON")) {
	 MemoryCacheMonitor.getInstance().run();
 } else if(value.equals("MONITORING_OFF")) {
	 MemoryCacheMonitor.getInstance().stopThread();
 } 
 
 String openURL = UtilRequest.getString(request,"openURL","");
%>
<jsp:include page="/luxor/common/header.jsp" />
<script type="text/javascript" src="/ep/luxor/ref/js/jquery.disable.text.select.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
<%
	if(openURL != null && !"".equals(openURL)){
%>	
	opener.location.href = "<%=openURL%>";
<%
	}
%>	
	window.close();
});
</script>
