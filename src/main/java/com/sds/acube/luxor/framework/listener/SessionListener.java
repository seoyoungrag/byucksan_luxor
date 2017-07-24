/*
 * @(#) SessionListener.java 2010. 8. 16.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.framework.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sds.acube.luxor.common.ConstantList;
import com.sds.acube.luxor.common.service.LoginService;
import com.sds.acube.luxor.common.vo.LoginHistoryVO;
import com.sds.acube.luxor.common.vo.UserProfileVO;


/**
 * 세션이 생성되거나 종료될때 이벤트를 받아서 추가적인 작업 정의
 */
public class SessionListener implements HttpSessionListener {
	private Log logger = LogFactory.getLog(this.getClass().getName());


	/**
	 * 세션이 생성될 때 호출됨
	 */
	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		try {} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * 세션이 사라질 때(invalidate) 호출됨
	 * 사용자 로그아웃 시간 기록함
	 */
	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		try {
			HttpSession session = httpsessionevent.getSession();
			ServletContext sc = session.getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			LoginService loginService = (LoginService) ctx.getBean("loginService");
			
			LoginHistoryVO vo = new LoginHistoryVO();
			vo.setSessionId(session.getId()); // Session Id
			UserProfileVO userProfile = (UserProfileVO) session.getAttribute("userProfile");
			
			// 로그아웃 시간 기록  
			// if (userProfile != null) { // 2013.08.26 수정 : LOGIN 하지 않은 GUEST 일 경우에는 호출하지 않도록 수정
			if(userProfile != null && !ConstantList.GUEST.equals(userProfile.getLoginId())){
				logger.info(userProfile.getLoginId() + "'s session destroyed");
				try {
					loginService.updateLoginHistory(vo);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			
			// 시스템에 등록된 '사용' 상태의 세션종료 호출
			/*
			 * WebApplicationContext ctx2 = WebApplicationContextUtils.getWebApplicationContext(sc);
			 * LogoutSysService logoutSysService = (LogoutSysService)ctx2.getBean("logoutSysService");
			 * 
			 * LogoutSysVO param = new LogoutSysVO();
			 * String tenantId = (String) httpsessionevent.getSession().getAttribute("TENANT_ID");
			 * String portalId = (String) httpsessionevent.getSession().getAttribute("PORTAL_ID");
			 * String langType = "";
			 * Locale locale = (Locale) httpsessionevent.getSession().getAttribute("LANG_TYPE");
			 * langType = locale.toString();
			 * 
			 * param.setTenantId(tenantId);
			 * param.setPortalId(portalId);
			 * param.setLangType(langType);
			 * 
			 * logoutSysService.logoutAll(param);
			 */
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
