/*
 * @(#) LogoutSysController.java 2010. 8. 17.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.LogoutSysService;
import com.sds.acube.luxor.portal.vo.LogoutSysVO;


/**
 * 
 * @author  Alex, Eum
 * @version $Revision: 1.2.2.1 $ $Date: 2011/02/10 06:05:42 $
 */
public class LogoutSysController extends BaseController {
	private LogoutSysService logoutSysService;
	private String listForm;
	private String registerForm;
	
	/**
	 * @param registerForm The registerForm to set.
	 */
	public void setRegisterForm(String registerForm) {
		this.registerForm = registerForm;
	}

	/**
	 * @param logoutSysService The logoutSysService to set.
	 */
	public void setLogoutSysService(LogoutSysService logoutSysService) {
		this.logoutSysService = logoutSysService;
	}
	
	/**
	 * @param listForm The listForm to set.
	 */
	public void setListForm(String listForm) {
		this.listForm = listForm;
	}
	
	/**
	 * 등록폼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView registerForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("registerForm called...");
		ModelAndView mav = new ModelAndView(registerForm);
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);
		mav.addObject("param", param);
		return mav;
	}
	
	/**
	 * 목록 전체
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getListAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getListAll called...");
		ModelAndView mav = new ModelAndView(listForm);
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);

		List<LogoutSysVO> list = logoutSysService.getListAll(param);
		
		mav.addObject("param", param);
		mav.addObject("list", list);
		return mav;
	}
	
	/**
	 * 목록
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("getList called...");
		
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);

		List<LogoutSysVO> list = logoutSysService.getList(param);
		LogoutSysVO[] logoutSys = new LogoutSysVO[list.size()];
		list.toArray(logoutSys);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", logoutSys);
		return mav;
	}
	
	/**
	 * 등록/수정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView apply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("apply called...");
		ModelAndView mav = new ModelAndView();
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);
        int r = logoutSysService.apply(param);
		
        if(r == 0) {
        	throw new Exception("apply is failed.");
        }else{
        	param.setMessageName("success");
        }
        
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * 사용으로 설정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView toEnable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("toEnable called...");
		ModelAndView mav = new ModelAndView();
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);
        int r = logoutSysService.toEnable(param);
		
        if(r == 0) {
        	throw new Exception("to Enable is failed.");
        }else{
        	param.setMessageName("success");
        }
        
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * 비사용으로 설정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView toUnable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("toUnable called...");
		ModelAndView mav = new ModelAndView();
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);
        int r = logoutSysService.toUnable(param);
		
        if(r == 0) {
        	throw new Exception("to Unable is failed.");
        }else{
        	param.setMessageName("success");
        }
        
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * 시스템에 등록된 '사용' 상태의 세션종료 url을 호출
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView logoutAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("logoutAll called...");
		ModelAndView mav = new ModelAndView();
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);
		
        if(!logoutSysService.logoutAll(param)) {
        	throw new Exception("logout all is failed.");
        }else{
        	param.setMessageName("success");
        }
        
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("delete called...");
		ModelAndView mav = new ModelAndView();
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);
        int r = logoutSysService.delete(param);
		
        if(r == 0) {
        	throw new Exception("delete is failed.");
        }else{
        	param.setMessageName("success");
        }
        
		mav.addObject("param", param);
		mav.addObject("jsonResult", param);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	/**
	 * 조회
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("get called...");
		ModelAndView mav = new ModelAndView();
		LogoutSysVO param = new LogoutSysVO();
		bind(request, param);
		LogoutSysVO r = logoutSysService.get(param);
		
		mav.addObject("param", param);
		mav.addObject("jsonResult", r);
		return mav;
	}
}
