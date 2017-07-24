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
	Locale locale = (Locale)session.getAttribute("LANG_TYPE");
	String mode = (String)request.getAttribute("mode");
	
	String portletContextName = portlet.getPortletContextName();
	
	String SSO = portlet.getSsoInfo();
	String param = "";
	
	String url = "";
	if(mode.equals("edit")) {
		url = portlet.getEditUrl();
	} else if(mode.equals("go")) {
		url = portlet.getGoUrl();
		if( url.equals("") || url == null ) {
			url = portlet.getViewUrl();
		}
	} else if(mode.equals("help")) {
		url = portlet.getHelpUrl();
	} else {
		url = portlet.getViewUrl();
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
			
			//url에 ?가 있는지 확인하고 첫번째 query에 request parameter를 추가한다.
			if(query.equals("")){
			  query += (url.lastIndexOf("?") > -1 ? "&" : "?") + paramKey + "=" + value[0];
			}else{
			  query += "&" + paramKey + "=" + value[0];
			}
			
		}
	}
	
	String D1 = "";
	String encodeAlgorithm = LuxorConfig.getString("SSO.ENCODE_ALGORITHM");
	com.sds.acube.luxor.common.vo.SsoToken ssoToken = null; //@add 2014.02.25
	
	if("D1".equals(SSO)) {
		D1 = UtilSSO.encodeSession(request, encodeAlgorithm);
		param = (url.lastIndexOf("?") > -1 ? "&" : "?") + "lang="+locale+"&D0=SDS&D1="+D1+"&D2=&D3="+encodeAlgorithm;
		ssoToken = UtilSSO.getSsoToken(request, D1); //@add 2014.02.25
	}
	
	String requestURI = url + query;
%>

<script type="text/javascript">
	// IFRAME 포틀릿 로딩 후 IFRAME의 높이를 조절
	$(document).ready(function() {
		var content_body_height = $('#ifr_<%=portletContextName%>').closest('.content-body').height();
		$('#ifr_<%=portletContextName%>').height(content_body_height);
		<%if("D1".equals(SSO)) {%>
			$('#form_<%=portletContextName%>').submit();
		<% } else { %>
			$('#ifr_<%=portletContextName%>').attr('src','<%=requestURI%>');
		<% } %>
	});
</script>
<form id="form_<%=portletContextName%>" name="form_<%=portletContextName%>" action="<%=requestURI %>" method="post" target="ifr_<%=portletContextName%>">
	<input type="hidden" name="lang" value="<%=locale %>"></input>
<%	if("D1".equals(SSO)) {%>	
	<input type="hidden" name="D0" value="SDS"/>
	<input type="hidden" name="D1" value="<%= D1 %>"/>
	<input type="hidden" name="D2" value=""/>
	<input type="hidden" name="D3" value="<%= encodeAlgorithm %>"/>
	<input type="hidden" name="STM" value="<%= ssoToken.getSTM() %>"/><!-- @add 2014.02.25 -->
	<input type="hidden" name="SHM" value="<%= ssoToken.getSHM() %>"/><!-- @add 2014.02.25 -->
<%	} %>	
</form>
<iframe id="ifr_<%=portletContextName%>" name="ifr_<%=portletContextName%>" src="" width="100%" frameborder="0" style="z-index:1;background-color:#FFFFFF;"></iframe>

