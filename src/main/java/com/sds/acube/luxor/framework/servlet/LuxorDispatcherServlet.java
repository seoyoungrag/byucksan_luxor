/**
 * 
 */
package com.sds.acube.luxor.framework.servlet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.anyframe.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import com.sds.acube.luxor.common.util.DateUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.CommonVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.cache.MemoryCacheMonitor;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.listener.BaseHandle;
import com.sds.acube.luxor.framework.service.CacheService;
import com.sds.acube.luxor.portal.service.GroupPortalService;
import com.sds.acube.luxor.portal.service.PortalPageService;
import com.sds.acube.luxor.portal.vo.GroupPortalVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;


/**
 * 
 */
public class LuxorDispatcherServlet extends DispatcherServlet {
	private static final long serialVersionUID = 6324027497250340696L;
	private Log logger = LogFactory.getLog(LuxorDispatcherServlet.class);
	private PortalPageService pageService;
	private GroupPortalService groupPortalService;

	
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		try {
			LuxorConfig.init(getWebApplicationContext());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// ContextRoot 절대경로 저장
		LuxorConfig.setContextRootRealPath(arg0.getServletContext().getRealPath("/"));
		List<String> serviceList = LuxorConfig.getList("Common", "SERVICE_LOADER");
		Class[] parameterClasses = new Class[1];
		parameterClasses[0] = Object.class;
		Object[] parameterObjects = new Object[1];
		parameterObjects[0] = getWebApplicationContext();
		
		for (int i = 0; i < serviceList.size(); i++) {
			String serviceFQNM = (String) serviceList.get(i);
			Class service = null;
			try {
				service = Class.forName(serviceFQNM);
				Object classObj = service.newInstance();
				if (classObj instanceof BaseHandle) {
					logger.info("Starting handler thread... : " + classObj);
					BaseHandle h = (BaseHandle) classObj;
					h.start();
				} else {
					Method methodFunction = service.getMethod("init", parameterClasses);
					methodFunction.invoke(service, parameterObjects);
				}
			} catch (ClassNotFoundException e) {
				//logger.error(serviceFQNM + " is not found.", e);
			} catch (NoSuchMethodException e) {
				//logger.error(serviceFQNM + ".init() method is not found.", e);
			} catch (Exception e) {
				//logger.error(e.getMessage(), e);
			}
		}
		
		// Cache Monitor Invoker (분산환경인 경우에만 시작됨)
		if ("Y".equals(LuxorConfig.getString("CACHE.IS_MULTI_CONTAINER"))) {
			// Cache check interval (초단위)
			int interval = LuxorConfig.getInt("Common", "CACHE.CHECK_INTERVAL");
			ApplicationContext context = getWebApplicationContext();
			CacheService cacheService = (CacheService) context.getBean("cacheService");
			MemoryCacheMonitor cacheMonitor = MemoryCacheMonitor.getInstance();
			cacheMonitor.setCacheService(cacheService);
			cacheMonitor.setCheckSec(interval);
			cacheMonitor.start();
		}
		
		WebApplicationContext context = getWebApplicationContext();
		pageService = (PortalPageService) context.getBean("portalPageService");
		groupPortalService = (GroupPortalService) context.getBean("groupPortalService");
	}

	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("Y".equals(UtilRequest.getString(request,"_reset"))) {
			this.init(this.getServletConfig());
			logger.info("Context Reloaded!!!");
		}
		
		if (request.getRequestURI().indexOf("page/index.do") != -1) {
			HttpSession session = request.getSession();
			UserProfileVO userProfile = (UserProfileVO) session.getAttribute("userProfile");
			
			//포탈 Id가 파라메터로 넘어오면,
			if(!UtilRequest.getString(request, "portalId").endsWith("")) {
				session.setAttribute("PORTAL_ID", UtilRequest.getString(request, "portalId"));
			}
			
			// 로그인을 하지 않은 경우
			if (userProfile == null) {
				PortalPageVO page = new PortalPageVO();
				String pageId = UtilRequest.getString(request,"pageId");
				if(pageId==null) {
					throw new Exception("Invalid Access - No Page ID");
				}
				page.setPageId(pageId);
				
				CommonVO r = pageService.getGlobalServiceIds(page);
				
				logger.debug("LuxorDispatcherServlet.doService():r.getTenantId()=" + r.getTenantId());
				logger.debug("LuxorDispatcherServlet.doService():r.getPortalId()=" + r.getPortalId());
				
				GroupPortalVO groupPortalVO = new GroupPortalVO();
				groupPortalVO.setTenantId(r.getTenantId());
				groupPortalVO.setPortalId(r.getPortalId());
				
				groupPortalVO = groupPortalService.get(groupPortalVO);
				String compId = groupPortalVO.getRelatedCompid();
				
				List accessIdList = new ArrayList();
				accessIdList.add("GUEST");

				session.setAttribute("LANG_TYPE", new Locale("ko"));
				session.setAttribute("TENANT_ID", r.getTenantId());
				session.setAttribute("PORTAL_ID", r.getPortalId());
				session.setAttribute("COMP_ID", compId);
				session.setAttribute("ACCESS_ID_LIST", accessIdList);
			}
		}
		
		String strStartTime = getTime();
		super.doService(request, response);
		outRequestTime(request, strStartTime);
	}


	public void destroy() {
		// Stop Cache monitor thread loop
		MemoryCacheMonitor.getInstance().stopThread();
	}


	protected String getTime() {
		return DateUtil.getCurrentTime17();
	}


	protected void outRequestTime(HttpServletRequest request, String strStartTime) {
		String strEndTime = DateUtil.getCurrentTime17();
		String strUrl = request.getRequestURL().toString();
		StringBuffer strBuf = new StringBuffer();
		String strBetweenTime = DateUtil.getTimeBetweenPeriod(strStartTime.substring(8, 17), strEndTime.substring(8, 17));
		strBuf.append("요청 URL : ").append(strUrl).append("\n");
		strBuf.append("수행시간 : ").append(strBetweenTime.substring(0, 2)).append("시 ").append(strBetweenTime.substring(2, 4)).append("분 ").append(strBetweenTime.substring(4, 6)).append(".").append(strBetweenTime.substring(6, 9)).append("초\n");
		strBuf.append("요청시간 : ").append(strStartTime.substring(8, 10)).append("시 ").append(strStartTime.substring(10, 12)).append("분 ").append(strStartTime.substring(12, 14)).append(".").append(strStartTime.substring(14, 17)).append("초\n");
		strBuf.append("Client IP : ").append(request.getRemoteAddr());
		logger.debug(strBuf.toString());
	}
}
