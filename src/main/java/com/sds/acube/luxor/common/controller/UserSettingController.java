package com.sds.acube.luxor.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.sds.acube.luxor.common.service.UserSettingService;
import com.sds.acube.luxor.common.vo.UserSettingVO;
import com.sds.acube.luxor.framework.controller.BaseController;

public class UserSettingController extends BaseController {
	private String clearUserSettingView;
	private UserSettingService userSettingService;

	
	public void setClearUserSettingView(String clearUserSettingView) {
    	this.clearUserSettingView = clearUserSettingView;
    }

	public void setUserSettingService(UserSettingService userSettingService) {
    	this.userSettingService = userSettingService;
    }
	
	
	public ModelAndView insertUserSetting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSettingVO paramVO = new UserSettingVO(); 
		bind(request, paramVO);
		
		paramVO.setUserId(paramVO.getRegUserId());
		
		boolean result = userSettingService.insertUserSetting(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}


	public ModelAndView updateUserSetting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSettingVO paramVO = new UserSettingVO(); 
		bind(request, paramVO);
		
		paramVO.setUserId(paramVO.getRegUserId());
		
		boolean result = userSettingService.updateUserSetting(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * 선택된 사용자의 선택된 개인화 정보를 삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteUserSetting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSettingVO paramVO = new UserSettingVO();
		bind(request, paramVO);
		
		paramVO.setUserId(paramVO.getRegUserId());
		
		loginCheck(request);
		
		boolean result = userSettingService.deleteUserSetting(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * 선택된 사용자의 모든 개인화 정보를 삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteUserAllSetting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSettingVO paramVO = new UserSettingVO();
		bind(request, paramVO);
		
		loginCheck(request);
		
		boolean result = userSettingService.deleteUserAllSetting(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}


	/**
	 * 모든 사용자의 모든 개인화 설정을 삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteAllSetting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSettingVO paramVO = new UserSettingVO();
		bind(request, paramVO);

		adminCheck(request);
		
		boolean result = userSettingService.deleteAllSetting(paramVO);

		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	public ModelAndView getUserSetting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSettingVO paramVO = new UserSettingVO(); 
		bind(request, paramVO);
		
		paramVO.setUserId(paramVO.getRegUserId());
		
		UserSettingVO result = userSettingService.getUserSetting(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}
	

	public ModelAndView getUserSettingList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSettingVO paramVO = new UserSettingVO(); 
		bind(request, paramVO);
		
		paramVO.setUserId(paramVO.getRegUserId());
		
		List list = userSettingService.getUserSettingList(paramVO);
		
		UserSettingVO[] result = new UserSettingVO[list.size()];
		list.toArray(result);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		return mav;
	}

	
	public ModelAndView clearUserSettingView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView(clearUserSettingView);
	}
}
