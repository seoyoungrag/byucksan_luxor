package com.sds.acube.luxor.framework.interceptor;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sds.acube.luxor.common.service.LoginService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.portal.vo.AdminUserVO;

/**
 * 모든 Controller가 호출될때 Interceptor됨 
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
	private LoginService loginService = null;
	private MessageSource messageSource = null;
	
	@Override
	/**
	 * Controller가 호출된기 전에 수행하는 작업
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hadler) throws Exception {
		// 아직 설치가 끝나지 않은 경우에는 관리자 체크 안함
		String installed = LuxorConfig.getString("BASIC.INSTALLED");
		if("N".equals(installed)) {
			return true;
		}
		
		String uri = request.getRequestURI();
		
		// Admin Page Access Check
		if(uri.contains("/admin/")) {
			AdminUserVO adminProfile = (AdminUserVO)request.getSession().getAttribute("adminProfile");
			if(adminProfile==null) {
				CommonUtil.script(response, "top.location.href = '/ep/luxor/admin/login.jsp'");
				return false;
			}
		}

		String limitConcurrentLogin = LuxorConfig.getEnvString(request, "CONCURRENT_LOGIN_LIMIT");
		String limitMethod = LuxorConfig.getEnvString(request, "CONCURRENT_LOGIN_METHOD");
		
		// 중복 로그인 체크
		// LLE : Last Login Enter (먼저 로그인해있는 사람은 로그아웃되고 새로 로그인하는 사람이 들어간다)
		if("Y".equals(limitConcurrentLogin) && "LLE".equals(limitMethod)) {
			String mvcBeanId = UtilRequest.getString(request,"mvcBeanId");
			String methodName = UtilRequest.getString(request,"methodName");
			
			// 로그인하는 경우는 건너뛰고 로그인 후 요청에 대해서만 체크함
			if(uri.contains("adminLoginProcess.do") ||
				(uri.contains("request.json") && "loginController".equals(mvcBeanId) && "loginProcessByPortlet".equals(methodName))) {
				return true;
			} else {
				String tenantId = "";
				String portalId = "";
				String loginId = "";
				boolean isLogin = false;

				UserProfileVO userProfileVO = (UserProfileVO) request.getSession().getAttribute("userProfile");
				if(userProfileVO!=null) {
					tenantId = userProfileVO.getTenantId();
					portalId = userProfileVO.getPortalId();
					loginId = userProfileVO.getLoginId();
				}
				
				String loginIp = request.getRemoteAddr();
				String sessionId = request.getSession().getId();

				LoginHistoryVO vo = new LoginHistoryVO();
				if(tenantId.equals("")) {
					vo.setTenantId((String) request.getSession().getAttribute("TENANT_ID"));
				} else {
					vo.setTenantId(tenantId);
				}
				if(portalId.equals("")) {
					vo.setPortalId((String) request.getSession().getAttribute("PORTAL_ID"));
				} else {
					vo.setPortalId(portalId);
				}
				vo.setLoginId(loginId);
				vo.setLoginIp(loginIp);
				vo.setSessionId(sessionId);

				if(loginService==null) {
					ServletContext sc = request.getSession().getServletContext();
					WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
					loginService = (LoginService)ctx.getBean("loginService");
				}

				// 내 login id로 나보다 이후에 로그인한 ip가 내 ip와 다를 경우
				isLogin = loginService.checkLoginLLE(vo);	
				
				// 인터셉터가 모든 컨트롤러에서 다 걸리기때문에 로그인 튕겨내는건 페이지 호출할때만 체크함
				// 사용자 페이지는 항상 page/index.do로 들어가고, 관리자페이지는 항상 uri.indexOf("admin/index.do")
				// 관리자만 LLE를 쓴다거나 사용자만 해당하는 경우에 If문에서 빼주기만 하면 됩니다.
				// 개발서버에 admin을 LLE로 둘 경우 어드민을 여럿 등록해야하므로 왠만하면 빼는게..
				// 제품 스펙은 admin page 의 경우 중복 로그인을 허용하도록 되어있음
				if(isLogin && (uri.indexOf("page/index.do") > -1)) {
					
					HttpSession session = request.getSession();
					Locale langType = (Locale)session.getAttribute("LANG_TYPE");
					
					if(messageSource==null) {
						ServletContext sc = request.getSession().getServletContext();
						WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
						messageSource = (MessageSource)ctx.getBean("messageSource");
					}
					
					String alertMessage = messageSource.getMessage("portal.alert.msg.77", null, langType);
					session.invalidate();
				
					if(uri.indexOf("page/index.do") > -1) {
						CommonUtil.script(response, "alert('"+alertMessage+"');top.location.href = '/ep';");
					} else if(uri.indexOf("admin/index.do") > -1) {
						CommonUtil.script(response, "alert('"+alertMessage+"');top.location.href = '/ep/luxor/admin';");
					}
					return false;
				}
			}
		}
		
		UserProfileVO userProfileVO = (UserProfileVO) request.getSession().getAttribute("userProfile");
        
		// 로그인 페이지가 바뀌었으므로...
        if((userProfileVO == null || userProfileVO.getLoginId().equals("GUEST")) && (uri.indexOf("page/main.do") > -1)) {
          
          HttpSession session = request.getSession();
          Locale langType = (Locale)session.getAttribute("LANG_TYPE");
          
          if(messageSource==null) {
              ServletContext sc = request.getSession().getServletContext();
              WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
              messageSource = (MessageSource)ctx.getBean("messageSource");
          }
          
          String alertMessage = messageSource.getMessage("portal.authorization.msg.2", null, langType);
          session.invalidate();
      
          if(uri.indexOf("page/main.do") > -1) {
              CommonUtil.script(response, "alert('"+alertMessage+"');top.location.href = '/ep/bics_login.jsp';");
          } 
          return false;
        }
		
		return true;
	}
	
	
	/**
	 * Controller가 호출되고나서 수행하는 작업
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object hadler, ModelAndView mav) throws Exception {
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object hadler, Exception exception) throws Exception {
	}
}
