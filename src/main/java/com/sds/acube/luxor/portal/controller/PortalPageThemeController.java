package com.sds.acube.luxor.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.anyframe.pagination.Page;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.controller.BaseController;
import com.sds.acube.luxor.portal.service.PortalPageThemeService;
import com.sds.acube.luxor.portal.service.PortalPageService;
import com.sds.acube.luxor.portal.vo.PortalPageThemeVO;
import com.sds.acube.luxor.portal.vo.PortalPageVO;


public class PortalPageThemeController extends BaseController {
	private PortalPageService portalPageService;
	private PortalPageThemeService portalPageThemeService;
	private String pageThemeList;
	private String pageThemeCollection;
	
	public void setPortalPageService(PortalPageService portalPageService) {
    	this.portalPageService = portalPageService;
    }
	public void setPortalPageThemeService(PortalPageThemeService portalPageThemeService) {
		this.portalPageThemeService = portalPageThemeService;
	}
	public void setPageThemeList(String pageThemeList) {
    	this.pageThemeList = pageThemeList;
    }
	public void setPageThemeCollection(String pageThemeCollection) {
    	this.pageThemeCollection = pageThemeCollection;
    }
	
	
	public ModelAndView insertPageTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageThemeVO paramVO = new PortalPageThemeVO();
		bind(request, paramVO);
		
		paramVO.setThemeId(CommonUtil.generateId("L"));
		
		boolean result = portalPageThemeService.insertPageTheme(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	
	public ModelAndView updatePageTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageThemeVO paramVO = new PortalPageThemeVO();
		bind(request, paramVO);
		
		boolean result = portalPageThemeService.updatePageTheme(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}
	
	public ModelAndView setDefaultPageTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageThemeVO paramVO = new PortalPageThemeVO();
		bind(request, paramVO);
		
		boolean result = portalPageThemeService.setDefaultPageTheme(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}

	public ModelAndView deletePageTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageThemeVO paramVO = new PortalPageThemeVO();
		bind(request, paramVO);
		
		boolean result = portalPageThemeService.deletePageTheme(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", result);
		mav.addObject("ADMIN_CHECK", true);
		return mav;
	}


	public ModelAndView getPageTheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageThemeVO paramVO = new PortalPageThemeVO();
		bind(request, paramVO);
		
		PortalPageThemeVO resultVO = portalPageThemeService.getPageTheme(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}

	/**
	 * callJson으로 호출
	 *  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageThemeAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageThemeVO paramVO = new PortalPageThemeVO();
		bind(request, paramVO);
		
		PortalPageThemeVO resultVO = portalPageThemeService.getPageTheme(paramVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", resultVO);
		return mav;
	}
	
	/**
	 * 레이아웃 관리시 목록
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageThemeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageThemeVO paramVO = new PortalPageThemeVO();
		bind(request, paramVO);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		Page page = portalPageThemeService.getPageThemeList(paramVO);
		if(page==null) {
			throw new Exception("목록 조회 실패");
		}
	
		ModelAndView mav = new ModelAndView(pageThemeList);
		mav.addObject("page", page);
		return mav;
	}


	/**
	 * 메인 페이지의 트리에서 노드 선택시 Ajax로 호출되어 우측 리스트를 가져옴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageThemeCollection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PortalPageVO pageVO = new PortalPageVO();
		PortalPageThemeVO paramVO = new PortalPageThemeVO();
		
		bind(request, pageVO);
		bind(request, paramVO);
		
		paramVO.setPageSize(LuxorConfig.getEnvInt(request, "PAGE_LIST_COUNT"));
		
		pageVO = portalPageService.getPageForAdmin(pageVO);
		if(pageVO==null) {
			pageVO = new PortalPageVO();
		}
		
		Page page = portalPageThemeService.getPageThemeList(paramVO);
		
		List list = (List)page.getList();
		PortalPageThemeVO[] theme = new PortalPageThemeVO[list.size()];
		list.toArray(theme);

		ModelAndView mav = new ModelAndView(pageThemeCollection);
		mav.addObject("theme", theme);
		mav.addObject("selectedThemeId", pageVO.getPageThemeId());
		return mav;
	}

}
