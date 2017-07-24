<%@page import="com.sds.acube.luxor.common.util.CommonUtil"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.security.UtilSSO" %>
<%@ page import="com.sds.acube.luxor.portal.vo.PortletContextVO" %>
<%@ page import="com.sds.acube.luxor.framework.config.LuxorConfig" %>

<%
	PortletContextVO portlet = (PortletContextVO)request.getAttribute("portlet");
	String mode = (String)request.getAttribute("mode");
	
	String portletContextName = portlet.getPortletContextName();
	
	String SSO = portlet.getSsoInfo();
	String param = "";
	
	String url = portlet.getHelpUrl();
	com.sds.acube.luxor.common.vo.SsoToken ssoToken = null; //@add 2014.02.25
	if("D1".equals(SSO)) {
		String encodeAlgorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
		String D1 = UtilSSO.encodeSession(request, encodeAlgorithm);
		ssoToken = UtilSSO.getSsoToken(request, D1); //@add 2014.02.25
		param = (url.lastIndexOf("?") > -1 ? "&" : "?") + "D0=SDS&D1="+D1+"&D2=&D3="+encodeAlgorithm;
		param += "&STM=" + ssoToken.getSTM() + "&SHM=" + ssoToken.getSHM(); //@add 2014.02.25
	}

	// 해당 포틀릿으로 가야하는 파라미터만 구별해냄
	Map map = request.getParameterMap();
	Iterator it = map.entrySet().iterator();

	String query = "";
	while(it.hasNext()) {
		Map.Entry entry = (Map.Entry)it.next();
		String key = CommonUtil.nullTrim((String)entry.getKey());
		
		if(key.contains(portletContextName)) {
			String paramKey = key.split("\\|")[1];
			String[] value = (String[])entry.getValue();
			query += "&"+ paramKey + "=" + value[0];
		}
	}
	
	String requestURI = url + param + query;
%>
<!DOCTYPE HTML>
<script type="text/javascript" src="/ep/luxor/ref/js/jquery-1.4.3.min.js" charset="utf-8"></script>
<script type="text/javascript">
	// IFRAME 포틀릿 로딩 후 IFRAME의 높이를 조절
	$(document).ready(function() {
		var content_body_height = $(window).height();
		$('#ifr_<%=portletContextName%>').height(content_body_height);
	});
</script>
<body style="overflow:hidden;margin:0px;">
<iframe id="ifr_<%=portletContextName%>" name="ifr_<%=portletContextName%>" src="<%=requestURI%>" frameborder="0" style="z-index:1;background-color:#FFFFFF;width:100%;height:100%;"></iframe>
</body>