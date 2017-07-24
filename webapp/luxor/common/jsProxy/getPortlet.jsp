<%@page import="com.sds.acube.luxor.framework.config.LuxorConfig"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.vo.*" %>
<%@ page import="com.sds.acube.luxor.common.util.*" %>
<%@ page import="com.sds.acube.luxor.portal.context.*" %>

<%
	try {
	  
	  	String D1 = CommonUtil.nullTrim(UtilRequest.getString(request, "D1"));
		String portalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
		String tenantId = CommonUtil.nullTrim((String)session.getAttribute("TENANT_ID"));
		
		if(!"".equals(D1)){
		  portalId = CommonUtil.nullTrim(UtilRequest.getString(request, "portalId"));
		  tenantId = CommonUtil.nullTrim(UtilRequest.getString(request, "tenantId"));
		}
		
		//String portalId = CommonUtil.nullTrim((String)session.getAttribute("PORTAL_ID"));
		//String tenantId = CommonUtil.nullTrim((String)session.getAttribute("TENANT_ID"));
		String mode = CommonUtil.nullTrim(UtilRequest.getString(request, "mode"));
		String pageId = CommonUtil.nullTrim(UtilRequest.getString(request, "pageId"));
		String portletId = CommonUtil.nullTrim(UtilRequest.getString(request, "portletId"));
		String contentId =  CommonUtil.nullTrim(UtilRequest.getString(request, "contentId"));
		String minHeight =  CommonUtil.nullTrim(UtilRequest.getString(request, "minHeight"));

		String contextName = portletId;
		
		PortletContextVO portlet = PortletContextRegistry.getPortlet(portalId, tenantId, portletId);
		
		int portletType = portlet.getTypeOfPortlet();

		portletId = portletType==5 ? "jsr168Portlet" : portletId;
		String deployPath = "/ep/luxor/deploy/"+portletId;
		String url = portlet.getViewUrl();
		
		// 수정 또는 도움말인 경우 URL 셋팅
		if(mode.equals("edit")) {
			url = portlet.getEditUrl();
		} else if(mode.equals("help")) {
			url = portlet.getHelpUrl();
		} else if(mode.equals("go")) {
			url = portlet.getGoUrl();
			if( url.equals("") || url == null ) {
				url = portlet.getViewUrl();
			}
		}

		// URL앞에 슬러쉬(/)가 있으면 제거
		url = url.startsWith("/") ? url.substring(1) : url;
		
		// 서블릿인지 JSP인지 구별
		url = (url.lastIndexOf(".do") > -1) ? url : "/luxor/deploy/"+portletId+"/jsp/"+url;
		
		// Iframe URL 셋팅
		String iframePortletUrl = "/portlet/iframeLoad.do?contextName="+portletId+"&mode="+mode;
		
		String helpPortletUrl = "/portlet/helpPageLoad.do?contextName="+portletId+"&mode="+mode;

		// 최종 URL 결정
		url = portletType==1 ? iframePortletUrl : url;
		
		
		//help 버튼을 팝업으로 사용 할 경우
		String helpButtonPopup = LuxorConfig.getEnvString(request, "HELP_BTN_POPUP") == null ? "N" : LuxorConfig.getEnvString(request, "HELP_BTN_POPUP");
		if(helpButtonPopup.equals("Y") && mode.equals("help")) {
			url = helpPortletUrl;
		}
		
		// Generic 포틀릿일 경우에는 컨트롤러를 한번 태워서 호출해줌 (다국어 문제때문에)
		if(url.startsWith("/luxor/deploy")) {
			url = "/portlet/genericPortletLoad.do?url="+url;
		}
		
		// 해당 포틀릿으로 가야하는 파라미터만 구별해냄
		// 이걸 구별할 수가 있냐? 개발자마다 파라미터를 쓸수도 있는거지, 뷰페이지 이동을 위해 수정하기로 함.
		Map map = request.getParameterMap();
		Iterator it = map.entrySet().iterator();

		String query = "";
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String key = CommonUtil.nullTrim((String)entry.getKey());
			
			if(key.contains(contextName)) {
				String paramKey = key.split("\\|")[1];
				String[] value = (String[])entry.getValue();
				query += "&"+ paramKey + "=" + value[0];
			}
		}
		
		url += query;
		request.setAttribute("portletId", portletId);
		request.setAttribute("pageId", pageId);
		request.setAttribute("portletContext", contextName);
		request.setAttribute("currentPath", deployPath);
		request.setAttribute("contentId", contentId);
		request.setAttribute("minHeight", minHeight);		
		
		request.getRequestDispatcher(url).include(request, response);
	} catch(Exception e) {
		e.printStackTrace();
		out.println("No Portlet Registered!!");
	}
%>
